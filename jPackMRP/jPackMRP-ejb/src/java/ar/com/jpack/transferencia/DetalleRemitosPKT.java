/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 *
 * @author jmhanun
 */
public class DetalleRemitosPKT implements Serializable {

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private int idDetalleRemito;
    private int idRemito;

    public DetalleRemitosPKT() {
    }

    public DetalleRemitosPKT(int idDetalleRemito, int idRemito) {
        this.idDetalleRemito = idDetalleRemito;
        this.idRemito = idRemito;
    }

    public int getIdDetalleRemito() {
        return idDetalleRemito;
    }

    public void setIdDetalleRemito(int idDetalleRemito) {
        int oldIdDetalleRemito = this.idDetalleRemito;
        this.idDetalleRemito = idDetalleRemito;
        changeSupport.firePropertyChange("idDetalleRemito", oldIdDetalleRemito, idDetalleRemito);
    }

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        int oldIdRemito = this.idRemito;
        this.idRemito = idRemito;
        changeSupport.firePropertyChange("idRemito", oldIdRemito, idRemito);
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
