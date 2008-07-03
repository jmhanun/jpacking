/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "detnotascredito")
@NamedQueries({@NamedQuery(name = "Detnotascredito.findByIdDetNotaCredito", query = "SELECT d FROM Detnotascredito d WHERE d.detnotascreditoPK.idDetNotaCredito = :idDetNotaCredito"), @NamedQuery(name = "Detnotascredito.findByIdNotaCredito", query = "SELECT d FROM Detnotascredito d WHERE d.detnotascreditoPK.idNotaCredito = :idNotaCredito"), @NamedQuery(name = "Detnotascredito.findByCantidad", query = "SELECT d FROM Detnotascredito d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detnotascredito.findByPrecioUnitario", query = "SELECT d FROM Detnotascredito d WHERE d.precioUnitario = :precioUnitario"), @NamedQuery(name = "Detnotascredito.findByImporte", query = "SELECT d FROM Detnotascredito d WHERE d.importe = :importe"), @NamedQuery(name = "Detnotascredito.findByDescuento", query = "SELECT d FROM Detnotascredito d WHERE d.descuento = :descuento")})
public class Detnotascredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetnotascreditoPK detnotascreditoPK;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "precioUnitario", nullable = false)
    private double precioUnitario;
    @Column(name = "importe", nullable = false)
    private double importe;
    @Column(name = "descuento", nullable = false)
    private float descuento;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idNotaCredito", referencedColumnName = "idNotaCredito", insertable = false, updatable = false)
    @ManyToOne
    private Notascredito notascredito;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne
    private Unidadesmedida idUnidMedida;

    public Detnotascredito() {
    }

    public Detnotascredito(DetnotascreditoPK detnotascreditoPK) {
        this.detnotascreditoPK = detnotascreditoPK;
    }

    public Detnotascredito(DetnotascreditoPK detnotascreditoPK, int cantidad, double precioUnitario, double importe, float descuento) {
        this.detnotascreditoPK = detnotascreditoPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
    }

    public Detnotascredito(int idDetNotaCredito, int idNotaCredito) {
        this.detnotascreditoPK = new DetnotascreditoPK(idDetNotaCredito, idNotaCredito);
    }

    public DetnotascreditoPK getDetnotascreditoPK() {
        return detnotascreditoPK;
    }

    public void setDetnotascreditoPK(DetnotascreditoPK detnotascreditoPK) {
        this.detnotascreditoPK = detnotascreditoPK;
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

    public Notascredito getNotascredito() {
        return notascredito;
    }

    public void setNotascredito(Notascredito notascredito) {
        this.notascredito = notascredito;
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
        hash += (detnotascreditoPK != null ? detnotascreditoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detnotascredito)) {
            return false;
        }
        Detnotascredito other = (Detnotascredito) object;
        if ((this.detnotascreditoPK == null && other.detnotascreditoPK != null) || (this.detnotascreditoPK != null && !this.detnotascreditoPK.equals(other.detnotascreditoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detnotascredito[detnotascreditoPK=" + detnotascreditoPK + "]";
    }

}
