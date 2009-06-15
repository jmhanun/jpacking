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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "prioridades")
@NamedQueries({@NamedQuery(name = "Prioridades.findByIdPrioridad", query = "SELECT p FROM Prioridades p WHERE p.idPrioridad = :idPrioridad"), @NamedQuery(name = "Prioridades.findByDescripcion", query = "SELECT p FROM Prioridades p WHERE p.descripcion = :descripcion")})
public class Prioridades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idPrioridad", nullable = false)
    private Integer idPrioridad;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrioridad", fetch = FetchType.LAZY)
    private Collection<Ordenesproduccion> ordenesproduccionCollection;

    public Prioridades() {
    }

    public Prioridades(Integer idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public Prioridades(Integer idPrioridad, String descripcion) {
        this.idPrioridad = idPrioridad;
        this.descripcion = descripcion;
    }

    public Integer getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Integer idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Ordenesproduccion> getOrdenesproduccionCollection() {
        return ordenesproduccionCollection;
    }

    public void setOrdenesproduccionCollection(Collection<Ordenesproduccion> ordenesproduccionCollection) {
        this.ordenesproduccionCollection = ordenesproduccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrioridad != null ? idPrioridad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prioridades)) {
            return false;
        }
        Prioridades other = (Prioridades) object;
        if ((this.idPrioridad == null && other.idPrioridad != null) || (this.idPrioridad != null && !this.idPrioridad.equals(other.idPrioridad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Prioridades[idPrioridad=" + idPrioridad + "]";
    }
}
