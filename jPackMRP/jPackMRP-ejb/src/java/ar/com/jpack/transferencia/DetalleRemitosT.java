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
public class DetalleRemitosT implements Serializable {

    protected DetalleRemitosPKT detalleremitosPK;
    private int cantidad;
    private double precioUnitario;
    private double importe;
    private int saldoOP;
    private ArticulosT idArticulo;
    private RemitosT remitos;
    private UnidadesMedidaT idUnidMedida;

    public DetalleRemitosT() {
    }

    public DetalleRemitosT(DetalleRemitosPKT detalleremitosPK, int cantidad, double precioUnitario, double importe, int saldoOP, ArticulosT idArticulo, RemitosT remitos, UnidadesMedidaT idUnidMedida) {
        this.detalleremitosPK = detalleremitosPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.saldoOP = saldoOP;
        this.idArticulo = idArticulo;
        this.remitos = remitos;
        this.idUnidMedida = idUnidMedida;
    }

    public DetalleRemitosT(int idDetalleRemito, int idRemito, int cantidad, double precioUnitario, double importe, int saldoOP, ArticulosT idArticulo, RemitosT remitos, UnidadesMedidaT idUnidMedida) {
        this.detalleremitosPK = new DetalleRemitosPKT(idDetalleRemito, idRemito);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.saldoOP = saldoOP;
        this.idArticulo = idArticulo;
        this.remitos = remitos;
        this.idUnidMedida = idUnidMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleRemitosPKT getDetalleremitosPK() {
        return detalleremitosPK;
    }

    public void setDetalleremitosPK(DetalleRemitosPKT detalleremitosPK) {
        this.detalleremitosPK = detalleremitosPK;
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

    public RemitosT getRemitos() {
        return remitos;
    }

    public void setRemitos(RemitosT remitos) {
        this.remitos = remitos;
    }

    public int getSaldoOP() {
        return saldoOP;
    }

    public void setSaldoOP(int saldoOP) {
        this.saldoOP = saldoOP;
    }
}
