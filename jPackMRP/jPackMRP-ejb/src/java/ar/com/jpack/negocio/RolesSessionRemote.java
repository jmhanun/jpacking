/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.transferencia.RolesT;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface RolesSessionRemote {

    List<RolesT> obtenerRoles();

    void agregarRol(RolesT oRol);

    void editarRol(RolesT oRol);
    
}
