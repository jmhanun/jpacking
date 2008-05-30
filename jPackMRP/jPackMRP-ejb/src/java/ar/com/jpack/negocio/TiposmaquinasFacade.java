/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposmaquinas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class TiposmaquinasFacade implements TiposmaquinasFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Tiposmaquinas tiposmaquinas) {
        em.persist(tiposmaquinas);
    }

    public void edit(Tiposmaquinas tiposmaquinas) {
        em.merge(tiposmaquinas);
    }

    public void remove(Tiposmaquinas tiposmaquinas) {
        em.remove(em.merge(tiposmaquinas));
    }

    public Tiposmaquinas find(Object id) {
        return em.find(ar.com.jpack.persistencia.Tiposmaquinas.class, id);
    }

    public List<Tiposmaquinas> findAll() {
        return em.createQuery("select object(o) from Tiposmaquinas as o").getResultList();
    }

}
