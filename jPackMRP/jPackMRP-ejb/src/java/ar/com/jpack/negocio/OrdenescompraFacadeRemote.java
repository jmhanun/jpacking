/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Ordenescompra;
import ar.com.jpack.transferencia.DetalleOrdenesComprasT;
import ar.com.jpack.transferencia.OrdenesCompraT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface OrdenescompraFacadeRemote {

    public List<OrdenesCompraT> getOrdenesCompraT(HashMap parametros);

    public List<Ordenescompra> getOrdenescompra(HashMap parametros);

    public OrdenesCompraT updateOrdenCompraT(OrdenesCompraT orden, ArrayList<DetalleOrdenesComprasT> listDto);

    public int getNextOrden();


}
