/*
 * UsuariosFacadeRemote.java
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.transferencia.UsuariosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface UsuariosFacadeRemote {

    /**
     * Actualiza o crea un usuarioT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     * 
     * @param usuariosT contiene los datos del usuario a actualizar
     * @return devuelve el usuarioT actualizado
     */
    public UsuariosT actualizarUsuariosT(UsuariosT usuariosT);

    /**
     * 
     * @return
     * @deprecated desde la llegada del dozer se debe usar getUsuariosT(Hashmap)
     */
    @Deprecated
    public List<UsuariosT> findAllUsuariosT();

    /**
     * Obtiene la lista de Usuarios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdUsuario</b>  filtra por 'eq' idUsuario (Integer) <br>
     * <b>pUsuario</b>    filtra por 'like AnyWhere' usuario (String) <br>
     * <b>pContrasena</b> filtra por 'like AnyWhere' contrasena (String) <br>
     * <b>pNombres</b>    filtra por 'like AnyWhere' nombres (String) <br>
     * <b>pApellidos</b>  filtra por 'like AnyWhere' apellidos (String) <br>
     * <b>pMails</b>      filtra por 'like AnyWhere' mails (String) <br>
     * <b>pTelefonos</b>  filtra por 'like AnyWhere' telefonos (String) <br>
     * <b>pJoinEstado</b> obliga a Joinear con Estados <br>
     * <b>pIdEstado</b>   filtra por 'eq' idEstado (Integer) (debe haber sido joineado con Estado) <br>
     * <b>pJoinRoles</b>  obliga a Joinear con Roles <br>
     * @return devuelve la lista de los Usuarios que cumplan con el filtro
     */
    public List<UsuariosT> getUsuariosT(HashMap parametros);

    /**
     *  Valida un usuario 
     *  recibe como parametro un UsuariosT con solo el nombre y la contraseña
     *  en caso de ser un usuario valido lo devuelve con todos los datos completados
     *  en caso de no ser valido devuelve el usuarioT sin modificar
     * 
     * @param usuariosT contiene los datos del usuario a validar
     * @return devuelve el usuarioT validado
     */
    public UsuariosT validarUsuario(UsuariosT usuariosT);

    /**
     * Obtiene la lista de Usuarios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdUsuario</b>  filtra por 'eq' idUsuario (Integer) <br>
     * <b>pUsuario</b>    filtra por 'like AnyWhere' usuario (String) <br>
     * <b>pContrasena</b> filtra por 'like AnyWhere' contrasena (String) <br>
     * <b>pNombres</b>    filtra por 'like AnyWhere' nombres (String) <br>
     * <b>pApellidos</b>  filtra por 'like AnyWhere' apellidos (String) <br>
     * <b>pMails</b>      filtra por 'like AnyWhere' mails (String) <br>
     * <b>pTelefonos</b>  filtra por 'like AnyWhere' telefonos (String) <br>
     * <b>pJoinEstado</b> obliga a Joinear con Estados <br>
     * <b>pIdEstado</b>   filtra por 'eq' idEstado (Integer) (debe haber sido joineado con Estado) <br>
     * <b>pJoinRoles</b>  obliga a Joinear con Roles <br>
     * @return devuelve la lista de los Usuarios que cumplan con el filtro
     */
    public List<Usuarios> getUsuarios(HashMap parametros);
}
