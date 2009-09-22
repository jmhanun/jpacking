/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Domicilios;
import ar.com.jpack.persistencia.Estados;
import ar.com.jpack.persistencia.Localidades;
import ar.com.jpack.persistencia.Paises;
import ar.com.jpack.persistencia.Provincias;
import ar.com.jpack.transferencia.DomiciliosT;
import ar.com.jpack.transferencia.LocalidadesT;
import ar.com.jpack.transferencia.ProvinciasT;
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
public class DomiciliosFacade implements DomiciliosFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public List<DomiciliosT> getDomiciliosT(HashMap parametros) {
        List<Domicilios> domiciliosList = getDomicilios(parametros);
        List<DomiciliosT> domiciliosTList = new ArrayList<DomiciliosT>();

        for (Domicilios c : domiciliosList) {
            DomiciliosT rdo = (DomiciliosT) DozerUtil.getDozerMapper(false).map(c, DomiciliosT.class);
            domiciliosTList.add(rdo);
        }
        return domiciliosTList;
    }

    public List<Domicilios> getDomicilios(HashMap parametros) {
        Criteria domiciliosCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Domicilios.class);
        List<Domicilios> domiciliosList;
        if (parametros.containsKey("pIdDomicilio")) {
            domiciliosCritearia.add(Restrictions.eq("idDomicilio", parametros.get("pIdDomicilio")));
        }
        if (parametros.containsKey("pIdCliente")) {
            domiciliosCritearia.setFetchMode("idCliente", FetchMode.JOIN);
            Criteria clientesCriteria = domiciliosCritearia.createCriteria("idCliente");
            clientesCriteria.add(Restrictions.eq("idCliente", parametros.get("pIdCliente")));
        }
        if (parametros.containsKey("pIdProveedor")) {
            domiciliosCritearia.setFetchMode("idProveedor", FetchMode.JOIN);
            Criteria proveedorCriteria = domiciliosCritearia.createCriteria("idProveedor");
            proveedorCriteria.add(Restrictions.eq("idProveedor", parametros.get("pIdProveedor")));
        }

        domiciliosCritearia.setFetchMode("idLocalidad", FetchMode.JOIN);
        Criteria localidadCriteria = domiciliosCritearia.createCriteria("idLocalidad");
        localidadCriteria.setFetchMode("idProvincia", FetchMode.JOIN);


        domiciliosList = domiciliosCritearia.list();
        return domiciliosList;
    }

    public List<LocalidadesT> getLocalidadesT(HashMap parametros) {
        List<Localidades> localidadesList = getLocalidades(parametros);
        List<LocalidadesT> localidadesTList = new ArrayList<LocalidadesT>();

        for (Localidades c : localidadesList) {
            LocalidadesT rdo = (LocalidadesT) DozerUtil.getDozerMapper(false).map(c, LocalidadesT.class);
            localidadesTList.add(rdo);
        }
        return localidadesTList;
    }

    public List<Localidades> getLocalidades(HashMap parametros) {
        Criteria localidadesCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Localidades.class);
        List<Localidades> localidadesList;
        if (parametros.containsKey("pIdLocalidad")) {
            localidadesCritearia.add(Restrictions.eq("idLocalidad", parametros.get("pIdLocalidad")));
        }
        if (parametros.containsKey("pIdProvincia")) {
            localidadesCritearia.setFetchMode("idProvincia", FetchMode.JOIN);
            Criteria provinciasCriteria = localidadesCritearia.createCriteria("idProvincia");
            provinciasCriteria.add(Restrictions.eq("idProvincia", parametros.get("pIdProvincia")));
        }


        localidadesList = localidadesCritearia.list();
        return localidadesList;
    }

    public List<ProvinciasT> getProvinciasT(HashMap parametros) {
        List<Provincias> provinciasList = getProvincias(parametros);
        List<ProvinciasT> provinciasTList = new ArrayList<ProvinciasT>();

        for (Provincias c : provinciasList) {
            ProvinciasT rdo = (ProvinciasT) DozerUtil.getDozerMapper(false).map(c, ProvinciasT.class);
            provinciasTList.add(rdo);
        }
        return provinciasTList;
    }

    public List<Provincias> getProvincias(HashMap parametros) {
        Criteria provinciasCritearia = ((EntityManagerImpl) em.getDelegate()).getSession().createCriteria(Provincias.class);
        List<Provincias> provinciasList;
        if (parametros.containsKey("pIdProvincia")) {
            provinciasCritearia.add(Restrictions.eq("idProvincia", parametros.get("pIdProvincia")));
        }

        provinciasList = provinciasCritearia.list();
        return provinciasList;
    }

    public DomiciliosT updateDomicilioT(DomiciliosT dto) {
        Domicilios domicilio = (Domicilios) DozerUtil.getDozerMapper(false).map(dto, Domicilios.class);
        Paises pais = em.find(Paises.class, 1);
        domicilio.getIdLocalidad().getIdProvincia().setIdPais(pais);
        Estados estado = em.find(Estados.class, 18);
        domicilio.setIdEstado(estado);

        //si el numero de id es null significa que es nuevo
        if (domicilio.getIdDomicilio() != null) {
            em.merge(domicilio);
        } else {
            em.persist(domicilio);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdDomicilio", domicilio.getIdDomicilio());
        return getDomiciliosT(parametros).get(0);
    }

    public ProvinciasT updateProvinciasT(ProvinciasT dto) {
        Provincias provincias = (Provincias) DozerUtil.getDozerMapper(false).map(dto, Provincias.class);

        //si el numero de id es null significa que es nuevo
        if (provincias.getIdProvincia()!= null) {
            em.merge(provincias);
        } else {
            Paises pais = em.find(Paises.class, 1);
            provincias.setIdPais(pais);
            em.persist(provincias);
        }
        HashMap parametros = new HashMap();
        parametros.put("pIdProvincia", provincias.getIdProvincia());
        return getProvinciasT(parametros).get(0);

    }
}
