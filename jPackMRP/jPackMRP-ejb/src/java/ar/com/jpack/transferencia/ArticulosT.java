/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Pablo
 */
public class ArticulosT implements Serializable {

    @Override
    public String toString() {
        return this.getCodigo() + " | " + this.getDescripcion();
    }

    private Integer idArticulo;
    private String codigo;
    private String descripcion;
    private String estado;
    private Double stock;
    private Double stockMinimo;
    private Double leadTime;
    private Collection<ComponentesT> coleccionComponentesT;

    public ArticulosT() {
        this.coleccionComponentesT = new ArrayList<ComponentesT>();
    }

    public ArticulosT(String codigo, String descripcion, String estado, Double stock, Double stockMinimo, Double leadTime, Collection coleccionComponentesT) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.leadTime = leadTime;
        this.coleccionComponentesT = coleccionComponentesT;
    }

    public ArticulosT(Integer idArticulo, String codigo, String descripcion, String estado, Double stock, Double stockMinimo, Double leadTime, Collection coleccionComponentesT) {
        this.idArticulo = idArticulo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.leadTime = leadTime;
        this.coleccionComponentesT = coleccionComponentesT;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Double getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Double leadTime) {
        this.leadTime = leadTime;
    }

    public Collection<ComponentesT> getColeccionComponentesT() {
        return coleccionComponentesT;
    }

    public void setColeccionComponentesT(Collection<ComponentesT> coleccionComponentesT) {
        this.coleccionComponentesT = coleccionComponentesT;
    }
}
