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
public class DetordenesdepositoPK implements Serializable {
    @Column(name = "idDetOrdDeposito", nullable = false)
    private int idDetOrdDeposito;
    @Column(name = "idOrdenDeposito", nullable = false)
    private int idOrdenDeposito;

    public DetordenesdepositoPK() {
    }

    public DetordenesdepositoPK(int idDetOrdDeposito, int idOrdenDeposito) {
        this.idDetOrdDeposito = idDetOrdDeposito;
        this.idOrdenDeposito = idOrdenDeposito;
    }

    public int getIdDetOrdDeposito() {
        return idDetOrdDeposito;
    }

    public void setIdDetOrdDeposito(int idDetOrdDeposito) {
        this.idDetOrdDeposito = idDetOrdDeposito;
    }

    public int getIdOrdenDeposito() {
        return idOrdenDeposito;
    }

    public void setIdOrdenDeposito(int idOrdenDeposito) {
        this.idOrdenDeposito = idOrdenDeposito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetOrdDeposito;
        hash += (int) idOrdenDeposito;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetordenesdepositoPK)) {
            return false;
        }
        DetordenesdepositoPK other = (DetordenesdepositoPK) object;
        if (this.idDetOrdDeposito != other.idDetOrdDeposito) {
            return false;
        }
        if (this.idOrdenDeposito != other.idOrdenDeposito) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetordenesdepositoPK[idDetOrdDeposito=" + idDetOrdDeposito + ", idOrdenDeposito=" + idOrdenDeposito + "]";
    }

}
