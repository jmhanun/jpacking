/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Movimientosstock;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface MovimientosstockFacadeRemote {

    void create(Movimientosstock movimientosstock);

    void edit(Movimientosstock movimientosstock);

    void remove(Movimientosstock movimientosstock);

    Movimientosstock find(Object id);

    List<Movimientosstock> findAll();

}
