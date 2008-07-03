/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jmhanun
 */
@Entity
@Table(name = "tiposdocumento")
@NamedQueries({@NamedQuery(name = "Tiposdocumento.findByIdTipoDocumento", query = "SELECT t FROM Tiposdocumento t WHERE t.idTipoDocumento = :idTipoDocumento"), @NamedQuery(name = "Tiposdocumento.findByDescripcion", query = "SELECT t FROM Tiposdocumento t WHERE t.descripcion = :descripcion"), @NamedQuery(name = "Tiposdocumento.findByAbreviatura", query = "SELECT t FROM Tiposdocumento t WHERE t.abreviatura = :abreviatura")})
public class Tiposdocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTipoDocumento", nullable = false)
    private Integer idTipoDocumento;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private Collection<Clientes> clientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private Collection<Proveedores> proveedoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private Collection<Empleados> empleadosCollection;

    public Tiposdocumento() {
    }

    public Tiposdocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Tiposdocumento(Integer idTipoDocumento, String descripcion, String abreviatura) {
        this.idTipoDocumento = idTipoDocumento;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Collection<Clientes> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<Clientes> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    public Collection<Proveedores> getProveedoresCollection() {
        return proveedoresCollection;
    }

    public void setProveedoresCollection(Collection<Proveedores> proveedoresCollection) {
        this.proveedoresCollection = proveedoresCollection;
    }

    public Collection<Empleados> getEmpleadosCollection() {
        return empleadosCollection;
    }

    public void setEmpleadosCollection(Collection<Empleados> empleadosCollection) {
        this.empleadosCollection = empleadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposdocumento)) {
            return false;
        }
        Tiposdocumento other = (Tiposdocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.jpack.persistencia.Tiposdocumento[idTipoDocumento=" + idTipoDocumento + "]";
    }

}
