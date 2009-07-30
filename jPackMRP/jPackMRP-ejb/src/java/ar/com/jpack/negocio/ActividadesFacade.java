/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Actividades;
import ar.com.jpack.persistencia.Actividadesxarticulos;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import ar.com.jpack.transferencia.ActividadesT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ActividadesFacade implements ActividadesFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<ActividadesT> getActividadesT(HashMap parametros) {
        List<Actividades> actividadesList = getActividades(parametros);
        List<ActividadesT> actividadesTList = new ArrayList();
        for (Actividades c : actividadesList) {
            ActividadesT rdo = (ActividadesT) DozerUtil.getDozerMapper(false).map(c, ActividadesT.class);
            actividadesTList.add(rdo);
        }
        return actividadesTList;
    }

    public List<Actividades> getActividades(HashMap parametros) {
        Criteria actividadesCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Actividades.class);
        List<Actividades> actividadesList;
        if (parametros.containsKey("pIdActividad")) {
            actividadesCriteria.add(Restrictions.eq("idActividad", parametros.get("pIdActividad")));
        }

        if (parametros.containsKey("pJoinActividadesxArticulos")) {
            actividadesCriteria.setFetchMode("actividadesxarticulosCollection", FetchMode.JOIN);
            if (parametros.containsKey("pIdArticulo")) {
                //Con esto filtro por el objeto que estaba lazy
                Criteria actxartCriteria = actividadesCriteria.createCriteria("actividadesxarticulosCollection");
                Criteria articulosCriteria = actxartCriteria.createCriteria("articulos");
                articulosCriteria.add(Restrictions.eq("idArticulo", parametros.get("pIdArticulo")));
            }
        }

        actividadesList = actividadesCriteria.list();
        return actividadesList;
    }

    public List<ActividadesArticulosT> getActividadesArticulosT(HashMap parametros) {
        List<Actividadesxarticulos> actividadesArticulosList = getActividadesArticulos(parametros);
        List<ActividadesArticulosT> actividadesArticulosTList = new ArrayList();
        for (Actividadesxarticulos c : actividadesArticulosList) {
            ActividadesArticulosT rdo = (ActividadesArticulosT) DozerUtil.getDozerMapper(false).map(c, ActividadesArticulosT.class);
            actividadesArticulosTList.add(rdo);
        }
        return actividadesArticulosTList;
    }

    public List<Actividadesxarticulos> getActividadesArticulos(HashMap parametros) {
        Criteria actividadesArticulosCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Actividadesxarticulos.class);
        List<Actividadesxarticulos> actividadesArticulosList;

        actividadesArticulosCriteria.setFetchMode("actividades", FetchMode.JOIN);
        actividadesArticulosCriteria.setFetchMode("articulos", FetchMode.JOIN);

        if (parametros.containsKey("pIdActividad")) {
            //Con esto filtro por el objeto que estaba lazy
            Criteria actCriteria = actividadesArticulosCriteria.createCriteria("actividades");
            actCriteria.add(Restrictions.eq("idActividad", parametros.get("pIdActividad")));
        }

        if (parametros.containsKey("pIdArticulo")) {
            //Con esto filtro por el objeto que estaba lazy
            Criteria artCriteria = actividadesArticulosCriteria.createCriteria("articulos");
            artCriteria.add(Restrictions.eq("idArticulo", parametros.get("pIdArticulo")));
        }

        actividadesArticulosList = actividadesArticulosCriteria.list();
        return actividadesArticulosList;
    }
}
