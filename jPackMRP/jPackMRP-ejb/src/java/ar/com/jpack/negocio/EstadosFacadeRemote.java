/*
 * EstadosFacadeRemote.java
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.transferencia.EstadosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface EstadosFacadeRemote {

    @Deprecated
    Estados find( Object id);

    @Deprecated
    public EstadosT findEstado(Integer id);

    /**
     * Obtiene la lista de Estados filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdEstados</b>           filtra por 'eq' idUsuario (Integer) <br>
     * <b>pDescripcionEstado</b>   filtra por 'like AnyWhere' descripcion del Estado (String) <br>
     * <b>pNotas</b>               filtra por 'like AnyWhere' notas (String) <br>
     * <b>pJoinDominios</b>        obliga a Joinear con Dominios <br>
     * <b>pIdDominio</b>           filtra por 'eq' idDominio (Integer) <br>
     * <b>pDescripcionDominio</b>  filtra por 'like AnyWhere' descripcion del Dominio (String) <br>
     * @return devuelve la lista de los Estados que cumplan con el filtro <br>
     */
    public List<EstadosT> getEstadosT(HashMap parametros);

    /**
     * Obtiene la lista de Estados filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdEstados</b>           filtra por 'eq' idUsuario (Integer) <br>
     * <b>pDescripcionEstado</b>   filtra por 'like AnyWhere' descripcion del Estado (String) <br>
     * <b>pNotas</b>               filtra por 'like AnyWhere' notas (String) <br>
     * <b>pJoinDominios</b>        obliga a Joinear con Dominios <br>
     * <b>pIdDominio</b>           filtra por 'eq' idDominio (Integer) <br>
     * <b>pDescripcionDominio</b>  filtra por 'like AnyWhere' descripcion del Dominio (String) <br>
     * @return devuelve la lista de los Estados que cumplan con el filtro <br>
     */
    public List<Estados> getEstados(HashMap parametros);
}
