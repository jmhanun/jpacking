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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "sellosxdetorden")
@NamedQueries({@NamedQuery(name = "Sellosxdetorden.findByIdSello", query = "SELECT s FROM Sellosxdetorden s WHERE s.sellosxdetordenPK.idSello = :idSello"), @NamedQuery(name = "Sellosxdetorden.findByIdCliente", query = "SELECT s FROM Sellosxdetorden s WHERE s.sellosxdetordenPK.idCliente = :idCliente"), @NamedQuery(name = "Sellosxdetorden.findByIdOrdenProduccion", query = "SELECT s FROM Sellosxdetorden s WHERE s.sellosxdetordenPK.idOrdenProduccion = :idOrdenProduccion"), @NamedQuery(name = "Sellosxdetorden.findByIdDetOrdProduccion", query = "SELECT s FROM Sellosxdetorden s WHERE s.sellosxdetordenPK.idDetOrdProduccion = :idDetOrdProduccion"), @NamedQuery(name = "Sellosxdetorden.findByOrden", query = "SELECT s FROM Sellosxdetorden s WHERE s.orden = :orden")})
public class Sellosxdetorden implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SellosxdetordenPK sellosxdetordenPK;
    @Column(name = "orden", nullable = false)
    private int orden;
    @JoinColumns({@JoinColumn(name = "idDetOrdProduccion", referencedColumnName = "idDetOrdProduccion", insertable = false, updatable = false), @JoinColumn(name = "idOrdenProduccion", referencedColumnName = "idOrdenProduccion", insertable = false, updatable = false)})
    @ManyToOne(fetch = FetchType.LAZY)
    private Detordenesproduccion detordenesproduccion;

    public Sellosxdetorden() {
    }

    public Sellosxdetorden(SellosxdetordenPK sellosxdetordenPK) {
        this.sellosxdetordenPK = sellosxdetordenPK;
    }

    public Sellosxdetorden(SellosxdetordenPK sellosxdetordenPK, int orden) {
        this.sellosxdetordenPK = sellosxdetordenPK;
        this.orden = orden;
    }

    public Sellosxdetorden(int idSello, int idCliente, int idOrdenProduccion, int idDetOrdProduccion) {
        this.sellosxdetordenPK = new SellosxdetordenPK(idSello, idCliente, idOrdenProduccion, idDetOrdProduccion);
    }

    public SellosxdetordenPK getSellosxdetordenPK() {
        return sellosxdetordenPK;
    }

    public void setSellosxdetordenPK(SellosxdetordenPK sellosxdetordenPK) {
        this.sellosxdetordenPK = sellosxdetordenPK;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Detordenesproduccion getDetordenesproduccion() {
        return detordenesproduccion;
    }

    public void setDetordenesproduccion(Detordenesproduccion detordenesproduccion) {
        this.detordenesproduccion = detordenesproduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sellosxdetordenPK != null ? sellosxdetordenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sellosxdetorden)) {
            return false;
        }
        Sellosxdetorden other = (Sellosxdetorden) object;
        if ((this.sellosxdetordenPK == null && other.sellosxdetordenPK != null) || (this.sellosxdetordenPK != null && !this.sellosxdetordenPK.equals(other.sellosxdetordenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Sellosxdetorden[sellosxdetordenPK=" + sellosxdetordenPK + "]";
    }

}
