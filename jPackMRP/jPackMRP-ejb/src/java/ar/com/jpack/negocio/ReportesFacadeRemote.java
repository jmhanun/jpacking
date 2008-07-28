/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import java.util.HashMap;
import javax.ejb.Remote;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ReportesFacadeRemote {

    public JasperPrint getReporte(String reporte, HashMap parametros);
    

}
