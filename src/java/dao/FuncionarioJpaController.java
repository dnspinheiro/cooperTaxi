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
import modelo.Viagem;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Funcionario;

/**
 *
 * @author denis
 */
public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionario funcionario) {
        funcionario.setId(null);
        if (funcionario.getViagens() == null) {
            funcionario.setViagens(new ArrayList<Viagem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Endereco endereco = funcionario.getEndereco();
            if (endereco != null) {
                endereco = em.getReference(endereco.getClass(), endereco.getId());
                funcionario.setEndereco(endereco);
            }
//            List<Viagem> attachedViagens = new ArrayList<Viagem>();
//            for (Viagem viagensViagemToAttach : funcionario.getViagens()) {
//                viagensViagemToAttach = em.getReference(viagensViagemToAttach.getClass(), viagensViagemToAttach.getId());
//                attachedViagens.add(viagensViagemToAttach);
//            }
//            funcionario.setViagens(attachedViagens);
             em.persist(funcionario);
//            if (endereco != null) {
//                endereco.getFuncs().add(funcionario);
//                endereco = em.merge(endereco);
//            }
//            for (Viagem viagensViagem : funcionario.getViagens()) {
//                Funcionario oldFuncOfViagensViagem = viagensViagem.getFunc();
//                viagensViagem.setFunc(funcionario);
//                viagensViagem = em.merge(viagensViagem);
//                if (oldFuncOfViagensViagem != null) {
//                    oldFuncOfViagensViagem.getViagens().remove(viagensViagem);
//                    oldFuncOfViagensViagem = em.merge(oldFuncOfViagensViagem);
//                }
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            Funcionario persistentFuncionario = em.find(Funcionario.class, funcionario.getId());
//            Endereco enderecoOld = persistentFuncionario.getEndereco();
//            Endereco enderecoNew = funcionario.getEndereco();
//            List<Viagem> viagensOld = persistentFuncionario.getViagens();
//            List<Viagem> viagensNew = funcionario.getViagens();
//            if (enderecoNew != null) {
//                enderecoNew = em.getReference(enderecoNew.getClass(), enderecoNew.getId());
//                funcionario.setEndereco(enderecoNew);
//            }
//            List<Viagem> attachedViagensNew = new ArrayList<Viagem>();
//            for (Viagem viagensNewViagemToAttach : viagensNew) {
//                viagensNewViagemToAttach = em.getReference(viagensNewViagemToAttach.getClass(), viagensNewViagemToAttach.getId());
//                attachedViagensNew.add(viagensNewViagemToAttach);
//            }
//            viagensNew = attachedViagensNew;
//            funcionario.setViagens(viagensNew);
            funcionario = em.merge(funcionario);
//            if (enderecoOld != null && !enderecoOld.equals(enderecoNew)) {
//                enderecoOld.getFuncs().remove(funcionario);
//                enderecoOld = em.merge(enderecoOld);
//            }
//            if (enderecoNew != null && !enderecoNew.equals(enderecoOld)) {
//                enderecoNew.getFuncs().add(funcionario);
//                enderecoNew = em.merge(enderecoNew);
//            }
//            for (Viagem viagensOldViagem : viagensOld) {
//                if (!viagensNew.contains(viagensOldViagem)) {
//                    viagensOldViagem.setFunc(null);
//                    viagensOldViagem = em.merge(viagensOldViagem);
//                }
//            }
//            for (Viagem viagensNewViagem : viagensNew) {
//                if (!viagensOld.contains(viagensNewViagem)) {
//                    Funcionario oldFuncOfViagensNewViagem = viagensNewViagem.getFunc();
//                    viagensNewViagem.setFunc(funcionario);
//                    viagensNewViagem = em.merge(viagensNewViagem);
//                    if (oldFuncOfViagensNewViagem != null && !oldFuncOfViagensNewViagem.equals(funcionario)) {
//                        oldFuncOfViagensNewViagem.getViagens().remove(viagensNewViagem);
//                        oldFuncOfViagensNewViagem = em.merge(oldFuncOfViagensNewViagem);
//                    }
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = funcionario.getId();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
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
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
//            Endereco endereco = funcionario.getEndereco();
//            if (endereco != null) {
//                endereco.getFuncs().remove(funcionario);
//                endereco = em.merge(endereco);
//            }
//            List<Viagem> viagens = funcionario.getViagens();
//            for (Viagem viagensViagem : viagens) {
//                viagensViagem.setFunc(null);
//                viagensViagem = em.merge(viagensViagem);
//            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Funcionario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Funcionario findFuncionario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Funcionario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
