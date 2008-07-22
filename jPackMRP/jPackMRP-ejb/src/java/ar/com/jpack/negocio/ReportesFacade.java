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

    public JasperPrint getReporteUsuarios() {
//        try {
//            String reportName = "ar/com/jpack/reportes/usuarios.jasper";
//            InputStream is = this.getClass().getClassLoader().getResourceAsStream(reportName);
////            Class.forName("org.gjt.mm.mysql.Driver");
//
//            Connection conn = jdbcRemotedbjPack.getConnection();
//            JasperPrint jasperPrint = JasperFillManager.fillReport(is, new HashMap(), conn);
////            JasperViewer jv = new JasperViewer(jasperPrint);
////            jv.setVisible(true);
//            return jasperPrint;
////        } catch (ClassNotFoundException ex) {
////            return null;
//        } catch (JRException ex) {
//            return null;
//        } catch (SQLException ex) {
//            return null;
//        }
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("ar/com/jpack/reportes/usuarios.jasper");
//            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = jdbcRemotedbjPack.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(is, new HashMap(), conn);
            return jp;


        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
