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
public class SellosDetOrdenT implements Serializable {

    protected SellosDetOrdenPKT sellosxdetordenPK;
    private int orden;
    private DetOrdenesProduccionT detordenesproduccion;

    public SellosDetOrdenT() {
    }

    public SellosDetOrdenT(SellosDetOrdenPKT sellosxdetordenPK, int orden, DetOrdenesProduccionT detordenesproduccion) {
        this.sellosxdetordenPK = sellosxdetordenPK;
        this.orden = orden;
        this.detordenesproduccion = detordenesproduccion;
    }

    public DetOrdenesProduccionT getDetordenesproduccion() {
        return detordenesproduccion;
    }

    public void setDetordenesproduccion(DetOrdenesProduccionT detordenesproduccion) {
        this.detordenesproduccion = detordenesproduccion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public SellosDetOrdenPKT getSellosxdetordenPK() {
        return sellosxdetordenPK;
    }

    public void setSellosxdetordenPK(SellosDetOrdenPKT sellosxdetordenPK) {
        this.sellosxdetordenPK = sellosxdetordenPK;
    }

}
