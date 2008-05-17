/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "precios")
@NamedQueries({
@NamedQuery(name = "Precios.findByIdPrecio", query = "SELECT p FROM Precios p WHERE p.idPrecio = :idPrecio"),
@NamedQuery(name = "Precios.findByPrecio", query = "SELECT p FROM Precios p WHERE p.precio = :precio")
})
public class Precios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idPrecio", nullable = false)
    private Integer idPrecio;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idLista", referencedColumnName = "idLista")
    @ManyToOne
    private ListaPrecios idLista;

    public Precios() {
    }

    public Precios(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public Precios(Integer idPrecio, Double precio) {
        this.idPrecio = idPrecio;
        this.precio = precio;
    }

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public ListaPrecios getIdLista() {
        return idLista;
    }

    public void setIdLista(ListaPrecios idLista) {
        this.idLista = idLista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrecio != null ? idPrecio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Precios)) {
            return false;
        }
        Precios other = (Precios) object;
        if ((this.idPrecio == null && other.idPrecio != null) || (this.idPrecio != null && !this.idPrecio.equals(other.idPrecio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Precios[idPrecio=" + idPrecio + "]";
    }
}
