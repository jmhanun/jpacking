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
public class TiposServiciosT implements Serializable {

    private Integer idTipoServicio;
    private String descripcion;
    private Collection<MantenimientoT> mantenimientoCollection;

    public TiposServiciosT() {
    }

    public TiposServiciosT(Integer idTipoServicio, String descripcion) {
        this.idTipoServicio = idTipoServicio;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Collection<MantenimientoT> getMantenimientoCollection() {
        return mantenimientoCollection;
    }

    public void setMantenimientoCollection(Collection<MantenimientoT> mantenimientoCollection) {
        this.mantenimientoCollection = mantenimientoCollection;
    }
}
