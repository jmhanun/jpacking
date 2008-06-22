/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jmhanun
 */
@Embeddable
public class DetrtosingresoPK implements Serializable {
    @Column(name = "idDetRtoIngreso", nullable = false)
    private int idDetRtoIngreso;
    @Column(name = "idRtoIngreso", nullable = false)
    private int idRtoIngreso;

    public DetrtosingresoPK() {
    }

    public DetrtosingresoPK(int idDetRtoIngreso, int idRtoIngreso) {
        this.idDetRtoIngreso = idDetRtoIngreso;
        this.idRtoIngreso = idRtoIngreso;
    }

    public int getIdDetRtoIngreso() {
        return idDetRtoIngreso;
    }

    public void setIdDetRtoIngreso(int idDetRtoIngreso) {
        this.idDetRtoIngreso = idDetRtoIngreso;
    }

    public int getIdRtoIngreso() {
        return idRtoIngreso;
    }

    public void setIdRtoIngreso(int idRtoIngreso) {
        this.idRtoIngreso = idRtoIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetRtoIngreso;
        hash += (int) idRtoIngreso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetrtosingresoPK)) {
            return false;
        }
        DetrtosingresoPK other = (DetrtosingresoPK) object;
        if (this.idDetRtoIngreso != other.idDetRtoIngreso) {
            return false;
        }
        if (this.idRtoIngreso != other.idRtoIngreso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetrtosingresoPK[idDetRtoIngreso=" + idDetRtoIngreso + ", idRtoIngreso=" + idRtoIngreso + "]";
    }

}
