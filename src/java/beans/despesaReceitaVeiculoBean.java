/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.FinancaJpaController;
import dao.VeiculoJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Financa;
import modelo.RelatorioFinanca;
import modelo.RelatorioFinancaVeiculo;
import modelo.Veiculo;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import util.JPAUtil;

/**
 *
 * @author maycon
 */
@ManagedBean
@RequestScoped
public class despesaReceitaVeiculoBean {

    private double jan = 0.0; 
    
    private double fev= 0.0;
    
    private double mar= 0.0;
    
    private double abr= 0.0;
    
    private double mai= 0.0;
    
    private double jun= 0.0;
    
    private double jul= 0.0;
    
    private double ago= 0.0;
    
    private double set= 0.0;
    
    private double out= 0.0;
    
    private double nov= 0.0;
    
    private double dez= 0.0;
    
    private String ano;
    
    FinancaJpaController daoFinanca = new FinancaJpaController(JPAUtil.factory);
    
    VeiculoJpaController daoVeiculo = new VeiculoJpaController(JPAUtil.factory);
    
    List<RelatorioFinanca> result = new ArrayList<RelatorioFinanca>();
    
    List<Veiculo> veiculos = new ArrayList<Veiculo>();
    
    private Financa financa = new Financa();
    
    private Veiculo veiculo = new Veiculo();
    
    private RelatorioFinanca relatorioFinanca = new RelatorioFinanca();
    
    private BarChartModel barModel;
        
    public despesaReceitaVeiculoBean() {
        createBarModel();
    }
    
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Finança de Todos os Veículos");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Veículo");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Receita X Despesas");
        yAxis.setMin(0);
        yAxis.setMax(2000);
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries despesas = new ChartSeries();
        
        despesas.setLabel("Despesas");
        for(RelatorioFinancaVeiculo r : pesquisarDespesaVeiculos()){
            despesas.set(r.getPlaca(), r.getTotal());
        }
        
        ChartSeries receitas = new ChartSeries();
        
        receitas.setLabel("Receitas");
        for(RelatorioFinancaVeiculo r : pesquisarReceitaVeiculos()){
            receitas.set(r.getPlaca(), r.getTotal());
        }
 
        model.addSeries(despesas);
        model.addSeries(receitas);
         
        return model;
    }
    
    public CartesianChartModel getModelo() {    
        
        jan = 0.0;
        fev = 0.0;
        mar = 0.0;
        abr = 0.0;
        mai = 0.0;
        jun = 0.0;
        jul = 0.0;
        ago = 0.0;
        set = 0.0;
        out = 0.0;
        nov = 0.0;
        dez = 0.0; 
        mesModelo("despesa");
        
        CartesianChartModel modelo= new CartesianChartModel();
        
        LineChartSeries despesa = new LineChartSeries();
        despesa.setLabel("Despesa");
        
        despesa.set("Jan", jan);
        despesa.set("Fev", fev);  
        despesa.set("Mar", mar);  
        despesa.set("Abr", abr);  
        despesa.set("Mai", mai);
        despesa.set("Jun",jun);
        despesa.set("Jul",jul);
        despesa.set("Ago",ago);
        despesa.set("Set",set);
        despesa.set("Out",out);
        despesa.set("Nov",nov);
        despesa.set("Dez",dez);
        
        modelo.addSeries(despesa);
        
        jan = 0.0;
        fev = 0.0;
        mar = 0.0;
        abr = 0.0;
        mai = 0.0;
        jun = 0.0;
        jul = 0.0;
        ago = 0.0;
        set = 0.0;
        out = 0.0;
        nov = 0.0;
        dez = 0.0;        
        
        mesModelo("receita");
        LineChartSeries receita = new LineChartSeries();
        receita.setLabel("Receita");
        
        receita.set("Jan", jan);
        receita.set("Fev", fev);  
        receita.set("Mar", mar);  
        receita.set("Abr", abr);  
        receita.set("Mai", mai);
        receita.set("Jun",jun);
        receita.set("Jul",jul);
        receita.set("Ago",ago);
        receita.set("Set",set);
        receita.set("Out",out);
        receita.set("Nov",nov);
        receita.set("Dez",dez);

        modelo.addSeries(receita);
        
        return modelo;  
    }
    
    public void carregarVeiculo(Long id){
        setVeiculo(daoVeiculo.findVeiculo(id));
        if(getVeiculo() == null){
            setVeiculo(new Veiculo());
        } 
    }
    
    public List<RelatorioFinanca> pesquisarDespesaVeiculo(){
        return daoFinanca.pesquisarDespesaVeiculo();
    }
    
    public List<RelatorioFinanca> pesquisarReceitaVeiculo(){
        return daoFinanca.pesquisarReceitaVeiculo();
    }
    
    public List<RelatorioFinancaVeiculo> pesquisarDespesaVeiculos(){
        return daoFinanca.pesquisarDespesaVeiculos();
    }
    
    public List<RelatorioFinancaVeiculo> pesquisarReceitaVeiculos(){
        return daoFinanca.pesquisarReceitaVeiculos();
    }
    
    public List<RelatorioFinanca> Relatorio(String tipo){ 
        List<RelatorioFinanca> res = new ArrayList<RelatorioFinanca>();
        if(tipo.equalsIgnoreCase("despesa")){
            res = pesquisarDespesaVeiculo();
        }else{
            res = pesquisarReceitaVeiculo();
        }
        result = new ArrayList<RelatorioFinanca>();
        
        for(RelatorioFinanca r: res){
            if (r.getPlaca().equals(veiculo.getPlaca())){
                result.add(r);
            }
        }
        return result;
    }
    
    public void chamarModelo(){
        getModelo();
    }
    
    public void mesModelo(String tipo){
        List<RelatorioFinanca> res = new ArrayList<RelatorioFinanca>();
        if(tipo.equalsIgnoreCase("despesa")){
            res = Relatorio(tipo);
        }else{
            res = Relatorio(tipo);
        }
        for(RelatorioFinanca r: res){
            switch(r.getDat().getMonth()){
                case 0:
                    jan+= r.getTotal();
                    break;
                case 1:
                    fev+= r.getTotal();
                    break;
                case 2:
                    mar+= r.getTotal();
                    break;
                case 3:
                    abr+= r.getTotal();
                    break;
                case 4:
                    mai+= r.getTotal();
                    break;
                case 5:
                    jun+= r.getTotal();
                    break;
                case 6:
                    jul+= r.getTotal();
                    break;
                case 7:
                    ago+= r.getTotal();
                    break;
               case 8:
                    set+= r.getTotal();
                    break;
               case 9:
                    out+= r.getTotal();
                    break;
               case 10:
                    nov+= r.getTotal();
                    break;
               case 11:
                    dez+= r.getTotal();
                    break;
            } 
        }
    }



    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * @return the veiculos
     */
    public List<Veiculo> getVeiculos() {
        return daoVeiculo.findVeiculoEntities();
    }

    /**
     * @param veiculos the veiculos to set
     */
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    /**
     * @return the relatorioFinanca
     */
    public RelatorioFinanca getRelatorioFinanca() {
        return relatorioFinanca;
    }

    /**
     * @param relatorioFinanca the relatorioFinanca to set
     */
    public void setRelatorioFinanca(RelatorioFinanca relatorioFinanca) {
        this.relatorioFinanca = relatorioFinanca;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }
}
