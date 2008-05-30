/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Movimientosstock;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class MovimientosstockFacade implements MovimientosstockFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Movimientosstock movimientosstock) {
        em.persist(movimientosstock);
    }

    public void edit(Movimientosstock movimientosstock) {
        em.merge(movimientosstock);
    }

    public void remove(Movimientosstock movimientosstock) {
        em.remove(em.merge(movimientosstock));
    }

    public Movimientosstock find(Object id) {
        return em.find(ar.com.jpack.persistencia.Movimientosstock.class, id);
    }

    public List<Movimientosstock> findAll() {
        return em.createQuery("select object(o) from Movimientosstock as o").getResultList();
    }

}
