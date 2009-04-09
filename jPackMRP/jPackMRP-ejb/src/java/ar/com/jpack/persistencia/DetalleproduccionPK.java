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
public class DetalleproduccionPK implements Serializable {
    @Column(name = "idDetalleProduccion", nullable = false)
    private int idDetalleProduccion;
    @Column(name = "idOrdenProduccion", nullable = false)
    private int idOrdenProduccion;
    @Column(name = "idMaquina", nullable = false)
    private int idMaquina;
    @Column(name = "idDetOrdProduccion", nullable = false)
    private int idDetOrdProduccion;

    public DetalleproduccionPK() {
    }

    public DetalleproduccionPK(int idDetalleProduccion, int idOrdenProduccion, int idMaquina, int idDetOrdProduccion) {
        this.idDetalleProduccion = idDetalleProduccion;
        this.idOrdenProduccion = idOrdenProduccion;
        this.idMaquina = idMaquina;
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdDetalleProduccion() {
        return idDetalleProduccion;
    }

    public void setIdDetalleProduccion(int idDetalleProduccion) {
        this.idDetalleProduccion = idDetalleProduccion;
    }

    public int getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(int idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getIdDetOrdProduccion() {
        return idDetOrdProduccion;
    }

    public void setIdDetOrdProduccion(int idDetOrdProduccion) {
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetalleProduccion;
        hash += (int) idOrdenProduccion;
        hash += (int) idMaquina;
        hash += (int) idDetOrdProduccion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleproduccionPK)) {
            return false;
        }
        DetalleproduccionPK other = (DetalleproduccionPK) object;
        if (this.idDetalleProduccion != other.idDetalleProduccion) {
            return false;
        }
        if (this.idOrdenProduccion != other.idOrdenProduccion) {
            return false;
        }
        if (this.idMaquina != other.idMaquina) {
            return false;
        }
        if (this.idDetOrdProduccion != other.idDetOrdProduccion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetalleproduccionPK[idDetalleProduccion=" + idDetalleProduccion + ", idOrdenProduccion=" + idOrdenProduccion + ", idMaquina=" + idMaquina + ", idDetOrdProduccion=" + idDetOrdProduccion + "]";
    }

}
