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
public class EstadosT implements Serializable {

    private Integer idEstado;
    private String descripcion;
    private String notas;
    private Collection<OrdenesProduccionT> ordenesproduccionCollection;
    private Collection<ArticulosT> articulosCollection;
    private Collection<RemitosT> remitosCollection;
    private Collection<FacturasComprasT> facturascomprasCollection;
    private Collection<DomiciliosT> domiciliosCollection;
    private DominiosT idDominio;
    private Collection<RemitosIngresoT> remitosingresoCollection;
    private Collection<AjustesStockT> ajustesstockCollection;
    private Collection<NotasCreditoT> notascreditoCollection;
    private Collection<ListasPreciosT> listaspreciosCollection;
    private Collection<FacturasT> facturasCollection;
    private Collection<NotasDebitoT> notasdebitoCollection;
    private Collection<TiposIvaT> tiposivaCollection;
    private Collection<SellosT> sellosCollection;
    private Collection<MaquinasT> maquinasCollection;
    private Collection<ClientesT> clientesCollection;
    private Collection<OrdenesDepositoT> ordenesdepositoCollection;
    private Collection<DetalleProduccionT> detalleproduccionCollection;
    private Collection<ProveedoresT> proveedoresCollection;
    private Collection<UsuariosT> usuariosCollection;

    public EstadosT() {
    }

    public EstadosT(Integer idEstado, String descripcion, String notas) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
        this.notas = notas;
    }

    public Collection<ArticulosT> getArticulosCollection() {
        return articulosCollection;
    }

    public Collection<AjustesStockT> getAjustesstockCollection() {
        return ajustesstockCollection;
    }

    public Collection<DetalleProduccionT> getDetalleproduccionCollection() {
        return detalleproduccionCollection;
    }

    public void setDetalleproduccionCollection(Collection<DetalleProduccionT> detalleproduccionCollection) {
        this.detalleproduccionCollection = detalleproduccionCollection;
    }

    public DominiosT getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(DominiosT idDominio) {
        this.idDominio = idDominio;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Collection<SellosT> getSellosCollection() {
        return sellosCollection;
    }

    public void setSellosCollection(Collection<SellosT> sellosCollection) {
        this.sellosCollection = sellosCollection;
    }

    public Collection<TiposIvaT> getTiposivaCollection() {
        return tiposivaCollection;
    }

    public void setTiposivaCollection(Collection<TiposIvaT> tiposivaCollection) {
        this.tiposivaCollection = tiposivaCollection;
    }

    public void setAjustesstockCollection(Collection<AjustesStockT> ajustesstockCollection) {
        this.ajustesstockCollection = ajustesstockCollection;
    }

    public void setArticulosCollection(Collection<ArticulosT> articulosCollection) {
        this.articulosCollection = articulosCollection;
    }

    public Collection<ClientesT> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<ClientesT> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<DomiciliosT> getDomiciliosCollection() {
        return domiciliosCollection;
    }

    public void setDomiciliosCollection(Collection<DomiciliosT> domiciliosCollection) {
        this.domiciliosCollection = domiciliosCollection;
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

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Collection<ListasPreciosT> getListaspreciosCollection() {
        return listaspreciosCollection;
    }

    public void setListaspreciosCollection(Collection<ListasPreciosT> listaspreciosCollection) {
        this.listaspreciosCollection = listaspreciosCollection;
    }

    public Collection<MaquinasT> getMaquinasCollection() {
        return maquinasCollection;
    }

    public void setMaquinasCollection(Collection<MaquinasT> maquinasCollection) {
        this.maquinasCollection = maquinasCollection;
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

    public Collection<ProveedoresT> getProveedoresCollection() {
        return proveedoresCollection;
    }

    public void setProveedoresCollection(Collection<ProveedoresT> proveedoresCollection) {
        this.proveedoresCollection = proveedoresCollection;
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

    public Collection<UsuariosT> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<UsuariosT> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public String toString() {
        //TODO: modificar para que se use el Render en lugar del toString(futuro)
        return descripcion;
    }
}
