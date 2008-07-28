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
@Table(name = "setup")
@NamedQueries({@NamedQuery(name = "Setup.findByIdSetup", query = "SELECT s FROM Setup s WHERE s.idSetup = :idSetup"), @NamedQuery(name = "Setup.findByDescripcion", query = "SELECT s FROM Setup s WHERE s.descripcion = :descripcion"), @NamedQuery(name = "Setup.findByValor", query = "SELECT s FROM Setup s WHERE s.valor = :valor"), @NamedQuery(name = "Setup.findByFechaModificacion", query = "SELECT s FROM Setup s WHERE s.fechaModificacion = :fechaModificacion")})
public class Setup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idSetup", nullable = false)
    private Integer idSetup;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "valor", nullable = false)
    private String valor;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuarios idUsuario;

    public Setup() {
    }

    public Setup(Integer idSetup) {
        this.idSetup = idSetup;
    }

    public Setup(Integer idSetup, String descripcion, String valor, Date fechaModificacion) {
        this.idSetup = idSetup;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fechaModificacion = fechaModificacion;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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
