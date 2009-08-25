/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposdesvios;
import ar.com.jpack.transferencia.TiposDesviosT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author Pablo
 */
@Stateless
public class TiposdesviosFacade implements TiposdesviosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public List<TiposDesviosT> getTiposDesviosT(HashMap parametros) {

        List<Tiposdesvios> tiposDesviosList = getTiposDesvios(parametros);
        List<TiposDesviosT> tiposDesviosTList = new ArrayList();
        for (Tiposdesvios c : tiposDesviosList) {
            TiposDesviosT rdo = (TiposDesviosT) DozerUtil.getDozerMapper(false).map(c, TiposDesviosT.class);
            tiposDesviosTList.add(rdo);
        }
        return tiposDesviosTList;
    }

    public List<Tiposdesvios> getTiposDesvios(HashMap parametros) {

        Criteria tiposDesviosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Tiposdesvios.class);
        List<Tiposdesvios> tiposDesviosList;
        if (parametros.containsKey("pIdTipoDesvio")) {
            tiposDesviosCritearia.add(Restrictions.eq("idTipoDesvio", parametros.get("pIdTipoDesvio")));
        }
        if (parametros.containsKey("pMotivo")) {
            tiposDesviosCritearia.add(Restrictions.like("motivo", parametros.get("pMotivo").toString(), MatchMode.ANYWHERE));
        }
        tiposDesviosList = tiposDesviosCritearia.list();
        return tiposDesviosList;
    }

}
