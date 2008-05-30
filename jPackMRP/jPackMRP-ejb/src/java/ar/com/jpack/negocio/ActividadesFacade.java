/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Actividades;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ActividadesFacade implements ActividadesFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Actividades actividades) {
        em.persist(actividades);
    }

    public void edit(Actividades actividades) {
        em.merge(actividades);
    }

    public void remove(Actividades actividades) {
        em.remove(em.merge(actividades));
    }

    public Actividades find(Object id) {
        return em.find(ar.com.jpack.persistencia.Actividades.class, id);
    }

    public List<Actividades> findAll() {
        return em.createQuery("select object(o) from Actividades as o").getResultList();
    }

}
