/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import CONTROLADOR.exceptions.IllegalOrphanException;
import CONTROLADOR.exceptions.NonexistentEntityException;
import CONTROLADOR.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODELO.Empleado;
import MODELO.Propiedad;
import MODELO.Visita;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Francisco
 */
public class PropiedadJpaController implements Serializable {

    public PropiedadJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DREAMHOMEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Propiedad propiedad) throws PreexistingEntityException, Exception {
        if (propiedad.getVisitaList() == null) {
            propiedad.setVisitaList(new ArrayList<Visita>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado numempleado = propiedad.getNumempleado();
            if (numempleado != null) {
                numempleado = em.getReference(numempleado.getClass(), numempleado.getNumempleado());
                propiedad.setNumempleado(numempleado);
            }
            List<Visita> attachedVisitaList = new ArrayList<Visita>();
            for (Visita visitaListVisitaToAttach : propiedad.getVisitaList()) {
                visitaListVisitaToAttach = em.getReference(visitaListVisitaToAttach.getClass(), visitaListVisitaToAttach.getNumvisita());
                attachedVisitaList.add(visitaListVisitaToAttach);
            }
            propiedad.setVisitaList(attachedVisitaList);
            em.persist(propiedad);
            if (numempleado != null) {
                numempleado.getPropiedadList().add(propiedad);
                numempleado = em.merge(numempleado);
            }
            for (Visita visitaListVisita : propiedad.getVisitaList()) {
                Propiedad oldNumpropiedadOfVisitaListVisita = visitaListVisita.getNumpropiedad();
                visitaListVisita.setNumpropiedad(propiedad);
                visitaListVisita = em.merge(visitaListVisita);
                if (oldNumpropiedadOfVisitaListVisita != null) {
                    oldNumpropiedadOfVisitaListVisita.getVisitaList().remove(visitaListVisita);
                    oldNumpropiedadOfVisitaListVisita = em.merge(oldNumpropiedadOfVisitaListVisita);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPropiedad(propiedad.getNumpropiedad()) != null) {
                throw new PreexistingEntityException("Propiedad " + propiedad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Propiedad propiedad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propiedad persistentPropiedad = em.find(Propiedad.class, propiedad.getNumpropiedad());
            Empleado numempleadoOld = persistentPropiedad.getNumempleado();
            Empleado numempleadoNew = propiedad.getNumempleado();
            List<Visita> visitaListOld = persistentPropiedad.getVisitaList();
            List<Visita> visitaListNew = propiedad.getVisitaList();
            List<String> illegalOrphanMessages = null;
            for (Visita visitaListOldVisita : visitaListOld) {
                if (!visitaListNew.contains(visitaListOldVisita)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Visita " + visitaListOldVisita + " since its numpropiedad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (numempleadoNew != null) {
                numempleadoNew = em.getReference(numempleadoNew.getClass(), numempleadoNew.getNumempleado());
                propiedad.setNumempleado(numempleadoNew);
            }
            List<Visita> attachedVisitaListNew = new ArrayList<Visita>();
            for (Visita visitaListNewVisitaToAttach : visitaListNew) {
                visitaListNewVisitaToAttach = em.getReference(visitaListNewVisitaToAttach.getClass(), visitaListNewVisitaToAttach.getNumvisita());
                attachedVisitaListNew.add(visitaListNewVisitaToAttach);
            }
            visitaListNew = attachedVisitaListNew;
            propiedad.setVisitaList(visitaListNew);
            propiedad = em.merge(propiedad);
            if (numempleadoOld != null && !numempleadoOld.equals(numempleadoNew)) {
                numempleadoOld.getPropiedadList().remove(propiedad);
                numempleadoOld = em.merge(numempleadoOld);
            }
            if (numempleadoNew != null && !numempleadoNew.equals(numempleadoOld)) {
                numempleadoNew.getPropiedadList().add(propiedad);
                numempleadoNew = em.merge(numempleadoNew);
            }
            for (Visita visitaListNewVisita : visitaListNew) {
                if (!visitaListOld.contains(visitaListNewVisita)) {
                    Propiedad oldNumpropiedadOfVisitaListNewVisita = visitaListNewVisita.getNumpropiedad();
                    visitaListNewVisita.setNumpropiedad(propiedad);
                    visitaListNewVisita = em.merge(visitaListNewVisita);
                    if (oldNumpropiedadOfVisitaListNewVisita != null && !oldNumpropiedadOfVisitaListNewVisita.equals(propiedad)) {
                        oldNumpropiedadOfVisitaListNewVisita.getVisitaList().remove(visitaListNewVisita);
                        oldNumpropiedadOfVisitaListNewVisita = em.merge(oldNumpropiedadOfVisitaListNewVisita);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = propiedad.getNumpropiedad();
                if (findPropiedad(id) == null) {
                    throw new NonexistentEntityException("The propiedad with id " + id + " no longer exists.");
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
            Propiedad propiedad;
            try {
                propiedad = em.getReference(Propiedad.class, id);
                propiedad.getNumpropiedad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The propiedad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Visita> visitaListOrphanCheck = propiedad.getVisitaList();
            for (Visita visitaListOrphanCheckVisita : visitaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Propiedad (" + propiedad + ") cannot be destroyed since the Visita " + visitaListOrphanCheckVisita + " in its visitaList field has a non-nullable numpropiedad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado numempleado = propiedad.getNumempleado();
            if (numempleado != null) {
                numempleado.getPropiedadList().remove(propiedad);
                numempleado = em.merge(numempleado);
            }
            em.remove(propiedad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Propiedad> findPropiedadEntities() {
        return findPropiedadEntities(true, -1, -1);
    }

    public List<Propiedad> findPropiedadEntities(int maxResults, int firstResult) {
        return findPropiedadEntities(false, maxResults, firstResult);
    }

    private List<Propiedad> findPropiedadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Propiedad.class));
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

    public Propiedad findPropiedad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Propiedad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPropiedadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Propiedad> rt = cq.from(Propiedad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
