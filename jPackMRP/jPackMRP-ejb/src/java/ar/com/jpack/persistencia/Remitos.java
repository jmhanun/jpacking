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
@Table(name = "remitos")
@NamedQueries({
@NamedQuery(name = "Remitos.findByIdRemito", query = "SELECT r FROM Remitos r WHERE r.idRemito = :idRemito"),
@NamedQuery(name = "Remitos.findByNroRemito", query = "SELECT r FROM Remitos r WHERE r.nroRemito = :nroRemito"),
@NamedQuery(name = "Remitos.findByFecha", query = "SELECT r FROM Remitos r WHERE r.fecha = :fecha"),
@NamedQuery(name = "Remitos.findByImporte", query = "SELECT r FROM Remitos r WHERE r.importe = :importe"),
@NamedQuery(name = "Remitos.findByEstado", query = "SELECT r FROM Remitos r WHERE r.estado = :estado")
})
public class Remitos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idRemito", nullable = false)
    private Integer idRemito;
    @Column(name = "nroRemito", nullable = false)
    private Integer nroRemito;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "importe", nullable = false)
    private Double importe;
    @Column(name = "estado", nullable = false)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRemito")
    private Collection<DetalleRemitos> detalleremitosCollection;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne
    private Clientes idCliente;

    public Remitos() {
    }

    public Remitos(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Remitos(Integer idRemito, Integer nroRemito, Date fecha, Double importe, String estado) {
        this.idRemito = idRemito;
        this.nroRemito = nroRemito;
        this.fecha = fecha;
        this.importe = importe;
        this.estado = estado;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Integer getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(Integer nroRemito) {
        this.nroRemito = nroRemito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<DetalleRemitos> getDetalleremitosCollection() {
        return detalleremitosCollection;
    }

    public void setDetalleremitosCollection(Collection<DetalleRemitos> detalleremitosCollection) {
        this.detalleremitosCollection = detalleremitosCollection;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRemito != null ? idRemito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remitos)) {
            return false;
        }
        Remitos other = (Remitos) object;
        if ((this.idRemito == null && other.idRemito != null) || (this.idRemito != null && !this.idRemito.equals(other.idRemito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Remitos[idRemito=" + idRemito + "]";
    }
}
