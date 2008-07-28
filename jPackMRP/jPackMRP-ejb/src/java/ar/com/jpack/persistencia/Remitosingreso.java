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
@Table(name = "remitosingreso")
@NamedQueries({@NamedQuery(name = "Remitosingreso.findByIdRtoIngreso", query = "SELECT r FROM Remitosingreso r WHERE r.idRtoIngreso = :idRtoIngreso"), @NamedQuery(name = "Remitosingreso.findByNroRemito", query = "SELECT r FROM Remitosingreso r WHERE r.nroRemito = :nroRemito"), @NamedQuery(name = "Remitosingreso.findByFecha", query = "SELECT r FROM Remitosingreso r WHERE r.fecha = :fecha"), @NamedQuery(name = "Remitosingreso.findByLetra", query = "SELECT r FROM Remitosingreso r WHERE r.letra = :letra"), @NamedQuery(name = "Remitosingreso.findByImporte", query = "SELECT r FROM Remitosingreso r WHERE r.importe = :importe"), @NamedQuery(name = "Remitosingreso.findByDescuento", query = "SELECT r FROM Remitosingreso r WHERE r.descuento = :descuento"), @NamedQuery(name = "Remitosingreso.findByFechaModificacion", query = "SELECT r FROM Remitosingreso r WHERE r.fechaModificacion = :fechaModificacion")})
public class Remitosingreso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idRtoIngreso", nullable = false)
    private Integer idRtoIngreso;
    @Column(name = "nroRemito", nullable = false)
    private int nroRemito;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "letra", nullable = false)
    private String letra;
    @Column(name = "importe", nullable = false)
    private double importe;
    @Column(name = "descuento", nullable = false)
    private float descuento;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "idRtoIngreso")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne
    private Proveedores idProveedor;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne
    private Tiposcomprobantes idTipoComprobante;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remitosingreso")
    private Collection<Detrtosingreso> detrtosingresoCollection;

    public Remitosingreso() {
    }

    public Remitosingreso(Integer idRtoIngreso) {
        this.idRtoIngreso = idRtoIngreso;
    }

    public Remitosingreso(Integer idRtoIngreso, int nroRemito, Date fecha, String letra, double importe, float descuento, Date fechaModificacion) {
        this.idRtoIngreso = idRtoIngreso;
        this.nroRemito = nroRemito;
        this.fecha = fecha;
        this.letra = letra;
        this.importe = importe;
        this.descuento = descuento;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdRtoIngreso() {
        return idRtoIngreso;
    }

    public void setIdRtoIngreso(Integer idRtoIngreso) {
        this.idRtoIngreso = idRtoIngreso;
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

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Collection<Detmovimientosstock> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<Detmovimientosstock> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
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

    public Collection<Detrtosingreso> getDetrtosingresoCollection() {
        return detrtosingresoCollection;
    }

    public void setDetrtosingresoCollection(Collection<Detrtosingreso> detrtosingresoCollection) {
        this.detrtosingresoCollection = detrtosingresoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRtoIngreso != null ? idRtoIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remitosingreso)) {
            return false;
        }
        Remitosingreso other = (Remitosingreso) object;
        if ((this.idRtoIngreso == null && other.idRtoIngreso != null) || (this.idRtoIngreso != null && !this.idRtoIngreso.equals(other.idRtoIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Remitosingreso[idRtoIngreso=" + idRtoIngreso + "]";
    }

}
