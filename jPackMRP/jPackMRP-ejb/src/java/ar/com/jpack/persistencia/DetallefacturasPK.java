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
public class DetallefacturasPK implements Serializable {
    @Column(name = "idDetalle", nullable = false)
    private int idDetalle;
    @Column(name = "idFactura", nullable = false)
    private int idFactura;

    public DetallefacturasPK() {
    }

    public DetallefacturasPK(int idDetalle, int idFactura) {
        this.idDetalle = idDetalle;
        this.idFactura = idFactura;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetalle;
        hash += (int) idFactura;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallefacturasPK)) {
            return false;
        }
        DetallefacturasPK other = (DetallefacturasPK) object;
        if (this.idDetalle != other.idDetalle) {
            return false;
        }
        if (this.idFactura != other.idFactura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetallefacturasPK[idDetalle=" + idDetalle + ", idFactura=" + idFactura + "]";
    }

}
