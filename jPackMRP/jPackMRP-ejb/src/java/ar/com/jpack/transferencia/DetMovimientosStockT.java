/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class DetMovimientosStockT implements Serializable{
    private Integer idDetMovStock;
    private String descripcion;
    private double cantidad;
    private Date fechaMovimiento;
    private AjustesStockT idAjusteStock;
    private ArticulosT idArticulo;
    private NotasCreditoT idNotaCredito;
    private NotasDebitoT idNotaDebito;
    private OrdenesDepositoT idOrdenDeposito;
    private OrdenesProduccionT idOrdenProduccion;
    private RemitosT idRemito;
    private RemitosIngresoT idRtoIngreso;
    private UnidadesMedidaT idUnidMedida;

    public DetMovimientosStockT() {
    }

    public DetMovimientosStockT(Integer idDetMovStock, 
            String descripcion, double cantidad, Date fechaMovimiento, AjustesStockT idAjusteStock,
            ArticulosT idArticulo, NotasCreditoT idNotaCredito, NotasDebitoT idNotaDebito, OrdenesDepositoT idOrdenDeposito, OrdenesProduccionT idOrdenProduccion, RemitosT idRemito, RemitosIngresoT idRtoIngreso, UnidadesMedidaT idUnidMedida) {
        this.idDetMovStock = idDetMovStock;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.idAjusteStock = idAjusteStock;
        this.idArticulo = idArticulo;
        this.idNotaCredito = idNotaCredito;
        this.idNotaDebito = idNotaDebito;
        this.idOrdenDeposito = idOrdenDeposito;
        this.idOrdenProduccion = idOrdenProduccion;
        this.idRemito = idRemito;
        this.idRtoIngreso = idRtoIngreso;
        this.idUnidMedida = idUnidMedida;
    }

    public AjustesStockT getIdAjusteStock() {
        return idAjusteStock;
    }

    public void setIdAjusteStock(AjustesStockT idAjusteStock) {
        this.idAjusteStock = idAjusteStock;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArticulosT getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(ArticulosT idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdDetMovStock() {
        return idDetMovStock;
    }

    public void setIdDetMovStock(Integer idDetMovStock) {
        this.idDetMovStock = idDetMovStock;
    }

    public NotasCreditoT getIdNotaCredito() {
        return idNotaCredito;
    }

    public void setIdNotaCredito(NotasCreditoT idNotaCredito) {
        this.idNotaCredito = idNotaCredito;
    }

    public NotasDebitoT getIdNotaDebito() {
        return idNotaDebito;
    }

    public void setIdNotaDebito(NotasDebitoT idNotaDebito) {
        this.idNotaDebito = idNotaDebito;
    }

    public OrdenesDepositoT getIdOrdenDeposito() {
        return idOrdenDeposito;
    }

    public void setIdOrdenDeposito(OrdenesDepositoT idOrdenDeposito) {
        this.idOrdenDeposito = idOrdenDeposito;
    }

    public OrdenesProduccionT getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(OrdenesProduccionT idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public RemitosT getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(RemitosT idRemito) {
        this.idRemito = idRemito;
    }

    public RemitosIngresoT getIdRtoIngreso() {
        return idRtoIngreso;
    }

    public void setIdRtoIngreso(RemitosIngresoT idRtoIngreso) {
        this.idRtoIngreso = idRtoIngreso;
    }

    public UnidadesMedidaT getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(UnidadesMedidaT idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }
}
