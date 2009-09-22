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
public class ActividadesT implements Serializable{
    private Integer idActividad;
    private String descripcion;
    private Collection<ActividadesArticulosT> actividadesxarticulosCollection;
    private Collection<MaquinasT> maquinasCollection;

    public ActividadesT() {
    }

    public ActividadesT(Integer idActividad, String descripcion) {
        this.idActividad = idActividad;
        this.descripcion = descripcion;
    }

    public Collection<ActividadesArticulosT> getActividadesxarticulosCollection() {
        return actividadesxarticulosCollection;
    }

    public void setActividadesxarticulosCollection(Collection<ActividadesArticulosT> actividadesxarticulosCollection) {
        this.actividadesxarticulosCollection = actividadesxarticulosCollection;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Collection<MaquinasT> getMaquinasCollection() {
        return maquinasCollection;
    }

    public void setMaquinasCollection(Collection<MaquinasT> maquinasCollection) {
        this.maquinasCollection = maquinasCollection;
    }

    @Override
    public String toString() {
        return this.getDescripcion();
    }

}
