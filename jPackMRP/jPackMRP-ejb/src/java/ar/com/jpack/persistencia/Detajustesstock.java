/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "detajustesstock")
@NamedQueries({@NamedQuery(name = "Detajustesstock.findByIdDetAjustesStock", query = "SELECT d FROM Detajustesstock d WHERE d.detajustesstockPK.idDetAjustesStock = :idDetAjustesStock"), @NamedQuery(name = "Detajustesstock.findByIdAjusteStock", query = "SELECT d FROM Detajustesstock d WHERE d.detajustesstockPK.idAjusteStock = :idAjusteStock"), @NamedQuery(name = "Detajustesstock.findByCantidad", query = "SELECT d FROM Detajustesstock d WHERE d.cantidad = :cantidad")})
public class Detajustesstock implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetajustesstockPK detajustesstockPK;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @JoinColumn(name = "idAjusteStock", referencedColumnName = "idAjusteStock", insertable = false, updatable = false)
    @ManyToOne
    private Ajustesstock ajustesstock;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne
    private Unidadesmedida idUnidMedida;

    public Detajustesstock() {
    }

    public Detajustesstock(DetajustesstockPK detajustesstockPK) {
        this.detajustesstockPK = detajustesstockPK;
    }

    public Detajustesstock(DetajustesstockPK detajustesstockPK, int cantidad) {
        this.detajustesstockPK = detajustesstockPK;
        this.cantidad = cantidad;
    }

    public Detajustesstock(int idDetAjustesStock, int idAjusteStock) {
        this.detajustesstockPK = new DetajustesstockPK(idDetAjustesStock, idAjusteStock);
    }

    public DetajustesstockPK getDetajustesstockPK() {
        return detajustesstockPK;
    }

    public void setDetajustesstockPK(DetajustesstockPK detajustesstockPK) {
        this.detajustesstockPK = detajustesstockPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Ajustesstock getAjustesstock() {
        return ajustesstock;
    }

    public void setAjustesstock(Ajustesstock ajustesstock) {
        this.ajustesstock = ajustesstock;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Unidadesmedida getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(Unidadesmedida idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detajustesstockPK != null ? detajustesstockPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detajustesstock)) {
            return false;
        }
        Detajustesstock other = (Detajustesstock) object;
        if ((this.detajustesstockPK == null && other.detajustesstockPK != null) || (this.detajustesstockPK != null && !this.detajustesstockPK.equals(other.detajustesstockPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detajustesstock[detajustesstockPK=" + detajustesstockPK + "]";
    }

}
