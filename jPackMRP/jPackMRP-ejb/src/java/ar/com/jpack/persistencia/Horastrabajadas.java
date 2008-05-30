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
@NamedQueries({@NamedQuery(name = "Horastrabajadas.findByIdEmpleado", query = "SELECT h FROM Horastrabajadas h WHERE h.horastrabajadasPK.idEmpleado = :idEmpleado"), @NamedQuery(name = "Horastrabajadas.findByIdActividad", query = "SELECT h FROM Horastrabajadas h WHERE h.horastrabajadasPK.idActividad = :idActividad"), @NamedQuery(name = "Horastrabajadas.findByFecha", query = "SELECT h FROM Horastrabajadas h WHERE h.horastrabajadasPK.fecha = :fecha"), @NamedQuery(name = "Horastrabajadas.findByHoras", query = "SELECT h FROM Horastrabajadas h WHERE h.horas = :horas"), @NamedQuery(name = "Horastrabajadas.findByMinutos", query = "SELECT h FROM Horastrabajadas h WHERE h.minutos = :minutos"), @NamedQuery(name = "Horastrabajadas.findByUnidades", query = "SELECT h FROM Horastrabajadas h WHERE h.unidades = :unidades")})
public class Horastrabajadas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorastrabajadasPK horastrabajadasPK;
    @Column(name = "horas", nullable = false)
    private int horas;
    @Column(name = "minutos", nullable = false)
    private int minutos;
    @Column(name = "unidades", nullable = false)
    private int unidades;
    @JoinColumn(name = "idActividad", referencedColumnName = "idActividad", insertable = false, updatable = false)
    @ManyToOne
    private Actividades actividades;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado", insertable = false, updatable = false)
    @ManyToOne
    private Empleados empleados;
    @JoinColumn(name = "idMaquina", referencedColumnName = "idMaquina")
    @ManyToOne
    private Maquinas idMaquina;

    public Horastrabajadas() {
    }

    public Horastrabajadas(HorastrabajadasPK horastrabajadasPK) {
        this.horastrabajadasPK = horastrabajadasPK;
    }

    public Horastrabajadas(HorastrabajadasPK horastrabajadasPK, int horas, int minutos, int unidades) {
        this.horastrabajadasPK = horastrabajadasPK;
        this.horas = horas;
        this.minutos = minutos;
        this.unidades = unidades;
    }

    public Horastrabajadas(int idEmpleado, int idActividad, Date fecha) {
        this.horastrabajadasPK = new HorastrabajadasPK(idEmpleado, idActividad, fecha);
    }

    public HorastrabajadasPK getHorastrabajadasPK() {
        return horastrabajadasPK;
    }

    public void setHorastrabajadasPK(HorastrabajadasPK horastrabajadasPK) {
        this.horastrabajadasPK = horastrabajadasPK;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
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
        if (!(object instanceof Horastrabajadas)) {
            return false;
        }
        Horastrabajadas other = (Horastrabajadas) object;
        if ((this.horastrabajadasPK == null && other.horastrabajadasPK != null) || (this.horastrabajadasPK != null && !this.horastrabajadasPK.equals(other.horastrabajadasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Horastrabajadas[horastrabajadasPK=" + horastrabajadasPK + "]";
    }

}
