/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia.helper;

import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.UsuariosT;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class DataTransferHelper {
    // ROLES
    public static List<RolesT> copiarRolesALista(Collection items) {
        List<RolesT> lista = new ArrayList<RolesT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarRol((Roles) i.next()));
        }

        return lista;
    }

    public static RolesT copiarRol(Roles item) {
        RolesT t = null;
        if (item != null) {
            t = new RolesT(item.getIdRol(),
                    item.getRol(),
                    item.getDescripcion(),
                    item.getComponente(),
                    item.getFuncion(),
                    null,
                    null,
                    copiarRol(item.getIdRolPadre()));
        }

        return t;
    }

    //USUARIOS
    public static List<UsuariosT> copiarUsuariosALista(Collection items) {
        List<UsuariosT> lista = new ArrayList<UsuariosT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarUsuario((Usuarios) i.next()));
        }

        return lista;
    }

    public static UsuariosT copiarUsuario(Usuarios item) {
        UsuariosT t = null;
        if (item != null) {
            t = new UsuariosT(item.getIdUsuario(),
                    item.getUsuario(),
                    item.getContrasena(),
                    item.getUltimoAcceso(),
                    item.getEstado(),
                    item.getNombres(),
                    item.getApellidos(),
                    item.getMails(),
                    item.getTelefonos(),
                    null);
        }
        return t;
    }
}
