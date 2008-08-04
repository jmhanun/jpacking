/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class RemitosT implements Serializable {

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
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
            Date fechaModificacion, ClientesT idCliente, EstadosT idEstado,
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
        Date oldFechaModificacion = this.fechaModificacion;
        this.fechaModificacion = fechaModificacion;
        changeSupport.firePropertyChange("fechaModificacion", oldFechaModificacion, fechaModificacion);
    }

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        UsuariosT oldIdUsuario = this.idUsuario;
        this.idUsuario = idUsuario;
        changeSupport.firePropertyChange("idUsuario", oldIdUsuario, idUsuario);
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
        Date oldFecha = this.fecha;
        this.fecha = fecha;
        changeSupport.firePropertyChange("fecha", oldFecha, fecha);
    }

    public Date getFechaAcordada() {
        return fechaAcordada;
    }

    public void setFechaAcordada(Date fechaAcordada) {
        Date oldFechaAcordada = this.fechaAcordada;
        this.fechaAcordada = fechaAcordada;
        changeSupport.firePropertyChange("fechaAcordada", oldFechaAcordada, fechaAcordada);
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        Date oldFechaEntrega = this.fechaEntrega;
        this.fechaEntrega = fechaEntrega;
        changeSupport.firePropertyChange("fechaEntrega", oldFechaEntrega, fechaEntrega);
    }

    public ClientesT getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ClientesT idCliente) {
        ClientesT oldIdCliente = this.idCliente;
        this.idCliente = idCliente;
        changeSupport.firePropertyChange("idCliente", oldIdCliente, idCliente);
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        EstadosT oldIdEstado = this.idEstado;
        this.idEstado = idEstado;
        changeSupport.firePropertyChange("idEstado", oldIdEstado, idEstado);
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
        Integer oldIdRemito = this.idRemito;
        this.idRemito = idRemito;
        changeSupport.firePropertyChange("idRemito", oldIdRemito, idRemito);
    }

    public TiposComprobantesT getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(TiposComprobantesT idTipoComprobante) {
        TiposComprobantesT oldIdTipoComprobante = this.idTipoComprobante;
        this.idTipoComprobante = idTipoComprobante;
        changeSupport.firePropertyChange("idTipoComprobante", oldIdTipoComprobante, idTipoComprobante);
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        double oldImporte = this.importe;
        this.importe = importe;
        changeSupport.firePropertyChange("importe", oldImporte, importe);
    }

    public int getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(int nroRemito) {
        int oldNroRemito = this.nroRemito;
        this.nroRemito = nroRemito;
        changeSupport.firePropertyChange("nroRemito", oldNroRemito, nroRemito);
    }

    public Collection<OrdenesProduccionT> getOrdenesproduccionCollection() {
        return ordenesproduccionCollection;
    }

    public void setOrdenesproduccionCollection(Collection<OrdenesProduccionT> ordenesproduccionCollection) {
        this.ordenesproduccionCollection = ordenesproduccionCollection;
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
