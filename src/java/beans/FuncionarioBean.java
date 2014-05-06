/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.FuncionarioJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Endereco;
import modelo.Funcionario;
import util.JPAUtil;

/**
 *
 * @author denis
 */
@ManagedBean
@RequestScoped
public class FuncionarioBean {
    
    FuncionarioJpaController funcionarioDAO = new FuncionarioJpaController(JPAUtil.factory);

    private Funcionario funcionario = new Funcionario();
    
    private Endereco endereco = new Endereco();
    
    private String mensagem;
    
    /**
     * Creates a new instance of FuncionarioBean
     */
    public FuncionarioBean() {
    }
    
    public void inserir(){
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            funcionario.setEndereco(endereco);
            funcionario.setNascimento(new Date());
            funcionarioDAO.create(funcionario);
            funcionario = new Funcionario();
            endereco = new Endereco();
        }catch(Exception ex){
            context.addMessage("formFuncionario", new FacesMessage("Funcionario não pode ser inserido"));
           Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formFuncionario", new FacesMessage("Funcionario foi inserido com sucesso!"));
    }
    
    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            funcionario.setEndereco(endereco);
            funcionarioDAO.edit(funcionario);
            funcionario = new Funcionario();
            endereco = new Endereco();
        } catch (NonexistentEntityException ex) {
            context.addMessage("formFuncionario", new FacesMessage("Funcionario não pode ser alterado"));
            Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formFuncionario", new FacesMessage("Funcionario não pode ser alterado"));
            Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formFuncionario", new FacesMessage("Funcionario foi alterado com sucesso!"));
    }
    
    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            funcionarioDAO.destroy(funcionario.getId());
            funcionario = new Funcionario();
            endereco = new Endereco();
        } catch (Exception ex) {
            context.addMessage("formFuncionario", new FacesMessage("Funcionario não pode ser excluido"));
            Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formFuncionario", new FacesMessage("Funcionario foi excluido com sucesso!"));
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public List<Funcionario> getFuncionarios(){
        return funcionarioDAO.findFuncionarioEntities();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
