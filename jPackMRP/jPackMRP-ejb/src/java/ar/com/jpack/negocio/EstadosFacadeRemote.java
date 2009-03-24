/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Estados;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface EstadosFacadeRemote {

    void create(Estados estados);

    void edit(Estados estados);

    void remove(Estados estados);

    Estados find(Object id);

    List<Estados> findAll();

    public ar.com.jpack.transferencia.EstadosT findEstado(java.lang.Integer id);

}
