/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Remitos;
import ar.com.jpack.persistencia.Remitosingreso;
import ar.com.jpack.transferencia.DetRtosIngresoT;
import ar.com.jpack.transferencia.DetalleRemitosT;
import ar.com.jpack.transferencia.DetalleRemitosTempT;
import ar.com.jpack.transferencia.RemitosIngresoT;
import ar.com.jpack.transferencia.RemitosT;
import java.util.ArrayList;
import java.util.Date;
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

    public RemitosIngresoT updateRemitosIngresosT(RemitosIngresoT remito, ArrayList<DetRtosIngresoT> listDto);

    public Date updateRemitosTempT(List<DetalleRemitosTempT> detalleRemitosTempT);

    /**
     * Obtiene la lista de Remitos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRemito</b>           filtra por 'eq' idRemito (Integer) <br>
     * <b>pJoinDetalleRemitos</b> obliga a Joinear con DetalleRemitos<br>
     * <b>pJoinClientes</b> obliga a Joinear con Clientes<br>
     * @return devuelve la lista de los Remitos que cumplan con el filtro
     */
    public List<RemitosT> getRemitosT(HashMap parametros);

    /**
     * Obtiene la lista de Remitos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRemito</b>           filtra por 'eq' idRemito (Integer) <br>
     * <b>pJoinDetalleRemitos</b> obliga a Joinear con DetalleRemitos<br>
     * <b>pJoinClientes</b> obliga a Joinear con Clientes<br>
     * @return devuelve la lista de los Remitos que cumplan con el filtro
     */
    public List<Remitos> getRemitos(HashMap parametros);

    /**
     * Obtiene el siguiente numero de instancia del detalleRemtioTemp
     * @return devuelve el siguiente numero de instancia del detalleRemtioTemp como int
     */
    public int getNextInstancia();

    /**
     * Actualiza o crea un remitoT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     * 
     * @param remitosT contiene los datos del remito a actualizar
     * @param detallesRemitosT contiene la lista de los detalles del remito a actualizar solo si es nuevo
     * @return devuelve el remitoT actualizado
     */
    public RemitosT updateRemitosT(RemitosT remitosT, List<DetalleRemitosT> detallesRemitosT);

    public List<RemitosIngresoT> getRemitosIngresosT(HashMap parametros);

    public List<Remitosingreso> getRemitosIngresos(HashMap parametros);

    public int getNextRemitoIngreso();//    public ar.com.jpack.transferencia.RemitosT updateRemitosT(ar.com.jpack.transferencia.RemitosT remitosT);
}
