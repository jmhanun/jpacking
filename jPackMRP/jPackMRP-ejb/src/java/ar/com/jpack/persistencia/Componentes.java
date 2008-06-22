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
@Table(name = "componentes")
@NamedQueries({@NamedQuery(name = "Componentes.findByIdArticulo", query = "SELECT c FROM Componentes c WHERE c.componentesPK.idArticulo = :idArticulo"), @NamedQuery(name = "Componentes.findByIdComponente", query = "SELECT c FROM Componentes c WHERE c.componentesPK.idComponente = :idComponente"), @NamedQuery(name = "Componentes.findByOrden", query = "SELECT c FROM Componentes c WHERE c.orden = :orden"), @NamedQuery(name = "Componentes.findByCantidad", query = "SELECT c FROM Componentes c WHERE c.cantidad = :cantidad")})
public class Componentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComponentesPK componentesPK;
    @Column(name = "orden", nullable = false)
    private int orden;
    @Column(name = "cantidad", nullable = false)
    private float cantidad;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo", insertable = false, updatable = false)
    @ManyToOne
    private Articulos articulos;
    @JoinColumn(name = "idComponente", referencedColumnName = "idArticulo", insertable = false, updatable = false)
    @ManyToOne
    private Articulos articulos1;

    public Componentes() {
    }

    public Componentes(ComponentesPK componentesPK) {
        this.componentesPK = componentesPK;
    }

    public Componentes(ComponentesPK componentesPK, int orden, float cantidad) {
        this.componentesPK = componentesPK;
        this.orden = orden;
        this.cantidad = cantidad;
    }

    public Componentes(int idArticulo, int idComponente) {
        this.componentesPK = new ComponentesPK(idArticulo, idComponente);
    }

    public ComponentesPK getComponentesPK() {
        return componentesPK;
    }

    public void setComponentesPK(ComponentesPK componentesPK) {
        this.componentesPK = componentesPK;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Articulos getArticulos() {
        return articulos;
    }

    public void setArticulos(Articulos articulos) {
        this.articulos = articulos;
    }

    public Articulos getArticulos1() {
        return articulos1;
    }

    public void setArticulos1(Articulos articulos1) {
        this.articulos1 = articulos1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (componentesPK != null ? componentesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componentes)) {
            return false;
        }
        Componentes other = (Componentes) object;
        if ((this.componentesPK == null && other.componentesPK != null) || (this.componentesPK != null && !this.componentesPK.equals(other.componentesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Componentes[componentesPK=" + componentesPK + "]";
    }

}
