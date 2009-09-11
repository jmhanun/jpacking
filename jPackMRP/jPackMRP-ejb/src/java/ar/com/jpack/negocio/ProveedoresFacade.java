/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Proveedores;
import ar.com.jpack.transferencia.ProveedoresT;
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
 * @author Pablo
 */
@Stateless
public class ProveedoresFacade implements ProveedoresFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Proveedores filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdProveedor</b>  filtra por 'eq' idProveedor (Integer) <br>
     * <b>pNombres</b>    filtra por 'like' nombres (String) <br>
     * <b>pCuit</b>    filtra por 'like' cuit (String) <br>
     * @return devuelve la lista de los Proveedores que cumplan con el filtro
     */
    public List<ProveedoresT> getProveedoresT(HashMap parametros) {
        List<Proveedores> proveedoresList = getProveedores(parametros);
        List<ProveedoresT> proveedoresTList = new ArrayList<ProveedoresT>();

        for (Proveedores c : proveedoresList) {
            ProveedoresT rdo = (ProveedoresT) DozerUtil.getDozerMapper(false).map(c, ProveedoresT.class);
            proveedoresTList.add(rdo);
        }
        return proveedoresTList;
    }

    /**
     * Obtiene la lista de Proveedores filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdProveedor</b>  filtra por 'eq' idProveedor (Integer) <br>
     * <b>pNombres</b>    filtra por 'like' nombres (String) <br>
     * <b>pCuit</b>    filtra por 'like' cuit (String) <br>
     * @return devuelve la lista de los Proveedores que cumplan con el filtro
     */
    public List<Proveedores> getProveedores(HashMap parametros) {
        Criteria proveedoresCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Proveedores.class);
        List<Proveedores> proveedoresList;
        if (parametros.containsKey("pIdProveedor")) {
            proveedoresCritearia.add(Restrictions.eq("idProveedor", parametros.get("pIdProveedor")));
        }
        if (parametros.containsKey("pNombres")) {
            proveedoresCritearia.add(Restrictions.like("nombres", parametros.get("pNombres").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pCuit")) {
            proveedoresCritearia.add(Restrictions.like("cuit", parametros.get("pCuit").toString(), MatchMode.ANYWHERE));
        }
        proveedoresCritearia.setFetchMode("idEstado", FetchMode.JOIN);
        Criteria estadoCriteria = proveedoresCritearia.createCriteria("idEstado");
        estadoCriteria.add(Restrictions.eq("idEstado", 10));

        proveedoresList = proveedoresCritearia.list();
        return proveedoresList;
    }
}
