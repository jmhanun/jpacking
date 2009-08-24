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
@Table(name = "tiposdesvios")
@NamedQueries({@NamedQuery(name = "Tiposdesvios.findByIdTipoDesvio", query = "SELECT t FROM Tiposdesvios t WHERE t.idTipoDesvio = :idTipoDesvio"), @NamedQuery(name = "Tiposdesvios.findByMotivo", query = "SELECT t FROM Tiposdesvios t WHERE t.motivo = :motivo")})
public class Tiposdesvios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTipoDesvio", nullable = false)
    private Integer idTipoDesvio;
    @Column(name = "motivo", nullable = false)
    private String motivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipodesvio")
    private Collection<Desvios> desviosCollection;

    public Tiposdesvios() {
    }

    public Tiposdesvios(Integer idTipoDesvio) {
        this.idTipoDesvio = idTipoDesvio;
    }

    public Tiposdesvios(Integer idTipoDesvio, String motivo) {
        this.idTipoDesvio = idTipoDesvio;
        this.motivo = motivo;
    }

    public Integer getIdTipoDesvio() {
        return idTipoDesvio;
    }

    public void setIdTipoDesvio(Integer idTipoDesvio) {
        this.idTipoDesvio = idTipoDesvio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Collection<Desvios> getDesviosCollection() {
        return desviosCollection;
    }

    public void setDesviosCollection(Collection<Desvios> desviosCollection) {
        this.desviosCollection = desviosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDesvio != null ? idTipoDesvio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposdesvios)) {
            return false;
        }
        Tiposdesvios other = (Tiposdesvios) object;
        if ((this.idTipoDesvio == null && other.idTipoDesvio != null) || (this.idTipoDesvio != null && !this.idTipoDesvio.equals(other.idTipoDesvio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Tiposdesvios[idTipoDesvio=" + idTipoDesvio + "]";
    }

}
