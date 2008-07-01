/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.transferencia.RolesT;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface RolesFacadeRemote {

    void create(Roles roles);

    void edit(Roles roles);

    void remove(Roles roles);

    Roles find(Object id);

    List<Roles> findAll();

    public List<RolesT> findAllUsuariosT();

}
