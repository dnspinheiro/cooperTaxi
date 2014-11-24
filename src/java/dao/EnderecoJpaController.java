/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import modelo.Cidade;
import modelo.Linha;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Funcionario;
import modelo.Cliente;
import modelo.Endereco;

/**
 *
 * @author denis
 */
public class EnderecoJpaController implements Serializable {

    public EnderecoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Endereco endereco) {
        endereco.setId(null);
//        if (endereco.getLinhas() == null) {
//            endereco.setLinhas(new ArrayList<Linha>());
//        }
//        if (endereco.getFuncs() == null) {
//            endereco.setFuncs(new ArrayList<Funcionario>());
//        }
//        if (endereco.getClientes() == null) {
//            endereco.setClientes(new ArrayList<Cliente>());
//        }
//        if (endereco.getOrigens() == null) {
//            endereco.setOrigens(new ArrayList<Linha>());
//        }
//        if (endereco.getDestinos() == null) {
//            endereco.setDestinos(new ArrayList<Linha>());
//        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cidade cidade = endereco.getCidade();
            if (cidade != null) {
                cidade = em.getReference(cidade.getClass(), cidade.getId());
                endereco.setCidade(cidade);
            }
//            List<Linha> attachedLinhas = new ArrayList<Linha>();
//            for (Linha linhasLinhaToAttach : endereco.getLinhas()) {
//                linhasLinhaToAttach = em.getReference(linhasLinhaToAttach.getClass(), linhasLinhaToAttach.getId());
//                attachedLinhas.add(linhasLinhaToAttach);
//            }
//            endereco.setLinhas(attachedLinhas);
//            List<Funcionario> attachedFuncs = new ArrayList<Funcionario>();
//            for (Funcionario funcsFuncionarioToAttach : endereco.getFuncs()) {
//                funcsFuncionarioToAttach = em.getReference(funcsFuncionarioToAttach.getClass(), funcsFuncionarioToAttach.getId());
//                attachedFuncs.add(funcsFuncionarioToAttach);
//            }
//            endereco.setFuncs(attachedFuncs);
//            List<Cliente> attachedClientes = new ArrayList<Cliente>();
//            for (Cliente clientesClienteToAttach : endereco.getClientes()) {
//                clientesClienteToAttach = em.getReference(clientesClienteToAttach.getClass(), clientesClienteToAttach.getId());
//                attachedClientes.add(clientesClienteToAttach);
//            }
//            endereco.setClientes(attachedClientes);
//            List<Linha> attachedOrigens = new ArrayList<Linha>();
//            for (Linha origensLinhaToAttach : endereco.getOrigens()) {
//                origensLinhaToAttach = em.getReference(origensLinhaToAttach.getClass(), origensLinhaToAttach.getId());
//                attachedOrigens.add(origensLinhaToAttach);
//            }
//            endereco.setOrigens(attachedOrigens);
//            List<Linha> attachedDestinos = new ArrayList<Linha>();
//            for (Linha destinosLinhaToAttach : endereco.getDestinos()) {
//                destinosLinhaToAttach = em.getReference(destinosLinhaToAttach.getClass(), destinosLinhaToAttach.getId());
//                attachedDestinos.add(destinosLinhaToAttach);
//            }
//            endereco.setDestinos(attachedDestinos);
            em.persist(endereco);
//            if (cidade != null) {
//                cidade.getEnderecos().add(endereco);
//                cidade = em.merge(cidade);
//            }
//            for (Linha linhasLinha : endereco.getLinhas()) {
//                Endereco oldOrigemOfLinhasLinha = linhasLinha.getOrigem();
//                linhasLinha.setOrigem(endereco);
//                linhasLinha = em.merge(linhasLinha);
//                if (oldOrigemOfLinhasLinha != null) {
//                    oldOrigemOfLinhasLinha.getLinhas().remove(linhasLinha);
//                    oldOrigemOfLinhasLinha = em.merge(oldOrigemOfLinhasLinha);
//                }
//            }
//            for (Funcionario funcsFuncionario : endereco.getFuncs()) {
//                Endereco oldEnderecoOfFuncsFuncionario = funcsFuncionario.getEndereco();
//                funcsFuncionario.setEndereco(endereco);
//                funcsFuncionario = em.merge(funcsFuncionario);
//                if (oldEnderecoOfFuncsFuncionario != null) {
//                    oldEnderecoOfFuncsFuncionario.getFuncs().remove(funcsFuncionario);
//                    oldEnderecoOfFuncsFuncionario = em.merge(oldEnderecoOfFuncsFuncionario);
//                }
//            }
//            for (Cliente clientesCliente : endereco.getClientes()) {
//                Endereco oldEnderecoOfClientesCliente = clientesCliente.getEndereco();
//                clientesCliente.setEndereco(endereco);
//                clientesCliente = em.merge(clientesCliente);
//                if (oldEnderecoOfClientesCliente != null) {
//                    oldEnderecoOfClientesCliente.getClientes().remove(clientesCliente);
//                    oldEnderecoOfClientesCliente = em.merge(oldEnderecoOfClientesCliente);
//                }
//            }
//            for (Linha origensLinha : endereco.getOrigens()) {
//                Endereco oldOrigemOfOrigensLinha = origensLinha.getOrigem();
//                origensLinha.setOrigem(endereco);
//                origensLinha = em.merge(origensLinha);
//                if (oldOrigemOfOrigensLinha != null) {
//                    oldOrigemOfOrigensLinha.getOrigens().remove(origensLinha);
//                    oldOrigemOfOrigensLinha = em.merge(oldOrigemOfOrigensLinha);
//                }
//            }
//            for (Linha destinosLinha : endereco.getDestinos()) {
//                Endereco oldOrigemOfDestinosLinha = destinosLinha.getOrigem();
//                destinosLinha.setOrigem(endereco);
//                destinosLinha = em.merge(destinosLinha);
//                if (oldOrigemOfDestinosLinha != null) {
//                    oldOrigemOfDestinosLinha.getDestinos().remove(destinosLinha);
//                    oldOrigemOfDestinosLinha = em.merge(oldOrigemOfDestinosLinha);
//                }
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Endereco endereco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            Endereco persistentEndereco = em.find(Endereco.class, endereco.getId());
//            Cidade cidadeOld = persistentEndereco.getCidade();
//            Cidade cidadeNew = endereco.getCidade();
//            List<Linha> linhasOld = persistentEndereco.getLinhas();
//            List<Linha> linhasNew = endereco.getLinhas();
//            List<Funcionario> funcsOld = persistentEndereco.getFuncs();
//            List<Funcionario> funcsNew = endereco.getFuncs();
//            List<Cliente> clientesOld = persistentEndereco.getClientes();
//            List<Cliente> clientesNew = endereco.getClientes();
//            List<Linha> origensOld = persistentEndereco.getOrigens();
//            List<Linha> origensNew = endereco.getOrigens();
//            List<Linha> destinosOld = persistentEndereco.getDestinos();
//            List<Linha> destinosNew = endereco.getDestinos();
//            if (cidadeNew != null) {
//                cidadeNew = em.getReference(cidadeNew.getClass(), cidadeNew.getId());
//                endereco.setCidade(cidadeNew);
//            }
//            List<Linha> attachedLinhasNew = new ArrayList<Linha>();
//            for (Linha linhasNewLinhaToAttach : linhasNew) {
//                linhasNewLinhaToAttach = em.getReference(linhasNewLinhaToAttach.getClass(), linhasNewLinhaToAttach.getId());
//                attachedLinhasNew.add(linhasNewLinhaToAttach);
//            }
//            linhasNew = attachedLinhasNew;
//            endereco.setLinhas(linhasNew);
//            List<Funcionario> attachedFuncsNew = new ArrayList<Funcionario>();
//            for (Funcionario funcsNewFuncionarioToAttach : funcsNew) {
//                funcsNewFuncionarioToAttach = em.getReference(funcsNewFuncionarioToAttach.getClass(), funcsNewFuncionarioToAttach.getId());
//                attachedFuncsNew.add(funcsNewFuncionarioToAttach);
//            }
//            funcsNew = attachedFuncsNew;
//            endereco.setFuncs(funcsNew);
//            List<Cliente> attachedClientesNew = new ArrayList<Cliente>();
//            for (Cliente clientesNewClienteToAttach : clientesNew) {
//                clientesNewClienteToAttach = em.getReference(clientesNewClienteToAttach.getClass(), clientesNewClienteToAttach.getId());
//                attachedClientesNew.add(clientesNewClienteToAttach);
//            }
//            clientesNew = attachedClientesNew;
//            endereco.setClientes(clientesNew);
//            List<Linha> attachedOrigensNew = new ArrayList<Linha>();
//            for (Linha origensNewLinhaToAttach : origensNew) {
//                origensNewLinhaToAttach = em.getReference(origensNewLinhaToAttach.getClass(), origensNewLinhaToAttach.getId());
//                attachedOrigensNew.add(origensNewLinhaToAttach);
//            }
//            origensNew = attachedOrigensNew;
//            endereco.setOrigens(origensNew);
//            List<Linha> attachedDestinosNew = new ArrayList<Linha>();
//            for (Linha destinosNewLinhaToAttach : destinosNew) {
//                destinosNewLinhaToAttach = em.getReference(destinosNewLinhaToAttach.getClass(), destinosNewLinhaToAttach.getId());
//                attachedDestinosNew.add(destinosNewLinhaToAttach);
//            }
//            destinosNew = attachedDestinosNew;
//            endereco.setDestinos(destinosNew);
            endereco = em.merge(endereco);
//            if (cidadeOld != null && !cidadeOld.equals(cidadeNew)) {
//                cidadeOld.getEnderecos().remove(endereco);
//                cidadeOld = em.merge(cidadeOld);
//            }
//            if (cidadeNew != null && !cidadeNew.equals(cidadeOld)) {
//                cidadeNew.getEnderecos().add(endereco);
//                cidadeNew = em.merge(cidadeNew);
//            }
//            for (Linha linhasOldLinha : linhasOld) {
//                if (!linhasNew.contains(linhasOldLinha)) {
//                    linhasOldLinha.setOrigem(null);
//                    linhasOldLinha = em.merge(linhasOldLinha);
//                }
//            }
//            for (Linha linhasNewLinha : linhasNew) {
//                if (!linhasOld.contains(linhasNewLinha)) {
//                    Endereco oldOrigemOfLinhasNewLinha = linhasNewLinha.getOrigem();
//                    linhasNewLinha.setOrigem(endereco);
//                    linhasNewLinha = em.merge(linhasNewLinha);
//                    if (oldOrigemOfLinhasNewLinha != null && !oldOrigemOfLinhasNewLinha.equals(endereco)) {
//                        oldOrigemOfLinhasNewLinha.getLinhas().remove(linhasNewLinha);
//                        oldOrigemOfLinhasNewLinha = em.merge(oldOrigemOfLinhasNewLinha);
//                    }
//                }
//            }
//            for (Funcionario funcsOldFuncionario : funcsOld) {
//                if (!funcsNew.contains(funcsOldFuncionario)) {
//                    funcsOldFuncionario.setEndereco(null);
//                    funcsOldFuncionario = em.merge(funcsOldFuncionario);
//                }
//            }
//            for (Funcionario funcsNewFuncionario : funcsNew) {
//                if (!funcsOld.contains(funcsNewFuncionario)) {
//                    Endereco oldEnderecoOfFuncsNewFuncionario = funcsNewFuncionario.getEndereco();
//                    funcsNewFuncionario.setEndereco(endereco);
//                    funcsNewFuncionario = em.merge(funcsNewFuncionario);
//                    if (oldEnderecoOfFuncsNewFuncionario != null && !oldEnderecoOfFuncsNewFuncionario.equals(endereco)) {
//                        oldEnderecoOfFuncsNewFuncionario.getFuncs().remove(funcsNewFuncionario);
//                        oldEnderecoOfFuncsNewFuncionario = em.merge(oldEnderecoOfFuncsNewFuncionario);
//                    }
//                }
//            }
//            for (Cliente clientesOldCliente : clientesOld) {
//                if (!clientesNew.contains(clientesOldCliente)) {
//                    clientesOldCliente.setEndereco(null);
//                    clientesOldCliente = em.merge(clientesOldCliente);
//                }
//            }
//            for (Cliente clientesNewCliente : clientesNew) {
//                if (!clientesOld.contains(clientesNewCliente)) {
//                    Endereco oldEnderecoOfClientesNewCliente = clientesNewCliente.getEndereco();
//                    clientesNewCliente.setEndereco(endereco);
//                    clientesNewCliente = em.merge(clientesNewCliente);
//                    if (oldEnderecoOfClientesNewCliente != null && !oldEnderecoOfClientesNewCliente.equals(endereco)) {
//                        oldEnderecoOfClientesNewCliente.getClientes().remove(clientesNewCliente);
//                        oldEnderecoOfClientesNewCliente = em.merge(oldEnderecoOfClientesNewCliente);
//                    }
//                }
//            }
//            for (Linha origensOldLinha : origensOld) {
//                if (!origensNew.contains(origensOldLinha)) {
//                    origensOldLinha.setOrigem(null);
//                    origensOldLinha = em.merge(origensOldLinha);
//                }
//            }
//            for (Linha origensNewLinha : origensNew) {
//                if (!origensOld.contains(origensNewLinha)) {
//                    Endereco oldOrigemOfOrigensNewLinha = origensNewLinha.getOrigem();
//                    origensNewLinha.setOrigem(endereco);
//                    origensNewLinha = em.merge(origensNewLinha);
//                    if (oldOrigemOfOrigensNewLinha != null && !oldOrigemOfOrigensNewLinha.equals(endereco)) {
//                        oldOrigemOfOrigensNewLinha.getOrigens().remove(origensNewLinha);
//                        oldOrigemOfOrigensNewLinha = em.merge(oldOrigemOfOrigensNewLinha);
//                    }
//                }
//            }
//            for (Linha destinosOldLinha : destinosOld) {
//                if (!destinosNew.contains(destinosOldLinha)) {
//                    destinosOldLinha.setOrigem(null);
//                    destinosOldLinha = em.merge(destinosOldLinha);
//                }
//            }
//            for (Linha destinosNewLinha : destinosNew) {
//                if (!destinosOld.contains(destinosNewLinha)) {
//                    Endereco oldOrigemOfDestinosNewLinha = destinosNewLinha.getOrigem();
//                    destinosNewLinha.setOrigem(endereco);
//                    destinosNewLinha = em.merge(destinosNewLinha);
//                    if (oldOrigemOfDestinosNewLinha != null && !oldOrigemOfDestinosNewLinha.equals(endereco)) {
//                        oldOrigemOfDestinosNewLinha.getDestinos().remove(destinosNewLinha);
//                        oldOrigemOfDestinosNewLinha = em.merge(oldOrigemOfDestinosNewLinha);
//                    }
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = endereco.getId();
                if (findEndereco(id) == null) {
                    throw new NonexistentEntityException("The endereco with id " + id + " no longer exists.");
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
            Endereco endereco;
            try {
                endereco = em.getReference(Endereco.class, id);
                endereco.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The endereco with id " + id + " no longer exists.", enfe);
            }
//            Cidade cidade = endereco.getCidade();
//            if (cidade != null) {
//                cidade.getEnderecos().remove(endereco);
//                cidade = em.merge(cidade);
//            }
//            List<Linha> linhas = endereco.getLinhas();
//            for (Linha linhasLinha : linhas) {
//                linhasLinha.setOrigem(null);
//                linhasLinha = em.merge(linhasLinha);
//            }
//            List<Funcionario> funcs = endereco.getFuncs();
//            for (Funcionario funcsFuncionario : funcs) {
//                funcsFuncionario.setEndereco(null);
//                funcsFuncionario = em.merge(funcsFuncionario);
//            }
//            List<Cliente> clientes = endereco.getClientes();
//            for (Cliente clientesCliente : clientes) {
//                clientesCliente.setEndereco(null);
//                clientesCliente = em.merge(clientesCliente);
//            }
//            List<Linha> origens = endereco.getOrigens();
//            for (Linha origensLinha : origens) {
//                origensLinha.setOrigem(null);
//                origensLinha = em.merge(origensLinha);
//            }
//            List<Linha> destinos = endereco.getDestinos();
//            for (Linha destinosLinha : destinos) {
//                destinosLinha.setOrigem(null);
//                destinosLinha = em.merge(destinosLinha);
//            }
            em.remove(endereco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Endereco> findEnderecoEntities() {
        return findEnderecoEntities(true, -1, -1);
    }

    public List<Endereco> findEnderecoEntities(int maxResults, int firstResult) {
        return findEnderecoEntities(false, maxResults, firstResult);
    }

    private List<Endereco> findEnderecoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Endereco as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Endereco findEndereco(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Endereco.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnderecoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Endereco as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
