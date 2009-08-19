/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Maquinas;
import ar.com.jpack.transferencia.MaquinasT;
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
public class MaquinasFacade implements MaquinasFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<MaquinasT> getMaquinasT(HashMap parametros) {
        List<Maquinas> maquinasList = getMaquinas(parametros);
        List<MaquinasT> maquinasTList = new ArrayList();
        for (Maquinas c : maquinasList) {
            MaquinasT rdo = (MaquinasT) DozerUtil.getDozerMapper(false).map(c, MaquinasT.class);
            maquinasTList.add(rdo);
        }
        return maquinasTList;
    }

    public List<Maquinas> getMaquinas(HashMap parametros) {
        Criteria maquinaCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Maquinas.class);

        List<Maquinas> maquinasList;

        if (parametros.containsKey("pIdMaquina")) {
            maquinaCriteria.add(Restrictions.eq("idMaquina", parametros.get("pIdMaquinas")));
        }

        maquinasList = maquinaCriteria.list();

        return maquinasList;
    }
}
