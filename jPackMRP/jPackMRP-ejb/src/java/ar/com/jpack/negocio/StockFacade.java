/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Stock;
import ar.com.jpack.transferencia.StockT;
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
public class StockFacade implements StockFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Stock filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdStock</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pJoinArticulos</b>  obliga a Joinear con Articulos<br>
     * @return devuelve la lista de los Mails que cumplan con el filtro
     */
    public List<StockT> getStockT(HashMap parametros) {
        List<Stock> stockList = getStock(parametros);
        List<StockT> stockTList = new ArrayList<StockT>();

        for (Stock c : stockList) {
            StockT rdo = (StockT) DozerUtil.getDozerMapper(false).map(c, StockT.class);
            stockTList.add(rdo);
        }
        return stockTList;
    }

    /**
     * Obtiene la lista de Stock filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdStock</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pJoinArticulos</b>  obliga a Joinear con Articulos<br>
     * @return devuelve la lista de los Mails que cumplan con el filtro
     */
    public List<Stock> getStock(HashMap parametros) {
        Criteria stockCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Stock.class);
        List<Stock> stockList;
        if (parametros.containsKey("pIdStock")) {
            stockCritearia.add(Restrictions.eq("idStock", parametros.get("pIdStock")));
        }
        //Con el setFetchMode obliga a que el lazy se ponga las pilas
        if (parametros.containsKey("pJoinArticulos")) {
            stockCritearia.setFetchMode("idArticulo", FetchMode.JOIN);
        }
        stockList = stockCritearia.list();
        return stockList;
    }
}
