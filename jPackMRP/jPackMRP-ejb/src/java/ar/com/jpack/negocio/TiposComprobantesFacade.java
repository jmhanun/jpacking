/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposcomprobantes;
import ar.com.jpack.transferencia.TiposComprobantesT;
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
public class TiposComprobantesFacade implements TiposComprobantesFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Tipos de comprobantes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTipoComprobante</b>  filtra por 'eq' idTipoComprobante (Integer) <br>
     * @return devuelve la lista de los Tipos de comprabantes que cumplan con el filtro
     */
    public List<TiposComprobantesT> getTiposComprobantesT(HashMap parametros) {
        List<Tiposcomprobantes> tiposComprobantesList = getTiposComprobantes(parametros);
        List<TiposComprobantesT> tiposComprobantesTList = new ArrayList<TiposComprobantesT>();

        for (Tiposcomprobantes c : tiposComprobantesList) {
            TiposComprobantesT rdo = (TiposComprobantesT) DozerUtil.getDozerMapper(false).map(c, TiposComprobantesT.class);
            tiposComprobantesTList.add(rdo);
        }
        return tiposComprobantesTList;
    }

    /**
     * Obtiene la lista de Tipos de comprobantes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTipoComprobante</b>  filtra por 'eq' idTipoComprobante (Integer) <br>
     * @return devuelve la lista de los Tipos de comprabantes que cumplan con el filtro
     */
    public List<Tiposcomprobantes> getTiposComprobantes(HashMap parametros) {
        Criteria tiposComprobantesCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Tiposcomprobantes.class);
        List<Tiposcomprobantes> tiposComprobantesList;
        if (parametros.containsKey("pIdTipoComprobante")) {
            tiposComprobantesCritearia.add(Restrictions.eq("idTipoComprobante", parametros.get("pIdTipoComprobante")));
        }

        tiposComprobantesList = tiposComprobantesCritearia.list();
        return tiposComprobantesList;
    }
}
