/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Proveedores;
import ar.com.jpack.transferencia.ProveedoresT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface ProveedoresFacadeRemote {

    /**
     * Obtiene la lista de Proveedores filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdProveedor</b>  filtra por 'eq' idProveedor (Integer) <br>
     * <b>pNombres</b>    filtra por 'like' nombres (String) <br>
     * <b>pCuit</b>    filtra por 'like' cuit (String) <br>
     * @return devuelve la lista de los Proveedores que cumplan con el filtro
     */
    public List<ProveedoresT> getProveedoresT(HashMap parametros);

    /**
     * Obtiene la lista de Proveedores filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdProveedor</b>  filtra por 'eq' idProveedor (Integer) <br>
     * <b>pNombres</b>    filtra por 'like' nombres (String) <br>
     * <b>pCuit</b>    filtra por 'like' cuit (String) <br>
     * @return devuelve la lista de los Proveedores que cumplan con el filtro
     */
    public List<Proveedores> getProveedores(HashMap parametros);
}
