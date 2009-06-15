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
public class MantenimientoT implements Serializable {

    private Integer idMantenimiento;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private MaquinasT idMaquina;
    private TiposServiciosT idTipoServicio;

    public MantenimientoT() {
    }

    public MantenimientoT(Integer idMantenimiento, String descripcion, Date fechaInicio, Date fechaFin, MaquinasT idMaquina, TiposServiciosT idTipoServicio) {
        this.idMantenimiento = idMantenimiento;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idMaquina = idMaquina;
        this.idTipoServicio = idTipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public MaquinasT getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(MaquinasT idMaquina) {
        this.idMaquina = idMaquina;
    }

    public TiposServiciosT getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(TiposServiciosT idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }
}
