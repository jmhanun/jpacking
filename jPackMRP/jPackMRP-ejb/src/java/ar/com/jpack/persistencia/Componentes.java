/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "componentes")
@NamedQueries({@NamedQuery(name = "Componentes.findByIdArticulo", query = "SELECT c FROM Componentes c WHERE c.componentesPK.idArticulo = :idArticulo"), @NamedQuery(name = "Componentes.findByIdComponente", query = "SELECT c FROM Componentes c WHERE c.componentesPK.idComponente = :idComponente"), @NamedQuery(name = "Componentes.findByOrden", query = "SELECT c FROM Componentes c WHERE c.orden = :orden"), @NamedQuery(name = "Componentes.findByCantidad", query = "SELECT c FROM Componentes c WHERE c.cantidad = :cantidad"), @NamedQuery(name = "Componentes.findByFechaAlta", query = "SELECT c FROM Componentes c WHERE c.fechaAlta = :fechaAlta"), @NamedQuery(name = "Componentes.findByFechaModificacion", query = "SELECT c FROM Componentes c WHERE c.fechaModificacion = :fechaModificacion")})
public class Componentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComponentesPK componentesPK;
    @Column(name = "orden", nullable = false)
    private int orden;
    @Column(name = "cantidad", nullable = false)
    private float cantidad;
    @Column(name = "fechaAlta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos articulos;
    @JoinColumn(name = "idComponente", referencedColumnName = "idArticulo", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos componentes;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;

    public Componentes() {
    }

    public Componentes(ComponentesPK componentesPK) {
        this.componentesPK = componentesPK;
    }

    public Componentes(ComponentesPK componentesPK, int orden, float cantidad, Date fechaAlta, Date fechaModificacion) {
        this.componentesPK = componentesPK;
        this.orden = orden;
        this.cantidad = cantidad;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
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

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Articulos getArticulos() {
        return articulos;
    }

    public void setArticulos(Articulos articulos) {
        this.articulos = articulos;
    }

    public Articulos getComponentes() {
        return componentes;
    }

    public void setComponentes(Articulos componentes) {
        this.componentes = componentes;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (componentesPK != null ? componentesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
