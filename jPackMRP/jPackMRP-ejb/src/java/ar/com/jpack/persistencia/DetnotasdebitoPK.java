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
public class DetnotasdebitoPK implements Serializable {
    @Column(name = "idDetNotaDebito", nullable = false)
    private int idDetNotaDebito;
    @Column(name = "idNotaDebito", nullable = false)
    private int idNotaDebito;

    public DetnotasdebitoPK() {
    }

    public DetnotasdebitoPK(int idDetNotaDebito, int idNotaDebito) {
        this.idDetNotaDebito = idDetNotaDebito;
        this.idNotaDebito = idNotaDebito;
    }

    public int getIdDetNotaDebito() {
        return idDetNotaDebito;
    }

    public void setIdDetNotaDebito(int idDetNotaDebito) {
        this.idDetNotaDebito = idDetNotaDebito;
    }

    public int getIdNotaDebito() {
        return idNotaDebito;
    }

    public void setIdNotaDebito(int idNotaDebito) {
        this.idNotaDebito = idNotaDebito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetNotaDebito;
        hash += (int) idNotaDebito;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetnotasdebitoPK)) {
            return false;
        }
        DetnotasdebitoPK other = (DetnotasdebitoPK) object;
        if (this.idDetNotaDebito != other.idDetNotaDebito) {
            return false;
        }
        if (this.idNotaDebito != other.idNotaDebito) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetnotasdebitoPK[idDetNotaDebito=" + idDetNotaDebito + ", idNotaDebito=" + idNotaDebito + "]";
    }

}
