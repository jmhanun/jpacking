/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Listasprecios;
import ar.com.jpack.transferencia.ListasPreciosT;
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
public class ListaspreciosFacade implements ListaspreciosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de ListasPrecios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdLista</b>  filtra por 'eq' idLista (Integer) <br>
     * @return devuelve la lista de los ListasPrecios que cumplan con el filtro
     */
    public List<ListasPreciosT> getListasPreciosT(HashMap parametros) {
        List<Listasprecios> listasPreciosList = getListasPrecios(parametros);
        List<ListasPreciosT> listasPreciosTList = new ArrayList<ListasPreciosT>();

        for (Listasprecios c : listasPreciosList) {
            ListasPreciosT rdo = (ListasPreciosT) DozerUtil.getDozerMapper(false).map(c, ListasPreciosT.class);
            listasPreciosTList.add(rdo);
        }
        return listasPreciosTList;
    }

    /**
     * Obtiene la lista de ListasPrecios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdLista</b>  filtra por 'eq' idLista (Integer) <br>
     * @return devuelve la lista de los ListasPrecios que cumplan con el filtro
     */
    public List<Listasprecios> getListasPrecios(HashMap parametros) {
        Criteria listasPreciosCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Listasprecios.class);
        List<Listasprecios> listasPreciosList;
        if (parametros.containsKey("pIdLista")) {
            listasPreciosCriteria.add(Restrictions.eq("idLista", parametros.get("pIdLista")));
        }
        if (parametros.containsKey("pJoinEstados")) {
            listasPreciosCriteria.setFetchMode("idEstado", FetchMode.JOIN);
        }
        listasPreciosList = listasPreciosCriteria.list();
        return listasPreciosList;
    }
}
