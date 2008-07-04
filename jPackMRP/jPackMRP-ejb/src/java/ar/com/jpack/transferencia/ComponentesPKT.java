/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.transferencia;

import java.io.Serializable;

/**
 *
 * @author jmhanun
 */
public class ComponentesPKT implements Serializable{
    private int idArticulo;
    private int idComponente;

    public ComponentesPKT() {
    }

    public ComponentesPKT(int idArticulo, int idComponente) {
        this.idArticulo = idArticulo;
        this.idComponente = idComponente;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }
    
}
