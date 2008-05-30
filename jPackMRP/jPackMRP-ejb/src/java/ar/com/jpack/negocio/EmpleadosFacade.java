/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Empleados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class EmpleadosFacade implements EmpleadosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Empleados empleados) {
        em.persist(empleados);
    }

    public void edit(Empleados empleados) {
        em.merge(empleados);
    }

    public void remove(Empleados empleados) {
        em.remove(em.merge(empleados));
    }

    public Empleados find(Object id) {
        return em.find(ar.com.jpack.persistencia.Empleados.class, id);
    }

    public List<Empleados> findAll() {
        return em.createQuery("select object(o) from Empleados as o").getResultList();
    }

}
