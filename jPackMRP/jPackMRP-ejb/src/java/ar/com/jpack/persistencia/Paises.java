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
@Table(name = "paises")
@NamedQueries({
@NamedQuery(name = "Paises.findByIdPais", query = "SELECT p FROM Paises p WHERE p.idPais = :idPais"),
@NamedQuery(name = "Paises.findByPais", query = "SELECT p FROM Paises p WHERE p.pais = :pais"),
@NamedQuery(name = "Paises.findBySiglas", query = "SELECT p FROM Paises p WHERE p.siglas = :siglas")
})
public class Paises implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idPais", nullable = false)
    private Integer idPais;
    @Column(name = "pais", nullable = false)
    private String pais;
    @Column(name = "siglas")
    private String siglas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private Collection<Provincias> provinciasCollection;

    public Paises() {
    }

    public Paises(Integer idPais) {
        this.idPais = idPais;
    }

    public Paises(Integer idPais, String pais) {
        this.idPais = idPais;
        this.pais = pais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public Collection<Provincias> getProvinciasCollection() {
        return provinciasCollection;
    }

    public void setProvinciasCollection(Collection<Provincias> provinciasCollection) {
        this.provinciasCollection = provinciasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paises)) {
            return false;
        }
        Paises other = (Paises) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Paises[idPais=" + idPais + "]";
    }
}
