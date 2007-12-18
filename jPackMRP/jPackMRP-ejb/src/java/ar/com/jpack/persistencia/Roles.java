/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "roles")
@NamedQueries({@NamedQuery(name = "Roles.findByIdRol", query = "SELECT r FROM Roles r WHERE r.idRol = :idRol"), 
@NamedQuery(name = "Roles.findByRol", query = "SELECT r FROM Roles r WHERE r.rol = :rol"),
@NamedQuery(name = "Roles.obtenerRoles", query = "SELECT r FROM Roles r"),
@NamedQuery(name = "Roles.findByDescripcion", query = "SELECT r FROM Roles r WHERE r.descripcion = :descripcion")})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idRol", nullable = false)
    private Integer idRol;
    @Column(name = "rol", nullable = false)
    private String rol;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @JoinTable(name = "rolesusuarios", joinColumns = {@JoinColumn(name = "idRol", referencedColumnName = "idRol")}, inverseJoinColumns = {@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany
    private Collection<Usuarios> idUsuarioCollection;

    public Roles() {
    }

    public Roles(Integer idRol) {
        this.idRol = idRol;
    }

    public Roles(Integer idRol, String rol, String descripcion) {
        this.idRol = idRol;
        this.rol = rol;
        this.descripcion = descripcion;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Usuarios> getIdUsuarioCollection() {
        return idUsuarioCollection;
    }

    public void setIdUsuarioCollection(Collection<Usuarios> idUsuarioCollection) {
        this.idUsuarioCollection = idUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Roles[idRol=" + idRol + "]";
    }

}
