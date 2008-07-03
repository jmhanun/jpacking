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
public class DetordenesproduccionPK implements Serializable {
    @Column(name = "idDetOrdProduccion", nullable = false)
    private int idDetOrdProduccion;
    @Column(name = "idOrdenProduccion", nullable = false)
    private int idOrdenProduccion;

    public DetordenesproduccionPK() {
    }

    public DetordenesproduccionPK(int idDetOrdProduccion, int idOrdenProduccion) {
        this.idDetOrdProduccion = idDetOrdProduccion;
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public int getIdDetOrdProduccion() {
        return idDetOrdProduccion;
    }

    public void setIdDetOrdProduccion(int idDetOrdProduccion) {
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(int idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetOrdProduccion;
        hash += (int) idOrdenProduccion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetordenesproduccionPK)) {
            return false;
        }
        DetordenesproduccionPK other = (DetordenesproduccionPK) object;
        if (this.idDetOrdProduccion != other.idDetOrdProduccion) {
            return false;
        }
        if (this.idOrdenProduccion != other.idOrdenProduccion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetordenesproduccionPK[idDetOrdProduccion=" + idDetOrdProduccion + ", idOrdenProduccion=" + idOrdenProduccion + "]";
    }

}
