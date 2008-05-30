/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Facturas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class FacturasFacade implements FacturasFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Facturas facturas) {
        em.persist(facturas);
    }

    public void edit(Facturas facturas) {
        em.merge(facturas);
    }

    public void remove(Facturas facturas) {
        em.remove(em.merge(facturas));
    }

    public Facturas find(Object id) {
        return em.find(ar.com.jpack.persistencia.Facturas.class, id);
    }

    public List<Facturas> findAll() {
        return em.createQuery("select object(o) from Facturas as o").getResultList();
    }

}
