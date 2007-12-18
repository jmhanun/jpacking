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
 * @author Pablo
 */
@Entity
@Table(name = "articulos")
@NamedQueries({@NamedQuery(name = "Articulos.findByIdArticulo", query = "SELECT a FROM Articulos a WHERE a.idArticulo = :idArticulo"), 
@NamedQuery(name = "Articulos.findByCodigo", query = "SELECT a FROM Articulos a WHERE a.codigo = :codigo"), 
@NamedQuery(name = "Articulos.findByDescripcion", query = "SELECT a FROM Articulos a WHERE a.descripcion = :descripcion"), 
@NamedQuery(name = "Articulos.findByEstado", query = "SELECT a FROM Articulos a WHERE a.estado = :estado"), 
@NamedQuery(name = "Articulos.findByStock", query = "SELECT a FROM Articulos a WHERE a.stock = :stock"), 
@NamedQuery(name = "Articulos.obtenerArticulos", query = "SELECT a FROM Articulos a"), 
@NamedQuery(name = "Articulos.findByStockMinimo", query = "SELECT a FROM Articulos a WHERE a.stockMinimo = :stockMinimo"), 
@NamedQuery(name = "Articulos.findByLeadTime", query = "SELECT a FROM Articulos a WHERE a.leadTime = :leadTime")})

public class Articulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idArticulo", nullable = false)
    private Integer idArticulo;
    @Column(name = "codigo", nullable = false)
    private String codigo;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "stock", nullable = false)
    private Double stock;
    @Column(name = "stockMinimo", nullable = false)
    private Double stockMinimo;
    @Column(name = "leadTime", nullable = false)
    private Double leadTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detalleremitos> detalleremitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Detallefacturas> detallefacturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private Collection<Precios> preciosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulos")
    private Collection<Componentes> articulosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentes")
    private Collection<Componentes> componentesCollection;

    public Articulos() {
    }

    public Articulos(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Articulos(Integer idArticulo, String codigo, String descripcion, String estado, Double stock, Double stockMinimo, Double leadTime) {
        this.idArticulo = idArticulo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Double getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Double leadTime) {
        this.leadTime = leadTime;
    }

    public Collection<Detalleremitos> getDetalleremitosCollection() {
        return detalleremitosCollection;
    }

    public void setDetalleremitosCollection(Collection<Detalleremitos> detalleremitosCollection) {
        this.detalleremitosCollection = detalleremitosCollection;
    }

    public Collection<Detallefacturas> getDetallefacturasCollection() {
        return detallefacturasCollection;
    }

    public void setDetallefacturasCollection(Collection<Detallefacturas> detallefacturasCollection) {
        this.detallefacturasCollection = detallefacturasCollection;
    }

    public Collection<Precios> getPreciosCollection() {
        return preciosCollection;
    }

    public void setPreciosCollection(Collection<Precios> preciosCollection) {
        this.preciosCollection = preciosCollection;
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
