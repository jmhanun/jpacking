/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposmovstock;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class TiposmovstockFacade implements TiposmovstockFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Tiposmovstock tiposmovstock) {
        em.persist(tiposmovstock);
    }

    public void edit(Tiposmovstock tiposmovstock) {
        em.merge(tiposmovstock);
    }

    public void remove(Tiposmovstock tiposmovstock) {
        em.remove(em.merge(tiposmovstock));
    }

    public Tiposmovstock find(Object id) {
        return em.find(ar.com.jpack.persistencia.Tiposmovstock.class, id);
    }

    public List<Tiposmovstock> findAll() {
        return em.createQuery("select object(o) from Tiposmovstock as o").getResultList();
    }

}
