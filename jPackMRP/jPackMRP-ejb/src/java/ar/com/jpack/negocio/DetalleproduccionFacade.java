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

    public List<DetalleProduccionT> getDetalleProduccionT(HashMap parametros) {
        List<Detalleproduccion> detallesList = getDetalleProduccion(parametros);
        List<DetalleProduccionT> detallesTList = new ArrayList();
        for (Detalleproduccion c : detallesList) {
            DetalleProduccionT rdo = (DetalleProduccionT) DozerUtil.getDozerMapper(false).map(c, DetalleProduccionT.class);
            detallesTList.add(rdo);
        }
        return detallesTList;
    }

    public List<Detalleproduccion> getDetalleProduccion(HashMap parametros) {
        Criteria detalleProduccionCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Detalleproduccion.class);







        List<Detalleproduccion> detallesList;

        if (parametros.containsKey("pIdDetalleProduccion")) {
            detalleProduccionCriteria.add(Restrictions.eq("idDetalleProduccion", parametros.get("pIdDetalleProduccion")));
        }


        if (parametros.containsKey("pJoinMaquinas")) {
            detalleProduccionCriteria.setFetchMode("idMaquina", FetchMode.JOIN);
        }
        if (parametros.containsKey("pJoinEstados")) {
            detalleProduccionCriteria.setFetchMode("idEstado", FetchMode.JOIN);
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
}
