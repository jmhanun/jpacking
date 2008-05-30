/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Domicilios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class DomiciliosFacade implements DomiciliosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Domicilios domicilios) {
        em.persist(domicilios);
    }

    public void edit(Domicilios domicilios) {
        em.merge(domicilios);
    }

    public void remove(Domicilios domicilios) {
        em.remove(em.merge(domicilios));
    }

    public Domicilios find(Object id) {
        return em.find(ar.com.jpack.persistencia.Domicilios.class, id);
    }

    public List<Domicilios> findAll() {
        return em.createQuery("select object(o) from Domicilios as o").getResultList();
    }

}
