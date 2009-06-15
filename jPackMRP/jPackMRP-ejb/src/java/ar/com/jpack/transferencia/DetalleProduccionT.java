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
public class DetalleProduccionT implements Serializable {

    protected DetalleProduccionPKT detalleproduccionPK;
    private int prioridad;
    private Date fechaInicioEstimada;
    private Date fechaFinEstimada;
    private Date fechaInicioProceso;
    private Date fechaFinProceso;
    private EstadosT idEstado;
    private MaquinasT maquinas;
    private DetOrdenesProduccionT detordenesproduccion;

    public DetalleProduccionT() {
    }

    public DetalleProduccionT(DetalleProduccionPKT detalleproduccionPK, int prioridad, Date fechaInicioEstimada, Date fechaFinEstimada, Date fechaInicioProceso, Date fechaFinProceso, EstadosT idEstado, MaquinasT maquinas, DetOrdenesProduccionT detordenesproduccion) {
        this.detalleproduccionPK = detalleproduccionPK;
        this.prioridad = prioridad;
        this.fechaInicioEstimada = fechaInicioEstimada;
        this.fechaFinEstimada = fechaFinEstimada;
        this.fechaInicioProceso = fechaInicioProceso;
        this.fechaFinProceso = fechaFinProceso;
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

    public Date getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public void setFechaFinEstimada(Date fechaFinEstimada) {
        this.fechaFinEstimada = fechaFinEstimada;
    }

    public Date getFechaFinProceso() {
        return fechaFinProceso;
    }

    public void setFechaFinProceso(Date fechaFinProceso) {
        this.fechaFinProceso = fechaFinProceso;
    }

    public Date getFechaInicioEstimada() {
        return fechaInicioEstimada;
    }

    public void setFechaInicioEstimada(Date fechaInicioEstimada) {
        this.fechaInicioEstimada = fechaInicioEstimada;
    }

    public Date getFechaInicioProceso() {
        return fechaInicioProceso;
    }

    public void setFechaInicioProceso(Date fechaInicioProceso) {
        this.fechaInicioProceso = fechaInicioProceso;
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