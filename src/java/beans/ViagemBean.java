/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ViagemClienteJpaController;
import dao.ViagemJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Linha;
import modelo.Veiculo;
import modelo.Viagem;
import modelo.ViagemCliente;
import util.JPAUtil;

/**
 *
 * @author denis
 */
@ManagedBean
@RequestScoped
public class ViagemBean {

    /**
     * Creates a new instance of ViagemBean
     */
    private Viagem viagem = new Viagem();
    private Linha linha = new Linha();
    private Funcionario funcionario = new Funcionario();
    private Cliente cliente = new Cliente();
    private Veiculo veiculo = new Veiculo();
    private ViagemCliente viagemCliente = new ViagemCliente();
    
    ViagemClienteJpaController daoViagemCliente = new ViagemClienteJpaController(JPAUtil.factory);
    ViagemJpaController daoViagem = new ViagemJpaController(JPAUtil.factory);
    
    private List<Cliente> clientes;
    private String mensagem;

    public ViagemBean() {
    }

    public void inserir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            viagem.setLinha(linha);
            viagem.setFunc(funcionario);
            viagem.setVeiculo(veiculo);
            viagem.setDat(new Date());
            daoViagem.create(viagem);
            getViagemCliente().setCliente(cliente);
            getViagemCliente().setViagem(viagem);
            daoViagemCliente.create(getViagemCliente());
            funcionario = new Funcionario();
            linha = new Linha();
            viagemCliente = new ViagemCliente();
            cliente = new Cliente();
            veiculo = new Veiculo();
            viagem = new Viagem();
        } catch (Exception ex) {
            Logger.getLogger(ViagemClienteBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage("formViagem", new FacesMessage("Viagem não pode ser inserido"));
        }
        context.addMessage("formViagem", new FacesMessage("Viagem foi inserido com sucesso!"));
    }

    public void inserirCliente() {
        clientes.add(cliente);
        setMensagem("Cliente adicionado na viagem!");
    }

    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            viagem.setLinha(linha);
            viagem.setFunc(funcionario);
            viagem.setVeiculo(veiculo);
            viagem.setDat(new Date());
            daoViagem.edit(viagem);
            funcionario = new Funcionario();
            linha = new Linha();
            veiculo = new Veiculo();
            viagem = new Viagem();
        } catch (NonexistentEntityException ex) {
            context.addMessage("formViagem", new FacesMessage("Viagem não pode ser alterado"));
            Logger.getLogger(ViagemBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formViagem", new FacesMessage("Viagem não pode ser alterado"));
            Logger.getLogger(ViagemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formViagem", new FacesMessage("Viagem foi alterado com sucesso!"));
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            daoViagem.destroy(viagem.getId());
            funcionario = new Funcionario();
            linha = new Linha();
            veiculo = new Veiculo();
            viagem = new Viagem();
        } catch (Exception ex) {
            context.addMessage("formViagem", new FacesMessage("Viagem não pode ser excluido"));
            Logger.getLogger(ViagemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formViagem", new FacesMessage("Viagem foi excluido com sucesso!"));
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public List<Viagem> getViagens() {
        return daoViagem.findViagemEntities();
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ViagemCliente getViagemCliente() {
        return viagemCliente;
    }

    public void setViagemCliente(ViagemCliente viagemCliente) {
        this.viagemCliente = viagemCliente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
