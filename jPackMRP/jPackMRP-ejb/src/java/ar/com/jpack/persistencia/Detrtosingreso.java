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
@Table(name = "detrtosingreso")
@NamedQueries({@NamedQuery(name = "Detrtosingreso.findByIdDetRtoIngreso", query = "SELECT d FROM Detrtosingreso d WHERE d.detrtosingresoPK.idDetRtoIngreso = :idDetRtoIngreso"), @NamedQuery(name = "Detrtosingreso.findByIdRtoIngreso", query = "SELECT d FROM Detrtosingreso d WHERE d.detrtosingresoPK.idRtoIngreso = :idRtoIngreso"), @NamedQuery(name = "Detrtosingreso.findByCantidad", query = "SELECT d FROM Detrtosingreso d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detrtosingreso.findByPrecioUnitario", query = "SELECT d FROM Detrtosingreso d WHERE d.precioUnitario = :precioUnitario"), @NamedQuery(name = "Detrtosingreso.findByImporte", query = "SELECT d FROM Detrtosingreso d WHERE d.importe = :importe"), @NamedQuery(name = "Detrtosingreso.findByDescuento", query = "SELECT d FROM Detrtosingreso d WHERE d.descuento = :descuento")})
public class Detrtosingreso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetrtosingresoPK detrtosingresoPK;
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
    @JoinColumn(name = "idRtoIngreso", referencedColumnName = "idRtoIngreso", insertable = false, updatable = false)
    @ManyToOne
    private Remitosingreso remitosingreso;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne
    private Unidadesmedida idUnidMedida;

    public Detrtosingreso() {
    }

    public Detrtosingreso(DetrtosingresoPK detrtosingresoPK) {
        this.detrtosingresoPK = detrtosingresoPK;
    }

    public Detrtosingreso(DetrtosingresoPK detrtosingresoPK, int cantidad, double precioUnitario, double importe, float descuento) {
        this.detrtosingresoPK = detrtosingresoPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
    }

    public Detrtosingreso(int idDetRtoIngreso, int idRtoIngreso) {
        this.detrtosingresoPK = new DetrtosingresoPK(idDetRtoIngreso, idRtoIngreso);
    }

    public DetrtosingresoPK getDetrtosingresoPK() {
        return detrtosingresoPK;
    }

    public void setDetrtosingresoPK(DetrtosingresoPK detrtosingresoPK) {
        this.detrtosingresoPK = detrtosingresoPK;
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

    public Remitosingreso getRemitosingreso() {
        return remitosingreso;
    }

    public void setRemitosingreso(Remitosingreso remitosingreso) {
        this.remitosingreso = remitosingreso;
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
        hash += (detrtosingresoPK != null ? detrtosingresoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detrtosingreso)) {
            return false;
        }
        Detrtosingreso other = (Detrtosingreso) object;
        if ((this.detrtosingresoPK == null && other.detrtosingresoPK != null) || (this.detrtosingresoPK != null && !this.detrtosingresoPK.equals(other.detrtosingresoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detrtosingreso[detrtosingresoPK=" + detrtosingresoPK + "]";
    }

}
