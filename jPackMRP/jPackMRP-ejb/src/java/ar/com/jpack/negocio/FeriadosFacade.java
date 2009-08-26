/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Feriados;
import ar.com.jpack.transferencia.FeriadosT;
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
public class FeriadosFacade implements FeriadosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public List<FeriadosT> getFeriadosT(HashMap parametros) {

        List<Feriados> feriadosList = getFeriados(parametros);
        List<FeriadosT> feriadosTList = new ArrayList();
        for (Feriados c : feriadosList) {
            FeriadosT rdo = (FeriadosT) DozerUtil.getDozerMapper(false).map(c, FeriadosT.class);
            feriadosTList.add(rdo);
        }
        return feriadosTList;
    }

    public List<Feriados> getFeriados(HashMap parametros) {

        Criteria feriadosCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Feriados.class);
        List<Feriados> feriadosList;
        if (parametros.containsKey("pIdFeriado")) {
            feriadosCriteria.add(Restrictions.eq("idFeriado", parametros.get("pIdFeriado")));
        }
        if (parametros.containsKey("pFecha")) {
            feriadosCriteria.add(Restrictions.like("fecha", parametros.get("pFecha").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pMotivo")) {
            feriadosCriteria.add(Restrictions.like("motivo", parametros.get("pMotivo").toString(), MatchMode.ANYWHERE));
        }
        feriadosList = feriadosCriteria.list();
        return feriadosList;
    }

}
