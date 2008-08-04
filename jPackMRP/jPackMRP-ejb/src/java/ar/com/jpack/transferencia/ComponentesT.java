/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class ComponentesT implements Serializable {

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    protected ComponentesPKT componentesPK;
    private int orden;
    private float cantidad;
    private Date fechaAlta;
    private Date fechaModificacion;
    private ArticulosT articulos;
    private ArticulosT componentes;

    public ComponentesT() {
    }

    public ComponentesT(ComponentesPKT componentesPK,
            int orden, float cantidad,
            Date fechaAlta,
            Date fechaModificacion,
            ArticulosT articulos, ArticulosT componentes) {
        this.componentesPK = componentesPK;
        this.orden = orden;
        this.cantidad = cantidad;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.articulos = articulos;
        this.componentes = componentes;
    }

    public ComponentesT(int idArticulo, int idComponente, int orden, float cantidad,
            Date fechaAlta,
            Date fechaModificacion,
            ArticulosT articulos, ArticulosT componentes) {
        this.componentesPK = new ComponentesPKT(idArticulo, idComponente);
        this.orden = orden;
        this.cantidad = cantidad;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.articulos = articulos;
        this.componentes = componentes;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        Date oldFechaAlta = this.fechaAlta;
        this.fechaAlta = fechaAlta;
        changeSupport.firePropertyChange("fechaAlta", oldFechaAlta, fechaAlta);
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        Date oldFechaModificacion = this.fechaModificacion;
        this.fechaModificacion = fechaModificacion;
        changeSupport.firePropertyChange("fechaModificacion", oldFechaModificacion, fechaModificacion);
    }

    public ArticulosT getArticulos() {
        return articulos;
    }

    public void setArticulos(ArticulosT articulos) {
        ArticulosT oldArticulos = this.articulos;
        this.articulos = articulos;
        changeSupport.firePropertyChange("articulos", oldArticulos, articulos);
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        float oldCantidad = this.cantidad;
        this.cantidad = cantidad;
        changeSupport.firePropertyChange("cantidad", oldCantidad, cantidad);
    }

    public ArticulosT getComponentes() {
        return componentes;
    }

    public void setComponentes(ArticulosT componentes) {
        ArticulosT oldComponentes = this.componentes;
        this.componentes = componentes;
        changeSupport.firePropertyChange("componentes", oldComponentes, componentes);
    }

    public ComponentesPKT getComponentesPK() {
        return componentesPK;
    }

    public void setComponentesPK(ComponentesPKT componentesPK) {
        ComponentesPKT oldComponentesPK = this.componentesPK;
        this.componentesPK = componentesPK;
        changeSupport.firePropertyChange("componentesPK", oldComponentesPK, componentesPK);
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        int oldOrden = this.orden;
        this.orden = orden;
        changeSupport.firePropertyChange("orden", oldOrden, orden);
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
