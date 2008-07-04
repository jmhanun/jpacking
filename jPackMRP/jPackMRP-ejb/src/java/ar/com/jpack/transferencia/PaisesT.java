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
public class PaisesT implements Serializable {

    private Integer idPais;
    private String pais;
    private String siglas;
    private Collection<ProvinciasT> provinciasCollection;

    public PaisesT() {
    }

    public PaisesT(Integer idPais, String pais, String siglas) {
        this.idPais = idPais;
        this.pais = pais;
        this.siglas = siglas;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Collection<ProvinciasT> getProvinciasCollection() {
        return provinciasCollection;
    }

    public void setProvinciasCollection(Collection<ProvinciasT> provinciasCollection) {
        this.provinciasCollection = provinciasCollection;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
}
