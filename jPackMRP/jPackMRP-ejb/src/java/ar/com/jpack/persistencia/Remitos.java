/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "remitos")
@NamedQueries({@NamedQuery(name = "Remitos.findByIdRemito", query = "SELECT r FROM Remitos r WHERE r.idRemito = :idRemito"), @NamedQuery(name = "Remitos.findByNroRemito", query = "SELECT r FROM Remitos r WHERE r.nroRemito = :nroRemito"), @NamedQuery(name = "Remitos.findByFecha", query = "SELECT r FROM Remitos r WHERE r.fecha = :fecha"), @NamedQuery(name = "Remitos.findByImporte", query = "SELECT r FROM Remitos r WHERE r.importe = :importe"), @NamedQuery(name = "Remitos.findByFechaAcordada", query = "SELECT r FROM Remitos r WHERE r.fechaAcordada = :fechaAcordada"), @NamedQuery(name = "Remitos.findByFechaEntrega", query = "SELECT r FROM Remitos r WHERE r.fechaEntrega = :fechaEntrega"), @NamedQuery(name = "Remitos.findByFechaModificacion", query = "SELECT r FROM Remitos r WHERE r.fechaModificacion = :fechaModificacion")})
public class Remitos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idRemito", nullable = false)
    private Integer idRemito;
    @Column(name = "nroRemito", nullable = false)
    private int nroRemito;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "importe", nullable = false)
    private double importe;
    @Column(name = "fechaAcordada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcordada;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @ManyToMany(mappedBy = "idRemitoCollection")
    private Collection<Facturas> idFacturaCollection;
    @OneToMany(mappedBy = "idRemito")
    private Collection<Ordenesproduccion> ordenesproduccionCollection;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne
    private Clientes idCliente;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne
    private Tiposcomprobantes idTipoComprobante;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuarios idUsuario;
    @OneToMany(mappedBy = "idRemito")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remitos")
    private Collection<Detalleremitos> detalleremitosCollection;

    public Remitos() {
    }

    public Remitos(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Remitos(Integer idRemito, int nroRemito, double importe, Date fechaModificacion) {
        this.idRemito = idRemito;
        this.nroRemito = nroRemito;
        this.importe = importe;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public int getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(int nroRemito) {
        this.nroRemito = nroRemito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFechaAcordada() {
        return fechaAcordada;
    }

    public void setFechaAcordada(Date fechaAcordada) {
        this.fechaAcordada = fechaAcordada;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Collection<Facturas> getIdFacturaCollection() {
        return idFacturaCollection;
    }

    public void setIdFacturaCollection(Collection<Facturas> idFacturaCollection) {
        this.idFacturaCollection = idFacturaCollection;
    }

    public Collection<Ordenesproduccion> getOrdenesproduccionCollection() {
        return ordenesproduccionCollection;
    }

    public void setOrdenesproduccionCollection(Collection<Ordenesproduccion> ordenesproduccionCollection) {
        this.ordenesproduccionCollection = ordenesproduccionCollection;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Tiposcomprobantes getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Tiposcomprobantes idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Collection<Detmovimientosstock> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<Detmovimientosstock> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Collection<Detalleremitos> getDetalleremitosCollection() {
        return detalleremitosCollection;
    }

    public void setDetalleremitosCollection(Collection<Detalleremitos> detalleremitosCollection) {
        this.detalleremitosCollection = detalleremitosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRemito != null ? idRemito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remitos)) {
            return false;
        }
        Remitos other = (Remitos) object;
        if ((this.idRemito == null && other.idRemito != null) || (this.idRemito != null && !this.idRemito.equals(other.idRemito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Remitos[idRemito=" + idRemito + "]";
    }

}
