/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Sellos;
import ar.com.jpack.transferencia.SellosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface SellosFacadeRemote {

    public List<SellosT> getSellosT(HashMap parametros);

    public List<Sellos> getSellos(HashMap parametros);

    

}
