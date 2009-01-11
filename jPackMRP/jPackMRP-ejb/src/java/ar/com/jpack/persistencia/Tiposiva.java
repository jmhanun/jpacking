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
@Table(name = "tiposiva")
@NamedQueries({@NamedQuery(name = "Tiposiva.findByIdTipoIVA", query = "SELECT t FROM Tiposiva t WHERE t.idTipoIVA = :idTipoIVA"), @NamedQuery(name = "Tiposiva.findByDescripcion", query = "SELECT t FROM Tiposiva t WHERE t.descripcion = :descripcion"), @NamedQuery(name = "Tiposiva.findByAbreviatura", query = "SELECT t FROM Tiposiva t WHERE t.abreviatura = :abreviatura")})
public class Tiposiva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTipoIVA", nullable = false)
    private Integer idTipoIVA;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;

    public Tiposiva() {
    }

    public Tiposiva(Integer idTipoIVA) {
        this.idTipoIVA = idTipoIVA;
    }

    public Tiposiva(Integer idTipoIVA, String descripcion, String abreviatura) {
        this.idTipoIVA = idTipoIVA;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public Integer getIdTipoIVA() {
        return idTipoIVA;
    }

    public void setIdTipoIVA(Integer idTipoIVA) {
        this.idTipoIVA = idTipoIVA;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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
        hash += (idTipoIVA != null ? idTipoIVA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposiva)) {
            return false;
        }
        Tiposiva other = (Tiposiva) object;
        if ((this.idTipoIVA == null && other.idTipoIVA != null) || (this.idTipoIVA != null && !this.idTipoIVA.equals(other.idTipoIVA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Tiposiva[idTipoIVA=" + idTipoIVA + "]";
    }

}
