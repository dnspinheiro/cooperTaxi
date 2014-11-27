/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.VeiculoJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
public class VeiculoBean {

    VeiculoJpaController veiculoDAO = new VeiculoJpaController(JPAUtil.factory);
    private Veiculo veiculo = new Veiculo();
    
    private String mensagem;
    
    private BarChartModel barModel;

    /**
     * Creates a new instance of FuncionarioMB
     */
    public VeiculoBean() {
        createBarModel();
    }

    public void inserir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            veiculoDAO.create(veiculo);
            veiculo = new Veiculo();
        } catch (Exception ex) {
            Logger.getLogger(VeiculoBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage("formVeiculo", new FacesMessage("Veículo não pode ser inserido"));
        }
        context.addMessage("formVeiculo", new FacesMessage("Veículo foi inserido com sucesso!"));
    }
    
    public List<RelatorioVeiculo> pesquisarInfoVeiculos(){
        return veiculoDAO.pesquisarInfoVeiculo();
    }
    
    public List<RelatorioVeiculo> pesquisarVeiculoQuantViagem(){
        return veiculoDAO.pesquisarVeiculoQuantViagem();
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries veis = new ChartSeries();
        veis.setLabel("Km * 100");
        
        for(RelatorioVeiculo r : pesquisarInfoVeiculos()){
            veis.set(r.getPlaca(), r.getDistTotal()/100);
        }
        
        ChartSeries veisViagem = new ChartSeries();
        veisViagem.setLabel("Nº de Viagens");
        
        for(RelatorioVeiculo r : pesquisarVeiculoQuantViagem()){
            veisViagem.set(r.getPlaca(), r.getDistTotal());
        }
 
        model.addSeries(veis);
        model.addSeries(veisViagem);
         
        return model;
    }
    
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Rodagem de Veículos");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Veículo");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Km");
        yAxis.setMin(0);
        yAxis.setMax(25);
    }

    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            veiculoDAO.edit(veiculo);
            veiculo = new Veiculo();
        } catch (NonexistentEntityException ex) {
            context.addMessage("formVeiculo", new FacesMessage("Veículo não pode ser alterado"));
            Logger.getLogger(VeiculoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage("formVeiculo", new FacesMessage("Veículo não pode ser alterado"));
            Logger.getLogger(VeiculoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formVeiculo", new FacesMessage("Veículo foi alterado com sucesso!"));
    }

    public void excluir() {
          FacesContext context = FacesContext.getCurrentInstance();
        try {
            veiculoDAO.destroy(veiculo.getId());
            veiculo = new Veiculo();
        } catch (Exception ex) {
             context.addMessage("formVeiculo", new FacesMessage("Veiculo não pode ser excluido"));
            Logger.getLogger(VeiculoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.addMessage("formVeiculo", new FacesMessage("Veículo foi excluido com sucesso!"));
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public List<Veiculo> getVeiculos(){
        return veiculoDAO.findVeiculoEntities();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
