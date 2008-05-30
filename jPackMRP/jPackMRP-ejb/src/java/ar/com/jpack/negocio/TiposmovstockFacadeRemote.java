/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposmovstock;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposmovstockFacadeRemote {

    void create(Tiposmovstock tiposmovstock);

    void edit(Tiposmovstock tiposmovstock);

    void remove(Tiposmovstock tiposmovstock);

    Tiposmovstock find(Object id);

    List<Tiposmovstock> findAll();

}
