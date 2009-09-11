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
public class DetalleOrdenesComprasPKT implements Serializable {

    private int idDetOrdenCompra;
    private int idOrdenCompra;

    public DetalleOrdenesComprasPKT() {
    }

    public DetalleOrdenesComprasPKT(int idDetOrdenCompra, int idOrdenCompra) {
        this.idDetOrdenCompra = idDetOrdenCompra;
        this.idOrdenCompra = idOrdenCompra;
    }

    public int getIdDetOrdenCompra() {
        return idDetOrdenCompra;
    }

    public void setIdDetOrdenCompra(int idDetOrdenCompra) {
        this.idDetOrdenCompra = idDetOrdenCompra;
    }

    public int getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(int idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }
}
