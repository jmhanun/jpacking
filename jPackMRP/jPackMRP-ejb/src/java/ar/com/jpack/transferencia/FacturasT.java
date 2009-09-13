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
public class FacturasT implements Serializable {

    private Integer idFactura;
    private int nroFactura;
    private Date fecha;
    private String letra;
    private double importe;
    private Float descuento;
    private Date fechaModificacion;
    private Integer idRemito;
    private Collection<RemitosT> idRemitoCollection;
    private Collection<DetalleFacturasT> detallefacturasCollection;
    private ClientesT idCliente;
    private EstadosT idEstado;
    private TiposComprobantesT idTipoComprobante;
    private UsuariosT idUsuario;

    public FacturasT() {
    }

    public FacturasT(Integer idFactura, int nroFactura, Date fecha, String letra, double importe, Float descuento, Date fechaModificacion, ClientesT idCliente, EstadosT idEstado, TiposComprobantesT idTipoComprobante, UsuariosT idUsuario) {
        this.idFactura = idFactura;
        this.nroFactura = nroFactura;
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

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Collection<DetalleFacturasT> getDetallefacturasCollection() {
        return detallefacturasCollection;
    }

    public void setDetallefacturasCollection(Collection<DetalleFacturasT> detallefacturasCollection) {
        this.detallefacturasCollection = detallefacturasCollection;
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

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Collection<RemitosT> getIdRemitoCollection() {
        return idRemitoCollection;
    }

    public void setIdRemitoCollection(Collection<RemitosT> idRemitoCollection) {
        this.idRemitoCollection = idRemitoCollection;
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

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }
}
