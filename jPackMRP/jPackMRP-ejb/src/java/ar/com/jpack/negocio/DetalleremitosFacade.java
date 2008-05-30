/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleremitos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class DetalleremitosFacade implements DetalleremitosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Detalleremitos detalleremitos) {
        em.persist(detalleremitos);
    }

    public void edit(Detalleremitos detalleremitos) {
        em.merge(detalleremitos);
    }

    public void remove(Detalleremitos detalleremitos) {
        em.remove(em.merge(detalleremitos));
    }

    public Detalleremitos find(Object id) {
        return em.find(ar.com.jpack.persistencia.Detalleremitos.class, id);
    }

    public List<Detalleremitos> findAll() {
        return em.createQuery("select object(o) from Detalleremitos as o").getResultList();
    }

}
