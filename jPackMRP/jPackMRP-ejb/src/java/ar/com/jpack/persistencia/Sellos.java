/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "sellos")
@NamedQueries({@NamedQuery(name = "Sellos.findByIdSello", query = "SELECT s FROM Sellos s WHERE s.idSello = :idSello"), @NamedQuery(name = "Sellos.findByDescripcion", query = "SELECT s FROM Sellos s WHERE s.descripcion = :descripcion"), @NamedQuery(name = "Sellos.findByTamano", query = "SELECT s FROM Sellos s WHERE s.tamano = :tamano")})
public class Sellos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idSello", nullable = false)
    private Integer idSello;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "tamano", nullable = false)
    private float tamano;
    @ManyToMany(mappedBy = "idSelloCollection", fetch = FetchType.LAZY)
    private Collection<Clientes> idClienteCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados idEstado;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos idArticulo;

    public Sellos() {
    }

    public Sellos(Integer idSello) {
        this.idSello = idSello;
    }

    public Sellos(Integer idSello, String descripcion, float tamano) {
        this.idSello = idSello;
        this.descripcion = descripcion;
        this.tamano = tamano;
    }

    public Integer getIdSello() {
        return idSello;
    }

    public void setIdSello(Integer idSello) {
        this.idSello = idSello;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTamano() {
        return tamano;
    }

    public void setTamano(float tamano) {
        this.tamano = tamano;
    }

    public Collection<Clientes> getIdClienteCollection() {
        return idClienteCollection;
    }

    public void setIdClienteCollection(Collection<Clientes> idClienteCollection) {
        this.idClienteCollection = idClienteCollection;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSello != null ? idSello.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sellos)) {
            return false;
        }
        Sellos other = (Sellos) object;
        if ((this.idSello == null && other.idSello != null) || (this.idSello != null && !this.idSello.equals(other.idSello))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Sellos[idSello=" + idSello + "]";
    }

}
