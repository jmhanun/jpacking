/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Domicilios;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface DomiciliosFacadeRemote {

    void create(Domicilios domicilios);

    void edit(Domicilios domicilios);

    void remove(Domicilios domicilios);

    Domicilios find(Object id);

    List<Domicilios> findAll();

}
