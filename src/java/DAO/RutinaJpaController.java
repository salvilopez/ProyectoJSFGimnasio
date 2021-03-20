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
import DTO.Cliente;
import DTO.Rutina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Salvi
 */
public class RutinaJpaController implements Serializable {

    public RutinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rutina rutina) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente nombreUsuario = rutina.getNombreUsuario();
            if (nombreUsuario != null) {
                nombreUsuario = em.getReference(nombreUsuario.getClass(), nombreUsuario.getNombreUsuario());
                rutina.setNombreUsuario(nombreUsuario);
            }
            em.persist(rutina);
            if (nombreUsuario != null) {
                nombreUsuario.getRutinaList().add(rutina);
                nombreUsuario = em.merge(nombreUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rutina rutina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rutina persistentRutina = em.find(Rutina.class, rutina.getCodRutina());
            Cliente nombreUsuarioOld = persistentRutina.getNombreUsuario();
            Cliente nombreUsuarioNew = rutina.getNombreUsuario();
            if (nombreUsuarioNew != null) {
                nombreUsuarioNew = em.getReference(nombreUsuarioNew.getClass(), nombreUsuarioNew.getNombreUsuario());
                rutina.setNombreUsuario(nombreUsuarioNew);
            }
            rutina = em.merge(rutina);
            if (nombreUsuarioOld != null && !nombreUsuarioOld.equals(nombreUsuarioNew)) {
                nombreUsuarioOld.getRutinaList().remove(rutina);
                nombreUsuarioOld = em.merge(nombreUsuarioOld);
            }
            if (nombreUsuarioNew != null && !nombreUsuarioNew.equals(nombreUsuarioOld)) {
                nombreUsuarioNew.getRutinaList().add(rutina);
                nombreUsuarioNew = em.merge(nombreUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rutina.getCodRutina();
                if (findRutina(id) == null) {
                    throw new NonexistentEntityException("The rutina with id " + id + " no longer exists.");
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
            Rutina rutina;
            try {
                rutina = em.getReference(Rutina.class, id);
                rutina.getCodRutina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rutina with id " + id + " no longer exists.", enfe);
            }
            Cliente nombreUsuario = rutina.getNombreUsuario();
            if (nombreUsuario != null) {
                nombreUsuario.getRutinaList().remove(rutina);
                nombreUsuario = em.merge(nombreUsuario);
            }
            em.remove(rutina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rutina> findRutinaEntities() {
        return findRutinaEntities(true, -1, -1);
    }

    public List<Rutina> findRutinaEntities(int maxResults, int firstResult) {
        return findRutinaEntities(false, maxResults, firstResult);
    }

    private List<Rutina> findRutinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rutina.class));
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

    public Rutina findRutina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rutina.class, id);
        } finally {
            em.close();
        }
    }

    public int getRutinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rutina> rt = cq.from(Rutina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Rutina> traeRutinas(String dia) {
        EntityManager em = getEntityManager();
        TypedQuery<Rutina> q = em.createNamedQuery("Rutina.findByDia", Rutina.class);
        q.setParameter("dia", dia);

        return q.getResultList();
    }
}
