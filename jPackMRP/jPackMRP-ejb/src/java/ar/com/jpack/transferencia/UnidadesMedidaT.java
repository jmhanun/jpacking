/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author jmhanun
 */
public class UnidadesMedidaT implements Serializable{
    private Integer idUnidMedida;
    private String descripcion;
    private String abreviatura;
    private Collection<DetNotasCreditoT> detnotascreditoCollection;
    private Collection<ArticulosT> articulosCollection;
    private Collection<DetOrdenesProduccionT> detordenesproduccionCollection;
    private Collection<DetMovimientosStockT> detmovimientosstockCollection;
    private Collection<DetalleFactComprasT> detallefactcomprasCollection;
    private Collection<DetNotasDebitoT> detnotasdebitoCollection;
    private Collection<DetalleFacturasT> detallefacturasCollection;
    private Collection<DetalleRemitosT> detalleremitosCollection;
    private Collection<DetOrdenesDepositoT> detordenesdepositoCollection;
    private Collection<DetRtosIngresoT> detrtosingresoCollection;

    public UnidadesMedidaT() {
    }

    public UnidadesMedidaT(Integer idUnidMedida, String descripcion, String abreviatura) {
        this.idUnidMedida = idUnidMedida;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Collection<ArticulosT> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<ArticulosT> articulosCollection) {
        this.articulosCollection = articulosCollection;
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

    public Integer getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(Integer idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }
    
    
}
