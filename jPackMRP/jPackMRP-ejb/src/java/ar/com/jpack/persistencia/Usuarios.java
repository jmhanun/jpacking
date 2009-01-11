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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "usuarios")
@NamedQueries({@NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"), @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario"), @NamedQuery(name = "Usuarios.findByContrasena", query = "SELECT u FROM Usuarios u WHERE u.contrasena = :contrasena"), @NamedQuery(name = "Usuarios.findByUltimoAcceso", query = "SELECT u FROM Usuarios u WHERE u.ultimoAcceso = :ultimoAcceso"), @NamedQuery(name = "Usuarios.findByNombres", query = "SELECT u FROM Usuarios u WHERE u.nombres = :nombres"), @NamedQuery(name = "Usuarios.findByApellidos", query = "SELECT u FROM Usuarios u WHERE u.apellidos = :apellidos"), @NamedQuery(name = "Usuarios.findByMails", query = "SELECT u FROM Usuarios u WHERE u.mails = :mails"), @NamedQuery(name = "Usuarios.findByTelefonos", query = "SELECT u FROM Usuarios u WHERE u.telefonos = :telefonos")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idUsuario", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idUsuario;
    @Column(name = "usuario", nullable = false)
    private String usuario;
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    @Column(name = "ultimoAcceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "mails")
    private String mails;
    @Column(name = "telefonos")
    private String telefonos;
    @JoinTable(name = "rolesusuarios", joinColumns = {@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")}, inverseJoinColumns = {@JoinColumn(name = "idRol", referencedColumnName = "idRol")})
    @ManyToMany
    private Collection<Roles> idRolCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Ordenesproduccion> ordenesproduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Articulos> articulosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Remitos> remitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Facturascompras> facturascomprasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Ajustesstock> ajustesstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Remitosingreso> remitosingresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Stock> stockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Listasprecios> listaspreciosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Setup> setupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Facturas> facturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Componentes> componentesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Notasdebito> notasdebitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Ordenesdeposito> ordenesdepositoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Precios> preciosCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String usuario, String contrasena, String nombres, String apellidos) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public Collection<Roles> getIdRolCollection() {
        return idRolCollection;
    }

    public void setIdRolCollection(Collection<Roles> idRolCollection) {
        this.idRolCollection = idRolCollection;
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

    public Collection<Ajustesstock> getAjustesstockCollection() {
        return ajustesstockCollection;
    }

    public void setAjustesstockCollection(Collection<Ajustesstock> ajustesstockCollection) {
        this.ajustesstockCollection = ajustesstockCollection;
    }

    public Collection<Remitosingreso> getRemitosingresoCollection() {
        return remitosingresoCollection;
    }

    public void setRemitosingresoCollection(Collection<Remitosingreso> remitosingresoCollection) {
        this.remitosingresoCollection = remitosingresoCollection;
    }

    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    public Collection<Listasprecios> getListaspreciosCollection() {
        return listaspreciosCollection;
    }

    public void setListaspreciosCollection(Collection<Listasprecios> listaspreciosCollection) {
        this.listaspreciosCollection = listaspreciosCollection;
    }

    public Collection<Setup> getSetupCollection() {
        return setupCollection;
    }

    public void setSetupCollection(Collection<Setup> setupCollection) {
        this.setupCollection = setupCollection;
    }

    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    public Collection<Componentes> getComponentesCollection() {
        return componentesCollection;
    }

    public void setComponentesCollection(Collection<Componentes> componentesCollection) {
        this.componentesCollection = componentesCollection;
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

    public Collection<Precios> getPreciosCollection() {
        return preciosCollection;
    }

    public void setPreciosCollection(Collection<Precios> preciosCollection) {
        this.preciosCollection = preciosCollection;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Usuarios[idUsuario=" + idUsuario + "]";
    }

}
