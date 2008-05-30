/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Articulos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ArticulosFacade implements ArticulosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Articulos articulos) {
        em.persist(articulos);
    }

    public void edit(Articulos articulos) {
        em.merge(articulos);
    }

    public void remove(Articulos articulos) {
        em.remove(em.merge(articulos));
    }

    public Articulos find(Object id) {
        return em.find(ar.com.jpack.persistencia.Articulos.class, id);
    }

    public List<Articulos> findAll() {
        return em.createQuery("select object(o) from Articulos as o").getResultList();
    }

}
