/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReporteDesvioFin.java
 *
 * Created on 11-ago-2009, 16:29:00
 */
package ar.com.jpack.desktop.reportes;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.DesktopView;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.transferencia.MaquinasT;
import java.beans.PropertyVetoException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author Pablo
 */
public class ReporteDesvioFin extends CustomInternalFrame {

    /** Creates new form ReporteDesvioFin */
    public ReporteDesvioFin() {
        initComponents();
        //Inicio modificacion combo maquinas
        HashMap parametros = new HashMap();
        ArrayList<MaquinasT> maquinasTs = (ArrayList<MaquinasT>) DesktopApp.getApplication().getMaquinasT(parametros);


        DefaultComboBoxModel maquinasComboBoxModel = new DefaultComboBoxModel();
        maquinasComboBoxModel.addElement("<Todos>");
        for (MaquinasT maquina : maquinasTs) {
            maquinasComboBoxModel.addElement(maquina);
        }
        cboMaquinas.setModel(maquinasComboBoxModel);
        getRootPane().setDefaultButton(aceptarButton);
    //Fin modificaciones combo maquinas

    }

    @Action
    public Task aceptar() {
        return new Reporte(DesktopApp.getApplication(), "Reporte iniciado");
    }

    class Reporte extends Task<String, Void> {

        String mensaje;
        DesktopView view;

        public Reporte(DesktopApp application, String mensaje) {
            super(application);
            this.mensaje = mensaje;
            this.view = application.getDesktopView();
        }

        @Override
        protected String doInBackground() throws Exception {
            view.setStatusMessage(mensaje);
            HashMap parametro = new HashMap();
            //Inicio modificacion combo maquinas
            if (!cboMaquinas.getSelectedItem().equals("<Todos>")) {
                parametro.put("pmaquina", ((MaquinasT) cboMaquinas.getSelectedItem()).getIdMaquina());
            }
//            if (!txtMaquina.getText().isEmpty()) {
//                parametro.put("pmaquina", new Integer(txtMaquina.getText()));
//            }
            //Fin modificaciones combo maquinas

            parametro.put("pdesvio", cmbDesvio.getSelectedItem());

            //INICIO MODIFICACION DE HORAS!!!! --Nuevo
            //Creo variables auxiliares para manipular fecha y hora
            GregorianCalendar gcd = new GregorianCalendar();
            GregorianCalendar gch = new GregorianCalendar();

            //Inicializo las variables auxiliares
            gcd.setTime(jDateChooser1.getDate());
            gch.setTime(jDateChooser2.getDate());

            //Seteo del inicio de la fecha desde
            gcd.set(GregorianCalendar.HOUR_OF_DAY, 10);
            gcd.set(GregorianCalendar.MINUTE, 0);
            gcd.set(GregorianCalendar.SECOND, 0);
            //Seteo del fin de la fecha hasta
            gch.set(GregorianCalendar.HOUR_OF_DAY, 18);
            gch.set(GregorianCalendar.MINUTE, 0);
            gch.set(GregorianCalendar.SECOND, 0);

            java.sql.Timestamp d = new Timestamp(gcd.getTimeInMillis());
            java.sql.Timestamp h = new Timestamp(gch.getTimeInMillis());
            //FIN MODIFICACIONES FECHAS

            parametro.put("pfechadesde", d);
            parametro.put("pfechahasta", h);
            //parametro.put("pmaquina", m);

            parametro.put("pduke", "C:\\Logos\\Duke.gif");
            parametro.put("pimagen", "C:\\Logos\\logoreporte.jpg");
            JasperPrint jp = DesktopApp.getApplication().getReporte("producciondesviofin", parametro);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Reporte de Desfasajes de Produccion");
            jv.setVisible(true);
            mensaje = "Reporte finalizado";
            return mensaje;
        }

        @Override
        protected void succeeded(String result) {
            super.succeeded(result);
            view.setStatusMessage(result);
        }
    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ReporteDesvioFin.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Action
    public void ayuda() {
        JOptionPane.showInternalMessageDialog(this, "Usa Fecha de Inicio");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        cancelarButton = new javax.swing.JButton();
        aceptarButton = new javax.swing.JButton();
        ayudaButton = new javax.swing.JButton();
        cmbDesvio = new javax.swing.JComboBox();
        cboMaquinas = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ReporteDesvioFin.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jDateChooser1.setName("jDateChooser1"); // NOI18N

        jDateChooser2.setName("jDateChooser2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ReporteDesvioFin.class, this);
        cancelarButton.setAction(actionMap.get("cancelar")); // NOI18N
        cancelarButton.setText(resourceMap.getString("cancelarButton.text")); // NOI18N
        cancelarButton.setName("cancelarButton"); // NOI18N

        aceptarButton.setAction(actionMap.get("aceptar")); // NOI18N
        aceptarButton.setText(resourceMap.getString("aceptarButton.text")); // NOI18N
        aceptarButton.setName("aceptarButton"); // NOI18N

        ayudaButton.setAction(actionMap.get("ayuda")); // NOI18N
        ayudaButton.setText(resourceMap.getString("ayudaButton.text")); // NOI18N
        ayudaButton.setName("ayudaButton"); // NOI18N

        cmbDesvio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEGUNDOS", "MINUTOS", "HORAS", "DIAS" }));
        cmbDesvio.setName("cmbDesvio"); // NOI18N

        cboMaquinas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMaquinas.setName("cboMaquinas"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ayudaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addGap(141, 141, 141)
                        .addComponent(aceptarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(cmbDesvio, 0, 330, Short.MAX_VALUE)
                    .addComponent(cboMaquinas, 0, 330, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbDesvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(aceptarButton)
                    .addComponent(ayudaButton))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton ayudaButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox cboMaquinas;
    private javax.swing.JComboBox cmbDesvio;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
