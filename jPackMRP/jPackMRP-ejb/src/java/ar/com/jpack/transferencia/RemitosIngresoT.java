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
public class RemitosIngresoT implements Serializable {

    private Integer idRtoIngreso;
    private int nroRemito;
    private Date fecha;
    private String letra;
    private double importe;
    private float descuento;
    private Date fechaModificacion;
    private Collection<DetMovimientosStockT> detmovimientosstockCollection;
    private EstadosT idEstado;
    private ProveedoresT idProveedor;
    private TiposComprobantesT idTipoComprobante;
    private UsuariosT idUsuario;
    private Collection<DetRtosIngresoT> detrtosingresoCollection;

    public RemitosIngresoT() {
    }

    public RemitosIngresoT(Integer idRtoIngreso, int nroRemito, Date fecha, String letra, double importe, float descuento, Date fechaModificacion, EstadosT idEstado, ProveedoresT idProveedor, TiposComprobantesT idTipoComprobante, UsuariosT idUsuario) {
        this.idRtoIngreso = idRtoIngreso;
        this.nroRemito = nroRemito;
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

    public Collection<DetMovimientosStockT> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<DetMovimientosStockT> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Collection<DetRtosIngresoT> getDetrtosingresoCollection() {
        return detrtosingresoCollection;
    }

    public void setDetrtosingresoCollection(Collection<DetRtosIngresoT> detrtosingresoCollection) {
        this.detrtosingresoCollection = detrtosingresoCollection;
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

    public ProveedoresT getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(ProveedoresT idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdRtoIngreso() {
        return idRtoIngreso;
    }

    public void setIdRtoIngreso(Integer idRtoIngreso) {
        this.idRtoIngreso = idRtoIngreso;
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

    public int getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(int nroRemito) {
        this.nroRemito = nroRemito;
    }
}
