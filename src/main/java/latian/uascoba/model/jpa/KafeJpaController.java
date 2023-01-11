/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latian.uascoba.model.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import latian.uascoba.model.entity.Kafe;
import latian.uascoba.model.jpa.exceptions.NonexistentEntityException;
import latian.uascoba.model.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author STRIX
 */
public class KafeJpaController implements Serializable {

    public KafeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("latian_uascoba_jar_0.0.1-SNAPSHOTPU");

    public KafeJpaController() {
    }
   

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kafe kafe) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kafe);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKafe(kafe.getId()) != null) {
                throw new PreexistingEntityException("Kafe " + kafe + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kafe kafe) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kafe = em.merge(kafe);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kafe.getId();
                if (findKafe(id) == null) {
                    throw new NonexistentEntityException("The kafe with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kafe kafe;
            try {
                kafe = em.getReference(Kafe.class, id);
                kafe.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kafe with id " + id + " no longer exists.", enfe);
            }
            em.remove(kafe);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kafe> findKafeEntities() {
        return findKafeEntities(true, -1, -1);
    }

    public List<Kafe> findKafeEntities(int maxResults, int firstResult) {
        return findKafeEntities(false, maxResults, firstResult);
    }

    private List<Kafe> findKafeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kafe.class));
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

    public Kafe findKafe(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kafe.class, id);
        } finally {
            em.close();
        }
    }

    public int getKafeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kafe> rt = cq.from(Kafe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
