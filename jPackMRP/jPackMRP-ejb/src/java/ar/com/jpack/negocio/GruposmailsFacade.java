/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Gruposmails;
import ar.com.jpack.transferencia.GruposMailsT;
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
public class GruposmailsFacade implements GruposmailsFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;

    /**
     * Obtiene la lista de GruposMails filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdGrupoMail</b>    filtra por 'eq' idGrupoMail (Integer) <br>
     * @return devuelve la lista de los GruposMails que cumplan con el filtro
     */
    public List<GruposMailsT> getGruposMailsT(HashMap parametros) {
        List<Gruposmails> gruposMailsList = getGruposMails(parametros);
        List<GruposMailsT> gruposMailsTList = new ArrayList<GruposMailsT>();

        for (Gruposmails c : gruposMailsList) {
            GruposMailsT rdo = (GruposMailsT) DozerUtil.getDozerMapper(false).map(c, GruposMailsT.class);
            gruposMailsTList.add(rdo);
        }
        return gruposMailsTList;
    }

    /**
     * Obtiene la lista de GruposMails filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdGrupoMail</b>    filtra por 'eq' idGrupoMail (Integer) <br>
     * @return devuelve la lista de los GruposMails que cumplan con el filtro
     */
    public List<Gruposmails> getGruposMails(HashMap parametros) {
        Criteria gruposMailsCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Gruposmails.class);
        List<Gruposmails> gruposMailsList;
        if (parametros.containsKey("pIdGrupoMail")) {
            gruposMailsCriteria.add(Restrictions.eq("idGrupoMail", parametros.get("pIdGrupoMail")));
        }
        gruposMailsList = gruposMailsCriteria.list();
        return gruposMailsList;
    }

    public Integer deleteGruposMailsT(Integer idGruposMails) {
        Integer resultado = null;
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();

            CallableStatement cs = conn.prepareCall("{call spabmgruposmails(?, ?)}");

            //set inputs
            cs.setInt(1, idGruposMails);
            //set outputs
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            // execute
            cs.executeQuery();
            // display returned values
            resultado = new Integer(cs.getInt(2));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GruposmailsFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
