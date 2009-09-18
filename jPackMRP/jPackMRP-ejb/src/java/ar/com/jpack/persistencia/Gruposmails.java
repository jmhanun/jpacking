/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "gruposmails")
@NamedQueries({@NamedQuery(name = "Gruposmails.findByIdGrupoMail", query = "SELECT g FROM Gruposmails g WHERE g.idGrupoMail = :idGrupoMail"), @NamedQuery(name = "Gruposmails.findByGrupoMail", query = "SELECT g FROM Gruposmails g WHERE g.grupoMail = :grupoMail")})
public class Gruposmails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idGrupoMail", nullable = false)
    private Integer idGrupoMail;
    @Column(name = "grupoMail", nullable = false)
    private String grupoMail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupoMail", fetch = FetchType.LAZY)
    private Collection<Mails> mailsCollection;

    public Gruposmails() {
    }

    public Gruposmails(Integer idGrupoMail) {
        this.idGrupoMail = idGrupoMail;
    }

    public Gruposmails(Integer idGrupoMail, String grupoMail) {
        this.idGrupoMail = idGrupoMail;
        this.grupoMail = grupoMail;
    }

    public Integer getIdGrupoMail() {
        return idGrupoMail;
    }

    public void setIdGrupoMail(Integer idGrupoMail) {
        this.idGrupoMail = idGrupoMail;
    }

    public String getGrupoMail() {
        return grupoMail;
    }

    public void setGrupoMail(String grupoMail) {
        this.grupoMail = grupoMail;
    }

    public Collection<Mails> getMailsCollection() {
        return mailsCollection;
    }

    public void setMailsCollection(Collection<Mails> mailsCollection) {
        this.mailsCollection = mailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoMail != null ? idGrupoMail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gruposmails)) {
            return false;
        }
        Gruposmails other = (Gruposmails) object;
        if ((this.idGrupoMail == null && other.idGrupoMail != null) || (this.idGrupoMail != null && !this.idGrupoMail.equals(other.idGrupoMail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Gruposmails[idGrupoMail=" + idGrupoMail + "]";
    }
}
