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
public class DesviosT implements Serializable {

    private Integer iddesvio;
    private String comentarios;
    private DetalleProduccionT iddetalleproduccion;
    private TiposDesviosT idtipodesvio;

    public DesviosT() {
    }

    public DesviosT(Integer iddesvio, String comentarios, DetalleProduccionT iddetalleproduccion, TiposDesviosT idtipodesvio) {
        this.iddesvio = iddesvio;
        this.comentarios = comentarios;
        this.iddetalleproduccion = iddetalleproduccion;
        this.idtipodesvio = idtipodesvio;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getIddesvio() {
        return iddesvio;
    }

    public void setIddesvio(Integer iddesvio) {
        this.iddesvio = iddesvio;
    }

    public DetalleProduccionT getIddetalleproduccion() {
        return iddetalleproduccion;
    }

    public void setIddetalleproduccion(DetalleProduccionT iddetalleproduccion) {
        this.iddetalleproduccion = iddetalleproduccion;
    }

    public TiposDesviosT getIdtipodesvio() {
        return idtipodesvio;
    }

    public void setIdtipodesvio(TiposDesviosT idtipodesvio) {
        this.idtipodesvio = idtipodesvio;
    }
}
