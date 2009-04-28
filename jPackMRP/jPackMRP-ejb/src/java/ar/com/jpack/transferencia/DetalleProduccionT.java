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
public class DetalleProduccionT implements Serializable {

    protected DetalleProduccionPKT detalleproduccionPK;
    private float duracion;
    private int prioridad;
    private EstadosT idEstado;
    private MaquinasT maquinas;
    private DetOrdenesProduccionT detordenesproduccion;

    public DetalleProduccionT() {
    }

    public DetalleProduccionT(DetalleProduccionPKT detalleproduccionPK, float duracion, int prioridad, EstadosT idEstado, MaquinasT maquinas, DetOrdenesProduccionT detordenesproduccion) {
        this.detalleproduccionPK = detalleproduccionPK;
        this.duracion = duracion;
        this.prioridad = prioridad;
        this.idEstado = idEstado;
        this.maquinas = maquinas;
        this.detordenesproduccion = detordenesproduccion;
    }

    public DetalleProduccionPKT getDetalleproduccionPK() {
        return detalleproduccionPK;
    }

    public void setDetalleproduccionPK(DetalleProduccionPKT detalleproduccionPK) {
        this.detalleproduccionPK = detalleproduccionPK;
    }

    public DetOrdenesProduccionT getDetordenesproduccion() {
        return detordenesproduccion;
    }

    public void setDetordenesproduccion(DetOrdenesProduccionT detordenesproduccion) {
        this.detordenesproduccion = detordenesproduccion;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public MaquinasT getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(MaquinasT maquinas) {
        this.maquinas = maquinas;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
