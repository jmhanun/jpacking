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
public class DetalleFacturasPKT implements Serializable {

    private int idDetalle;
    private int idFactura;

    public DetalleFacturasPKT() {
    }

    public DetalleFacturasPKT(int idDetalle, int idFactura) {
        this.idDetalle = idDetalle;
        this.idFactura = idFactura;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
}
