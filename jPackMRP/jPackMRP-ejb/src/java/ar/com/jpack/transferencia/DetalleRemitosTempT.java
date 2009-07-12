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
public class DetalleRemitosTempT implements Serializable {

    protected DetalleRemitosTempPKT detalleremitostempPK;
    private int cantidad;
    private Date fechafin;
    private int orden;

    public DetalleRemitosTempT() {
    }

    public DetalleRemitosTempT(DetalleRemitosTempPKT detalleremitostempPK, int cantidad, Date fechafin, int orden) {
        this.detalleremitostempPK = detalleremitostempPK;
        this.cantidad = cantidad;
        this.fechafin = fechafin;
        this.orden = orden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleRemitosTempPKT getDetalleremitostempPK() {
        return detalleremitostempPK;
    }

    public void setDetalleremitostempPK(DetalleRemitosTempPKT detalleremitostempPK) {
        this.detalleremitostempPK = detalleremitostempPK;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
