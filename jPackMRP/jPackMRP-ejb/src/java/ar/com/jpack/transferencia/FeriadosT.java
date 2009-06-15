/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class FeriadosT implements Serializable {

    private Integer idFeriado;
    private Date fecha;
    private String motivo;

    public FeriadosT() {
    }

    public FeriadosT(Integer idFeriado, Date fecha, String motivo) {
        this.idFeriado = idFeriado;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdFeriado() {
        return idFeriado;
    }

    public void setIdFeriado(Integer idFeriado) {
        this.idFeriado = idFeriado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
