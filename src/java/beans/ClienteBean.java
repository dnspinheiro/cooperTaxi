/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import modelo.Cliente;
import dao.ClienteJpaController;
import dao.EnderecoJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Endereco;
import util.JPAUtil;

/**
 *
 * @author denis
 */
@ManagedBean
@RequestScoped
public class ClienteBean {

    /**
     * Creates a new instance of ClienteBean
     */
    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();
    
    ClienteJpaController daoCliente = new ClienteJpaController(JPAUtil.factory);
    EnderecoJpaController daoEndereco = new EnderecoJpaController(JPAUtil.factory);
    
    private String mensagem;

    public ClienteBean() {
    }

    public void inserir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
        cliente.setEndereco(endereco);
        daoCliente.create(cliente);
        endereco = new Endereco();
        cliente = new Cliente();
        } catch (Exception ex) {
            context.addMessage("formCliente", new FacesMessage("Cliente não pode ser inserido"));
            Logger.getLogger(ViagemClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formCliente", new FacesMessage("Cliente foi inserido com sucesso!"));
    }
    
    public List<modelo.RelatorioCliente> pesquisarCliente(){
        return daoCliente.pesquisarInfoCliente();
    }

    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            cliente.setEndereco(endereco);
            daoCliente.edit(cliente);
            endereco = new Endereco();
            cliente = new Cliente();
        } catch (NonexistentEntityException ex) {
            context.addMessage("formCliente", new FacesMessage("Cliente não pode ser alterado"));
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formCliente", new FacesMessage("Cliente não pode ser alterado"));
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formCliente", new FacesMessage("Cliente foi alterado com sucesso!"));
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            daoCliente.destroy(cliente.getId());
            endereco = new Endereco();
            cliente = new Cliente();
        } catch (Exception ex) {
             context.addMessage("formCliente", new FacesMessage("Cliente não pode ser excluido"));
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formCliente", new FacesMessage("Cliente foi excluido com sucesso!"));
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        setEndereco(cliente.getEndereco());
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public List<Cliente> getClientes(){
        return daoCliente.findClienteEntities();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public List<Endereco> getEnderecos(){
        return daoEndereco.findEnderecoEntities();
    }
}
