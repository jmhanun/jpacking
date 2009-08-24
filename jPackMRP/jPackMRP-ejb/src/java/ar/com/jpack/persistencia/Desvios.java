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
@Table(name = "desvios")
@NamedQueries({@NamedQuery(name = "Desvios.findByIddesvio", query = "SELECT d FROM Desvios d WHERE d.iddesvio = :iddesvio"), @NamedQuery(name = "Desvios.findByComentarios", query = "SELECT d FROM Desvios d WHERE d.comentarios = :comentarios")})
public class Desvios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "iddesvio", nullable = false)
    private Integer iddesvio;
    @Column(name = "comentarios", nullable = false)
    private String comentarios;
    @JoinColumn(name = "iddetalleproduccion", referencedColumnName = "idDetalleProduccion")
    @ManyToOne
    private Detalleproduccion iddetalleproduccion;
    @JoinColumn(name = "idtipodesvio", referencedColumnName = "idTipoDesvio")
    @ManyToOne
    private Tiposdesvios idtipodesvio;

    public Desvios() {
    }

    public Desvios(Integer iddesvio) {
        this.iddesvio = iddesvio;
    }

    public Desvios(Integer iddesvio, String comentarios) {
        this.iddesvio = iddesvio;
        this.comentarios = comentarios;
    }

    public Integer getIddesvio() {
        return iddesvio;
    }

    public void setIddesvio(Integer iddesvio) {
        this.iddesvio = iddesvio;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Detalleproduccion getIddetalleproduccion() {
        return iddetalleproduccion;
    }

    public void setIddetalleproduccion(Detalleproduccion iddetalleproduccion) {
        this.iddetalleproduccion = iddetalleproduccion;
    }

    public Tiposdesvios getIdtipodesvio() {
        return idtipodesvio;
    }

    public void setIdtipodesvio(Tiposdesvios idtipodesvio) {
        this.idtipodesvio = idtipodesvio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddesvio != null ? iddesvio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desvios)) {
            return false;
        }
        Desvios other = (Desvios) object;
        if ((this.iddesvio == null && other.iddesvio != null) || (this.iddesvio != null && !this.iddesvio.equals(other.iddesvio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Desvios[iddesvio=" + iddesvio + "]";
    }

}
