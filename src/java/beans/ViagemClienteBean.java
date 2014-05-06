/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteJpaController;
import dao.ViagemClienteJpaController;
import dao.ViagemJpaController;
import dao.exceptions.NonexistentEntityException;
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
public class ViagemClienteBean {

    private ViagemCliente viagemCliente = new ViagemCliente();
    private Cliente cliente = new Cliente();
    private Linha linha = new Linha();
    private Funcionario funcionario = new Funcionario();
    private Veiculo veiculo = new Veiculo();
    private Viagem viagem = new Viagem();
    
    ViagemJpaController daoViagem = new ViagemJpaController(JPAUtil.factory);
    ViagemClienteJpaController viagemClienteDAO = new ViagemClienteJpaController(JPAUtil.factory);
    ClienteJpaController daoCliente = new ClienteJpaController(JPAUtil.factory);
    
    private List<Cliente> clientes;

    /**
     * Creates a new instance of FuncionarioMB
     */
    public ViagemClienteBean() {
    }

    public void inserir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            viagem.setLinha(linha);
            viagem.setFunc(funcionario);
            viagem.setVeiculo(veiculo);
            viagem.setDat(new Date());
            daoViagem.create(viagem);
            viagemCliente.setCliente(cliente);
            viagemCliente.setViagem(viagem);
            viagemClienteDAO.create(viagemCliente);
            funcionario = new Funcionario();
            linha = new Linha();
            viagemCliente = new ViagemCliente();
            cliente = new Cliente();
            veiculo = new Veiculo();
            viagem = new Viagem();

            //clientes.clear();
        } catch (Exception ex) {
            context.addMessage("formViagem", new FacesMessage("Viagem não pode ser excluido!"));
            Logger.getLogger(ViagemClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formViagem", new FacesMessage("Viagem foi inserido com sucesso!"));
    }

    /*public void inserirCliente() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            cliente = daoCliente.findCliente(cliente.getId());
            clientes.add(cliente);
        } catch (Exception ex) {
            context.addMessage("formAddCliente", new FacesMessage("Cliente não foi inserido!"));
            Logger.getLogger(ViagemClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formAddCliente", new FacesMessage("Cliente foi inserido na viagem com sucesso!"));
    }*/

    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            viagemCliente.setCliente(cliente);
            viagemCliente.setViagem(viagem);
            viagemClienteDAO.edit(viagemCliente);
            cliente = new Cliente();
            viagem = new Viagem();
            viagemCliente = new ViagemCliente();

            clientes.clear();
        } catch (NonexistentEntityException ex) {
            context.addMessage("formViagem", new FacesMessage("Viagem não foi alterada!"));
            Logger.getLogger(LinhaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formViagem", new FacesMessage("Viagem não foi alterada!"));
            Logger.getLogger(LinhaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long id = viagemClienteDAO.pesquisarViagemid(viagem.getId());
        
        try {
            viagemClienteDAO.destroy(id);
            daoViagem.destroy(viagem.getId());
            funcionario = new Funcionario();
            linha = new Linha();
            veiculo = new Veiculo();
            viagem = new Viagem();
        } catch (Exception ex) {
            context.addMessage("formViagem", new FacesMessage("Viagem não foi excluida!"));
            Logger.getLogger(ViagemClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formViagem", new FacesMessage("Viagem foiexculida com sucesso!"));
}

    public ViagemCliente getViagemCliente() {
        return viagemCliente;
    }

    public List<ViagemCliente> getViagemClientes() {
        return viagemClienteDAO.findViagemClienteEntities();
    }

    public void setViagemCliente(ViagemCliente viagemCliente) {
        this.viagemCliente = viagemCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
