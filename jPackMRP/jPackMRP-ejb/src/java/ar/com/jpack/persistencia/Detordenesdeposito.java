/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "detordenesdeposito")
@NamedQueries({@NamedQuery(name = "Detordenesdeposito.findByIdDetOrdDeposito", query = "SELECT d FROM Detordenesdeposito d WHERE d.detordenesdepositoPK.idDetOrdDeposito = :idDetOrdDeposito"), @NamedQuery(name = "Detordenesdeposito.findByIdOrdenDeposito", query = "SELECT d FROM Detordenesdeposito d WHERE d.detordenesdepositoPK.idOrdenDeposito = :idOrdenDeposito"), @NamedQuery(name = "Detordenesdeposito.findByCantidad", query = "SELECT d FROM Detordenesdeposito d WHERE d.cantidad = :cantidad")})
public class Detordenesdeposito implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetordenesdepositoPK detordenesdepositoPK;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos idArticulo;
    @JoinColumn(name = "idOrdenDeposito", referencedColumnName = "idOrdenDeposito", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordenesdeposito ordenesdeposito;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unidadesmedida idUnidMedida;

    public Detordenesdeposito() {
    }

    public Detordenesdeposito(DetordenesdepositoPK detordenesdepositoPK) {
        this.detordenesdepositoPK = detordenesdepositoPK;
    }

    public Detordenesdeposito(DetordenesdepositoPK detordenesdepositoPK, int cantidad) {
        this.detordenesdepositoPK = detordenesdepositoPK;
        this.cantidad = cantidad;
    }

    public Detordenesdeposito(int idDetOrdDeposito, int idOrdenDeposito) {
        this.detordenesdepositoPK = new DetordenesdepositoPK(idDetOrdDeposito, idOrdenDeposito);
    }

    public DetordenesdepositoPK getDetordenesdepositoPK() {
        return detordenesdepositoPK;
    }

    public void setDetordenesdepositoPK(DetordenesdepositoPK detordenesdepositoPK) {
        this.detordenesdepositoPK = detordenesdepositoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Ordenesdeposito getOrdenesdeposito() {
        return ordenesdeposito;
    }

    public void setOrdenesdeposito(Ordenesdeposito ordenesdeposito) {
        this.ordenesdeposito = ordenesdeposito;
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
        hash += (detordenesdepositoPK != null ? detordenesdepositoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detordenesdeposito)) {
            return false;
        }
        Detordenesdeposito other = (Detordenesdeposito) object;
        if ((this.detordenesdepositoPK == null && other.detordenesdepositoPK != null) || (this.detordenesdepositoPK != null && !this.detordenesdepositoPK.equals(other.detordenesdepositoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detordenesdeposito[detordenesdepositoPK=" + detordenesdepositoPK + "]";
    }

}
