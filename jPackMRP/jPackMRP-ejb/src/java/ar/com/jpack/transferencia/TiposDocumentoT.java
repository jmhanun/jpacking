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
public class TiposDocumentoT implements Serializable {
    
    private Integer idTipoDocumento;
    private String descripcion;
    private String abreviatura;
    private Collection<ClientesT> clientesCollection;
    private Collection<ProveedoresT> proveedoresCollection;
    private Collection<EmpleadosT> empleadosCollection;

    public TiposDocumentoT() {
    }

    public TiposDocumentoT(Integer idTipoDocumento, String descripcion, String abreviatura) {
        this.idTipoDocumento = idTipoDocumento;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Collection<ClientesT> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<ClientesT> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<EmpleadosT> getEmpleadosCollection() {
        return empleadosCollection;
    }

    public void setEmpleadosCollection(Collection<EmpleadosT> empleadosCollection) {
        this.empleadosCollection = empleadosCollection;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Collection<ProveedoresT> getProveedoresCollection() {
        return proveedoresCollection;
    }

    public void setProveedoresCollection(Collection<ProveedoresT> proveedoresCollection) {
        this.proveedoresCollection = proveedoresCollection;
    }
    
}
