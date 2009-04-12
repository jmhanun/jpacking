/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.transferencia.UsuariosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface UsuariosFacadeRemote {

    public UsuariosT editUsuariosT(UsuariosT usuariosT);

    public List<UsuariosT> findAllUsuariosT();

    public List<UsuariosT> getUsuariosT(HashMap parametros);

    Usuarios create(Usuarios usuarios);

    void edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);

    List<Usuarios> findAll();

    public UsuariosT validarUsuario(UsuariosT usuariosT);

}
