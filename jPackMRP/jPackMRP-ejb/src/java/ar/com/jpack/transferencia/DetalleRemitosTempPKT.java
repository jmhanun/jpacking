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
public class DetalleRemitosTempPKT implements Serializable {

    private int instancia;
    private int idarticulo;
    private int idactividad;

    public DetalleRemitosTempPKT() {
    }

    public DetalleRemitosTempPKT(int instancia, int idarticulo, int idactividad) {
        this.instancia = instancia;
        this.idarticulo = idarticulo;
        this.idactividad = idactividad;
    }

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getInstancia() {
        return instancia;
    }

    public void setInstancia(int instancia) {
        this.instancia = instancia;
    }
}
