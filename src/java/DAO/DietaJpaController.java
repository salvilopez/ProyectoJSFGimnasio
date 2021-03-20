/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Comida;
import DTO.Cliente;
import DTO.Dieta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Salvi
 */
public class DietaJpaController implements Serializable {

    public DietaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dieta dieta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comida codComida = dieta.getCodComida();
            if (codComida != null) {
                codComida = em.getReference(codComida.getClass(), codComida.getCodComida());
                dieta.setCodComida(codComida);
            }
            Cliente nombreUsuario = dieta.getNombreUsuario();
            if (nombreUsuario != null) {
                nombreUsuario = em.getReference(nombreUsuario.getClass(), nombreUsuario.getNombreUsuario());
                dieta.setNombreUsuario(nombreUsuario);
            }
            em.persist(dieta);
            if (codComida != null) {
                codComida.getDietaList().add(dieta);
                codComida = em.merge(codComida);
            }
            if (nombreUsuario != null) {
                nombreUsuario.getDietaList().add(dieta);
                nombreUsuario = em.merge(nombreUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dieta dieta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dieta persistentDieta = em.find(Dieta.class, dieta.getCodDieta());
            Comida codComidaOld = persistentDieta.getCodComida();
            Comida codComidaNew = dieta.getCodComida();
            Cliente nombreUsuarioOld = persistentDieta.getNombreUsuario();
            Cliente nombreUsuarioNew = dieta.getNombreUsuario();
            if (codComidaNew != null) {
                codComidaNew = em.getReference(codComidaNew.getClass(), codComidaNew.getCodComida());
                dieta.setCodComida(codComidaNew);
            }
            if (nombreUsuarioNew != null) {
                nombreUsuarioNew = em.getReference(nombreUsuarioNew.getClass(), nombreUsuarioNew.getNombreUsuario());
                dieta.setNombreUsuario(nombreUsuarioNew);
            }
            dieta = em.merge(dieta);
            if (codComidaOld != null && !codComidaOld.equals(codComidaNew)) {
                codComidaOld.getDietaList().remove(dieta);
                codComidaOld = em.merge(codComidaOld);
            }
            if (codComidaNew != null && !codComidaNew.equals(codComidaOld)) {
                codComidaNew.getDietaList().add(dieta);
                codComidaNew = em.merge(codComidaNew);
            }
            if (nombreUsuarioOld != null && !nombreUsuarioOld.equals(nombreUsuarioNew)) {
                nombreUsuarioOld.getDietaList().remove(dieta);
                nombreUsuarioOld = em.merge(nombreUsuarioOld);
            }
            if (nombreUsuarioNew != null && !nombreUsuarioNew.equals(nombreUsuarioOld)) {
                nombreUsuarioNew.getDietaList().add(dieta);
                nombreUsuarioNew = em.merge(nombreUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dieta.getCodDieta();
                if (findDieta(id) == null) {
                    throw new NonexistentEntityException("The dieta with id " + id + " no longer exists.");
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
            Dieta dieta;
            try {
                dieta = em.getReference(Dieta.class, id);
                dieta.getCodDieta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dieta with id " + id + " no longer exists.", enfe);
            }
            Comida codComida = dieta.getCodComida();
            if (codComida != null) {
                codComida.getDietaList().remove(dieta);
                codComida = em.merge(codComida);
            }
            Cliente nombreUsuario = dieta.getNombreUsuario();
            if (nombreUsuario != null) {
                nombreUsuario.getDietaList().remove(dieta);
                nombreUsuario = em.merge(nombreUsuario);
            }
            em.remove(dieta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dieta> findDietaEntities() {
        return findDietaEntities(true, -1, -1);
    }

    public List<Dieta> findDietaEntities(int maxResults, int firstResult) {
        return findDietaEntities(false, maxResults, firstResult);
    }

    private List<Dieta> findDietaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dieta.class));
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

    public Dieta findDieta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dieta.class, id);
        } finally {
            em.close();
        }
    }

    public int getDietaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dieta> rt = cq.from(Dieta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
        public List<Dieta> traeDietaspordia(String dia) {
        EntityManager em = getEntityManager();
        TypedQuery<Dieta> q = em.createNamedQuery("Dieta.findByDia", Dieta.class);
        q.setParameter("dia", dia);

        return q.getResultList();
    }
         
}
