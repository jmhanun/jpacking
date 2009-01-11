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
public class ArticulosT implements Serializable {

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
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
    private Collection<DetAjustesStockT> detajustesstockCollection;
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
        Date oldFechaAlta = this.fechaAlta;
        this.fechaAlta = fechaAlta;
        changeSupport.firePropertyChange("fechaAlta", oldFechaAlta, fechaAlta);
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
        String oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
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
        String oldDescripcion = this.descripcion;
        this.descripcion = descripcion;
        changeSupport.firePropertyChange("descripcion", oldDescripcion, descripcion);
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

    public Collection<DetAjustesStockT> getDetAjustesStockCollection() {
        return detajustesstockCollection;
    }

    public void setDetAjustesStockCollection(Collection<DetAjustesStockT> detajustesstockCollection) {
        this.detajustesstockCollection = detajustesstockCollection;
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
        Integer oldIdArticulo = this.idArticulo;
        this.idArticulo = idArticulo;
        changeSupport.firePropertyChange("idArticulo", oldIdArticulo, idArticulo);
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        EstadosT oldIdEstado = this.idEstado;
        this.idEstado = idEstado;
        changeSupport.firePropertyChange("idEstado", oldIdEstado, idEstado);
    }

    public UnidadesMedidaT getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(UnidadesMedidaT idUnidMedida) {
        UnidadesMedidaT oldIdUnidMedida = this.idUnidMedida;
        this.idUnidMedida = idUnidMedida;
        changeSupport.firePropertyChange("idUnidMedida", oldIdUnidMedida, idUnidMedida);
    }

    public float getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(float leadTime) {
        float oldLeadTime = this.leadTime;
        this.leadTime = leadTime;
        changeSupport.firePropertyChange("leadTime", oldLeadTime, leadTime);
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
        float oldStock = this.stock;
        this.stock = stock;
        changeSupport.firePropertyChange("stock", oldStock, stock);
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
        float oldStockMinimo = this.stockMinimo;
        this.stockMinimo = stockMinimo;
        changeSupport.firePropertyChange("stockMinimo", oldStockMinimo, stockMinimo);
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
