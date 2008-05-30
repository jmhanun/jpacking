/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Listasprecios;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ListaspreciosFacadeRemote {

    void create(Listasprecios listasprecios);

    void edit(Listasprecios listasprecios);

    void remove(Listasprecios listasprecios);

    Listasprecios find(Object id);

    List<Listasprecios> findAll();

}
