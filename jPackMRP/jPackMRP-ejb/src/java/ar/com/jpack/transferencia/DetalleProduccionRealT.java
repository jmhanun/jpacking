/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class DetalleProduccionRealT implements Serializable {

    protected DetalleProduccionRealPKT detalleproduccionrealPK;
    private float duracion;
    private int prioridad;
    private Date fechaInicioReal;
    private Date fechaFinReal;
    private EstadosT idEstado;
    private MaquinasT maquinas;
    private DetOrdenesProduccionT detordenesproduccion;

    public DetalleProduccionRealT() {
    }

    public DetalleProduccionRealT(DetalleProduccionRealPKT detalleproduccionrealPK, float duracion, int prioridad, Date fechaInicioReal, Date fechaFinReal, EstadosT idEstado, MaquinasT maquinas, DetOrdenesProduccionT detordenesproduccion) {
        this.detalleproduccionrealPK = detalleproduccionrealPK;
        this.duracion = duracion;
        this.prioridad = prioridad;
        this.fechaInicioReal = fechaInicioReal;
        this.fechaFinReal = fechaFinReal;
        this.idEstado = idEstado;
        this.maquinas = maquinas;
        this.detordenesproduccion = detordenesproduccion;
    }

    public DetalleProduccionRealPKT getDetalleproduccionrealPK() {
        return detalleproduccionrealPK;
    }

    public void setDetalleproduccionrealPK(DetalleProduccionRealPKT detalleproduccionrealPK) {
        this.detalleproduccionrealPK = detalleproduccionrealPK;
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

    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public Date getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(Date fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
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

