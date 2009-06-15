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
public class DetalleProduccionRealPKT implements Serializable {

    private int idDetalleProduccionReal;
    private int idOrdenProduccion;
    private int idMaquina;
    private int idDetOrdProduccion;

    public DetalleProduccionRealPKT() {
    }

    public DetalleProduccionRealPKT(int idDetalleProduccionReal, int idOrdenProduccion, int idMaquina, int idDetOrdProduccion) {
        this.idDetalleProduccionReal = idDetalleProduccionReal;
        this.idOrdenProduccion = idOrdenProduccion;
        this.idMaquina = idMaquina;
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdDetOrdProduccion() {
        return idDetOrdProduccion;
    }

    public void setIdDetOrdProduccion(int idDetOrdProduccion) {
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdDetalleProduccionReal() {
        return idDetalleProduccionReal;
    }

    public void setIdDetalleProduccionReal(int idDetalleProduccionReal) {
        this.idDetalleProduccionReal = idDetalleProduccionReal;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(int idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }
}
