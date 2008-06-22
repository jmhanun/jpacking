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
public class ComponentesPK implements Serializable {
    @Column(name = "idArticulo", nullable = false)
    private int idArticulo;
    @Column(name = "idComponente", nullable = false)
    private int idComponente;

    public ComponentesPK() {
    }

    public ComponentesPK(int idArticulo, int idComponente) {
        this.idArticulo = idArticulo;
        this.idComponente = idComponente;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArticulo;
        hash += (int) idComponente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComponentesPK)) {
            return false;
        }
        ComponentesPK other = (ComponentesPK) object;
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        if (this.idComponente != other.idComponente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.ComponentesPK[idArticulo=" + idArticulo + ", idComponente=" + idComponente + "]";
    }

}
