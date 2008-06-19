/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.transferencia.UsuariosT;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jmhanun
 */
@Stateless
public class UsuariosFacade implements UsuariosFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(Usuarios usuarios) {
        em.persist(usuarios);
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
        return em.createQuery("select object(o) from Usuarios as o").getResultList();
    }

    public UsuariosT validarUsuario(UsuariosT usuariosT) {
        Query query = em.createQuery("SELECT u FROM Usuarios as u WHERE u.usuario = :usuario and u.contrasena = :contrasena");
        query.setParameter("usuario", usuariosT.getUsuario());
        query.setParameter("contrasena", usuariosT.getContrasena());
        Usuarios usuario = null;
        try {
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

    public String getMensaje() {
        return "Hola Jose Miguel, soy una llamada remota";
    }
}
