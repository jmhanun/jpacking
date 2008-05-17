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
@Table(name = "listaprecios")
@NamedQueries({
@NamedQuery(name = "Listaprecios.findByIdLista", query = "SELECT l FROM Listaprecios l WHERE l.idLista = :idLista"),
@NamedQuery(name = "Listaprecios.findByFechaDesde", query = "SELECT l FROM Listaprecios l WHERE l.fechaDesde = :fechaDesde"),
@NamedQuery(name = "Listaprecios.findByFechaHasta", query = "SELECT l FROM Listaprecios l WHERE l.fechaHasta = :fechaHasta"),
@NamedQuery(name = "Listaprecios.findByEstado", query = "SELECT l FROM Listaprecios l WHERE l.estado = :estado")
})
public class ListaPrecios implements Serializable {

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
    @Column(name = "estado", nullable = false)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLista")
    private Collection<Precios> preciosCollection;

    public ListaPrecios() {
    }

    public ListaPrecios(Integer idLista) {
        this.idLista = idLista;
    }

    public ListaPrecios(Integer idLista, Date fechaDesde, String estado) {
        this.idLista = idLista;
        this.fechaDesde = fechaDesde;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        if (!(object instanceof ListaPrecios)) {
            return false;
        }
        ListaPrecios other = (ListaPrecios) object;
        if ((this.idLista == null && other.idLista != null) || (this.idLista != null && !this.idLista.equals(other.idLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Listaprecios[idLista=" + idLista + "]";
    }
}
