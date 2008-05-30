/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Provincias;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ProvinciasFacade implements ProvinciasFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Provincias provincias) {
        em.persist(provincias);
    }

    public void edit(Provincias provincias) {
        em.merge(provincias);
    }

    public void remove(Provincias provincias) {
        em.remove(em.merge(provincias));
    }

    public Provincias find(Object id) {
        return em.find(ar.com.jpack.persistencia.Provincias.class, id);
    }

    public List<Provincias> findAll() {
        return em.createQuery("select object(o) from Provincias as o").getResultList();
    }

}
