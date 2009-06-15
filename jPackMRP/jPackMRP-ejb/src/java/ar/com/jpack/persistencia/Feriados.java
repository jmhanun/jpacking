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
@Table(name = "feriados")
@NamedQueries({@NamedQuery(name = "Feriados.findByIdFeriado", query = "SELECT f FROM Feriados f WHERE f.idFeriado = :idFeriado"), @NamedQuery(name = "Feriados.findByFecha", query = "SELECT f FROM Feriados f WHERE f.fecha = :fecha"), @NamedQuery(name = "Feriados.findByMotivo", query = "SELECT f FROM Feriados f WHERE f.motivo = :motivo")})
public class Feriados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idFeriado", nullable = false)
    private Integer idFeriado;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "motivo", nullable = false)
    private String motivo;

    public Feriados() {
    }

    public Feriados(Integer idFeriado) {
        this.idFeriado = idFeriado;
    }

    public Feriados(Integer idFeriado, Date fecha, String motivo) {
        this.idFeriado = idFeriado;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public Integer getIdFeriado() {
        return idFeriado;
    }

    public void setIdFeriado(Integer idFeriado) {
        this.idFeriado = idFeriado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFeriado != null ? idFeriado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feriados)) {
            return false;
        }
        Feriados other = (Feriados) object;
        if ((this.idFeriado == null && other.idFeriado != null) || (this.idFeriado != null && !this.idFeriado.equals(other.idFeriado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Feriados[idFeriado=" + idFeriado + "]";
    }

}
