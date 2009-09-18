/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Ordenesproduccion;
import ar.com.jpack.transferencia.AjustesStockT;
import ar.com.jpack.transferencia.DetAjustesStockT;
import ar.com.jpack.transferencia.DetOrdenesProduccionT;
import ar.com.jpack.transferencia.OrdenesProduccionT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface OrdenesproduccionFacadeRemote {

    public AjustesStockT updateAjustesStockT(AjustesStockT ajuste, ArrayList<DetAjustesStockT> listaDetAjustesStockT);

    public OrdenesProduccionT updateOrdenesProduccionT(OrdenesProduccionT opT, ArrayList<DetOrdenesProduccionT> listaDetalleOPT);

    public List<OrdenesProduccionT> getOrdenesProduccionT(HashMap parametros);

    public List<Ordenesproduccion> getOrdenesProduccion(HashMap parametros);

    public int getNextOrdenProduccion();

    public void setEstadoOP(Integer idOp, Integer newEstado);

    public Integer getStockOrdenesProduccion(Integer idArticulo);

}
