/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Precios;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface PreciosFacadeRemote {

    void create(Precios precios);

    void edit(Precios precios);

    void remove(Precios precios);

    Precios find(Object id);

    List<Precios> findAll();

}
