/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleordenescompras;
import ar.com.jpack.persistencia.Ordenescompra;
import ar.com.jpack.transferencia.DetalleOrdenesComprasT;
import ar.com.jpack.transferencia.OrdenesCompraT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author jmhanun
 */
@Stateless
public class OrdenescompraFacade implements OrdenescompraFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<OrdenesCompraT> getOrdenesCompraT(HashMap parametros) {
        List<Ordenescompra> ordenesCompraList = getOrdenescompra(parametros);
        List<OrdenesCompraT> ordenesCompraTList = new ArrayList();
        for (Ordenescompra c : ordenesCompraList) {
            OrdenesCompraT rdo = (OrdenesCompraT) DozerUtil.getDozerMapper(false).map(c, OrdenesCompraT.class);
            ordenesCompraTList.add(rdo);
        }
        return ordenesCompraTList;
    }

    public List<Ordenescompra> getOrdenescompra(HashMap parametros) {
        Criteria ordenesCompraCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Ordenescompra.class);

        List<Ordenescompra> ordenesCompraList;

        if (parametros.containsKey("pIdOrdenCompra")) {
            ordenesCompraCriteria.add(Restrictions.eq("idOrdenCompra", parametros.get("pIdOrdenCompra")));
        }
        ordenesCompraList = ordenesCompraCriteria.list();
        return ordenesCompraList;
    }

    public int getNextOrden() {
        String hql = "select max(o.nroOrdenCompra) from Ordenescompra o";
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

    public OrdenesCompraT updateOrdenCompraT(OrdenesCompraT orden, ArrayList<DetalleOrdenesComprasT> listDto) {
        HashMap parametros = new HashMap();
        Ordenescompra ordenCompra = (Ordenescompra) DozerUtil.getDozerMapper(false).map(orden, Ordenescompra.class);

        //si el numero de id es null significa que es nuevo
        if (ordenCompra.getIdOrdenCompra() != null) {
            em.merge(ordenCompra);
            parametros.put("pIdOrdenCompra", ordenCompra.getIdOrdenCompra());

            return getOrdenesCompraT(parametros).get(0);
        } else {
            ordenCompra.setNroOrdenCompra(getNextOrden());

            em.persist(ordenCompra);
            parametros.put("pIdOrdenCompra", ordenCompra.getIdOrdenCompra());

            OrdenesCompraT ordenOK = getOrdenesCompraT(parametros).get(0);

            if (listDto != null) {
                em.flush();
                int nroOrden = ordenCompra.getIdOrdenCompra();
                for (DetalleOrdenesComprasT item : listDto) {
                    item.getDetalleordenescomprasPK().setIdOrdenCompra(nroOrden);
                    item.setOrdenescompra(ordenOK);
                    Detalleordenescompras detalle = (Detalleordenescompras) DozerUtil.getDozerMapper(false).map(item, Detalleordenescompras.class);
                    em.persist(detalle);
                    em.flush();
                }
            }
            return ordenOK;
        }
    }
}
