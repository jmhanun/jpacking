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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "clientes")
@NamedQueries({@NamedQuery(name = "Clientes.findByIdCliente", query = "SELECT c FROM Clientes c WHERE c.idCliente = :idCliente"), @NamedQuery(name = "Clientes.findBySituacionIva", query = "SELECT c FROM Clientes c WHERE c.situacionIva = :situacionIva"), @NamedQuery(name = "Clientes.findByLimiteCredito", query = "SELECT c FROM Clientes c WHERE c.limiteCredito = :limiteCredito"), @NamedQuery(name = "Clientes.findByObservaciones", query = "SELECT c FROM Clientes c WHERE c.observaciones = :observaciones"), @NamedQuery(name = "Clientes.findByEstado", query = "SELECT c FROM Clientes c WHERE c.estado = :estado")})
public class Clientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;
    @Column(name = "situacionIva", nullable = false)
    private String situacionIva;
    @Column(name = "limiteCredito", nullable = false)
    private int limiteCredito;
    @Column(name = "observaciones", nullable = false)
    private String observaciones;
    @Column(name = "estado", nullable = false)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Remitos> remitosCollection;
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    @ManyToOne
    private Personas idPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Facturas> facturasCollection;

    public Clientes() {
    }

    public Clientes(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Clientes(Integer idCliente, String situacionIva, int limiteCredito, String observaciones, String estado) {
        this.idCliente = idCliente;
        this.situacionIva = situacionIva;
        this.limiteCredito = limiteCredito;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getSituacionIva() {
        return situacionIva;
    }

    public void setSituacionIva(String situacionIva) {
        this.situacionIva = situacionIva;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(int limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Remitos> getRemitosCollection() {
        return remitosCollection;
    }

    public void setRemitosCollection(Collection<Remitos> remitosCollection) {
        this.remitosCollection = remitosCollection;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Clientes[idCliente=" + idCliente + "]";
    }

}
