/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia.listas;

import java.io.Serializable;

/**
 *
 * @author jmhanun
 */
public class TiposIvaListaT implements Serializable, Comparable<TiposIvaListaT> {

    private Integer idTipoIVA;
    private String descripcion;
    private String abreviatura;
    private String estado;

    public TiposIvaListaT() {
    }

    public TiposIvaListaT(Integer idTipoIVA, String descripcion, String abreviatura, String estado) {
        this.idTipoIVA = idTipoIVA;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estado = estado;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdTipoIVA() {
        return idTipoIVA;
    }

    public void setIdTipoIVA(Integer idTipoIVA) {
        this.idTipoIVA = idTipoIVA;
    }


    public int compareTo(TiposIvaListaT o) {
        Integer thisId = this.idTipoIVA;
        Integer anotherId = o.getIdTipoIVA();
        return thisId.compareTo(anotherId);
    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final TiposIvaT other = (TiposIvaT) obj;
//        if (this.idTipoIVA != other.idTipoIVA && (this.idTipoIVA == null || !this.idTipoIVA.equals(other.idTipoIVA))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 17 * hash + (this.idTipoIVA != null ? this.idTipoIVA.hashCode() : 0);
//        return hash;
//    }
}
