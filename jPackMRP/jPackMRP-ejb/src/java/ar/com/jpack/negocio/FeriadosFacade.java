/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Feriados;
import ar.com.jpack.transferencia.FeriadosT;
import ar.com.jpack.util.DozerUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

/**
 *
 * @author Pablo
 */
@Stateless
public class FeriadosFacade implements FeriadosFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;

    public List<FeriadosT> getFeriadosT(HashMap parametros) {

        List<Feriados> feriadosList = getFeriados(parametros);
        List<FeriadosT> feriadosTList = new ArrayList();
        for (Feriados c : feriadosList) {
            FeriadosT rdo = (FeriadosT) DozerUtil.getDozerMapper(false).map(c, FeriadosT.class);
            feriadosTList.add(rdo);
        }
        return feriadosTList;
    }

    public List<Feriados> getFeriados(HashMap parametros) {

        Criteria feriadosCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Feriados.class);
        List<Feriados> feriadosList;
        if (parametros.containsKey("pIdFeriado")) {
            feriadosCriteria.add(Restrictions.eq("idFeriado", parametros.get("pIdFeriado")));
        }
        if (parametros.containsKey("pFecha")) {
            feriadosCriteria.add(Restrictions.like("fecha", parametros.get("pFecha").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pMotivo")) {
            feriadosCriteria.add(Restrictions.like("motivo", parametros.get("pMotivo").toString(), MatchMode.ANYWHERE));
        }
        feriadosList = feriadosCriteria.list();
        return feriadosList;
    }

    public FeriadosT updateFeriadosT(FeriadosT feriadosT) {
        Feriados feriados = (Feriados) DozerUtil.getDozerMapper(false).map(feriadosT, Feriados.class);

        //si el numero de id es null significa que es nuevo
        if (feriados.getIdFeriado()!= null) {
            em.merge(feriados);
        } else {
            em.persist(feriados);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdFeriado", feriados.getIdFeriado());
        return getFeriadosT(parametros).get(0);

    }

    public Integer deleteFeriadoT(Integer idFeriado) {
        Integer resultado = null;
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();

            CallableStatement cs = conn.prepareCall("{call spabmferiados(?, ?)}");

            //set inputs
            cs.setInt(1, idFeriado);
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
