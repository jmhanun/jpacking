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
public class OrdenesDepositoT implements Serializable {

    private Integer idOrdenDeposito;
    private int nroOrdenDep;
    private Date fecha;
    private Date fechaModificacion;
    private Collection<DetMovimientosStockT> detmovimientosstockCollection;
    private Collection<DetOrdenesDepositoT> detordenesdepositoCollection;
    private EstadosT idEstado;
    private TiposComprobantesT idTipoComprobante;
    private UsuariosT idUsuario;

    public OrdenesDepositoT() {
    }

    public OrdenesDepositoT(Integer idOrdenDeposito, int nroOrdenDep, Date fecha, Date fechaModificacion, EstadosT idEstado, TiposComprobantesT idTipoComprobante, UsuariosT idUsuario) {
        this.idOrdenDeposito = idOrdenDeposito;
        this.nroOrdenDep = nroOrdenDep;
        this.fecha = fecha;
        this.fechaModificacion = fechaModificacion;
        this.idEstado = idEstado;
        this.idTipoComprobante = idTipoComprobante;
        this.idUsuario = idUsuario;
    }

    public Collection<DetMovimientosStockT> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<DetMovimientosStockT> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Collection<DetOrdenesDepositoT> getDetordenesdepositoCollection() {
        return detordenesdepositoCollection;
    }

    public void setDetordenesdepositoCollection(Collection<DetOrdenesDepositoT> detordenesdepositoCollection) {
        this.detordenesdepositoCollection = detordenesdepositoCollection;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Integer getIdOrdenDeposito() {
        return idOrdenDeposito;
    }

    public void setIdOrdenDeposito(Integer idOrdenDeposito) {
        this.idOrdenDeposito = idOrdenDeposito;
    }

    public TiposComprobantesT getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(TiposComprobantesT idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNroOrdenDep() {
        return nroOrdenDep;
    }

    public void setNroOrdenDep(int nroOrdenDep) {
        this.nroOrdenDep = nroOrdenDep;
    }
}
