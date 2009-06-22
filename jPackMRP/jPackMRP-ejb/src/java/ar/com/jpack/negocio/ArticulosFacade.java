/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Articulos;
import ar.com.jpack.persistencia.Precios;
import ar.com.jpack.transferencia.ArticulosT;
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
public class ArticulosFacade implements ArticulosFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Articulos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdArticulos</b>   filtra por 'eq' idArticulo (Integer) <br>
     * <b>pCodigo</b>        filtra por 'like AnyWhere' codigo (String) <br>
     * <b>pDescripcion</b>   filtra por 'like AnyWhere' descripcion (String) <br>
     * @return devuelve la lista de los Articulos que cumplan con el filtro <br>
     */
    public List<ArticulosT> getArticulosT(HashMap parametros) {
        List<Articulos> articulosList = getArticulos(parametros);
        List<ArticulosT> articulosTList = new ArrayList<ArticulosT>();

        for (Articulos c : articulosList) {
            ArticulosT rdo = (ArticulosT) DozerUtil.getDozerMapper(false).map(c, ArticulosT.class);
            articulosTList.add(rdo);
        }
        return articulosTList;
    }

    /**
     * Obtiene la lista de Articulos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdArticulos</b>   filtra por 'eq' idArticulo (Integer) <br>
     * <b>pCodigo</b>        filtra por 'like AnyWhere' codigo (String) <br>
     * <b>pDescripcion</b>   filtra por 'like AnyWhere' descripcion (String) <br>
     * @return devuelve la lista de los Articulos que cumplan con el filtro <br>
     */
    public List<Articulos> getArticulos(HashMap parametros) {
        Criteria articulosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Articulos.class);
        List<Articulos> articulosList;
        if (parametros.containsKey("pIdArticulos")) {
            articulosCritearia.add(Restrictions.eq("idArticulos", parametros.get("pIdArticulos")));
        }
        if (parametros.containsKey("pCodigo")) {
            articulosCritearia.add(Restrictions.like("codigo", parametros.get("pCodigo").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pDescripcion")) {
            articulosCritearia.add(Restrictions.like("descripcion", parametros.get("pDescripcion").toString(), MatchMode.ANYWHERE));
        }

        articulosCritearia.setFetchMode("idEstado", FetchMode.JOIN);
        articulosCritearia.setFetchMode("idUnidMedida", FetchMode.JOIN);


        articulosList = articulosCritearia.list();
        return articulosList;
    }

    /**
     * Obtiene el precio vigente de un Articulo
     * @param ArticuloT del que se desea conocer el precio
     * @return devuelve el precio como double
     */
    public double getPrecioArticuloVigente(ArticulosT articulosT) {
        Criteria preciosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Precios.class);
        Precios precioVigente;

        preciosCritearia.setFetchMode("idArticulo", FetchMode.JOIN);
        preciosCritearia.setFetchMode("idLista", FetchMode.JOIN);

        Criteria articulosCriteria = preciosCritearia.createCriteria("idArticulo");
        articulosCriteria.add(Restrictions.eq("idArticulo", articulosT.getIdArticulo()));
        Criteria listasPreciosCriteria = preciosCritearia.createCriteria("idLista");
        listasPreciosCriteria.add(Restrictions.isNull("fechaHasta"));


        precioVigente = (Precios) preciosCritearia.uniqueResult();
        return precioVigente.getPrecio();
    }
}
