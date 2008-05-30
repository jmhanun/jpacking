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
@Table(name = "movimientosstock")
@NamedQueries({@NamedQuery(name = "Movimientosstock.findByIdMovStock", query = "SELECT m FROM Movimientosstock m WHERE m.idMovStock = :idMovStock"), @NamedQuery(name = "Movimientosstock.findByDescripcion", query = "SELECT m FROM Movimientosstock m WHERE m.descripcion = :descripcion"), @NamedQuery(name = "Movimientosstock.findByFecha", query = "SELECT m FROM Movimientosstock m WHERE m.fecha = :fecha")})
public class Movimientosstock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idMovStock", nullable = false)
    private Integer idMovStock;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimientosstock")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;

    public Movimientosstock() {
    }

    public Movimientosstock(Integer idMovStock) {
        this.idMovStock = idMovStock;
    }

    public Movimientosstock(Integer idMovStock, String descripcion, Date fecha) {
        this.idMovStock = idMovStock;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Integer getIdMovStock() {
        return idMovStock;
    }

    public void setIdMovStock(Integer idMovStock) {
        this.idMovStock = idMovStock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Collection<Detmovimientosstock> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<Detmovimientosstock> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovStock != null ? idMovStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientosstock)) {
            return false;
        }
        Movimientosstock other = (Movimientosstock) object;
        if ((this.idMovStock == null && other.idMovStock != null) || (this.idMovStock != null && !this.idMovStock.equals(other.idMovStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Movimientosstock[idMovStock=" + idMovStock + "]";
    }

}
