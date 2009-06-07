/*
 * RolesFacadeRemote.java
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.transferencia.RolesT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface RolesFacadeRemote {

    /**
     * Obtiene la lista de Usuarios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRol</b>        filtra por 'eq' idRol (Integer) <br>
     * <b>pRol</b>          filtra por 'like AnyWhere' rol (String) <br>
     * <b>pDescripcion</b>  filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pComponente</b>   filtra por 'like AnyWhere' componente (String) <br>
     * <b>pFuncion</b>      filtra por 'like AnyWhere' funcion (String) <br>
     * <b>pOrden</b>        filtra por 'eq' orden (int) <br>
     * <b>pOrdenHermano</b> filtra por 'eq' ordenHermano (int) <br>
     * <b>pJoinUsuarios</b> obliga a Joinear con Usuarios <br>
     * <b>pIdUsuario</b>    filtra por 'eq' idUsuario (Integer) (debe haber sido joineado con Estado) <br>
     * @return devuelve la lista de los Usuarios que cumplan con el filtro
     */
    public List<RolesT> getRolesT(HashMap parametros);

    /**
     * Obtiene la lista de Usuarios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRol</b>        filtra por 'eq' idRol (Integer) <br>
     * <b>pRol</b>          filtra por 'like AnyWhere' rol (String) <br>
     * <b>pDescripcion</b>  filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pComponente</b>   filtra por 'like AnyWhere' componente (String) <br>
     * <b>pFuncion</b>      filtra por 'like AnyWhere' funcion (String) <br>
     * <b>pOrden</b>        filtra por 'eq' orden (int) <br>
     * <b>pOrdenHermano</b> filtra por 'eq' ordenHermano (int) <br>
     * <b>pJoinUsuarios</b> obliga a Joinear con Usuarios <br>
     * <b>pIdUsuario</b>    filtra por 'eq' idUsuario (Integer) (debe haber sido joineado con Estado) <br>
     * @return devuelve la lista de los Usuarios que cumplan con el filtro
     */
    public List<Roles> getRoles(HashMap parametros);

    public RolesT updateRolesT(RolesT rolesT);

    public List<Roles> getMenues(boolean isMenu);

    public List<RolesT> getMenuesT(boolean isMenu);
}
