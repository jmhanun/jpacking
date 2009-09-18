/*
 * ClientesFacade.java
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Clientes;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.util.DozerUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.Date;
import org.hibernate.FetchMode;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ClientesFacade implements ClientesFacadeRemote {
    @EJB
    private TiposDocumentoFacadeRemote tiposDocumentoFacade;
    @EJB
    private TiposIvaFacadeRemote tiposIvaFacade;
    @EJB
    private EstadosFacadeRemote estadosFacade;

    @PersistenceContext
    private EntityManager em;
    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;

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
            clienteCritearia.add(Restrictions.like("cuit", parametros.get("pCuit").toString(), MatchMode.ANYWHERE));
        }
        clienteCritearia.setFetchMode("idEstado", FetchMode.JOIN);
        Criteria estadoCriteria = clienteCritearia.createCriteria("idEstado");
        estadoCriteria.add(Restrictions.eq("idEstado", 10));

        clientesList = clienteCritearia.list();
        return clientesList;
    }

    public Integer deleteClienteT(Integer idCliente) {
        Integer resultado = null;
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();

            CallableStatement cs = conn.prepareCall("{call spabmclientes(?, ?)}");

            //set inputs
            cs.setInt(1, idCliente);
            //set outputs
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            // execute
            cs.executeQuery();
            // display returned values
            resultado = new Integer(cs.getInt(2));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeriadosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public ClientesT updateClientesT(ClientesT dto) {
        Clientes clientes = (Clientes) DozerUtil.getDozerMapper(false).map(dto, Clientes.class);

        //si el numero de id es null significa que es nuevo
        if (clientes.getIdCliente() != null) {
            HashMap parametros = new HashMap();
            parametros.put("pIdEstados", 10);
            clientes.setIdEstado(estadosFacade.getEstados(parametros).get(0));
            
            parametros = new HashMap();
            parametros.put("pIdTipoDocumento", 2);
            clientes.setIdTipoDocumento(tiposDocumentoFacade.getTiposDocumento(parametros).get(0));
            
            parametros = new HashMap();
            parametros.put("pIdTipoIva", 1);
            clientes.setIdTipoIva(tiposIvaFacade.getTiposIva(parametros).get(0));

            em.merge(clientes);
        } else {
            clientes.setFechaAlta(new Date());
            HashMap parametros = new HashMap();
            parametros.put("pIdEstados", 10);
            clientes.setIdEstado(estadosFacade.getEstados(parametros).get(0));
            
            parametros = new HashMap();
            parametros.put("pIdTipoDocumento", 2);
            clientes.setIdTipoDocumento(tiposDocumentoFacade.getTiposDocumento(parametros).get(0));
            
            parametros = new HashMap();
            parametros.put("pIdTipoIva", 1);
            clientes.setIdTipoIva(tiposIvaFacade.getTiposIva(parametros).get(0));

            em.persist(clientes);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdCliente", clientes.getIdCliente());
        return getClientesT(parametros).get(0);

    }
}
