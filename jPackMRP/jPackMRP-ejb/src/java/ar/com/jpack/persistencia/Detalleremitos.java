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
@Table(name = "detalleremitos")
@NamedQueries({@NamedQuery(name = "Detalleremitos.findByIdDetalleRemito", query = "SELECT d FROM Detalleremitos d WHERE d.detalleremitosPK.idDetalleRemito = :idDetalleRemito"), @NamedQuery(name = "Detalleremitos.findByIdRemito", query = "SELECT d FROM Detalleremitos d WHERE d.detalleremitosPK.idRemito = :idRemito"), @NamedQuery(name = "Detalleremitos.findByCantidad", query = "SELECT d FROM Detalleremitos d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detalleremitos.findByPrecioUnitario", query = "SELECT d FROM Detalleremitos d WHERE d.precioUnitario = :precioUnitario"), @NamedQuery(name = "Detalleremitos.findByImporte", query = "SELECT d FROM Detalleremitos d WHERE d.importe = :importe")})
public class Detalleremitos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleremitosPK detalleremitosPK;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "precioUnitario", nullable = false)
    private double precioUnitario;
    @Column(name = "importe", nullable = false)
    private double importe;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos idArticulo;
    @JoinColumn(name = "idRemito", referencedColumnName = "idRemito", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Remitos remitos;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unidadesmedida idUnidMedida;

    public Detalleremitos() {
    }

    public Detalleremitos(DetalleremitosPK detalleremitosPK) {
        this.detalleremitosPK = detalleremitosPK;
    }

    public Detalleremitos(DetalleremitosPK detalleremitosPK, int cantidad, double precioUnitario, double importe) {
        this.detalleremitosPK = detalleremitosPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
    }

    public Detalleremitos(int idDetalleRemito, int idRemito) {
        this.detalleremitosPK = new DetalleremitosPK(idDetalleRemito, idRemito);
    }

    public DetalleremitosPK getDetalleremitosPK() {
        return detalleremitosPK;
    }

    public void setDetalleremitosPK(DetalleremitosPK detalleremitosPK) {
        this.detalleremitosPK = detalleremitosPK;
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

    public Remitos getRemitos() {
        return remitos;
    }

    public void setRemitos(Remitos remitos) {
        this.remitos = remitos;
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
        hash += (detalleremitosPK != null ? detalleremitosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleremitos)) {
            return false;
        }
        Detalleremitos other = (Detalleremitos) object;
        if ((this.detalleremitosPK == null && other.detalleremitosPK != null) || (this.detalleremitosPK != null && !this.detalleremitosPK.equals(other.detalleremitosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detalleremitos[detalleremitosPK=" + detalleremitosPK + "]";
    }

}
