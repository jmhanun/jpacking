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
public class OrdenesProduccionT implements Serializable {

    private Integer idOrdenProduccion;
    private int nroOrdenProduccion;
    private Date fecha;
    private Date fechaModificacion;
    private Date fechaInicioEstimada;
    private EstadosT idEstado;
    private PrioridadesT idPrioridad;
    private RemitosT idRemito;
    private TiposComprobantesT idTipoComprobante;
    private UsuariosT idUsuario;
    private Collection<DetOrdenesProduccionT> detordenesproduccionCollection;
    private Collection<DetMovimientosStockT> detmovimientosstockCollection;

    public OrdenesProduccionT() {
    }

    public OrdenesProduccionT(Integer idOrdenProduccion, int nroOrdenProduccion, Date fecha, Date fechaModificacion, Date fechaInicioEstimada, EstadosT idEstado, PrioridadesT idPrioridad, RemitosT idRemito, TiposComprobantesT idTipoComprobante, UsuariosT idUsuario) {
        this.idOrdenProduccion = idOrdenProduccion;
        this.nroOrdenProduccion = nroOrdenProduccion;
        this.fecha = fecha;
        this.fechaModificacion = fechaModificacion;
        this.fechaInicioEstimada = fechaInicioEstimada;
        this.idEstado = idEstado;
        this.idPrioridad = idPrioridad;
        this.idRemito = idRemito;
        this.idTipoComprobante = idTipoComprobante;
        this.idUsuario = idUsuario;
    }

    public Collection<DetMovimientosStockT> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<DetMovimientosStockT> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Collection<DetOrdenesProduccionT> getDetordenesproduccionCollection() {
        return detordenesproduccionCollection;
    }

    public void setDetordenesproduccionCollection(Collection<DetOrdenesProduccionT> detordenesproduccionCollection) {
        this.detordenesproduccionCollection = detordenesproduccionCollection;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaInicioEstimada() {
        return fechaInicioEstimada;
    }

    public void setFechaInicioEstimada(Date fechaInicioEstimada) {
        this.fechaInicioEstimada = fechaInicioEstimada;
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

    public Integer getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public PrioridadesT getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(PrioridadesT idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public RemitosT getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(RemitosT idRemito) {
        this.idRemito = idRemito;
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

    public int getNroOrdenProduccion() {
        return nroOrdenProduccion;
    }

    public void setNroOrdenProduccion(int nroOrdenProduccion) {
        this.nroOrdenProduccion = nroOrdenProduccion;
    }
}