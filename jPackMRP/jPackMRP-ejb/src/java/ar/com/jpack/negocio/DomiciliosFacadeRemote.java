/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Domicilios;
import ar.com.jpack.persistencia.Localidades;
import ar.com.jpack.persistencia.Provincias;
import ar.com.jpack.transferencia.DomiciliosT;
import ar.com.jpack.transferencia.LocalidadesT;
import ar.com.jpack.transferencia.ProvinciasT;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jmhanun
 */
@Remote
public interface DomiciliosFacadeRemote {

    public List<DomiciliosT> getDomiciliosT(HashMap parametros);

    public List<Domicilios> getDomicilios(HashMap parametros);

    public List<LocalidadesT> getLocalidadesT(HashMap parametros);

    public List<Localidades> getLocalidades(HashMap parametros);

    public List<ProvinciasT> getProvinciasT(HashMap parametros);

    public List<Provincias> getProvincias(HashMap parametros);
}
