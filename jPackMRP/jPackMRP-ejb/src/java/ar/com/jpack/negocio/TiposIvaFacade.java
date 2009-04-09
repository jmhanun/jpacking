/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.persistencia.Tiposiva;
import ar.com.jpack.transferencia.TiposIvaT;
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
public class TiposIvaFacade implements TiposIvaFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<TiposIvaT> getTiposIvaT(HashMap parametros) {

        Criteria tiposIvaCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Tiposiva.class);
        List<Tiposiva> tiposIvaList;
        List<TiposIvaT> tiposIvaTList = new ArrayList();
        if (parametros.containsKey("pIdTipoIva")) {
            tiposIvaCritearia.add(Restrictions.eq("idTipoIVA", parametros.get("pIdTipoIva")));
        }
        if (parametros.containsKey("pDescripcion")) {
            tiposIvaCritearia.add(Restrictions.like("descripcion", parametros.get("pDescripcion").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pAbreviatura")) {
            tiposIvaCritearia.add(Restrictions.like("abreviatura", parametros.get("pAbreviatura").toString(), MatchMode.ANYWHERE));
        }

        if (parametros.containsKey("pIdEstado")) {
            //Con el setFetchMode obliga a que el lazy se ponga las pilas
            tiposIvaCritearia.setFetchMode("idEstado", FetchMode.JOIN);

            //Con esto filtro por el objeto que estaba lazy
            Criteria estadoCriteria = tiposIvaCritearia.createCriteria("idEstado");
            
            estadoCriteria.add(Restrictions.eq("idEstado", parametros.get("pIdEstado")));
        }



        /*
        Criteria estadoCriteria=clienteCritearia.createCriteria("idEstado");
        estadoCriteria.setFetchMode("FacturaCollecoin", FetchMode.JOIN);
        estadoCriteria.add(Restrictions.like("nombreEstado", "ACTIVO"));
         */

        tiposIvaList = tiposIvaCritearia.list();
        for (Tiposiva c : tiposIvaList) {
            TiposIvaT rdo = (TiposIvaT) DozerUtil.getDozerMapper(false).map(c, TiposIvaT.class);
            tiposIvaTList.add(rdo);
        }
        return tiposIvaTList;
    }
//    public TiposIvaT getTipoIvaT(Integer idTipoIva) {
//        HashMap parametros = new HashMap();
//        parametros.put("pIdTipoIva", idTipoIva);
//        List<Tiposiva> tiposIva = findTiposIvaT(parametros);
////        SELECT a FROM Tiposiva a WHERE a.idTipoIva = :idTipoIva
////        SELECT t FROM Tiposiva t WHERE t.idTipoIVA = :idTipoIVA
////        Query q = em.createQuery("SELECT t FROM Tiposiva t WHERE t.idTipoIVA = :idTipoIVA");
////        q.setParameter("idTipoIva", idTipoIva);
////        List<Tiposiva> tiposIva = q.getResultList();
//        return DataTransferHelper.copiarTipoIva(tiposIva.get(0));
//    }
    public void addTipoIva(TiposIvaT nuevoIva) {
        Tiposiva tipoIva = new Tiposiva();
        Estados estado = em.find(ar.com.jpack.persistencia.Estados.class,
                nuevoIva.getIdEstado().getIdEstado());

        tipoIva.setAbreviatura(nuevoIva.getAbreviatura());
        tipoIva.setDescripcion(nuevoIva.getDescripcion());
        tipoIva.setIdEstado(estado);
        tipoIva.setIdTipoIVA(
                99);
        em.persist(tipoIva);
        em.flush();
    }//    private List<Tiposiva> findTiposIvaT(HashMap parametros) {
//        String sql = "SELECT a FROM Tiposiva a";
//        boolean condicion = false;
//        if (parametros.containsKey("pIdTipoIva")) {
//            sql += " WHERE a.idTipoIVA = :idTipoIva";
//            condicion = true;
//        }
//        if (parametros.containsKey("pDescripcion")) {
//            if (condicion) {
//                sql += " AND a.descripcion like :descripcion";
//            } else {
//                sql += " WHERE a.descripcion like :descripcion";
//                condicion = true;
//            }
//        }
//        if (parametros.containsKey("pAbreviatura")) {
//            if (condicion) {
//                sql += " AND a.abreviatura like :abreviatura";
//            } else {
//                sql += " WHERE a.abreviatura like :abreviatura";
//                condicion = true;
//            }
//        }
//        if (parametros.containsKey("pEstado")) {
//            if (condicion) {
//                sql += " AND a.idEstado.idEstado = :estado";
//            } else {
//                sql += " WHERE a.idEstado.idEstado = :estado";
//                condicion = true;
//            }
//        }
//        Query query = em.createQuery(sql);
//
//        if (parametros.containsKey("pIdTipoIva")) {
//            query.setParameter("idTipoIva", parametros.get("pIdTipoIva"));
//        }
//        if (parametros.containsKey("pDescripcion")) {
//            query.setParameter("descripcion", parametros.get("pDescripcion"));
//        }
//        if (parametros.containsKey("pAbreviatura")) {
//            query.setParameter("abreviatura", parametros.get("pAbreviatura"));
//        }
//        if (parametros.containsKey("pEstado")) {
//            query.setParameter("estado", parametros.get("pEstado"));
//        }
//        query.setHint("toplink.refresh", "true");
//        List<Tiposiva> tiposIva = query.getResultList();
//        return tiposIva;
//    }
}
