/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Detalleproduccion;
import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.transferencia.DetalleProduccionT;
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
import javax.annotation.Resource;
import javax.ejb.EJB;
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
 * @author Pablo
 */
@Stateless
public class DetalleproduccionFacade implements DetalleproduccionFacadeRemote {

    @EJB
    private EstadosFacadeRemote estadosFacade;
    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;
    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de DetalleProduccion filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdDetalleProduccion</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pFechaInicioEstimadaLT</b>  filtra por 'lt' fechaInicioEstimada (Date) <br>
     * <b>pFechaFinEstimadaGT</b>  filtra por 'gt' pFechaFinEstimada (Date) <br>
     * <b>pFechaInicioEstimada</b>  filtra por 'between' fechaInicioEstimada (Date) <br>
     * <b>pFechaDesdeEstimada</b> ; <b>pFechaHastaEstimada</b> <br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * <b>pJoinMaquinas</b> obliga a Joinear con Maquinas<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pJoinOrdenes</b>  obliga a Joinear con Detalle de Ordenes de Produccion<br>
     * @return devuelve la lista de los DetalleProduccion que cumplan con el filtro
     */
    public List<DetalleProduccionT> getDetalleProduccionT(HashMap parametros) {
        List<Detalleproduccion> detallesList = getDetalleProduccion(parametros);
        List<DetalleProduccionT> detallesTList = new ArrayList();
        for (Detalleproduccion c : detallesList) {
            DetalleProduccionT rdo = (DetalleProduccionT) DozerUtil.getDozerMapper(false).map(c, DetalleProduccionT.class);
            detallesTList.add(rdo);
        }
        return detallesTList;
    }

    /**
     * Obtiene la lista de DetalleProduccion filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdDetalleProduccion</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pFechaInicioEstimadaLT</b>  filtra por 'lt' fechaInicioEstimada (Date) <br>
     * <b>pFechaFinEstimadaGT</b>  filtra por 'gt' pFechaFinEstimada (Date) <br>
     * <b>pFechaInicioEstimada</b>  filtra por 'between' fechaInicioEstimada (Date) <br>
     * <b>pFechaDesdeEstimada</b> ; <b>pFechaHastaEstimada</b> <br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * <b>pJoinMaquinas</b> obliga a Joinear con Maquinas<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pJoinOrdenes</b>  obliga a Joinear con Detalle de Ordenes de Produccion<br>
     * @return devuelve la lista de los DetalleProduccion que cumplan con el filtro
     */
    public List<Detalleproduccion> getDetalleProduccion(HashMap parametros) {
        Criteria detalleProduccionCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Detalleproduccion.class);
        List<Detalleproduccion> detallesList;

        if (parametros.containsKey("pIdDetalleProduccion")) {
            detalleProduccionCriteria.add(Restrictions.eq("idDetalleProduccion", parametros.get("pIdDetalleProduccion")));
        }

        if (parametros.containsKey("pFechaInicioEstimada")) {
            detalleProduccionCriteria.add(Restrictions.between("fechaInicioEstimada", parametros.get("pFechaDesdeEstimada"), parametros.get("pFechaHastaEstimada")));
        }
        if (parametros.containsKey("pFechaInicioEstimadaLT")) {
            detalleProduccionCriteria.add(Restrictions.lt("fechaInicioEstimada", parametros.get("pFechaInicioEstimadaLT")));
        }
        if (parametros.containsKey("pFechaFinEstimadaGT")) {
            detalleProduccionCriteria.add(Restrictions.gt("fechaFinEstimada", parametros.get("pFechaFinEstimadaGT")));
        }
        if (parametros.containsKey("pJoinMaquinas")) {
            detalleProduccionCriteria.setFetchMode("idMaquina", FetchMode.JOIN);
        }
        if (parametros.containsKey("pJoinEstados")) {
            detalleProduccionCriteria.setFetchMode("idEstado", FetchMode.JOIN);
            if (parametros.containsKey("pIdEstado")) {
                //Con esto filtro por el objeto que estaba lazy
                Criteria estadoCriteria = detalleProduccionCriteria.createCriteria("idEstado");
                estadoCriteria.add(Restrictions.eq("idEstado", parametros.get("pIdEstado")));
            }
        }
        if (parametros.containsKey("pJoinOrdenes")) {
            detalleProduccionCriteria.setFetchMode("detordenesproduccion", FetchMode.JOIN);
        }

        detallesList = detalleProduccionCriteria.list();

        return detallesList;
    }

    public Double getAvanceProduccion(DetalleProduccionT detalleProduccionT) {
        Double avance = null;
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();

            CallableStatement cs = conn.prepareCall("{call spavance(?, ?)}");

            //set inputs
            cs.setInt(1, detalleProduccionT.getIdDetalleProduccion());
            //set outputs
            cs.registerOutParameter(2, java.sql.Types.DOUBLE);
            // execute
            cs.executeQuery();
            // display returned values
            avance = new Double(cs.getDouble(2));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DetalleproduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avance;
    }

    public void updateDetalleProduccion(DetalleProduccionT detalleProduccionT) {


        Detalleproduccion detalleProduccion = (Detalleproduccion) DozerUtil.getDozerMapper(false).map(detalleProduccionT, Detalleproduccion.class);

        HashMap parametros = new HashMap();
        parametros.put("pIdEstados", detalleProduccionT.getIdEstado().getIdEstado());
        Estados estado = (Estados) estadosFacade.getEstados(parametros).get(0);

        detalleProduccion.setIdEstado(estado);

        em.merge(detalleProduccion);

    }

    public Boolean getFeriado(Date fecha) {
        Boolean feriado = null;

        try {
            Connection conn = jdbcRemotedbjPack.getConnection();

            CallableStatement cs = conn.prepareCall("{call spferiado(?, ?)}");

            //set inputs
            java.sql.Date f = new java.sql.Date(fecha.getTime());
            cs.setDate(1, f);
            //set outputs
            cs.registerOutParameter(2, java.sql.Types.BOOLEAN);
            // execute
            cs.executeQuery();
            // display returned values
            feriado = new Boolean(cs.getBoolean(2));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DetalleproduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feriado;
    }
}
