/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleproduccion;
import ar.com.jpack.transferencia.DetalleProduccionT;
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
 * @author Pablo
 */
@Stateless
public class DetalleproduccionFacade implements DetalleproduccionFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<DetalleProduccionT> getDetalleProduccionT(HashMap parametros) {
        List<Detalleproduccion> detallesList = getDetalleProduccion(parametros);
        List<DetalleProduccionT> detallesTList = new ArrayList();
        for (Detalleproduccion c : detallesList) {
            DetalleProduccionT rdo = (DetalleProduccionT) DozerUtil.getDozerMapper(false).map(c, DetalleProduccionT.class);
            detallesTList.add(rdo);
        }
        return detallesTList;
    }

    public List<Detalleproduccion> getDetalleProduccion(HashMap parametros) {
        Criteria detalleProduccionCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Detalleproduccion.class);







        List<Detalleproduccion> detallesList;

        if(parametros.containsKey("pIdDetalleProduccion")){
            detalleProduccionCriteria.add(Restrictions.eq("idDetalleProduccion", parametros.get("pIdDetalleProduccion")));
        }


        if (parametros.containsKey("pJoinMaquinas")) {
            detalleProduccionCriteria.setFetchMode("idMaquina", FetchMode.JOIN);
        }

        detallesList = detalleProduccionCriteria.list();

        return detallesList;
    }
}
