/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class RolesFacade implements RolesFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(Roles roles) {
        em.persist(roles);
    }

    public void edit(Roles roles) {
        em.merge(roles);
    }

    public void remove(Roles roles) {
        em.remove(em.merge(roles));
    }

    public Roles find(Object id) {
        return em.find(ar.com.jpack.persistencia.Roles.class, id);
    }

    public List<Roles> findAll() {
        return em.createQuery("select object(o) from Roles as o").getResultList();
    }

    public List<RolesT> findAllUsuariosT() {
        List<Roles> roles = findAll();
        return DataTransferHelper.copiarRolesALista(roles);
    }
}
