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
public class NotasDebitoT implements Serializable {

    private Integer idNotaDebito;
    private int nroNotaDebito;
    private Date fecha;
    private String letra;
    private double importe;
    private float descuento;
    private Date fechaModificacion;
    private Collection<DetMovimientosStockT> detmovimientosstockCollection;
    private Collection<DetNotasDebitoT> detnotasdebitoCollection;
    private ClientesT idCliente;
    private EstadosT idEstado;
    private TiposComprobantesT idTipoComprobante;
    private UsuariosT idUsuario;

    public NotasDebitoT() {
    }

    public NotasDebitoT(Integer idNotaDebito, int nroNotaDebito, Date fecha, String letra, double importe, float descuento, Date fechaModificacion, ClientesT idCliente, EstadosT idEstado, TiposComprobantesT idTipoComprobante, UsuariosT idUsuario) {
        this.idNotaDebito = idNotaDebito;
        this.nroNotaDebito = nroNotaDebito;
        this.fecha = fecha;
        this.letra = letra;
        this.importe = importe;
        this.descuento = descuento;
        this.fechaModificacion = fechaModificacion;
        this.idCliente = idCliente;
        this.idEstado = idEstado;
        this.idTipoComprobante = idTipoComprobante;
        this.idUsuario = idUsuario;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Collection<DetMovimientosStockT> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<DetMovimientosStockT> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Collection<DetNotasDebitoT> getDetnotasdebitoCollection() {
        return detnotasdebitoCollection;
    }

    public void setDetnotasdebitoCollection(Collection<DetNotasDebitoT> detnotasdebitoCollection) {
        this.detnotasdebitoCollection = detnotasdebitoCollection;
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

    public ClientesT getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ClientesT idCliente) {
        this.idCliente = idCliente;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdNotaDebito() {
        return idNotaDebito;
    }

    public void setIdNotaDebito(Integer idNotaDebito) {
        this.idNotaDebito = idNotaDebito;
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

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNroNotaDebito() {
        return nroNotaDebito;
    }

    public void setNroNotaDebito(int nroNotaDebito) {
        this.nroNotaDebito = nroNotaDebito;
    }
}
