/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Gruposmails;
import ar.com.jpack.transferencia.GruposMailsT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pablo
 */
@Remote
public interface GruposmailsFacadeRemote {

    /**
     * Obtiene la lista de GruposMails filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdGrupoMail</b>    filtra por 'eq' idGrupoMail (Integer) <br>
     * @return devuelve la lista de los GruposMails que cumplan con el filtro
     */
    public List<GruposMailsT> getGruposMailsT(HashMap parametros);

    /**
     * Obtiene la lista de GruposMails filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdGrupoMail</b>    filtra por 'eq' idGrupoMail (Integer) <br>
     * @return devuelve la lista de los GruposMails que cumplan con el filtro
     */
    public List<Gruposmails> getGruposMails(HashMap parametros);

    public java.lang.Integer deleteGruposMailsT(java.lang.Integer idGruposMails);

    public GruposMailsT updateGruposMailsT(GruposMailsT dto);

}
