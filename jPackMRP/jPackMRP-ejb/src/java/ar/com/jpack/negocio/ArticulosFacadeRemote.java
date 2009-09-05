/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Articulos;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.ComponentesT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ArticulosFacadeRemote {

    /**
     * Obtiene la lista de Articulos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdArticulos</b>   filtra por 'eq' idArticulo (Integer) <br>
     * <b>pCodigo</b>        filtra por 'like AnyWhere' codigo (String) <br>
     * <b>pDescripcion</b>   filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pFinal</b>   filtra por 'eq' articuloFinal (String) <br>
     * <b>pImprimible</b>   filtra por 'eq' imprimible (String) <br>
     * @return devuelve la lista de los Articulos que cumplan con el filtro <br>
     */
    public List<ArticulosT> getArticulosT(HashMap parametros);

    /**
     * Obtiene la lista de Articulos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdArticulos</b>   filtra por 'eq' idArticulo (Integer) <br>
     * <b>pCodigo</b>        filtra por 'like AnyWhere' codigo (String) <br>
     * <b>pDescripcion</b>   filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pFinal</b>   filtra por 'eq' articuloFinal (String) <br>
     * <b>pImprimible</b>   filtra por 'eq' imprimible (String) <br>
     * @return devuelve la lista de los Articulos que cumplan con el filtro <br>
     */
    public List<Articulos> getArticulos(HashMap parametros);

    public List<ComponentesT> getComponentesT(HashMap parametros);

    /**
     * Obtiene el precio vigente de un Articulo
     * @param ArticuloT del que se desea conocer el precio
     * @return devuelve el precio como double
     */
    public double getPrecioArticuloVigente(ArticulosT articulosT);

    /**
     * Obtiene el stock de un Articulo
     * @param ArticuloT del que se desea conocer el stock
     * @return devuelve la cantidad de stock como double
     */
    public double getStockArticulo(ArticulosT articulosT);

    public ArticulosT updateArticulosT(ArticulosT dto);

    public void updateComponentesT(ArrayList<ComponentesT> listDto);

}
