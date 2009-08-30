/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Mantenimiento;
import ar.com.jpack.transferencia.MantenimientoT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author jmhanun
 */
@Stateless
public class MantenimientoFacade implements MantenimientoFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Mantenimientos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMantenimiento</b>  filtra por 'eq' idMantenimiento (Integer) <br>
     * <b>pFechaFinNull</b>  filtra por 'isNull' fechaFin (Integer) <br>
     * <b>pJoinMaquinas</b>  obliga a Joinear con Maquinas<br>
     * <b>pJoinTiposServicios</b>  obliga a Joinear con TiposServicios<br>
     * @return devuelve la lista de los Mantenimientos que cumplan con el filtro
     */
    public List<MantenimientoT> getMantenimientoT(HashMap parametros) {
        List<Mantenimiento> mantenimientoList = getMantenimiento(parametros);
        List<MantenimientoT> mantenimientoTList = new ArrayList<MantenimientoT>();

        for (Mantenimiento c : mantenimientoList) {
            MantenimientoT rdo = (MantenimientoT) DozerUtil.getDozerMapper(false).map(c, MantenimientoT.class);
            mantenimientoTList.add(rdo);
        }
        return mantenimientoTList;
    }

    /**
     * Obtiene la lista de Mantenimientos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMantenimiento</b>  filtra por 'eq' idMantenimiento (Integer) <br>
     * <b>pFechaFinNull</b>  filtra por 'isNull' fechaFin (Integer) <br>
     * <b>pJoinMaquinas</b>  obliga a Joinear con Maquinas<br>
     * <b>pJoinTiposServicios</b>  obliga a Joinear con TiposServicios<br>
     * @return devuelve la lista de los Mantenimientos que cumplan con el filtro
     */
    public List<Mantenimiento> getMantenimiento(HashMap parametros) {
        Criteria mantenimientoCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Mantenimiento.class);
        List<Mantenimiento> mantenimientoList;
        if (parametros.containsKey("pIdMantenimiento")) {
            mantenimientoCritearia.add(Restrictions.eq("idMantenimiento", parametros.get("pIdMantenimiento")));
        }
        if (parametros.containsKey("pFechaFinNull")) {
            mantenimientoCritearia.add(Restrictions.isNull("fechaFin"));
        }
        if (parametros.containsKey("pJoinMaquinas")) {
            mantenimientoCritearia.setFetchMode("idMaquina", FetchMode.JOIN);
        }
        if (parametros.containsKey("pJoinTiposServicios")) {
            mantenimientoCritearia.setFetchMode("idTipoServicio", FetchMode.JOIN);
        }
        mantenimientoList = mantenimientoCritearia.list();
        return mantenimientoList;
    }
}
