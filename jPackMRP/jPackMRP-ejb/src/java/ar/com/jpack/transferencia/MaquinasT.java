/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author jmhanun
 */
public class MaquinasT implements Serializable {

    private Integer idMaquina;
    private String descripcion;
    private float horasMantenimiento;
    private float horasUso;
    private Collection<MantenimientoT> mantenimientoCollection;
    private Collection<DetalleProduccionT> detalleproduccionCollection;
    private ActividadesT idActividad;
    private EstadosT idEstado;

    public MaquinasT() {
    }

    public MaquinasT(Integer idMaquina, String descripcion, float horasMantenimiento, float horasUso, ActividadesT idActividad, EstadosT idEstado) {
        this.idMaquina = idMaquina;
        this.descripcion = descripcion;
        this.horasMantenimiento = horasMantenimiento;
        this.horasUso = horasUso;
        this.idActividad = idActividad;
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<DetalleProduccionT> getDetalleproduccionCollection() {
        return detalleproduccionCollection;
    }

    public void setDetalleproduccionCollection(Collection<DetalleProduccionT> detalleproduccionCollection) {
        this.detalleproduccionCollection = detalleproduccionCollection;
    }

    public float getHorasMantenimiento() {
        return horasMantenimiento;
    }

    public void setHorasMantenimiento(float horasMantenimiento) {
        this.horasMantenimiento = horasMantenimiento;
    }

    public float getHorasUso() {
        return horasUso;
    }

    public void setHorasUso(float horasUso) {
        this.horasUso = horasUso;
    }

    public ActividadesT getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(ActividadesT idActividad) {
        this.idActividad = idActividad;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Collection<MantenimientoT> getMantenimientoCollection() {
        return mantenimientoCollection;
    }

    public void setMantenimientoCollection(Collection<MantenimientoT> mantenimientoCollection) {
        this.mantenimientoCollection = mantenimientoCollection;
    }

    @Override
    public String toString() {
        return this.getDescripcion();
    }
}