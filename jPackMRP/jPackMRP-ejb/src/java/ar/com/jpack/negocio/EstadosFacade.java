/*
 * EstadosFacade.java
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author jmhanun
 */
@Stateless
public class EstadosFacade implements EstadosFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    @Deprecated
    public Estados find(Object id) {
        return em.find(ar.com.jpack.persistencia.Estados.class, id);
    }

    @Deprecated
    public EstadosT findEstado(Integer id) {
        Estados estado = find(id);
        EstadosT estadoT = DataTransferHelper.copiarEstado(estado);
        return estadoT;
    }

    /**
     * Obtiene la lista de Estados filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdEstados</b>           filtra por 'eq' idUsuario (Integer) <br>
     * <b>pDescripcionEstado</b>   filtra por 'like AnyWhere' descripcion del Estado (String) <br>
     * <b>pNotas</b>               filtra por 'like AnyWhere' notas (String) <br>
     * <b>pJoinDominios</b>        obliga a Joinear con Dominios <br>
     * <b>pIdDominio</b>           filtra por 'eq' idDominio (Integer) <br>
     * <b>pDescripcionDominio</b>  filtra por 'like AnyWhere' descripcion del Dominio (String) <br>
     * @return devuelve la lista de los Estados que cumplan con el filtro <br>
     */
    public List<EstadosT> getEstadosT(HashMap parametros) {
        List<Estados> estadosList = getEstados(parametros);
        List<EstadosT> estadosTList = new ArrayList<EstadosT>();

        for (Estados c : estadosList) {
            EstadosT rdo = (EstadosT) DozerUtil.getDozerMapper(false).map(c, EstadosT.class);
            estadosTList.add(rdo);
        }
        return estadosTList;
    }

    /**
     * Obtiene la lista de Estados filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdEstados</b>           filtra por 'eq' idUsuario (Integer) <br>
     * <b>pDescripcionEstado</b>   filtra por 'like AnyWhere' descripcion del Estado (String) <br>
     * <b>pNotas</b>               filtra por 'like AnyWhere' notas (String) <br>
     * <b>pJoinDominios</b>        obliga a Joinear con Dominios <br>
     * <b>pIdDominio</b>           filtra por 'eq' idDominio (Integer) <br>
     * <b>pDescripcionDominio</b>  filtra por 'like AnyWhere' descripcion del Dominio (String) <br>
     * @return devuelve la lista de los Estados que cumplan con el filtro <br>
     */
    public List<Estados> getEstados(HashMap parametros) {
        Criteria estadosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Estados.class);
        List<Estados> estadosList;
        if (parametros.containsKey("pIdEstados")) {
            estadosCritearia.add(Restrictions.eq("idEstado", parametros.get("pIdEstados")));
        }
        if (parametros.containsKey("pDescripcionEstado")) {
            estadosCritearia.add(Restrictions.like("descripcion", parametros.get("pDescripcionEstado").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pNotas")) {
            estadosCritearia.add(Restrictions.like("notas", parametros.get("pNotas").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pJoinDominios")) {
            estadosCritearia.setFetchMode("idDominio", FetchMode.JOIN);
            Criteria dominioCriteria = estadosCritearia.createCriteria("idDominio");
            if (parametros.containsKey("pIdDominio")) {
                dominioCriteria.add(Restrictions.eq("idDominio", parametros.get("pIdDominio")));
            }
            if (parametros.containsKey("pDescripcionDominio")) {
                dominioCriteria.add(Restrictions.eq("descripcion", parametros.get("pDescripcionDominio")));
            }
        }
        estadosList = estadosCritearia.list();
        return estadosList;
    }
}
