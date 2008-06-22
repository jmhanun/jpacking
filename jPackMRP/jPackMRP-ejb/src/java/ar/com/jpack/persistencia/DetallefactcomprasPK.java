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
public class DetallefactcomprasPK implements Serializable {
    @Column(name = "idDetFactCompras", nullable = false)
    private int idDetFactCompras;
    @Column(name = "idFactCompra", nullable = false)
    private int idFactCompra;

    public DetallefactcomprasPK() {
    }

    public DetallefactcomprasPK(int idDetFactCompras, int idFactCompra) {
        this.idDetFactCompras = idDetFactCompras;
        this.idFactCompra = idFactCompra;
    }

    public int getIdDetFactCompras() {
        return idDetFactCompras;
    }

    public void setIdDetFactCompras(int idDetFactCompras) {
        this.idDetFactCompras = idDetFactCompras;
    }

    public int getIdFactCompra() {
        return idFactCompra;
    }

    public void setIdFactCompra(int idFactCompra) {
        this.idFactCompra = idFactCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetFactCompras;
        hash += (int) idFactCompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallefactcomprasPK)) {
            return false;
        }
        DetallefactcomprasPK other = (DetallefactcomprasPK) object;
        if (this.idDetFactCompras != other.idDetFactCompras) {
            return false;
        }
        if (this.idFactCompra != other.idFactCompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetallefactcomprasPK[idDetFactCompras=" + idDetFactCompras + ", idFactCompra=" + idFactCompra + "]";
    }

}
