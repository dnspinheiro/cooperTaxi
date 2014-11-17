/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author denis
 */
public class RelatorioEstado {
    private String sigla;
    private Long quantCidades;
    private Long popTotal;
    
    public RelatorioEstado(){}
    
    public RelatorioEstado(String s, Long q, Long p){
        sigla = s;
        quantCidades = q;
        popTotal = p;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getQuantCidades() {
        return quantCidades;
    }

    public void setQuantCidades(Long quantCidades) {
        this.quantCidades = quantCidades;
    }

    public Long getPopTotal() {
        return popTotal;
    }

    public void setPopTotal(Long popTotal) {
        this.popTotal = popTotal;
    }
}
