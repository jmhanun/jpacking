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
public class DetalleordenescomprasPK implements Serializable {
    @Column(name = "idDetOrdenCompra", nullable = false)
    private int idDetOrdenCompra;
    @Column(name = "idOrdenCompra", nullable = false)
    private int idOrdenCompra;

    public DetalleordenescomprasPK() {
    }

    public DetalleordenescomprasPK(int idDetOrdenCompra, int idOrdenCompra) {
        this.idDetOrdenCompra = idDetOrdenCompra;
        this.idOrdenCompra = idOrdenCompra;
    }

    public int getIdDetOrdenCompra() {
        return idDetOrdenCompra;
    }

    public void setIdDetOrdenCompra(int idDetOrdenCompra) {
        this.idDetOrdenCompra = idDetOrdenCompra;
    }

    public int getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(int idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetOrdenCompra;
        hash += (int) idOrdenCompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleordenescomprasPK)) {
            return false;
        }
        DetalleordenescomprasPK other = (DetalleordenescomprasPK) object;
        if (this.idDetOrdenCompra != other.idDetOrdenCompra) {
            return false;
        }
        if (this.idOrdenCompra != other.idOrdenCompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetalleordenescomprasPK[idDetOrdenCompra=" + idDetOrdenCompra + ", idOrdenCompra=" + idOrdenCompra + "]";
    }

}
