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
public class DetalleremitosPK implements Serializable {
    @Column(name = "idDetalleRemito", nullable = false)
    private int idDetalleRemito;
    @Column(name = "idRemito", nullable = false)
    private int idRemito;

    public DetalleremitosPK() {
    }

    public DetalleremitosPK(int idDetalleRemito, int idRemito) {
        this.idDetalleRemito = idDetalleRemito;
        this.idRemito = idRemito;
    }

    public int getIdDetalleRemito() {
        return idDetalleRemito;
    }

    public void setIdDetalleRemito(int idDetalleRemito) {
        this.idDetalleRemito = idDetalleRemito;
    }

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetalleRemito;
        hash += (int) idRemito;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleremitosPK)) {
            return false;
        }
        DetalleremitosPK other = (DetalleremitosPK) object;
        if (this.idDetalleRemito != other.idDetalleRemito) {
            return false;
        }
        if (this.idRemito != other.idRemito) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetalleremitosPK[idDetalleRemito=" + idDetalleRemito + ", idRemito=" + idRemito + "]";
    }

}
