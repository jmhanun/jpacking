/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleproduccion;
import ar.com.jpack.transferencia.DetalleProduccionT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface DetalleproduccionFacadeRemote {

    public List<DetalleProduccionT> getDetalleProduccionT(HashMap parametros);

    public List<Detalleproduccion> getDetalleProduccion(HashMap parametros);

    public Double getAvanceProduccion(DetalleProduccionT detalleProduccionT);

    public void updateDetalleProduccion(DetalleProduccionT detalleProduccionT);
}
