/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmhanun
 */
@Embeddable
public class HorasTrabajadasPK implements Serializable {
    @Column(name = "idEmpleado", nullable = false)
    private Integer idEmpleado;
    @Column(name = "idActividad", nullable = false)
    private Integer idActividad;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public HorasTrabajadasPK() {
    }

    public HorasTrabajadasPK(Integer idEmpleado, Integer idActividad, Date fecha) {
        this.idEmpleado = idEmpleado;
        this.idActividad = idActividad;
        this.fecha = fecha;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmpleado;
        hash += (int) idActividad;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorasTrabajadasPK)) {
            return false;
        }
        HorasTrabajadasPK other = (HorasTrabajadasPK) object;
        if (this.idEmpleado != other.idEmpleado) {
            return false;
        }
        if (this.idActividad != other.idActividad) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persist.HorastrabajadasPK[idEmpleado=" + idEmpleado + ", idActividad=" + idActividad + ", fecha=" + fecha + "]";
    }

}
