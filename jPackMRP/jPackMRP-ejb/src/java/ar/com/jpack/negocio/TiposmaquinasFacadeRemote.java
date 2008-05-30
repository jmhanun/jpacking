/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposmaquinas;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposmaquinasFacadeRemote {

    void create(Tiposmaquinas tiposmaquinas);

    void edit(Tiposmaquinas tiposmaquinas);

    void remove(Tiposmaquinas tiposmaquinas);

    Tiposmaquinas find(Object id);

    List<Tiposmaquinas> findAll();

}
