/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "detalleproduccionreal")
@NamedQueries({@NamedQuery(name = "Detalleproduccionreal.findByIdDetalleProduccionReal", query = "SELECT d FROM Detalleproduccionreal d WHERE d.detalleproduccionrealPK.idDetalleProduccionReal = :idDetalleProduccionReal"), @NamedQuery(name = "Detalleproduccionreal.findByIdOrdenProduccion", query = "SELECT d FROM Detalleproduccionreal d WHERE d.detalleproduccionrealPK.idOrdenProduccion = :idOrdenProduccion"), @NamedQuery(name = "Detalleproduccionreal.findByIdMaquina", query = "SELECT d FROM Detalleproduccionreal d WHERE d.detalleproduccionrealPK.idMaquina = :idMaquina"), @NamedQuery(name = "Detalleproduccionreal.findByDuracion", query = "SELECT d FROM Detalleproduccionreal d WHERE d.duracion = :duracion"), @NamedQuery(name = "Detalleproduccionreal.findByIdDetOrdProduccion", query = "SELECT d FROM Detalleproduccionreal d WHERE d.detalleproduccionrealPK.idDetOrdProduccion = :idDetOrdProduccion"), @NamedQuery(name = "Detalleproduccionreal.findByPrioridad", query = "SELECT d FROM Detalleproduccionreal d WHERE d.prioridad = :prioridad"), @NamedQuery(name = "Detalleproduccionreal.findByFechaInicioReal", query = "SELECT d FROM Detalleproduccionreal d WHERE d.fechaInicioReal = :fechaInicioReal"), @NamedQuery(name = "Detalleproduccionreal.findByFechaFinReal", query = "SELECT d FROM Detalleproduccionreal d WHERE d.fechaFinReal = :fechaFinReal")})
public class Detalleproduccionreal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleproduccionrealPK detalleproduccionrealPK;
    @Column(name = "duracion", nullable = false)
    private float duracion;
    @Column(name = "prioridad", nullable = false)
    private int prioridad;
    @Column(name = "fechaInicioReal", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioReal;
    @Column(name = "fechaFinReal", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinReal;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @JoinColumn(name = "idMaquina", referencedColumnName = "idMaquina", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Maquinas maquinas;
    @JoinColumns({@JoinColumn(name = "idDetOrdProduccion", referencedColumnName = "idDetOrdProduccion", insertable = false, updatable = false), @JoinColumn(name = "idOrdenProduccion", referencedColumnName = "idOrdenProduccion", insertable = false, updatable = false)})
    @ManyToOne(fetch = FetchType.LAZY)
    private Detordenesproduccion detordenesproduccion;

    public Detalleproduccionreal() {
    }

    public Detalleproduccionreal(DetalleproduccionrealPK detalleproduccionrealPK) {
        this.detalleproduccionrealPK = detalleproduccionrealPK;
    }

    public Detalleproduccionreal(DetalleproduccionrealPK detalleproduccionrealPK, float duracion, int prioridad, Date fechaInicioReal, Date fechaFinReal) {
        this.detalleproduccionrealPK = detalleproduccionrealPK;
        this.duracion = duracion;
        this.prioridad = prioridad;
        this.fechaInicioReal = fechaInicioReal;
        this.fechaFinReal = fechaFinReal;
    }

    public Detalleproduccionreal(int idDetalleProduccionReal, int idOrdenProduccion, int idMaquina, int idDetOrdProduccion) {
        this.detalleproduccionrealPK = new DetalleproduccionrealPK(idDetalleProduccionReal, idOrdenProduccion, idMaquina, idDetOrdProduccion);
    }

    public DetalleproduccionrealPK getDetalleproduccionrealPK() {
        return detalleproduccionrealPK;
    }

    public void setDetalleproduccionrealPK(DetalleproduccionrealPK detalleproduccionrealPK) {
        this.detalleproduccionrealPK = detalleproduccionrealPK;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(Date fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Maquinas getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(Maquinas maquinas) {
        this.maquinas = maquinas;
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
        hash += (detalleproduccionrealPK != null ? detalleproduccionrealPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleproduccionreal)) {
            return false;
        }
        Detalleproduccionreal other = (Detalleproduccionreal) object;
        if ((this.detalleproduccionrealPK == null && other.detalleproduccionrealPK != null) || (this.detalleproduccionrealPK != null && !this.detalleproduccionrealPK.equals(other.detalleproduccionrealPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detalleproduccionreal[detalleproduccionrealPK=" + detalleproduccionrealPK + "]";
    }
}
