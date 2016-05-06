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
import java.util.Collection;
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
        if (propiedad.getVisitaCollection() == null) {
            propiedad.setVisitaCollection(new ArrayList<Visita>());
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
            Collection<Visita> attachedVisitaCollection = new ArrayList<Visita>();
            for (Visita visitaCollectionVisitaToAttach : propiedad.getVisitaCollection()) {
                visitaCollectionVisitaToAttach = em.getReference(visitaCollectionVisitaToAttach.getClass(), visitaCollectionVisitaToAttach.getNumvisita());
                attachedVisitaCollection.add(visitaCollectionVisitaToAttach);
            }
            propiedad.setVisitaCollection(attachedVisitaCollection);
            em.persist(propiedad);
            if (numempleado != null) {
                numempleado.getPropiedadCollection().add(propiedad);
                numempleado = em.merge(numempleado);
            }
            for (Visita visitaCollectionVisita : propiedad.getVisitaCollection()) {
                Propiedad oldNumpropiedadOfVisitaCollectionVisita = visitaCollectionVisita.getNumpropiedad();
                visitaCollectionVisita.setNumpropiedad(propiedad);
                visitaCollectionVisita = em.merge(visitaCollectionVisita);
                if (oldNumpropiedadOfVisitaCollectionVisita != null) {
                    oldNumpropiedadOfVisitaCollectionVisita.getVisitaCollection().remove(visitaCollectionVisita);
                    oldNumpropiedadOfVisitaCollectionVisita = em.merge(oldNumpropiedadOfVisitaCollectionVisita);
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
            Collection<Visita> visitaCollectionOld = persistentPropiedad.getVisitaCollection();
            Collection<Visita> visitaCollectionNew = propiedad.getVisitaCollection();
            List<String> illegalOrphanMessages = null;
            for (Visita visitaCollectionOldVisita : visitaCollectionOld) {
                if (!visitaCollectionNew.contains(visitaCollectionOldVisita)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Visita " + visitaCollectionOldVisita + " since its numpropiedad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (numempleadoNew != null) {
                numempleadoNew = em.getReference(numempleadoNew.getClass(), numempleadoNew.getNumempleado());
                propiedad.setNumempleado(numempleadoNew);
            }
            Collection<Visita> attachedVisitaCollectionNew = new ArrayList<Visita>();
            for (Visita visitaCollectionNewVisitaToAttach : visitaCollectionNew) {
                visitaCollectionNewVisitaToAttach = em.getReference(visitaCollectionNewVisitaToAttach.getClass(), visitaCollectionNewVisitaToAttach.getNumvisita());
                attachedVisitaCollectionNew.add(visitaCollectionNewVisitaToAttach);
            }
            visitaCollectionNew = attachedVisitaCollectionNew;
            propiedad.setVisitaCollection(visitaCollectionNew);
            propiedad = em.merge(propiedad);
            if (numempleadoOld != null && !numempleadoOld.equals(numempleadoNew)) {
                numempleadoOld.getPropiedadCollection().remove(propiedad);
                numempleadoOld = em.merge(numempleadoOld);
            }
            if (numempleadoNew != null && !numempleadoNew.equals(numempleadoOld)) {
                numempleadoNew.getPropiedadCollection().add(propiedad);
                numempleadoNew = em.merge(numempleadoNew);
            }
            for (Visita visitaCollectionNewVisita : visitaCollectionNew) {
                if (!visitaCollectionOld.contains(visitaCollectionNewVisita)) {
                    Propiedad oldNumpropiedadOfVisitaCollectionNewVisita = visitaCollectionNewVisita.getNumpropiedad();
                    visitaCollectionNewVisita.setNumpropiedad(propiedad);
                    visitaCollectionNewVisita = em.merge(visitaCollectionNewVisita);
                    if (oldNumpropiedadOfVisitaCollectionNewVisita != null && !oldNumpropiedadOfVisitaCollectionNewVisita.equals(propiedad)) {
                        oldNumpropiedadOfVisitaCollectionNewVisita.getVisitaCollection().remove(visitaCollectionNewVisita);
                        oldNumpropiedadOfVisitaCollectionNewVisita = em.merge(oldNumpropiedadOfVisitaCollectionNewVisita);
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
            Collection<Visita> visitaCollectionOrphanCheck = propiedad.getVisitaCollection();
            for (Visita visitaCollectionOrphanCheckVisita : visitaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Propiedad (" + propiedad + ") cannot be destroyed since the Visita " + visitaCollectionOrphanCheckVisita + " in its visitaCollection field has a non-nullable numpropiedad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado numempleado = propiedad.getNumempleado();
            if (numempleado != null) {
                numempleado.getPropiedadCollection().remove(propiedad);
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
