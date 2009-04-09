/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "maquinas")
@NamedQueries({@NamedQuery(name = "Maquinas.findByIdMaquina", query = "SELECT m FROM Maquinas m WHERE m.idMaquina = :idMaquina"), @NamedQuery(name = "Maquinas.findByDescripcion", query = "SELECT m FROM Maquinas m WHERE m.descripcion = :descripcion")})
public class Maquinas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idMaquina", nullable = false)
    private Integer idMaquina;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @JoinColumn(name = "idActividad", referencedColumnName = "idActividad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actividades idActividad;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maquinas", fetch = FetchType.LAZY)
    private Collection<Detalleproduccion> detalleproduccionCollection;

    public Maquinas() {
    }

    public Maquinas(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Maquinas(Integer idMaquina, String descripcion) {
        this.idMaquina = idMaquina;
        this.descripcion = descripcion;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Actividades getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Actividades idActividad) {
        this.idActividad = idActividad;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Collection<Detalleproduccion> getDetalleproduccionCollection() {
        return detalleproduccionCollection;
    }

    public void setDetalleproduccionCollection(Collection<Detalleproduccion> detalleproduccionCollection) {
        this.detalleproduccionCollection = detalleproduccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaquina != null ? idMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquinas)) {
            return false;
        }
        Maquinas other = (Maquinas) object;
        if ((this.idMaquina == null && other.idMaquina != null) || (this.idMaquina != null && !this.idMaquina.equals(other.idMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Maquinas[idMaquina=" + idMaquina + "]";
    }

}
