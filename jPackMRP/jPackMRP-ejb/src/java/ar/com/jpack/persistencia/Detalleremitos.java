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
 * @author Pablo
 */
@Entity
@Table(name = "detalleremitos")
@NamedQueries({@NamedQuery(name = "Detalleremitos.findByIdDetalle", query = "SELECT d FROM Detalleremitos d WHERE d.idDetalle = :idDetalle"), @NamedQuery(name = "Detalleremitos.findByCantidad", query = "SELECT d FROM Detalleremitos d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detalleremitos.findByPrecioUnitario", query = "SELECT d FROM Detalleremitos d WHERE d.precioUnitario = :precioUnitario"), @NamedQuery(name = "Detalleremitos.findByImporte", query = "SELECT d FROM Detalleremitos d WHERE d.importe = :importe")})
public class Detalleremitos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idDetalle", nullable = false)
    private Integer idDetalle;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "precioUnitario", nullable = false)
    private double precioUnitario;
    @Column(name = "importe", nullable = false)
    private double importe;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idRemito", referencedColumnName = "idRemito")
    @ManyToOne
    private Remitos idRemito;

    public Detalleremitos() {
    }

    public Detalleremitos(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Detalleremitos(Integer idDetalle, int cantidad, double precioUnitario, double importe) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
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

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Remitos getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Remitos idRemito) {
        this.idRemito = idRemito;
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
        if (!(object instanceof Detalleremitos)) {
            return false;
        }
        Detalleremitos other = (Detalleremitos) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detalleremitos[idDetalle=" + idDetalle + "]";
    }

}
