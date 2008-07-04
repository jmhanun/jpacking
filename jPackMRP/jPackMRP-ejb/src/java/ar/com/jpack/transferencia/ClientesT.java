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
public class ClientesT implements Serializable {

    private Integer idCliente;
    private String situacionIva;
    private int limiteCredito;
    private String observaciones;
    private String nombres;
    private String apellidos;
    private String mails;
    private String telefonos;
    private Date fechaAlta;
    private String cuit;
    private Collection<RemitosT> remitosCollection;
    private Collection<DomiciliosT> domiciliosCollection;
    private Collection<FacturasT> facturasCollection;
    private Collection<NotasDebitoT> notasdebitoCollection;
    private EstadosT idEstado;
    private TiposDocumentoT idTipoDocumento;

    public ClientesT() {
    }

    public ClientesT(Integer idCliente, String situacionIva, int limiteCredito, String observaciones, String nombres, String apellidos, String mails, String telefonos, Date fechaAlta, String cuit, EstadosT idEstado, TiposDocumentoT idTipoDocumento) {
        this.idCliente = idCliente;
        this.situacionIva = situacionIva;
        this.limiteCredito = limiteCredito;
        this.observaciones = observaciones;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.mails = mails;
        this.telefonos = telefonos;
        this.fechaAlta = fechaAlta;
        this.cuit = cuit;
        this.idEstado = idEstado;
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public Collection<FacturasT> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<FacturasT> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public TiposDocumentoT getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TiposDocumentoT idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(int limiteCredito) {
        this.limiteCredito = limiteCredito;
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

    public Collection<NotasDebitoT> getNotasdebitoCollection() {
        return notasdebitoCollection;
    }

    public void setNotasdebitoCollection(Collection<NotasDebitoT> notasdebitoCollection) {
        this.notasdebitoCollection = notasdebitoCollection;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Collection<RemitosT> getRemitosCollection() {
        return remitosCollection;
    }

    public void setRemitosCollection(Collection<RemitosT> remitosCollection) {
        this.remitosCollection = remitosCollection;
    }

    public String getSituacionIva() {
        return situacionIva;
    }

    public void setSituacionIva(String situacionIva) {
        this.situacionIva = situacionIva;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }
}
