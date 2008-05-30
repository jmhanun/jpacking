/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Unidadesmedida;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
