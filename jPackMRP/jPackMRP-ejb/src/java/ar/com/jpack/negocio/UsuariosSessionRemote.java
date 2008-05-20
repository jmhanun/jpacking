/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.transferencia.UsuariosT;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface UsuariosSessionRemote {

    UsuariosT validarUsuario(UsuariosT usuarioT);
    
}
