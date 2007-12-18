/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;

/**
 *
 * @author Pablo
 */
public class ComponentesT implements Serializable {

    private Integer idArticulo;
    private Integer idComponente;
    private Integer orden;
    private Double cantidad;
    private ArticulosT articulos;
    private ArticulosT componentes;

    public ComponentesT() {
    }

    public ComponentesT(Integer orden, Double cantidad, ArticulosT articulos, ArticulosT componentes) {
        this.orden = orden;
        this.cantidad = cantidad;
        this.articulos = articulos;
        this.componentes = componentes;
    }

    public ComponentesT(Integer idArticulo, Integer idComponente, Integer orden, Double cantidad, ArticulosT articulos, ArticulosT componentes) {
        this.idArticulo = idArticulo;
        this.idComponente = idComponente;
        this.orden = orden;
        this.cantidad = cantidad;
        this.articulos = articulos;
        this.componentes = componentes;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public ArticulosT getArticulos() {
        return articulos;
    }

    public void setArticulos(ArticulosT articulos) {
        this.articulos = articulos;
    }

    public ArticulosT getComponentes() {
        return componentes;
    }

    public void setComponentes(ArticulosT componentes) {
        this.componentes = componentes;
    }
}
