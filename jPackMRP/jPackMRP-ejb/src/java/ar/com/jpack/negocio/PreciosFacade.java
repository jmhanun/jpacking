/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Precios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class PreciosFacade implements PreciosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Precios precios) {
        em.persist(precios);
    }

    public void edit(Precios precios) {
        em.merge(precios);
    }

    public void remove(Precios precios) {
        em.remove(em.merge(precios));
    }

    public Precios find(Object id) {
        return em.find(ar.com.jpack.persistencia.Precios.class, id);
    }

    public List<Precios> findAll() {
        return em.createQuery("select object(o) from Precios as o").getResultList();
    }

}
