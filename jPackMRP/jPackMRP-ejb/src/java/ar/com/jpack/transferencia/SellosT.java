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
public class SellosT implements Serializable {

    private Integer idSello;
    private String descripcion;
    private float tamano;
    private Collection<ClientesT> idClienteCollection;
    private EstadosT idEstado;
    private ArticulosT idArticulo;

    public SellosT() {
    }

    public SellosT(Integer idSello, String descripcion, float tamano, Collection<ClientesT> idClienteCollection, EstadosT idEstado, ArticulosT idArticulo) {
        this.idSello = idSello;
        this.descripcion = descripcion;
        this.tamano = tamano;
        this.idClienteCollection = idClienteCollection;
        this.idEstado = idEstado;
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArticulosT getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(ArticulosT idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Collection<ClientesT> getIdClienteCollection() {
        return idClienteCollection;
    }

    public void setIdClienteCollection(Collection<ClientesT> idClienteCollection) {
        this.idClienteCollection = idClienteCollection;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdSello() {
        return idSello;
    }

    public void setIdSello(Integer idSello) {
        this.idSello = idSello;
    }

    public float getTamano() {
        return tamano;
    }

    public void setTamano(float tamano) {
        this.tamano = tamano;
    }
}
