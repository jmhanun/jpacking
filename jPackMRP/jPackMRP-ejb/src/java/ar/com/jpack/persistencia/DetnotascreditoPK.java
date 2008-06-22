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
public class DetnotascreditoPK implements Serializable {
    @Column(name = "idDetNotaCredito", nullable = false)
    private int idDetNotaCredito;
    @Column(name = "idNotaCredito", nullable = false)
    private int idNotaCredito;

    public DetnotascreditoPK() {
    }

    public DetnotascreditoPK(int idDetNotaCredito, int idNotaCredito) {
        this.idDetNotaCredito = idDetNotaCredito;
        this.idNotaCredito = idNotaCredito;
    }

    public int getIdDetNotaCredito() {
        return idDetNotaCredito;
    }

    public void setIdDetNotaCredito(int idDetNotaCredito) {
        this.idDetNotaCredito = idDetNotaCredito;
    }

    public int getIdNotaCredito() {
        return idNotaCredito;
    }

    public void setIdNotaCredito(int idNotaCredito) {
        this.idNotaCredito = idNotaCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetNotaCredito;
        hash += (int) idNotaCredito;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetnotascreditoPK)) {
            return false;
        }
        DetnotascreditoPK other = (DetnotascreditoPK) object;
        if (this.idDetNotaCredito != other.idDetNotaCredito) {
            return false;
        }
        if (this.idNotaCredito != other.idNotaCredito) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetnotascreditoPK[idDetNotaCredito=" + idDetNotaCredito + ", idNotaCredito=" + idNotaCredito + "]";
    }

}
