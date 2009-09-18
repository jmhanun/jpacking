/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposiva;
import ar.com.jpack.transferencia.TiposIvaT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposIvaFacadeRemote {

    public List<TiposIvaT> getTiposIvaT(HashMap parametros);

    public void addTipoIva(TiposIvaT nuevoIva);

    public List<Tiposiva> getTiposIva(HashMap parametros);
}
