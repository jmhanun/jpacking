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
public class DetAjustesStockPKT implements Serializable {

    private int idDetAjustesStock;
    private int idAjusteStock;

    public DetAjustesStockPKT() {
    }

    public DetAjustesStockPKT(int idDetAjustesStock, int idAjusteStock) {
        this.idDetAjustesStock = idDetAjustesStock;
        this.idAjusteStock = idAjusteStock;
    }

    public int getIdAjusteStock() {
        return idAjusteStock;
    }

    public void setIdAjusteStock(int idAjusteStock) {
        this.idAjusteStock = idAjusteStock;
    }

    public int getIdDetAjustesStock() {
        return idDetAjustesStock;
    }

    public void setIdDetAjustesStock(int idDetAjustesStock) {
        this.idDetAjustesStock = idDetAjustesStock;
    }
}
