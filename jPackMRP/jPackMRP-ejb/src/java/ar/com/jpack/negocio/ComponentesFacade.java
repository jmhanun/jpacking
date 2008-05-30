/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Componentes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ComponentesFacade implements ComponentesFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Componentes componentes) {
        em.persist(componentes);
    }

    public void edit(Componentes componentes) {
        em.merge(componentes);
    }

    public void remove(Componentes componentes) {
        em.remove(em.merge(componentes));
    }

    public Componentes find(Object id) {
        return em.find(ar.com.jpack.persistencia.Componentes.class, id);
    }

    public List<Componentes> findAll() {
        return em.createQuery("select object(o) from Componentes as o").getResultList();
    }

}
