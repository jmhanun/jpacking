/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "roles")
@NamedQueries({@NamedQuery(name = "Roles.findByIdRol", query = "SELECT r FROM Roles r WHERE r.idRol = :idRol"), @NamedQuery(name = "Roles.findByRol", query = "SELECT r FROM Roles r WHERE r.rol = :rol"), @NamedQuery(name = "Roles.findByDescripcion", query = "SELECT r FROM Roles r WHERE r.descripcion = :descripcion"), @NamedQuery(name = "Roles.findByComponente", query = "SELECT r FROM Roles r WHERE r.componente = :componente"), @NamedQuery(name = "Roles.findByFuncion", query = "SELECT r FROM Roles r WHERE r.funcion = :funcion"), @NamedQuery(name = "Roles.findByOrden", query = "SELECT r FROM Roles r WHERE r.orden = :orden"), @NamedQuery(name = "Roles.findByOrdenHermano", query = "SELECT r FROM Roles r WHERE r.ordenHermano = :ordenHermano")})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idRol", nullable = false)
    private Integer idRol;
    @Column(name = "rol", nullable = false)
    private String rol;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "componente")
    private String componente;
    @Column(name = "funcion")
    private String funcion;
    @Column(name = "orden", nullable = false)
    private int orden;
    @Column(name = "ordenHermano", nullable = false)
    private int ordenHermano;

    @ManyToMany(mappedBy="idRolCollection", fetch = FetchType.LAZY)
    private Collection<Usuarios> idUsuarioCollection;
    @OneToMany(mappedBy = "idRolPadre", fetch = FetchType.LAZY)
    private Collection<Roles> rolesCollection;
    @JoinColumn(name = "idRolPadre", referencedColumnName = "idRol")
    @ManyToOne(fetch = FetchType.LAZY)
    private Roles idRolPadre;

    public Roles() {
    }

    public Roles(Integer idRol) {
        this.idRol = idRol;
    }

    public Roles(Integer idRol, String rol, String descripcion, int orden, int ordenHermano) {
        this.idRol = idRol;
        this.rol = rol;
        this.descripcion = descripcion;
        this.orden = orden;
        this.ordenHermano = ordenHermano;
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

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrdenHermano() {
        return ordenHermano;
    }

    public void setOrdenHermano(int ordenHermano) {
        this.ordenHermano = ordenHermano;
    }

    public Collection<Usuarios> getIdUsuarioCollection() {
        return idUsuarioCollection;
    }

    public void setIdUsuarioCollection(Collection<Usuarios> idUsuarioCollection) {
        this.idUsuarioCollection = idUsuarioCollection;
    }

    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    public Roles getIdRolPadre() {
        return idRolPadre;
    }

    public void setIdRolPadre(Roles idRolPadre) {
        this.idRolPadre = idRolPadre;
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
