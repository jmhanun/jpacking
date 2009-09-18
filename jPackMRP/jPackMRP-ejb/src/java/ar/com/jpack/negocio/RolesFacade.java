/*
 * RolesFacade.java
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author jmhanun
 */
@Stateless
public class RolesFacade implements RolesFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Roles filtrados por el Hasmap
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
     * @return devuelve la lista de los Roles que cumplan con el filtro
     */
    public List<RolesT> getRolesT(HashMap parametros) {
        List<Roles> rolesList = getRoles(parametros);
        List<RolesT> rolesTList = new ArrayList();
        for (Roles c : rolesList) {
            RolesT rdo = (RolesT) DozerUtil.getDozerMapper(false).map(c, RolesT.class);
            rolesTList.add(rdo);
        }
        return rolesTList;
    }

    /**
     * Obtiene la lista de Roles filtrados por el Hasmap
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
     * @return devuelve la lista de los Roles que cumplan con el filtro
     */
    public List<Roles> getRoles(HashMap parametros) {
        Criteria rolesCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Roles.class);
        List<Roles> rolesList;
        if (parametros.containsKey("pIdRol")) {
            rolesCritearia.add(Restrictions.eq("idRol", parametros.get("pIdRol")));
        }
        if (parametros.containsKey("pRol")) {
            rolesCritearia.add(Restrictions.like("rol", parametros.get("pRol").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pDescripcion")) {
            rolesCritearia.add(Restrictions.like("descripcion", parametros.get("pDescripcion").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pComponente")) {
            rolesCritearia.add(Restrictions.like("componente", parametros.get("pComponente").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pFuncion")) {
            rolesCritearia.add(Restrictions.like("funcion", parametros.get("pFuncion").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pOrden")) {
            rolesCritearia.add(Restrictions.eq("orden", parametros.get("pOrden")));
        }
        if (parametros.containsKey("pOrdenHermano")) {
            rolesCritearia.add(Restrictions.eq("ordenHermano", parametros.get("pOrdenHermano")));
        }
        if (parametros.containsKey("pJoinUsuarios")) {
            rolesCritearia.setFetchMode("idUsuarioCollection", FetchMode.JOIN);
            if (parametros.containsKey("pIdUsuario")) {
                //Con esto filtro por el objeto que estaba lazy
                Criteria usuarioCriteria = rolesCritearia.createCriteria("idUsuarioCollection");
                usuarioCriteria.add(Restrictions.eq("idUsuario", parametros.get("pIdUsuario")));
            }
        }
        rolesList = rolesCritearia.list();
        return rolesList;
    }

    public List<Roles> getMenues(boolean isMenu) {
        Criteria rolesCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Roles.class);
        List<Roles> rolesList;
        if (isMenu) {
            rolesCritearia.add(Restrictions.isNull("funcion"));
        } else {
            rolesCritearia.add(Restrictions.isNotNull("funcion"));
        }
        rolesList = rolesCritearia.list();
        return rolesList;
    }

    public List<RolesT> getMenuesT(boolean isMenu) {
        List<Roles> rolesList = getMenues(isMenu);
        List<RolesT> rolesTList = new ArrayList();
        for (Roles c : rolesList) {
            RolesT rdo = (RolesT) DozerUtil.getDozerMapper(false).map(c, RolesT.class);
            rolesTList.add(rdo);
        }
        return rolesTList;
    }

    public RolesT updateRolesT(RolesT rolesT) {

        Roles roles = (Roles) DozerUtil.getDozerMapper(false).map(rolesT, Roles.class);
        if (roles.getFuncion() != null) {
            String funcion = roles.getFuncion().trim();
            if (funcion.equals("")) {
                roles.setFuncion(null);
            }
        }
        //si el numero de id es null significa que es nuevo
        if (roles.getIdRol() != null) {
            HashMap parametros = new HashMap();
            parametros.put("pIdRol", roles.getIdRol());

            if (roles.getIdRolPadre() != null) {
                if (!(roles.getIdRolPadre().getIdRol().equals(getRoles(parametros).get(0).getIdRolPadre().getIdRol()))) {
                    roles.setOrden(getNextOrden(roles));
                    roles.setOrdenHermano(getNextOrdenHermano(roles));
                }
            }
            em.merge(roles);
        } else {
            roles.setOrden(getNextOrden(roles));
            roles.setOrdenHermano(getNextOrdenHermano(roles));
            em.persist(roles);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdRol", roles.getIdRol());
        return getRolesT(parametros).get(0);
    }

    private int getNextOrden(Roles roles) {

        if (roles.getIdRolPadre() == null) {
            return 0;
        } else {
            HashMap parametros = new HashMap();
            parametros.put("pIdRol", roles.getIdRolPadre().getIdRol());
            int orden = getRoles(parametros).get(0).getOrden() + 1;
            return orden;
        }
    }

    private int getNextOrdenHermano(Roles roles) {

        Query query;
        if (roles.getIdRolPadre() != null) {
            query = ((EntityManagerImpl) em.getDelegate()).getSession().createQuery(
                    "SELECT max(r.ordenHermano) " +
                    "from Roles r " +
                    "Where r.orden = :ordenRol " +
                    "AND r.idRolPadre = :rolPadre");
            query.setInteger("ordenRol", roles.getOrden());
            query.setEntity("rolPadre", roles.getIdRolPadre());
        } else {
            query = ((EntityManagerImpl) em.getDelegate()).getSession().createQuery(
                    "SELECT max(r.ordenHermano) " +
                    "from Roles r " +
                    "Where r.orden = :ordenRol");
            query.setInteger("ordenRol", roles.getOrden());
        }
        Integer ordenHermano = (Integer) query.uniqueResult();
        if (ordenHermano == null) {
            ordenHermano = 0;
        } else {
            ordenHermano++;
        }
        return ordenHermano;
    }
}