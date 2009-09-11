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
public class TiposDesviosT implements Serializable {

    private Integer idTipoDesvio;
    private String motivo;
    private Collection<DesviosT> desviosCollection;

    public TiposDesviosT() {
    }

    public TiposDesviosT(Integer idTipoDesvio, String motivo) {
        this.idTipoDesvio = idTipoDesvio;
        this.motivo = motivo;
    }

    public Collection<DesviosT> getDesviosCollection() {
        return desviosCollection;
    }

    public void setDesviosCollection(Collection<DesviosT> desviosCollection) {
        this.desviosCollection = desviosCollection;
    }

    public Integer getIdTipoDesvio() {
        return idTipoDesvio;
    }

    public void setIdTipoDesvio(Integer idTipoDesvio) {
        this.idTipoDesvio = idTipoDesvio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return this.getMotivo();
    }
}
