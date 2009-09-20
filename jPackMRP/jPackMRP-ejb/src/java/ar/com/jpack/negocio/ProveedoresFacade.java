/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Proveedores;
import ar.com.jpack.transferencia.ProveedoresT;
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
 * @author Pablo
 */
@Stateless
public class ProveedoresFacade implements ProveedoresFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private TiposDocumentoFacadeRemote tiposDocumentoFacade;
    @EJB
    private TiposIvaFacadeRemote tiposIvaFacade;
    @EJB
    private EstadosFacadeRemote estadosFacade;

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

    public ProveedoresT updateProveedoresT(ProveedoresT dto) {
        Proveedores proveedor = (Proveedores) DozerUtil.getDozerMapper(false).map(dto, Proveedores.class);

        //si el numero de id es null significa que es nuevo
        if (proveedor.getIdProveedor() != null) {
            HashMap parametros = new HashMap();
            parametros.put("pIdEstados", 10);
            proveedor.setIdEstado(estadosFacade.getEstados(parametros).get(0));

            parametros = new HashMap();
            parametros.put("pIdTipoDocumento", 2);
            proveedor.setIdTipoDocumento(tiposDocumentoFacade.getTiposDocumento(parametros).get(0));


            em.merge(proveedor);
        } else {
            proveedor.setFechaAlta(new Date());
            HashMap parametros = new HashMap();
            parametros.put("pIdEstados", 10);
            proveedor.setIdEstado(estadosFacade.getEstados(parametros).get(0));

            parametros = new HashMap();
            parametros.put("pIdTipoDocumento", 2);
            proveedor.setIdTipoDocumento(tiposDocumentoFacade.getTiposDocumento(parametros).get(0));

            em.persist(proveedor);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdProveedor", proveedor.getIdProveedor());
        return getProveedoresT(parametros).get(0);
    }
}
