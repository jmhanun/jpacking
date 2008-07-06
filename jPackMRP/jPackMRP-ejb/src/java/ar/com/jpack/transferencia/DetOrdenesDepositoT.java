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
public class DetOrdenesDepositoT implements Serializable {

    protected DetOrdenesDepositoPKT detordenesdepositoPK;
    private int cantidad;
    private ArticulosT idArticulo;
    private OrdenesDepositoT ordenesdeposito;
    private UnidadesMedidaT idUnidMedida;

    public DetOrdenesDepositoT() {
    }

    public DetOrdenesDepositoT(DetOrdenesDepositoPKT detordenesdepositoPK, int cantidad, ArticulosT idArticulo, OrdenesDepositoT ordenesdeposito, UnidadesMedidaT idUnidMedida) {
        this.detordenesdepositoPK = detordenesdepositoPK;
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.ordenesdeposito = ordenesdeposito;
        this.idUnidMedida = idUnidMedida;
    }

    public DetOrdenesDepositoT(int idDetOrdDeposito, int idOrdenDeposito, int cantidad, ArticulosT idArticulo, OrdenesDepositoT ordenesdeposito, UnidadesMedidaT idUnidMedida) {
        this.detordenesdepositoPK = new DetOrdenesDepositoPKT(idDetOrdDeposito, idOrdenDeposito);
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.ordenesdeposito = ordenesdeposito;
        this.idUnidMedida = idUnidMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetOrdenesDepositoPKT getDetordenesdepositoPK() {
        return detordenesdepositoPK;
    }

    public void setDetordenesdepositoPK(DetOrdenesDepositoPKT detordenesdepositoPK) {
        this.detordenesdepositoPK = detordenesdepositoPK;
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

    public OrdenesDepositoT getOrdenesdeposito() {
        return ordenesdeposito;
    }

    public void setOrdenesdeposito(OrdenesDepositoT ordenesdeposito) {
        this.ordenesdeposito = ordenesdeposito;
    }
}
