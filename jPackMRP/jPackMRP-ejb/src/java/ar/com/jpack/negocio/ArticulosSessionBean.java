/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import ar.com.jpack.persistencia.Articulos;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.helper.DataTransferHelper;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pablo
 */
@Stateless
public class ArticulosSessionBean implements ArticulosSessionRemote {

    @PersistenceContext
    private EntityManager em;

    public List<ArticulosT> obtenerArticulos() {
        Collection articulos = null;
        articulos = em.createNamedQuery("Articulos.obtenerArticulos").getResultList();
        if (articulos.isEmpty()) {
            return null;
        } else {
            return DataTransferHelper.copiarArticulosALista(articulos);
        }
    }

    public void agregarArticulo(ArticulosT oArticulo) {
        Articulos articulo = new Articulos(oArticulo.getIdArticulo(), 
                oArticulo.getCodigo(),
                oArticulo.getDescripcion(),
                oArticulo.getEstado(),
                oArticulo.getStock(),
                oArticulo.getStockMinimo(),
                oArticulo.getLeadTime());
        em.persist(articulo);
        em.flush();
    }

    public void editarArticulo(ArticulosT oArticulo) {
        Articulos articulo = em.find(Articulos.class, oArticulo.getIdArticulo());
        articulo.setCodigo(oArticulo.getCodigo());
        articulo.setDescripcion(oArticulo.getDescripcion());
        articulo.setEstado(oArticulo.getEstado());
        articulo.setStock(oArticulo.getStock());
        articulo.setStockMinimo(oArticulo.getStockMinimo());
        articulo.setLeadTime(oArticulo.getLeadTime());
        em.merge(articulo);
        em.flush();
    }
    
    /*
         public void editarRol(RolesT oRol) {
        
    }
     */
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")

    /*
    public void create(Articulos articulos) {
        em.persist(articulos);
    }

    public void edit(Articulos articulos) {
        em.merge(articulos);
    }

    public void remove(Articulos articulos) {
        em.remove(em.merge(articulos));
    }

    public Articulos find(Object id) {
        return em.find(ar.com.jpack.persistencia.Articulos.class, id);
    }

    public List findAll() {
        return em.createQuery("select object(o) from Articulos as o").getResultList();
    }
     */
}
