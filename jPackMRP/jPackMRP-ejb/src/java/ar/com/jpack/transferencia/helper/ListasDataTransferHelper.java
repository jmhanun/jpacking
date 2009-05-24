/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia.helper;

import ar.com.jpack.persistencia.Clientes;
import ar.com.jpack.transferencia.listas.ClientesListaT;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class ListasDataTransferHelper {

    // CLIENTES 1
    public static ClientesListaT copiarCliente(Clientes item) {
        ClientesListaT t = null;
        if (item != null) {
            t = new ClientesListaT();
            t.setIdCliente(item.getIdCliente());
            t.setNombres(item.getNombres());
            t.setCuit(item.getCuit());
        }
        return t;
    }
    // Clientes muchos
    public static List<ClientesListaT> copiarClientesALista(Collection items) {
        List<ClientesListaT> lista = new ArrayList<ClientesListaT>();
        Iterator i = items.iterator();
        while (i.hasNext()) {
            lista.add(copiarCliente((Clientes) i.next()));
        }

        return lista;
    }
}
