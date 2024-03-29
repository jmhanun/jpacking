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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "facturas")
@NamedQueries({@NamedQuery(name = "Facturas.findByIdFactura", query = "SELECT f FROM Facturas f WHERE f.idFactura = :idFactura"), @NamedQuery(name = "Facturas.findByNroFactura", query = "SELECT f FROM Facturas f WHERE f.nroFactura = :nroFactura"), @NamedQuery(name = "Facturas.findByFecha", query = "SELECT f FROM Facturas f WHERE f.fecha = :fecha"), @NamedQuery(name = "Facturas.findByLetra", query = "SELECT f FROM Facturas f WHERE f.letra = :letra"), @NamedQuery(name = "Facturas.findByImporte", query = "SELECT f FROM Facturas f WHERE f.importe = :importe"), @NamedQuery(name = "Facturas.findByDescuento", query = "SELECT f FROM Facturas f WHERE f.descuento = :descuento"), @NamedQuery(name = "Facturas.findByFechaModificacion", query = "SELECT f FROM Facturas f WHERE f.fechaModificacion = :fechaModificacion")})
public class Facturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idFactura", nullable = false)
    private Integer idFactura;
    @Column(name = "nroFactura", nullable = false)
    private int nroFactura;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "letra", nullable = false)
    private String letra;
    @Column(name = "importe", nullable = false)
    private double importe;
    @Column(name = "descuento")
    private Float descuento;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "idRemito")
    private Integer idRemito;
    @JoinTable(name = "remitosxfactura", joinColumns = {@JoinColumn(name = "idFactura", referencedColumnName = "idFactura")}, inverseJoinColumns = {@JoinColumn(name = "idRemito", referencedColumnName = "idRemito")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Remitos> idRemitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturas", fetch = FetchType.LAZY)
    private Collection<Detallefacturas> detallefacturasCollection;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes idCliente;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tiposcomprobantes idTipoComprobante;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;

    public Facturas() {
    }

    public Facturas(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Facturas(Integer idFactura, int nroFactura, Date fecha, String letra, double importe, Date fechaModificacion) {
        this.idFactura = idFactura;
        this.nroFactura = nroFactura;
        this.fecha = fecha;
        this.letra = letra;
        this.importe = importe;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
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

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Collection<Remitos> getIdRemitoCollection() {
        return idRemitoCollection;
    }

    public void setIdRemitoCollection(Collection<Remitos> idRemitoCollection) {
        this.idRemitoCollection = idRemitoCollection;
    }

    public Collection<Detallefacturas> getDetallefacturasCollection() {
        return detallefacturasCollection;
    }

    public void setDetallefacturasCollection(Collection<Detallefacturas> detallefacturasCollection) {
        this.detallefacturasCollection = detallefacturasCollection;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
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

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Facturas[idFactura=" + idFactura + "]";
    }
}
