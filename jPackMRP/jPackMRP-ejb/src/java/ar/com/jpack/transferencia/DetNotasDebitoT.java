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
public class DetNotasDebitoT implements Serializable {

    protected DetNotasDebitoPKT detnotasdebitoPK;
    private int cantidad;
    private double precioUnitario;
    private double importe;
    private float descuento;
    private ArticulosT idArticulo;
    private NotasDebitoT notasdebito;
    private UnidadesMedidaT idUnidMedida;

    public DetNotasDebitoT() {
    }

    public DetNotasDebitoT(DetNotasDebitoPKT detnotasdebitoPK, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, NotasDebitoT notasdebito, UnidadesMedidaT idUnidMedida) {
        this.detnotasdebitoPK = detnotasdebitoPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.notasdebito = notasdebito;
        this.idUnidMedida = idUnidMedida;
    }

    public DetNotasDebitoT(int idDetNotaDebito, int idNotaDebito, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, NotasDebitoT notasdebito, UnidadesMedidaT idUnidMedida) {
        this.detnotasdebitoPK = new DetNotasDebitoPKT(idDetNotaDebito, idNotaDebito);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.notasdebito = notasdebito;
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

    public DetNotasDebitoPKT getDetnotasdebitoPK() {
        return detnotasdebitoPK;
    }

    public void setDetnotasdebitoPK(DetNotasDebitoPKT detnotasdebitoPK) {
        this.detnotasdebitoPK = detnotasdebitoPK;
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

    public NotasDebitoT getNotasdebito() {
        return notasdebito;
    }

    public void setNotasdebito(NotasDebitoT notasdebito) {
        this.notasdebito = notasdebito;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
