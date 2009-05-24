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
public class PreciosT implements Serializable {

    private Integer idPrecio;
    private double precio;
    private Date fechaModificacion;
    private ArticulosT idArticulo;
    private ListasPreciosT idLista;
    private UsuariosT idUsuario;

    public PreciosT() {
    }

    public PreciosT(Integer idPrecio, double precio, Date fechaModificacion, ArticulosT idArticulo, ListasPreciosT idLista, UsuariosT idUsuario) {
        this.idPrecio = idPrecio;
        this.precio = precio;
        this.fechaModificacion = fechaModificacion;
        this.idArticulo = idArticulo;
        this.idLista = idLista;
        this.idUsuario = idUsuario;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public ArticulosT getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(ArticulosT idArticulo) {
        this.idArticulo = idArticulo;
    }

    public ListasPreciosT getIdLista() {
        return idLista;
    }

    public void setIdLista(ListasPreciosT idLista) {
        this.idLista = idLista;
    }

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
