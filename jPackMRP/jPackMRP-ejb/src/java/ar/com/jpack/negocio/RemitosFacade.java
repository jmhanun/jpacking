/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleremitos;
import ar.com.jpack.persistencia.Remitos;
import ar.com.jpack.transferencia.DetalleRemitosT;
import ar.com.jpack.transferencia.RemitosT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
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
public class RemitosFacade implements RemitosFacadeRemote {

    @EJB
    private TiposComprobantesFacadeRemote tiposComprobantesFacade;
    @EJB
    private EstadosFacadeRemote estadosFacade;
    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Remitos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRemito</b>           filtra por 'eq' idRemito (Integer) <br>
     * <b>pJoinDetalleRemitos</b> obliga a Joinear con DetalleRemitos<br>
     * @return devuelve la lista de los Remitos que cumplan con el filtro
     */
    public List<RemitosT> getRemitosT(HashMap parametros) {
        List<Remitos> remitosList = getRemitos(parametros);
        List<RemitosT> remitosTList = new ArrayList();
        for (Remitos c : remitosList) {
            RemitosT rdo = (RemitosT) DozerUtil.getDozerMapper(false).map(c, RemitosT.class);
            remitosTList.add(rdo);
        }
        return remitosTList;
    }

    /**
     * Obtiene la lista de Remitos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRemito</b>           filtra por 'eq' idRemito (Integer) <br>
     * <b>pJoinDetalleRemitos</b> obliga a Joinear con DetalleRemitos<br>
     * @return devuelve la lista de los Remitos que cumplan con el filtro
     */
    public List<Remitos> getRemitos(HashMap parametros) {
        Criteria remitosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Remitos.class);
        List<Remitos> remitosList;
        if (parametros.containsKey("pIdRemito")) {
            remitosCritearia.add(Restrictions.eq("idRemito", parametros.get("pIdRemito")));
        }
        if (parametros.containsKey("pJoinDetalleRemitos")) {
            remitosCritearia.setFetchMode("detalleremitosCollection", FetchMode.JOIN);
        }
        remitosList = remitosCritearia.list();
        return remitosList;
    }

    /**
     * Obtiene el siguiente numero de remito
     * @return devuelve el siguiente numero de remito como int
     */
    public int getNextRemito() {
        String hql = "select max(r.nroRemito) from Remitos r";
        Integer maxID = (Integer) ((EntityManagerImpl) em.getDelegate()).getSession().createQuery(hql).uniqueResult();
        maxID++;
        if (maxID == null) {
            return 0;
        }
        if (maxID < 0) {
            return 0;
        }
        return maxID;
    }

    /**
     * Obtiene el siguiente numero de instancia del detalleRemtioTemp
     * @return devuelve el siguiente numero de instancia del detalleRemtioTemp como int
     */
    public int getNextInstancia() {
        String hql = "select max(d.instancia) from Detalleremitostemp d";
        Integer maxID = (Integer) ((EntityManagerImpl) em.getDelegate()).getSession().createQuery(hql).uniqueResult();
        maxID++;
        if (maxID == null) {
            return 0;
        }
        if (maxID < 0) {
            return 0;
        }
        return maxID;
    }

    /**
     * Actualiza o crea un remitoT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     * 
     * @param remitosT contiene los datos del remito a actualizar
     * @param detallesRemitosT contiene la lista de los detalles del remito a actualizar solo si es nuevo
     * @return devuelve el remitoT actualizado
     */
    public RemitosT updateRemitosT(RemitosT remitosT, List<DetalleRemitosT> detallesRemitosT) {

        HashMap parametros = new HashMap();
        Remitos remitos = (Remitos) DozerUtil.getDozerMapper(false).map(remitosT, Remitos.class);

        //si el numero de id es null significa que es nuevo
        if (remitos.getIdRemito() != null) {
            em.merge(remitos);
            parametros.put("pIdRemito", remitos.getIdRemito());

            return getRemitosT(parametros).get(0);
        } else {
            remitos.setNroRemito(getNextRemito());
            parametros.put("pIdEstados", remitosT.getIdEstado().getIdEstado());
            remitos.setIdEstado((estadosFacade.getEstados(parametros)).get(0));
            parametros = new HashMap();
            parametros.put("pIdTipoComprobante", remitosT.getIdTipoComprobante().getIdTipoComprobante());
            remitos.setIdTipoComprobante((tiposComprobantesFacade.getTiposComprobantes(parametros)).get(0));

            em.persist(remitos);
            parametros.put("pIdRemito", remitos.getIdRemito());

            RemitosT remitoOk = getRemitosT(parametros).get(0);

            if (detallesRemitosT != null) {
                em.flush();
                int nroRemito = remitos.getIdRemito();
                for (DetalleRemitosT item : detallesRemitosT) {
                    item.getDetalleremitosPK().setIdRemito(nroRemito);
                    item.setRemitos(remitoOk);
                    Detalleremitos detalle = (Detalleremitos) DozerUtil.getDozerMapper(false).map(item, Detalleremitos.class);
                    em.persist(detalle);
                    em.flush();
                }
            }
            return remitoOk;
        }
    }

    /**
     * Actualiza o crea un remitoT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     * 
     * @param remitosT contiene los datos del remito a actualizar
     * @return devuelve el remitoT actualizado
     */
    public RemitosT updateRemitosT(RemitosT remitosT) {
        HashMap parametros = new HashMap();

        Remitos remitos = (Remitos) DozerUtil.getDozerMapper(false).map(remitosT, Remitos.class);

        //si el numero de id es null significa que es nuevo
        if (remitos.getIdRemito() != null) {
            em.merge(remitos);
        } else {
            remitos.setNroRemito(getNextRemito());
            parametros.put("pIdEstados", remitosT.getIdEstado().getIdEstado());
            remitos.setIdEstado((estadosFacade.getEstados(parametros)).get(0));
            parametros = new HashMap();
            parametros.put("pIdTipoComprobante", remitosT.getIdTipoComprobante().getIdTipoComprobante());
            remitos.setIdTipoComprobante((tiposComprobantesFacade.getTiposComprobantes(parametros)).get(0));

            em.persist(remitos);
        }
        parametros.put("pIdRemito", remitos.getIdRemito());

        return getRemitosT(parametros).get(0);

    }
}
