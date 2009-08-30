/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Precios;
import ar.com.jpack.transferencia.PreciosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface PreciosFacadeRemote {

    /**
     * Obtiene la lista de Precios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdPrecio</b>  filtra por 'eq' idPrecio (Integer) <br>
     * <b>pJoinUsuarios</b>  obliga a joinear con Usuarios <br>
     * <b>pJoinArticulos</b>  obliga a joinear con Articulos <br>
     * <b>pJoinListasPrecios</b>  obliga a joinear con ListasPrecios <br>
     * @return devuelve la lista de los Precios que cumplan con el filtro
     */
    public List<PreciosT> getPreciosT(HashMap parametros);

    /**
     * Obtiene la lista de Precios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdPrecio</b>  filtra por 'eq' idPrecio (Integer) <br>
     * <b>pJoinUsuarios</b>  obliga a joinear con Usuarios <br>
     * <b>pJoinArticulos</b>  obliga a joinear con Articulos <br>
     * <b>pJoinListasPrecios</b>  obliga a joinear con ListasPrecios <br>
     * @return devuelve la lista de los Precios que cumplan con el filtro
     */
    public List<Precios> getPrecios(HashMap parametros);
}
