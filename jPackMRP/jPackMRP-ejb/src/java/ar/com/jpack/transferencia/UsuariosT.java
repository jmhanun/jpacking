/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class UsuariosT implements Serializable {
    private Integer idUsuario;
    private String usuario;
    private String contrasena;
    private Date ultimoAcceso;
    private String tipoUsuario;
    private String estado;
    private String nombres;
    private String apellidos;
    private String mails;
    private String telefonos;
    private Collection<RolesT> coleccionRolesT;

    public UsuariosT() {
        this.coleccionRolesT = new ArrayList<RolesT>();
    }

    public UsuariosT(Integer idUsuario, String usuario, String contrasena, Date ultimoAcceso, String tipoUsuario, String estado, String nombres, String apellidos, String mails, String telefonos) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.ultimoAcceso = ultimoAcceso;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.mails = mails;
        this.telefonos = telefonos;
        this.coleccionRolesT = new ArrayList<RolesT>();
    }

    public UsuariosT(Integer idUsuario, String usuario, String contrasena, Date ultimoAcceso, String tipoUsuario, String estado, String nombres, String apellidos, String mails, String telefonos, Collection<RolesT> coleccionRolesT) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.ultimoAcceso = ultimoAcceso;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.mails = mails;
        this.telefonos = telefonos;
        this.coleccionRolesT = coleccionRolesT;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Collection<RolesT> getColeccionRolesT() {
        return coleccionRolesT;
    }

    public void setColeccionRolesT(Collection<RolesT> coleccionRolesT) {
        this.coleccionRolesT = coleccionRolesT;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
