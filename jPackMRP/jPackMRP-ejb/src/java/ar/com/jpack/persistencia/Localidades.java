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
@Table(name = "localidades")
@NamedQueries({@NamedQuery(name = "Localidades.findByIdLocalidad", query = "SELECT l FROM Localidades l WHERE l.idLocalidad = :idLocalidad"), @NamedQuery(name = "Localidades.findByLocalidad", query = "SELECT l FROM Localidades l WHERE l.localidad = :localidad"), @NamedQuery(name = "Localidades.findByCodigoPostal", query = "SELECT l FROM Localidades l WHERE l.codigoPostal = :codigoPostal")})
public class Localidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idLocalidad", nullable = false)
    private Integer idLocalidad;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "codigoPostal")
    private String codigoPostal;
    @OneToMany(mappedBy = "idLocalidad")
    private Collection<Domicilios> domiciliosCollection;
    @JoinColumn(name = "idProvincia", referencedColumnName = "idProvincia")
    @ManyToOne
    private Provincias idProvincia;

    public Localidades() {
    }

    public Localidades(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Collection<Domicilios> getDomiciliosCollection() {
        return domiciliosCollection;
    }

    public void setDomiciliosCollection(Collection<Domicilios> domiciliosCollection) {
        this.domiciliosCollection = domiciliosCollection;
    }

    public Provincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincias idProvincia) {
        this.idProvincia = idProvincia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalidad != null ? idLocalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidades)) {
            return false;
        }
        Localidades other = (Localidades) object;
        if ((this.idLocalidad == null && other.idLocalidad != null) || (this.idLocalidad != null && !this.idLocalidad.equals(other.idLocalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Localidades[idLocalidad=" + idLocalidad + "]";
    }

}
