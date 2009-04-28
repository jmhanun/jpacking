/*
 * RolesT.java
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author jmhanun
 */
public class RolesT implements Serializable, Comparable<RolesT> {

    private Integer idRol;
    private String rol;
    private String descripcion;
    private String componente;
    private String funcion;
    private int orden;
    private int ordenHermano;
    private Collection<UsuariosT> idUsuarioCollection;
    private Collection<RolesT> rolesCollection;
    private RolesT idRolPadre;

    public RolesT() {
    }

    public RolesT(Integer idRol, String rol, String descripcion, String componente, String funcion, int orden, int ordenHermano, RolesT idRolPadre) {
        this.idRol = idRol;
        this.rol = rol;
        this.descripcion = descripcion;
        this.componente = componente;
        this.funcion = funcion;
        this.orden = orden;
        this.ordenHermano = ordenHermano;
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrdenHermano() {
        return ordenHermano;
    }

    public void setOrdenHermano(int ordenHermano) {
        this.ordenHermano = ordenHermano;
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

    @Override
    public String toString() {
        return rol;
    }

    public int compareTo(RolesT rolesT) {
        int resultValue = 0;
        Integer thisOrden = this.orden;
        Integer thisHermano = this.ordenHermano;
        Integer anotherOrden = rolesT.getOrden();
        Integer anotherHermano = rolesT.getOrdenHermano();
        resultValue = thisOrden.compareTo(anotherOrden);
        if(resultValue == 0) {
            resultValue = thisHermano.compareTo(anotherHermano);
        }
        return resultValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RolesT other = (RolesT) obj;
        if (this.idRol != other.idRol && (this.idRol == null || !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.idRol != null ? this.idRol.hashCode() : 0);
        return hash;
    }
}
