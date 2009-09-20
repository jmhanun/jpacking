/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposdesvios;
import ar.com.jpack.transferencia.TiposDesviosT;
import org.hibernate.criterion.MatchMode;
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
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author Pablo
 */
@Stateless
public class TiposdesviosFacade implements TiposdesviosFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;

    public List<TiposDesviosT> getTiposDesviosT(HashMap parametros) {

        List<Tiposdesvios> tiposDesviosList = getTiposDesvios(parametros);
        List<TiposDesviosT> tiposDesviosTList = new ArrayList();
        for (Tiposdesvios c : tiposDesviosList) {
            TiposDesviosT rdo = (TiposDesviosT) DozerUtil.getDozerMapper(false).map(c, TiposDesviosT.class);
            tiposDesviosTList.add(rdo);
        }
        return tiposDesviosTList;
    }

    public List<Tiposdesvios> getTiposDesvios(HashMap parametros) {

        Criteria tiposDesviosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Tiposdesvios.class);
        List<Tiposdesvios> tiposDesviosList;
        if (parametros.containsKey("pIdTipoDesvio")) {
            tiposDesviosCritearia.add(Restrictions.eq("idTipoDesvio", parametros.get("pIdTipoDesvio")));
        }
        if (parametros.containsKey("pMotivo")) {
            tiposDesviosCritearia.add(Restrictions.like("motivo", parametros.get("pMotivo").toString(), MatchMode.ANYWHERE));
        }
        tiposDesviosList = tiposDesviosCritearia.list();
        return tiposDesviosList;
    }

    public void insertDesvioT(Integer idDetalleProduccion, Integer idTipoDesvio, String comentario) {
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();
//`spcargadesvio`(piddetalleproduccion integer, pidtipodesvio integer, pcomentarios varchar(100))
            CallableStatement cs = conn.prepareCall("{call spcargadesvio(?, ?, ?)}");

            //set inputs
            cs.setInt(1, idDetalleProduccion);
            cs.setInt(2, idTipoDesvio);
            cs.setString(3, comentario);
            //set outputs
//            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            // execute
            cs.executeQuery();
            // display returned values
//            valor = new String(cs.getString(2));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TiposdesviosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TiposDesviosT updateTiposDesviosT(TiposDesviosT dto) {
        Tiposdesvios tiposdesvios = (Tiposdesvios) DozerUtil.getDozerMapper(false).map(dto, Tiposdesvios.class);

        //si el numero de id es null significa que es nuevo
        if (tiposdesvios.getIdTipoDesvio() != null) {
            em.merge(tiposdesvios);
        } else {         
            em.persist(tiposdesvios);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdTipoDesvio", tiposdesvios.getIdTipoDesvio());
        return getTiposDesviosT(parametros).get(0);
    }

}
