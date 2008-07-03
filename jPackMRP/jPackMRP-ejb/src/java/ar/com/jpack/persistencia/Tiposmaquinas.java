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
@Table(name = "tiposmaquinas")
@NamedQueries({@NamedQuery(name = "Tiposmaquinas.findByIdTipoMaquina", query = "SELECT t FROM Tiposmaquinas t WHERE t.idTipoMaquina = :idTipoMaquina"), @NamedQuery(name = "Tiposmaquinas.findByDescripcion", query = "SELECT t FROM Tiposmaquinas t WHERE t.descripcion = :descripcion")})
public class Tiposmaquinas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTipoMaquina", nullable = false)
    private Integer idTipoMaquina;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMaquina")
    private Collection<Maquinas> maquinasCollection;

    public Tiposmaquinas() {
    }

    public Tiposmaquinas(Integer idTipoMaquina) {
        this.idTipoMaquina = idTipoMaquina;
    }

    public Tiposmaquinas(Integer idTipoMaquina, String descripcion) {
        this.idTipoMaquina = idTipoMaquina;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoMaquina() {
        return idTipoMaquina;
    }

    public void setIdTipoMaquina(Integer idTipoMaquina) {
        this.idTipoMaquina = idTipoMaquina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Maquinas> getMaquinasCollection() {
        return maquinasCollection;
    }

    public void setMaquinasCollection(Collection<Maquinas> maquinasCollection) {
        this.maquinasCollection = maquinasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMaquina != null ? idTipoMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposmaquinas)) {
            return false;
        }
        Tiposmaquinas other = (Tiposmaquinas) object;
        if ((this.idTipoMaquina == null && other.idTipoMaquina != null) || (this.idTipoMaquina != null && !this.idTipoMaquina.equals(other.idTipoMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Tiposmaquinas[idTipoMaquina=" + idTipoMaquina + "]";
    }

}
