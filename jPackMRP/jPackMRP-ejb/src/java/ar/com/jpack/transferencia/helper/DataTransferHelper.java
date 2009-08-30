/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia.helper;

import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.persistencia.Unidadesmedida;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.UnidadesMedidaT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class DataTransferHelper {
    

    
    // ESTADOS 1
    public static EstadosT copiarEstado(Estados item) {
        EstadosT t = null;
        if (item != null) {
            t = new EstadosT(item.getIdEstado(),
                    item.getDescripcion(), item.getNotas());
        }
        return t;
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
