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
@Table(name = "maquinas")
@NamedQueries({
@NamedQuery(name = "Maquinas.findByIdMaquina", query = "SELECT m FROM Maquinas m WHERE m.idMaquina = :idMaquina"),
@NamedQuery(name = "Maquinas.findByDescripcion", query = "SELECT m FROM Maquinas m WHERE m.descripcion = :descripcion"),
@NamedQuery(name = "Maquinas.findByEstado", query = "SELECT m FROM Maquinas m WHERE m.estado = :estado")
})
public class Maquinas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idMaquina", nullable = false)
    private Integer idMaquina;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "estado", nullable = false)
    private String estado;
    @OneToMany(mappedBy = "idMaquina")
    private Collection<HorasTrabajadas> horastrabajadasCollection;
    @JoinColumn(name = "idTipoMaquina", referencedColumnName = "idTipoMaquina")
    @ManyToOne
    private TipoMaquina idTipoMaquina;

    public Maquinas() {
    }

    public Maquinas(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Maquinas(Integer idMaquina, String descripcion, String estado) {
        this.idMaquina = idMaquina;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<HorasTrabajadas> getHorastrabajadasCollection() {
        return horastrabajadasCollection;
    }

    public void setHorastrabajadasCollection(Collection<HorasTrabajadas> horastrabajadasCollection) {
        this.horastrabajadasCollection = horastrabajadasCollection;
    }

    public TipoMaquina getIdTipoMaquina() {
        return idTipoMaquina;
    }

    public void setIdTipoMaquina(TipoMaquina idTipoMaquina) {
        this.idTipoMaquina = idTipoMaquina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaquina != null ? idMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquinas)) {
            return false;
        }
        Maquinas other = (Maquinas) object;
        if ((this.idMaquina == null && other.idMaquina != null) || (this.idMaquina != null && !this.idMaquina.equals(other.idMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persist.Maquinas[idMaquina=" + idMaquina + "]";
    }
}
