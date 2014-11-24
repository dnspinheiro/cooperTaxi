/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CidadeJpaController;
import dao.EnderecoJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Cidade;
import modelo.Endereco;
import util.JPAUtil;

/**
 *
 * @author denis
 */
@ManagedBean
@RequestScoped
public class EnderecoBean {

    /**
     * Creates a new instance of EnderecoBean
     */
    private Endereco endereco = new Endereco();
    private Cidade cidade = new Cidade();
    
    EnderecoJpaController daoEndereco = new EnderecoJpaController(JPAUtil.factory);
    CidadeJpaController daoCidade = new CidadeJpaController(JPAUtil.factory);
    
    private String mensagem;

    public EnderecoBean() {
    }

    public void inserir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
        endereco.setCidade(cidade);
        daoEndereco.create(getEndereco());
        endereco = new Endereco();
        cidade = new Cidade();
        } catch (Exception ex) {
            Logger.getLogger(ViagemClienteBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage("formEndereco", new FacesMessage("Endereço não pode ser inserido"));
        }
        context.addMessage("formEndereco", new FacesMessage("Endereço foi inserido com sucesso!"));
    }

    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            endereco.setCidade(cidade);
            daoEndereco.edit(endereco);
            endereco = new Endereco();
            cidade = new Cidade();
        } catch (NonexistentEntityException ex) {
            context.addMessage("formEndereco", new FacesMessage("Endereço não foi alterado"));
            Logger.getLogger(EnderecoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formEndereco", new FacesMessage("Endereço não foi alterado"));
            Logger.getLogger(EnderecoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formEndereco", new FacesMessage("Endereço foi alterado com sucesso!"));
    }

    public void excluir() {
          FacesContext context = FacesContext.getCurrentInstance();
        try {
            daoEndereco.destroy(endereco.getId());
            endereco = new Endereco();
            cidade = new Cidade();
        } catch (Exception ex) {
               context.addMessage("formEndereco", new FacesMessage("Endereco não pode ser excluido"));
            Logger.getLogger(EnderecoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formEndereco", new FacesMessage("Endereco foi excluido com sucesso!"));
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        setCidade(endereco.getCidade());
        this.endereco = endereco;
    }
    
    public List<Endereco> getEnderecos(){
        return daoEndereco.findEnderecoEntities();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public List<Cidade> getCidades(){
        return daoCidade.findCidadeEntities();
    }
}
