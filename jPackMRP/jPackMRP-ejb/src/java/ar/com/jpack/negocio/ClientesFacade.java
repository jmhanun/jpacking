/*
 * ClientesFacade.java
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Clientes;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

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
        Criteria clienteCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Clientes.class);
        List <Clientes> clientesList;
        List <ClientesT> clientesTList=new ArrayList();
        if (parametros.containsKey("pIdCliente")) {
            clienteCritearia.add(Restrictions.eq("idCliente", parametros.get("pIdCliente")));
        }
        if (parametros.containsKey("pNombres")) {
            
            clienteCritearia.add(Restrictions.like("nombres", parametros.get("pNombres").toString(),MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pCuit")) {
            clienteCritearia.add(Restrictions.eq("cuit", parametros.get("pCuit")));
        }
       clienteCritearia.setFetchMode("idEstado", FetchMode.JOIN);
       /*Criteria estadoCriteria=clienteCritearia.createCriteria("idEstado");
       estadoCriteria.setFetchMode("FacturaCollecoin", FetchMode.JOIN);
       estadoCriteria.add(Restrictions.like("nombreEstado", "ACTIVO"));
       */
       clientesList=clienteCritearia.list();
        for(Clientes c:clientesList){
            ClientesT rdo=(ClientesT)DozerUtil.getDozerMapper(false).map(c, ClientesT.class);
            clientesTList.add(rdo);
        }
        return clientesTList;
    }
}
