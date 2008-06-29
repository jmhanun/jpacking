/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Estados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class EstadosFacade implements EstadosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Estados estados) {
        em.persist(estados);
    }

    public void edit(Estados estados) {
        em.merge(estados);
    }

    public void remove(Estados estados) {
        em.remove(em.merge(estados));
    }

    public Estados find(Object id) {
        return em.find(ar.com.jpack.persistencia.Estados.class, id);
    }

    public List<Estados> findAll() {
        return em.createQuery("select object(o) from Estados as o").getResultList();
    }

}
