/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Setup;
import ar.com.jpack.transferencia.SetupT;
import org.hibernate.criterion.MatchMode;
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
public class SetupFacade implements SetupFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;

    /**
     * Obtiene la lista de Setup filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdSetup</b>           filtra por 'eq' idRol (Integer) <br>
     * <b>pDescripcion</b>       filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pValor</b>             filtra por 'like AnyWhere' valor (String) <br>
     * <b>pFechaModificacion</b> filtra por 'eq' fechaModificacion (Date) <br>
     * <b>pJoinUsuarios</b>      obliga a Joinear con Usuarios <br>
     * <b>pIdUsuario</b>         filtra por 'eq' idUsuario (Integer) (debe haber sido joineado con Estado) <br>
     * @return devuelve la lista de los Setup que cumplan con el filtro
     */
    public List<SetupT> getSetupT(HashMap parametros) {
        List<Setup> setupList = getSetup(parametros);
        List<SetupT> setupTList = new ArrayList();
        for (Setup c : setupList) {
            SetupT rdo = (SetupT) DozerUtil.getDozerMapper(false).map(c, SetupT.class);
            setupTList.add(rdo);
        }
        return setupTList;
    }

    /**
     * Obtiene la lista de Setup filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdSetup</b>           filtra por 'eq' idRol (Integer) <br>
     * <b>pDescripcion</b>       filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pValor</b>             filtra por 'like AnyWhere' valor (String) <br>
     * <b>pFechaModificacion</b> filtra por 'eq' fechaModificacion (Date) <br>
     * <b>pJoinUsuarios</b>      obliga a Joinear con Usuarios <br>
     * <b>pIdUsuario</b>         filtra por 'eq' idUsuario (Integer) (debe haber sido joineado con Estado) <br>
     * @return devuelve la lista de los Setup que cumplan con el filtro
     */
    public List<Setup> getSetup(HashMap parametros) {
        Criteria setupCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Setup.class);
        List<Setup> setupList;
        if (parametros.containsKey("pIdSetup")) {
            setupCritearia.add(Restrictions.eq("idSetup", parametros.get("pIdSetup")));
        }
        if (parametros.containsKey("pDescripcion")) {
            setupCritearia.add(Restrictions.like("descripcion", parametros.get("pDescripcion").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pValor")) {
            setupCritearia.add(Restrictions.like("valor", parametros.get("pValor").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pFechaModificacion")) {
            setupCritearia.add(Restrictions.eq("fechaModificacion", parametros.get("pFechaModificacion").toString()));
        }
        if (parametros.containsKey("pJoinUsuarios")) {
            setupCritearia.setFetchMode("idUsuario", FetchMode.JOIN);
            if (parametros.containsKey("pIdUsuario")) {
                //Con esto filtro por el objeto que estaba lazy
                Criteria usuarioCriteria = setupCritearia.createCriteria("idUsuario");
                usuarioCriteria.add(Restrictions.eq("idUsuario", parametros.get("pIdUsuario")));
            }
        }
        setupList = setupCritearia.list();
        return setupList;
    }

    public String getValorSetup(Integer idSetup) {
        String valor = null;
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();
//`spsetup`(pidsetup INTEGER, out vvalor VARCHAR(50))
            CallableStatement cs = conn.prepareCall("{call spsetup(?, ?)}");

            //set inputs
            cs.setInt(1, idSetup);
            //set outputs
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            // execute
            cs.executeQuery();
            // display returned values
            valor = new String(cs.getString(2));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SetupFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    public SetupT updateSetupT(SetupT dto) {
        Setup setup = (Setup) DozerUtil.getDozerMapper(false).map(dto, Setup.class);
        //si el numero de id es null significa que es nuevo
        if (setup.getIdSetup() != null) {
            em.merge(setup);
        } else {
            em.persist(setup);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdSetup", setup.getIdSetup());
        return getSetupT(parametros).get(0);
    }
 
}
