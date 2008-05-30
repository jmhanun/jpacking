/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.transferencia.UsuariosT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
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
        try {
            return DataTransferHelper.copiarUsuario((Usuarios) query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }catch (NonUniqueResultException e){
            return null;
        }
    }
}
