/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleproduccion;
import ar.com.jpack.transferencia.DetalleProduccionT;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface DetalleproduccionFacadeRemote {

    /**
     * Obtiene la lista de DetalleProduccion filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdDetalleProduccion</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pFechaInicioEstimadaLT</b>  filtra por 'lt' fechaInicioEstimada (Date) <br>
     * <b>pFechaFinEstimadaGT</b>  filtra por 'gt' pFechaFinEstimada (Date) <br>
     * <b>pFechaInicioEstimada</b>  filtra por 'between' fechaInicioEstimada (Date) <br>
     * <b>pFechaDesdeEstimada</b> ; <b>pFechaHastaEstimada</b> <br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * <b>pJoinMaquinas</b> obliga a Joinear con Maquinas<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pJoinOrdenes</b>  obliga a Joinear con Detalle de Ordenes de Produccion<br>
     * @return devuelve la lista de los DetalleProduccion que cumplan con el filtro
     */
    public List<DetalleProduccionT> getDetalleProduccionT(HashMap parametros);

    /**
     * Obtiene la lista de DetalleProduccion filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdDetalleProduccion</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pFechaInicioEstimadaLT</b>  filtra por 'lt' fechaInicioEstimada (Date) <br>
     * <b>pFechaFinEstimadaGT</b>  filtra por 'gt' pFechaFinEstimada (Date) <br>
     * <b>pFechaInicioEstimada</b>  filtra por 'between' fechaInicioEstimada (Date) <br>
     * <b>pFechaDesdeEstimada</b> ; <b>pFechaHastaEstimada</b> <br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * <b>pJoinMaquinas</b> obliga a Joinear con Maquinas<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pJoinOrdenes</b>  obliga a Joinear con Detalle de Ordenes de Produccion<br>
     * @return devuelve la lista de los DetalleProduccion que cumplan con el filtro
     */
    public List<Detalleproduccion> getDetalleProduccion(HashMap parametros);

    public Double getAvanceProduccion(DetalleProduccionT detalleProduccionT);

    public Boolean getFeriado(Date fecha);

    public void updateDetalleProduccion(DetalleProduccionT detalleProduccionT);

    public void setEstadoProduccion(Integer idDetalleProduccion, Integer idEstado, Integer idEstadoAnterior, Date fecha);

    public Integer getTiempoRealProduccion(Integer idDetalleProduccion);

    public Integer getTiempoEstimadoProduccion(Integer idDetalleProduccion);
}
