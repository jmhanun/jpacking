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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "actividades")
@NamedQueries({@NamedQuery(name = "Actividades.findByIdActividad", query = "SELECT a FROM Actividades a WHERE a.idActividad = :idActividad"), @NamedQuery(name = "Actividades.findByDescripcion", query = "SELECT a FROM Actividades a WHERE a.descripcion = :descripcion")})
public class Actividades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idActividad", nullable = false)
    private Integer idActividad;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividades", fetch = FetchType.LAZY)
    private Collection<Actividadesxarticulos> actividadesxarticulosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActividad", fetch = FetchType.LAZY)
    private Collection<Maquinas> maquinasCollection;

    public Actividades() {
    }

    public Actividades(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Actividades(Integer idActividad, String descripcion) {
        this.idActividad = idActividad;
        this.descripcion = descripcion;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Actividadesxarticulos> getActividadesxarticulosCollection() {
        return actividadesxarticulosCollection;
    }

    public void setActividadesxarticulosCollection(Collection<Actividadesxarticulos> actividadesxarticulosCollection) {
        this.actividadesxarticulosCollection = actividadesxarticulosCollection;
    }

    public Collection<Maquinas> getMaquinasCollection() {
        return maquinasCollection;
    }

    public void setMaquinasCollection(Collection<Maquinas> maquinasCollection) {
        this.maquinasCollection = maquinasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Actividades[idActividad=" + idActividad + "]";
    }

}
