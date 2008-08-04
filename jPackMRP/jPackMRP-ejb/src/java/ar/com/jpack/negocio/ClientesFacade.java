/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Clientes;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public List<ClientesT> findAllClientesT() {
        List<Clientes> clientes = findAll();
        return DataTransferHelper.copiarClientesALista(clientes);
    }

    public List<ClientesT> findClientesT(HashMap parametros) {
        String sql = "SELECT c FROM Clientes c";
        boolean condicion = false;
        if (parametros.containsKey("pIdCliente")) {
            sql += " WHERE c.idCliente = :idCliente";
            condicion = true;
        }
        if (parametros.containsKey("pNombres")) {
            if (condicion) {
                sql += " AND c.nombres like :nombres";
            } else {
                sql += " WHERE c.nombres like :nombres";
                condicion = true;
            }
        }
        if (parametros.containsKey("pCuit")) {
            if (condicion) {
                sql += " AND c.cuit like :cuit";
            } else {
                sql += " WHERE c.cuit like :cuit";
                condicion = true;
            }
        }
        Query query = em.createQuery(sql);
        
        if (parametros.containsKey("pIdCliente")) {
            query.setParameter("idCliente", parametros.get("pIdCliente"));
        }
        if (parametros.containsKey("pNombres")) {
            query.setParameter("nombres", parametros.get("pNombres"));
        }
        if (parametros.containsKey("pCuit")) {
            query.setParameter("cuit", parametros.get("pCuit"));            
        }

        return DataTransferHelper.copiarClientesALista(query.getResultList());
    }
}
