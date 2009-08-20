/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Mails;
import ar.com.jpack.transferencia.MailsT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface MailsFacadeRemote {

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
    public List<MailsT> getMailsT(HashMap parametros);

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
    public List<Mails> getMails(HashMap parametros);


}
