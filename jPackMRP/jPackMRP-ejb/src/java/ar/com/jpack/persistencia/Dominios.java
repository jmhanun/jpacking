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
@Table(name = "dominios")
@NamedQueries({@NamedQuery(name = "Dominios.findByIdDominio", query = "SELECT d FROM Dominios d WHERE d.idDominio = :idDominio"), @NamedQuery(name = "Dominios.findByDescripcion", query = "SELECT d FROM Dominios d WHERE d.descripcion = :descripcion")})
public class Dominios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idDominio", nullable = false)
    private Integer idDominio;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDominio", fetch = FetchType.LAZY)
    private Collection<Estados> estadosCollection;

    public Dominios() {
    }

    public Dominios(Integer idDominio) {
        this.idDominio = idDominio;
    }

    public Dominios(Integer idDominio, String descripcion) {
        this.idDominio = idDominio;
        this.descripcion = descripcion;
    }

    public Integer getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(Integer idDominio) {
        this.idDominio = idDominio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Estados> getEstadosCollection() {
        return estadosCollection;
    }

    public void setEstadosCollection(Collection<Estados> estadosCollection) {
        this.estadosCollection = estadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDominio != null ? idDominio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dominios)) {
            return false;
        }
        Dominios other = (Dominios) object;
        if ((this.idDominio == null && other.idDominio != null) || (this.idDominio != null && !this.idDominio.equals(other.idDominio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Dominios[idDominio=" + idDominio + "]";
    }

}
