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
public class ComponentesPKT implements Serializable {

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private int idArticulo;
    private int idComponente;

    public ComponentesPKT() {
    }

    public ComponentesPKT(int idArticulo, int idComponente) {
        this.idArticulo = idArticulo;
        this.idComponente = idComponente;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        int oldIdArticulo = this.idArticulo;
        this.idArticulo = idArticulo;
        changeSupport.firePropertyChange("idArticulo", oldIdArticulo, idArticulo);
    }

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        int oldIdComponente = this.idComponente;
        this.idComponente = idComponente;
        changeSupport.firePropertyChange("idComponente", oldIdComponente, idComponente);
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
