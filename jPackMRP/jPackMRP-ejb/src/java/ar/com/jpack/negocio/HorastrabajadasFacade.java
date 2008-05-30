/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Horastrabajadas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class HorastrabajadasFacade implements HorastrabajadasFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Horastrabajadas horastrabajadas) {
        em.persist(horastrabajadas);
    }

    public void edit(Horastrabajadas horastrabajadas) {
        em.merge(horastrabajadas);
    }

    public void remove(Horastrabajadas horastrabajadas) {
        em.remove(em.merge(horastrabajadas));
    }

    public Horastrabajadas find(Object id) {
        return em.find(ar.com.jpack.persistencia.Horastrabajadas.class, id);
    }

    public List<Horastrabajadas> findAll() {
        return em.createQuery("select object(o) from Horastrabajadas as o").getResultList();
    }

}
