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
public class DetalleProduccionPKT implements Serializable {

    private int idDetalleProduccion;
    private int idOrdenProduccion;
    private int idMaquina;
    private int idDetOrdProduccion;

    public DetalleProduccionPKT() {
    }

    public DetalleProduccionPKT(int idDetalleProduccion, int idOrdenProduccion, int idMaquina, int idDetOrdProduccion) {
        this.idDetalleProduccion = idDetalleProduccion;
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

    public int getIdDetalleProduccion() {
        return idDetalleProduccion;
    }

    public void setIdDetalleProduccion(int idDetalleProduccion) {
        this.idDetalleProduccion = idDetalleProduccion;
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
