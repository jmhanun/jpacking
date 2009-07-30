/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Actividades;
import ar.com.jpack.persistencia.Actividadesxarticulos;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import ar.com.jpack.transferencia.ActividadesT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ActividadesFacadeRemote {

    public List<ActividadesArticulosT> getActividadesArticulosT(HashMap parametros);

    public List<ActividadesT> getActividadesT(HashMap parametros);

    public List<Actividades> getActividades(HashMap parametros);

    public List<Actividadesxarticulos> getActividadesArticulos(HashMap parametros);
}