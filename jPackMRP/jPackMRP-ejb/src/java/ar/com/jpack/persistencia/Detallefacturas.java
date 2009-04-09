/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@NamedQueries({@NamedQuery(name = "Detallefacturas.findByIdDetalle", query = "SELECT d FROM Detallefacturas d WHERE d.detallefacturasPK.idDetalle = :idDetalle"), @NamedQuery(name = "Detallefacturas.findByIdFactura", query = "SELECT d FROM Detallefacturas d WHERE d.detallefacturasPK.idFactura = :idFactura"), @NamedQuery(name = "Detallefacturas.findByCantidad", query = "SELECT d FROM Detallefacturas d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detallefacturas.findByPrecioUnitario", query = "SELECT d FROM Detallefacturas d WHERE d.precioUnitario = :precioUnitario"), @NamedQuery(name = "Detallefacturas.findByImporte", query = "SELECT d FROM Detallefacturas d WHERE d.importe = :importe"), @NamedQuery(name = "Detallefacturas.findByDescuento", query = "SELECT d FROM Detallefacturas d WHERE d.descuento = :descuento")})
public class Detallefacturas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallefacturasPK detallefacturasPK;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "precioUnitario", nullable = false)
    private double precioUnitario;
    @Column(name = "importe", nullable = false)
    private double importe;
    @Column(name = "descuento", nullable = false)
    private float descuento;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos idArticulo;
    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Facturas facturas;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unidadesmedida idUnidMedida;

    public Detallefacturas() {
    }

    public Detallefacturas(DetallefacturasPK detallefacturasPK) {
        this.detallefacturasPK = detallefacturasPK;
    }

    public Detallefacturas(DetallefacturasPK detallefacturasPK, int cantidad, double precioUnitario, double importe, float descuento) {
        this.detallefacturasPK = detallefacturasPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
    }

    public Detallefacturas(int idDetalle, int idFactura) {
        this.detallefacturasPK = new DetallefacturasPK(idDetalle, idFactura);
    }

    public DetallefacturasPK getDetallefacturasPK() {
        return detallefacturasPK;
    }

    public void setDetallefacturasPK(DetallefacturasPK detallefacturasPK) {
        this.detallefacturasPK = detallefacturasPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Facturas getFacturas() {
        return facturas;
    }

    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
    }

    public Unidadesmedida getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(Unidadesmedida idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallefacturasPK != null ? detallefacturasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefacturas)) {
            return false;
        }
        Detallefacturas other = (Detallefacturas) object;
        if ((this.detallefacturasPK == null && other.detallefacturasPK != null) || (this.detallefacturasPK != null && !this.detallefacturasPK.equals(other.detallefacturasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detallefacturas[detallefacturasPK=" + detallefacturasPK + "]";
    }

}
