/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.UsuariosT;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import oracle.toplink.essentials.config.HintValues;
import org.hibernate.ejb.EntityManagerImpl;
//import oracle.toplink.essentials.config.TopLinkQueryHints;

/**
 *
 * @author jmhanun
 */
@Stateless
public class UsuariosFacade implements UsuariosFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public Usuarios create(Usuarios usuarios) {
        em.persist(usuarios);
        return usuarios;
    }

    public void edit(Usuarios usuarios) {
        em.merge(usuarios);
    }

    public void remove(Usuarios usuarios) {
        em.remove(em.merge(usuarios));
    }

    public Usuarios find(Object id) {
        return em.find(ar.com.jpack.persistencia.Usuarios.class, id);
    }

    public List<Usuarios> findAll() {
        
        return ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Usuarios.class).list();
        
    }

    public UsuariosT validarUsuario(UsuariosT usuariosT) {
        StringBuffer codificado = codificar(usuariosT.getContrasena());
        usuariosT.setContrasena(codificado.toString());

        Query query = em.createQuery("SELECT u FROM Usuarios as u WHERE u.usuario = :usuario and u.contrasena = :contrasena");

        /*
        .setHint("toplink.refresh", "true")
        .setParameter("id", id)
        .getSingleResult();
         */
//        query.setHint("toplink.refresh", "true");
        query.setParameter("usuario", usuariosT.getUsuario());
        query.setParameter("contrasena", usuariosT.getContrasena());
        Usuarios usuario = null;
        try {
//            query.setHint(TopLinkQueryHints.REFRESH, HintValues.TRUE);
            usuario = (Usuarios) query.getSingleResult();
            usuariosT = DataTransferHelper.copiarUsuario(usuario);
            usuariosT.setIdRolCollection((ArrayList<RolesT>) DataTransferHelper.copiarRolesALista(usuario.getIdRolCollection()));
        } catch (NoResultException e) {
            System.out.println("No hay resultados en SELECT u FROM Usuarios as u WHERE u.usuario = :usuario and u.contrasena = :contrasena");
        } catch (NonUniqueResultException e) {
            System.out.println("No hay resultado unico en SELECT u FROM Usuarios as u WHERE u.usuario = :usuario and u.contrasena = :contrasena");
        }
        return usuariosT;
    }

    private StringBuffer codificar(String string) {

        MessageDigest messagedigest = null;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        messagedigest.update(string.getBytes());
        byte[] hash = messagedigest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xFF & hash[i]);
            if (hex.length() == 1) {
                hexString.append("0" + hex);
            } else {
                hexString.append(hex);
            }
        }
        return hexString;
    }

    public List<UsuariosT> findAllUsuariosT() {
        List<Usuarios> usuarios = findAll();
        List<UsuariosT> usuariosTs = new ArrayList<UsuariosT>();
        for (Iterator<Usuarios> it = usuarios.iterator(); it.hasNext();) {
            Usuarios usuario = it.next();
            UsuariosT usuariosT;
            usuariosT = DataTransferHelper.copiarUsuario(usuario);
            usuariosT.setIdRolCollection((ArrayList<RolesT>) DataTransferHelper.copiarRolesALista(usuario.getIdRolCollection()));
            usuariosTs.add(usuariosT);
        }
        return usuariosTs;
    }

    public UsuariosT editUsuariosT(UsuariosT usuariosT) {

        Usuarios usuarios = null;
        //si el numero de usuario es null, significa que es un nuevo usuario.
        if (usuariosT.getIdUsuario() != null) {
            usuarios = em.find(Usuarios.class, usuariosT.getIdUsuario());
            usuarios.setUltimoAcceso(usuariosT.getUltimoAcceso());
            cargarDatos(usuariosT, usuarios);
            edit(usuarios);
            em.flush();
        } else {
            usuarios = new Usuarios();
            StringBuffer codificado = codificar(usuariosT.getContrasena());
            usuariosT.setContrasena(codificado.toString());
            usuarios.setContrasena(usuariosT.getContrasena());
            usuarios.setUsuario(usuariosT.getUsuario());
            usuariosT.setIdEstado(new EstadosT(1, "HABILITADO"));
            cargarDatos(usuariosT, usuarios);
            create(usuarios);
        }
        em.flush();
        usuariosT = DataTransferHelper.copiarUsuario(usuarios);
        usuariosT.setIdRolCollection((ArrayList<RolesT>) DataTransferHelper.copiarRolesALista(usuarios.getIdRolCollection()));
        return usuariosT;
    }

    private void cargarDatos(UsuariosT usuariosT, Usuarios usuarios) {
        Estados estado = em.find(Estados.class, usuariosT.getIdEstado().getIdEstado());
        usuarios.setIdEstado(estado);
        usuarios.setApellidos(usuariosT.getApellidos());
        usuarios.setMails(usuariosT.getMails());
        usuarios.setNombres(usuariosT.getNombres());
        if (usuariosT.getIdRolCollection() != null) {
            List<Roles> roles = new ArrayList<Roles>();
            for (Iterator<RolesT> it = usuariosT.getIdRolCollection().iterator(); it.hasNext();) {
                RolesT rolesT = it.next();
                if (rolesT.getIdRol() != null) {
                    Roles rol = em.find(Roles.class, rolesT.getIdRol());
                    roles.add(rol);
                }
            }
            usuarios.setIdRolCollection(roles);
        }
    }
}
