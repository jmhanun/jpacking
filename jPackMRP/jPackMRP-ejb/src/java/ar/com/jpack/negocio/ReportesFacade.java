/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.negocio;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author jmhanun
 */
@Stateless
public class ReportesFacade implements ReportesFacadeRemote {

    @Resource(name = "jdbc/remoto.dbjpack")
    private DataSource jdbcRemotedbjPack;
    @PersistenceContext
    private EntityManager em;

    public JasperPrint getReporte(String reporte, HashMap parametros) {
        try {
            if (parametros == null) {
                parametros = new HashMap();
            }
//            InputStream is = this.getClass().getClassLoader().getResourceAsStream("ar/com/jpack/reportes/usuarios.jasper");
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("ar/com/jpack/reportes/" + reporte + ".jasper");
            Connection conn = jdbcRemotedbjPack.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(is, parametros, conn);
            return jp;
        } catch (JRException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
