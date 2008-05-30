/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detallefacturas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class DetallefacturasFacade implements DetallefacturasFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Detallefacturas detallefacturas) {
        em.persist(detallefacturas);
    }

    public void edit(Detallefacturas detallefacturas) {
        em.merge(detallefacturas);
    }

    public void remove(Detallefacturas detallefacturas) {
        em.remove(em.merge(detallefacturas));
    }

    public Detallefacturas find(Object id) {
        return em.find(ar.com.jpack.persistencia.Detallefacturas.class, id);
    }

    public List<Detallefacturas> findAll() {
        return em.createQuery("select object(o) from Detallefacturas as o").getResultList();
    }

}
