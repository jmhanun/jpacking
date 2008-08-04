/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 *
 * @author jmhanun
 */
public class DetalleRemitosTHelper implements Serializable, Comparable<DetalleRemitosTHelper> {
    private static final long serialVersionUID = 99L;
    private Integer idArticulo;
    private Integer cantidad;
    private Integer idUnidadMedida;
    private Double precioUnitario;
    private Double importe;
    public static final String PROP_IMPORTE = "importe";
    public static final String PROP_PRECIOUNITARIO = "precioUnitario";
    public static final String PROP_IDUNIDADMEDIDA = "idUnidadMedida";
    public static final String PROP_CANTIDAD = "cantidad";
    public static final String PROP_IDARTICULO = "idArticulo";

    public boolean isCompleto() {
        boolean completo = true;
        if (idArticulo == null) {
            completo = false;
        } else {
            if (idArticulo < 0) {
                completo = false;
            }
        }
        if (cantidad == null) {
            completo = false;
        } else {
            if (cantidad < 0) {
                completo = false;
            }
        }
        if (idUnidadMedida == null) {
            completo = false;
        } else {
            if (idUnidadMedida < 0) {
                completo = false;
            }
        }
        if (precioUnitario == null) {
            completo = false;
        } else {
            if (precioUnitario < 0) {
                completo = false;
            }
        }
        if (importe == null) {
            completo = false;
        } else {
            if (importe < 0) {
                completo = false;
            }
        }
        return completo;
    }

    /**
     * Get the value of importe
     *
     * @return the value of importe
     */
    public Double getImporte() {
        return importe;
    }

    /**
     * Set the value of importe
     *
     * @param importe new value of importe
     */
    public void setImporte(Double importe) {
        Double oldImporte = this.importe;
        this.importe = importe;
        propertyChangeSupport.firePropertyChange(PROP_IMPORTE, oldImporte, importe);
    }

    /**
     * Get the value of precioUnitario
     *
     * @return the value of precioUnitario
     */
    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Set the value of precioUnitario
     *
     * @param precioUnitario new value of precioUnitario
     */
    public void setPrecioUnitario(Double precioUnitario) {
        Double oldPrecioUnitario = this.precioUnitario;
        this.precioUnitario = precioUnitario;
        propertyChangeSupport.firePropertyChange(PROP_PRECIOUNITARIO, oldPrecioUnitario, precioUnitario);
    }

    /**
     * Get the value of idUnidadMedida
     *
     * @return the value of idUnidadMedida
     */
    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    /**
     * Set the value of idUnidadMedida
     *
     * @param idUnidadMedida new value of idUnidadMedida
     */
    public void setIdUnidadMedida(Integer idUnidadMedida) {
        Integer oldIdUnidadMedida = this.idUnidadMedida;
        this.idUnidadMedida = idUnidadMedida;
        propertyChangeSupport.firePropertyChange(PROP_IDUNIDADMEDIDA, oldIdUnidadMedida, idUnidadMedida);
    }

    /**
     * Get the value of cantidad
     *
     * @return the value of cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Set the value of cantidad
     *
     * @param cantidad new value of cantidad
     */
    public void setCantidad(Integer cantidad) {
        Integer oldCantidad = this.cantidad;
        this.cantidad = cantidad;
        propertyChangeSupport.firePropertyChange(PROP_CANTIDAD, oldCantidad, cantidad);
    }

    /**
     * Get the value of idArticulo
     *
     * @return the value of idArticulo
     */
    public Integer getIdArticulo() {
        return idArticulo;
    }

    /**
     * Set the value of idArticulo
     *
     * @param idArticulo new value of idArticulo
     */
    public void setIdArticulo(Integer idArticulo) {
        Integer oldIdArticulo = this.idArticulo;
        this.idArticulo = idArticulo;
        propertyChangeSupport.firePropertyChange(PROP_IDARTICULO, oldIdArticulo, idArticulo);
    }
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public int compareTo(DetalleRemitosTHelper o) {
        Integer thisIdArticulo = this.idArticulo;
        Integer anotherIdArticulo = o.getIdArticulo();
        return thisIdArticulo.compareTo(anotherIdArticulo);
    }
}
