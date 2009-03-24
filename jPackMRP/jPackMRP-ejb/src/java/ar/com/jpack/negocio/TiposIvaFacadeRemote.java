/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;


import ar.com.jpack.transferencia.TiposIvaT;
import ar.com.jpack.transferencia.listas.TiposIvaListaT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposIvaFacadeRemote {

    public List<TiposIvaListaT> findAllTiposIvaLista(HashMap parametros);

    public void addTipoIva(TiposIvaT nuevoIva);

    public TiposIvaT getTipoIvaT(Integer idTipoIva);

    
}
