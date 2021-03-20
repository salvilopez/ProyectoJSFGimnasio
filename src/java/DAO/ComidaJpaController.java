/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Comida;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Dieta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Salvi
 */
public class ComidaJpaController implements Serializable {

    public ComidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comida comida) {
        if (comida.getDietaList() == null) {
            comida.setDietaList(new ArrayList<Dieta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dieta> attachedDietaList = new ArrayList<Dieta>();
            for (Dieta dietaListDietaToAttach : comida.getDietaList()) {
                dietaListDietaToAttach = em.getReference(dietaListDietaToAttach.getClass(), dietaListDietaToAttach.getCodDieta());
                attachedDietaList.add(dietaListDietaToAttach);
            }
            comida.setDietaList(attachedDietaList);
            em.persist(comida);
            for (Dieta dietaListDieta : comida.getDietaList()) {
                Comida oldCodComidaOfDietaListDieta = dietaListDieta.getCodComida();
                dietaListDieta.setCodComida(comida);
                dietaListDieta = em.merge(dietaListDieta);
                if (oldCodComidaOfDietaListDieta != null) {
                    oldCodComidaOfDietaListDieta.getDietaList().remove(dietaListDieta);
                    oldCodComidaOfDietaListDieta = em.merge(oldCodComidaOfDietaListDieta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comida comida) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comida persistentComida = em.find(Comida.class, comida.getCodComida());
            List<Dieta> dietaListOld = persistentComida.getDietaList();
            List<Dieta> dietaListNew = comida.getDietaList();
            List<String> illegalOrphanMessages = null;
            for (Dieta dietaListOldDieta : dietaListOld) {
                if (!dietaListNew.contains(dietaListOldDieta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dieta " + dietaListOldDieta + " since its codComida field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Dieta> attachedDietaListNew = new ArrayList<Dieta>();
            for (Dieta dietaListNewDietaToAttach : dietaListNew) {
                dietaListNewDietaToAttach = em.getReference(dietaListNewDietaToAttach.getClass(), dietaListNewDietaToAttach.getCodDieta());
                attachedDietaListNew.add(dietaListNewDietaToAttach);
            }
            dietaListNew = attachedDietaListNew;
            comida.setDietaList(dietaListNew);
            comida = em.merge(comida);
            for (Dieta dietaListNewDieta : dietaListNew) {
                if (!dietaListOld.contains(dietaListNewDieta)) {
                    Comida oldCodComidaOfDietaListNewDieta = dietaListNewDieta.getCodComida();
                    dietaListNewDieta.setCodComida(comida);
                    dietaListNewDieta = em.merge(dietaListNewDieta);
                    if (oldCodComidaOfDietaListNewDieta != null && !oldCodComidaOfDietaListNewDieta.equals(comida)) {
                        oldCodComidaOfDietaListNewDieta.getDietaList().remove(dietaListNewDieta);
                        oldCodComidaOfDietaListNewDieta = em.merge(oldCodComidaOfDietaListNewDieta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comida.getCodComida();
                if (findComida(id) == null) {
                    throw new NonexistentEntityException("The comida with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comida comida;
            try {
                comida = em.getReference(Comida.class, id);
                comida.getCodComida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comida with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Dieta> dietaListOrphanCheck = comida.getDietaList();
            for (Dieta dietaListOrphanCheckDieta : dietaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Comida (" + comida + ") cannot be destroyed since the Dieta " + dietaListOrphanCheckDieta + " in its dietaList field has a non-nullable codComida field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(comida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comida> findComidaEntities() {
        return findComidaEntities(true, -1, -1);
    }

    public List<Comida> findComidaEntities(int maxResults, int firstResult) {
        return findComidaEntities(false, maxResults, firstResult);
    }

    private List<Comida> findComidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comida.class));
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

    public Comida findComida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comida.class, id);
        } finally {
            em.close();
        }
    }

    public int getComidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comida> rt = cq.from(Comida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
