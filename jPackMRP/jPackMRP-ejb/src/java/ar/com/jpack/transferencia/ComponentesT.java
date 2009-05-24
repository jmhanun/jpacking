/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class ComponentesT implements Serializable {

    protected ComponentesPKT componentesPK;
    private int orden;
    private float cantidad;
    private Date fechaAlta;
    private Date fechaModificacion;
    private ArticulosT articulos;
    private ArticulosT componentes;
    private UsuariosT idUsuario;

    public ComponentesT() {
    }

    public ComponentesT(ComponentesPKT componentesPK, int orden, float cantidad, Date fechaAlta, Date fechaModificacion, ArticulosT articulos, ArticulosT componentes, UsuariosT idUsuario) {
        this.componentesPK = componentesPK;
        this.orden = orden;
        this.cantidad = cantidad;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.articulos = articulos;
        this.componentes = componentes;
        this.idUsuario = idUsuario;
    }

    public ArticulosT getArticulos() {
        return articulos;
    }

    public void setArticulos(ArticulosT articulos) {
        this.articulos = articulos;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public ArticulosT getComponentes() {
        return componentes;
    }

    public void setComponentes(ArticulosT componentes) {
        this.componentes = componentes;
    }

    public ComponentesPKT getComponentesPK() {
        return componentesPK;
    }

    public void setComponentesPK(ComponentesPKT componentesPK) {
        this.componentesPK = componentesPK;
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

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }    
}
