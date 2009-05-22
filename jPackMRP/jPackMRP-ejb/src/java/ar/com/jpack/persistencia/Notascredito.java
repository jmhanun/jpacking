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
@Table(name = "notascredito")
@NamedQueries({@NamedQuery(name = "Notascredito.findByIdNotaCredito", query = "SELECT n FROM Notascredito n WHERE n.idNotaCredito = :idNotaCredito"), @NamedQuery(name = "Notascredito.findByNroNotaCredito", query = "SELECT n FROM Notascredito n WHERE n.nroNotaCredito = :nroNotaCredito"), @NamedQuery(name = "Notascredito.findByFecha", query = "SELECT n FROM Notascredito n WHERE n.fecha = :fecha"), @NamedQuery(name = "Notascredito.findByLetra", query = "SELECT n FROM Notascredito n WHERE n.letra = :letra"), @NamedQuery(name = "Notascredito.findByImporte", query = "SELECT n FROM Notascredito n WHERE n.importe = :importe"), @NamedQuery(name = "Notascredito.findByDescuento", query = "SELECT n FROM Notascredito n WHERE n.descuento = :descuento"), @NamedQuery(name = "Notascredito.findByFechaModificacion", query = "SELECT n FROM Notascredito n WHERE n.fechaModificacion = :fechaModificacion")})
public class Notascredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idNotaCredito", nullable = false)
    private Integer idNotaCredito;
    @Column(name = "nroNotaCredito", nullable = false)
    private int nroNotaCredito;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "letra", nullable = false)
    private String letra;
    @Column(name = "importe", nullable = false)
    private double importe;
    @Column(name = "descuento", nullable = false)
    private float descuento;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notascredito", fetch = FetchType.LAZY)
    private Collection<Detnotascredito> detnotascreditoCollection;
    @OneToMany(mappedBy = "idNotaCredito", fetch = FetchType.LAZY)
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proveedores idProveedor;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tiposcomprobantes idTipoComprobante;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;

    public Notascredito() {
    }

    public Notascredito(Integer idNotaCredito) {
        this.idNotaCredito = idNotaCredito;
    }

    public Notascredito(Integer idNotaCredito, int nroNotaCredito, Date fecha, String letra, double importe, float descuento, Date fechaModificacion) {
        this.idNotaCredito = idNotaCredito;
        this.nroNotaCredito = nroNotaCredito;
        this.fecha = fecha;
        this.letra = letra;
        this.importe = importe;
        this.descuento = descuento;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdNotaCredito() {
        return idNotaCredito;
    }

    public void setIdNotaCredito(Integer idNotaCredito) {
        this.idNotaCredito = idNotaCredito;
    }

    public int getNroNotaCredito() {
        return nroNotaCredito;
    }

    public void setNroNotaCredito(int nroNotaCredito) {
        this.nroNotaCredito = nroNotaCredito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Collection<Detnotascredito> getDetnotascreditoCollection() {
        return detnotascreditoCollection;
    }

    public void setDetnotascreditoCollection(Collection<Detnotascredito> detnotascreditoCollection) {
        this.detnotascreditoCollection = detnotascreditoCollection;
    }

    public Collection<Detmovimientosstock> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<Detmovimientosstock> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Tiposcomprobantes getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Tiposcomprobantes idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotaCredito != null ? idNotaCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notascredito)) {
            return false;
        }
        Notascredito other = (Notascredito) object;
        if ((this.idNotaCredito == null && other.idNotaCredito != null) || (this.idNotaCredito != null && !this.idNotaCredito.equals(other.idNotaCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Notascredito[idNotaCredito=" + idNotaCredito + "]";
    }

}
