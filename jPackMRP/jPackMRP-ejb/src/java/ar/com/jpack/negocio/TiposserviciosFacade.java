/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposservicios;
import ar.com.jpack.transferencia.TiposServiciosT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author jmhanun
 */
@Stateless
public class TiposserviciosFacade implements TiposserviciosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de TiposServicios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTiposServicios</b>  filtra por 'eq' idTiposServicios(Integer) <br>
     * @return devuelve la lista de los TiposServicios que cumplan con el filtro
     */
    public List<TiposServiciosT> getTiposServiciosT(HashMap parametros) {
        List<Tiposservicios> tiposServiciosList = getTiposServicios(parametros);
        List<TiposServiciosT> tiposServiciosTList = new ArrayList<TiposServiciosT>();

        for (Tiposservicios c : tiposServiciosList) {
            TiposServiciosT rdo = (TiposServiciosT) DozerUtil.getDozerMapper(false).map(c, TiposServiciosT.class);
            tiposServiciosTList.add(rdo);
        }
        return tiposServiciosTList;
    }

    /**
     * Obtiene la lista de TiposServicios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTiposServicios</b>  filtra por 'eq' idTiposServicios(Integer) <br>
     * @return devuelve la lista de los TiposServicios que cumplan con el filtro
     */
    public List<Tiposservicios> getTiposServicios(HashMap parametros) {
        Criteria tiposServiciosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Tiposservicios.class);
        List<Tiposservicios> tiposServiciosList;
        if (parametros.containsKey("pIdTipoServicio")) {
            tiposServiciosCritearia.add(Restrictions.eq("idTipoServicio", parametros.get("pIdTiposServicio")));
        }
        tiposServiciosList = tiposServiciosCritearia.list();
        return tiposServiciosList;
    }

}
