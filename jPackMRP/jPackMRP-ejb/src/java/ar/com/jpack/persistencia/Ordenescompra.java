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
import javax.persistence.FetchType;
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
@Table(name = "ordenescompra")
@NamedQueries({@NamedQuery(name = "Ordenescompra.findByIdOrdenCompra", query = "SELECT o FROM Ordenescompra o WHERE o.idOrdenCompra = :idOrdenCompra"), @NamedQuery(name = "Ordenescompra.findByNroOrdenCompra", query = "SELECT o FROM Ordenescompra o WHERE o.nroOrdenCompra = :nroOrdenCompra"), @NamedQuery(name = "Ordenescompra.findByFecha", query = "SELECT o FROM Ordenescompra o WHERE o.fecha = :fecha")})
public class Ordenescompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idOrdenCompra", nullable = false)
    private Integer idOrdenCompra;
    @Column(name = "nroOrdenCompra", nullable = false)
    private int nroOrdenCompra;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proveedores idProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenescompra", fetch = FetchType.LAZY)
    private Collection<Detalleordenescompras> detalleordenescomprasCollection;

    public Ordenescompra() {
    }

    public Ordenescompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Ordenescompra(Integer idOrdenCompra, int nroOrdenCompra, Date fecha) {
        this.idOrdenCompra = idOrdenCompra;
        this.nroOrdenCompra = nroOrdenCompra;
        this.fecha = fecha;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public int getNroOrdenCompra() {
        return nroOrdenCompra;
    }

    public void setNroOrdenCompra(int nroOrdenCompra) {
        this.nroOrdenCompra = nroOrdenCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Collection<Detalleordenescompras> getDetalleordenescomprasCollection() {
        return detalleordenescomprasCollection;
    }

    public void setDetalleordenescomprasCollection(Collection<Detalleordenescompras> detalleordenescomprasCollection) {
        this.detalleordenescomprasCollection = detalleordenescomprasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenCompra != null ? idOrdenCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenescompra)) {
            return false;
        }
        Ordenescompra other = (Ordenescompra) object;
        if ((this.idOrdenCompra == null && other.idOrdenCompra != null) || (this.idOrdenCompra != null && !this.idOrdenCompra.equals(other.idOrdenCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Ordenescompra[idOrdenCompra=" + idOrdenCompra + "]";
    }

}
