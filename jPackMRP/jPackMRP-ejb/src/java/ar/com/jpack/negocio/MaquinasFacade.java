/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Maquinas;
import ar.com.jpack.transferencia.MaquinasT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class MaquinasFacade implements MaquinasFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Maquinas filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMaquina</b>  filtra por 'eq' idMaquina (Integer) <br>
     * <b>pMantenimiento</b>  filtra si horasUso >= horasMantenimiento<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pJoinActividades</b>  obliga a Joinear con Actividades<br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * @return devuelve la lista de los Maquinas que cumplan con el filtro
     */
    public List<MaquinasT> getMaquinasT(HashMap parametros) {
        List<Maquinas> maquinasList = getMaquinas(parametros);
        List<MaquinasT> maquinasTList = new ArrayList();
        for (Maquinas c : maquinasList) {
            MaquinasT rdo = (MaquinasT) DozerUtil.getDozerMapper(false).map(c, MaquinasT.class);
            maquinasTList.add(rdo);
        }
        return maquinasTList;
    }

    /**
     * Obtiene la lista de Maquinas filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMaquina</b>  filtra por 'eq' idMaquina (Integer) <br>
     * <b>pMantenimiento</b>  filtra si horasUso >= horasMantenimiento<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pJoinActividades</b>  obliga a Joinear con Actividades<br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * @return devuelve la lista de los Maquinas que cumplan con el filtro
     */
    public List<Maquinas> getMaquinas(HashMap parametros) {
        Criteria maquinaCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Maquinas.class);

        List<Maquinas> maquinasList;

        if (parametros.containsKey("pIdMaquina")) {
            maquinaCriteria.add(Restrictions.eq("idMaquina", parametros.get("pIdMaquinas")));
        }
        if (parametros.containsKey("pJoinEstados")) {
            maquinaCriteria.setFetchMode("idEstado", FetchMode.JOIN);
            if (parametros.containsKey("pIdEstado")) {
                Criteria estadosCriteria = maquinaCriteria.createCriteria("idEstado");
                estadosCriteria.add(Restrictions.eq("idEstado", parametros.get("pIdEstado")));
            }
        }
        if (parametros.containsKey("pJoinActividades")) {
            maquinaCriteria.setFetchMode("idActividad", FetchMode.JOIN);
        }
        maquinasList = maquinaCriteria.list();

        if (parametros.containsKey("pMantenimiento")) {
            Iterator<Maquinas> it = maquinasList.iterator();
            while (it.hasNext()) {
                Maquinas maquina = (Maquinas) it.next();
                if (maquina.getHorasUso() < maquina.getHorasMantenimiento()) {
                    it.remove();
                }
            }
        }
        return maquinasList;
    }
}
