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
@Table(name = "horastrabajadas")
@NamedQueries({
@NamedQuery(name = "Horastrabajadas.findByIdEmpleado", query = "SELECT h FROM Horastrabajadas h WHERE h.horastrabajadasPK.idEmpleado = :idEmpleado"),
@NamedQuery(name = "Horastrabajadas.findByIdActividad", query = "SELECT h FROM Horastrabajadas h WHERE h.horastrabajadasPK.idActividad = :idActividad"),
@NamedQuery(name = "Horastrabajadas.findByFecha", query = "SELECT h FROM Horastrabajadas h WHERE h.horastrabajadasPK.fecha = :fecha"),
@NamedQuery(name = "Horastrabajadas.findByHoras", query = "SELECT h FROM Horastrabajadas h WHERE h.horas = :horas"),
@NamedQuery(name = "Horastrabajadas.findByMinutos", query = "SELECT h FROM Horastrabajadas h WHERE h.minutos = :minutos")
})
public class HorasTrabajadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorasTrabajadasPK horastrabajadasPK;
    @Column(name = "horas", nullable = false)
    private Integer horas;
    @Column(name = "minutos", nullable = false)
    private Integer minutos;
    @JoinColumn(name = "idActividad", referencedColumnName = "idActividad", insertable = false, updatable = false)
    @ManyToOne
    private Actividades actividades;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado", insertable = false, updatable = false)
    @ManyToOne
    private Empleados empleados;
    @JoinColumn(name = "idMaquina", referencedColumnName = "idMaquina")
    @ManyToOne
    private Maquinas idMaquina;

    public HorasTrabajadas() {
    }

    public HorasTrabajadas(HorasTrabajadasPK horastrabajadasPK) {
        this.horastrabajadasPK = horastrabajadasPK;
    }

    public HorasTrabajadas(HorasTrabajadasPK horastrabajadasPK, Integer horas, Integer minutos) {
        this.horastrabajadasPK = horastrabajadasPK;
        this.horas = horas;
        this.minutos = minutos;
    }

    public HorasTrabajadas(Integer idEmpleado, Integer idActividad, Date fecha) {
        this.horastrabajadasPK = new HorasTrabajadasPK(idEmpleado, idActividad, fecha);
    }

    public HorasTrabajadasPK getHorastrabajadasPK() {
        return horastrabajadasPK;
    }

    public void setHorastrabajadasPK(HorasTrabajadasPK horastrabajadasPK) {
        this.horastrabajadasPK = horastrabajadasPK;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Maquinas getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Maquinas idMaquina) {
        this.idMaquina = idMaquina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horastrabajadasPK != null ? horastrabajadasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorasTrabajadas)) {
            return false;
        }
        HorasTrabajadas other = (HorasTrabajadas) object;
        if ((this.horastrabajadasPK == null && other.horastrabajadasPK != null) || (this.horastrabajadasPK != null && !this.horastrabajadasPK.equals(other.horastrabajadasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persist.Horastrabajadas[horastrabajadasPK=" + horastrabajadasPK + "]";
    }
}
