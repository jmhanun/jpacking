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
public class SellosDetOrdenPKT implements Serializable {

    private int idSello;
    private int idCliente;
    private int idOrdenProduccion;
    private int idDetOrdProduccion;

    public SellosDetOrdenPKT() {
    }

    public SellosDetOrdenPKT(int idSello, int idCliente, int idOrdenProduccion, int idDetOrdProduccion) {
        this.idSello = idSello;
        this.idCliente = idCliente;
        this.idOrdenProduccion = idOrdenProduccion;
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDetOrdProduccion() {
        return idDetOrdProduccion;
    }

    public void setIdDetOrdProduccion(int idDetOrdProduccion) {
        this.idDetOrdProduccion = idDetOrdProduccion;
    }

    public int getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(int idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public int getIdSello() {
        return idSello;
    }

    public void setIdSello(int idSello) {
        this.idSello = idSello;
    }

    
}
