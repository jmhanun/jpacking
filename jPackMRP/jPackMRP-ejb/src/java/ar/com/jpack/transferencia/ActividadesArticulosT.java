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
public class ActividadesArticulosT implements Serializable{
    protected ActividadesArticulosPKT actividadesxarticulosPK;
    private float tiempo;
    private int orden;
    private ActividadesT actividades;
    private ArticulosT articulos;

    public ActividadesArticulosT() {
    }

    public ActividadesArticulosT(ActividadesArticulosPKT actividadesxarticulosPK, float tiempo, int orden, ActividadesT actividades, ArticulosT articulos) {
        this.actividadesxarticulosPK = actividadesxarticulosPK;
        this.tiempo = tiempo;
        this.orden = orden;
        this.actividades = actividades;
        this.articulos = articulos;
    }

    public ActividadesT getActividades() {
        return actividades;
    }

    public void setActividades(ActividadesT actividades) {
        this.actividades = actividades;
    }

    public ActividadesArticulosPKT getActividadesxarticulosPK() {
        return actividadesxarticulosPK;
    }

    public void setActividadesxarticulosPK(ActividadesArticulosPKT actividadesxarticulosPK) {
        this.actividadesxarticulosPK = actividadesxarticulosPK;
    }

    public ArticulosT getArticulos() {
        return articulos;
    }

    public void setArticulos(ArticulosT articulos) {
        this.articulos = articulos;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

}
