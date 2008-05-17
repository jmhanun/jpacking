/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "clientes")
@NamedQueries({
@NamedQuery(name = "Clientes.findByIdCliente", query = "SELECT c FROM Clientes c WHERE c.idCliente = :idCliente"),
@NamedQuery(name = "Clientes.findBySituacionIva", query = "SELECT c FROM Clientes c WHERE c.situacionIva = :situacionIva"),
@NamedQuery(name = "Clientes.findByLimiteCredito", query = "SELECT c FROM Clientes c WHERE c.limiteCredito = :limiteCredito"),
@NamedQuery(name = "Clientes.findByObservaciones", query = "SELECT c FROM Clientes c WHERE c.observaciones = :observaciones"),
@NamedQuery(name = "Clientes.findByEstado", query = "SELECT c FROM Clientes c WHERE c.estado = :estado"),
@NamedQuery(name = "Clientes.findByNombres", query = "SELECT c FROM Clientes c WHERE c.nombres = :nombres"),
@NamedQuery(name = "Clientes.findByApellidos", query = "SELECT c FROM Clientes c WHERE c.apellidos = :apellidos"),
@NamedQuery(name = "Clientes.findByMails", query = "SELECT c FROM Clientes c WHERE c.mails = :mails"),
@NamedQuery(name = "Clientes.findByTelefonos", query = "SELECT c FROM Clientes c WHERE c.telefonos = :telefonos"),
@NamedQuery(name = "Clientes.findByFechaAlta", query = "SELECT c FROM Clientes c WHERE c.fechaAlta = :fechaAlta")
})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;
    @Column(name = "situacionIva", nullable = false)
    private String situacionIva;
    @Column(name = "limiteCredito", nullable = false)
    private int limiteCredito;
    @Column(name = "observaciones", nullable = false)
    private String observaciones;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "mails", nullable = false)
    private String mails;
    @Column(name = "telefonos", nullable = false)
    private String telefonos;
    @Column(name = "fechaAlta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Remitos> remitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Domicilios> domiciliosCollection;
    @JoinColumn(name = "idTipoDocumento", referencedColumnName = "idTipoDocumento")
    @ManyToOne
    private TiposDocumento idTipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Facturas> facturasCollection;

    public Clientes() {
    }

    public Clientes(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Clientes(Integer idCliente, String situacionIva, int limiteCredito, String observaciones, String estado, String nombres, String apellidos, String mails, String telefonos, Date fechaAlta) {
        this.idCliente = idCliente;
        this.situacionIva = situacionIva;
        this.limiteCredito = limiteCredito;
        this.observaciones = observaciones;
        this.estado = estado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.mails = mails;
        this.telefonos = telefonos;
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getSituacionIva() {
        return situacionIva;
    }

    public void setSituacionIva(String situacionIva) {
        this.situacionIva = situacionIva;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(int limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Collection<Remitos> getRemitosCollection() {
        return remitosCollection;
    }

    public void setRemitosCollection(Collection<Remitos> remitosCollection) {
        this.remitosCollection = remitosCollection;
    }

    public Collection<Domicilios> getDomiciliosCollection() {
        return domiciliosCollection;
    }

    public void setDomiciliosCollection(Collection<Domicilios> domiciliosCollection) {
        this.domiciliosCollection = domiciliosCollection;
    }

    public TiposDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TiposDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Clientes[idCliente=" + idCliente + "]";
    }
}
