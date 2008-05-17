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
    private Integer idArticulo;
    @Column(name = "idComponente", nullable = false)
    private Integer idComponente;

    public ComponentesPK() {
    }

    public ComponentesPK(Integer idArticulo, Integer idComponente) {
        this.idArticulo = idArticulo;
        this.idComponente = idComponente;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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
