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
public class DetNotasCreditoT implements Serializable {

    protected DetNotasCreditoPKT detnotascreditoPK;
    private int cantidad;
    private double precioUnitario;
    private double importe;
    private float descuento;
    private ArticulosT idArticulo;
    private NotasCreditoT notascredito;
    private UnidadesMedidaT idUnidMedida;

    public DetNotasCreditoT() {
    }

    public DetNotasCreditoT(DetNotasCreditoPKT detnotascreditoPK, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, NotasCreditoT notascredito, UnidadesMedidaT idUnidMedida) {
        this.detnotascreditoPK = detnotascreditoPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.notascredito = notascredito;
        this.idUnidMedida = idUnidMedida;
    }

    public DetNotasCreditoT(int idDetNotaCredito, int idNotaCredito, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, NotasCreditoT notascredito, UnidadesMedidaT idUnidMedida) {
        this.detnotascreditoPK = new DetNotasCreditoPKT(idDetNotaCredito, idNotaCredito);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.notascredito = notascredito;
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

    public DetNotasCreditoPKT getDetnotascreditoPK() {
        return detnotascreditoPK;
    }

    public void setDetnotascreditoPK(DetNotasCreditoPKT detnotascreditoPK) {
        this.detnotascreditoPK = detnotascreditoPK;
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

    public NotasCreditoT getNotascredito() {
        return notascredito;
    }

    public void setNotascredito(NotasCreditoT notascredito) {
        this.notascredito = notascredito;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
