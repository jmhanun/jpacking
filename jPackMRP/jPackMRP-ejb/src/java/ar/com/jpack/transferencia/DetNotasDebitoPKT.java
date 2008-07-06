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
public class DetNotasDebitoPKT implements Serializable {

    private int idDetNotaDebito;
    private int idNotaDebito;

    public DetNotasDebitoPKT() {
    }

    public DetNotasDebitoPKT(int idDetNotaDebito, int idNotaDebito) {
        this.idDetNotaDebito = idDetNotaDebito;
        this.idNotaDebito = idNotaDebito;
    }

    public int getIdDetNotaDebito() {
        return idDetNotaDebito;
    }

    public void setIdDetNotaDebito(int idDetNotaDebito) {
        this.idDetNotaDebito = idDetNotaDebito;
    }

    public int getIdNotaDebito() {
        return idNotaDebito;
    }

    public void setIdNotaDebito(int idNotaDebito) {
        this.idNotaDebito = idNotaDebito;
    }
}
