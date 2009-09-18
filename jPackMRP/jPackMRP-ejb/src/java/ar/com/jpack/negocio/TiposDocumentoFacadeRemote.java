/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposdocumento;
import ar.com.jpack.transferencia.TiposDocumentoT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposDocumentoFacadeRemote {

    public List<TiposDocumentoT> getTiposDocumentoT(HashMap parametros);

    public List<Tiposdocumento> getTiposDocumento(HashMap parametros);
}
