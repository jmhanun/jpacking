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
public class GruposMailsT implements Serializable {

    private Integer idGrupoMail;
    private String grupoMail;
    private Collection<MailsT> mailsCollection;

    public GruposMailsT() {
    }

    public GruposMailsT(Integer idGrupoMail, String grupoMail) {
        this.idGrupoMail = idGrupoMail;
        this.grupoMail = grupoMail;
    }

    public String getGrupoMail() {
        return grupoMail;
    }

    public void setGrupoMail(String grupoMail) {
        this.grupoMail = grupoMail;
    }

    public Integer getIdGrupoMail() {
        return idGrupoMail;
    }

    public void setIdGrupoMail(Integer idGrupoMail) {
        this.idGrupoMail = idGrupoMail;
    }

    public Collection<MailsT> getMailsCollection() {
        return mailsCollection;
    }

    public void setMailsCollection(Collection<MailsT> mailsCollection) {
        this.mailsCollection = mailsCollection;
    }

    @Override
    public String toString() {
        return this.getGrupoMail();
    }

}
