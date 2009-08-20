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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "articulos")
public class Articulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idArticulo", nullable = false)
    private Integer idArticulo;
    @Column(name = "codigo", nullable = false)
    private String codigo;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "stockMinimo", nullable = false)
    private float stockMinimo;
    @Column(name = "leadTime", nullable = false)
    private float leadTime;
    @Column(name = "fechaAlta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "imprimible", nullable = false)
    private String imprimible;
    @Column(name = "final", nullable = false)
    private String articuloFinal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detnotascredito> detnotascreditoCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unidadesmedida idUnidMedida;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detordenesproduccion> detordenesproduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detallefactcompras> detallefactcomprasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulos", fetch = FetchType.LAZY)
    private Collection<Actividadesxarticulos> actividadesxarticulosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detnotasdebito> detnotasdebitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Stock> stockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detallefacturas> detallefacturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulos", fetch = FetchType.LAZY)
    private Collection<Componentes> articulosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentes", fetch = FetchType.LAZY)
    private Collection<Componentes> componentesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detalleremitos> detalleremitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detajustesstock> detajustesstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detordenesdeposito> detordenesdepositoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Sellos> sellosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Detrtosingreso> detrtosingresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", fetch = FetchType.LAZY)
    private Collection<Precios> preciosCollection;

    public Articulos() {
    }

    public Articulos(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Articulos(Integer idArticulo, String codigo, String descripcion, float stockMinimo, float leadTime, Date fechaAlta, Date fechaModificacion, String imprimible, String articuloFinal) {
        this.idArticulo = idArticulo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stockMinimo = stockMinimo;
        this.leadTime = leadTime;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.imprimible = imprimible;
        this.articuloFinal = articuloFinal;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(float stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public float getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(float leadTime) {
        this.leadTime = leadTime;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getImprimible() {
        return imprimible;
    }

    public void setImprimible(String imprimible) {
        this.imprimible = imprimible;
    }

    public Collection<Detnotascredito> getDetnotascreditoCollection() {
        return detnotascreditoCollection;
    }

    public void setDetnotascreditoCollection(Collection<Detnotascredito> detnotascreditoCollection) {
        this.detnotascreditoCollection = detnotascreditoCollection;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Unidadesmedida getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(Unidadesmedida idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
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

    public Collection<Actividadesxarticulos> getActividadesxarticulosCollection() {
        return actividadesxarticulosCollection;
    }

    public void setActividadesxarticulosCollection(Collection<Actividadesxarticulos> actividadesxarticulosCollection) {
        this.actividadesxarticulosCollection = actividadesxarticulosCollection;
    }

    public Collection<Detnotasdebito> getDetnotasdebitoCollection() {
        return detnotasdebitoCollection;
    }

    public void setDetnotasdebitoCollection(Collection<Detnotasdebito> detnotasdebitoCollection) {
        this.detnotasdebitoCollection = detnotasdebitoCollection;
    }

    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    public Collection<Detallefacturas> getDetallefacturasCollection() {
        return detallefacturasCollection;
    }

    public void setDetallefacturasCollection(Collection<Detallefacturas> detallefacturasCollection) {
        this.detallefacturasCollection = detallefacturasCollection;
    }

    public Collection<Componentes> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<Componentes> articulosCollection) {
        this.articulosCollection = articulosCollection;
    }

    public Collection<Componentes> getComponentesCollection() {
        return componentesCollection;
    }

    public void setComponentesCollection(Collection<Componentes> componentesCollection) {
        this.componentesCollection = componentesCollection;
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

    public Collection<Sellos> getSellosCollection() {
        return sellosCollection;
    }

    public void setSellosCollection(Collection<Sellos> sellosCollection) {
        this.sellosCollection = sellosCollection;
    }

    public Collection<Detrtosingreso> getDetrtosingresoCollection() {
        return detrtosingresoCollection;
    }

    public void setDetrtosingresoCollection(Collection<Detrtosingreso> detrtosingresoCollection) {
        this.detrtosingresoCollection = detrtosingresoCollection;
    }

    public Collection<Precios> getPreciosCollection() {
        return preciosCollection;
    }

    public void setPreciosCollection(Collection<Precios> preciosCollection) {
        this.preciosCollection = preciosCollection;
    }

    public String getArticuloFinal() {
        return articuloFinal;
    }

    public void setArticuloFinal(String articuloFinal) {
        this.articuloFinal = articuloFinal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.idArticulo == null && other.idArticulo != null) || (this.idArticulo != null && !this.idArticulo.equals(other.idArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Articulos[idArticulo=" + idArticulo + "]";
    }
}
