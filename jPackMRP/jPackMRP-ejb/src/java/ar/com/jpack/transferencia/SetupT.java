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
public class SetupT implements Serializable {
    private Integer idSetup;
    private String descripcion;
    private String valor;
    private Date fechaModificacion;
    private UsuariosT idUsuario;

    public SetupT() {
    }

    public SetupT(Integer idSetup, String descripcion, String valor, Date fechaModificacion, UsuariosT idUsuario) {
        this.idSetup = idSetup;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fechaModificacion = fechaModificacion;
        this.idUsuario = idUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdSetup() {
        return idSetup;
    }

    public void setIdSetup(Integer idSetup) {
        this.idSetup = idSetup;
    }

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
