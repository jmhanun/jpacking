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
public class ProvinciasT implements Serializable {

    private Integer idProvincia;
    private String provincia;
    private String letra;
    private PaisesT idPais;
    private Collection<LocalidadesT> localidadesCollection;

    public ProvinciasT() {
    }

    public ProvinciasT(Integer idProvincia, String provincia, String letra, PaisesT idPais) {
        this.idProvincia = idProvincia;
        this.provincia = provincia;
        this.letra = letra;
        this.idPais = idPais;
    }

    public PaisesT getIdPais() {
        return idPais;
    }

    public void setIdPais(PaisesT idPais) {
        this.idPais = idPais;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Collection<LocalidadesT> getLocalidadesCollection() {
        return localidadesCollection;
    }

    public void setLocalidadesCollection(Collection<LocalidadesT> localidadesCollection) {
        this.localidadesCollection = localidadesCollection;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return this.getProvincia();
    }
}
