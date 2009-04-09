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
public class ActividadesxarticulosPK implements Serializable {
    @Column(name = "idActividad", nullable = false)
    private int idActividad;
    @Column(name = "idArticulo", nullable = false)
    private int idArticulo;

    public ActividadesxarticulosPK() {
    }

    public ActividadesxarticulosPK(int idActividad, int idArticulo) {
        this.idActividad = idActividad;
        this.idArticulo = idArticulo;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idActividad;
        hash += (int) idArticulo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadesxarticulosPK)) {
            return false;
        }
        ActividadesxarticulosPK other = (ActividadesxarticulosPK) object;
        if (this.idActividad != other.idActividad) {
            return false;
        }
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.ActividadesxarticulosPK[idActividad=" + idActividad + ", idArticulo=" + idArticulo + "]";
    }

}
