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
public class SellosxdetordenPK implements Serializable {
    @Column(name = "idSello", nullable = false)
    private int idSello;
    @Column(name = "idCliente", nullable = false)
    private int idCliente;
    @Column(name = "idOrdenProduccion", nullable = false)
    private int idOrdenProduccion;
    @Column(name = "idDetOrdProduccion", nullable = false)
    private int idDetOrdProduccion;

    public SellosxdetordenPK() {
    }

    public SellosxdetordenPK(int idSello, int idCliente, int idOrdenProduccion, int idDetOrdProduccion) {
        this.idSello = idSello;
        this.idCliente = idCliente;
        this.idOrdenProduccion = idOrdenProduccion;
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdSello() {
        return idSello;
    }

    public void setIdSello(int idSello) {
        this.idSello = idSello;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(int idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
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
        hash += (int) idSello;
        hash += (int) idCliente;
        hash += (int) idOrdenProduccion;
        hash += (int) idDetOrdProduccion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SellosxdetordenPK)) {
            return false;
        }
        SellosxdetordenPK other = (SellosxdetordenPK) object;
        if (this.idSello != other.idSello) {
            return false;
        }
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.idOrdenProduccion != other.idOrdenProduccion) {
            return false;
        }
        if (this.idDetOrdProduccion != other.idDetOrdProduccion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.SellosxdetordenPK[idSello=" + idSello + ", idCliente=" + idCliente + ", idOrdenProduccion=" + idOrdenProduccion + ", idDetOrdProduccion=" + idDetOrdProduccion + "]";
    }

}
