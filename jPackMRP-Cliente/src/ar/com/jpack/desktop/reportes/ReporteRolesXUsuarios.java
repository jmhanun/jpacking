/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReporteRolesXUsuarios.java
 *
 * Created on 29-jul-2009, 20:40:19
 */

package ar.com.jpack.desktop.reportes;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.DesktopView;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.transferencia.RolesT;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author Pablo
 */
public class ReporteRolesXUsuarios extends CustomInternalFrame {

    /** Creates new form ReporteRolesXUsuarios */
    public ReporteRolesXUsuarios() {
        super(new RolesT());
        initComponents();
        getRootPane().setDefaultButton(aceptarButton);
    }

    @Action
    public void cancel() {
        this.dispose();
    }

    @Action
    public Task showReporte() {
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
            if (!txtUsuario.getText().isEmpty()) {
                parametro.put("pusuario", Integer.parseInt(txtUsuario.getText()));
            }
            parametro.put("pduke", "C:\\Logos\\Duke.gif");
            parametro.put("pimagen", "C:\\Logos\\logoreporte.jpg");
            JasperPrint jp = DesktopApp.getApplication().getReporte("rolesxusuarios", parametro);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Reporte de Roles por Usuarios");
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        cancelarButton = new javax.swing.JButton();
        aceptarButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ReporteRolesXUsuarios.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtUsuario.setText(resourceMap.getString("txtUsuario.text")); // NOI18N
        txtUsuario.setName("txtUsuario"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ReporteRolesXUsuarios.class, this);
        cancelarButton.setAction(actionMap.get("cancel")); // NOI18N
        cancelarButton.setText(resourceMap.getString("cancelarButton.text")); // NOI18N
        cancelarButton.setName("cancelarButton"); // NOI18N

        aceptarButton.setAction(actionMap.get("showReporte")); // NOI18N
        aceptarButton.setText(resourceMap.getString("aceptarButton.text")); // NOI18N
        aceptarButton.setName("aceptarButton"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(aceptarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(aceptarButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
