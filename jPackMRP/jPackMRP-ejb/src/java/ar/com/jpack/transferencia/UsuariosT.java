/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class UsuariosT implements Serializable {

    private Integer idUsuario;
    private String usuario;
    private String contrasena;
    private Date ultimoAcceso;
    private String nombres;
    private String apellidos;
    private String mails;
    private String telefonos;
    private Collection<RolesT> idRolCollection;
    private Collection<OrdenesProduccionT> ordenesproduccionCollection;
    private Collection<ArticulosT> articulosCollection;
    private Collection<RemitosT> remitosCollection;
    private Collection<FacturasComprasT> facturascomprasCollection;
    private Collection<AjustesStockT> ajustesstockCollection;
    private Collection<RemitosIngresoT> remitosingresoCollection;
    private Collection<StockT> stockCollection;
    private Collection<ListasPreciosT> listaspreciosCollection;
    private Collection<SetupT> setupCollection;
    private Collection<FacturasT> facturasCollection;
    private Collection<ComponentesT> componentesCollection;
    private Collection<NotasDebitoT> notasdebitoCollection;
    private Collection<OrdenesDepositoT> ordenesdepositoCollection;
    private Collection<PreciosT> preciosCollection;
    private EstadosT idEstado;

    public UsuariosT() {
    }

    public UsuariosT(Integer idUsuario, String usuario, String contrasena, Date ultimoAcceso, String nombres, String apellidos, String mails, String telefonos, EstadosT idEstado) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.ultimoAcceso = ultimoAcceso;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.mails = mails;
        this.telefonos = telefonos;
        this.idEstado = idEstado;
    }

    public Collection<AjustesStockT> getAjustesstockCollection() {
        return ajustesstockCollection;
    }

    public void setAjustesstockCollection(Collection<AjustesStockT> ajustesstockCollection) {
        this.ajustesstockCollection = ajustesstockCollection;
    }

    public Collection<FacturasT> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<FacturasT> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    public Collection<ArticulosT> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<ArticulosT> articulosCollection) {
        this.articulosCollection = articulosCollection;
    }

    public Collection<ComponentesT> getComponentesCollection() {
        return componentesCollection;
    }

    public void setComponentesCollection(Collection<ComponentesT> componentesCollection) {
        this.componentesCollection = componentesCollection;
    }

    public Collection<FacturasComprasT> getFacturascomprasCollection() {
        return facturascomprasCollection;
    }

    public void setFacturascomprasCollection(Collection<FacturasComprasT> facturascomprasCollection) {
        this.facturascomprasCollection = facturascomprasCollection;
    }

    public Collection<ListasPreciosT> getListaspreciosCollection() {
        return listaspreciosCollection;
    }

    public void setListaspreciosCollection(Collection<ListasPreciosT> listaspreciosCollection) {
        this.listaspreciosCollection = listaspreciosCollection;
    }

    public Collection<NotasDebitoT> getNotasdebitoCollection() {
        return notasdebitoCollection;
    }

    public void setNotasdebitoCollection(Collection<NotasDebitoT> notasdebitoCollection) {
        this.notasdebitoCollection = notasdebitoCollection;
    }

    public Collection<OrdenesDepositoT> getOrdenesdepositoCollection() {
        return ordenesdepositoCollection;
    }

    public void setOrdenesdepositoCollection(Collection<OrdenesDepositoT> ordenesdepositoCollection) {
        this.ordenesdepositoCollection = ordenesdepositoCollection;
    }

    public Collection<OrdenesProduccionT> getOrdenesproduccionCollection() {
        return ordenesproduccionCollection;
    }

    public void setOrdenesproduccionCollection(Collection<OrdenesProduccionT> ordenesproduccionCollection) {
        this.ordenesproduccionCollection = ordenesproduccionCollection;
    }

    public Collection<PreciosT> getPreciosCollection() {
        return preciosCollection;
    }

    public void setPreciosCollection(Collection<PreciosT> preciosCollection) {
        this.preciosCollection = preciosCollection;
    }

    public Collection<RemitosT> getRemitosCollection() {
        return remitosCollection;
    }

    public void setRemitosCollection(Collection<RemitosT> remitosCollection) {
        this.remitosCollection = remitosCollection;
    }

    public Collection<RemitosIngresoT> getRemitosingresoCollection() {
        return remitosingresoCollection;
    }

    public void setRemitosingresoCollection(Collection<RemitosIngresoT> remitosingresoCollection) {
        this.remitosingresoCollection = remitosingresoCollection;
    }

    public Collection<SetupT> getSetupCollection() {
        return setupCollection;
    }

    public void setSetupCollection(Collection<SetupT> setupCollection) {
        this.setupCollection = setupCollection;
    }

    public Collection<StockT> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<StockT> stockCollection) {
        this.stockCollection = stockCollection;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Collection<RolesT> getIdRolCollection() {
        return idRolCollection;
    }

    public void setIdRolCollection(Collection<RolesT> idRolCollection) {
        this.idRolCollection = idRolCollection;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public EstadosT getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosT idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return getUsuario();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuariosT other = (UsuariosT) obj;
        if (this.idUsuario != other.idUsuario && (this.idUsuario == null || !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idUsuario != null ? this.idUsuario.hashCode() : 0);
        return hash;
    }


}
