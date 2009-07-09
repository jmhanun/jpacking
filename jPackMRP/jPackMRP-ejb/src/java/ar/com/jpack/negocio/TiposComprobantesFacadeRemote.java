/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposcomprobantes;
import ar.com.jpack.transferencia.TiposComprobantesT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposComprobantesFacadeRemote {

    /**
     * Obtiene la lista de Tipos de comprobantes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTipoComprobante</b>  filtra por 'eq' idTipoComprobante (Integer) <br>
     * @return devuelve la lista de los Tipos de comprabantes que cumplan con el filtro
     */
    public List<TiposComprobantesT> getTiposComprobantesT(HashMap parametros);

    /**
     * Obtiene la lista de Tipos de comprobantes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTipoComprobante</b>  filtra por 'eq' idTipoComprobante (Integer) <br>
     * @return devuelve la lista de los Tipos de comprabantes que cumplan con el filtro
     */
    public List<Tiposcomprobantes> getTiposComprobantes(HashMap parametros);

}
