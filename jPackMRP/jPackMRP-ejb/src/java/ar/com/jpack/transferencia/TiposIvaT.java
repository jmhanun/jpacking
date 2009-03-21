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
public class TiposIvaT implements Serializable {

    private Integer idTipoIVA;
    private String descripcion;
    private String abreviatura;
    private EstadosT idEstado;
    private Collection<ClientesT> clientesCollection;

    public TiposIvaT() {
    }

    public TiposIvaT(Integer idTipoIVA, String descripcion, String abreviatura, EstadosT idEstado) {
        this.idTipoIVA = idTipoIVA;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.idEstado = idEstado;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Collection<ClientesT> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<ClientesT> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdTipoIVA() {
        return idTipoIVA;
    }

    public void setIdTipoIVA(Integer idTipoIVA) {
        this.idTipoIVA = idTipoIVA;
    }
}
