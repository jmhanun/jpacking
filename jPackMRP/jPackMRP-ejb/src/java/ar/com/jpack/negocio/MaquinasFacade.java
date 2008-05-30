/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Maquinas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class MaquinasFacade implements MaquinasFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Maquinas maquinas) {
        em.persist(maquinas);
    }

    public void edit(Maquinas maquinas) {
        em.merge(maquinas);
    }

    public void remove(Maquinas maquinas) {
        em.remove(em.merge(maquinas));
    }

    public Maquinas find(Object id) {
        return em.find(ar.com.jpack.persistencia.Maquinas.class, id);
    }

    public List<Maquinas> findAll() {
        return em.createQuery("select object(o) from Maquinas as o").getResultList();
    }

}
