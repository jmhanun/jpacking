/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "listasprecios")
@NamedQueries({@NamedQuery(name = "Listasprecios.findByIdLista", query = "SELECT l FROM Listasprecios l WHERE l.idLista = :idLista"), @NamedQuery(name = "Listasprecios.findByFechaDesde", query = "SELECT l FROM Listasprecios l WHERE l.fechaDesde = :fechaDesde"), @NamedQuery(name = "Listasprecios.findByFechaHasta", query = "SELECT l FROM Listasprecios l WHERE l.fechaHasta = :fechaHasta"), @NamedQuery(name = "Listasprecios.findByFechaModificacion", query = "SELECT l FROM Listasprecios l WHERE l.fechaModificacion = :fechaModificacion")})
public class Listasprecios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idLista", nullable = false)
    private Integer idLista;
    @Column(name = "fechaDesde", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;
    @Column(name = "fechaHasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLista")
    private Collection<Precios> preciosCollection;

    public Listasprecios() {
    }

    public Listasprecios(Integer idLista) {
        this.idLista = idLista;
    }

    public Listasprecios(Integer idLista, Date fechaDesde, Date fechaModificacion) {
        this.idLista = idLista;
        this.fechaDesde = fechaDesde;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Collection<Precios> getPreciosCollection() {
        return preciosCollection;
    }

    public void setPreciosCollection(Collection<Precios> preciosCollection) {
        this.preciosCollection = preciosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLista != null ? idLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listasprecios)) {
            return false;
        }
        Listasprecios other = (Listasprecios) object;
        if ((this.idLista == null && other.idLista != null) || (this.idLista != null && !this.idLista.equals(other.idLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Listasprecios[idLista=" + idLista + "]";
    }

}
