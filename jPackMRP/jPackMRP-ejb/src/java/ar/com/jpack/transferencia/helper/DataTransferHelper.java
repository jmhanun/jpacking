/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia.helper;

import ar.com.jpack.persistencia.Articulos;
import ar.com.jpack.persistencia.Componentes;
import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.ComponentesT;
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

    // ARTICULOS
    public static List<ArticulosT> copiarArticulosALista(Collection items) {
        List<ArticulosT> lista = new ArrayList<ArticulosT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarArticulo((Articulos) i.next()));
        }
        return lista;
    }

    public static ArticulosT copiarArticulo(Articulos item) {
        ArticulosT t = null;
        if (item != null) {
            t = new ArticulosT(item.getIdArticulo(),
                    item.getCodigo(),
                    item.getDescripcion(),
                    item.getEstado(),
                    item.getStock(),
                    item.getStockMinimo(),
                    item.getLeadTime(),
                    copiarComponentesALista(item.getArticulosCollection()));
        }
        return t;
    }

    // COMPONENTES
    public static List<ComponentesT> copiarComponentesALista(Collection items) {
        List<ComponentesT> lista = new ArrayList<ComponentesT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarComponente((Componentes) i.next()));
        }
        return lista;
    }

    public static ComponentesT copiarComponente(Componentes item) {
        ComponentesT t = null;
        if (item != null) {

            t = new ComponentesT(item.getComponentesPK().getIdArticulo(),
                    item.getComponentesPK().getIdComponente(),
                    item.getOrden(),
                    item.getCantidad(),
                    copiarArticuloComponente(item.getArticulos()),
                    copiarArticuloComponente(item.getComponentes()));
        }
        return t;
    }

    public static ArticulosT copiarArticuloComponente(Articulos item) {
        ArticulosT t = null;
        if (item != null) {
            t = new ArticulosT(item.getIdArticulo(),
                    item.getCodigo(),
                    item.getDescripcion(),
                    item.getEstado(),
                    item.getStock(),
                    item.getStockMinimo(),
                    item.getLeadTime(),
                    null);
        }
        return t;
    }

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
                    item.getDescripcion());
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
                    item.getTipoUsuario(),
                    item.getEstado(),
                    item.getNombres(),
                    item.getApellidos(),
                    item.getMails(),
                    item.getTelefonos(),
                    copiarRolesALista(item.getIdRolCollection()));
        }
        return t;
    }
}
