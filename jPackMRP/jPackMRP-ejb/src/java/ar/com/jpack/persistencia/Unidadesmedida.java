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
@Table(name = "unidadesmedida")
@NamedQueries({@NamedQuery(name = "Unidadesmedida.findByIdUnidMedida", query = "SELECT u FROM Unidadesmedida u WHERE u.idUnidMedida = :idUnidMedida"), @NamedQuery(name = "Unidadesmedida.findByDescripcion", query = "SELECT u FROM Unidadesmedida u WHERE u.descripcion = :descripcion"), @NamedQuery(name = "Unidadesmedida.findByAbreviatura", query = "SELECT u FROM Unidadesmedida u WHERE u.abreviatura = :abreviatura")})
public class Unidadesmedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idUnidMedida", nullable = false)
    private Integer idUnidMedida;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;

    public Unidadesmedida() {
    }

    public Unidadesmedida(Integer idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    public Unidadesmedida(Integer idUnidMedida, String descripcion, String abreviatura) {
        this.idUnidMedida = idUnidMedida;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public Integer getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(Integer idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Collection<Detmovimientosstock> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<Detmovimientosstock> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidMedida != null ? idUnidMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadesmedida)) {
            return false;
        }
        Unidadesmedida other = (Unidadesmedida) object;
        if ((this.idUnidMedida == null && other.idUnidMedida != null) || (this.idUnidMedida != null && !this.idUnidMedida.equals(other.idUnidMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Unidadesmedida[idUnidMedida=" + idUnidMedida + "]";
    }

}
