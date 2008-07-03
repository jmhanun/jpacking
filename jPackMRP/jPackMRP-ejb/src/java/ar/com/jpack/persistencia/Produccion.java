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
@Table(name = "produccion")
@NamedQueries({@NamedQuery(name = "Produccion.findByIdEmpleado", query = "SELECT p FROM Produccion p WHERE p.produccionPK.idEmpleado = :idEmpleado"), @NamedQuery(name = "Produccion.findByFecha", query = "SELECT p FROM Produccion p WHERE p.produccionPK.fecha = :fecha"), @NamedQuery(name = "Produccion.findByUnidades", query = "SELECT p FROM Produccion p WHERE p.unidades = :unidades")})
public class Produccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProduccionPK produccionPK;
    @Column(name = "unidades", nullable = false)
    private int unidades;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado", insertable = false, updatable = false)
    @ManyToOne
    private Empleados empleados;
    @JoinColumn(name = "idMaquina", referencedColumnName = "idMaquina")
    @ManyToOne
    private Maquinas idMaquina;

    public Produccion() {
    }

    public Produccion(ProduccionPK produccionPK) {
        this.produccionPK = produccionPK;
    }

    public Produccion(ProduccionPK produccionPK, int unidades) {
        this.produccionPK = produccionPK;
        this.unidades = unidades;
    }

    public Produccion(int idEmpleado, Date fecha) {
        this.produccionPK = new ProduccionPK(idEmpleado, fecha);
    }

    public ProduccionPK getProduccionPK() {
        return produccionPK;
    }

    public void setProduccionPK(ProduccionPK produccionPK) {
        this.produccionPK = produccionPK;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
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
        hash += (produccionPK != null ? produccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produccion)) {
            return false;
        }
        Produccion other = (Produccion) object;
        if ((this.produccionPK == null && other.produccionPK != null) || (this.produccionPK != null && !this.produccionPK.equals(other.produccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Produccion[produccionPK=" + produccionPK + "]";
    }

}
