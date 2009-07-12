/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Remitos;
import ar.com.jpack.transferencia.DetalleRemitosT;
import ar.com.jpack.transferencia.RemitosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface RemitosFacadeRemote {

    /**
     * Obtiene el siguiente numero de remito
     * @return devuelve el siguiente numero de remito como int
     */
    public int getNextRemito();

    /**
     * Actualiza o crea un remitoT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     * 
     * @param remitosT contiene los datos del remito a actualizar
     * @return devuelve el remitoT actualizado
     */
    public RemitosT updateRemitosT(RemitosT remitosT);

    /**
     * Obtiene la lista de Remitos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRemito</b>           filtra por 'eq' idRemito (Integer) <br>
     * <b>pJoinDetalleRemitos</b> obliga a Joinear con DetalleRemitos<br>
     * @return devuelve la lista de los Remitos que cumplan con el filtro
     */
    public List<RemitosT> getRemitosT(HashMap parametros);

    /**
     * Obtiene la lista de Remitos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRemito</b>           filtra por 'eq' idRemito (Integer) <br>
     * <b>pJoinDetalleRemitos</b> obliga a Joinear con DetalleRemitos<br>
     * @return devuelve la lista de los Remitos que cumplan con el filtro
     */
    public List<Remitos> getRemitos(HashMap parametros);

    /**
     * Actualiza o crea un remitoT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     * 
     * @param remitosT contiene los datos del remito a actualizar
     * @param detallesRemitosT contiene la lista de los detalles del remito a actualizar solo si es nuevo
     * @return devuelve el remitoT actualizado
     */
    public RemitosT updateRemitosT(RemitosT remitosT, List<DetalleRemitosT> detallesRemitosT);

    /**
     * Obtiene el siguiente numero de instancia del detalleRemtioTemp
     * @return devuelve el siguiente numero de instancia del detalleRemtioTemp como int
     */
    public int getNextInstancia();
}
