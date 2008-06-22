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
 * @author jmhanun
 */
@Entity
@Table(name = "articulos")
@NamedQueries({@NamedQuery(name = "Articulos.findByIdArticulo", query = "SELECT a FROM Articulos a WHERE a.idArticulo = :idArticulo"), @NamedQuery(name = "Articulos.findByCodigo", query = "SELECT a FROM Articulos a WHERE a.codigo = :codigo"), @NamedQuery(name = "Articulos.findByDescripcion", query = "SELECT a FROM Articulos a WHERE a.descripcion = :descripcion"), @NamedQuery(name = "Articulos.findByStock", query = "SELECT a FROM Articulos a WHERE a.stock = :stock"), @NamedQuery(name = "Articulos.findByStockMinimo", query = "SELECT a FROM Articulos a WHERE a.stockMinimo = :stockMinimo"), @NamedQuery(name = "Articulos.findByLeadTime", query = "SELECT a FROM Articulos a WHERE a.leadTime = :leadTime")})
public class Articulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idArticulo", nullable = false)
    private Integer idArticulo;
    @Column(name = "codigo", nullable = false)
    private String codigo;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "stock", nullable = false)
    private float stock;
    @Column(name = "stockMinimo", nullable = false)
    private float stockMinimo;
    @Column(name = "leadTime", nullable = false)
    private float leadTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detnotascredito> detnotascreditoCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne
    private Unidadesmedida idUnidMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detordenesproduccion> detordenesproduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detallefactcompras> detallefactcomprasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detnotasdebito> detnotasdebitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Stock> stockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detallefacturas> detallefacturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulos")
    private Collection<Componentes> componentesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulos1")
    private Collection<Componentes> componentesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detalleremitos> detalleremitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detordenesdeposito> detordenesdepositoCollection;
    @OneToMany(mappedBy = "idArticulo")
    private Collection<Produccion> produccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detrtosingreso> detrtosingresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Precios> preciosCollection;

    public Articulos() {
    }

    public Articulos(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Articulos(Integer idArticulo, String codigo, String descripcion, float stock, float stockMinimo, float leadTime) {
        this.idArticulo = idArticulo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.leadTime = leadTime;
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

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
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

    public Collection<Componentes> getComponentesCollection() {
        return componentesCollection;
    }

    public void setComponentesCollection(Collection<Componentes> componentesCollection) {
        this.componentesCollection = componentesCollection;
    }

    public Collection<Componentes> getComponentesCollection1() {
        return componentesCollection1;
    }

    public void setComponentesCollection1(Collection<Componentes> componentesCollection1) {
        this.componentesCollection1 = componentesCollection1;
    }

    public Collection<Detalleremitos> getDetalleremitosCollection() {
        return detalleremitosCollection;
    }

    public void setDetalleremitosCollection(Collection<Detalleremitos> detalleremitosCollection) {
        this.detalleremitosCollection = detalleremitosCollection;
    }

    public Collection<Detordenesdeposito> getDetordenesdepositoCollection() {
        return detordenesdepositoCollection;
    }

    public void setDetordenesdepositoCollection(Collection<Detordenesdeposito> detordenesdepositoCollection) {
        this.detordenesdepositoCollection = detordenesdepositoCollection;
    }

    public Collection<Produccion> getProduccionCollection() {
        return produccionCollection;
    }

    public void setProduccionCollection(Collection<Produccion> produccionCollection) {
        this.produccionCollection = produccionCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the id fields are not set
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
