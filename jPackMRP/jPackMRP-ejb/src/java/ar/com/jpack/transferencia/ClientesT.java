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
public class ClientesT implements Serializable, Comparable<ClientesT> {

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 9999L;
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
        String oldApellidos = this.apellidos;
        this.apellidos = apellidos;
        changeSupport.firePropertyChange("apellidos", oldApellidos, apellidos);
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        String oldCuit = this.cuit;
        this.cuit = cuit;
        changeSupport.firePropertyChange("cuit", oldCuit, cuit);
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
        Date oldFechaAlta = this.fechaAlta;
        this.fechaAlta = fechaAlta;
        changeSupport.firePropertyChange("fechaAlta", oldFechaAlta, fechaAlta);
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        Integer oldIdCliente = this.idCliente;
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

    public TiposDocumentoT getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TiposDocumentoT idTipoDocumento) {
        TiposDocumentoT oldIdTipoDocumento = this.idTipoDocumento;
        this.idTipoDocumento = idTipoDocumento;
        changeSupport.firePropertyChange("idTipoDocumento", oldIdTipoDocumento, idTipoDocumento);
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(int limiteCredito) {
        int oldLimiteCredito = this.limiteCredito;
        this.limiteCredito = limiteCredito;
        changeSupport.firePropertyChange("limiteCredito", oldLimiteCredito, limiteCredito);
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        String oldMails = this.mails;
        this.mails = mails;
        changeSupport.firePropertyChange("mails", oldMails, mails);
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        String oldNombres = this.nombres;
        this.nombres = nombres;
        changeSupport.firePropertyChange("nombres", oldNombres, nombres);
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
        String oldObservaciones = this.observaciones;
        this.observaciones = observaciones;
        changeSupport.firePropertyChange("observaciones", oldObservaciones, observaciones);
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
        String oldSituacionIva = this.observaciones;
        this.situacionIva = situacionIva;
        changeSupport.firePropertyChange("situacionIva", oldSituacionIva, situacionIva);
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        String oldTelefonos = this.telefonos;
        this.telefonos = telefonos;
        changeSupport.firePropertyChange("telefonos", oldTelefonos, telefonos);
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

    public int compareTo(ClientesT clientesT) {
        Integer thisIdCliente = this.idCliente;
        Integer anotherIdCliente = clientesT.getIdCliente();
        return thisIdCliente.compareTo(anotherIdCliente);
    }
}
