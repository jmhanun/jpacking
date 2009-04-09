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
@Table(name = "facturascompras")
@NamedQueries({@NamedQuery(name = "Facturascompras.findByIdFactCompra", query = "SELECT f FROM Facturascompras f WHERE f.idFactCompra = :idFactCompra"), @NamedQuery(name = "Facturascompras.findByNroFactura", query = "SELECT f FROM Facturascompras f WHERE f.nroFactura = :nroFactura"), @NamedQuery(name = "Facturascompras.findByFecha", query = "SELECT f FROM Facturascompras f WHERE f.fecha = :fecha"), @NamedQuery(name = "Facturascompras.findByLetra", query = "SELECT f FROM Facturascompras f WHERE f.letra = :letra"), @NamedQuery(name = "Facturascompras.findByImporte", query = "SELECT f FROM Facturascompras f WHERE f.importe = :importe"), @NamedQuery(name = "Facturascompras.findByDescuento", query = "SELECT f FROM Facturascompras f WHERE f.descuento = :descuento"), @NamedQuery(name = "Facturascompras.findByFechaModificacion", query = "SELECT f FROM Facturascompras f WHERE f.fechaModificacion = :fechaModificacion")})
public class Facturascompras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idFactCompra", nullable = false)
    private Integer idFactCompra;
    @Column(name = "nroFactura", nullable = false)
    private int nroFactura;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturascompras", fetch = FetchType.LAZY)
    private Collection<Detallefactcompras> detallefactcomprasCollection;

    public Facturascompras() {
    }

    public Facturascompras(Integer idFactCompra) {
        this.idFactCompra = idFactCompra;
    }

    public Facturascompras(Integer idFactCompra, int nroFactura, Date fecha, String letra, double importe, float descuento, Date fechaModificacion) {
        this.idFactCompra = idFactCompra;
        this.nroFactura = nroFactura;
        this.fecha = fecha;
        this.letra = letra;
        this.importe = importe;
        this.descuento = descuento;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdFactCompra() {
        return idFactCompra;
    }

    public void setIdFactCompra(Integer idFactCompra) {
        this.idFactCompra = idFactCompra;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
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

    public Collection<Detallefactcompras> getDetallefactcomprasCollection() {
        return detallefactcomprasCollection;
    }

    public void setDetallefactcomprasCollection(Collection<Detallefactcompras> detallefactcomprasCollection) {
        this.detallefactcomprasCollection = detallefactcomprasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactCompra != null ? idFactCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturascompras)) {
            return false;
        }
        Facturascompras other = (Facturascompras) object;
        if ((this.idFactCompra == null && other.idFactCompra != null) || (this.idFactCompra != null && !this.idFactCompra.equals(other.idFactCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Facturascompras[idFactCompra=" + idFactCompra + "]";
    }

}
