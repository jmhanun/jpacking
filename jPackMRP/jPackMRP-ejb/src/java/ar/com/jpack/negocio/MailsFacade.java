/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Mails;
import ar.com.jpack.transferencia.MailsT;
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
 * @author jmhanun
 */
@Stateless
public class MailsFacade implements MailsFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtiene la lista de Mails filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdUsuario</b>  filtra por 'eq' idUsuario (Integer) <br>
     * <b>pIdGrupoMail</b>    filtra por 'eq' idGrupoMail (Integer) <br>
     * <b>pGrupoMail</b> filtra por 'like AnyWhere' grupoMail (String) <br>
     * <b>pJoinGruposMails</b>  obliga a Joinear con GruposMails<br>
     * <b>pJoinUsuarios</b>  obliga a Joinear con Usuarios<br>
     * @return devuelve la lista de los Mails que cumplan con el filtro
     */
    public List<MailsT> getMailsT(HashMap parametros) {
        List<Mails> mailsList = getMails(parametros);
        List<MailsT> mailsTList = new ArrayList<MailsT>();

        for (Mails c : mailsList) {
            MailsT rdo = (MailsT) DozerUtil.getDozerMapper(false).map(c, MailsT.class);
            mailsTList.add(rdo);
        }
        return mailsTList;
    }

    /**
     * Obtiene la lista de Mails filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdUsuario</b>  filtra por 'eq' idUsuario (Integer) <br>
     * <b>pIdGrupoMail</b>    filtra por 'eq' idGrupoMail (Integer) <br>
     * <b>pGrupoMail</b> filtra por 'like AnyWhere' grupoMail (String) <br>
     * <b>pJoinGruposMails</b>  obliga a Joinear con GruposMails<br>
     * <b>pJoinUsuarios</b>  obliga a Joinear con Usuarios<br>
     * @return devuelve la lista de los Mails que cumplan con el filtro
     */
    public List<Mails> getMails(HashMap parametros) {
        Criteria mailsCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Mails.class);
        List<Mails> mailsList;
        if (parametros.containsKey("pIdMail")) {
            mailsCritearia.add(Restrictions.eq("idMail", parametros.get("pIdMail")));
        }
        //Con el setFetchMode obliga a que el lazy se ponga las pilas
        if (parametros.containsKey("pJoinGruposMails")) {
            mailsCritearia.setFetchMode("idGrupoMail", FetchMode.JOIN);
            if (parametros.containsKey("pIdGrupoMail")) {
                //Con esto filtro por el objeto que estaba lazy
                Criteria grupoMailCriteria = mailsCritearia.createCriteria("idGrupoMail");
                grupoMailCriteria.add(Restrictions.eq("idGrupoMail", parametros.get("pIdGrupoMail")));
            }
            if (parametros.containsKey("pGrupoMail")) {
                //Con esto filtro por el objeto que estaba lazy
                Criteria grupoMailCriteria = mailsCritearia.createCriteria("idGrupoMail");
                grupoMailCriteria.add(Restrictions.eq("grupoMail", parametros.get("pGrupoMail")));
            }
        }
        if (parametros.containsKey("pJoinUsuarios")) {
            mailsCritearia.setFetchMode("idUsuario", FetchMode.JOIN);
            if (parametros.containsKey("pIdUsuario")) {
                //Con esto filtro por el objeto que estaba lazy
                Criteria grupoMailCriteria = mailsCritearia.createCriteria("idUsuario");
                grupoMailCriteria.add(Restrictions.eq("idUsuario", parametros.get("pIdUsuario")));
            }
        }
        mailsList = mailsCritearia.list();
        return mailsList;
    }
}
