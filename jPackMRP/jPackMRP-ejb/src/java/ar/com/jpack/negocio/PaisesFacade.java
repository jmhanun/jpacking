/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Paises;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class PaisesFacade implements PaisesFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Paises paises) {
        em.persist(paises);
    }

    public void edit(Paises paises) {
        em.merge(paises);
    }

    public void remove(Paises paises) {
        em.remove(em.merge(paises));
    }

    public Paises find(Object id) {
        return em.find(ar.com.jpack.persistencia.Paises.class, id);
    }

    public List<Paises> findAll() {
        return em.createQuery("select object(o) from Paises as o").getResultList();
    }

}
