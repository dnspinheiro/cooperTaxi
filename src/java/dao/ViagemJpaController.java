/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import modelo.Funcionario;
import modelo.Financa;
import modelo.Linha;
import modelo.Veiculo;
import modelo.ViagemCliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Viagem;

/**
 *
 * @author denis
 */
public class ViagemJpaController implements Serializable {

    public ViagemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Viagem viagem) {
        viagem.setId(null);
        if (viagem.getViagemClientes() == null) {
            viagem.setViagemClientes(new ArrayList<ViagemCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario func = viagem.getFunc();
            if (func != null) {
                func = em.getReference(func.getClass(), func.getId());
                viagem.setFunc(func);
            }
            Financa financa = viagem.getFinanca();
            if (financa != null) {
                financa = em.getReference(financa.getClass(), financa.getId());
                viagem.setFinanca(financa);
            }
            Linha linha = viagem.getLinha();
            if (linha != null) {
                linha = em.getReference(linha.getClass(), linha.getId());
                viagem.setLinha(linha);
            }
            Veiculo veiculo = viagem.getVeiculo();
            if (veiculo != null) {
                veiculo = em.getReference(veiculo.getClass(), veiculo.getId());
                viagem.setVeiculo(veiculo);
            }
//            List<ViagemCliente> attachedViagemClientes = new ArrayList<ViagemCliente>();
//            for (ViagemCliente viagemClientesViagemClienteToAttach : viagem.getViagemClientes()) {
//                viagemClientesViagemClienteToAttach = em.getReference(viagemClientesViagemClienteToAttach.getClass(), viagemClientesViagemClienteToAttach.getId());
//                attachedViagemClientes.add(viagemClientesViagemClienteToAttach);
//            }
//            viagem.setViagemClientes(attachedViagemClientes);
            em.persist(viagem);
//            if (func != null) {
//                func.getViagens().add(viagem);
//                func = em.merge(func);
//            }
//            if (linha != null) {
//                linha.getViagens().add(viagem);
//                linha = em.merge(linha);
//            }
//            if (veiculo != null) {
//                veiculo.getViagens().add(viagem);
//                veiculo = em.merge(veiculo);
//            }
//            for (ViagemCliente viagemClientesViagemCliente : viagem.getViagemClientes()) {
//                Viagem oldViagemOfViagemClientesViagemCliente = viagemClientesViagemCliente.getViagem();
//                viagemClientesViagemCliente.setViagem(viagem);
//                viagemClientesViagemCliente = em.merge(viagemClientesViagemCliente);
//                if (oldViagemOfViagemClientesViagemCliente != null) {
//                    oldViagemOfViagemClientesViagemCliente.getViagemClientes().remove(viagemClientesViagemCliente);
//                    oldViagemOfViagemClientesViagemCliente = em.merge(oldViagemOfViagemClientesViagemCliente);
//                }
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Viagem viagem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            Viagem persistentViagem = em.find(Viagem.class, viagem.getId());
//            Funcionario funcOld = persistentViagem.getFunc();
//            Funcionario funcNew = viagem.getFunc();
//            Linha linhaOld = persistentViagem.getLinha();
//            Linha linhaNew = viagem.getLinha();
//            Veiculo veiculoOld = persistentViagem.getVeiculo();
//            Veiculo veiculoNew = viagem.getVeiculo();
//            List<ViagemCliente> viagemClientesOld = persistentViagem.getViagemClientes();
//            List<ViagemCliente> viagemClientesNew = viagem.getViagemClientes();
//            if (funcNew != null) {
//                funcNew = em.getReference(funcNew.getClass(), funcNew.getId());
//                viagem.setFunc(funcNew);
//            }
//            if (linhaNew != null) {
//                linhaNew = em.getReference(linhaNew.getClass(), linhaNew.getId());
//                viagem.setLinha(linhaNew);
//            }
//            if (veiculoNew != null) {
//                veiculoNew = em.getReference(veiculoNew.getClass(), veiculoNew.getId());
//                viagem.setVeiculo(veiculoNew);
//            }
//            List<ViagemCliente> attachedViagemClientesNew = new ArrayList<ViagemCliente>();
//            for (ViagemCliente viagemClientesNewViagemClienteToAttach : viagemClientesNew) {
//                viagemClientesNewViagemClienteToAttach = em.getReference(viagemClientesNewViagemClienteToAttach.getClass(), viagemClientesNewViagemClienteToAttach.getId());
//                attachedViagemClientesNew.add(viagemClientesNewViagemClienteToAttach);
//            }
//            viagemClientesNew = attachedViagemClientesNew;
//            viagem.setViagemClientes(viagemClientesNew);
            viagem = em.merge(viagem);
//            if (funcOld != null && !funcOld.equals(funcNew)) {
//                funcOld.getViagens().remove(viagem);
//                funcOld = em.merge(funcOld);
//            }
//            if (funcNew != null && !funcNew.equals(funcOld)) {
//                funcNew.getViagens().add(viagem);
//                funcNew = em.merge(funcNew);
//            }
//            if (linhaOld != null && !linhaOld.equals(linhaNew)) {
//                linhaOld.getViagens().remove(viagem);
//                linhaOld = em.merge(linhaOld);
//            }
//            if (linhaNew != null && !linhaNew.equals(linhaOld)) {
//                linhaNew.getViagens().add(viagem);
//                linhaNew = em.merge(linhaNew);
//            }
//            if (veiculoOld != null && !veiculoOld.equals(veiculoNew)) {
//                veiculoOld.getViagens().remove(viagem);
//                veiculoOld = em.merge(veiculoOld);
//            }
//            if (veiculoNew != null && !veiculoNew.equals(veiculoOld)) {
//                veiculoNew.getViagens().add(viagem);
//                veiculoNew = em.merge(veiculoNew);
//            }
//            for (ViagemCliente viagemClientesOldViagemCliente : viagemClientesOld) {
//                if (!viagemClientesNew.contains(viagemClientesOldViagemCliente)) {
//                    viagemClientesOldViagemCliente.setViagem(null);
//                    viagemClientesOldViagemCliente = em.merge(viagemClientesOldViagemCliente);
//                }
//            }
//            for (ViagemCliente viagemClientesNewViagemCliente : viagemClientesNew) {
//                if (!viagemClientesOld.contains(viagemClientesNewViagemCliente)) {
//                    Viagem oldViagemOfViagemClientesNewViagemCliente = viagemClientesNewViagemCliente.getViagem();
//                    viagemClientesNewViagemCliente.setViagem(viagem);
//                    viagemClientesNewViagemCliente = em.merge(viagemClientesNewViagemCliente);
//                    if (oldViagemOfViagemClientesNewViagemCliente != null && !oldViagemOfViagemClientesNewViagemCliente.equals(viagem)) {
//                        oldViagemOfViagemClientesNewViagemCliente.getViagemClientes().remove(viagemClientesNewViagemCliente);
//                        oldViagemOfViagemClientesNewViagemCliente = em.merge(oldViagemOfViagemClientesNewViagemCliente);
//                    }
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = viagem.getId();
                if (findViagem(id) == null) {
                    throw new NonexistentEntityException("The viagem with id " + id + " no longer exists.");
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
            Viagem viagem;
            try {
                viagem = em.getReference(Viagem.class, id);
                viagem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The viagem with id " + id + " no longer exists.", enfe);
            }
//            Funcionario func = viagem.getFunc();
//            if (func != null) {
//                func.getViagens().remove(viagem);
//                func = em.merge(func);
//            }
//            Linha linha = viagem.getLinha();
//            if (linha != null) {
//                linha.getViagens().remove(viagem);
//                linha = em.merge(linha);
//            }
//            Veiculo veiculo = viagem.getVeiculo();
//            if (veiculo != null) {
//                veiculo.getViagens().remove(viagem);
//                veiculo = em.merge(veiculo);
//            }
//            List<ViagemCliente> viagemClientes = viagem.getViagemClientes();
//            for (ViagemCliente viagemClientesViagemCliente : viagemClientes) {
//                viagemClientesViagemCliente.setViagem(null);
//                viagemClientesViagemCliente = em.merge(viagemClientesViagemCliente);
//            }
            em.remove(viagem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Viagem> findViagemEntities() {
        return findViagemEntities(true, -1, -1);
    }

    public List<Viagem> findViagemEntities(int maxResults, int firstResult) {
        return findViagemEntities(false, maxResults, firstResult);
    }

    private List<Viagem> findViagemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Viagem as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Viagem findViagem(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Viagem.class, id);
        } finally {
            em.close();
        }
    }

    public int getViagemCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Viagem as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
