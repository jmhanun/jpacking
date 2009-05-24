/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class ListasPreciosT implements Serializable {

    private Integer idLista;
    private Date fechaDesde;
    private Date fechaHasta;
    private Date fechaModificacion;
    private EstadosT idEstado;
    private UsuariosT idUsuario;
    private Collection<PreciosT> preciosCollection;

    public ListasPreciosT() {
    }

    public ListasPreciosT(Integer idLista, Date fechaDesde, Date fechaHasta, Date fechaModificacion, EstadosT idEstado, UsuariosT idUsuario) {
        this.idLista = idLista;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.fechaModificacion = fechaModificacion;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Collection<PreciosT> getPreciosCollection() {
        return preciosCollection;
    }

    public void setPreciosCollection(Collection<PreciosT> preciosCollection) {
        this.preciosCollection = preciosCollection;
    }
}
