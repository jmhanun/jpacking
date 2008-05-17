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
@Table(name = "tiposroles")
@NamedQueries({
@NamedQuery(name = "Tiposroles.findByIdTipoRol", query = "SELECT t FROM Tiposroles t WHERE t.idTipoRol = :idTipoRol"),
@NamedQuery(name = "Tiposroles.findByDescripcion", query = "SELECT t FROM Tiposroles t WHERE t.descripcion = :descripcion")
})
public class TiposRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTipoRol", nullable = false)
    private Integer idTipoRol;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoRol")
    private Collection<Roles> rolesCollection;

    public TiposRoles() {
    }

    public TiposRoles(Integer idTipoRol) {
        this.idTipoRol = idTipoRol;
    }

    public TiposRoles(Integer idTipoRol, String descripcion) {
        this.idTipoRol = idTipoRol;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoRol() {
        return idTipoRol;
    }

    public void setIdTipoRol(Integer idTipoRol) {
        this.idTipoRol = idTipoRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoRol != null ? idTipoRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposRoles)) {
            return false;
        }
        TiposRoles other = (TiposRoles) object;
        if ((this.idTipoRol == null && other.idTipoRol != null) || (this.idTipoRol != null && !this.idTipoRol.equals(other.idTipoRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persist.Tiposroles[idTipoRol=" + idTipoRol + "]";
    }
}
