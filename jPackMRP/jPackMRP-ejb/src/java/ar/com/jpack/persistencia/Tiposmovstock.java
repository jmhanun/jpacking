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
@Table(name = "tiposmovstock")
@NamedQueries({@NamedQuery(name = "Tiposmovstock.findByIdTipoMov", query = "SELECT t FROM Tiposmovstock t WHERE t.idTipoMov = :idTipoMov"), @NamedQuery(name = "Tiposmovstock.findByDescripcion", query = "SELECT t FROM Tiposmovstock t WHERE t.descripcion = :descripcion"), @NamedQuery(name = "Tiposmovstock.findBySigno", query = "SELECT t FROM Tiposmovstock t WHERE t.signo = :signo")})
public class Tiposmovstock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTipoMov", nullable = false)
    private Integer idTipoMov;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "signo", nullable = false)
    private String signo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMov")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;

    public Tiposmovstock() {
    }

    public Tiposmovstock(Integer idTipoMov) {
        this.idTipoMov = idTipoMov;
    }

    public Tiposmovstock(Integer idTipoMov, String descripcion, String signo) {
        this.idTipoMov = idTipoMov;
        this.descripcion = descripcion;
        this.signo = signo;
    }

    public Integer getIdTipoMov() {
        return idTipoMov;
    }

    public void setIdTipoMov(Integer idTipoMov) {
        this.idTipoMov = idTipoMov;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
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
        hash += (idTipoMov != null ? idTipoMov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposmovstock)) {
            return false;
        }
        Tiposmovstock other = (Tiposmovstock) object;
        if ((this.idTipoMov == null && other.idTipoMov != null) || (this.idTipoMov != null && !this.idTipoMov.equals(other.idTipoMov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Tiposmovstock[idTipoMov=" + idTipoMov + "]";
    }

}
