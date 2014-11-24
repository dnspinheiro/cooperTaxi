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
import modelo.Financa;
import modelo.RelatorioFinanca;
import modelo.Veiculo;

/**
 *
 * @author denis
 */
public class FinancaJpaController implements Serializable {

    public FinancaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Financa financa) {
        financa.setId(null);
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Veiculo veiculo = financa.getVeiculo();
            if (veiculo != null) {
                veiculo = em.getReference(veiculo.getClass(), veiculo.getId());
                financa.setVeiculo(veiculo);
            }
            
            financa.setTipo("Despesa");
            em.persist(financa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Financa createToViagem(Financa financa) {
        financa.setId(null);
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Veiculo veiculo = financa.getVeiculo();
            if (veiculo != null) {
                veiculo = em.getReference(veiculo.getClass(), veiculo.getId());
                financa.setVeiculo(veiculo);
            }
            
            financa.setTipo("Receita");
            em.persist(financa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return financa;
    }
    
    public List<RelatorioFinanca> pesquisarInfoFinanca() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select NEW modelo.RelatorioFinanca(tipo, sum(valor)) from Financa group by tipo");
            
            //select c.nome,sum(l.DISTANCIA) from viagem v,viagemCliente vc, cliente c, linha l where v.linha_id=l.id and vc.viagem_id=v.id and vc.cliente_id=c.id group by c.nome order by sum(l.DISTANCIA) desc;
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<RelatorioFinanca> pesquisarInfoUltimo() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select NEW modelo.RelatorioFinanca(tipo, sum(valor)) from Financa group by tipo");
            
            //select c.nome,sum(l.DISTANCIA) from viagem v,viagemCliente vc, cliente c, linha l where v.linha_id=l.id and vc.viagem_id=v.id and vc.cliente_id=c.id group by c.nome order by sum(l.DISTANCIA) desc;
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void edit(Financa financa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            financa = em.merge(financa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = financa.getId();
                if (findFinanca(id) == null) {
                    throw new NonexistentEntityException("The financa with id " + id + " no longer exists.");
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
            Financa financa;
            try {
                financa = em.getReference(Financa.class, id);
                financa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financa with id " + id + " no longer exists.", enfe);
            }
            em.remove(financa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Financa> findFinancaEntities() {
        return findFinancaEntities(true, -1, -1);
    }

    public List<Financa> findFinancaEntities(int maxResults, int firstResult) {
        return findFinancaEntities(false, maxResults, firstResult);
    }

    private List<Financa> findFinancaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Financa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Financa findFinanca(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Financa.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinancaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Financa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
