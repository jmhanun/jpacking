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
public class DetalleFactComprasPKT implements Serializable {

    private int idDetFactCompras;
    private int idFactCompra;

    public DetalleFactComprasPKT() {
    }

    public DetalleFactComprasPKT(int idDetFactCompras, int idFactCompra) {
        this.idDetFactCompras = idDetFactCompras;
        this.idFactCompra = idFactCompra;
    }

    public int getIdDetFactCompras() {
        return idDetFactCompras;
    }

    public void setIdDetFactCompras(int idDetFactCompras) {
        this.idDetFactCompras = idDetFactCompras;
    }

    public int getIdFactCompra() {
        return idFactCompra;
    }

    public void setIdFactCompra(int idFactCompra) {
        this.idFactCompra = idFactCompra;
    }
}
