/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Articulos;
import ar.com.jpack.persistencia.Componentes;
import ar.com.jpack.persistencia.Precios;
import ar.com.jpack.persistencia.Stock;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.ComponentesT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
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
    @EJB
    private EstadosFacadeRemote estadosFacade;

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Articulos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdArticulos</b>   filtra por 'eq' idArticulo (Integer) <br>
     * <b>pCodigo</b>        filtra por 'like AnyWhere' codigo (String) <br>
     * <b>pDescripcion</b>   filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pFinal</b>   filtra por 'eq' articuloFinal (String) <br>
     * <b>pImprimible</b>   filtra por 'eq' imprimible (String) <br>
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
     * <b>pFinal</b>   filtra por 'eq' articuloFinal (String) <br>
     * <b>pImprimible</b>   filtra por 'eq' imprimible (String) <br>
     * @return devuelve la lista de los Articulos que cumplan con el filtro <br>
     */
    public List<Articulos> getArticulos(HashMap parametros) {
        Criteria articulosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Articulos.class);
        List<Articulos> articulosList;
        if (parametros.containsKey("pIdArticulos")) {
            articulosCritearia.add(Restrictions.eq("idArticulo", parametros.get("pIdArticulos")));
        }
        if (parametros.containsKey("pCodigo")) {
            articulosCritearia.add(Restrictions.like("codigo", parametros.get("pCodigo").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pDescripcion")) {
            articulosCritearia.add(Restrictions.like("descripcion", parametros.get("pDescripcion").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pFinal")) {
            articulosCritearia.add(Restrictions.eq("articuloFinal", parametros.get("pFinal").toString()));
        }
        if (parametros.containsKey("pImprimible")) {
            articulosCritearia.add(Restrictions.eq("imprimible", parametros.get("pImprimible").toString()));
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
        if (precioVigente == null) {
            return 0.0;
        } else {
            return precioVigente.getPrecio();
        }
    }

    /**
     * Obtiene el stock de un Articulo
     * @param ArticuloT del que se desea conocer el stock
     * @return devuelve la cantidad de stock como double
     */
    public double getStockArticulo(ArticulosT articulosT) {

//        java.sql.Connection con = ((EntityManagerImpl) em.getDelegate()).getSession().connection();

        Criteria stockCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Stock.class);
        Stock stock;

        stockCritearia.setFetchMode("idArticulo", FetchMode.JOIN);

        Criteria articulosCriteria = stockCritearia.createCriteria("idArticulo");
        articulosCriteria.add(Restrictions.eq("idArticulo", articulosT.getIdArticulo()));


        stock = (Stock) stockCritearia.uniqueResult();
        if (stock == null) {
            return 0.0;
        } else {
            return stock.getCantidad();
        }
    }

    /**
     * Obtiene la lista de Componentes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdArticulo</b>   filtra por 'eq' idArticulo (Integer) <br>
     * @return devuelve la lista de los Componentes que cumplan con el filtro <br>
     */
    public List<ComponentesT> getComponentesT(HashMap parametros) {
        List<Componentes> componentesList = getComponentes(parametros);
        List<ComponentesT> componentesTList = new ArrayList<ComponentesT>();

        for (Componentes c : componentesList) {
            ComponentesT rdo = (ComponentesT) DozerUtil.getDozerMapper(false).map(c, ComponentesT.class);
            componentesTList.add(rdo);
        }
        return componentesTList;
    }

    /**
     * Obtiene la lista de Componentes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdArticulo</b>   filtra por 'eq' idArticulo (Integer) <br>
     * @return devuelve la lista de los Componentes que cumplan con el filtro <br>
     */
    public List<Componentes> getComponentes(HashMap parametros) {
        Criteria componentesCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Componentes.class);
        
        List<Componentes> componentesList;
        
        componentesCritearia.setFetchMode("articulos", FetchMode.JOIN);
        componentesCritearia.setFetchMode("componentes", FetchMode.JOIN);
        
        if (parametros.containsKey("pIdArticulo")) {
            Criteria articuloCriteria = componentesCritearia.createCriteria("articulos");
            articuloCriteria.add(Restrictions.eq("idArticulo", parametros.get("pIdArticulo")));
        }
        if (parametros.containsKey("pIdComponente")) {
            Criteria compCriteria = componentesCritearia.createCriteria("componentes");
            compCriteria.add(Restrictions.eq("componentes", parametros.get("pIdComponente")));
        }

        componentesList = componentesCritearia.list();
        return componentesList;
    }

    public ArticulosT updateArticulosT(ArticulosT dto) {
        Articulos articulos = (Articulos) DozerUtil.getDozerMapper(false).map(dto, Articulos.class);

        //si el numero de id es null significa que es nuevo
        if (articulos.getIdArticulo() != null) {
            articulos.setFechaModificacion(new Date());
            em.merge(articulos);
        } else {
            articulos.setFechaAlta(new Date());
            articulos.setFechaModificacion(new Date());
            HashMap parametros = new HashMap();
            parametros.put("pIdEstados", 16);
            articulos.setIdEstado(estadosFacade.getEstados(parametros).get(0));
            em.persist(articulos);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdArticulos", articulos.getIdArticulo());
        return getArticulosT(parametros).get(0);
    }
}
