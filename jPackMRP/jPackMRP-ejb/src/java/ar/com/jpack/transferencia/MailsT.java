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
public class MailsT implements Serializable {

    private Integer idMail;
    private GruposMailsT idGrupoMail;
    private UsuariosT idUsuario;

    public MailsT() {
    }

    public MailsT(Integer idMail, GruposMailsT idGrupoMail, UsuariosT idUsuario) {
        this.idMail = idMail;
        this.idGrupoMail = idGrupoMail;
        this.idUsuario = idUsuario;
    }

    public GruposMailsT getIdGrupoMail() {
        return idGrupoMail;
    }

    public void setIdGrupoMail(GruposMailsT idGrupoMail) {
        this.idGrupoMail = idGrupoMail;
    }

    public Integer getIdMail() {
        return idMail;
    }

    public void setIdMail(Integer idMail) {
        this.idMail = idMail;
    }

    public UsuariosT getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosT idUsuario) {
        this.idUsuario = idUsuario;
    }
}
