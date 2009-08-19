/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Maquinas;
import ar.com.jpack.transferencia.MaquinasT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface MaquinasFacadeRemote {

    public List<MaquinasT> getMaquinasT(HashMap parametros);

    public List<Maquinas> getMaquinas(HashMap parametros);

}
