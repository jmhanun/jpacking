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
public class FacturasComprasT implements Serializable{

    private Integer idFactCompra;
    private int nroFactura;
    private Date fecha;
    private String letra;
    private double importe;
    private float descuento;
    private Date fechaModificacion;
    private EstadosT idEstado;
    private ProveedoresT idProveedor;
    private TiposComprobantesT idTipoComprobante;
    private UsuariosT idUsuario;
    private Collection<DetalleFactComprasT> detallefactcomprasCollection;

    public FacturasComprasT() {
    }

    public FacturasComprasT(Integer idFactCompra, int nroFactura, Date fecha, String letra, double importe, float descuento, Date fechaModificacion, EstadosT idEstado, ProveedoresT idProveedor, TiposComprobantesT idTipoComprobante, UsuariosT idUsuario) {
        this.idFactCompra = idFactCompra;
        this.nroFactura = nroFactura;
        this.fecha = fecha;
        this.letra = letra;
        this.importe = importe;
        this.descuento = descuento;
        this.fechaModificacion = fechaModificacion;
        this.idEstado = idEstado;
        this.idProveedor = idProveedor;
        this.idTipoComprobante = idTipoComprobante;
        this.idUsuario = idUsuario;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Collection<DetalleFactComprasT> getDetallefactcomprasCollection() {
        return detallefactcomprasCollection;
    }

    public void setDetallefactcomprasCollection(Collection<DetalleFactComprasT> detallefactcomprasCollection) {
        this.detallefactcomprasCollection = detallefactcomprasCollection;
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

    public Integer getIdFactCompra() {
        return idFactCompra;
    }

    public void setIdFactCompra(Integer idFactCompra) {
        this.idFactCompra = idFactCompra;
    }

    public ProveedoresT getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(ProveedoresT idProveedor) {
        this.idProveedor = idProveedor;
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

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

}
