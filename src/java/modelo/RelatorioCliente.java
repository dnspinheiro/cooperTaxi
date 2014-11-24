/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author denis
 */
public class RelatorioCliente {
    private String nome;
    private Long distTotal;

    public RelatorioCliente(){}
    
    public RelatorioCliente(String n, Long dt){
        this.nome = n;
        this.distTotal = dt;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDistancia() {
        return distTotal;
    }

    public void setDistancia(Long distTotal) {
        this.distTotal = distTotal;
    }
}
