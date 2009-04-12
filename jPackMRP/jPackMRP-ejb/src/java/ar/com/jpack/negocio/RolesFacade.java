/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Roles;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class RolesFacade implements RolesFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(Roles roles) {
        em.persist(roles);
    }

    public void edit(Roles roles) {
        em.merge(roles);
    }

    public void remove(Roles roles) {
        em.remove(em.merge(roles));
    }

    public Roles find(Object id) {
        return em.find(ar.com.jpack.persistencia.Roles.class, id);
    }

    public List<Roles> findAll() {
        return ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Roles.class).list();
    }

    public List<RolesT> findAllUsuariosT() {
        List<Roles> roles = findAll();
        return DataTransferHelper.copiarRolesALista(roles);
    }

    public List<RolesT> getRolesT(HashMap parametros) {

        Criteria rolesCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Roles.class);
        List<Roles> rolesList;
        List<RolesT> rolesTList = new ArrayList();

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
        for (Roles c : rolesList) {
            RolesT rdo = (RolesT) DozerUtil.getDozerMapper(false).map(c, RolesT.class);
            rolesTList.add(rdo);
        }
        return rolesTList;
    }
}
