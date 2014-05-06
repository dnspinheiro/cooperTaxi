/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import modelo.Endereco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Cliente;
import modelo.RelatorioCliente;

/**
 *
 * @author denis
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<RelatorioCliente> pesquisarInfoCliente() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select NEW modelo.RelatorioCliente(c.nome, sum(l.distancia))"
                    + "               from Cliente c, ViagemCliente vc, Viagem v, Linha l "
                    + "               where v.linha.id = l.id and vc.viagem.id = v.id and vc.cliente.id = c.id "
                    + "               group by c.nome order by sum(l.distancia) desc");
            
            //select c.nome,sum(l.DISTANCIA) from viagem v,viagemCliente vc, cliente c, linha l where v.linha_id=l.id and vc.viagem_id=v.id and vc.cliente_id=c.id group by c.nome order by sum(l.DISTANCIA) desc;
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(Cliente cliente) {
        cliente.setId(null);
//        if (cliente.getViagemClientes() == null) {
//            cliente.setViagemClientes(new ArrayList<ViagemCliente>());
//        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Endereco endereco = cliente.getEndereco();
            if (endereco != null) {
                endereco = em.getReference(endereco.getClass(), endereco.getId());
                cliente.setEndereco(endereco);
            }
//            List<ViagemCliente> attachedViagemClientes = new ArrayList<ViagemCliente>();
//            for (ViagemCliente viagemClientesViagemClienteToAttach : cliente.getViagemClientes()) {
//                viagemClientesViagemClienteToAttach = em.getReference(viagemClientesViagemClienteToAttach.getClass(), viagemClientesViagemClienteToAttach.getId());
//                attachedViagemClientes.add(viagemClientesViagemClienteToAttach);
//            }
//            cliente.setViagemClientes(attachedViagemClientes);
            em.persist(cliente);
//            if (endereco != null) {
//                endereco.getClientes().add(cliente);
//                endereco = em.merge(endereco);
//            }
//            for (ViagemCliente viagemClientesViagemCliente : cliente.getViagemClientes()) {
//                Cliente oldClienteOfViagemClientesViagemCliente = viagemClientesViagemCliente.getCliente();
//                viagemClientesViagemCliente.setCliente(cliente);
//                viagemClientesViagemCliente = em.merge(viagemClientesViagemCliente);
//                if (oldClienteOfViagemClientesViagemCliente != null) {
//                    oldClienteOfViagemClientesViagemCliente.getViagemClientes().remove(viagemClientesViagemCliente);
//                    oldClienteOfViagemClientesViagemCliente = em.merge(oldClienteOfViagemClientesViagemCliente);
//                }
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
//            Endereco enderecoOld = persistentCliente.getEndereco();
//            Endereco enderecoNew = cliente.getEndereco();
//            List<ViagemCliente> viagemClientesOld = persistentCliente.getViagemClientes();
//            List<ViagemCliente> viagemClientesNew = cliente.getViagemClientes();
//            if (enderecoNew != null) {
//                enderecoNew = em.getReference(enderecoNew.getClass(), enderecoNew.getId());
//                cliente.setEndereco(enderecoNew);
//            }
//            List<ViagemCliente> attachedViagemClientesNew = new ArrayList<ViagemCliente>();
//            for (ViagemCliente viagemClientesNewViagemClienteToAttach : viagemClientesNew) {
//                viagemClientesNewViagemClienteToAttach = em.getReference(viagemClientesNewViagemClienteToAttach.getClass(), viagemClientesNewViagemClienteToAttach.getId());
//                attachedViagemClientesNew.add(viagemClientesNewViagemClienteToAttach);
//            }
//            viagemClientesNew = attachedViagemClientesNew;
//            cliente.setViagemClientes(viagemClientesNew);
            cliente = em.merge(cliente);
//            if (enderecoOld != null && !enderecoOld.equals(enderecoNew)) {
//                enderecoOld.getClientes().remove(cliente);
//                enderecoOld = em.merge(enderecoOld);
//            }
//            if (enderecoNew != null && !enderecoNew.equals(enderecoOld)) {
//                enderecoNew.getClientes().add(cliente);
//                enderecoNew = em.merge(enderecoNew);
//            }
//            for (ViagemCliente viagemClientesOldViagemCliente : viagemClientesOld) {
//                if (!viagemClientesNew.contains(viagemClientesOldViagemCliente)) {
//                    viagemClientesOldViagemCliente.setCliente(null);
//                    viagemClientesOldViagemCliente = em.merge(viagemClientesOldViagemCliente);
//                }
//            }
//            for (ViagemCliente viagemClientesNewViagemCliente : viagemClientesNew) {
//                if (!viagemClientesOld.contains(viagemClientesNewViagemCliente)) {
//                    Cliente oldClienteOfViagemClientesNewViagemCliente = viagemClientesNewViagemCliente.getCliente();
//                    viagemClientesNewViagemCliente.setCliente(cliente);
//                    viagemClientesNewViagemCliente = em.merge(viagemClientesNewViagemCliente);
//                    if (oldClienteOfViagemClientesNewViagemCliente != null && !oldClienteOfViagemClientesNewViagemCliente.equals(cliente)) {
//                        oldClienteOfViagemClientesNewViagemCliente.getViagemClientes().remove(viagemClientesNewViagemCliente);
//                        oldClienteOfViagemClientesNewViagemCliente = em.merge(oldClienteOfViagemClientesNewViagemCliente);
//                    }
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getId();
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

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
//            Endereco endereco = cliente.getEndereco();
//            if (endereco != null) {
//                endereco.getClientes().remove(cliente);
//                endereco = em.merge(endereco);
//            }
//            List<ViagemCliente> viagemClientes = cliente.getViagemClientes();
//            for (ViagemCliente viagemClientesViagemCliente : viagemClientes) {
//                viagemClientesViagemCliente.setCliente(null);
//                viagemClientesViagemCliente = em.merge(viagemClientesViagemCliente);
//            }
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
            Query q = em.createQuery("select object(o) from Cliente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(Long id) {
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
            Query q = em.createQuery("select count(o) from Cliente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
