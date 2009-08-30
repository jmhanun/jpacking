/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Mantenimiento;
import ar.com.jpack.transferencia.MantenimientoT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface MantenimientoFacadeRemote {

    /**
     * Obtiene la lista de Mantenimientos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMantenimiento</b>  filtra por 'eq' idMantenimiento (Integer) <br>
     * <b>pFechaFinNull</b>  filtra por 'isNull' fechaFin (Integer) <br>
     * <b>pJoinMaquinas</b>  obliga a Joinear con Maquinas<br>
     * <b>pJoinTiposServicios</b>  obliga a Joinear con TiposServicios<br>
     * @return devuelve la lista de los Mantenimientos que cumplan con el filtro
     */
    public List<MantenimientoT> getMantenimientoT(HashMap parametros);

    /**
     * Obtiene la lista de Mantenimientos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMantenimiento</b>  filtra por 'eq' idMantenimiento (Integer) <br>
     * <b>pFechaFinNull</b>  filtra por 'isNull' fechaFin (Integer) <br>
     * <b>pJoinMaquinas</b>  obliga a Joinear con Maquinas<br>
     * <b>pJoinTiposServicios</b>  obliga a Joinear con TiposServicios<br>
     * @return devuelve la lista de los Mantenimientos que cumplan con el filtro
     */
    public List<Mantenimiento> getMantenimiento(HashMap parametros);
}
