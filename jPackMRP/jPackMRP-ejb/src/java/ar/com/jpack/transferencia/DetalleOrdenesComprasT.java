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
public class DetalleOrdenesComprasT implements Serializable {

    protected DetalleOrdenesComprasPKT detalleordenescomprasPK;
    private int cantidad;
    private ArticulosT idArticulo;
    private OrdenesCompraT ordenescompra;
    private UnidadesMedidaT idUnidMedida;

    public DetalleOrdenesComprasT() {
    }

    public DetalleOrdenesComprasT(DetalleOrdenesComprasPKT detalleordenescomprasPK, int cantidad, ArticulosT idArticulo, OrdenesCompraT ordenescompra, UnidadesMedidaT idUnidMedida) {
        this.detalleordenescomprasPK = detalleordenescomprasPK;
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.ordenescompra = ordenescompra;
        this.idUnidMedida = idUnidMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleOrdenesComprasPKT getDetalleordenescomprasPK() {
        return detalleordenescomprasPK;
    }

    public void setDetalleordenescomprasPK(DetalleOrdenesComprasPKT detalleordenescomprasPK) {
        this.detalleordenescomprasPK = detalleordenescomprasPK;
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

    public OrdenesCompraT getOrdenescompra() {
        return ordenescompra;
    }

    public void setOrdenescompra(OrdenesCompraT ordenescompra) {
        this.ordenescompra = ordenescompra;
    }
}
