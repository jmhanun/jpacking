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
public class DetalleRemitosPKT implements Serializable {

    private int idDetalleRemito;
    private int idRemito;

    public DetalleRemitosPKT() {
    }

    public DetalleRemitosPKT(int idDetalleRemito, int idRemito) {
        this.idDetalleRemito = idDetalleRemito;
        this.idRemito = idRemito;
    }

    public int getIdDetalleRemito() {
        return idDetalleRemito;
    }

    public void setIdDetalleRemito(int idDetalleRemito) {
        this.idDetalleRemito = idDetalleRemito;
    }

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    
}
