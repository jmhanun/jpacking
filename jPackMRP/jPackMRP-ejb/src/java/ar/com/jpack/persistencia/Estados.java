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
@Table(name = "estados")
@NamedQueries({@NamedQuery(name = "Estados.findByIdEstado", query = "SELECT e FROM Estados e WHERE e.idEstado = :idEstado"), @NamedQuery(name = "Estados.findByDescripcion", query = "SELECT e FROM Estados e WHERE e.descripcion = :descripcion"), @NamedQuery(name = "Estados.findByNotas", query = "SELECT e FROM Estados e WHERE e.notas = :notas")})
public class Estados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idEstado", nullable = false)
    private Integer idEstado;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "notas", nullable = false)
    private String notas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Ordenesproduccion> ordenesproduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Articulos> articulosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Remitos> remitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Facturascompras> facturascomprasCollection;
    @OneToMany(mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Domicilios> domiciliosCollection;
    @JoinColumn(name = "idDominio", referencedColumnName = "idDominio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dominios idDominio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Remitosingreso> remitosingresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Ajustesstock> ajustesstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Notascredito> notascreditoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Listasprecios> listaspreciosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Facturas> facturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Notasdebito> notasdebitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Tiposiva> tiposivaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Sellos> sellosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Maquinas> maquinasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Clientes> clientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Ordenesdeposito> ordenesdepositoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Detalleproduccion> detalleproduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Proveedores> proveedoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Collection<Usuarios> usuariosCollection;

    public Estados() {
    }

    public Estados(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Estados(Integer idEstado, String descripcion, String notas) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
        this.notas = notas;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Collection<Ordenesproduccion> getOrdenesproduccionCollection() {
        return ordenesproduccionCollection;
    }

    public void setOrdenesproduccionCollection(Collection<Ordenesproduccion> ordenesproduccionCollection) {
        this.ordenesproduccionCollection = ordenesproduccionCollection;
    }

    public Collection<Articulos> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<Articulos> articulosCollection) {
        this.articulosCollection = articulosCollection;
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

    public Collection<Domicilios> getDomiciliosCollection() {
        return domiciliosCollection;
    }

    public void setDomiciliosCollection(Collection<Domicilios> domiciliosCollection) {
        this.domiciliosCollection = domiciliosCollection;
    }

    public Dominios getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(Dominios idDominio) {
        this.idDominio = idDominio;
    }

    public Collection<Remitosingreso> getRemitosingresoCollection() {
        return remitosingresoCollection;
    }

    public void setRemitosingresoCollection(Collection<Remitosingreso> remitosingresoCollection) {
        this.remitosingresoCollection = remitosingresoCollection;
    }

    public Collection<Ajustesstock> getAjustesstockCollection() {
        return ajustesstockCollection;
    }

    public void setAjustesstockCollection(Collection<Ajustesstock> ajustesstockCollection) {
        this.ajustesstockCollection = ajustesstockCollection;
    }

    public Collection<Notascredito> getNotascreditoCollection() {
        return notascreditoCollection;
    }

    public void setNotascreditoCollection(Collection<Notascredito> notascreditoCollection) {
        this.notascreditoCollection = notascreditoCollection;
    }

    public Collection<Listasprecios> getListaspreciosCollection() {
        return listaspreciosCollection;
    }

    public void setListaspreciosCollection(Collection<Listasprecios> listaspreciosCollection) {
        this.listaspreciosCollection = listaspreciosCollection;
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

    public Collection<Tiposiva> getTiposivaCollection() {
        return tiposivaCollection;
    }

    public void setTiposivaCollection(Collection<Tiposiva> tiposivaCollection) {
        this.tiposivaCollection = tiposivaCollection;
    }

    public Collection<Sellos> getSellosCollection() {
        return sellosCollection;
    }

    public void setSellosCollection(Collection<Sellos> sellosCollection) {
        this.sellosCollection = sellosCollection;
    }

    public Collection<Maquinas> getMaquinasCollection() {
        return maquinasCollection;
    }

    public void setMaquinasCollection(Collection<Maquinas> maquinasCollection) {
        this.maquinasCollection = maquinasCollection;
    }

    public Collection<Clientes> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<Clientes> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    public Collection<Ordenesdeposito> getOrdenesdepositoCollection() {
        return ordenesdepositoCollection;
    }

    public void setOrdenesdepositoCollection(Collection<Ordenesdeposito> ordenesdepositoCollection) {
        this.ordenesdepositoCollection = ordenesdepositoCollection;
    }

    public Collection<Detalleproduccion> getDetalleproduccionCollection() {
        return detalleproduccionCollection;
    }

    public void setDetalleproduccionCollection(Collection<Detalleproduccion> detalleproduccionCollection) {
        this.detalleproduccionCollection = detalleproduccionCollection;
    }

    public Collection<Proveedores> getProveedoresCollection() {
        return proveedoresCollection;
    }

    public void setProveedoresCollection(Collection<Proveedores> proveedoresCollection) {
        this.proveedoresCollection = proveedoresCollection;
    }

    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Estados[idEstado=" + idEstado + "]";
    }

}
