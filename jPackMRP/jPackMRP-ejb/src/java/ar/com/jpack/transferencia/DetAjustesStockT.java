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
public class DetAjustesStockT implements Serializable {

    protected DetAjustesStockPKT detajustesstockPK;
    private int cantidad;
    private AjustesStockT ajustesstock;
    private ArticulosT idArticulo;
    private UnidadesMedidaT idUnidMedida;

    public DetAjustesStockT() {
    }

    public DetAjustesStockT(DetAjustesStockPKT detajustesstockPK, int cantidad, AjustesStockT ajustesstock, ArticulosT idArticulo, UnidadesMedidaT idUnidMedida) {
        this.detajustesstockPK = detajustesstockPK;
        this.cantidad = cantidad;
        this.ajustesstock = ajustesstock;
        this.idArticulo = idArticulo;
        this.idUnidMedida = idUnidMedida;
    }

    public DetAjustesStockT(int idDetAjustesStock, int idAjusteStock, int cantidad, AjustesStockT ajustesstock, ArticulosT idArticulo, UnidadesMedidaT idUnidMedida) {
        this.detajustesstockPK = new DetAjustesStockPKT(idDetAjustesStock, idAjusteStock);
        this.cantidad = cantidad;
        this.ajustesstock = ajustesstock;
        this.idArticulo = idArticulo;
        this.idUnidMedida = idUnidMedida;
    }

    public AjustesStockT getAjustesstock() {
        return ajustesstock;
    }

    public void setAjustesstock(AjustesStockT ajustesstock) {
        this.ajustesstock = ajustesstock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetAjustesStockPKT getDetajustesstockPK() {
        return detajustesstockPK;
    }

    public void setDetajustesstockPK(DetAjustesStockPKT detajustesstockPK) {
        this.detajustesstockPK = detajustesstockPK;
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
}
