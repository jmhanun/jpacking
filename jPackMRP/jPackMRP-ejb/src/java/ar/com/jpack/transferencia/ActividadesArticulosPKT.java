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
public class ActividadesArticulosPKT implements Serializable{
    private int idActividad;
    private int idArticulo;

    public ActividadesArticulosPKT() {
    }

    public ActividadesArticulosPKT(int idActividad, int idArticulo) {
        this.idActividad = idActividad;
        this.idArticulo = idArticulo;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

}
