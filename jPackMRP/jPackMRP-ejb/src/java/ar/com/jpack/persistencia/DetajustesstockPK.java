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
public class DetajustesstockPK implements Serializable {
    @Column(name = "idDetAjustesStock", nullable = false)
    private int idDetAjustesStock;
    @Column(name = "idAjusteStock", nullable = false)
    private int idAjusteStock;

    public DetajustesstockPK() {
    }

    public DetajustesstockPK(int idDetAjustesStock, int idAjusteStock) {
        this.idDetAjustesStock = idDetAjustesStock;
        this.idAjusteStock = idAjusteStock;
    }

    public int getIdDetAjustesStock() {
        return idDetAjustesStock;
    }

    public void setIdDetAjustesStock(int idDetAjustesStock) {
        this.idDetAjustesStock = idDetAjustesStock;
    }

    public int getIdAjusteStock() {
        return idAjusteStock;
    }

    public void setIdAjusteStock(int idAjusteStock) {
        this.idAjusteStock = idAjusteStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetAjustesStock;
        hash += (int) idAjusteStock;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetajustesstockPK)) {
            return false;
        }
        DetajustesstockPK other = (DetajustesstockPK) object;
        if (this.idDetAjustesStock != other.idDetAjustesStock) {
            return false;
        }
        if (this.idAjusteStock != other.idAjusteStock) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.DetajustesstockPK[idDetAjustesStock=" + idDetAjustesStock + ", idAjusteStock=" + idAjusteStock + "]";
    }

}
