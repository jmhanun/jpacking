/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Precios;
import ar.com.jpack.transferencia.PreciosT;
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
 * @author Pablo
 */
@Stateless
public class PreciosFacade implements PreciosFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Precios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdPrecio</b>  filtra por 'eq' idPrecio (Integer) <br>
     * <b>pJoinUsuarios</b>  obliga a joinear con Usuarios <br>
     * <b>pJoinArticulos</b>  obliga a joinear con Articulos <br>
     * <b>pJoinListasPrecios</b>  obliga a joinear con ListasPrecios <br>
     * @return devuelve la lista de los Precios que cumplan con el filtro
     */
    public List<PreciosT> getPreciosT(HashMap parametros) {
        List<Precios> preciosList = getPrecios(parametros);
        List<PreciosT> preciosTList = new ArrayList<PreciosT>();

        for (Precios c : preciosList) {
            PreciosT rdo = (PreciosT) DozerUtil.getDozerMapper(false).map(c, PreciosT.class);
            preciosTList.add(rdo);
        }
        return preciosTList;
    }

    /**
     * Obtiene la lista de Precios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdPrecio</b>  filtra por 'eq' idPrecio (Integer) <br>
     * <b>pJoinUsuarios</b>  obliga a joinear con Usuarios <br>
     * <b>pJoinArticulos</b>  obliga a joinear con Articulos <br>
     * <b>pJoinListasPrecios</b>  obliga a joinear con ListasPrecios <br>
     * @return devuelve la lista de los Precios que cumplan con el filtro
     */
    public List<Precios> getPrecios(HashMap parametros) {

        Criteria preciosCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Precios.class);
        List<Precios> preciosList;
        if (parametros.containsKey("pIdPrecio")) {
            preciosCriteria.add(Restrictions.eq("idPrecio", parametros.get("pIdPrecio")));
        }
        if (parametros.containsKey("pJoinListasPrecios")) {
            preciosCriteria.setFetchMode("idLista", FetchMode.JOIN);
        }
        if (parametros.containsKey("pJoinArticulos")) {
            preciosCriteria.setFetchMode("idArticulo", FetchMode.JOIN);
        }
        if (parametros.containsKey("pJoinUsuarios")) {
            preciosCriteria.setFetchMode("idUsuario", FetchMode.JOIN);
        }

        preciosList = preciosCriteria.list();
        return preciosList;
    }
}
