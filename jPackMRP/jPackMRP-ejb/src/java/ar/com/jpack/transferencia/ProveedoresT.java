/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class ProveedoresT implements Serializable{
    private Integer idProveedor;
    private String nombres;
    private String apellido;
    private String mails;
    private String telefonos;
    private Date fechaAlta;
    private String observaciones;
    private Collection<FacturasComprasT> facturascomprasCollection;
    private Collection<DomiciliosT> domiciliosCollection;
    private Collection<RemitosIngresoT> remitosingresoCollection;
    private Collection<NotasCreditoT> notascreditoCollection;
    private EstadosT idEstado;
    private TiposDocumentoT idTipoDocumento;
}
