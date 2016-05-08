/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import CONTROLADOR.exceptions.IllegalOrphanException;
import CONTROLADOR.exceptions.NonexistentEntityException;
import CONTROLADOR.exceptions.PreexistingEntityException;
import MODELO.Empleado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODELO.Totpropempleado;
import MODELO.Propiedad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Francisco
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) throws PreexistingEntityException, Exception {
        if (empleado.getPropiedadList() == null) {
            empleado.setPropiedadList(new ArrayList<Propiedad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Totpropempleado totpropempleado = empleado.getTotpropempleado();
            if (totpropempleado != null) {
                totpropempleado = em.getReference(totpropempleado.getClass(), totpropempleado.getNumempleado());
                empleado.setTotpropempleado(totpropempleado);
            }
            List<Propiedad> attachedPropiedadList = new ArrayList<Propiedad>();
            for (Propiedad propiedadListPropiedadToAttach : empleado.getPropiedadList()) {
                propiedadListPropiedadToAttach = em.getReference(propiedadListPropiedadToAttach.getClass(), propiedadListPropiedadToAttach.getNumpropiedad());
                attachedPropiedadList.add(propiedadListPropiedadToAttach);
            }
            empleado.setPropiedadList(attachedPropiedadList);
            em.persist(empleado);
            if (totpropempleado != null) {
                Empleado oldEmpleadoOfTotpropempleado = totpropempleado.getEmpleado();
                if (oldEmpleadoOfTotpropempleado != null) {
                    oldEmpleadoOfTotpropempleado.setTotpropempleado(null);
                    oldEmpleadoOfTotpropempleado = em.merge(oldEmpleadoOfTotpropempleado);
                }
                totpropempleado.setEmpleado(empleado);
                totpropempleado = em.merge(totpropempleado);
            }
            for (Propiedad propiedadListPropiedad : empleado.getPropiedadList()) {
                Empleado oldNumempleadoOfPropiedadListPropiedad = propiedadListPropiedad.getNumempleado();
                propiedadListPropiedad.setNumempleado(empleado);
                propiedadListPropiedad = em.merge(propiedadListPropiedad);
                if (oldNumempleadoOfPropiedadListPropiedad != null) {
                    oldNumempleadoOfPropiedadListPropiedad.getPropiedadList().remove(propiedadListPropiedad);
                    oldNumempleadoOfPropiedadListPropiedad = em.merge(oldNumempleadoOfPropiedadListPropiedad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleado(empleado.getNumempleado()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getNumempleado());
            Totpropempleado totpropempleadoOld = persistentEmpleado.getTotpropempleado();
            Totpropempleado totpropempleadoNew = empleado.getTotpropempleado();
            List<Propiedad> propiedadListOld = persistentEmpleado.getPropiedadList();
            List<Propiedad> propiedadListNew = empleado.getPropiedadList();
            List<String> illegalOrphanMessages = null;
            if (totpropempleadoOld != null && !totpropempleadoOld.equals(totpropempleadoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Totpropempleado " + totpropempleadoOld + " since its empleado field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (totpropempleadoNew != null) {
                totpropempleadoNew = em.getReference(totpropempleadoNew.getClass(), totpropempleadoNew.getNumempleado());
                empleado.setTotpropempleado(totpropempleadoNew);
            }
            List<Propiedad> attachedPropiedadListNew = new ArrayList<Propiedad>();
            for (Propiedad propiedadListNewPropiedadToAttach : propiedadListNew) {
                propiedadListNewPropiedadToAttach = em.getReference(propiedadListNewPropiedadToAttach.getClass(), propiedadListNewPropiedadToAttach.getNumpropiedad());
                attachedPropiedadListNew.add(propiedadListNewPropiedadToAttach);
            }
            propiedadListNew = attachedPropiedadListNew;
            empleado.setPropiedadList(propiedadListNew);
            empleado = em.merge(empleado);
            if (totpropempleadoNew != null && !totpropempleadoNew.equals(totpropempleadoOld)) {
                Empleado oldEmpleadoOfTotpropempleado = totpropempleadoNew.getEmpleado();
                if (oldEmpleadoOfTotpropempleado != null) {
                    oldEmpleadoOfTotpropempleado.setTotpropempleado(null);
                    oldEmpleadoOfTotpropempleado = em.merge(oldEmpleadoOfTotpropempleado);
                }
                totpropempleadoNew.setEmpleado(empleado);
                totpropempleadoNew = em.merge(totpropempleadoNew);
            }
            for (Propiedad propiedadListOldPropiedad : propiedadListOld) {
                if (!propiedadListNew.contains(propiedadListOldPropiedad)) {
                    propiedadListOldPropiedad.setNumempleado(null);
                    propiedadListOldPropiedad = em.merge(propiedadListOldPropiedad);
                }
            }
            for (Propiedad propiedadListNewPropiedad : propiedadListNew) {
                if (!propiedadListOld.contains(propiedadListNewPropiedad)) {
                    Empleado oldNumempleadoOfPropiedadListNewPropiedad = propiedadListNewPropiedad.getNumempleado();
                    propiedadListNewPropiedad.setNumempleado(empleado);
                    propiedadListNewPropiedad = em.merge(propiedadListNewPropiedad);
                    if (oldNumempleadoOfPropiedadListNewPropiedad != null && !oldNumempleadoOfPropiedadListNewPropiedad.equals(empleado)) {
                        oldNumempleadoOfPropiedadListNewPropiedad.getPropiedadList().remove(propiedadListNewPropiedad);
                        oldNumempleadoOfPropiedadListNewPropiedad = em.merge(oldNumempleadoOfPropiedadListNewPropiedad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = empleado.getNumempleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getNumempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Totpropempleado totpropempleadoOrphanCheck = empleado.getTotpropempleado();
            if (totpropempleadoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Totpropempleado " + totpropempleadoOrphanCheck + " in its totpropempleado field has a non-nullable empleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Propiedad> propiedadList = empleado.getPropiedadList();
            for (Propiedad propiedadListPropiedad : propiedadList) {
                propiedadListPropiedad.setNumempleado(null);
                propiedadListPropiedad = em.merge(propiedadListPropiedad);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Empleado findEmpleado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
}
