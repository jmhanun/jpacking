/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Componentes;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ComponentesFacadeRemote {

    void create(Componentes componentes);

    void edit(Componentes componentes);

    void remove(Componentes componentes);

    Componentes find(Object id);

    List<Componentes> findAll();

}
