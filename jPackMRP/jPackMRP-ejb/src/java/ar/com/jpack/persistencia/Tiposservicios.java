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
@Table(name = "tiposservicios")
@NamedQueries({@NamedQuery(name = "Tiposservicios.findByIdTipoServicio", query = "SELECT t FROM Tiposservicios t WHERE t.idTipoServicio = :idTipoServicio"), @NamedQuery(name = "Tiposservicios.findByDescripcion", query = "SELECT t FROM Tiposservicios t WHERE t.descripcion = :descripcion")})
public class Tiposservicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTipoServicio", nullable = false)
    private Integer idTipoServicio;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoServicio", fetch = FetchType.LAZY)
    private Collection<Mantenimiento> mantenimientoCollection;

    public Tiposservicios() {
    }

    public Tiposservicios(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Tiposservicios(Integer idTipoServicio, String descripcion) {
        this.idTipoServicio = idTipoServicio;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Mantenimiento> getMantenimientoCollection() {
        return mantenimientoCollection;
    }

    public void setMantenimientoCollection(Collection<Mantenimiento> mantenimientoCollection) {
        this.mantenimientoCollection = mantenimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoServicio != null ? idTipoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposservicios)) {
            return false;
        }
        Tiposservicios other = (Tiposservicios) object;
        if ((this.idTipoServicio == null && other.idTipoServicio != null) || (this.idTipoServicio != null && !this.idTipoServicio.equals(other.idTipoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Tiposservicios[idTipoServicio=" + idTipoServicio + "]";
    }
}
