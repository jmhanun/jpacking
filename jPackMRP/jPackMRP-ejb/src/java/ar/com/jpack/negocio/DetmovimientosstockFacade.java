/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detmovimientosstock;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class DetmovimientosstockFacade implements DetmovimientosstockFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Detmovimientosstock detmovimientosstock) {
        em.persist(detmovimientosstock);
    }

    public void edit(Detmovimientosstock detmovimientosstock) {
        em.merge(detmovimientosstock);
    }

    public void remove(Detmovimientosstock detmovimientosstock) {
        em.remove(em.merge(detmovimientosstock));
    }

    public Detmovimientosstock find(Object id) {
        return em.find(ar.com.jpack.persistencia.Detmovimientosstock.class, id);
    }

    public List<Detmovimientosstock> findAll() {
        return em.createQuery("select object(o) from Detmovimientosstock as o").getResultList();
    }

}
