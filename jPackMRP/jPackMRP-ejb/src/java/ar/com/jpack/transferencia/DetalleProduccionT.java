/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class DetalleProduccionT implements Serializable {

    private Integer idDetalleProduccion;
    private int prioridad;
    private Date fechaInicioEstimada;
    private Date fechaFinEstimada;
    private Date fechaInicioProceso;
    private Date fechaFinProceso;
    private EstadosT idEstado;
    private MaquinasT idMaquina;
    private DetOrdenesProduccionT detordenesproduccion;
    private Collection<DesviosT> desviosCollection;

    public DetalleProduccionT() {
    }

    public DetalleProduccionT(Integer idDetalleProduccion, int prioridad, Date fechaInicioEstimada, Date fechaFinEstimada, Date fechaInicioProceso, Date fechaFinProceso, EstadosT idEstado, MaquinasT idMaquina, DetOrdenesProduccionT detordenesproduccion) {
        this.idDetalleProduccion = idDetalleProduccion;
        this.prioridad = prioridad;
        this.fechaInicioEstimada = fechaInicioEstimada;
        this.fechaFinEstimada = fechaFinEstimada;
        this.fechaInicioProceso = fechaInicioProceso;
        this.fechaFinProceso = fechaFinProceso;
        this.idEstado = idEstado;
        this.idMaquina = idMaquina;
        this.detordenesproduccion = detordenesproduccion;
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

    public Integer getIdDetalleProduccion() {
        return idDetalleProduccion;
    }

    public void setIdDetalleProduccion(Integer idDetalleProduccion) {
        this.idDetalleProduccion = idDetalleProduccion;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public MaquinasT getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(MaquinasT idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Collection<DesviosT> getDesviosCollection() {
        return desviosCollection;
    }

    public void setDesviosCollection(Collection<DesviosT> desviosCollection) {
        this.desviosCollection = desviosCollection;
    }
    
}