/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import modelo.Viagem;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.RelatorioVeiculo;
import modelo.Veiculo;

/**
 *
 * @author denis
 */
public class VeiculoJpaController implements Serializable {

    public VeiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Veiculo veiculo) {
        veiculo.setId(null);
        if (veiculo.getViagens() == null) {
            veiculo.setViagens(new ArrayList<Viagem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            List<Viagem> attachedViagens = new ArrayList<Viagem>();
//            for (Viagem viagensViagemToAttach : veiculo.getViagens()) {
//                viagensViagemToAttach = em.getReference(viagensViagemToAttach.getClass(), viagensViagemToAttach.getId());
//                attachedViagens.add(viagensViagemToAttach);
//            }
//            veiculo.setViagens(attachedViagens);
            em.persist(veiculo);
//            for (Viagem viagensViagem : veiculo.getViagens()) {
//                Veiculo oldVeiculoOfViagensViagem = viagensViagem.getVeiculo();
//                viagensViagem.setVeiculo(veiculo);
//                viagensViagem = em.merge(viagensViagem);
//                if (oldVeiculoOfViagensViagem != null) {
//                    oldVeiculoOfViagensViagem.getViagens().remove(viagensViagem);
//                    oldVeiculoOfViagensViagem = em.merge(oldVeiculoOfViagensViagem);
//                }
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<RelatorioVeiculo> pesquisarInfoVeiculo(){
        EntityManager em = getEntityManager();
        try {
            
            Query q = em.createQuery("select NEW modelo.RelatorioVeiculo(vei.placa, sum(l.distancia)) from Viagem v, Linha l, Veiculo vei where v.veiculo.id = vei.id and v.linha.id = l.id group by vei.placa order by sum(l.distancia) desc");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void edit(Veiculo veiculo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            Veiculo persistentVeiculo = em.find(Veiculo.class, veiculo.getId());
//            List<Viagem> viagensOld = persistentVeiculo.getViagens();
//            List<Viagem> viagensNew = veiculo.getViagens();
//            List<Viagem> attachedViagensNew = new ArrayList<Viagem>();
//            for (Viagem viagensNewViagemToAttach : viagensNew) {
//                viagensNewViagemToAttach = em.getReference(viagensNewViagemToAttach.getClass(), viagensNewViagemToAttach.getId());
//                attachedViagensNew.add(viagensNewViagemToAttach);
//            }
//            viagensNew = attachedViagensNew;
//            veiculo.setViagens(viagensNew);
            veiculo = em.merge(veiculo);
//            for (Viagem viagensOldViagem : viagensOld) {
//                if (!viagensNew.contains(viagensOldViagem)) {
//                    viagensOldViagem.setVeiculo(null);
//                    viagensOldViagem = em.merge(viagensOldViagem);
//                }
//            }
//            for (Viagem viagensNewViagem : viagensNew) {
//                if (!viagensOld.contains(viagensNewViagem)) {
//                    Veiculo oldVeiculoOfViagensNewViagem = viagensNewViagem.getVeiculo();
//                    viagensNewViagem.setVeiculo(veiculo);
//                    viagensNewViagem = em.merge(viagensNewViagem);
//                    if (oldVeiculoOfViagensNewViagem != null && !oldVeiculoOfViagensNewViagem.equals(veiculo)) {
//                        oldVeiculoOfViagensNewViagem.getViagens().remove(viagensNewViagem);
//                        oldVeiculoOfViagensNewViagem = em.merge(oldVeiculoOfViagensNewViagem);
//                    }
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = veiculo.getId();
                if (findVeiculo(id) == null) {
                    throw new NonexistentEntityException("The veiculo with id " + id + " no longer exists.");
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
            Veiculo veiculo;
            try {
                veiculo = em.getReference(Veiculo.class, id);
                veiculo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veiculo with id " + id + " no longer exists.", enfe);
            }
//            List<Viagem> viagens = veiculo.getViagens();
//            for (Viagem viagensViagem : viagens) {
//                viagensViagem.setVeiculo(null);
//                viagensViagem = em.merge(viagensViagem);
//            }
            em.remove(veiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Veiculo> findVeiculoEntities() {
        return findVeiculoEntities(true, -1, -1);
    }

    public List<Veiculo> findVeiculoEntities(int maxResults, int firstResult) {
        return findVeiculoEntities(false, maxResults, firstResult);
    }

    private List<Veiculo> findVeiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Veiculo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Veiculo findVeiculo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Veiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeiculoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Veiculo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
