/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Unidadesmedida;
import ar.com.jpack.transferencia.UnidadesMedidaT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
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
 * @author jmhanun
 */
@Stateless
public class UnidadesmedidaFacade implements UnidadesmedidaFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(Unidadesmedida unidadesmedida) {
        em.persist(unidadesmedida);
    }

    public void edit(Unidadesmedida unidadesmedida) {
        em.merge(unidadesmedida);
    }

    public void remove(Unidadesmedida unidadesmedida) {
        em.remove(em.merge(unidadesmedida));
    }

    public Unidadesmedida find(Object id) {
        return em.find(ar.com.jpack.persistencia.Unidadesmedida.class, id);
    }

    public List<Unidadesmedida> findAll() {
        return em.createQuery("select object(o) from Unidadesmedida as o").getResultList();
    }

    public List<UnidadesMedidaT> findAllUnidadesMedidaT() {
        List<Unidadesmedida> unidadesMedida = findAll();
        return DataTransferHelper.copiarUnidadesMedidaALista(unidadesMedida);
    }

    public List<UnidadesMedidaT> getUnidadesMedidaT(HashMap parametros) {

        List<Unidadesmedida> unidadesMedidaList = getUnidadesMedida(parametros);
        List<UnidadesMedidaT> unidadesMedidaTList = new ArrayList();
        for (Unidadesmedida c : unidadesMedidaList) {
            UnidadesMedidaT rdo = (UnidadesMedidaT) DozerUtil.getDozerMapper(false).map(c, UnidadesMedidaT.class);
            unidadesMedidaTList.add(rdo);
        }
        return unidadesMedidaTList;
    }

    private List<Unidadesmedida> getUnidadesMedida(HashMap parametros) {

        Criteria unidadesMedidaCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Unidadesmedida.class);
        List<Unidadesmedida> unidadesMedidaList;
        if (parametros.containsKey("pIdUnidMedida")) {
            unidadesMedidaCritearia.add(Restrictions.eq("idUnidMedida", parametros.get("pIdUnidMedida")));
        }
        if (parametros.containsKey("pDescripcion")) {
            unidadesMedidaCritearia.add(Restrictions.like("descripcion", parametros.get("pDescripcion").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pAbreviatura")) {
            unidadesMedidaCritearia.add(Restrictions.like("abreviatura", parametros.get("pAbreviatura").toString(), MatchMode.ANYWHERE));
        }
        unidadesMedidaList = unidadesMedidaCritearia.list();
        return unidadesMedidaList;
    }
}


