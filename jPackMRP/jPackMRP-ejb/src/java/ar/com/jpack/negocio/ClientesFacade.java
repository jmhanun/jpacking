/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Clientes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ClientesFacade implements ClientesFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Clientes clientes) {
        em.persist(clientes);
    }

    public void edit(Clientes clientes) {
        em.merge(clientes);
    }

    public void remove(Clientes clientes) {
        em.remove(em.merge(clientes));
    }

    public Clientes find(Object id) {
        return em.find(ar.com.jpack.persistencia.Clientes.class, id);
    }

    public List<Clientes> findAll() {
        return em.createQuery("select object(o) from Clientes as o").getResultList();
    }

}
