/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Remitos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class RemitosFacade implements RemitosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Remitos remitos) {
        em.persist(remitos);
    }

    public void edit(Remitos remitos) {
        em.merge(remitos);
    }

    public void remove(Remitos remitos) {
        em.remove(em.merge(remitos));
    }

    public Remitos find(Object id) {
        return em.find(ar.com.jpack.persistencia.Remitos.class, id);
    }

    public List<Remitos> findAll() {
        return em.createQuery("select object(o) from Remitos as o").getResultList();
    }

}
