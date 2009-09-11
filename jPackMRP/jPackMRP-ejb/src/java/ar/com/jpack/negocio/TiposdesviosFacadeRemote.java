/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposdesvios;
import ar.com.jpack.transferencia.TiposDesviosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface TiposdesviosFacadeRemote {

    public List<TiposDesviosT> getTiposDesviosT(HashMap parametros);

    public List<Tiposdesvios> getTiposDesvios(HashMap parametros);

    public void insertDesvioT(Integer idDetalleProduccion, Integer idTipoDesvio, String comentario);
}
