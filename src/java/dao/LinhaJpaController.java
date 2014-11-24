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
import modelo.Linha;

/**
 *
 * @author denis
 */
public class LinhaJpaController implements Serializable {

    public LinhaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Linha linha) {
        linha.setId(null);
        if (linha.getViagens() == null) {
            linha.setViagens(new ArrayList<Viagem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Endereco origem = linha.getOrigem();
            if (origem != null) {
                origem = em.getReference(origem.getClass(), origem.getId());
                linha.setOrigem(origem);
            }
            Endereco destino = linha.getDestino();
            if (destino != null) {
                destino = em.getReference(destino.getClass(), destino.getId());
                linha.setDestino(destino);
            }
//            List<Viagem> attachedViagens = new ArrayList<Viagem>();
//            for (Viagem viagensViagemToAttach : linha.getViagens()) {
//                viagensViagemToAttach = em.getReference(viagensViagemToAttach.getClass(), viagensViagemToAttach.getId());
//                attachedViagens.add(viagensViagemToAttach);
//            }
//            linha.setViagens(attachedViagens);
            em.persist(linha);
//            if (origem != null) {
//                origem.getLinhas().add(linha);
//                origem = em.merge(origem);
//            }
//            if (destino != null) {
//                destino.getLinhas().add(linha);
//                destino = em.merge(destino);
//            }
//            for (Viagem viagensViagem : linha.getViagens()) {
//                Linha oldLinhaOfViagensViagem = viagensViagem.getLinha();
//                viagensViagem.setLinha(linha);
//                viagensViagem = em.merge(viagensViagem);
//                if (oldLinhaOfViagensViagem != null) {
//                    oldLinhaOfViagensViagem.getViagens().remove(viagensViagem);
//                    oldLinhaOfViagensViagem = em.merge(oldLinhaOfViagensViagem);
//                }
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Linha linha) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            Linha persistentLinha = em.find(Linha.class, linha.getId());
//            Endereco origemOld = persistentLinha.getOrigem();
//            Endereco origemNew = linha.getOrigem();
//            Endereco destinoOld = persistentLinha.getDestino();
//            Endereco destinoNew = linha.getDestino();
//            List<Viagem> viagensOld = persistentLinha.getViagens();
//            List<Viagem> viagensNew = linha.getViagens();
//            if (origemNew != null) {
//                origemNew = em.getReference(origemNew.getClass(), origemNew.getId());
//                linha.setOrigem(origemNew);
//            }
//            if (destinoNew != null) {
//                destinoNew = em.getReference(destinoNew.getClass(), destinoNew.getId());
//                linha.setDestino(destinoNew);
//            }
//            List<Viagem> attachedViagensNew = new ArrayList<Viagem>();
//            for (Viagem viagensNewViagemToAttach : viagensNew) {
//                viagensNewViagemToAttach = em.getReference(viagensNewViagemToAttach.getClass(), viagensNewViagemToAttach.getId());
//                attachedViagensNew.add(viagensNewViagemToAttach);
//            }
//            viagensNew = attachedViagensNew;
//            linha.setViagens(viagensNew);
            linha = em.merge(linha);
//            if (origemOld != null && !origemOld.equals(origemNew)) {
//                origemOld.getLinhas().remove(linha);
//                origemOld = em.merge(origemOld);
//            }
//            if (origemNew != null && !origemNew.equals(origemOld)) {
//                origemNew.getLinhas().add(linha);
//                origemNew = em.merge(origemNew);
//            }
//            if (destinoOld != null && !destinoOld.equals(destinoNew)) {
//                destinoOld.getLinhas().remove(linha);
//                destinoOld = em.merge(destinoOld);
//            }
//            if (destinoNew != null && !destinoNew.equals(destinoOld)) {
//                destinoNew.getLinhas().add(linha);
//                destinoNew = em.merge(destinoNew);
//            }
//            for (Viagem viagensOldViagem : viagensOld) {
//                if (!viagensNew.contains(viagensOldViagem)) {
//                    viagensOldViagem.setLinha(null);
//                    viagensOldViagem = em.merge(viagensOldViagem);
//                }
//            }
//            for (Viagem viagensNewViagem : viagensNew) {
//                if (!viagensOld.contains(viagensNewViagem)) {
//                    Linha oldLinhaOfViagensNewViagem = viagensNewViagem.getLinha();
//                    viagensNewViagem.setLinha(linha);
//                    viagensNewViagem = em.merge(viagensNewViagem);
//                    if (oldLinhaOfViagensNewViagem != null && !oldLinhaOfViagensNewViagem.equals(linha)) {
//                        oldLinhaOfViagensNewViagem.getViagens().remove(viagensNewViagem);
//                        oldLinhaOfViagensNewViagem = em.merge(oldLinhaOfViagensNewViagem);
//                    }
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = linha.getId();
                if (findLinha(id) == null) {
                    throw new NonexistentEntityException("The linha with id " + id + " no longer exists.");
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
            Linha linha;
            try {
                linha = em.getReference(Linha.class, id);
                linha.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The linha with id " + id + " no longer exists.", enfe);
            }
//            Endereco origem = linha.getOrigem();
//            if (origem != null) {
//                origem.getLinhas().remove(linha);
//                origem = em.merge(origem);
//            }
//            Endereco destino = linha.getDestino();
//            if (destino != null) {
//                destino.getLinhas().remove(linha);
//                destino = em.merge(destino);
//            }
//            List<Viagem> viagens = linha.getViagens();
//            for (Viagem viagensViagem : viagens) {
//                viagensViagem.setLinha(null);
//                viagensViagem = em.merge(viagensViagem);
//            }
            em.remove(linha);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Linha> findLinhaEntities() {
        return findLinhaEntities(true, -1, -1);
    }

    public List<Linha> findLinhaEntities(int maxResults, int firstResult) {
        return findLinhaEntities(false, maxResults, firstResult);
    }

    private List<Linha> findLinhaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Linha as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Linha findLinha(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Linha.class, id);
        } finally {
            em.close();
        }
    }

    public int getLinhaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Linha as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
