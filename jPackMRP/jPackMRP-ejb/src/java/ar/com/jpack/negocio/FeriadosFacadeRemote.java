/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Feriados;
import ar.com.jpack.transferencia.FeriadosT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface FeriadosFacadeRemote {

    public List<FeriadosT> getFeriadosT(HashMap parametros);

    public List<Feriados> getFeriados(HashMap parametros);

    public Integer deleteFeriadoT(Integer idFeriado);

    public FeriadosT updateFeriadosT(FeriadosT feriadosT);

}
