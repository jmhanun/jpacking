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
 public class ArticulosT implements Serializable{
    private Integer idArticulo;
    private String codigo;
    private String descripcion;
    private float stock;
    private float stockMinimo;
    private float leadTime;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Collection<DetNotasCreditoT> detnotascreditoCollection;
    private EstadosT idEstado;
    private UnidadesMedidaT idUnidMedida;
    private UsuariosT idUsuario;
    private Collection<DetOrdenesProduccionT> detordenesproduccionCollection;
    private Collection<DetMovimientosStockT> detmovimientosstockCollection;
    private Collection<DetalleFactComprasT> detallefactcomprasCollection;
    private Collection<DetNotasDebitoT> detnotasdebitoCollection;
    private Collection<StockT> stockCollection;
    private Collection<DetalleFacturasT> detallefacturasCollection;
    private Collection<ComponentesT> articulosCollection;
    private Collection<ComponentesT> componentesCollection;
    private Collection<DetalleRemitosT> detalleremitosCollection;
    private Collection<DetOrdenesDepositoT> detordenesdepositoCollection;
    private Collection<ProduccionT> produccionCollection;
    private Collection<DetRtosIngresoT> detrtosingresoCollection;
    private Collection<PreciosT> preciosCollection;

    public ArticulosT() {
    }

    public ArticulosT(Integer idArticulo, String codigo, 
            String descripcion, 
            float stock, 
            float stockMinimo, 
            float leadTime, 
            Date fechaAlta,
            Date fechaModificacion,
            EstadosT idEstado, UnidadesMedidaT idUnidMedida,
            UsuariosT idUsuario) {
        this.idArticulo = idArticulo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.leadTime = leadTime;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.idEstado = idEstado;
        this.idUnidMedida = idUnidMedida;
        this.idUsuario = idUsuario;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
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

    public Collection<ComponentesT> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<ComponentesT> articulosCollection) {
        this.articulosCollection = articulosCollection;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Collection<ComponentesT> getComponentesCollection() {
        return componentesCollection;
    }

    public void setComponentesCollection(Collection<ComponentesT> componentesCollection) {
        this.componentesCollection = componentesCollection;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<DetalleFactComprasT> getDetallefactcomprasCollection() {
        return detallefactcomprasCollection;
    }

    public void setDetallefactcomprasCollection(Collection<DetalleFactComprasT> detallefactcomprasCollection) {
        this.detallefactcomprasCollection = detallefactcomprasCollection;
    }

    public Collection<DetalleFacturasT> getDetallefacturasCollection() {
        return detallefacturasCollection;
    }

    public void setDetallefacturasCollection(Collection<DetalleFacturasT> detallefacturasCollection) {
        this.detallefacturasCollection = detallefacturasCollection;
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

    public Collection<DetNotasCreditoT> getDetnotascreditoCollection() {
        return detnotascreditoCollection;
    }

    public void setDetnotascreditoCollection(Collection<DetNotasCreditoT> detnotascreditoCollection) {
        this.detnotascreditoCollection = detnotascreditoCollection;
    }

    public Collection<DetNotasDebitoT> getDetnotasdebitoCollection() {
        return detnotasdebitoCollection;
    }

    public void setDetnotasdebitoCollection(Collection<DetNotasDebitoT> detnotasdebitoCollection) {
        this.detnotasdebitoCollection = detnotasdebitoCollection;
    }

    public Collection<DetOrdenesDepositoT> getDetordenesdepositoCollection() {
        return detordenesdepositoCollection;
    }

    public void setDetordenesdepositoCollection(Collection<DetOrdenesDepositoT> detordenesdepositoCollection) {
        this.detordenesdepositoCollection = detordenesdepositoCollection;
    }

    public Collection<DetOrdenesProduccionT> getDetordenesproduccionCollection() {
        return detordenesproduccionCollection;
    }

    public void setDetordenesproduccionCollection(Collection<DetOrdenesProduccionT> detordenesproduccionCollection) {
        this.detordenesproduccionCollection = detordenesproduccionCollection;
    }

    public Collection<DetRtosIngresoT> getDetrtosingresoCollection() {
        return detrtosingresoCollection;
    }

    public void setDetrtosingresoCollection(Collection<DetRtosIngresoT> detrtosingresoCollection) {
        this.detrtosingresoCollection = detrtosingresoCollection;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public UnidadesMedidaT getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(UnidadesMedidaT idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    public float getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(float leadTime) {
        this.leadTime = leadTime;
    }

    public Collection<PreciosT> getPreciosCollection() {
        return preciosCollection;
    }

    public void setPreciosCollection(Collection<PreciosT> preciosCollection) {
        this.preciosCollection = preciosCollection;
    }

    public Collection<ProduccionT> getProduccionCollection() {
        return produccionCollection;
    }

    public void setProduccionCollection(Collection<ProduccionT> produccionCollection) {
        this.produccionCollection = produccionCollection;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public Collection<StockT> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<StockT> stockCollection) {
        this.stockCollection = stockCollection;
    }

    public float getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(float stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
