/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author denis
 */
public class RelatorioVeiculo {
    private String placa;
    private Long distTotal;
    
    public RelatorioVeiculo(){}
    
    public RelatorioVeiculo(String p, Long dt){
        this.placa = p;
        this.distTotal = dt;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getDistTotal() {
        return distTotal;
    }

    public void setDistTotal(Long distTotal) {
        this.distTotal = distTotal;
    }
    
}
