/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "mantenimiento")
@NamedQueries({@NamedQuery(name = "Mantenimiento.findByIdMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.idMantenimiento = :idMantenimiento"), @NamedQuery(name = "Mantenimiento.findByDescripcion", query = "SELECT m FROM Mantenimiento m WHERE m.descripcion = :descripcion"), @NamedQuery(name = "Mantenimiento.findByFechaInicio", query = "SELECT m FROM Mantenimiento m WHERE m.fechaInicio = :fechaInicio"), @NamedQuery(name = "Mantenimiento.findByFechaFin", query = "SELECT m FROM Mantenimiento m WHERE m.fechaFin = :fechaFin")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMantenimiento", nullable = false)
    private Integer idMantenimiento;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "fechaInicio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @JoinColumn(name = "idMaquina", referencedColumnName = "idMaquina")
    @ManyToOne(fetch = FetchType.LAZY)
    private Maquinas idMaquina;
    @JoinColumn(name = "idTipoServicio", referencedColumnName = "idTipoServicio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tiposservicios idTipoServicio;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Mantenimiento(Integer idMantenimiento, String descripcion, Date fechaInicio) {
        this.idMantenimiento = idMantenimiento;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Maquinas getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Maquinas idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Tiposservicios getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Tiposservicios idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMantenimiento != null ? idMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.idMantenimiento == null && other.idMantenimiento != null) || (this.idMantenimiento != null && !this.idMantenimiento.equals(other.idMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Mantenimiento[idMantenimiento=" + idMantenimiento + "]";
    }
}
