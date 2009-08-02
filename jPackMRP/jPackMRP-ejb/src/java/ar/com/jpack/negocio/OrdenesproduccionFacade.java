/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detordenesproduccion;
import ar.com.jpack.persistencia.Ordenesproduccion;
import ar.com.jpack.transferencia.DetOrdenesProduccionT;
import ar.com.jpack.transferencia.OrdenesProduccionT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
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
public class OrdenesproduccionFacade implements OrdenesproduccionFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private TiposComprobantesFacadeRemote tiposComprobantesFacade;
    @EJB
    private EstadosFacadeRemote estadosFacade;
    @EJB
    private PrioridadesFacadeRemote prioridadFacade;

    public OrdenesProduccionT updateOrdenesProduccionT(OrdenesProduccionT opT, ArrayList<DetOrdenesProduccionT> listaDetalleOPT) {

        HashMap parametros = new HashMap();
        Ordenesproduccion ordenesProduccion = (Ordenesproduccion) DozerUtil.getDozerMapper(false).map(opT, Ordenesproduccion.class);

        //si el numero de id es null significa que es nuevo
        if (ordenesProduccion.getIdOrdenProduccion() != null) {
            em.merge(ordenesProduccion);
            parametros.put("pIdOrdenProduccion", ordenesProduccion.getIdRemito());

            return getOrdenesProduccionT(parametros).get(0);
        } else {
            ordenesProduccion.setNroOrdenProduccion(getNextOrdenProduccion());
            parametros.put("pIdEstados", opT.getIdEstado().getIdEstado());
            ordenesProduccion.setIdEstado((estadosFacade.getEstados(parametros)).get(0));
            parametros = new HashMap();
            parametros.put("pIdTipoComprobante", opT.getIdTipoComprobante().getIdTipoComprobante());
            ordenesProduccion.setIdTipoComprobante((tiposComprobantesFacade.getTiposComprobantes(parametros)).get(0));
            parametros = new HashMap();
            parametros.put("pIdPrioridad", opT.getIdPrioridad().getIdPrioridad());
            ordenesProduccion.setIdPrioridad((prioridadFacade.getPrioridades(parametros)).get(0));

            em.persist(ordenesProduccion);
            parametros.put("pIdOrdenProduccion", ordenesProduccion.getIdOrdenProduccion());

            OrdenesProduccionT ordenProduccionOK = getOrdenesProduccionT(parametros).get(0);

            if (listaDetalleOPT != null) {
                em.flush();
                int nroOrdenProduccion = ordenesProduccion.getIdOrdenProduccion();
                for (DetOrdenesProduccionT item : listaDetalleOPT) {
                    item.getDetordenesproduccionPK().setIdOrdenProduccion(nroOrdenProduccion);
                    item.setOrdenesproduccion(ordenProduccionOK);
                    Detordenesproduccion detalle = (Detordenesproduccion) DozerUtil.getDozerMapper(false).map(item, Detordenesproduccion.class);
                    em.persist(detalle);
                    em.flush();
                }
            }
            return ordenProduccionOK;
        }
    }
    public List<OrdenesProduccionT> getOrdenesProduccionT(HashMap parametros) {
        List<Ordenesproduccion> opList = getOrdenesProduccion(parametros);
        List<OrdenesProduccionT> opTList = new ArrayList();
        for (Ordenesproduccion c : opList) {
            OrdenesProduccionT rdo = (OrdenesProduccionT) DozerUtil.getDozerMapper(false).map(c, OrdenesProduccionT.class);
            opTList.add(rdo);
        }
        return opTList;
    }

    public List<Ordenesproduccion> getOrdenesProduccion(HashMap parametros) {
        Criteria opCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Ordenesproduccion.class);
        List<Ordenesproduccion> opList;
        if (parametros.containsKey("pIdOrdenProduccion")) {
            opCritearia.add(Restrictions.eq("idOrdenProduccion", parametros.get("pIdOrdenProduccion")));
        }
        if (parametros.containsKey("pJoinDetalleOrdenProduccion")) {
            opCritearia.setFetchMode("detordenesproduccionCollection", FetchMode.JOIN);
        }
        opList = opCritearia.list();
        return opList;
    }

    public int getNextOrdenProduccion() {
        String hql = "select max(op.nroOrdenProduccion) from Ordenesproduccion op";
        Integer maxID = (Integer) ((EntityManagerImpl) em.getDelegate()).getSession().createQuery(hql).uniqueResult();
        if (maxID != null) {
            maxID++;
        } else {
            return 1;
        }
        if (maxID < 0) {
            return 1;
        }
        return maxID;
    }
}
