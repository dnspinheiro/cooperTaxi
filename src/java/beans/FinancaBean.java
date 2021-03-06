/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.FinancaJpaController;
import dao.VeiculoJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Financa;
import modelo.RelatorioFinanca;
import modelo.RelatorioVeiculo;
import modelo.Veiculo;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
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
    private Veiculo veiculo = new Veiculo();
    VeiculoJpaController daoVeiculo = new VeiculoJpaController(JPAUtil.factory);
    FinancaJpaController daoFinanca = new FinancaJpaController(JPAUtil.factory);
    private String mensagem;

    public FinancaBean() {
    }

    public void inserir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{

        //financa.setDat(new Date());
        financa.setVeiculo(veiculo);
        daoFinanca.create(financa);
        financa = new Financa();
        veiculo = new Veiculo();
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
            financa.setVeiculo(veiculo);
            daoFinanca.edit(financa);
            financa = new Financa();
            veiculo = new Veiculo();
            context.addMessage("formFinanca", new FacesMessage("Financa foi alterada com sucesso!"));
        } catch (NonexistentEntityException ex) {
            context.addMessage("formFinanca", new FacesMessage("Financa não pode ser alterada"));
            Logger.getLogger(FinancaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formFinanca", new FacesMessage("Financa não pode ser alterada"));
            Logger.getLogger(FinancaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            daoFinanca.destroy(financa.getId());
            financa = new Financa();
            veiculo = new Veiculo();
            context.addMessage("formFinanca", new FacesMessage("Financa foi excluido com sucesso!"));
        } catch (Exception ex) {
            context.addMessage("formFinanca", new FacesMessage("Financa não pode ser excluido"));
            Logger.getLogger(FinancaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String formatarData(Date d){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatador.format(d);
    }

    public Financa getFinanca() {
        return financa;
    }

    public void setFinanca(Financa financa) {
        setVeiculo(financa.getVeiculo());
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public List<Veiculo> getVeiculos(){
        return daoVeiculo.findVeiculoEntities();    
    }
}
