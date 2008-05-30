/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detallefacturas;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface DetallefacturasFacadeRemote {

    void create(Detallefacturas detallefacturas);

    void edit(Detallefacturas detallefacturas);

    void remove(Detallefacturas detallefacturas);

    Detallefacturas find(Object id);

    List<Detallefacturas> findAll();

}
