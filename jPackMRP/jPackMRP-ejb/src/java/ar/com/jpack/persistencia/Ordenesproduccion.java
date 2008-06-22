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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ordenesproduccion")
@NamedQueries({@NamedQuery(name = "Ordenesproduccion.findByIdOrdenProduccion", query = "SELECT o FROM Ordenesproduccion o WHERE o.idOrdenProduccion = :idOrdenProduccion"), @NamedQuery(name = "Ordenesproduccion.findByNroOrdenProduccion", query = "SELECT o FROM Ordenesproduccion o WHERE o.nroOrdenProduccion = :nroOrdenProduccion"), @NamedQuery(name = "Ordenesproduccion.findByFecha", query = "SELECT o FROM Ordenesproduccion o WHERE o.fecha = :fecha")})
public class Ordenesproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idOrdenProduccion", nullable = false)
    private Integer idOrdenProduccion;
    @Column(name = "nroOrdenProduccion", nullable = false)
    private int nroOrdenProduccion;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne
    private Tiposcomprobantes idTipoComprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenesproduccion")
    private Collection<Detordenesproduccion> detordenesproduccionCollection;
    @OneToMany(mappedBy = "idOrdenProduccion")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;

    public Ordenesproduccion() {
    }

    public Ordenesproduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public Ordenesproduccion(Integer idOrdenProduccion, int nroOrdenProduccion, Date fecha) {
        this.idOrdenProduccion = idOrdenProduccion;
        this.nroOrdenProduccion = nroOrdenProduccion;
        this.fecha = fecha;
    }

    public Integer getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public int getNroOrdenProduccion() {
        return nroOrdenProduccion;
    }

    public void setNroOrdenProduccion(int nroOrdenProduccion) {
        this.nroOrdenProduccion = nroOrdenProduccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Tiposcomprobantes getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Tiposcomprobantes idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Collection<Detordenesproduccion> getDetordenesproduccionCollection() {
        return detordenesproduccionCollection;
    }

    public void setDetordenesproduccionCollection(Collection<Detordenesproduccion> detordenesproduccionCollection) {
        this.detordenesproduccionCollection = detordenesproduccionCollection;
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
        hash += (idOrdenProduccion != null ? idOrdenProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenesproduccion)) {
            return false;
        }
        Ordenesproduccion other = (Ordenesproduccion) object;
        if ((this.idOrdenProduccion == null && other.idOrdenProduccion != null) || (this.idOrdenProduccion != null && !this.idOrdenProduccion.equals(other.idOrdenProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Ordenesproduccion[idOrdenProduccion=" + idOrdenProduccion + "]";
    }

}
