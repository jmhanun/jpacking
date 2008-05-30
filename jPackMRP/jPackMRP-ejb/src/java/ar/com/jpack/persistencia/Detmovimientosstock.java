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
@Table(name = "detmovimientosstock")
@NamedQueries({@NamedQuery(name = "Detmovimientosstock.findByIdDetMovStock", query = "SELECT d FROM Detmovimientosstock d WHERE d.detmovimientosstockPK.idDetMovStock = :idDetMovStock"), @NamedQuery(name = "Detmovimientosstock.findByDescripcion", query = "SELECT d FROM Detmovimientosstock d WHERE d.descripcion = :descripcion"), @NamedQuery(name = "Detmovimientosstock.findByCantidad", query = "SELECT d FROM Detmovimientosstock d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detmovimientosstock.findByIdMovStock", query = "SELECT d FROM Detmovimientosstock d WHERE d.detmovimientosstockPK.idMovStock = :idMovStock")})
public class Detmovimientosstock implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetmovimientosstockPK detmovimientosstockPK;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "cantidad", nullable = false)
    private double cantidad;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idMovStock", referencedColumnName = "idMovStock", insertable = false, updatable = false)
    @ManyToOne
    private Movimientosstock movimientosstock;
    @JoinColumn(name = "idTipoMov", referencedColumnName = "idTipoMov")
    @ManyToOne
    private Tiposmovstock idTipoMov;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne
    private Unidadesmedida idUnidMedida;

    public Detmovimientosstock() {
    }

    public Detmovimientosstock(DetmovimientosstockPK detmovimientosstockPK) {
        this.detmovimientosstockPK = detmovimientosstockPK;
    }

    public Detmovimientosstock(DetmovimientosstockPK detmovimientosstockPK, String descripcion, double cantidad) {
        this.detmovimientosstockPK = detmovimientosstockPK;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Detmovimientosstock(int idDetMovStock, int idMovStock) {
        this.detmovimientosstockPK = new DetmovimientosstockPK(idDetMovStock, idMovStock);
    }

    public DetmovimientosstockPK getDetmovimientosstockPK() {
        return detmovimientosstockPK;
    }

    public void setDetmovimientosstockPK(DetmovimientosstockPK detmovimientosstockPK) {
        this.detmovimientosstockPK = detmovimientosstockPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Movimientosstock getMovimientosstock() {
        return movimientosstock;
    }

    public void setMovimientosstock(Movimientosstock movimientosstock) {
        this.movimientosstock = movimientosstock;
    }

    public Tiposmovstock getIdTipoMov() {
        return idTipoMov;
    }

    public void setIdTipoMov(Tiposmovstock idTipoMov) {
        this.idTipoMov = idTipoMov;
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
        hash += (detmovimientosstockPK != null ? detmovimientosstockPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detmovimientosstock)) {
            return false;
        }
        Detmovimientosstock other = (Detmovimientosstock) object;
        if ((this.detmovimientosstockPK == null && other.detmovimientosstockPK != null) || (this.detmovimientosstockPK != null && !this.detmovimientosstockPK.equals(other.detmovimientosstockPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detmovimientosstock[detmovimientosstockPK=" + detmovimientosstockPK + "]";
    }

}
