/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleremitos;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface DetalleremitosFacadeRemote {

    void create(Detalleremitos detalleremitos);

    void edit(Detalleremitos detalleremitos);

    void remove(Detalleremitos detalleremitos);

    Detalleremitos find(Object id);

    List<Detalleremitos> findAll();

}
