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
@Table(name = "detallefactcompras")
@NamedQueries({@NamedQuery(name = "Detallefactcompras.findByIdDetFactCompras", query = "SELECT d FROM Detallefactcompras d WHERE d.detallefactcomprasPK.idDetFactCompras = :idDetFactCompras"), @NamedQuery(name = "Detallefactcompras.findByIdFactCompra", query = "SELECT d FROM Detallefactcompras d WHERE d.detallefactcomprasPK.idFactCompra = :idFactCompra"), @NamedQuery(name = "Detallefactcompras.findByCantidad", query = "SELECT d FROM Detallefactcompras d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detallefactcompras.findByPrecioUnitario", query = "SELECT d FROM Detallefactcompras d WHERE d.precioUnitario = :precioUnitario"), @NamedQuery(name = "Detallefactcompras.findByImporte", query = "SELECT d FROM Detallefactcompras d WHERE d.importe = :importe"), @NamedQuery(name = "Detallefactcompras.findByDescuento", query = "SELECT d FROM Detallefactcompras d WHERE d.descuento = :descuento")})
public class Detallefactcompras implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallefactcomprasPK detallefactcomprasPK;
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
    @JoinColumn(name = "idFactCompra", referencedColumnName = "idFactCompra", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Facturascompras facturascompras;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unidadesmedida idUnidMedida;

    public Detallefactcompras() {
    }

    public Detallefactcompras(DetallefactcomprasPK detallefactcomprasPK) {
        this.detallefactcomprasPK = detallefactcomprasPK;
    }

    public Detallefactcompras(DetallefactcomprasPK detallefactcomprasPK, int cantidad, double precioUnitario, double importe, float descuento) {
        this.detallefactcomprasPK = detallefactcomprasPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
    }

    public Detallefactcompras(int idDetFactCompras, int idFactCompra) {
        this.detallefactcomprasPK = new DetallefactcomprasPK(idDetFactCompras, idFactCompra);
    }

    public DetallefactcomprasPK getDetallefactcomprasPK() {
        return detallefactcomprasPK;
    }

    public void setDetallefactcomprasPK(DetallefactcomprasPK detallefactcomprasPK) {
        this.detallefactcomprasPK = detallefactcomprasPK;
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

    public Facturascompras getFacturascompras() {
        return facturascompras;
    }

    public void setFacturascompras(Facturascompras facturascompras) {
        this.facturascompras = facturascompras;
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
        hash += (detallefactcomprasPK != null ? detallefactcomprasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactcompras)) {
            return false;
        }
        Detallefactcompras other = (Detallefactcompras) object;
        if ((this.detallefactcomprasPK == null && other.detallefactcomprasPK != null) || (this.detallefactcomprasPK != null && !this.detallefactcomprasPK.equals(other.detallefactcomprasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detallefactcompras[detallefactcomprasPK=" + detallefactcomprasPK + "]";
    }

}
