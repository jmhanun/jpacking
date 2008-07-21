/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ireport;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "rolesusuarios",schema = "")
@NamedQueries({@NamedQuery(name = "Rolesusuarios.findByIdUsuario", query = "SELECT r FROM Rolesusuarios r WHERE r.rolesusuariosPK.idUsuario = :idUsuario"), @NamedQuery(name = "Rolesusuarios.findByIdRol", query = "SELECT r FROM Rolesusuarios r WHERE r.rolesusuariosPK.idRol = :idRol")})
public class Rolesusuarios implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesusuariosPK rolesusuariosPK;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne
    private Usuarios usuarios;

    public Rolesusuarios() {
    }

    public Rolesusuarios(RolesusuariosPK rolesusuariosPK) {
        this.rolesusuariosPK = rolesusuariosPK;
    }

    public Rolesusuarios(int idUsuario, int idRol) {
        this.rolesusuariosPK = new RolesusuariosPK(idUsuario, idRol);
    }

    public RolesusuariosPK getRolesusuariosPK() {
        return rolesusuariosPK;
    }

    public void setRolesusuariosPK(RolesusuariosPK rolesusuariosPK) {
        this.rolesusuariosPK = rolesusuariosPK;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        Usuarios oldUsuarios = this.usuarios;
        this.usuarios = usuarios;
        changeSupport.firePropertyChange("usuarios", oldUsuarios, usuarios);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesusuariosPK != null ? rolesusuariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolesusuarios)) {
            return false;
        }
        Rolesusuarios other = (Rolesusuarios) object;
        if ((this.rolesusuariosPK == null && other.rolesusuariosPK != null) || (this.rolesusuariosPK != null && !this.rolesusuariosPK.equals(other.rolesusuariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ireport.Rolesusuarios[rolesusuariosPK=" + rolesusuariosPK + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
