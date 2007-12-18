/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pablo
 */
@Stateless
public class RolesSessionBean implements RolesSessionRemote {

    @PersistenceContext
    private EntityManager em;

    public List<RolesT> obtenerRoles() {
        Collection roles = null;
        roles = em.createNamedQuery("Roles.obtenerRoles").getResultList();
        if (roles.isEmpty()) {
            return null;
        }else{
            return DataTransferHelper.copiarRolesALista(roles);
        }

    }

    public void agregarRol(RolesT oRol) {
        Roles rol = new Roles(oRol.getIdRol(), oRol.getRol(), oRol.getDescripcion());
        em.persist(rol);
        em.flush();
    }

    public void editarRol(RolesT oRol) {
        Roles rol = em.find(Roles.class, oRol.getIdRol());
        rol.setRol(oRol.getRol());
        rol.setDescripcion(oRol.getDescripcion());
        em.merge(rol);
        em.flush();
    }
}
