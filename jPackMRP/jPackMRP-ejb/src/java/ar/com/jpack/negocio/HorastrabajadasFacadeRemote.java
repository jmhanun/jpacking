/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Horastrabajadas;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface HorastrabajadasFacadeRemote {

    void create(Horastrabajadas horastrabajadas);

    void edit(Horastrabajadas horastrabajadas);

    void remove(Horastrabajadas horastrabajadas);

    Horastrabajadas find(Object id);

    List<Horastrabajadas> findAll();

}
