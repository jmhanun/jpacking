/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.negocio;

import javax.ejb.Remote;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jmhanun
 */
@Remote
public interface ReportesFacadeRemote {

    public JasperPrint getReporteUsuarios();
    

}
