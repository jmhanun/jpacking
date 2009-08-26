/*
 * ClientesFacade.java
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Clientes;
import ar.com.jpack.transferencia.ClientesT;
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

    /**
     * Obtiene la lista de Clientes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdCliente</b>    filtra por 'eq' idCliente (Integer) <br>
     * <b>pNombres</b>      filtra por 'like AnyWhere' nombres (String) <br>
     * <b>pCuit</b>         filtra por 'like AnyWhere' cuit (String) <br>
     * @return devuelve la lista de los Clientes que cumplan con el filtro
     */
    public List<ClientesT> getClientesT(HashMap parametros) {
        List<Clientes> clientesList = getClientes(parametros);
        List<ClientesT> clientesTList = new ArrayList();
        for (Clientes c : clientesList) {
            ClientesT rdo = (ClientesT) DozerUtil.getDozerMapper(false).map(c, ClientesT.class);
            clientesTList.add(rdo);
        }
        return clientesTList;
    }

    /**
     * Obtiene la lista de Clientes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdCliente</b>    filtra por 'eq' idCliente (Integer) <br>
     * <b>pNombres</b>      filtra por 'like AnyWhere' nombres (String) <br>
     * <b>pCuit</b>         filtra por 'like AnyWhere' cuit (String) <br>
     * @return devuelve la lista de los Clientes que cumplan con el filtro
     */
    public List<Clientes> getClientes(HashMap parametros) {
        Criteria clienteCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Clientes.class);
        List<Clientes> clientesList;
        if (parametros.containsKey("pIdCliente")) {
            clienteCritearia.add(Restrictions.eq("idCliente", parametros.get("pIdCliente")));
        }
        if (parametros.containsKey("pNombres")) {
            clienteCritearia.add(Restrictions.like("nombres", parametros.get("pNombres").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pCuit")) {
            clienteCritearia.add(Restrictions.like("cuit", parametros.get("pCuit").toString(),MatchMode.ANYWHERE));
        }
//       clienteCritearia.setFetchMode("idEstado", FetchMode.JOIN);
       /*Criteria estadoCriteria=clienteCritearia.createCriteria("idEstado");
        estadoCriteria.setFetchMode("FacturaCollecoin", FetchMode.JOIN);
        estadoCriteria.add(Restrictions.like("nombreEstado", "ACTIVO"));
         */
        clientesList = clienteCritearia.list();
        return clientesList;
    }
}
