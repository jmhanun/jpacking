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
public class DetalleremitostempPK implements Serializable {
    @Column(name = "instancia", nullable = false)
    private int instancia;
    @Column(name = "idarticulo", nullable = false)
    private int idarticulo;
    @Column(name = "idactividad", nullable = false)
    private int idactividad;

    public DetalleremitostempPK() {
    }

    public DetalleremitostempPK(int instancia, int idarticulo, int idactividad) {
        this.instancia = instancia;
        this.idarticulo = idarticulo;
        this.idactividad = idactividad;
    }

    public int getInstancia() {
        return instancia;
    }

    public void setInstancia(int instancia) {
        this.instancia = instancia;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) instancia;
        hash += (int) idarticulo;
        hash += (int) idactividad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleremitostempPK)) {
            return false;
        }
        DetalleremitostempPK other = (DetalleremitostempPK) object;
        if (this.instancia != other.instancia) {
            return false;
        }
        if (this.idarticulo != other.idarticulo) {
            return false;
        }
        if (this.idactividad != other.idactividad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetalleremitostempPK[instancia=" + instancia + ", idarticulo=" + idarticulo + ", idactividad=" + idactividad + "]";
    }

}
