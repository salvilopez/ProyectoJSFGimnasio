/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Rutina;
import java.util.ArrayList;
import java.util.List;
import DTO.Reserva;
import DTO.Dieta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Salvi
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getRutinaList() == null) {
            cliente.setRutinaList(new ArrayList<Rutina>());
        }
        if (cliente.getReservaList() == null) {
            cliente.setReservaList(new ArrayList<Reserva>());
        }
        if (cliente.getDietaList() == null) {
            cliente.setDietaList(new ArrayList<Dieta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rutina> attachedRutinaList = new ArrayList<Rutina>();
            for (Rutina rutinaListRutinaToAttach : cliente.getRutinaList()) {
                rutinaListRutinaToAttach = em.getReference(rutinaListRutinaToAttach.getClass(), rutinaListRutinaToAttach.getCodRutina());
                attachedRutinaList.add(rutinaListRutinaToAttach);
            }
            cliente.setRutinaList(attachedRutinaList);
            List<Reserva> attachedReservaList = new ArrayList<Reserva>();
            for (Reserva reservaListReservaToAttach : cliente.getReservaList()) {
                reservaListReservaToAttach = em.getReference(reservaListReservaToAttach.getClass(), reservaListReservaToAttach.getCodCita());
                attachedReservaList.add(reservaListReservaToAttach);
            }
            cliente.setReservaList(attachedReservaList);
            List<Dieta> attachedDietaList = new ArrayList<Dieta>();
            for (Dieta dietaListDietaToAttach : cliente.getDietaList()) {
                dietaListDietaToAttach = em.getReference(dietaListDietaToAttach.getClass(), dietaListDietaToAttach.getCodDieta());
                attachedDietaList.add(dietaListDietaToAttach);
            }
            cliente.setDietaList(attachedDietaList);
            em.persist(cliente);
            for (Rutina rutinaListRutina : cliente.getRutinaList()) {
                Cliente oldNombreUsuarioOfRutinaListRutina = rutinaListRutina.getNombreUsuario();
                rutinaListRutina.setNombreUsuario(cliente);
                rutinaListRutina = em.merge(rutinaListRutina);
                if (oldNombreUsuarioOfRutinaListRutina != null) {
                    oldNombreUsuarioOfRutinaListRutina.getRutinaList().remove(rutinaListRutina);
                    oldNombreUsuarioOfRutinaListRutina = em.merge(oldNombreUsuarioOfRutinaListRutina);
                }
            }
            for (Reserva reservaListReserva : cliente.getReservaList()) {
                Cliente oldNombreUsuarioOfReservaListReserva = reservaListReserva.getNombreUsuario();
                reservaListReserva.setNombreUsuario(cliente);
                reservaListReserva = em.merge(reservaListReserva);
                if (oldNombreUsuarioOfReservaListReserva != null) {
                    oldNombreUsuarioOfReservaListReserva.getReservaList().remove(reservaListReserva);
                    oldNombreUsuarioOfReservaListReserva = em.merge(oldNombreUsuarioOfReservaListReserva);
                }
            }
            for (Dieta dietaListDieta : cliente.getDietaList()) {
                Cliente oldNombreUsuarioOfDietaListDieta = dietaListDieta.getNombreUsuario();
                dietaListDieta.setNombreUsuario(cliente);
                dietaListDieta = em.merge(dietaListDieta);
                if (oldNombreUsuarioOfDietaListDieta != null) {
                    oldNombreUsuarioOfDietaListDieta.getDietaList().remove(dietaListDieta);
                    oldNombreUsuarioOfDietaListDieta = em.merge(oldNombreUsuarioOfDietaListDieta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getNombreUsuario()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getNombreUsuario());
            List<Rutina> rutinaListOld = persistentCliente.getRutinaList();
            List<Rutina> rutinaListNew = cliente.getRutinaList();
            List<Reserva> reservaListOld = persistentCliente.getReservaList();
            List<Reserva> reservaListNew = cliente.getReservaList();
            List<Dieta> dietaListOld = persistentCliente.getDietaList();
            List<Dieta> dietaListNew = cliente.getDietaList();
            List<String> illegalOrphanMessages = null;
            for (Rutina rutinaListOldRutina : rutinaListOld) {
                if (!rutinaListNew.contains(rutinaListOldRutina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rutina " + rutinaListOldRutina + " since its nombreUsuario field is not nullable.");
                }
            }
            for (Reserva reservaListOldReserva : reservaListOld) {
                if (!reservaListNew.contains(reservaListOldReserva)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reserva " + reservaListOldReserva + " since its nombreUsuario field is not nullable.");
                }
            }
            for (Dieta dietaListOldDieta : dietaListOld) {
                if (!dietaListNew.contains(dietaListOldDieta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dieta " + dietaListOldDieta + " since its nombreUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Rutina> attachedRutinaListNew = new ArrayList<Rutina>();
            for (Rutina rutinaListNewRutinaToAttach : rutinaListNew) {
                rutinaListNewRutinaToAttach = em.getReference(rutinaListNewRutinaToAttach.getClass(), rutinaListNewRutinaToAttach.getCodRutina());
                attachedRutinaListNew.add(rutinaListNewRutinaToAttach);
            }
            rutinaListNew = attachedRutinaListNew;
            cliente.setRutinaList(rutinaListNew);
            List<Reserva> attachedReservaListNew = new ArrayList<Reserva>();
            for (Reserva reservaListNewReservaToAttach : reservaListNew) {
                reservaListNewReservaToAttach = em.getReference(reservaListNewReservaToAttach.getClass(), reservaListNewReservaToAttach.getCodCita());
                attachedReservaListNew.add(reservaListNewReservaToAttach);
            }
            reservaListNew = attachedReservaListNew;
            cliente.setReservaList(reservaListNew);
            List<Dieta> attachedDietaListNew = new ArrayList<Dieta>();
            for (Dieta dietaListNewDietaToAttach : dietaListNew) {
                dietaListNewDietaToAttach = em.getReference(dietaListNewDietaToAttach.getClass(), dietaListNewDietaToAttach.getCodDieta());
                attachedDietaListNew.add(dietaListNewDietaToAttach);
            }
            dietaListNew = attachedDietaListNew;
            cliente.setDietaList(dietaListNew);
            cliente = em.merge(cliente);
            for (Rutina rutinaListNewRutina : rutinaListNew) {
                if (!rutinaListOld.contains(rutinaListNewRutina)) {
                    Cliente oldNombreUsuarioOfRutinaListNewRutina = rutinaListNewRutina.getNombreUsuario();
                    rutinaListNewRutina.setNombreUsuario(cliente);
                    rutinaListNewRutina = em.merge(rutinaListNewRutina);
                    if (oldNombreUsuarioOfRutinaListNewRutina != null && !oldNombreUsuarioOfRutinaListNewRutina.equals(cliente)) {
                        oldNombreUsuarioOfRutinaListNewRutina.getRutinaList().remove(rutinaListNewRutina);
                        oldNombreUsuarioOfRutinaListNewRutina = em.merge(oldNombreUsuarioOfRutinaListNewRutina);
                    }
                }
            }
            for (Reserva reservaListNewReserva : reservaListNew) {
                if (!reservaListOld.contains(reservaListNewReserva)) {
                    Cliente oldNombreUsuarioOfReservaListNewReserva = reservaListNewReserva.getNombreUsuario();
                    reservaListNewReserva.setNombreUsuario(cliente);
                    reservaListNewReserva = em.merge(reservaListNewReserva);
                    if (oldNombreUsuarioOfReservaListNewReserva != null && !oldNombreUsuarioOfReservaListNewReserva.equals(cliente)) {
                        oldNombreUsuarioOfReservaListNewReserva.getReservaList().remove(reservaListNewReserva);
                        oldNombreUsuarioOfReservaListNewReserva = em.merge(oldNombreUsuarioOfReservaListNewReserva);
                    }
                }
            }
            for (Dieta dietaListNewDieta : dietaListNew) {
                if (!dietaListOld.contains(dietaListNewDieta)) {
                    Cliente oldNombreUsuarioOfDietaListNewDieta = dietaListNewDieta.getNombreUsuario();
                    dietaListNewDieta.setNombreUsuario(cliente);
                    dietaListNewDieta = em.merge(dietaListNewDieta);
                    if (oldNombreUsuarioOfDietaListNewDieta != null && !oldNombreUsuarioOfDietaListNewDieta.equals(cliente)) {
                        oldNombreUsuarioOfDietaListNewDieta.getDietaList().remove(dietaListNewDieta);
                        oldNombreUsuarioOfDietaListNewDieta = em.merge(oldNombreUsuarioOfDietaListNewDieta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getNombreUsuario();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getNombreUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Rutina> rutinaListOrphanCheck = cliente.getRutinaList();
            for (Rutina rutinaListOrphanCheckRutina : rutinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Rutina " + rutinaListOrphanCheckRutina + " in its rutinaList field has a non-nullable nombreUsuario field.");
            }
            List<Reserva> reservaListOrphanCheck = cliente.getReservaList();
            for (Reserva reservaListOrphanCheckReserva : reservaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Reserva " + reservaListOrphanCheckReserva + " in its reservaList field has a non-nullable nombreUsuario field.");
            }
            List<Dieta> dietaListOrphanCheck = cliente.getDietaList();
            for (Dieta dietaListOrphanCheckDieta : dietaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Dieta " + dietaListOrphanCheckDieta + " in its dietaList field has a non-nullable nombreUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
