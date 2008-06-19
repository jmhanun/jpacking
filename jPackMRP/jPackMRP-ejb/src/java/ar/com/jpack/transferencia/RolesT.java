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
public class RolesT implements Serializable {
    
    private Integer idRol;
    private String rol;
    private String descripcion;
    private String componente;
    private String funcion;
    private Collection<UsuariosT> idUsuarioCollection;
    private Collection<RolesT> rolesCollection;
    private RolesT idRolPadre;

    public RolesT() {
    }

    public RolesT(Integer idRol, String rol, String descripcion, String componente, String funcion, Collection<UsuariosT> idUsuarioCollection, Collection<RolesT> rolesCollection, RolesT idRolPadre) {
        this.idRol = idRol;
        this.rol = rol;
        this.descripcion = descripcion;
        this.componente = componente;
        this.funcion = funcion;
        this.idUsuarioCollection = idUsuarioCollection;
        this.rolesCollection = rolesCollection;
        this.idRolPadre = idRolPadre;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public RolesT getIdRolPadre() {
        return idRolPadre;
    }

    public void setIdRolPadre(RolesT idRolPadre) {
        this.idRolPadre = idRolPadre;
    }

    public Collection<UsuariosT> getIdUsuarioCollection() {
        return idUsuarioCollection;
    }

    public void setIdUsuarioCollection(Collection<UsuariosT> idUsuarioCollection) {
        this.idUsuarioCollection = idUsuarioCollection;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Collection<RolesT> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<RolesT> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }
    
}
