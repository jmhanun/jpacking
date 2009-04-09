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
@Table(name = "detalleproduccion")
@NamedQueries({@NamedQuery(name = "Detalleproduccion.findByIdDetalleProduccion", query = "SELECT d FROM Detalleproduccion d WHERE d.detalleproduccionPK.idDetalleProduccion = :idDetalleProduccion"), @NamedQuery(name = "Detalleproduccion.findByIdOrdenProduccion", query = "SELECT d FROM Detalleproduccion d WHERE d.detalleproduccionPK.idOrdenProduccion = :idOrdenProduccion"), @NamedQuery(name = "Detalleproduccion.findByIdMaquina", query = "SELECT d FROM Detalleproduccion d WHERE d.detalleproduccionPK.idMaquina = :idMaquina"), @NamedQuery(name = "Detalleproduccion.findByDuracion", query = "SELECT d FROM Detalleproduccion d WHERE d.duracion = :duracion"), @NamedQuery(name = "Detalleproduccion.findByIdDetOrdProduccion", query = "SELECT d FROM Detalleproduccion d WHERE d.detalleproduccionPK.idDetOrdProduccion = :idDetOrdProduccion"), @NamedQuery(name = "Detalleproduccion.findByPrioridad", query = "SELECT d FROM Detalleproduccion d WHERE d.prioridad = :prioridad")})
public class Detalleproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleproduccionPK detalleproduccionPK;
    @Column(name = "duracion", nullable = false)
    private float duracion;
    @Column(name = "prioridad", nullable = false)
    private int prioridad;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @JoinColumn(name = "idMaquina", referencedColumnName = "idMaquina", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Maquinas maquinas;
    @JoinColumns({@JoinColumn(name = "idDetOrdProduccion", referencedColumnName = "idDetOrdProduccion", insertable = false, updatable = false), @JoinColumn(name = "idOrdenProduccion", referencedColumnName = "idOrdenProduccion", insertable = false, updatable = false)})
    @ManyToOne(fetch = FetchType.LAZY)
    private Detordenesproduccion detordenesproduccion;

    public Detalleproduccion() {
    }

    public Detalleproduccion(DetalleproduccionPK detalleproduccionPK) {
        this.detalleproduccionPK = detalleproduccionPK;
    }

    public Detalleproduccion(DetalleproduccionPK detalleproduccionPK, float duracion, int prioridad) {
        this.detalleproduccionPK = detalleproduccionPK;
        this.duracion = duracion;
        this.prioridad = prioridad;
    }

    public Detalleproduccion(int idDetalleProduccion, int idOrdenProduccion, int idMaquina, int idDetOrdProduccion) {
        this.detalleproduccionPK = new DetalleproduccionPK(idDetalleProduccion, idOrdenProduccion, idMaquina, idDetOrdProduccion);
    }

    public DetalleproduccionPK getDetalleproduccionPK() {
        return detalleproduccionPK;
    }

    public void setDetalleproduccionPK(DetalleproduccionPK detalleproduccionPK) {
        this.detalleproduccionPK = detalleproduccionPK;
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
        hash += (detalleproduccionPK != null ? detalleproduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleproduccion)) {
            return false;
        }
        Detalleproduccion other = (Detalleproduccion) object;
        if ((this.detalleproduccionPK == null && other.detalleproduccionPK != null) || (this.detalleproduccionPK != null && !this.detalleproduccionPK.equals(other.detalleproduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detalleproduccion[detalleproduccionPK=" + detalleproduccionPK + "]";
    }

}
