/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;

/**
 *
 * @author jmhanun
 */
public class TurnosT implements Serializable {

    private Integer idTurno;
    private float horaDesde;
    private float horaHasta;

    public TurnosT() {
    }

    public TurnosT(Integer idTurno, float horaDesde, float horaHasta) {
        this.idTurno = idTurno;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
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

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }
}
