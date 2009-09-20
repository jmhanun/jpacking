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
public class LocalidadesT implements Serializable {

    private Integer idLocalidad;
    private String localidad;
    private String codigoPostal;
    private Collection<DomiciliosT> domiciliosCollection;
    private ProvinciasT idProvincia;

    public LocalidadesT() {
    }

    public LocalidadesT(Integer idLocalidad, String localidad, String codigoPostal, ProvinciasT idProvincia) {
        this.idLocalidad = idLocalidad;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.idProvincia = idProvincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Collection<DomiciliosT> getDomiciliosCollection() {
        return domiciliosCollection;
    }

    public void setDomiciliosCollection(Collection<DomiciliosT> domiciliosCollection) {
        this.domiciliosCollection = domiciliosCollection;
    }

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public ProvinciasT getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(ProvinciasT idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return (this.getLocalidad() + " (" + this.getCodigoPostal() + ")");
    }
}
