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
import javax.persistence.FetchType;
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
@Table(name = "proveedores")
@NamedQueries({@NamedQuery(name = "Proveedores.findByIdProveedor", query = "SELECT p FROM Proveedores p WHERE p.idProveedor = :idProveedor"), @NamedQuery(name = "Proveedores.findByNombres", query = "SELECT p FROM Proveedores p WHERE p.nombres = :nombres"), @NamedQuery(name = "Proveedores.findByApellido", query = "SELECT p FROM Proveedores p WHERE p.apellido = :apellido"), @NamedQuery(name = "Proveedores.findByMails", query = "SELECT p FROM Proveedores p WHERE p.mails = :mails"), @NamedQuery(name = "Proveedores.findByTelefonos", query = "SELECT p FROM Proveedores p WHERE p.telefonos = :telefonos"), @NamedQuery(name = "Proveedores.findByFechaAlta", query = "SELECT p FROM Proveedores p WHERE p.fechaAlta = :fechaAlta"), @NamedQuery(name = "Proveedores.findByObservaciones", query = "SELECT p FROM Proveedores p WHERE p.observaciones = :observaciones")})
public class Proveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idProveedor", nullable = false)
    private Integer idProveedor;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "mails", nullable = false)
    private String mails;
    @Column(name = "telefonos", nullable = false)
    private String telefonos;
    @Column(name = "fechaAlta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "observaciones", nullable = false)
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private Collection<Facturascompras> facturascomprasCollection;
    @OneToMany(mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private Collection<Domicilios> domiciliosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private Collection<Remitosingreso> remitosingresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private Collection<Notascredito> notascreditoCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @JoinColumn(name = "idTipoDocumento", referencedColumnName = "idTipoDocumento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tiposdocumento idTipoDocumento;

    public Proveedores() {
    }

    public Proveedores(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedores(Integer idProveedor, String nombres, String apellido, String mails, String telefonos, Date fechaAlta, String observaciones) {
        this.idProveedor = idProveedor;
        this.nombres = nombres;
        this.apellido = apellido;
        this.mails = mails;
        this.telefonos = telefonos;
        this.fechaAlta = fechaAlta;
        this.observaciones = observaciones;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Collection<Facturascompras> getFacturascomprasCollection() {
        return facturascomprasCollection;
    }

    public void setFacturascomprasCollection(Collection<Facturascompras> facturascomprasCollection) {
        this.facturascomprasCollection = facturascomprasCollection;
    }

    public Collection<Domicilios> getDomiciliosCollection() {
        return domiciliosCollection;
    }

    public void setDomiciliosCollection(Collection<Domicilios> domiciliosCollection) {
        this.domiciliosCollection = domiciliosCollection;
    }

    public Collection<Remitosingreso> getRemitosingresoCollection() {
        return remitosingresoCollection;
    }

    public void setRemitosingresoCollection(Collection<Remitosingreso> remitosingresoCollection) {
        this.remitosingresoCollection = remitosingresoCollection;
    }

    public Collection<Notascredito> getNotascreditoCollection() {
        return notascreditoCollection;
    }

    public void setNotascreditoCollection(Collection<Notascredito> notascreditoCollection) {
        this.notascreditoCollection = notascreditoCollection;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Tiposdocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Tiposdocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Proveedores[idProveedor=" + idProveedor + "]";
    }

}
