/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Tiposdocumento;
import ar.com.jpack.transferencia.TiposDocumentoT;
import ar.com.jpack.util.DozerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;

/**
 *
 * @author jmhanun
 */
@Stateless
public class TiposDocumentoFacade implements TiposDocumentoFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<TiposDocumentoT> getTiposDocumentoT(HashMap parametros) {
        Criteria tiposDocumentoCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Tiposdocumento.class);
        List<Tiposdocumento> tiposDocumentoList;
        List<TiposDocumentoT> tiposDocumentoTList = new ArrayList();
        if (parametros.containsKey("pIdTipoDocumento")) {
            tiposDocumentoCritearia.add(Restrictions.eq("idTipoDocumento", parametros.get("pIdTipoDocumento")));
        }
        if (parametros.containsKey("pDescripcion")) {
            tiposDocumentoCritearia.add(Restrictions.like("descripcion", parametros.get("pDescripcion").toString(), MatchMode.ANYWHERE));
        }
        if (parametros.containsKey("pAbreviatura")) {
            tiposDocumentoCritearia.add(Restrictions.like("abreviatura", parametros.get("pAbreviatura").toString(), MatchMode.ANYWHERE));
        }

        tiposDocumentoList = tiposDocumentoCritearia.list();
        for (Tiposdocumento c : tiposDocumentoList) {
            TiposDocumentoT rdo = (TiposDocumentoT) DozerUtil.getDozerMapper(false).map(c, TiposDocumentoT.class);
            tiposDocumentoTList.add(rdo);
        }
        return tiposDocumentoTList;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
 
}
