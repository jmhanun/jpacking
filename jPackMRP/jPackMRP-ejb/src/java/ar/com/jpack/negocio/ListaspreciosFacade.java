/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Listasprecios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ListaspreciosFacade implements ListaspreciosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Listasprecios listasprecios) {
        em.persist(listasprecios);
    }

    public void edit(Listasprecios listasprecios) {
        em.merge(listasprecios);
    }

    public void remove(Listasprecios listasprecios) {
        em.remove(em.merge(listasprecios));
    }

    public Listasprecios find(Object id) {
        return em.find(ar.com.jpack.persistencia.Listasprecios.class, id);
    }

    public List<Listasprecios> findAll() {
        return em.createQuery("select object(o) from Listasprecios as o").getResultList();
    }

}
