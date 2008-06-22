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
@Table(name = "notasdebito")
@NamedQueries({@NamedQuery(name = "Notasdebito.findByIdNotaDebito", query = "SELECT n FROM Notasdebito n WHERE n.idNotaDebito = :idNotaDebito"), @NamedQuery(name = "Notasdebito.findByNroNotaDebito", query = "SELECT n FROM Notasdebito n WHERE n.nroNotaDebito = :nroNotaDebito"), @NamedQuery(name = "Notasdebito.findByFecha", query = "SELECT n FROM Notasdebito n WHERE n.fecha = :fecha"), @NamedQuery(name = "Notasdebito.findByLetra", query = "SELECT n FROM Notasdebito n WHERE n.letra = :letra"), @NamedQuery(name = "Notasdebito.findByImporte", query = "SELECT n FROM Notasdebito n WHERE n.importe = :importe"), @NamedQuery(name = "Notasdebito.findByDescuento", query = "SELECT n FROM Notasdebito n WHERE n.descuento = :descuento")})
public class Notasdebito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idNotaDebito", nullable = false)
    private Integer idNotaDebito;
    @Column(name = "nroNotaDebito", nullable = false)
    private int nroNotaDebito;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "letra", nullable = false)
    private String letra;
    @Column(name = "importe", nullable = false)
    private double importe;
    @Column(name = "descuento", nullable = false)
    private float descuento;
    @OneToMany(mappedBy = "idNotaDebito")
    private Collection<Detmovimientosstock> detmovimientosstockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notasdebito")
    private Collection<Detnotasdebito> detnotasdebitoCollection;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne
    private Clientes idCliente;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idTipoComprobante", referencedColumnName = "idTipoComprobante")
    @ManyToOne
    private Tiposcomprobantes idTipoComprobante;

    public Notasdebito() {
    }

    public Notasdebito(Integer idNotaDebito) {
        this.idNotaDebito = idNotaDebito;
    }

    public Notasdebito(Integer idNotaDebito, int nroNotaDebito, Date fecha, String letra, double importe, float descuento) {
        this.idNotaDebito = idNotaDebito;
        this.nroNotaDebito = nroNotaDebito;
        this.fecha = fecha;
        this.letra = letra;
        this.importe = importe;
        this.descuento = descuento;
    }

    public Integer getIdNotaDebito() {
        return idNotaDebito;
    }

    public void setIdNotaDebito(Integer idNotaDebito) {
        this.idNotaDebito = idNotaDebito;
    }

    public int getNroNotaDebito() {
        return nroNotaDebito;
    }

    public void setNroNotaDebito(int nroNotaDebito) {
        this.nroNotaDebito = nroNotaDebito;
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

    public Collection<Detmovimientosstock> getDetmovimientosstockCollection() {
        return detmovimientosstockCollection;
    }

    public void setDetmovimientosstockCollection(Collection<Detmovimientosstock> detmovimientosstockCollection) {
        this.detmovimientosstockCollection = detmovimientosstockCollection;
    }

    public Collection<Detnotasdebito> getDetnotasdebitoCollection() {
        return detnotasdebitoCollection;
    }

    public void setDetnotasdebitoCollection(Collection<Detnotasdebito> detnotasdebitoCollection) {
        this.detnotasdebitoCollection = detnotasdebitoCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotaDebito != null ? idNotaDebito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notasdebito)) {
            return false;
        }
        Notasdebito other = (Notasdebito) object;
        if ((this.idNotaDebito == null && other.idNotaDebito != null) || (this.idNotaDebito != null && !this.idNotaDebito.equals(other.idNotaDebito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Notasdebito[idNotaDebito=" + idNotaDebito + "]";
    }

}
