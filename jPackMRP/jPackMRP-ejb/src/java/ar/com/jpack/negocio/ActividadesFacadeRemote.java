/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Actividades;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ActividadesFacadeRemote {

    void create(Actividades actividades);

    void edit(Actividades actividades);

    void remove(Actividades actividades);

    Actividades find(Object id);

    List<Actividades> findAll();

}
