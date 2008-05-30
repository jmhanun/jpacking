/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Remitos;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface RemitosFacadeRemote {

    void create(Remitos remitos);

    void edit(Remitos remitos);

    void remove(Remitos remitos);

    Remitos find(Object id);

    List<Remitos> findAll();

}
