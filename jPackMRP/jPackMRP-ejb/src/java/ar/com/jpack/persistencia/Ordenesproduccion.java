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
import javax.persistence.FetchType;
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
@NamedQueries({@NamedQuery(name = "Ordenesproduccion.findByIdOrdenProduccion", query = "SELECT o FROM Ordenesproduccion o WHERE o.idOrdenProduccion = :idOrdenProduccion"), @NamedQuery(name = "Ordenesproduccion.findByNroOrdenProduccion", query = "SELECT o FROM Ordenesproduccion o WHERE o.nroOrdenProduccion = :nroOrdenProduccion"), @NamedQuery(name = "Ordenesproduccion.findByFecha", query = "SELECT o FROM Ordenesproduccion o WHERE o.fecha = :fecha"), @NamedQuery(name = "Ordenesproduccion.findByFechaModificacion", query = "SELECT o FROM Ordenesproduccion o WHERE o.fechaModificacion = :fechaModificacion"), @NamedQuery(name = "Ordenesproduccion.findByFechaInicioEstimada", query = "SELECT o FROM Ordenesproduccion o WHERE o.fechaInicioEstimada = :fechaInicioEstimada")})
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
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "fechaInicioEstimada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioEstimada;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @JoinColumn(name = "idPrioridad", referencedColumnName = "idPrioridad")
    @ManyToOne(fetch=FetchType.LAZY)
    private Prioridades idPrioridad;
    @JoinColumn(name = "idRemito", referencedColumnName = "idRemito")
    @ManyToOne(fetch = FetchType.LAZY)
    private Remitos idRemito;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tiposcomprobantes idTipoComprobante;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenesproduccion", fetch = FetchType.LAZY)
    private Collection<Detordenesproduccion> detordenesproduccionCollection;
    @OneToMany(mappedBy = "idOrdenProduccion", fetch = FetchType.LAZY)
    private Collection<Detmovimientosstock> detmovimientosstockCollection;

    public Ordenesproduccion() {
    }

    public Ordenesproduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public Ordenesproduccion(Integer idOrdenProduccion, int nroOrdenProduccion, Date fecha, Date fechaModificacion) {
        this.idOrdenProduccion = idOrdenProduccion;
        this.nroOrdenProduccion = nroOrdenProduccion;
        this.fecha = fecha;
        this.fechaModificacion = fechaModificacion;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaInicioEstimada() {
        return fechaInicioEstimada;
    }

    public void setFechaInicioEstimada(Date fechaInicioEstimada) {
        this.fechaInicioEstimada = fechaInicioEstimada;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Prioridades getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Prioridades idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public Remitos getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Remitos idRemito) {
        this.idRemito = idRemito;
    }

    public Tiposcomprobantes getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Tiposcomprobantes idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
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
