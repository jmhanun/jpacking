/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Maquinas;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface MaquinasFacadeRemote {

    void create(Maquinas maquinas);

    void edit(Maquinas maquinas);

    void remove(Maquinas maquinas);

    Maquinas find(Object id);

    List<Maquinas> findAll();

}
