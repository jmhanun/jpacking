/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Stock;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class StockFacade implements StockFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Stock stock) {
        em.persist(stock);
    }

    public void edit(Stock stock) {
        em.merge(stock);
    }

    public void remove(Stock stock) {
        em.remove(em.merge(stock));
    }

    public Stock find(Object id) {
        return em.find(ar.com.jpack.persistencia.Stock.class, id);
    }

    public List<Stock> findAll() {
        return em.createQuery("select object(o) from Stock as o").getResultList();
    }

}
