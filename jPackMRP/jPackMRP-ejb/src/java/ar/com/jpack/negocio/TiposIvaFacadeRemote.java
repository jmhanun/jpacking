/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;


import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface TiposIvaFacadeRemote {

    public java.util.List<ar.com.jpack.transferencia.TiposIvaT> findAllTiposIva();

    
}
