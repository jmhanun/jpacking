/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Prioridades;
import ar.com.jpack.transferencia.PrioridadesT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface PrioridadesFacadeRemote {

    public List<PrioridadesT> getPrioridadesT(HashMap parametros);

    public List<Prioridades> getPrioridades(HashMap parametros);


}
