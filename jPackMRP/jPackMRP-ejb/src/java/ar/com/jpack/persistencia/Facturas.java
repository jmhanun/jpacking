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
@Table(name = "facturas")
@NamedQueries({
@NamedQuery(name = "Facturas.findByIdFactura", query = "SELECT f FROM Facturas f WHERE f.idFactura = :idFactura"),
@NamedQuery(name = "Facturas.findByNroFactura", query = "SELECT f FROM Facturas f WHERE f.nroFactura = :nroFactura"),
@NamedQuery(name = "Facturas.findByFecha", query = "SELECT f FROM Facturas f WHERE f.fecha = :fecha"),
@NamedQuery(name = "Facturas.findByLetra", query = "SELECT f FROM Facturas f WHERE f.letra = :letra"),
@NamedQuery(name = "Facturas.findByImporte", query = "SELECT f FROM Facturas f WHERE f.importe = :importe"),
@NamedQuery(name = "Facturas.findByRemitos", query = "SELECT f FROM Facturas f WHERE f.remitos = :remitos"),
@NamedQuery(name = "Facturas.findByDescuento", query = "SELECT f FROM Facturas f WHERE f.descuento = :descuento"),
@NamedQuery(name = "Facturas.findByEstado", query = "SELECT f FROM Facturas f WHERE f.estado = :estado")
})
public class Facturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idFactura", nullable = false)
    private Integer idFactura;
    @Column(name = "nroFactura", nullable = false)
    private Integer nroFactura;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "letra", nullable = false)
    private String letra;
    @Column(name = "importe", nullable = false)
    private Double importe;
    @Column(name = "remitos")
    private String remitos;
    @Column(name = "descuento")
    private Float descuento;
    @Column(name = "estado", nullable = false)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactura")
    private Collection<DetalleFacturas> detallefacturasCollection;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne
    private Clientes idCliente;

    public Facturas() {
    }

    public Facturas(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Facturas(Integer idFactura, Integer nroFactura, Date fecha, String letra, Double importe, String estado) {
        this.idFactura = idFactura;
        this.nroFactura = nroFactura;
        this.fecha = fecha;
        this.letra = letra;
        this.importe = importe;
        this.estado = estado;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(Integer nroFactura) {
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

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getRemitos() {
        return remitos;
    }

    public void setRemitos(String remitos) {
        this.remitos = remitos;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<DetalleFacturas> getDetallefacturasCollection() {
        return detallefacturasCollection;
    }

    public void setDetallefacturasCollection(Collection<DetalleFacturas> detallefacturasCollection) {
        this.detallefacturasCollection = detallefacturasCollection;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
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
