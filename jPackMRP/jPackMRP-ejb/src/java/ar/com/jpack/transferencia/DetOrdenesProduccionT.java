/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author jmhanun
 */
public class DetOrdenesProduccionT implements Serializable {

    protected DetOrdenesProduccionPKT detordenesproduccionPK;
    private int cantidad;
    private ArticulosT idArticulo;
    private OrdenesProduccionT ordenesproduccion;
    private UnidadesMedidaT idUnidMedida;
    private Collection<SellosDetOrdenT> sellosxdetordenCollection;
    private Collection<DetalleProduccionT> detalleproduccionCollection;

    public DetOrdenesProduccionT() {
    }

    public DetOrdenesProduccionT(DetOrdenesProduccionPKT detordenesproduccionPK, int cantidad, ArticulosT idArticulo, OrdenesProduccionT ordenesproduccion, UnidadesMedidaT idUnidMedida) {
        this.detordenesproduccionPK = detordenesproduccionPK;
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.ordenesproduccion = ordenesproduccion;
        this.idUnidMedida = idUnidMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Collection<DetalleProduccionT> getDetalleproduccionCollection() {
        return detalleproduccionCollection;
    }

    public void setDetalleproduccionCollection(Collection<DetalleProduccionT> detalleproduccionCollection) {
        this.detalleproduccionCollection = detalleproduccionCollection;
    }

    public DetOrdenesProduccionPKT getDetordenesproduccionPK() {
        return detordenesproduccionPK;
    }

    public void setDetordenesproduccionPK(DetOrdenesProduccionPKT detordenesproduccionPK) {
        this.detordenesproduccionPK = detordenesproduccionPK;
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

    public OrdenesProduccionT getOrdenesproduccion() {
        return ordenesproduccion;
    }

    public void setOrdenesproduccion(OrdenesProduccionT ordenesproduccion) {
        this.ordenesproduccion = ordenesproduccion;
    }

    public Collection<SellosDetOrdenT> getSellosxdetordenCollection() {
        return sellosxdetordenCollection;
    }

    public void setSellosxdetordenCollection(Collection<SellosDetOrdenT> sellosxdetordenCollection) {
        this.sellosxdetordenCollection = sellosxdetordenCollection;
    }


}
