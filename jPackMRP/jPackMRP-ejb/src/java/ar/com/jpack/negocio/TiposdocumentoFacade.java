/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposdocumento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class TiposdocumentoFacade implements TiposdocumentoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Tiposdocumento tiposdocumento) {
        em.persist(tiposdocumento);
    }

    public void edit(Tiposdocumento tiposdocumento) {
        em.merge(tiposdocumento);
    }

    public void remove(Tiposdocumento tiposdocumento) {
        em.remove(em.merge(tiposdocumento));
    }

    public Tiposdocumento find(Object id) {
        return em.find(ar.com.jpack.persistencia.Tiposdocumento.class, id);
    }

    public List<Tiposdocumento> findAll() {
        return em.createQuery("select object(o) from Tiposdocumento as o").getResultList();
    }

}
