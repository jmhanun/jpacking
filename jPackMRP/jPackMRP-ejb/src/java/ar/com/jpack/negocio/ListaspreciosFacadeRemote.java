/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Listasprecios;
import ar.com.jpack.transferencia.ListasPreciosT;
import ar.com.jpack.transferencia.UsuariosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface ListaspreciosFacadeRemote {

    /**
     * Obtiene la lista de ListasPrecios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdLista</b>  filtra por 'eq' idLista (Integer) <br>
     * @return devuelve la lista de los ListasPrecios que cumplan con el filtro
     */
    public List<ListasPreciosT> getListasPreciosT(HashMap parametros);

    /**
     * Obtiene la lista de ListasPrecios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdLista</b>  filtra por 'eq' idLista (Integer) <br>
     * @return devuelve la lista de los ListasPrecios que cumplan con el filtro
     */
    public List<Listasprecios> getListasPrecios(HashMap parametros);

    public void insertarListasPreciosT(Double porcentaje, UsuariosT usuarioLogueado);
}
