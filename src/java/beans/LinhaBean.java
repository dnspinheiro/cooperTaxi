/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.LinhaJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Endereco;
import modelo.Linha;
import util.JPAUtil;

/**
 *
 * @author denis
 */
@ManagedBean
@RequestScoped
public class LinhaBean {
    
    LinhaJpaController linhaDAO = new LinhaJpaController(JPAUtil.factory);

    private Linha linha = new Linha();
    
    private Endereco origem = new Endereco();
    private Endereco destino = new Endereco();
    
    private String mensagem;
    
    /**
     * Creates a new instance of FuncionarioMB
     */
    public LinhaBean() {
    }
    
    public void inserir(){
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            linha.setOrigem(origem);
            linha.setDestino(destino);
            linhaDAO.create(linha);
            linha = new Linha();
            origem = new Endereco();
            destino = new Endereco();
        }catch(Exception ex){
            context.addMessage("formLinha", new FacesMessage("Linha não pode ser inserido"));
           Logger.getLogger(LinhaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formLinha", new FacesMessage("Linha foi inserido com sucesso!"));
    }
    
    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            linha.setOrigem(origem);
            linha.setDestino(destino);
            linhaDAO.edit(linha);
            linha = new Linha();
            origem = new Endereco();
            destino = new Endereco();
        } catch (NonexistentEntityException ex) {
            context.addMessage("formLinha", new FacesMessage("Linha não pode ser alterado"));
            Logger.getLogger(LinhaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formLinha", new FacesMessage("Linha não pode ser alterado"));
            Logger.getLogger(LinhaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formLinha", new FacesMessage("Linha foi alterado com sucesso!"));
    }
    
    public void excluir() {
         FacesContext context = FacesContext.getCurrentInstance();
        try {
            linhaDAO.destroy(linha.getId());
            linha = new Linha();
            origem = new Endereco();
            destino = new Endereco();
        } catch (Exception ex) {
            context.addMessage("formLinha", new FacesMessage("Linha não pode ser excluido"));
            Logger.getLogger(LinhaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formLinha", new FacesMessage("Linha foi excluido com sucesso!"));
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }
    
    public List<Linha> getLinhas(){
        return linhaDAO.findLinhaEntities();
    }

    public Endereco getOrigem() {
        return origem;
    }

    public void setOrigem(Endereco origem) {
        this.origem = origem;
    }

    public Endereco getDestino() {
        return destino;
    }

    public void setDestino(Endereco destino) {
        this.destino = destino;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
