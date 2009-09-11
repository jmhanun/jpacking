/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Setup;
import ar.com.jpack.transferencia.SetupT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface SetupFacadeRemote {

    /**
     * Obtiene la lista de Setup filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdSetup</b>           filtra por 'eq' idRol (Integer) <br>
     * <b>pDescripcion</b>       filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pValor</b>             filtra por 'like AnyWhere' valor (String) <br>
     * <b>pFechaModificacion</b> filtra por 'eq' fechaModificacion (Date) <br>
     * <b>pJoinUsuarios</b>      obliga a Joinear con Usuarios <br>
     * <b>pIdUsuario</b>         filtra por 'eq' idUsuario (Integer) (debe haber sido joineado con Estado) <br>
     * @return devuelve la lista de los Setup que cumplan con el filtro
     */
    public List<SetupT> getSetupT(HashMap parametros);

    /**
     * Obtiene la lista de Setup filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdSetup</b>           filtra por 'eq' idRol (Integer) <br>
     * <b>pDescripcion</b>       filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pValor</b>             filtra por 'like AnyWhere' valor (String) <br>
     * <b>pFechaModificacion</b> filtra por 'eq' fechaModificacion (Date) <br>
     * <b>pJoinUsuarios</b>      obliga a Joinear con Usuarios <br>
     * <b>pIdUsuario</b>         filtra por 'eq' idUsuario (Integer) (debe haber sido joineado con Estado) <br>
     * @return devuelve la lista de los Setup que cumplan con el filtro
     */
    public List<Setup> getSetup(HashMap parametros);

    public String getValorSetup(Integer idSetup);
}
