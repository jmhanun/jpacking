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
public class DetalleproduccionrealPK implements Serializable {

    @Column(name = "idDetalleProduccionReal", nullable = false)
    private int idDetalleProduccionReal;
    @Column(name = "idOrdenProduccion", nullable = false)
    private int idOrdenProduccion;
    @Column(name = "idMaquina", nullable = false)
    private int idMaquina;
    @Column(name = "idDetOrdProduccion", nullable = false)
    private int idDetOrdProduccion;

    public DetalleproduccionrealPK() {
    }

    public DetalleproduccionrealPK(int idDetalleProduccionReal, int idOrdenProduccion, int idMaquina, int idDetOrdProduccion) {
        this.idDetalleProduccionReal = idDetalleProduccionReal;
        this.idOrdenProduccion = idOrdenProduccion;
        this.idMaquina = idMaquina;
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdDetalleProduccionReal() {
        return idDetalleProduccionReal;
    }

    public void setIdDetalleProduccionReal(int idDetalleProduccionReal) {
        this.idDetalleProduccionReal = idDetalleProduccionReal;
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
        hash += (int) idDetalleProduccionReal;
        hash += (int) idOrdenProduccion;
        hash += (int) idMaquina;
        hash += (int) idDetOrdProduccion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleproduccionrealPK)) {
            return false;
        }
        DetalleproduccionrealPK other = (DetalleproduccionrealPK) object;
        if (this.idDetalleProduccionReal != other.idDetalleProduccionReal) {
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
        return "ar.com.jpack.persistencia.DetalleproduccionrealPK[idDetalleProduccionReal=" + idDetalleProduccionReal + ", idOrdenProduccion=" + idOrdenProduccion + ", idMaquina=" + idMaquina + ", idDetOrdProduccion=" + idDetOrdProduccion + "]";
    }
}
