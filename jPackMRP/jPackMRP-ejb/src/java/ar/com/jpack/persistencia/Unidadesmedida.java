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
import javax.persistence.FetchType;
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
@Table(name = "unidadesmedida")
@NamedQueries({@NamedQuery(name = "Unidadesmedida.findByIdUnidMedida", query = "SELECT u FROM Unidadesmedida u WHERE u.idUnidMedida = :idUnidMedida"), @NamedQuery(name = "Unidadesmedida.findByDescripcion", query = "SELECT u FROM Unidadesmedida u WHERE u.descripcion = :descripcion"), @NamedQuery(name = "Unidadesmedida.findByAbreviatura", query = "SELECT u FROM Unidadesmedida u WHERE u.abreviatura = :abreviatura")})
public class Unidadesmedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idUnidMedida", nullable = false)
    private Integer idUnidMedida;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detnotascredito> detnotascreditoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Articulos> articulosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detordenesproduccion> detordenesproduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detallefactcompras> detallefactcomprasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detnotasdebito> detnotasdebitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detallefacturas> detallefacturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detalleremitos> detalleremitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detajustesstock> detajustesstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detordenesdeposito> detordenesdepositoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detrtosingreso> detrtosingresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidMedida", fetch = FetchType.LAZY)
    private Collection<Detalleordenescompras> detalleordenescomprasCollection;

    public Unidadesmedida() {
    }

    public Unidadesmedida(Integer idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    public Unidadesmedida(Integer idUnidMedida, String descripcion, String abreviatura) {
        this.idUnidMedida = idUnidMedida;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public Integer getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(Integer idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
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

    public Collection<Detnotascredito> getDetnotascreditoCollection() {
        return detnotascreditoCollection;
    }

    public void setDetnotascreditoCollection(Collection<Detnotascredito> detnotascreditoCollection) {
        this.detnotascreditoCollection = detnotascreditoCollection;
    }

    public Collection<Articulos> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<Articulos> articulosCollection) {
        this.articulosCollection = articulosCollection;
    }

    public Collection<Detordenesproduccion> getDetordenesproduccionCollection() {
        return detordenesproduccionCollection;
    }

    public void setDetordenesproduccionCollection(Collection<Detordenesproduccion> detordenesproduccionCollection) {
        this.detordenesproduccionCollection = detordenesproduccionCollection;
    }

    public Collection<Detmovimientosstock> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<Detmovimientosstock> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Collection<Detallefactcompras> getDetallefactcomprasCollection() {
        return detallefactcomprasCollection;
    }

    public void setDetallefactcomprasCollection(Collection<Detallefactcompras> detallefactcomprasCollection) {
        this.detallefactcomprasCollection = detallefactcomprasCollection;
    }

    public Collection<Detnotasdebito> getDetnotasdebitoCollection() {
        return detnotasdebitoCollection;
    }

    public void setDetnotasdebitoCollection(Collection<Detnotasdebito> detnotasdebitoCollection) {
        this.detnotasdebitoCollection = detnotasdebitoCollection;
    }

    public Collection<Detallefacturas> getDetallefacturasCollection() {
        return detallefacturasCollection;
    }

    public void setDetallefacturasCollection(Collection<Detallefacturas> detallefacturasCollection) {
        this.detallefacturasCollection = detallefacturasCollection;
    }

    public Collection<Detalleremitos> getDetalleremitosCollection() {
        return detalleremitosCollection;
    }

    public void setDetalleremitosCollection(Collection<Detalleremitos> detalleremitosCollection) {
        this.detalleremitosCollection = detalleremitosCollection;
    }

    public Collection<Detajustesstock> getDetajustesstockCollection() {
        return detajustesstockCollection;
    }

    public void setDetajustesstockCollection(Collection<Detajustesstock> detajustesstockCollection) {
        this.detajustesstockCollection = detajustesstockCollection;
    }

    public Collection<Detordenesdeposito> getDetordenesdepositoCollection() {
        return detordenesdepositoCollection;
    }

    public void setDetordenesdepositoCollection(Collection<Detordenesdeposito> detordenesdepositoCollection) {
        this.detordenesdepositoCollection = detordenesdepositoCollection;
    }

    public Collection<Detrtosingreso> getDetrtosingresoCollection() {
        return detrtosingresoCollection;
    }

    public void setDetrtosingresoCollection(Collection<Detrtosingreso> detrtosingresoCollection) {
        this.detrtosingresoCollection = detrtosingresoCollection;
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
        hash += (idUnidMedida != null ? idUnidMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadesmedida)) {
            return false;
        }
        Unidadesmedida other = (Unidadesmedida) object;
        if ((this.idUnidMedida == null && other.idUnidMedida != null) || (this.idUnidMedida != null && !this.idUnidMedida.equals(other.idUnidMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Unidadesmedida[idUnidMedida=" + idUnidMedida + "]";
    }
}
