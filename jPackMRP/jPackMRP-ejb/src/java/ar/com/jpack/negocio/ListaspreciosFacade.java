/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Listasprecios;
import ar.com.jpack.transferencia.ListasPreciosT;
import ar.com.jpack.transferencia.UsuariosT;
import org.hibernate.FetchMode;
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
public class ListaspreciosFacade implements ListaspreciosFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;

    /**
     * Obtiene la lista de ListasPrecios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdLista</b>  filtra por 'eq' idLista (Integer) <br>
     * @return devuelve la lista de los ListasPrecios que cumplan con el filtro
     */
    public List<ListasPreciosT> getListasPreciosT(HashMap parametros) {
        List<Listasprecios> listasPreciosList = getListasPrecios(parametros);
        List<ListasPreciosT> listasPreciosTList = new ArrayList<ListasPreciosT>();

        for (Listasprecios c : listasPreciosList) {
            ListasPreciosT rdo = (ListasPreciosT) DozerUtil.getDozerMapper(false).map(c, ListasPreciosT.class);
            listasPreciosTList.add(rdo);
        }
        return listasPreciosTList;
    }

    /**
     * Obtiene la lista de ListasPrecios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdLista</b>  filtra por 'eq' idLista (Integer) <br>
     * @return devuelve la lista de los ListasPrecios que cumplan con el filtro
     */
    public List<Listasprecios> getListasPrecios(HashMap parametros) {
        Criteria listasPreciosCriteria = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Listasprecios.class);
        List<Listasprecios> listasPreciosList;
        if (parametros.containsKey("pIdLista")) {
            listasPreciosCriteria.add(Restrictions.eq("idLista", parametros.get("pIdLista")));
        }
        if (parametros.containsKey("pJoinEstados")) {
            listasPreciosCriteria.setFetchMode("idEstado", FetchMode.JOIN);
        }
        if (parametros.containsKey("pJoinUsuarios")) {
            listasPreciosCriteria.setFetchMode("idUsuario", FetchMode.JOIN);
        }
        if (parametros.containsKey("pVigente")) {
            listasPreciosCriteria.add(Restrictions.isNull("fechaHasta"));
        }
        listasPreciosList = listasPreciosCriteria.list();
        return listasPreciosList;
    }

    public void insertarListasPreciosT(Double porcentaje, UsuariosT usuarioLogueado) {
//`splistaprecios`(paumento DOUBLE, pidusuario INTEGER, plistaant INTEGER, out vidlista integer)
        Integer idLista = null;
        HashMap parametros = new HashMap();
        parametros.put("pVigente", true);
        try {
            Connection conn = jdbcRemotedbjPack.getConnection();

            CallableStatement cs = conn.prepareCall("{call splistaprecios(?, ?, ?, ?)}");


            Integer idListaVigente = getListasPrecios(parametros).get(0).getIdLista();

            //set inputs
            cs.setDouble(1, porcentaje);

            cs.setInt(2, usuarioLogueado.getIdUsuario());

            cs.setInt(3, idListaVigente);
            //set outputs
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            // execute
            cs.executeQuery();
            // display returned values
            idLista = new Integer(cs.getInt(4));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeriadosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
