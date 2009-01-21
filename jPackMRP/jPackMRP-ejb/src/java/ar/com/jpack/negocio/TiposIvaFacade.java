/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposiva;
import ar.com.jpack.transferencia.TiposIvaT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class TiposIvaFacade implements TiposIvaFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<TiposIvaT> findAllTiposIva() {
        
        List<Tiposiva> tiposIva = em.createQuery("select object(o) from Tiposiva as o").getResultList();
        List<TiposIvaT> tiposIvaTs = new ArrayList<TiposIvaT>();

        tiposIvaTs = DataTransferHelper.copiarTiposIvaALista(tiposIva);

        return tiposIvaTs;
    }

    public List<Tiposiva> findAll() {
        return em.createQuery("select object(o) from Tiposiva as o").getResultList();
    }

    /*
     *     public List<UsuariosT> findAllUsuariosT() {
    List<Usuarios> usuarios = findAll();
    List<UsuariosT> usuariosTs = new ArrayList<UsuariosT>();
    for (Iterator<Usuarios> it = usuarios.iterator(); it.hasNext();) {
    Usuarios usuario = it.next();
    UsuariosT usuariosT;
    usuariosT = DataTransferHelper.copiarUsuario(usuario);
    usuariosT.setIdRolCollection((ArrayList<RolesT>) DataTransferHelper.copiarRolesALista(usuario.getIdRolCollection()));
    usuariosTs.add(usuariosT);
    }
    return usuariosTs;
    }
     */
}
