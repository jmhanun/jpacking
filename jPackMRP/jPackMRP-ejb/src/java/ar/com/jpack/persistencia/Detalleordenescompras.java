/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "detalleordenescompras")
@NamedQueries({@NamedQuery(name = "Detalleordenescompras.findByIdDetOrdenCompra", query = "SELECT d FROM Detalleordenescompras d WHERE d.detalleordenescomprasPK.idDetOrdenCompra = :idDetOrdenCompra"), @NamedQuery(name = "Detalleordenescompras.findByIdOrdenCompra", query = "SELECT d FROM Detalleordenescompras d WHERE d.detalleordenescomprasPK.idOrdenCompra = :idOrdenCompra"), @NamedQuery(name = "Detalleordenescompras.findByCantidad", query = "SELECT d FROM Detalleordenescompras d WHERE d.cantidad = :cantidad")})
public class Detalleordenescompras implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleordenescomprasPK detalleordenescomprasPK;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulos idArticulo;
    @JoinColumn(name = "idOrdenCompra", referencedColumnName = "idOrdenCompra", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordenescompra ordenescompra;
    @JoinColumn(name = "idUnidMedida", referencedColumnName = "idUnidMedida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unidadesmedida idUnidMedida;

    public Detalleordenescompras() {
    }

    public Detalleordenescompras(DetalleordenescomprasPK detalleordenescomprasPK) {
        this.detalleordenescomprasPK = detalleordenescomprasPK;
    }

    public Detalleordenescompras(DetalleordenescomprasPK detalleordenescomprasPK, int cantidad) {
        this.detalleordenescomprasPK = detalleordenescomprasPK;
        this.cantidad = cantidad;
    }

    public Detalleordenescompras(int idDetOrdenCompra, int idOrdenCompra) {
        this.detalleordenescomprasPK = new DetalleordenescomprasPK(idDetOrdenCompra, idOrdenCompra);
    }

    public DetalleordenescomprasPK getDetalleordenescomprasPK() {
        return detalleordenescomprasPK;
    }

    public void setDetalleordenescomprasPK(DetalleordenescomprasPK detalleordenescomprasPK) {
        this.detalleordenescomprasPK = detalleordenescomprasPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Ordenescompra getOrdenescompra() {
        return ordenescompra;
    }

    public void setOrdenescompra(Ordenescompra ordenescompra) {
        this.ordenescompra = ordenescompra;
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
        hash += (detalleordenescomprasPK != null ? detalleordenescomprasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleordenescompras)) {
            return false;
        }
        Detalleordenescompras other = (Detalleordenescompras) object;
        if ((this.detalleordenescomprasPK == null && other.detalleordenescomprasPK != null) || (this.detalleordenescomprasPK != null && !this.detalleordenescomprasPK.equals(other.detalleordenescomprasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Detalleordenescompras[detalleordenescomprasPK=" + detalleordenescomprasPK + "]";
    }
}
