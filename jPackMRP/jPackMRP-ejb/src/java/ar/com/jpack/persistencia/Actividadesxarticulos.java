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
@Table(name = "actividadesxarticulos")
@NamedQueries({@NamedQuery(name = "Actividadesxarticulos.findByIdActividad", query = "SELECT a FROM Actividadesxarticulos a WHERE a.actividadesxarticulosPK.idActividad = :idActividad"), @NamedQuery(name = "Actividadesxarticulos.findByIdArticulo", query = "SELECT a FROM Actividadesxarticulos a WHERE a.actividadesxarticulosPK.idArticulo = :idArticulo"), @NamedQuery(name = "Actividadesxarticulos.findByTiempo", query = "SELECT a FROM Actividadesxarticulos a WHERE a.tiempo = :tiempo"), @NamedQuery(name = "Actividadesxarticulos.findByOrden", query = "SELECT a FROM Actividadesxarticulos a WHERE a.orden = :orden")})
public class Actividadesxarticulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActividadesxarticulosPK actividadesxarticulosPK;
    @Column(name = "tiempo", nullable = false)
    private float tiempo;
    @Column(name = "orden", nullable = false)
    private int orden;
    @JoinColumn(name = "idActividad", referencedColumnName = "idActividad", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Actividades actividades;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos articulos;

    public Actividadesxarticulos() {
    }

    public Actividadesxarticulos(ActividadesxarticulosPK actividadesxarticulosPK) {
        this.actividadesxarticulosPK = actividadesxarticulosPK;
    }

    public Actividadesxarticulos(ActividadesxarticulosPK actividadesxarticulosPK, float tiempo, int orden) {
        this.actividadesxarticulosPK = actividadesxarticulosPK;
        this.tiempo = tiempo;
        this.orden = orden;
    }

    public Actividadesxarticulos(int idActividad, int idArticulo) {
        this.actividadesxarticulosPK = new ActividadesxarticulosPK(idActividad, idArticulo);
    }

    public ActividadesxarticulosPK getActividadesxarticulosPK() {
        return actividadesxarticulosPK;
    }

    public void setActividadesxarticulosPK(ActividadesxarticulosPK actividadesxarticulosPK) {
        this.actividadesxarticulosPK = actividadesxarticulosPK;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

    public Articulos getArticulos() {
        return articulos;
    }

    public void setArticulos(Articulos articulos) {
        this.articulos = articulos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actividadesxarticulosPK != null ? actividadesxarticulosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividadesxarticulos)) {
            return false;
        }
        Actividadesxarticulos other = (Actividadesxarticulos) object;
        if ((this.actividadesxarticulosPK == null && other.actividadesxarticulosPK != null) || (this.actividadesxarticulosPK != null && !this.actividadesxarticulosPK.equals(other.actividadesxarticulosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Actividadesxarticulos[actividadesxarticulosPK=" + actividadesxarticulosPK + "]";
    }

}
