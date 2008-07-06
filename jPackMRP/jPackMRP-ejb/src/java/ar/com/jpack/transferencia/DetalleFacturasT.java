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
public class DetalleFacturasT implements Serializable {

    protected DetalleFacturasPKT detallefacturasPK;
    private int cantidad;
    private double precioUnitario;
    private double importe;
    private float descuento;
    private ArticulosT idArticulo;
    private FacturasT facturas;
    private UnidadesMedidaT idUnidMedida;

    public DetalleFacturasT() {
    }

    public DetalleFacturasT(DetalleFacturasPKT detallefacturasPK, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, FacturasT facturas, UnidadesMedidaT idUnidMedida) {
        this.detallefacturasPK = detallefacturasPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.facturas = facturas;
        this.idUnidMedida = idUnidMedida;
    }

    public DetalleFacturasT(int idDetalle, int idFactura, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, FacturasT facturas, UnidadesMedidaT idUnidMedida) {
        this.detallefacturasPK = new DetalleFacturasPKT(idDetalle, idFactura);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.facturas = facturas;
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

    public DetalleFacturasPKT getDetallefacturasPK() {
        return detallefacturasPK;
    }

    public void setDetallefacturasPK(DetalleFacturasPKT detallefacturasPK) {
        this.detallefacturasPK = detallefacturasPK;
    }

    public FacturasT getFacturas() {
        return facturas;
    }

    public void setFacturas(FacturasT facturas) {
        this.facturas = facturas;
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
}
