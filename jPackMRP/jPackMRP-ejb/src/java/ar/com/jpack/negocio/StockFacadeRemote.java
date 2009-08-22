/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Stock;
import ar.com.jpack.transferencia.StockT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface StockFacadeRemote {

    /**
     * Obtiene la lista de Stock filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdStock</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pJoinArticulos</b>  obliga a Joinear con Articulos<br>
     * @return devuelve la lista de los Mails que cumplan con el filtro
     */
    public List<StockT> getStockT(HashMap parametros);

    /**
     * Obtiene la lista de Stock filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdStock</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pJoinArticulos</b>  obliga a Joinear con Articulos<br>
     * @return devuelve la lista de los Mails que cumplan con el filtro
     */
    public List<Stock> getStock(HashMap parametros);
}
