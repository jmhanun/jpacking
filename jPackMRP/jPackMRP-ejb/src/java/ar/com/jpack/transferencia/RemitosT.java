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
public class RemitosT implements Serializable{
    private Integer idRemito;
    private int nroRemito;
    private Date fecha;
    private double importe;
    private Date fechaAcordada;
    private Date fechaEntrega;
    private Date fechaModificacion;
    private Collection<FacturasT> idFacturaCollection;
    private Collection<OrdenesProduccionT> ordenesproduccionCollection;
    private ClientesT idCliente;
    private EstadosT idEstado;
    private TiposComprobantesT idTipoComprobante;
    private UsuariosT idUsuario;
    private Collection<DetMovimientosStockT> detmovimientosstockCollection;
    private Collection<DetalleRemitosT> detalleremitosCollection;

    public RemitosT() {
    }

    public RemitosT(Integer idRemito, int nroRemito, Date fecha, 
            double importe, Date fechaAcordada, Date fechaEntrega, 
            Date fechaModificacion,ClientesT idCliente, EstadosT idEstado,
            TiposComprobantesT idTipoComprobante,
            UsuariosT idUsuario) {
        this.idRemito = idRemito;
        this.nroRemito = nroRemito;
        this.fecha = fecha;
        this.importe = importe;
        this.fechaAcordada = fechaAcordada;
        this.fechaEntrega = fechaEntrega;
        this.fechaModificacion = fechaModificacion;
        this.idCliente = idCliente;
        this.idEstado = idEstado;
        this.idTipoComprobante = idTipoComprobante;
        this.idUsuario = idUsuario;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Collection<DetalleRemitosT> getDetalleremitosCollection() {
        return detalleremitosCollection;
    }

    public void setDetalleremitosCollection(Collection<DetalleRemitosT> detalleremitosCollection) {
        this.detalleremitosCollection = detalleremitosCollection;
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

    public Date getFechaAcordada() {
        return fechaAcordada;
    }

    public void setFechaAcordada(Date fechaAcordada) {
        this.fechaAcordada = fechaAcordada;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public Collection<FacturasT> getIdFacturaCollection() {
        return idFacturaCollection;
    }

    public void setIdFacturaCollection(Collection<FacturasT> idFacturaCollection) {
        this.idFacturaCollection = idFacturaCollection;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public TiposComprobantesT getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(TiposComprobantesT idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(int nroRemito) {
        this.nroRemito = nroRemito;
    }

    public Collection<OrdenesProduccionT> getOrdenesproduccionCollection() {
        return ordenesproduccionCollection;
    }

    public void setOrdenesproduccionCollection(Collection<OrdenesProduccionT> ordenesproduccionCollection) {
        this.ordenesproduccionCollection = ordenesproduccionCollection;
    }

}
