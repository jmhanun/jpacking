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
@Table(name = "ordenesdeposito")
@NamedQueries({@NamedQuery(name = "Ordenesdeposito.findByIdOrdenDeposito", query = "SELECT o FROM Ordenesdeposito o WHERE o.idOrdenDeposito = :idOrdenDeposito"), @NamedQuery(name = "Ordenesdeposito.findByNroOrdenDep", query = "SELECT o FROM Ordenesdeposito o WHERE o.nroOrdenDep = :nroOrdenDep"), @NamedQuery(name = "Ordenesdeposito.findByFecha", query = "SELECT o FROM Ordenesdeposito o WHERE o.fecha = :fecha")})
public class Ordenesdeposito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idOrdenDeposito", nullable = false)
    private Integer idOrdenDeposito;
    @Column(name = "nroOrdenDep", nullable = false)
    private int nroOrdenDep;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(mappedBy = "idOrdenDeposito")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenesdeposito")
    private Collection<Detordenesdeposito> detordenesdepositoCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne
    private Tiposcomprobantes idTipoComprobante;

    public Ordenesdeposito() {
    }

    public Ordenesdeposito(Integer idOrdenDeposito) {
        this.idOrdenDeposito = idOrdenDeposito;
    }

    public Ordenesdeposito(Integer idOrdenDeposito, int nroOrdenDep, Date fecha) {
        this.idOrdenDeposito = idOrdenDeposito;
        this.nroOrdenDep = nroOrdenDep;
        this.fecha = fecha;
    }

    public Integer getIdOrdenDeposito() {
        return idOrdenDeposito;
    }

    public void setIdOrdenDeposito(Integer idOrdenDeposito) {
        this.idOrdenDeposito = idOrdenDeposito;
    }

    public int getNroOrdenDep() {
        return nroOrdenDep;
    }

    public void setNroOrdenDep(int nroOrdenDep) {
        this.nroOrdenDep = nroOrdenDep;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Collection<Detmovimientosstock> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<Detmovimientosstock> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Collection<Detordenesdeposito> getDetordenesdepositoCollection() {
        return detordenesdepositoCollection;
    }

    public void setDetordenesdepositoCollection(Collection<Detordenesdeposito> detordenesdepositoCollection) {
        this.detordenesdepositoCollection = detordenesdepositoCollection;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Tiposcomprobantes getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Tiposcomprobantes idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenDeposito != null ? idOrdenDeposito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenesdeposito)) {
            return false;
        }
        Ordenesdeposito other = (Ordenesdeposito) object;
        if ((this.idOrdenDeposito == null && other.idOrdenDeposito != null) || (this.idOrdenDeposito != null && !this.idOrdenDeposito.equals(other.idOrdenDeposito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Ordenesdeposito[idOrdenDeposito=" + idOrdenDeposito + "]";
    }

}
