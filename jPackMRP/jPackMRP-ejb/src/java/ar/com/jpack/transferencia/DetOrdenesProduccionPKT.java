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
public class DetOrdenesProduccionPKT implements Serializable {
    private int idDetOrdProduccion;
    private int idOrdenProduccion;

    public DetOrdenesProduccionPKT() {
    }

    public DetOrdenesProduccionPKT(int idDetOrdProduccion, int idOrdenProduccion) {
        this.idDetOrdProduccion = idDetOrdProduccion;
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public int getIdDetOrdProduccion() {
        return idDetOrdProduccion;
    }

    public void setIdDetOrdProduccion(int idDetOrdProduccion) {
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(int idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

}
