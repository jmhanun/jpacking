/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposdocumento;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposdocumentoFacadeRemote {

    void create(Tiposdocumento tiposdocumento);

    void edit(Tiposdocumento tiposdocumento);

    void remove(Tiposdocumento tiposdocumento);

    Tiposdocumento find(Object id);

    List<Tiposdocumento> findAll();

}
