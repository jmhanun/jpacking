/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.transferencia;

import java.io.Serializable;

/**
 *
 * @author Pablo
 */
public class RolesT implements Serializable {
    
    private Integer idRol;
    private String rol;
    private String descripcion;
    
 
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public RolesT(Integer idRol, String rol, String descripcion) {
        this.idRol = idRol;
        this.rol = rol;
        this.descripcion = descripcion;
    }

    public RolesT(String rol, String descripcion) {
        this.rol = rol;
        this.descripcion = descripcion;
    }

    public RolesT() {
    }
    
    
}
