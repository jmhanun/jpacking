/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "detalleremitostemp")
@NamedQueries({@NamedQuery(name = "Detalleremitostemp.findByInstancia", query = "SELECT d FROM Detalleremitostemp d WHERE d.detalleremitostempPK.instancia = :instancia"), @NamedQuery(name = "Detalleremitostemp.findByIdarticulo", query = "SELECT d FROM Detalleremitostemp d WHERE d.detalleremitostempPK.idarticulo = :idarticulo"), @NamedQuery(name = "Detalleremitostemp.findByCantidad", query = "SELECT d FROM Detalleremitostemp d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detalleremitostemp.findByIdactividad", query = "SELECT d FROM Detalleremitostemp d WHERE d.detalleremitostempPK.idactividad = :idactividad"), @NamedQuery(name = "Detalleremitostemp.findByFechafin", query = "SELECT d FROM Detalleremitostemp d WHERE d.fechafin = :fechafin"), @NamedQuery(name = "Detalleremitostemp.findByOrden", query = "SELECT d FROM Detalleremitostemp d WHERE d.orden = :orden")})
public class Detalleremitostemp implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleremitostempPK detalleremitostempPK;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "fechafin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @Column(name = "orden", nullable = false)
    private int orden;

    public Detalleremitostemp() {
    }

    public Detalleremitostemp(DetalleremitostempPK detalleremitostempPK) {
        this.detalleremitostempPK = detalleremitostempPK;
    }

    public Detalleremitostemp(DetalleremitostempPK detalleremitostempPK, int cantidad, int orden) {
        this.detalleremitostempPK = detalleremitostempPK;
        this.cantidad = cantidad;
        this.orden = orden;
    }

    public Detalleremitostemp(int instancia, int idarticulo, int idactividad) {
        this.detalleremitostempPK = new DetalleremitostempPK(instancia, idarticulo, idactividad);
    }

    public DetalleremitostempPK getDetalleremitostempPK() {
        return detalleremitostempPK;
    }

    public void setDetalleremitostempPK(DetalleremitostempPK detalleremitostempPK) {
        this.detalleremitostempPK = detalleremitostempPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleremitostempPK != null ? detalleremitostempPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleremitostemp)) {
            return false;
        }
        Detalleremitostemp other = (Detalleremitostemp) object;
        if ((this.detalleremitostempPK == null && other.detalleremitostempPK != null) || (this.detalleremitostempPK != null && !this.detalleremitostempPK.equals(other.detalleremitostempPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detalleremitostemp[detalleremitostempPK=" + detalleremitostempPK + "]";
    }

}
