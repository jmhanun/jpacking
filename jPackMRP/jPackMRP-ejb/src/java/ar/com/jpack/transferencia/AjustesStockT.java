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
public class AjustesStockT implements Serializable {
    private Integer idAjusteStock;
    private int nroAjuste;
    private Date fecha;
    private Date fechaModificacion;
    private String signo;
    private Collection<DetMovimientosStockT> detmovimientosstockCollection;
    private EstadosT idEstado;
    private TiposComprobantesT idTipoComprobante;
    private UsuariosT idUsuario;
    private Collection<DetAjustesStockT> detajustesstockCollection;

    public AjustesStockT() {
    }

    public AjustesStockT(Integer idAjusteStock, int nroAjuste, Date fecha, Date fechaModificacion, String signo, EstadosT idEstado, TiposComprobantesT idTipoComprobante, UsuariosT idUsuario) {
        this.idAjusteStock = idAjusteStock;
        this.nroAjuste = nroAjuste;
        this.fecha = fecha;
        this.fechaModificacion = fechaModificacion;
        this.signo = signo;
        this.idEstado = idEstado;
        this.idTipoComprobante = idTipoComprobante;
        this.idUsuario = idUsuario;
    }

    public Collection<DetAjustesStockT> getDetajustesstockCollection() {
        return detajustesstockCollection;
    }

    public void setDetajustesstockCollection(Collection<DetAjustesStockT> detajustesstockCollection) {
        this.detajustesstockCollection = detajustesstockCollection;
    }

    public Collection<DetMovimientosStockT> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<DetMovimientosStockT> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
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

    public Integer getIdAjusteStock() {
        return idAjusteStock;
    }

    public void setIdAjusteStock(Integer idAjusteStock) {
        this.idAjusteStock = idAjusteStock;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
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

    public int getNroAjuste() {
        return nroAjuste;
    }

    public void setNroAjuste(int nroAjuste) {
        this.nroAjuste = nroAjuste;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

}
