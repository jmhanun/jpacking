/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia.helper;

import ar.com.jpack.persistencia.Articulos;
import ar.com.jpack.persistencia.Clientes;
import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.persistencia.Tiposdocumento;
import ar.com.jpack.persistencia.Unidadesmedida;
import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.persistencia.Tiposiva;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.TiposDocumentoT;
import ar.com.jpack.transferencia.TiposIvaT;
import ar.com.jpack.transferencia.UnidadesMedidaT;
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

    // ARTICULOS 1
    public static ArticulosT copiarArticulos(Articulos item) {
        ArticulosT t = null;
        if (item != null) {
            t = new ArticulosT(item.getIdArticulo(),
                    item.getCodigo(),
                    item.getDescripcion(),
                    item.getStock(),
                    item.getStockMinimo(),
                    item.getLeadTime(),
                    item.getFechaAlta(),
                    item.getFechaModificacion(),
                    copiarEstado(item.getIdEstado()),
                    copiarUnidadMedida(item.getIdUnidMedida()),
                    copiarUsuario(item.getIdUsuario()));
        }
        return t;
    }

    // ARTICULOS muchos
    public static List<ArticulosT> copiarArticulosALista(List<Articulos> items) {
        List<ArticulosT> lista = new ArrayList<ArticulosT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarArticulos((Articulos) i.next()));
        }

        return lista;
    }
    
    // CLIENTES 1
    public static ClientesT copiarCliente(Clientes item) {
        ClientesT t = null;
        if (item != null) {
            t = new ClientesT();
            t.setIdCliente(item.getIdCliente());
            t.setIdTipoIva(copiarTipoIva(item.getIdTipoIva()));
            t.setLimiteCredito(item.getLimiteCredito());
            t.setObservaciones(item.getObservaciones());
            t.setNombres(item.getNombres());
            t.setApellidos(item.getApellidos());
            t.setMails(item.getMails());
            t.setTelefonos(item.getTelefonos());
            t.setFechaAlta(item.getFechaAlta());
            t.setCuit(item.getCuit());
            t.setIdEstado(copiarEstado(item.getIdEstado()));
            t.setIdTipoDocumento(copiarTipoDocumento(item.getIdTipoDocumento()));
        }
        return t;
    }
    // Clientes muchos
    public static List<ClientesT> copiarClientesALista(Collection items) {
        List<ClientesT> lista = new ArrayList<ClientesT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarCliente((Clientes) i.next()));
        }

        return lista;
    }
    // ROLES muchos
    public static List<RolesT> copiarRolesALista(Collection items) {
        List<RolesT> lista = new ArrayList<RolesT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarRol((Roles) i.next()));
        }

        return lista;
    }

    // ROLES 1
    public static RolesT copiarRol(Roles item) {
        RolesT t = null;
        if (item != null) {
            t = new RolesT(item.getIdRol(),
                    item.getRol(),
                    item.getDescripcion(),
                    item.getComponente(),
                    item.getFuncion(),
                    item.getOrden(),
                    item.getOrdenHermano(),
                    copiarRol(item.getIdRolPadre()));
        }

        return t;
    }

    // USUARIOS muchos
    public static List<UsuariosT> copiarUsuariosALista(Collection items) {
        List<UsuariosT> lista = new ArrayList<UsuariosT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarUsuario((Usuarios) i.next()));
        }

        return lista;
    }

    // USUARIOS 1
    public static UsuariosT copiarUsuario(Usuarios item) {
        UsuariosT t = null;
        if (item != null) {
            t = new UsuariosT(item.getIdUsuario(),
                    item.getUsuario(),
                    item.getContrasena(),
                    item.getUltimoAcceso(),
                    item.getNombres(),
                    item.getApellidos(),
                    item.getMails(),
                    item.getTelefonos(),
                    copiarEstado(item.getIdEstado()));
        }
        return t;
    }

    // ESTADOS muchos
    public static List<EstadosT> copiarEstadosALista(Collection items) {
        List<EstadosT> lista = new ArrayList<EstadosT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarEstado((Estados) i.next()));
        }

        return lista;
    }

    // ESTADOS 1
    public static EstadosT copiarEstado(Estados item) {
        EstadosT t = null;
        if (item != null) {
            t = new EstadosT(item.getIdEstado(),
                    item.getDescripcion());
        }
        return t;
    }

    // TIPOS DOCUMENTOS 1
    public static TiposDocumentoT copiarTipoDocumento(Tiposdocumento item) {
        TiposDocumentoT t = null;
        if (item != null) {
            t = new TiposDocumentoT(item.getIdTipoDocumento(),
                    item.getDescripcion(),
                    item.getAbreviatura());
        }
        return t;
    }
    // TIPOS IVA 1
    public static TiposIvaT copiarTipoIva(Tiposiva item) {
        TiposIvaT t = null;
        if (item != null) {
            t = new TiposIvaT(item.getIdTipoIVA(),
                    item.getDescripcion(), 
                    item.getAbreviatura(), 
                    copiarEstado(item.getIdEstado()));
        }
        return t;
    }

    // Tipos Iva muchos
    public static List<TiposIvaT> copiarTiposIvaALista(List<Tiposiva> items) {
        List<TiposIvaT> lista = new ArrayList<TiposIvaT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarTipoIva((Tiposiva) i.next()));
        }

        return lista;
    }

    
    // UNIDADES MEDIDA muchos
    public static List<UnidadesMedidaT> copiarUnidadesMedidaALista(List<Unidadesmedida> items) {
        List<UnidadesMedidaT> lista = new ArrayList<UnidadesMedidaT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarUnidadMedida((Unidadesmedida) i.next()));
        }

        return lista;
    }
    // UNIDADES MEDIDA 1
    public static UnidadesMedidaT copiarUnidadMedida(Unidadesmedida item) {
        UnidadesMedidaT t = null;
        if (item != null) {
            t = new UnidadesMedidaT(item.getIdUnidMedida(),
                    item.getDescripcion(),
                    item.getAbreviatura());
        }
        return t;
    }
}
