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
public class TiposComprobantesT implements Serializable {

    private Integer idTipoComprobante;
    private String descripcion;
    private String abreviatura;
    private String signoStock;
    private Collection<OrdenesProduccionT> ordenesproduccionCollection;
    private Collection<RemitosT> remitosCollection;
    private Collection<FacturasComprasT> facturascomprasCollection;
    private Collection<RemitosIngresoT> remitosingresoCollection;
    private Collection<AjustesStockT> ajustesstockCollection;
    private Collection<NotasCreditoT> notascreditoCollection;
    private Collection<FacturasT> facturasCollection;
    private Collection<NotasDebitoT> notasdebitoCollection;
    private Collection<OrdenesDepositoT> ordenesdepositoCollection;

    public TiposComprobantesT() {
    }

    public TiposComprobantesT(Integer idTipoComprobante, String descripcion, String abreviatura, String signoStock) {
        this.idTipoComprobante = idTipoComprobante;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.signoStock = signoStock;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Collection<AjustesStockT> getAjustesstockCollection() {
        return ajustesstockCollection;
    }

    public void setAjustesstockCollection(Collection<AjustesStockT> ajustesstockCollection) {
        this.ajustesstockCollection = ajustesstockCollection;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<FacturasT> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<FacturasT> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    public Collection<FacturasComprasT> getFacturascomprasCollection() {
        return facturascomprasCollection;
    }

    public void setFacturascomprasCollection(Collection<FacturasComprasT> facturascomprasCollection) {
        this.facturascomprasCollection = facturascomprasCollection;
    }

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Collection<NotasCreditoT> getNotascreditoCollection() {
        return notascreditoCollection;
    }

    public void setNotascreditoCollection(Collection<NotasCreditoT> notascreditoCollection) {
        this.notascreditoCollection = notascreditoCollection;
    }

    public Collection<NotasDebitoT> getNotasdebitoCollection() {
        return notasdebitoCollection;
    }

    public void setNotasdebitoCollection(Collection<NotasDebitoT> notasdebitoCollection) {
        this.notasdebitoCollection = notasdebitoCollection;
    }

    public Collection<OrdenesDepositoT> getOrdenesdepositoCollection() {
        return ordenesdepositoCollection;
    }

    public void setOrdenesdepositoCollection(Collection<OrdenesDepositoT> ordenesdepositoCollection) {
        this.ordenesdepositoCollection = ordenesdepositoCollection;
    }

    public Collection<OrdenesProduccionT> getOrdenesproduccionCollection() {
        return ordenesproduccionCollection;
    }

    public void setOrdenesproduccionCollection(Collection<OrdenesProduccionT> ordenesproduccionCollection) {
        this.ordenesproduccionCollection = ordenesproduccionCollection;
    }

    public Collection<RemitosT> getRemitosCollection() {
        return remitosCollection;
    }

    public void setRemitosCollection(Collection<RemitosT> remitosCollection) {
        this.remitosCollection = remitosCollection;
    }

    public Collection<RemitosIngresoT> getRemitosingresoCollection() {
        return remitosingresoCollection;
    }

    public void setRemitosingresoCollection(Collection<RemitosIngresoT> remitosingresoCollection) {
        this.remitosingresoCollection = remitosingresoCollection;
    }

    public String getSignoStock() {
        return signoStock;
    }

    public void setSignoStock(String signoStock) {
        this.signoStock = signoStock;
    }
}
