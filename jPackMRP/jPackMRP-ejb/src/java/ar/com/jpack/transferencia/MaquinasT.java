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
    private ActividadesT idActividad;
    private EstadosT idEstado;
    private Collection<DetalleProduccionT> detalleproduccionCollection;

    public MaquinasT() {
    }

    public MaquinasT(Integer idMaquina, String descripcion, ActividadesT idActividad, EstadosT idEstado) {
        this.idMaquina = idMaquina;
        this.descripcion = descripcion;
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
}
