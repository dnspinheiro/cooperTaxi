/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author denis
 */
public class RelatorioFinanca {
    
    private String placa;
    private Double total;
    private Date dat;
    
    public RelatorioFinanca(){}
    
    public RelatorioFinanca(String n, Double t, Date d){
        this.placa = n;
        this.total = t;
        this.dat = d;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the dat
     */
    public Date getDat() {
        return dat;
    }

    /**
     * @param dat the dat to set
     */
    public void setDat(Date dat) {
        this.dat = dat;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
}
