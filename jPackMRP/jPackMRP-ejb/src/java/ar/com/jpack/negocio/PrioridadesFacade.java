/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Prioridades;
import ar.com.jpack.transferencia.PrioridadesT;
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
public class PrioridadesFacade implements PrioridadesFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<PrioridadesT> getPrioridadesT(HashMap parametros) {
        List<Prioridades> prioridadesList = getPrioridades(parametros);
        List<PrioridadesT> prioridadesTList = new ArrayList<PrioridadesT>();

        for (Prioridades c : prioridadesList) {
            PrioridadesT rdo = (PrioridadesT) DozerUtil.getDozerMapper(false).map(c, PrioridadesT.class);
            prioridadesTList.add(rdo);
        }
        return prioridadesTList;
    }

    public List<Prioridades> getPrioridades(HashMap parametros) {
        Criteria prioridadesCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Prioridades.class);
        List<Prioridades> prioridadesList;
        if (parametros.containsKey("pIdPrioridad")) {
            prioridadesCritearia.add(Restrictions.eq("idPrioridad", parametros.get("pIdPrioridad")));
        }

        prioridadesList = prioridadesCritearia.list();
        return prioridadesList;
    }
}
