/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia.listas;

import java.io.Serializable;

/**
 *
 * @author jmhanun
 */
public class ClientesListaT implements Serializable {

    private static final long serialVersionUID = 9999L;
    private Integer idCliente;
    private String nombres;
    private String cuit;
    
    public ClientesListaT() {
    }

    public ClientesListaT(Integer idCliente, String nombres, String cuit) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.cuit = cuit;
    }


    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

   
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

}
