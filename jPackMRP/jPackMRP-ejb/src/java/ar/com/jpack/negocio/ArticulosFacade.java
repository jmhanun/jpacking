/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Articulos;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ArticulosFacade implements ArticulosFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(Articulos articulos) {
        em.persist(articulos);
    }

    public void edit(Articulos articulos) {
        em.merge(articulos);
    }

    public void remove(Articulos articulos) {
        em.remove(em.merge(articulos));
    }

    public Articulos find(Object id) {
        return em.find(ar.com.jpack.persistencia.Articulos.class, id);
    }

    public List<Articulos> findAll() {
        return em.createQuery("select object(o) from Articulos as o").getResultList();
    }

    public List<ArticulosT> findAllArticulosT() {
        List<Articulos> articulos = findAll();
        return DataTransferHelper.copiarArticulosALista(articulos);
    }

    public List<ArticulosT> findArticulosT(HashMap parametros) {
        String sql = "SELECT a FROM Articulos a";
        boolean condicion = false;
        if (parametros.containsKey("pIdArticulo")) {
            sql += " WHERE a.idArticulo = :idArticulo";
            condicion = true;
        }
        if (parametros.containsKey("pCodigo")) {
            if (condicion) {
                sql += " AND a.codigo like :codigo";
            } else {
                sql += " WHERE a.codigo like :codigo";
                condicion = true;
            }
        }
        if (parametros.containsKey("pDescripcion")) {
            if (condicion) {
                sql += " AND a.descripcion like :descripcion";
            } else {
                sql += " WHERE a.descripcion like :descripcion";
                condicion = true;
            }
        }
        Query query = em.createQuery(sql);

        if (parametros.containsKey("pIdArticulo")) {
            query.setParameter("idArticulo", parametros.get("pIdArticulo"));
        }
        if (parametros.containsKey("pCodigo")) {
            query.setParameter("codigo", parametros.get("pCodigo"));
        }
        if (parametros.containsKey("pDescripcion")) {
            query.setParameter("descripcion", parametros.get("pDescripcion"));
        }

        return DataTransferHelper.copiarArticulosALista(query.getResultList());

    }
}
