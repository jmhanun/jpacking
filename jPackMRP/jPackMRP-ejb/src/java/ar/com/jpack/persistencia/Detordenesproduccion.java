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
@Table(name = "detordenesproduccion")
@NamedQueries({@NamedQuery(name = "Detordenesproduccion.findByIdDetOrdProduccion", query = "SELECT d FROM Detordenesproduccion d WHERE d.detordenesproduccionPK.idDetOrdProduccion = :idDetOrdProduccion"), @NamedQuery(name = "Detordenesproduccion.findByIdOrdenProduccion", query = "SELECT d FROM Detordenesproduccion d WHERE d.detordenesproduccionPK.idOrdenProduccion = :idOrdenProduccion"), @NamedQuery(name = "Detordenesproduccion.findByCantidad", query = "SELECT d FROM Detordenesproduccion d WHERE d.cantidad = :cantidad")})
public class Detordenesproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetordenesproduccionPK detordenesproduccionPK;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idOrdenProduccion", referencedColumnName = "idOrdenProduccion", insertable = false, updatable = false)
    @ManyToOne
    private Ordenesproduccion ordenesproduccion;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne
    private Unidadesmedida idUnidMedida;

    public Detordenesproduccion() {
    }

    public Detordenesproduccion(DetordenesproduccionPK detordenesproduccionPK) {
        this.detordenesproduccionPK = detordenesproduccionPK;
    }

    public Detordenesproduccion(DetordenesproduccionPK detordenesproduccionPK, int cantidad) {
        this.detordenesproduccionPK = detordenesproduccionPK;
        this.cantidad = cantidad;
    }

    public Detordenesproduccion(int idDetOrdProduccion, int idOrdenProduccion) {
        this.detordenesproduccionPK = new DetordenesproduccionPK(idDetOrdProduccion, idOrdenProduccion);
    }

    public DetordenesproduccionPK getDetordenesproduccionPK() {
        return detordenesproduccionPK;
    }

    public void setDetordenesproduccionPK(DetordenesproduccionPK detordenesproduccionPK) {
        this.detordenesproduccionPK = detordenesproduccionPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Ordenesproduccion getOrdenesproduccion() {
        return ordenesproduccion;
    }

    public void setOrdenesproduccion(Ordenesproduccion ordenesproduccion) {
        this.ordenesproduccion = ordenesproduccion;
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
        hash += (detordenesproduccionPK != null ? detordenesproduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detordenesproduccion)) {
            return false;
        }
        Detordenesproduccion other = (Detordenesproduccion) object;
        if ((this.detordenesproduccionPK == null && other.detordenesproduccionPK != null) || (this.detordenesproduccionPK != null && !this.detordenesproduccionPK.equals(other.detordenesproduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detordenesproduccion[detordenesproduccionPK=" + detordenesproduccionPK + "]";
    }

}
