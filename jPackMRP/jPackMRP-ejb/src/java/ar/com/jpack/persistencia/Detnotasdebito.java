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
@Table(name = "detnotasdebito")
@NamedQueries({@NamedQuery(name = "Detnotasdebito.findByIdDetNotaDebito", query = "SELECT d FROM Detnotasdebito d WHERE d.detnotasdebitoPK.idDetNotaDebito = :idDetNotaDebito"), @NamedQuery(name = "Detnotasdebito.findByIdNotaDebito", query = "SELECT d FROM Detnotasdebito d WHERE d.detnotasdebitoPK.idNotaDebito = :idNotaDebito"), @NamedQuery(name = "Detnotasdebito.findByCantidad", query = "SELECT d FROM Detnotasdebito d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detnotasdebito.findByPrecioUnitario", query = "SELECT d FROM Detnotasdebito d WHERE d.precioUnitario = :precioUnitario"), @NamedQuery(name = "Detnotasdebito.findByImporte", query = "SELECT d FROM Detnotasdebito d WHERE d.importe = :importe"), @NamedQuery(name = "Detnotasdebito.findByDescuento", query = "SELECT d FROM Detnotasdebito d WHERE d.descuento = :descuento")})
public class Detnotasdebito implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetnotasdebitoPK detnotasdebitoPK;
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
    @JoinColumn(name = "idNotaDebito", referencedColumnName = "idNotaDebito", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Notasdebito notasdebito;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unidadesmedida idUnidMedida;

    public Detnotasdebito() {
    }

    public Detnotasdebito(DetnotasdebitoPK detnotasdebitoPK) {
        this.detnotasdebitoPK = detnotasdebitoPK;
    }

    public Detnotasdebito(DetnotasdebitoPK detnotasdebitoPK, int cantidad, double precioUnitario, double importe, float descuento) {
        this.detnotasdebitoPK = detnotasdebitoPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
    }

    public Detnotasdebito(int idDetNotaDebito, int idNotaDebito) {
        this.detnotasdebitoPK = new DetnotasdebitoPK(idDetNotaDebito, idNotaDebito);
    }

    public DetnotasdebitoPK getDetnotasdebitoPK() {
        return detnotasdebitoPK;
    }

    public void setDetnotasdebitoPK(DetnotasdebitoPK detnotasdebitoPK) {
        this.detnotasdebitoPK = detnotasdebitoPK;
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

    public Notasdebito getNotasdebito() {
        return notasdebito;
    }

    public void setNotasdebito(Notasdebito notasdebito) {
        this.notasdebito = notasdebito;
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
        hash += (detnotasdebitoPK != null ? detnotasdebitoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detnotasdebito)) {
            return false;
        }
        Detnotasdebito other = (Detnotasdebito) object;
        if ((this.detnotasdebitoPK == null && other.detnotasdebitoPK != null) || (this.detnotasdebitoPK != null && !this.detnotasdebitoPK.equals(other.detnotasdebitoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detnotasdebito[detnotasdebitoPK=" + detnotasdebitoPK + "]";
    }

}
