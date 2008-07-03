/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "provincias")
@NamedQueries({@NamedQuery(name = "Provincias.findByIdProvincia", query = "SELECT p FROM Provincias p WHERE p.idProvincia = :idProvincia"), @NamedQuery(name = "Provincias.findByProvincia", query = "SELECT p FROM Provincias p WHERE p.provincia = :provincia"), @NamedQuery(name = "Provincias.findByLetra", query = "SELECT p FROM Provincias p WHERE p.letra = :letra")})
public class Provincias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idProvincia", nullable = false)
    private Integer idProvincia;
    @Column(name = "provincia", nullable = false)
    private String provincia;
    @Column(name = "letra")
    private String letra;
    @JoinColumn(name = "idPais", referencedColumnName = "idPais")
    @ManyToOne
    private Paises idPais;
    @OneToMany(mappedBy = "idProvincia")
    private Collection<Localidades> localidadesCollection;

    public Provincias() {
    }

    public Provincias(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Provincias(Integer idProvincia, String provincia) {
        this.idProvincia = idProvincia;
        this.provincia = provincia;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(Paises idPais) {
        this.idPais = idPais;
    }

    public Collection<Localidades> getLocalidadesCollection() {
        return localidadesCollection;
    }

    public void setLocalidadesCollection(Collection<Localidades> localidadesCollection) {
        this.localidadesCollection = localidadesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvincia != null ? idProvincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincias)) {
            return false;
        }
        Provincias other = (Provincias) object;
        if ((this.idProvincia == null && other.idProvincia != null) || (this.idProvincia != null && !this.idProvincia.equals(other.idProvincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Provincias[idProvincia=" + idProvincia + "]";
    }

}
