/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Articulos;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ArticulosFacadeRemote {

    void create(Articulos articulos);

    void edit(Articulos articulos);

    void remove(Articulos articulos);

    Articulos find(Object id);

    List<Articulos> findAll();

}
