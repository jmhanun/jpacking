/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "precios")
@NamedQueries({@NamedQuery(name = "Precios.findByIdPrecio", query = "SELECT p FROM Precios p WHERE p.idPrecio = :idPrecio"), @NamedQuery(name = "Precios.findByPrecio", query = "SELECT p FROM Precios p WHERE p.precio = :precio"), @NamedQuery(name = "Precios.findByFechaModificacion", query = "SELECT p FROM Precios p WHERE p.fechaModificacion = :fechaModificacion")})
public class Precios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idPrecio", nullable = false)
    private Integer idPrecio;
    @Column(name = "precio", nullable = false)
    private double precio;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulos idArticulo;
    @JoinColumn(name = "idLista", referencedColumnName = "idLista")
    @ManyToOne
    private Listasprecios idLista;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuarios idUsuario;

    public Precios() {
    }

    public Precios(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public Precios(Integer idPrecio, double precio, Date fechaModificacion) {
        this.idPrecio = idPrecio;
        this.precio = precio;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Listasprecios getIdLista() {
        return idLista;
    }

    public void setIdLista(Listasprecios idLista) {
        this.idLista = idLista;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
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
