/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReporteProveedores.java
 *
 * Created on 30-jul-2009, 16:44:50
 */

package ar.com.jpack.desktop.reportes;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.DesktopView;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.transferencia.ProveedoresT;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author Pablo
 */
public class ReporteProveedores extends CustomInternalFrame {

    /** Creates new form ReporteProveedores */
    public ReporteProveedores() {
        super(new ProveedoresT());
        initComponents();
        //Inicio modificacion combo
        HashMap parametros = new HashMap();
        ArrayList<ProveedoresT> proveedoresTs = (ArrayList<ProveedoresT>) DesktopApp.getApplication().getProveedoresT(parametros);


        DefaultComboBoxModel proveedoresComboBoxModel = new DefaultComboBoxModel();
        proveedoresComboBoxModel.addElement("<Todos>");
        for (ProveedoresT proveedor : proveedoresTs) {
            proveedoresComboBoxModel.addElement(proveedor);
        }
        cmbProveedores.setModel(proveedoresComboBoxModel);
        getRootPane().setDefaultButton(aceptarButton);
        //Fin modificaciones combo
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
            //Inicio modificacion combo maquinas
            if (!cmbProveedores.getSelectedItem().equals("<Todos>")) {
                parametro.put("pproveedor", ((ProveedoresT) cmbProveedores.getSelectedItem()).getIdProveedor());
            }
            parametro.put("pduke", "C:\\Logos\\Duke.gif");
            parametro.put("pimagen", "C:\\Logos\\logoreporte.jpg");
            JasperPrint jp = DesktopApp.getApplication().getReporte("proveedores", parametro);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Reporte de Proveedores");
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
        cancelarButton = new javax.swing.JButton();
        aceptarButton = new javax.swing.JButton();
        cmbProveedores = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ReporteProveedores.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ReporteProveedores.class, this);
        cancelarButton.setAction(actionMap.get("cancel")); // NOI18N
        cancelarButton.setText(resourceMap.getString("cancelarButton.text")); // NOI18N
        cancelarButton.setName("cancelarButton"); // NOI18N

        aceptarButton.setAction(actionMap.get("showReporte")); // NOI18N
        aceptarButton.setText(resourceMap.getString("aceptarButton.text")); // NOI18N
        aceptarButton.setName("aceptarButton"); // NOI18N

        cmbProveedores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProveedores.setName("cmbProveedores"); // NOI18N

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
                        .addComponent(cmbProveedores, 0, 320, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(aceptarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(aceptarButton))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox cmbProveedores;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
