/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Sellos;
import ar.com.jpack.transferencia.SellosT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
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
public class SellosFacade implements SellosFacadeRemote {
    @PersistenceContext
    private EntityManager em;

   /**
     * Obtiene la lista de Sellos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdSello</b>  filtra por 'eq' idSello (Integer) <br>
     * <b>pJoinArticulos</b>  obliga a Joinear con Articulos<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * @return devuelve la lista de los Sellos que cumplan con el filtro
     */
    public List<SellosT> getSellosT(HashMap parametros) {
        List<Sellos> sellosList = getSellos(parametros);
        List<SellosT> sellosTList = new ArrayList<SellosT>();

        for (Sellos c : sellosList) {
            SellosT rdo = (SellosT) DozerUtil.getDozerMapper(false).map(c, SellosT.class);
            sellosTList.add(rdo);
        }
        return sellosTList;
    }

   /**
     * Obtiene la lista de Sellos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdSello</b>  filtra por 'eq' idSello (Integer) <br>
     * <b>pJoinArticulos</b>  obliga a Joinear con Articulos<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * @return devuelve la lista de los Sellos que cumplan con el filtro
     */
     public List<Sellos> getSellos(HashMap parametros) {
        Criteria mailsCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Sellos.class);
        List<Sellos> mailsList;
        if (parametros.containsKey("pIdSello")) {
            mailsCritearia.add(Restrictions.eq("idSello", parametros.get("pIdSello")));
        }
        //Con el setFetchMode obliga a que el lazy se ponga las pilas
        if (parametros.containsKey("pJoinArticulos")) {
            mailsCritearia.setFetchMode("idArticulo", FetchMode.JOIN);
        }
        if (parametros.containsKey("pJoinEstados")) {
            mailsCritearia.setFetchMode("idEstado", FetchMode.JOIN);
        }
        mailsList = mailsCritearia.list();
        return mailsList;
    }
}
