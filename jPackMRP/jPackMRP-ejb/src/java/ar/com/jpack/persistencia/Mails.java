/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "mails")
@NamedQueries({@NamedQuery(name = "Mails.findByIdMail", query = "SELECT m FROM Mails m WHERE m.idMail = :idMail")})
public class Mails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMail", nullable = false)
    private Integer idMail;
    @JoinColumn(name = "idGrupoMail", referencedColumnName = "idGrupoMail")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gruposmails idGrupoMail;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;

    public Mails() {
    }

    public Mails(Integer idMail) {
        this.idMail = idMail;
    }

    public Integer getIdMail() {
        return idMail;
    }

    public void setIdMail(Integer idMail) {
        this.idMail = idMail;
    }

    public Gruposmails getIdGrupoMail() {
        return idGrupoMail;
    }

    public void setIdGrupoMail(Gruposmails idGrupoMail) {
        this.idGrupoMail = idGrupoMail;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMail != null ? idMail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mails)) {
            return false;
        }
        Mails other = (Mails) object;
        if ((this.idMail == null && other.idMail != null) || (this.idMail != null && !this.idMail.equals(other.idMail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Mails[idMail=" + idMail + "]";
    }
}
