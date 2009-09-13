/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleremitos;
import ar.com.jpack.persistencia.Detalleremitostemp;
import ar.com.jpack.persistencia.Detrtosingreso;
import ar.com.jpack.persistencia.Remitos;
import ar.com.jpack.persistencia.Remitosingreso;
import ar.com.jpack.transferencia.DetRtosIngresoT;
import ar.com.jpack.transferencia.DetalleRemitosT;
import ar.com.jpack.transferencia.DetalleRemitosTempT;
import ar.com.jpack.transferencia.RemitosIngresoT;
import ar.com.jpack.transferencia.RemitosT;
import ar.com.jpack.util.DozerUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
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

    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;
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
     * <b>pJoinClientes</b> obliga a Joinear con Clientes<br>
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
     * <b>pJoinClientes</b> obliga a Joinear con Clientes<br>
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
        if (parametros.containsKey("pJoinClientes")) {
            remitosCritearia.setFetchMode("idCliente", FetchMode.JOIN);
            if (parametros.containsKey("pIdCliente")) {
                Criteria clienteCriteria = remitosCritearia.createCriteria("idCliente");
                clienteCriteria.add(Restrictions.eq("idCliente", parametros.get("pIdCliente")));
            }
        }
        if (parametros.containsKey("pJoinEstados")) {
            remitosCritearia.setFetchMode("idEstado", FetchMode.JOIN);
            if (parametros.containsKey("pIdEstado")) {
                Criteria clienteCriteria = remitosCritearia.createCriteria("idEstado");
                clienteCriteria.add(Restrictions.eq("idEstado", parametros.get("pIdEstado")));
            }
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
        if (maxID != null) {
            maxID++;
        } else {
            return 1;
        }
        if (maxID < 0) {
            return 1;
        }
        return maxID;
    }

    /**
     * Obtiene el siguiente numero de instancia del detalleRemtioTemp
     * @return devuelve el siguiente numero de instancia del detalleRemtioTemp como int
     */
    public int getNextInstancia() {
        String hql = "select max(d.detalleremitostempPK.instancia) from Detalleremitostemp d";

        Integer maxID = (Integer) ((EntityManagerImpl) em.getDelegate()).getSession().createQuery(hql).uniqueResult();

        if (maxID != null) {
            maxID++;
        } else {
            return 1;
        }
        if (maxID < 0) {
            return 1;
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

//    /**
//     * Actualiza o crea un remitoT recibido por parametro
//     * Si existe, se actualiza. Si no existe, se crea.
//     * 
//     * @param remitosT contiene los datos del remito a actualizar
//     * @return devuelve el remitoT actualizado
//     */
//    public RemitosT updateRemitosT(RemitosT remitosT) {
//        HashMap parametros = new HashMap();
//
//        Remitos remitos = (Remitos) DozerUtil.getDozerMapper(false).map(remitosT, Remitos.class);
//
//        //si el numero de id es null significa que es nuevo
//        if (remitos.getIdRemito() != null) {
//            em.merge(remitos);
//        } else {
//            remitos.setNroRemito(getNextRemito());
//            parametros.put("pIdEstados", remitosT.getIdEstado().getIdEstado());
//            remitos.setIdEstado((estadosFacade.getEstados(parametros)).get(0));
//            parametros = new HashMap();
//            parametros.put("pIdTipoComprobante", remitosT.getIdTipoComprobante().getIdTipoComprobante());
//            remitos.setIdTipoComprobante((tiposComprobantesFacade.getTiposComprobantes(parametros)).get(0));
//
//            em.persist(remitos);
//        }
//        parametros.put("pIdRemito", remitos.getIdRemito());
//
//        return getRemitosT(parametros).get(0);
//
//    }
    public Date updateRemitosTempT(List<DetalleRemitosTempT> detalleRemitosTempT) {
        Date fechaAcordada = null;
        for (DetalleRemitosTempT itemT : detalleRemitosTempT) {
            Detalleremitostemp item = (Detalleremitostemp) DozerUtil.getDozerMapper(false).map(itemT, Detalleremitostemp.class);
            em.persist(item);
        }
        em.flush();
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();

            CallableStatement cs = conn.prepareCall("{call spfechaacordada(?, ?)}");

            //set inputs
            cs.setInt(1, detalleRemitosTempT.get(0).getDetalleremitostempPK().getInstancia());
            //set outputs
            cs.registerOutParameter(2, java.sql.Types.TIMESTAMP);
            // execute
            cs.executeQuery();
            // display returned values
            fechaAcordada = new Date(cs.getTimestamp(2).getTime());
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(RemitosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fechaAcordada;
    }

    public RemitosIngresoT updateRemitosIngresosT(RemitosIngresoT remitosT, ArrayList<DetRtosIngresoT> detallesRemitosT) {

        HashMap parametros = new HashMap();
        Remitosingreso remitos = (Remitosingreso) DozerUtil.getDozerMapper(false).map(remitosT, Remitosingreso.class);

        //si el numero de id es null significa que es nuevo
        if (remitos.getIdRtoIngreso() != null) {
            em.merge(remitos);
            parametros.put("pIdRemito", remitos.getIdRtoIngreso());

            return getRemitosIngresosT(parametros).get(0);
        } else {
            //remitos.setNroRemito(getNextRemitoIngreso());
            parametros.put("pIdEstados", remitosT.getIdEstado().getIdEstado());
            remitos.setIdEstado((estadosFacade.getEstados(parametros)).get(0));
            parametros = new HashMap();
            parametros.put("pIdTipoComprobante", remitosT.getIdTipoComprobante().getIdTipoComprobante());
            remitos.setIdTipoComprobante((tiposComprobantesFacade.getTiposComprobantes(parametros)).get(0));

            em.persist(remitos);
            parametros.put("pIdRemito", remitos.getIdRtoIngreso());

            RemitosIngresoT remitoOk = getRemitosIngresosT(parametros).get(0);

            if (detallesRemitosT != null) {
                em.flush();
                int nroRemito = remitos.getIdRtoIngreso();
                for (DetRtosIngresoT item : detallesRemitosT) {
                    item.getDetrtosingresoPK().setIdRtoIngreso(nroRemito);
                    item.setRemitosingreso(remitoOk);
                    Detrtosingreso detalle = (Detrtosingreso) DozerUtil.getDozerMapper(false).map(item, Detrtosingreso.class);
                    em.persist(detalle);
                    em.flush();
                }
            }
            return remitoOk;
        }
    }

    public List<RemitosIngresoT> getRemitosIngresosT(HashMap parametros) {
        List<Remitosingreso> remitosList = getRemitosIngresos(parametros);
        List<RemitosIngresoT> remitosTList = new ArrayList();
        for (Remitosingreso c : remitosList) {
            RemitosIngresoT rdo = (RemitosIngresoT) DozerUtil.getDozerMapper(false).map(c, RemitosIngresoT.class);
            remitosTList.add(rdo);
        }
        return remitosTList;
    }

    public List<Remitosingreso> getRemitosIngresos(HashMap parametros) {
        Criteria remitosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Remitosingreso.class);
        List<Remitosingreso> remitosList;
        if (parametros.containsKey("pIdRemito")) {
            remitosCritearia.add(Restrictions.eq("idRtoIngreso", parametros.get("pIdRemito")));
        }
        if (parametros.containsKey("pJoinDetalleRemitosIngresos")) {
            remitosCritearia.setFetchMode("detrtosingresoCollection", FetchMode.JOIN);
        }
        remitosList = remitosCritearia.list();
        return remitosList;
    }

    public int getNextRemitoIngreso() {
        String hql = "select max(r.nroRemito) from Remitosingreso r";
        Integer maxID = (Integer) ((EntityManagerImpl) em.getDelegate()).getSession().createQuery(hql).uniqueResult();
        if (maxID != null) {
            maxID++;
        } else {
            return 1;
        }
        if (maxID < 0) {
            return 1;
        }
        return maxID;
    }

    public Integer insertFacturaT(Integer idRemito) {
        Integer resultado = null;
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();
//`spfacturar`(pidremito integer, out vnrofactura integer)
            CallableStatement cs = conn.prepareCall("{call spfacturar(?, ?)}");

            //set inputs
            cs.setInt(1, idRemito);
            //set outputs
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            // execute
            cs.executeQuery();
            // display returned values
            resultado = new Integer(cs.getInt(2));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeriadosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
