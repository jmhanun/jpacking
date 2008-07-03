/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "migraclientes")
@NamedQueries({@NamedQuery(name = "Migraclientes.findByNumero", query = "SELECT m FROM Migraclientes m WHERE m.numero = :numero"), @NamedQuery(name = "Migraclientes.findByNombre", query = "SELECT m FROM Migraclientes m WHERE m.nombre = :nombre"), @NamedQuery(name = "Migraclientes.findByCuenta", query = "SELECT m FROM Migraclientes m WHERE m.cuenta = :cuenta"), @NamedQuery(name = "Migraclientes.findByContacto", query = "SELECT m FROM Migraclientes m WHERE m.contacto = :contacto"), @NamedQuery(name = "Migraclientes.findByCalle", query = "SELECT m FROM Migraclientes m WHERE m.calle = :calle"), @NamedQuery(name = "Migraclientes.findByNrocalle", query = "SELECT m FROM Migraclientes m WHERE m.nrocalle = :nrocalle"), @NamedQuery(name = "Migraclientes.findByBarrio", query = "SELECT m FROM Migraclientes m WHERE m.barrio = :barrio"), @NamedQuery(name = "Migraclientes.findByLocalidad", query = "SELECT m FROM Migraclientes m WHERE m.localidad = :localidad"), @NamedQuery(name = "Migraclientes.findByCodpostal", query = "SELECT m FROM Migraclientes m WHERE m.codpostal = :codpostal"), @NamedQuery(name = "Migraclientes.findByProvincia", query = "SELECT m FROM Migraclientes m WHERE m.provincia = :provincia"), @NamedQuery(name = "Migraclientes.findByTelefono", query = "SELECT m FROM Migraclientes m WHERE m.telefono = :telefono"), @NamedQuery(name = "Migraclientes.findByFax", query = "SELECT m FROM Migraclientes m WHERE m.fax = :fax"), @NamedQuery(name = "Migraclientes.findByTipoiva", query = "SELECT m FROM Migraclientes m WHERE m.tipoiva = :tipoiva"), @NamedQuery(name = "Migraclientes.findByNrocuit", query = "SELECT m FROM Migraclientes m WHERE m.nrocuit = :nrocuit"), @NamedQuery(name = "Migraclientes.findByNroingbrut", query = "SELECT m FROM Migraclientes m WHERE m.nroingbrut = :nroingbrut"), @NamedQuery(name = "Migraclientes.findByFechaalta", query = "SELECT m FROM Migraclientes m WHERE m.fechaalta = :fechaalta"), @NamedQuery(name = "Migraclientes.findByFultcompra", query = "SELECT m FROM Migraclientes m WHERE m.fultcompra = :fultcompra"), @NamedQuery(name = "Migraclientes.findByZona", query = "SELECT m FROM Migraclientes m WHERE m.zona = :zona"), @NamedQuery(name = "Migraclientes.findByCategoria", query = "SELECT m FROM Migraclientes m WHERE m.categoria = :categoria"), @NamedQuery(name = "Migraclientes.findByUltimacta", query = "SELECT m FROM Migraclientes m WHERE m.ultimacta = :ultimacta"), @NamedQuery(name = "Migraclientes.findBySaldomaxim", query = "SELECT m FROM Migraclientes m WHERE m.saldomaxim = :saldomaxim"), @NamedQuery(name = "Migraclientes.findByTexto", query = "SELECT m FROM Migraclientes m WHERE m.texto = :texto"), @NamedQuery(name = "Migraclientes.findByTituctacte", query = "SELECT m FROM Migraclientes m WHERE m.tituctacte = :tituctacte"), @NamedQuery(name = "Migraclientes.findByDiasvencim", query = "SELECT m FROM Migraclientes m WHERE m.diasvencim = :diasvencim"), @NamedQuery(name = "Migraclientes.findByEmail", query = "SELECT m FROM Migraclientes m WHERE m.email = :email"), @NamedQuery(name = "Migraclientes.findByVendedor", query = "SELECT m FROM Migraclientes m WHERE m.vendedor = :vendedor")})
public class Migraclientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "NUMERO", nullable = false)
    private String numero;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CUENTA")
    private String cuenta;
    @Column(name = "CONTACTO")
    private String contacto;
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "NROCALLE")
    private String nrocalle;
    @Column(name = "BARRIO")
    private String barrio;
    @Column(name = "LOCALIDAD")
    private String localidad;
    @Column(name = "CODPOSTAL")
    private String codpostal;
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "TIPOIVA")
    private String tipoiva;
    @Column(name = "NROCUIT")
    private String nrocuit;
    @Column(name = "NROINGBRUT")
    private String nroingbrut;
    @Column(name = "FECHAALTA")
    private String fechaalta;
    @Column(name = "FULTCOMPRA")
    private String fultcompra;
    @Column(name = "ZONA")
    private String zona;
    @Column(name = "CATEGORIA")
    private String categoria;
    @Column(name = "ULTIMACTA")
    private String ultimacta;
    @Column(name = "SALDOMAXIM")
    private String saldomaxim;
    @Column(name = "TEXTO")
    private String texto;
    @Column(name = "TITUCTACTE")
    private String tituctacte;
    @Column(name = "DIASVENCIM")
    private String diasvencim;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "VENDEDOR")
    private String vendedor;

    public Migraclientes() {
    }

    public Migraclientes(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNrocalle() {
        return nrocalle;
    }

    public void setNrocalle(String nrocalle) {
        this.nrocalle = nrocalle;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTipoiva() {
        return tipoiva;
    }

    public void setTipoiva(String tipoiva) {
        this.tipoiva = tipoiva;
    }

    public String getNrocuit() {
        return nrocuit;
    }

    public void setNrocuit(String nrocuit) {
        this.nrocuit = nrocuit;
    }

    public String getNroingbrut() {
        return nroingbrut;
    }

    public void setNroingbrut(String nroingbrut) {
        this.nroingbrut = nroingbrut;
    }

    public String getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(String fechaalta) {
        this.fechaalta = fechaalta;
    }

    public String getFultcompra() {
        return fultcompra;
    }

    public void setFultcompra(String fultcompra) {
        this.fultcompra = fultcompra;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUltimacta() {
        return ultimacta;
    }

    public void setUltimacta(String ultimacta) {
        this.ultimacta = ultimacta;
    }

    public String getSaldomaxim() {
        return saldomaxim;
    }

    public void setSaldomaxim(String saldomaxim) {
        this.saldomaxim = saldomaxim;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTituctacte() {
        return tituctacte;
    }

    public void setTituctacte(String tituctacte) {
        this.tituctacte = tituctacte;
    }

    public String getDiasvencim() {
        return diasvencim;
    }

    public void setDiasvencim(String diasvencim) {
        this.diasvencim = diasvencim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Migraclientes)) {
            return false;
        }
        Migraclientes other = (Migraclientes) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Migraclientes[numero=" + numero + "]";
    }

}
