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
public class StockT implements Serializable {

    private Integer idStock;
    private Date fechaUltMod;
    private double cantidad;
    private ArticulosT idArticulo;
    private UsuariosT idUsuario;

    public StockT() {
    }

    public StockT(Integer idStock, Date fechaUltMod, double cantidad, ArticulosT idArticulo, UsuariosT idUsuario) {
        this.idStock = idStock;
        this.fechaUltMod = fechaUltMod;
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.idUsuario = idUsuario;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(Date fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
    }

    public ArticulosT getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(ArticulosT idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        this.idUsuario = idUsuario;
    }
}
