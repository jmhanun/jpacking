/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ireport;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Pablo
 */
@Embeddable
public class RolesusuariosPK implements Serializable {
    @Column(name = "idUsuario", nullable = false)
    private int idUsuario;
    @Column(name = "idRol", nullable = false)
    private int idRol;

    public RolesusuariosPK() {
    }

    public RolesusuariosPK(int idUsuario, int idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesusuariosPK)) {
            return false;
        }
        RolesusuariosPK other = (RolesusuariosPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idRol != other.idRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ireport.RolesusuariosPK[idUsuario=" + idUsuario + ", idRol=" + idRol + "]";
    }

}
