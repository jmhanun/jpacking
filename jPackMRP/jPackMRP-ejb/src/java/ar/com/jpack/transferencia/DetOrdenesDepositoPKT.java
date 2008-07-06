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
public class DetOrdenesDepositoPKT implements Serializable {

    private int idDetOrdDeposito;
    private int idOrdenDeposito;

    public DetOrdenesDepositoPKT() {
    }

    public DetOrdenesDepositoPKT(int idDetOrdDeposito, int idOrdenDeposito) {
        this.idDetOrdDeposito = idDetOrdDeposito;
        this.idOrdenDeposito = idOrdenDeposito;
    }

    public int getIdDetOrdDeposito() {
        return idDetOrdDeposito;
    }

    public void setIdDetOrdDeposito(int idDetOrdDeposito) {
        this.idDetOrdDeposito = idDetOrdDeposito;
    }

    public int getIdOrdenDeposito() {
        return idOrdenDeposito;
    }

    public void setIdOrdenDeposito(int idOrdenDeposito) {
        this.idOrdenDeposito = idOrdenDeposito;
    }
    
}
