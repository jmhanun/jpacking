/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "detmovimientosstock")
@NamedQueries({@NamedQuery(name = "Detmovimientosstock.findByIdDetMovStock", query = "SELECT d FROM Detmovimientosstock d WHERE d.idDetMovStock = :idDetMovStock"), @NamedQuery(name = "Detmovimientosstock.findByDescripcion", query = "SELECT d FROM Detmovimientosstock d WHERE d.descripcion = :descripcion"), @NamedQuery(name = "Detmovimientosstock.findByCantidad", query = "SELECT d FROM Detmovimientosstock d WHERE d.cantidad = :cantidad"), @NamedQuery(name = "Detmovimientosstock.findByFechaMovimiento", query = "SELECT d FROM Detmovimientosstock d WHERE d.fechaMovimiento = :fechaMovimiento")})
public class Detmovimientosstock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idDetMovStock", nullable = false)
    private Integer idDetMovStock;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "cantidad", nullable = false)
    private double cantidad;
    @Column(name = "fechaMovimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    @JoinColumn(name = "idAjusteStock", referencedColumnName = "idAjusteStock")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ajustesstock idAjusteStock;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos idArticulo;
    @JoinColumn(name = "idNotaCredito", referencedColumnName = "idNotaCredito")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notascredito idNotaCredito;
    @JoinColumn(name = "idNotaDebito", referencedColumnName = "idNotaDebito")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notasdebito idNotaDebito;
    @JoinColumn(name = "idOrdenDeposito", referencedColumnName = "idOrdenDeposito")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordenesdeposito idOrdenDeposito;
    @JoinColumn(name = "idOrdenProduccion", referencedColumnName = "idOrdenProduccion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordenesproduccion idOrdenProduccion;
    @JoinColumn(name = "idRemito", referencedColumnName = "idRemito")
    @ManyToOne(fetch = FetchType.LAZY)
    private Remitos idRemito;
    @JoinColumn(name = "idRtoIngreso", referencedColumnName = "idRtoIngreso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Remitosingreso idRtoIngreso;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unidadesmedida idUnidMedida;

    public Detmovimientosstock() {
    }

    public Detmovimientosstock(Integer idDetMovStock) {
        this.idDetMovStock = idDetMovStock;
    }

    public Detmovimientosstock(Integer idDetMovStock, String descripcion, double cantidad) {
        this.idDetMovStock = idDetMovStock;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Integer getIdDetMovStock() {
        return idDetMovStock;
    }

    public void setIdDetMovStock(Integer idDetMovStock) {
        this.idDetMovStock = idDetMovStock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Ajustesstock getIdAjusteStock() {
        return idAjusteStock;
    }

    public void setIdAjusteStock(Ajustesstock idAjusteStock) {
        this.idAjusteStock = idAjusteStock;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Notascredito getIdNotaCredito() {
        return idNotaCredito;
    }

    public void setIdNotaCredito(Notascredito idNotaCredito) {
        this.idNotaCredito = idNotaCredito;
    }

    public Notasdebito getIdNotaDebito() {
        return idNotaDebito;
    }

    public void setIdNotaDebito(Notasdebito idNotaDebito) {
        this.idNotaDebito = idNotaDebito;
    }

    public Ordenesdeposito getIdOrdenDeposito() {
        return idOrdenDeposito;
    }

    public void setIdOrdenDeposito(Ordenesdeposito idOrdenDeposito) {
        this.idOrdenDeposito = idOrdenDeposito;
    }

    public Ordenesproduccion getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(Ordenesproduccion idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public Remitos getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Remitos idRemito) {
        this.idRemito = idRemito;
    }

    public Remitosingreso getIdRtoIngreso() {
        return idRtoIngreso;
    }

    public void setIdRtoIngreso(Remitosingreso idRtoIngreso) {
        this.idRtoIngreso = idRtoIngreso;
    }

    public Unidadesmedida getIdUnidMedida() {
        return idUnidMedida;
    }

    public void setIdUnidMedida(Unidadesmedida idUnidMedida) {
        this.idUnidMedida = idUnidMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetMovStock != null ? idDetMovStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detmovimientosstock)) {
            return false;
        }
        Detmovimientosstock other = (Detmovimientosstock) object;
        if ((this.idDetMovStock == null && other.idDetMovStock != null) || (this.idDetMovStock != null && !this.idDetMovStock.equals(other.idDetMovStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detmovimientosstock[idDetMovStock=" + idDetMovStock + "]";
    }

}
