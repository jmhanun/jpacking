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
public class DetrtosIngresoPKT implements Serializable {

    private int idDetRtoIngreso;
    private int idRtoIngreso;

    public DetrtosIngresoPKT() {
    }

    public DetrtosIngresoPKT(int idDetRtoIngreso, int idRtoIngreso) {
        this.idDetRtoIngreso = idDetRtoIngreso;
        this.idRtoIngreso = idRtoIngreso;
    }

    public int getIdDetRtoIngreso() {
        return idDetRtoIngreso;
    }

    public void setIdDetRtoIngreso(int idDetRtoIngreso) {
        this.idDetRtoIngreso = idDetRtoIngreso;
    }

    public int getIdRtoIngreso() {
        return idRtoIngreso;
    }

    public void setIdRtoIngreso(int idRtoIngreso) {
        this.idRtoIngreso = idRtoIngreso;
    }
}
