/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ireport;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "usuarios",schema = "")
@NamedQueries({@NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"), @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario"), @NamedQuery(name = "Usuarios.findByContrasena", query = "SELECT u FROM Usuarios u WHERE u.contrasena = :contrasena"), @NamedQuery(name = "Usuarios.findByUltimoAcceso", query = "SELECT u FROM Usuarios u WHERE u.ultimoAcceso = :ultimoAcceso"), @NamedQuery(name = "Usuarios.findByNombres", query = "SELECT u FROM Usuarios u WHERE u.nombres = :nombres"), @NamedQuery(name = "Usuarios.findByApellidos", query = "SELECT u FROM Usuarios u WHERE u.apellidos = :apellidos"), @NamedQuery(name = "Usuarios.findByMails", query = "SELECT u FROM Usuarios u WHERE u.mails = :mails"), @NamedQuery(name = "Usuarios.findByTelefonos", query = "SELECT u FROM Usuarios u WHERE u.telefonos = :telefonos"), @NamedQuery(name = "Usuarios.findByIdEstado", query = "SELECT u FROM Usuarios u WHERE u.idEstado = :idEstado")})
public class Usuarios implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;
    @Column(name = "usuario", nullable = false)
    private String usuario;
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    @Column(name = "ultimoAcceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "mails")
    private String mails;
    @Column(name = "telefonos")
    private String telefonos;
    @Column(name = "idEstado", nullable = false)
    private int idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private Collection<Rolesusuarios> rolesusuariosCollection;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String usuario, String contrasena, String nombres, String apellidos, int idEstado) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.idEstado = idEstado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        Integer oldIdUsuario = this.idUsuario;
        this.idUsuario = idUsuario;
        changeSupport.firePropertyChange("idUsuario", oldIdUsuario, idUsuario);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        String oldUsuario = this.usuario;
        this.usuario = usuario;
        changeSupport.firePropertyChange("usuario", oldUsuario, usuario);
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        String oldContrasena = this.contrasena;
        this.contrasena = contrasena;
        changeSupport.firePropertyChange("contrasena", oldContrasena, contrasena);
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        Date oldUltimoAcceso = this.ultimoAcceso;
        this.ultimoAcceso = ultimoAcceso;
        changeSupport.firePropertyChange("ultimoAcceso", oldUltimoAcceso, ultimoAcceso);
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        String oldNombres = this.nombres;
        this.nombres = nombres;
        changeSupport.firePropertyChange("nombres", oldNombres, nombres);
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        String oldApellidos = this.apellidos;
        this.apellidos = apellidos;
        changeSupport.firePropertyChange("apellidos", oldApellidos, apellidos);
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        String oldMails = this.mails;
        this.mails = mails;
        changeSupport.firePropertyChange("mails", oldMails, mails);
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        String oldTelefonos = this.telefonos;
        this.telefonos = telefonos;
        changeSupport.firePropertyChange("telefonos", oldTelefonos, telefonos);
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        int oldIdEstado = this.idEstado;
        this.idEstado = idEstado;
        changeSupport.firePropertyChange("idEstado", oldIdEstado, idEstado);
    }

    public Collection<Rolesusuarios> getRolesusuariosCollection() {
        return rolesusuariosCollection;
    }

    public void setRolesusuariosCollection(Collection<Rolesusuarios> rolesusuariosCollection) {
        this.rolesusuariosCollection = rolesusuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ireport.Usuarios[idUsuario=" + idUsuario + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
