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
public class DomiciliosT implements Serializable{
    private Integer idDomicilio;
    private String calle;
    private String numero;
    private String barrio;
    private String piso;
    private String departamento;
    private String torre;
    private ClientesT idCliente;
    private EstadosT idEstado;
    private LocalidadesT idLocalidad;
    private ProveedoresT idProveedor;

    public DomiciliosT() {
    }

    public DomiciliosT(Integer idDomicilio, String calle, String numero, String barrio, String piso, String departamento, String torre, ClientesT idCliente, EstadosT idEstado, LocalidadesT idLocalidad, ProveedoresT idProveedor) {
        this.idDomicilio = idDomicilio;
        this.calle = calle;
        this.numero = numero;
        this.barrio = barrio;
        this.piso = piso;
        this.departamento = departamento;
        this.torre = torre;
        this.idCliente = idCliente;
        this.idEstado = idEstado;
        this.idLocalidad = idLocalidad;
        this.idProveedor = idProveedor;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public ClientesT getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ClientesT idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    public LocalidadesT getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(LocalidadesT idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public ProveedoresT getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(ProveedoresT idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTorre() {
        return torre;
    }

    public void setTorre(String torre) {
        this.torre = torre;
    }
    
    
}
