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
@Table(name = "tiposcomprobantes")
@NamedQueries({@NamedQuery(name = "Tiposcomprobantes.findByIdTipoComprobante", query = "SELECT t FROM Tiposcomprobantes t WHERE t.idTipoComprobante = :idTipoComprobante"), @NamedQuery(name = "Tiposcomprobantes.findByDescripcion", query = "SELECT t FROM Tiposcomprobantes t WHERE t.descripcion = :descripcion"), @NamedQuery(name = "Tiposcomprobantes.findByAbreviatura", query = "SELECT t FROM Tiposcomprobantes t WHERE t.abreviatura = :abreviatura"), @NamedQuery(name = "Tiposcomprobantes.findBySignoStock", query = "SELECT t FROM Tiposcomprobantes t WHERE t.signoStock = :signoStock")})
public class Tiposcomprobantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTipoComprobante", nullable = false)
    private Integer idTipoComprobante;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    @Column(name = "signoStock")
    private String signoStock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private Collection<Ordenesproduccion> ordenesproduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private Collection<Remitos> remitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private Collection<Facturascompras> facturascomprasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private Collection<Remitosingreso> remitosingresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private Collection<Notascredito> notascreditoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private Collection<Facturas> facturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private Collection<Notasdebito> notasdebitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private Collection<Ordenesdeposito> ordenesdepositoCollection;

    public Tiposcomprobantes() {
    }

    public Tiposcomprobantes(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Tiposcomprobantes(Integer idTipoComprobante, String descripcion, String abreviatura) {
        this.idTipoComprobante = idTipoComprobante;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getSignoStock() {
        return signoStock;
    }

    public void setSignoStock(String signoStock) {
        this.signoStock = signoStock;
    }

    public Collection<Ordenesproduccion> getOrdenesproduccionCollection() {
        return ordenesproduccionCollection;
    }

    public void setOrdenesproduccionCollection(Collection<Ordenesproduccion> ordenesproduccionCollection) {
        this.ordenesproduccionCollection = ordenesproduccionCollection;
    }

    public Collection<Remitos> getRemitosCollection() {
        return remitosCollection;
    }

    public void setRemitosCollection(Collection<Remitos> remitosCollection) {
        this.remitosCollection = remitosCollection;
    }

    public Collection<Facturascompras> getFacturascomprasCollection() {
        return facturascomprasCollection;
    }

    public void setFacturascomprasCollection(Collection<Facturascompras> facturascomprasCollection) {
        this.facturascomprasCollection = facturascomprasCollection;
    }

    public Collection<Remitosingreso> getRemitosingresoCollection() {
        return remitosingresoCollection;
    }

    public void setRemitosingresoCollection(Collection<Remitosingreso> remitosingresoCollection) {
        this.remitosingresoCollection = remitosingresoCollection;
    }

    public Collection<Notascredito> getNotascreditoCollection() {
        return notascreditoCollection;
    }

    public void setNotascreditoCollection(Collection<Notascredito> notascreditoCollection) {
        this.notascreditoCollection = notascreditoCollection;
    }

    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    public Collection<Notasdebito> getNotasdebitoCollection() {
        return notasdebitoCollection;
    }

    public void setNotasdebitoCollection(Collection<Notasdebito> notasdebitoCollection) {
        this.notasdebitoCollection = notasdebitoCollection;
    }

    public Collection<Ordenesdeposito> getOrdenesdepositoCollection() {
        return ordenesdepositoCollection;
    }

    public void setOrdenesdepositoCollection(Collection<Ordenesdeposito> ordenesdepositoCollection) {
        this.ordenesdepositoCollection = ordenesdepositoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoComprobante != null ? idTipoComprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposcomprobantes)) {
            return false;
        }
        Tiposcomprobantes other = (Tiposcomprobantes) object;
        if ((this.idTipoComprobante == null && other.idTipoComprobante != null) || (this.idTipoComprobante != null && !this.idTipoComprobante.equals(other.idTipoComprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Tiposcomprobantes[idTipoComprobante=" + idTipoComprobante + "]";
    }

}
