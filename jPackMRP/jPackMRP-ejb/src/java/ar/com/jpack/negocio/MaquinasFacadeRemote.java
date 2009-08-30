/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Maquinas;
import ar.com.jpack.transferencia.MaquinasT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface MaquinasFacadeRemote {

    /**
     * Obtiene la lista de Maquinas filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMaquina</b>  filtra por 'eq' idMaquina (Integer) <br>
     * <b>pMantenimiento</b>  filtra si horasUso >= horasMantenimiento<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * @return devuelve la lista de los Maquinas que cumplan con el filtro
     */
    public List<MaquinasT> getMaquinasT(HashMap parametros);

    /**
     * Obtiene la lista de Maquinas filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMaquina</b>  filtra por 'eq' idMaquina (Integer) <br>
     * <b>pMantenimiento</b>  filtra si horasUso >= horasMantenimiento<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * @return devuelve la lista de los Maquinas que cumplan con el filtro
     */
    public List<Maquinas> getMaquinas(HashMap parametros);
}
