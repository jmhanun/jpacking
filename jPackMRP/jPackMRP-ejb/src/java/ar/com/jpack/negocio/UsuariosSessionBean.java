/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.transferencia.UsuariosT;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class UsuariosSessionBean implements UsuariosSessionRemote {

    @PersistenceContext
    private EntityManager em;

    public UsuariosT validarUsuario(UsuariosT usuarioT) {
        /*
        roles = em.createNamedQuery("Roles.obtenerRoles").getResultList();
        if (roles.isEmpty()) {
            return null;
        }else{
            return DataTransferHelper.copiarRolesALista(roles);
        }
         */
        return null;
    }
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
}
