/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Clientes;
import ar.com.jpack.transferencia.ClientesT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ClientesFacadeRemote {

    public Integer deleteClienteT(Integer idCliente);

    /**
     * Obtiene la lista de Clientes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdCliente</b>    filtra por 'eq' idCliente (Integer) <br>
     * <b>pNombres</b>      filtra por 'like AnyWhere' nombres (String) <br>
     * <b>pCuit</b>         filtra por 'like AnyWhere' cuit (String) <br>
     * @return devuelve la lista de los Clientes que cumplan con el filtro
     */
    public List<ClientesT> getClientesT(HashMap parametros);

    /**
     * Obtiene la lista de Clientes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdCliente</b>    filtra por 'eq' idCliente (Integer) <br>
     * <b>pNombres</b>      filtra por 'like AnyWhere' nombres (String) <br>
     * <b>pCuit</b>         filtra por 'like AnyWhere' cuit (String) <br>
     * @return devuelve la lista de los Clientes que cumplan con el filtro
     */
    public List<Clientes> getClientes(HashMap parametros);

    public ClientesT updateClientesT(ClientesT dto);
}
