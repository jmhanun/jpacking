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
public class DetNotasCreditoPKT implements Serializable {

    private int idDetNotaCredito;
    private int idNotaCredito;

    public DetNotasCreditoPKT() {
    }

    public DetNotasCreditoPKT(int idDetNotaCredito, int idNotaCredito) {
        this.idDetNotaCredito = idDetNotaCredito;
        this.idNotaCredito = idNotaCredito;
    }

    public int getIdDetNotaCredito() {
        return idDetNotaCredito;
    }

    public void setIdDetNotaCredito(int idDetNotaCredito) {
        this.idDetNotaCredito = idDetNotaCredito;
    }

    public int getIdNotaCredito() {
        return idNotaCredito;
    }

    public void setIdNotaCredito(int idNotaCredito) {
        this.idNotaCredito = idNotaCredito;
    }
}
