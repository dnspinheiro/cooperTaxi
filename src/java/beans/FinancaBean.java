/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.FinancaJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Financa;
import modelo.RelatorioFinanca;
import util.JPAUtil;

/**
 *
 * @author denis
 */
@ManagedBean
@RequestScoped
public class FinancaBean {

    /**
     * Creates a new instance of FinancaBean
     */
    private Financa financa = new Financa();
    FinancaJpaController daoFinanca = new FinancaJpaController(JPAUtil.factory);
    private String mensagem;

    public FinancaBean() {
    }

    public void inserir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
        financa.setDat(new java.util.Date());
        daoFinanca.create(financa);
        financa = new Financa();
        } catch (Exception ex) {
            context.addMessage("formFinanca", new FacesMessage("Financa não pode ser inserido"));
            Logger.getLogger(ViagemClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<RelatorioFinanca> pesquisarInfoFinancas(){
        return daoFinanca.pesquisarInfoFinanca();
    }
    
    public List<RelatorioFinanca> pesquisarFinancasUltimoMes(){
        return daoFinanca.pesquisarInfoUltimo();
    }
    

    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            financa.setDat(new java.util.Date());
            daoFinanca.edit(financa);
            financa = new Financa();
        } catch (NonexistentEntityException ex) {
            context.addMessage("formFinanca", new FacesMessage("Financa não pode ser alterada"));
            Logger.getLogger(FinancaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formFinanca", new FacesMessage("Financa não pode ser alterada"));
            Logger.getLogger(FinancaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formFinanca", new FacesMessage("Financa foi alterada com sucesso!"));
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            daoFinanca.destroy(financa.getId());
            financa = new Financa();
        } catch (Exception ex) {
            context.addMessage("formFinanca", new FacesMessage("Financa não pode ser excluido"));
            Logger.getLogger(FinancaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formFinanca", new FacesMessage("Financa foi excluido com sucesso!"));
    }

    public Financa getFinanca() {
        return financa;
    }

    public void setFinanca(Financa financa) {
        this.financa = financa;
    }

    public List<Financa> getFinancas() {
        return daoFinanca.findFinancaEntities();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
