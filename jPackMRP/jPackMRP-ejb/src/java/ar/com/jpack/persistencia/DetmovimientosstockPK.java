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
public class DetmovimientosstockPK implements Serializable {
    @Column(name = "idDetMovStock", nullable = false)
    private int idDetMovStock;
    @Column(name = "idMovStock", nullable = false)
    private int idMovStock;

    public DetmovimientosstockPK() {
    }

    public DetmovimientosstockPK(int idDetMovStock, int idMovStock) {
        this.idDetMovStock = idDetMovStock;
        this.idMovStock = idMovStock;
    }

    public int getIdDetMovStock() {
        return idDetMovStock;
    }

    public void setIdDetMovStock(int idDetMovStock) {
        this.idDetMovStock = idDetMovStock;
    }

    public int getIdMovStock() {
        return idMovStock;
    }

    public void setIdMovStock(int idMovStock) {
        this.idMovStock = idMovStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetMovStock;
        hash += (int) idMovStock;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetmovimientosstockPK)) {
            return false;
        }
        DetmovimientosstockPK other = (DetmovimientosstockPK) object;
        if (this.idDetMovStock != other.idDetMovStock) {
            return false;
        }
        if (this.idMovStock != other.idMovStock) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetmovimientosstockPK[idDetMovStock=" + idDetMovStock + ", idMovStock=" + idMovStock + "]";
    }

}
