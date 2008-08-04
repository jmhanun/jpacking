/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Unidadesmedida;
import ar.com.jpack.transferencia.UnidadesMedidaT;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface UnidadesmedidaFacadeRemote {

    void create(Unidadesmedida unidadesmedida);

    void edit(Unidadesmedida unidadesmedida);

    void remove(Unidadesmedida unidadesmedida);

    Unidadesmedida find(Object id);

    List<Unidadesmedida> findAll();

    public List<UnidadesMedidaT> findAllUnidadesMedidaT();
}
