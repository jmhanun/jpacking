
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Usuarios;
import ar.com.jpack.transferencia.UsuariosT;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import ar.com.jpack.util.DozerUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author jmhanun
 */
@Stateless
public class UsuariosFacade implements UsuariosFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     *  Valida un usuario 
     *  recibe como parametro un UsuariosT con solo el nombre y la contraseña
     *  en caso de ser un usuario valido lo devuelve con todos los datos completados
     *  en caso de no ser valido devuelve el usuarioT sin modificar
     * 
     * @param usuariosT contiene los datos del usuario a validar
     * @return devuelve el usuarioT validado
     */
    public UsuariosT validarUsuario(UsuariosT usuariosT) {
        StringBuffer codificado = codificar(usuariosT.getContrasena());
        usuariosT.setContrasena(codificado.toString());
        HashMap parametros = new HashMap();
        parametros.put("pJoinRoles", true);
        parametros.put("pJoinEstados", true);
        parametros.put("pUsuario", usuariosT.getUsuario());
        parametros.put("pContrasena", usuariosT.getContrasena());
        List<Usuarios> listUsuarios = getUsuarios(parametros);
        if (listUsuarios.size() != 1) {
            return usuariosT;
        } else {
            return (UsuariosT) DozerUtil.getDozerMapper(false).map(listUsuarios.get(0), UsuariosT.class);
        }
    }

    private StringBuffer codificar(String string) {
        MessageDigest messagedigest = null;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        messagedigest.update(string.getBytes());
        byte[] hash = messagedigest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xFF & hash[i]);
            if (hex.length() == 1) {
                hexString.append("0" + hex);
            } else {
                hexString.append(hex);
            }
        }
        return hexString;
    }

    /**
     * 
     * @return
     * @deprecated desde la llegada del dozer se debe usar getUsuariosT(Hashmap)
     */
    @Deprecated
    public List<UsuariosT> findAllUsuariosT() {
        List<Usuarios> usuarios = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Usuarios.class).list();
        List<UsuariosT> usuariosTs = new ArrayList<UsuariosT>();
        for (Iterator<Usuarios> it = usuarios.iterator(); it.hasNext();) {
            Usuarios usuario = it.next();
            UsuariosT usuariosT;
            usuariosT = DataTransferHelper.copiarUsuario(usuario);
            usuariosT.setIdRolCollection((ArrayList<RolesT>) DataTransferHelper.copiarRolesALista(usuario.getIdRolCollection()));
            usuariosTs.add(usuariosT);
        }
        return usuariosTs;
    }

    /**
     * Actualiza o crea un usuarioT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     * 
     * @param usuariosT contiene los datos del usuario a actualizar
     * @return devuelve el usuarioT actualizado
     */
    public UsuariosT actualizarUsuariosT(UsuariosT usuariosT) {
        Usuarios usuarios = (Usuarios) DozerUtil.getDozerMapper(false).map(usuariosT, Usuarios.class);
        //si el numero de usuario es null significa que es un nuevo usuario
        if (usuarios.getIdUsuario() != null) {
            em.merge(usuarios);
        } else {
            em.persist(usuarios);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdUsuario", usuarios.getIdUsuario());
        parametros.put("pJoinRoles", true);
        parametros.put("pJoinEstados", true);
        return getUsuariosT(parametros).get(0);
//        //si el numero de usuario es null, significa que es un nuevo usuario.
//        if (usuariosT.getIdUsuario() != null) {
//            usuarios = em.find(Usuarios.class, usuariosT.getIdUsuario());
//            usuarios.setUltimoAcceso(usuariosT.getUltimoAcceso());
//            cargarDatos(usuariosT, usuarios);
//            edit(usuarios);
//            em.flush();
//        } else {
//            usuarios = new Usuarios();
//            StringBuffer codificado = codificar(usuariosT.getContrasena());
//            usuariosT.setContrasena(codificado.toString());
//            usuarios.setContrasena(usuariosT.getContrasena());
//            usuarios.setUsuario(usuariosT.getUsuario());
//            usuariosT.setIdEstado(new EstadosT(1, "HABILITADO"));
//            cargarDatos(usuariosT, usuarios);
//            create(usuarios);
//        }
//        em.flush();
//        usuariosT = DataTransferHelper.copiarUsuario(usuarios);
//        usuariosT.setIdRolCollection((ArrayList<RolesT>) DataTransferHelper.copiarRolesALista(usuarios.getIdRolCollection()));
//        return usuariosT;
    }
//
//    private void cargarDatos(UsuariosT usuariosT, Usuarios usuarios) {
//        Estados estado = em.find(Estados.class, usuariosT.getIdEstado().getIdEstado());
//        usuarios.setIdEstado(estado);
//        usuarios.setApellidos(usuariosT.getApellidos());
//        usuarios.setMails(usuariosT.getMails());
//        usuarios.setNombres(usuariosT.getNombres());
//        if (usuariosT.getIdRolCollection() != null) {
//            List<Roles> roles = new ArrayList<Roles>();
//            for (Iterator<RolesT> it = usuariosT.getIdRolCollection().iterator(); it.hasNext();) {
//                RolesT rolesT = it.next();
//                if (rolesT.getIdRol() != null) {
//                    Roles rol = em.find(Roles.class, rolesT.getIdRol());
//                    roles.add(rol);
//                }
//            }
//            usuarios.setIdRolCollection(roles);
//        }
//    }

    /**
     * Obtiene la lista de UsuariosT filtrados por el Hasmap
     * Usa la funcion getUsuarios(Hashmap)
     * @param parametros
     * Lista de parametros:
     * pIdUsuario  filtra por 'eq' idUsuario (Integer)
     * pUsuario    filtra por 'like AnyWhere' usuario (String)
     * pContrasena filtra por 'like AnyWhere' contrasena (String)
     * pNombres    filtra por 'like AnyWhere' nombres (String)
     * pApellidos  filtra por 'like AnyWhere' apellidos (String)
     * pMails      filtra por 'like AnyWhere' mails (String)
     * pTelefonos  filtra por 'like AnyWhere' telefonos (String)
     * pJoinEstado obliga a Joinear con Estados
     * pIdEstado   filtra por 'eq' idEstado (Integer) (debe haber sido joineado con Estado)
     * pJoinRoles  obliga a Joinear con Roles
     * @return devuelve la lista de los UsuariosT que cumplan con el filtro
     */
    public List<UsuariosT> getUsuariosT(HashMap parametros) {
        List<Usuarios> usuariosList = getUsuarios(parametros);
        List<UsuariosT> usuariosTList = new ArrayList<UsuariosT>();

        for (Usuarios c : usuariosList) {
            UsuariosT rdo = (UsuariosT) DozerUtil.getDozerMapper(false).map(c, UsuariosT.class);
            usuariosTList.add(rdo);
        }
        return usuariosTList;
    }

    /**
     * Obtiene la lista de Usuarios filtrados por el Hasmap
     * @param parametros
     * Lista de parametros:
     * pIdUsuario  filtra por 'eq' idUsuario (Integer)
     * pUsuario    filtra por 'like AnyWhere' usuario (String)
     * pContrasena filtra por 'like AnyWhere' contrasena (String)
     * pNombres    filtra por 'like AnyWhere' nombres (String)
     * pApellidos  filtra por 'like AnyWhere' apellidos (String)
     * pMails      filtra por 'like AnyWhere' mails (String)
     * pTelefonos  filtra por 'like AnyWhere' telefonos (String)
     * pJoinEstado obliga a Joinear con Estados
     * pIdEstado   filtra por 'eq' idEstado (Integer) (debe haber sido joineado con Estado)
     * pJoinRoles  obliga a Joinear con Roles
     * @return devuelve la lista de los Usuarios que cumplan con el filtro
     */
    public List<Usuarios> getUsuarios(HashMap parametros) {
        Criteria usuariosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Usuarios.class);
        List<Usuarios> usuariosList;
        if (parametros.containsKey("pIdUsuario")) {
            usuariosCritearia.add(Restrictions.eq("idUsuario", parametros.get("pIdUsuario")));
        }
        if (parametros.containsKey("pUsuario")) {
            usuariosCritearia.add(Restrictions.like("usuario", parametros.get("pUsuario").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pContrasena")) {
            usuariosCritearia.add(Restrictions.like("contrasena", parametros.get("pContrasena").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pUltimoAcceso")) {
            usuariosCritearia.add(Restrictions.like("ultimoAcceso", parametros.get("pUltimoAcceso").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pNombres")) {
            usuariosCritearia.add(Restrictions.like("nombres", parametros.get("pNombres").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pApellidos")) {
            usuariosCritearia.add(Restrictions.like("apellidos", parametros.get("pApellidos").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pMails")) {
            usuariosCritearia.add(Restrictions.like("mails", parametros.get("pMails").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pTelefonos")) {
            usuariosCritearia.add(Restrictions.like("telefonos", parametros.get("pTelefonos").toString(), MatchMode.ANYWHERE));
        }
        //Con el setFetchMode obliga a que el lazy se ponga las pilas
        if (parametros.containsKey("pJoinEstados")) {
            usuariosCritearia.setFetchMode("idEstado", FetchMode.JOIN);
            if (parametros.containsKey("pIdEstado")) {
                //Con esto filtro por el objeto que estaba lazy
                Criteria estadoCriteria = usuariosCritearia.createCriteria("idEstado");
                estadoCriteria.add(Restrictions.eq("idEstado", parametros.get("pIdEstado")));
            }
        }
        if (parametros.containsKey("pJoinRoles")) {
            usuariosCritearia.setFetchMode("idRolCollection", FetchMode.JOIN);
            usuariosCritearia.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }
        usuariosList = usuariosCritearia.list();
        return usuariosList;
    }
}
