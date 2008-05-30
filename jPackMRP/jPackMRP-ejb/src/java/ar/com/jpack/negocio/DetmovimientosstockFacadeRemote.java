/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detmovimientosstock;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface DetmovimientosstockFacadeRemote {

    void create(Detmovimientosstock detmovimientosstock);

    void edit(Detmovimientosstock detmovimientosstock);

    void remove(Detmovimientosstock detmovimientosstock);

    Detmovimientosstock find(Object id);

    List<Detmovimientosstock> findAll();

}
