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
@Table(name = "turnos")
@NamedQueries({@NamedQuery(name = "Turnos.findByIdTurno", query = "SELECT t FROM Turnos t WHERE t.idTurno = :idTurno"), @NamedQuery(name = "Turnos.findByHoraDesde", query = "SELECT t FROM Turnos t WHERE t.horaDesde = :horaDesde"), @NamedQuery(name = "Turnos.findByHoraHasta", query = "SELECT t FROM Turnos t WHERE t.horaHasta = :horaHasta")})
public class Turnos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTurno", nullable = false)
    private Integer idTurno;
    @Column(name = "horaDesde", nullable = false)
    private float horaDesde;
    @Column(name = "horaHasta", nullable = false)
    private float horaHasta;

    public Turnos() {
    }

    public Turnos(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Turnos(Integer idTurno, float horaDesde, float horaHasta) {
        this.idTurno = idTurno;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public float getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(float horaDesde) {
        this.horaDesde = horaDesde;
    }

    public float getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(float horaHasta) {
        this.horaHasta = horaHasta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurno != null ? idTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turnos)) {
            return false;
        }
        Turnos other = (Turnos) object;
        if ((this.idTurno == null && other.idTurno != null) || (this.idTurno != null && !this.idTurno.equals(other.idTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Turnos[idTurno=" + idTurno + "]";
    }

}
