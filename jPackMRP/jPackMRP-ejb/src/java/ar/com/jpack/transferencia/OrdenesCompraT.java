/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class OrdenesCompraT implements Serializable {

    private Integer idOrdenCompra;
    private int nroOrdenCompra;
    private Date fecha;
    private ProveedoresT idProveedor;
    private Collection<DetalleOrdenesComprasT> detalleordenescomprasCollection;

    public OrdenesCompraT() {
    }

    public OrdenesCompraT(Integer idOrdenCompra, int nroOrdenCompra, Date fecha, ProveedoresT idProveedor) {
        this.idOrdenCompra = idOrdenCompra;
        this.nroOrdenCompra = nroOrdenCompra;
        this.fecha = fecha;
        this.idProveedor = idProveedor;
    }

    public Collection<DetalleOrdenesComprasT> getDetalleordenescomprasCollection() {
        return detalleordenescomprasCollection;
    }

    public void setDetalleordenescomprasCollection(Collection<DetalleOrdenesComprasT> detalleordenescomprasCollection) {
        this.detalleordenescomprasCollection = detalleordenescomprasCollection;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public ProveedoresT getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(ProveedoresT idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getNroOrdenCompra() {
        return nroOrdenCompra;
    }

    public void setNroOrdenCompra(int nroOrdenCompra) {
        this.nroOrdenCompra = nroOrdenCompra;
    }
}
