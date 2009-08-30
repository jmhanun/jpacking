/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposservicios;
import ar.com.jpack.transferencia.TiposServiciosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposserviciosFacadeRemote {

    /**
     * Obtiene la lista de TiposServicios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTiposServicios</b>  filtra por 'eq' idTiposServicios(Integer) <br>
     * @return devuelve la lista de los TiposServicios que cumplan con el filtro
     */
    public List<TiposServiciosT> getTiposServiciosT(HashMap parametros);

    /**
     * Obtiene la lista de TiposServicios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTiposServicios</b>  filtra por 'eq' idTiposServicios(Integer) <br>
     * @return devuelve la lista de los TiposServicios que cumplan con el filtro
     */
    public List<Tiposservicios> getTiposServicios(HashMap parametros);
}
