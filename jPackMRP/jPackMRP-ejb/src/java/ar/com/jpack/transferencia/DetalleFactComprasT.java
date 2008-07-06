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
public class DetalleFactComprasT implements Serializable{
    protected DetalleFactComprasPKT detallefactcomprasPK;
    private int cantidad;
    private double precioUnitario;
    private double importe;
    private float descuento;
    private ArticulosT idArticulo;
    private FacturasComprasT facturascompras;
    private UnidadesMedidaT idUnidMedida;

    public DetalleFactComprasT() {
    }

    public DetalleFactComprasT(DetalleFactComprasPKT detallefactcomprasPK, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, FacturasComprasT facturascompras, UnidadesMedidaT idUnidMedida) {
        this.detallefactcomprasPK = detallefactcomprasPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.facturascompras = facturascompras;
        this.idUnidMedida = idUnidMedida;
    }

    public DetalleFactComprasT(int idDetFactCompras,int idFactCompra, int cantidad, double precioUnitario, double importe, float descuento, ArticulosT idArticulo, FacturasComprasT facturascompras, UnidadesMedidaT idUnidMedida) {
        this.detallefactcomprasPK = new DetalleFactComprasPKT(idDetFactCompras, idFactCompra);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.descuento = descuento;
        this.idArticulo = idArticulo;
        this.facturascompras = facturascompras;
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

    public DetalleFactComprasPKT getDetallefactcomprasPK() {
        return detallefactcomprasPK;
    }

    public void setDetallefactcomprasPK(DetalleFactComprasPKT detallefactcomprasPK) {
        this.detallefactcomprasPK = detallefactcomprasPK;
    }

    public FacturasComprasT getFacturascompras() {
        return facturascompras;
    }

    public void setFacturascompras(FacturasComprasT facturascompras) {
        this.facturascompras = facturascompras;
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
