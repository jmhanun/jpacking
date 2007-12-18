/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.transferencia.ArticulosT;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface ArticulosSessionRemote {

    List<ArticulosT> obtenerArticulos();

    void agregarArticulo(ArticulosT articulo);

    void editarArticulo(ArticulosT oArticulo);
    
}
