/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author denis
 */
public class RelatorioFinanca {
    
    private String tipo;
    private Long total;
    
    public RelatorioFinanca(){}
    
    public RelatorioFinanca(String n, Long t){
        this.tipo = n;
        this.total = t;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    
}
