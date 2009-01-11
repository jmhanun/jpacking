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
@Table(name = "ajustesstock")
@NamedQueries({@NamedQuery(name = "Ajustesstock.findByIdAjusteStock", query = "SELECT a FROM Ajustesstock a WHERE a.idAjusteStock = :idAjusteStock"), @NamedQuery(name = "Ajustesstock.findByNroAjuste", query = "SELECT a FROM Ajustesstock a WHERE a.nroAjuste = :nroAjuste"), @NamedQuery(name = "Ajustesstock.findByFecha", query = "SELECT a FROM Ajustesstock a WHERE a.fecha = :fecha"), @NamedQuery(name = "Ajustesstock.findByFechaModificacion", query = "SELECT a FROM Ajustesstock a WHERE a.fechaModificacion = :fechaModificacion"), @NamedQuery(name = "Ajustesstock.findBySigno", query = "SELECT a FROM Ajustesstock a WHERE a.signo = :signo")})
public class Ajustesstock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idAjusteStock", nullable = false)
    private Integer idAjusteStock;
    @Column(name = "nroAjuste", nullable = false)
    private int nroAjuste;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "fechaModificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "signo", nullable = false)
    private String signo;
    @OneToMany(mappedBy = "idAjusteStock")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne
    private Tiposcomprobantes idTipoComprobante;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ajustesstock")
    private Collection<Detajustesstock> detajustesstockCollection;

    public Ajustesstock() {
    }

    public Ajustesstock(Integer idAjusteStock) {
        this.idAjusteStock = idAjusteStock;
    }

    public Ajustesstock(Integer idAjusteStock, int nroAjuste, Date fecha, Date fechaModificacion, String signo) {
        this.idAjusteStock = idAjusteStock;
        this.nroAjuste = nroAjuste;
        this.fecha = fecha;
        this.fechaModificacion = fechaModificacion;
        this.signo = signo;
    }

    public Integer getIdAjusteStock() {
        return idAjusteStock;
    }

    public void setIdAjusteStock(Integer idAjusteStock) {
        this.idAjusteStock = idAjusteStock;
    }

    public int getNroAjuste() {
        return nroAjuste;
    }

    public void setNroAjuste(int nroAjuste) {
        this.nroAjuste = nroAjuste;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
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

    public Collection<Detajustesstock> getDetajustesstockCollection() {
        return detajustesstockCollection;
    }

    public void setDetajustesstockCollection(Collection<Detajustesstock> detajustesstockCollection) {
        this.detajustesstockCollection = detajustesstockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAjusteStock != null ? idAjusteStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ajustesstock)) {
            return false;
        }
        Ajustesstock other = (Ajustesstock) object;
        if ((this.idAjusteStock == null && other.idAjusteStock != null) || (this.idAjusteStock != null && !this.idAjusteStock.equals(other.idAjusteStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Ajustesstock[idAjusteStock=" + idAjusteStock + "]";
    }

}
