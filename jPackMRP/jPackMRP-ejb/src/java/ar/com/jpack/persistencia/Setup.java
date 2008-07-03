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
@Table(name = "setup")
@NamedQueries({@NamedQuery(name = "Setup.findByIdSetup", query = "SELECT s FROM Setup s WHERE s.idSetup = :idSetup"), @NamedQuery(name = "Setup.findByDescripcion", query = "SELECT s FROM Setup s WHERE s.descripcion = :descripcion"), @NamedQuery(name = "Setup.findByValor", query = "SELECT s FROM Setup s WHERE s.valor = :valor")})
public class Setup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idSetup", nullable = false)
    private Integer idSetup;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "valor", nullable = false)
    private String valor;

    public Setup() {
    }

    public Setup(Integer idSetup) {
        this.idSetup = idSetup;
    }

    public Setup(Integer idSetup, String descripcion, String valor) {
        this.idSetup = idSetup;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Integer getIdSetup() {
        return idSetup;
    }

    public void setIdSetup(Integer idSetup) {
        this.idSetup = idSetup;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSetup != null ? idSetup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Setup)) {
            return false;
        }
        Setup other = (Setup) object;
        if ((this.idSetup == null && other.idSetup != null) || (this.idSetup != null && !this.idSetup.equals(other.idSetup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Setup[idSetup=" + idSetup + "]";
    }

}
