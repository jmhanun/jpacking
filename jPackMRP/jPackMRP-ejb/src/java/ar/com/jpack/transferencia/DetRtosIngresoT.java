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
public class DetRtosIngresoT implements Serializable {

    protected DetrtosIngresoPKT detrtosingresoPK;
    private int cantidad;
    private double precioUnitario;
    private double importe;
    private float descuento;
    private ArticulosT idArticulo;
    private RemitosIngresoT remitosingreso;
    private UnidadesMedidaT idUnidMedida;

    public DetRtosIngresoT() {
    }

    public DetRtosIngresoT(DetrtosIngresoPKT detrtosingresoPK, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, RemitosIngresoT remitosingreso, UnidadesMedidaT idUnidMedida) {
        this.detrtosingresoPK = detrtosingresoPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.remitosingreso = remitosingreso;
        this.idUnidMedida = idUnidMedida;
    }

    public DetRtosIngresoT(int idDetRtoIngreso, int idRtoIngreso, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, RemitosIngresoT remitosingreso, UnidadesMedidaT idUnidMedida) {
        this.detrtosingresoPK = new DetrtosIngresoPKT(idDetRtoIngreso, idRtoIngreso);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.remitosingreso = remitosingreso;
        this.idUnidMedida = idUnidMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public DetrtosIngresoPKT getDetrtosingresoPK() {
        return detrtosingresoPK;
    }

    public void setDetrtosingresoPK(DetrtosIngresoPKT detrtosingresoPK) {
        this.detrtosingresoPK = detrtosingresoPK;
    }

    public ArticulosT getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(ArticulosT idArticulo) {
        this.idArticulo = idArticulo;
    }

    public UnidadesMedidaT getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(UnidadesMedidaT idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public RemitosIngresoT getRemitosingreso() {
        return remitosingreso;
    }

    public void setRemitosingreso(RemitosIngresoT remitosingreso) {
        this.remitosingreso = remitosingreso;
    }
}
