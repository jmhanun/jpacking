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
public class ProveedoresT implements Serializable {

    private Integer idProveedor;
    private String nombres;
    private String apellido;
    private String mails;
    private String telefonos;
    private Date fechaAlta;
    private String observaciones;
    private String cuit;
    private Collection<OrdenesCompraT> ordenescompraCollection;
    private Collection<FacturasComprasT> facturascomprasCollection;
    private Collection<DomiciliosT> domiciliosCollection;
    private Collection<RemitosIngresoT> remitosingresoCollection;
    private Collection<NotasCreditoT> notascreditoCollection;
    private EstadosT idEstado;
    private TiposDocumentoT idTipoDocumento;

    public ProveedoresT() {
    }

    public ProveedoresT(Integer idProveedor, String nombres, String apellido, String mails, String telefonos, Date fechaAlta, String observaciones, String cuit, EstadosT idEstado, TiposDocumentoT idTipoDocumento) {
        this.idProveedor = idProveedor;
        this.nombres = nombres;
        this.apellido = apellido;
        this.mails = mails;
        this.telefonos = telefonos;
        this.fechaAlta = fechaAlta;
        this.observaciones = observaciones;
        this.cuit = cuit;
        this.idEstado = idEstado;
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Collection<DomiciliosT> getDomiciliosCollection() {
        return domiciliosCollection;
    }

    public void setDomiciliosCollection(Collection<DomiciliosT> domiciliosCollection) {
        this.domiciliosCollection = domiciliosCollection;
    }

    public Collection<FacturasComprasT> getFacturascomprasCollection() {
        return facturascomprasCollection;
    }

    public void setFacturascomprasCollection(Collection<FacturasComprasT> facturascomprasCollection) {
        this.facturascomprasCollection = facturascomprasCollection;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public TiposDocumentoT getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TiposDocumentoT idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Collection<NotasCreditoT> getNotascreditoCollection() {
        return notascreditoCollection;
    }

    public void setNotascreditoCollection(Collection<NotasCreditoT> notascreditoCollection) {
        this.notascreditoCollection = notascreditoCollection;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Collection<OrdenesCompraT> getOrdenescompraCollection() {
        return ordenescompraCollection;
    }

    public void setOrdenescompraCollection(Collection<OrdenesCompraT> ordenescompraCollection) {
        this.ordenescompraCollection = ordenescompraCollection;
    }

    public Collection<RemitosIngresoT> getRemitosingresoCollection() {
        return remitosingresoCollection;
    }

    public void setRemitosingresoCollection(Collection<RemitosIngresoT> remitosingresoCollection) {
        this.remitosingresoCollection = remitosingresoCollection;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    @Override
    public String toString() {
        return this.getNombres();
    }
}
