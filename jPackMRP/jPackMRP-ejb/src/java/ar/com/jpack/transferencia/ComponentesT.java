/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;

/**
 *
 * @author jmhanun
 */
public class ComponentesT implements Serializable {

    protected ComponentesPKT componentesPK;
    private int orden;
    private float cantidad;
    private ArticulosT articulos;
    private ArticulosT componentes;

    public ComponentesT() {
    }

    public ComponentesT(ComponentesPKT componentesPK, int orden, float cantidad, ArticulosT articulos, ArticulosT componentes) {
        this.componentesPK = componentesPK;
        this.orden = orden;
        this.cantidad = cantidad;
        this.articulos = articulos;
        this.componentes = componentes;
    }

    public ComponentesT(int idArticulo, int idComponente, int orden, float cantidad, ArticulosT articulos, ArticulosT componentes) {
        this.componentesPK = new ComponentesPKT(idArticulo, idComponente);
        this.orden = orden;
        this.cantidad = cantidad;
        this.articulos = articulos;
        this.componentes = componentes;
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
