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
import DTO.Sala;
import DTO.Cliente;
import DTO.Reserva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Salvi
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sala codSala = reserva.getCodSala();
            if (codSala != null) {
                codSala = em.getReference(codSala.getClass(), codSala.getCodigoSala());
                reserva.setCodSala(codSala);
            }
            Cliente nombreUsuario = reserva.getNombreUsuario();
            if (nombreUsuario != null) {
                nombreUsuario = em.getReference(nombreUsuario.getClass(), nombreUsuario.getNombreUsuario());
                reserva.setNombreUsuario(nombreUsuario);
            }
            em.persist(reserva);
            if (codSala != null) {
                codSala.getReservaList().add(reserva);
                codSala = em.merge(codSala);
            }
            if (nombreUsuario != null) {
                nombreUsuario.getReservaList().add(reserva);
                nombreUsuario = em.merge(nombreUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getCodCita());
            Sala codSalaOld = persistentReserva.getCodSala();
            Sala codSalaNew = reserva.getCodSala();
            Cliente nombreUsuarioOld = persistentReserva.getNombreUsuario();
            Cliente nombreUsuarioNew = reserva.getNombreUsuario();
            if (codSalaNew != null) {
                codSalaNew = em.getReference(codSalaNew.getClass(), codSalaNew.getCodigoSala());
                reserva.setCodSala(codSalaNew);
            }
            if (nombreUsuarioNew != null) {
                nombreUsuarioNew = em.getReference(nombreUsuarioNew.getClass(), nombreUsuarioNew.getNombreUsuario());
                reserva.setNombreUsuario(nombreUsuarioNew);
            }
            reserva = em.merge(reserva);
            if (codSalaOld != null && !codSalaOld.equals(codSalaNew)) {
                codSalaOld.getReservaList().remove(reserva);
                codSalaOld = em.merge(codSalaOld);
            }
            if (codSalaNew != null && !codSalaNew.equals(codSalaOld)) {
                codSalaNew.getReservaList().add(reserva);
                codSalaNew = em.merge(codSalaNew);
            }
            if (nombreUsuarioOld != null && !nombreUsuarioOld.equals(nombreUsuarioNew)) {
                nombreUsuarioOld.getReservaList().remove(reserva);
                nombreUsuarioOld = em.merge(nombreUsuarioOld);
            }
            if (nombreUsuarioNew != null && !nombreUsuarioNew.equals(nombreUsuarioOld)) {
                nombreUsuarioNew.getReservaList().add(reserva);
                nombreUsuarioNew = em.merge(nombreUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reserva.getCodCita();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
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
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getCodCita();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Sala codSala = reserva.getCodSala();
            if (codSala != null) {
                codSala.getReservaList().remove(reserva);
                codSala = em.merge(codSala);
            }
            Cliente nombreUsuario = reserva.getNombreUsuario();
            if (nombreUsuario != null) {
                nombreUsuario.getReservaList().remove(reserva);
                nombreUsuario = em.merge(nombreUsuario);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
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

    public Reserva findReserva(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
