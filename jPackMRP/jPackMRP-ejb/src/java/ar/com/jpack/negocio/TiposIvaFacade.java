/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.persistencia.Tiposiva;
import ar.com.jpack.transferencia.TiposIvaT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import ar.com.jpack.transferencia.helper.ListasDataTransferHelper;
import ar.com.jpack.transferencia.listas.TiposIvaListaT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jmhanun
 */
@Stateless
public class TiposIvaFacade implements TiposIvaFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<TiposIvaListaT> findAllTiposIvaLista(HashMap parametros) {
        List<Tiposiva> tiposIva = findTiposIvaT(parametros);
        List<TiposIvaListaT> tiposIvaTs = new ArrayList<TiposIvaListaT>();
        tiposIvaTs = ListasDataTransferHelper.copiarTiposIvaALista(tiposIva);
        return tiposIvaTs;
    }

    public TiposIvaT getTipoIvaT(Integer idTipoIva) {
        HashMap parametros = new HashMap();
        parametros.put("pIdTipoIva", idTipoIva);
        List<Tiposiva> tiposIva = findTiposIvaT(parametros);
//        SELECT a FROM Tiposiva a WHERE a.idTipoIva = :idTipoIva
//        SELECT t FROM Tiposiva t WHERE t.idTipoIVA = :idTipoIVA
//        Query q = em.createQuery("SELECT t FROM Tiposiva t WHERE t.idTipoIVA = :idTipoIVA");
//        q.setParameter("idTipoIva", idTipoIva);
//        List<Tiposiva> tiposIva = q.getResultList();
        return DataTransferHelper.copiarTipoIva(tiposIva.get(0));
    }

    public void addTipoIva(TiposIvaT nuevoIva) {
        Tiposiva tipoIva = new Tiposiva();
        Estados estado = em.find(ar.com.jpack.persistencia.Estados.class,
                nuevoIva.getIdEstado().getIdEstado());

        tipoIva.setAbreviatura(nuevoIva.getAbreviatura());
        tipoIva.setDescripcion(nuevoIva.getDescripcion());
        tipoIva.setIdEstado(estado);
        tipoIva.setIdTipoIVA(99);
        em.persist(tipoIva);
        em.flush();
    }

    private List<Tiposiva> findTiposIvaT(HashMap parametros) {
        String sql = "SELECT a FROM Tiposiva a";
        boolean condicion = false;
        if (parametros.containsKey("pIdTipoIva")) {
            sql += " WHERE a.idTipoIVA = :idTipoIva";
            condicion = true;
        }
        if (parametros.containsKey("pDescripcion")) {
            if (condicion) {
                sql += " AND a.descripcion like :descripcion";
            } else {
                sql += " WHERE a.descripcion like :descripcion";
                condicion = true;
            }
        }
        if (parametros.containsKey("pAbreviatura")) {
            if (condicion) {
                sql += " AND a.abreviatura like :abreviatura";
            } else {
                sql += " WHERE a.abreviatura like :abreviatura";
                condicion = true;
            }
        }
        if (parametros.containsKey("pEstado")) {
            if (condicion) {
                sql += " AND a.idEstado.idEstado = :estado";
            } else {
                sql += " WHERE a.idEstado.idEstado = :estado";
                condicion = true;
            }
        }
        Query query = em.createQuery(sql);

        if (parametros.containsKey("pIdTipoIva")) {
            query.setParameter("idTipoIva", parametros.get("pIdTipoIva"));
        }
        if (parametros.containsKey("pDescripcion")) {
            query.setParameter("descripcion", parametros.get("pDescripcion"));
        }
        if (parametros.containsKey("pAbreviatura")) {
            query.setParameter("abreviatura", parametros.get("pAbreviatura"));
        }
        if (parametros.containsKey("pEstado")) {
            query.setParameter("estado", parametros.get("pEstado"));
        }
        query.setHint("toplink.refresh", "true");
        List<Tiposiva> tiposIva = query.getResultList();
        return tiposIva;
    }
}
