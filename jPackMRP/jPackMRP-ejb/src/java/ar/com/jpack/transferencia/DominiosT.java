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
public class DominiosT implements Serializable{

    private Integer idDominio;
    private String descripcion;
    private Collection<EstadosT> estadosCollection;

    public DominiosT() {
    }

    public DominiosT(Integer idDominio, String descripcion, Collection<EstadosT> estadosCollection) {
        this.idDominio = idDominio;
        this.descripcion = descripcion;
        this.estadosCollection = estadosCollection;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<EstadosT> getEstadosCollection() {
        return estadosCollection;
    }

    public void setEstadosCollection(Collection<EstadosT> estadosCollection) {
        this.estadosCollection = estadosCollection;
    }

    public Integer getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(Integer idDominio) {
        this.idDominio = idDominio;
    }

}
