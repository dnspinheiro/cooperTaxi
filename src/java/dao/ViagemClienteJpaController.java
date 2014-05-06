/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import modelo.Cliente;
import modelo.Viagem;
import modelo.ViagemCliente;

/**
 *
 * @author denis
 */
public class ViagemClienteJpaController implements Serializable {

    public ViagemClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ViagemCliente viagemCliente) {
        viagemCliente.setId(null);
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = viagemCliente.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                viagemCliente.setCliente(cliente);
            }
            Viagem viagem = viagemCliente.getViagem();
            if (viagem != null) {
                viagem = em.getReference(viagem.getClass(), viagem.getId());
                viagemCliente.setViagem(viagem);
            }
            em.persist(viagemCliente);
//            if (cliente != null) {
//                cliente.getViagemClientes().add(viagemCliente);
//                cliente = em.merge(cliente);
//            }
//            if (viagem != null) {
//                viagem.getViagemClientes().add(viagemCliente);
//                viagem = em.merge(viagem);
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ViagemCliente viagemCliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            ViagemCliente persistentViagemCliente = em.find(ViagemCliente.class, viagemCliente.getId());
//            Cliente clienteOld = persistentViagemCliente.getCliente();
//            Cliente clienteNew = viagemCliente.getCliente();
//            Viagem viagemOld = persistentViagemCliente.getViagem();
//            Viagem viagemNew = viagemCliente.getViagem();
//            if (clienteNew != null) {
//                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
//                viagemCliente.setCliente(clienteNew);
//            }
//            if (viagemNew != null) {
//                viagemNew = em.getReference(viagemNew.getClass(), viagemNew.getId());
//                viagemCliente.setViagem(viagemNew);
//            }
            viagemCliente = em.merge(viagemCliente);
//            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
//                clienteOld.getViagemClientes().remove(viagemCliente);
//                clienteOld = em.merge(clienteOld);
//            }
//            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
//                clienteNew.getViagemClientes().add(viagemCliente);
//                clienteNew = em.merge(clienteNew);
//            }
//            if (viagemOld != null && !viagemOld.equals(viagemNew)) {
//                viagemOld.getViagemClientes().remove(viagemCliente);
//                viagemOld = em.merge(viagemOld);
//            }
//            if (viagemNew != null && !viagemNew.equals(viagemOld)) {
//                viagemNew.getViagemClientes().add(viagemCliente);
//                viagemNew = em.merge(viagemNew);
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = viagemCliente.getId();
                if (findViagemCliente(id) == null) {
                    throw new NonexistentEntityException("The viagemCliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Long  pesquisarViagemid(Long id){

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT id FROM ViagemCliente  WHERE VIAGEM_ID = :m");
        query.setParameter("m", id);
        List<String>  rsl = query.getResultList();
        String g = rsl.toString();
        String result = g.replace(" ", "-").substring(1, g.length()-1);
        id = Long.parseLong(result);
        return id;
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ViagemCliente viagemCliente;
            try {
                viagemCliente = em.getReference(ViagemCliente.class, id);
                viagemCliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The viagemCliente with id " + id + " no longer exists.", enfe);
            }
            //em.remove(viagemCliente.getViagem().getId());
//            Cliente cliente = viagemCliente.getCliente();
//            if (cliente != null) {
//                cliente.getViagemClientes().remove(viagemCliente);
//                cliente = em.merge(cliente);
//            }
//            Viagem viagem = viagemCliente.getViagem();
//            if (viagem != null) {
//                viagem.getViagemClientes().remove(viagemCliente);
//                viagem = em.merge(viagem);
//            }
            em.remove(viagemCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ViagemCliente> findViagemClienteEntities() {
        return findViagemClienteEntities(true, -1, -1);
    }

    public List<ViagemCliente> findViagemClienteEntities(int maxResults, int firstResult) {
        return findViagemClienteEntities(false, maxResults, firstResult);
    }

    private List<ViagemCliente> findViagemClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ViagemCliente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ViagemCliente findViagemCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ViagemCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getViagemClienteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ViagemCliente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
