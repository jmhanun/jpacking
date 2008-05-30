/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Localidades;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface LocalidadesFacadeRemote {

    void create(Localidades localidades);

    void edit(Localidades localidades);

    void remove(Localidades localidades);

    Localidades find(Object id);

    List<Localidades> findAll();

}
