/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "detallefacturas")
@NamedQueries({
@NamedQuery(name = "Detallefacturas.findByIdDetalle", query = "SELECT d FROM Detallefacturas d WHERE d.idDetalle = :idDetalle"),
@NamedQuery(name = "Detallefacturas.findByCantidad", query = "SELECT d FROM Detallefacturas d WHERE d.cantidad = :cantidad"),
@NamedQuery(name = "Detallefacturas.findByPrecioUnitario", query = "SELECT d FROM Detallefacturas d WHERE d.precioUnitario = :precioUnitario"),
@NamedQuery(name = "Detallefacturas.findByImporte", query = "SELECT d FROM Detallefacturas d WHERE d.importe = :importe"),
@NamedQuery(name = "Detallefacturas.findByDescuento", query = "SELECT d FROM Detallefacturas d WHERE d.descuento = :descuento")
})
public class DetalleFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idDetalle", nullable = false)
    private Integer idDetalle;
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    @Column(name = "precioUnitario", nullable = false)
    private Double precioUnitario;
    @Column(name = "importe", nullable = false)
    private Double importe;
    @Column(name = "descuento", nullable = false)
    private Double descuento;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura")
    @ManyToOne
    private Facturas idFactura;

    public DetalleFacturas() {
    }

    public DetalleFacturas(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public DetalleFacturas(Integer idDetalle, Integer cantidad, Double precioUnitario, Double importe, Double descuento) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Facturas getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Facturas idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFacturas)) {
            return false;
        }
        DetalleFacturas other = (DetalleFacturas) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detallefacturas[idDetalle=" + idDetalle + "]";
    }
}
