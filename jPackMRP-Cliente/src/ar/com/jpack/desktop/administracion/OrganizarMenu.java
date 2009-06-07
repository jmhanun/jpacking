/*
 * OrganizarMenu.java
 *
 * Created on 3 de junio de 2009, 21:59
 */
package ar.com.jpack.desktop.administracion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.transferencia.RolesT;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import org.jdesktop.application.Action;

/**
 *
 * @author  jmhanun
 */
public class OrganizarMenu extends CustomInternalFrame<RolesT> {

    /** Creates new form OrganizarMenu */
    public OrganizarMenu() {
        super(null);
        initComponents();
        itemListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("<Principal>")) {
                    setDto(null);
                } else {
                    setDto((RolesT) e.getItem());
                }
                cambiarRolT();
            }
        };

        rolesMenues = (ArrayList<RolesT>) DesktopApp.getApplication().getMenuesT(true);
        Collections.sort(rolesMenues);

        DefaultComboBoxModel rolesComboBoxModel = new DefaultComboBoxModel();
        rolesComboBoxModel.addElement("<Principal>");
        for (RolesT rol : rolesMenues) {
            rolesComboBoxModel.addElement(rol);
        }
        cboRoles.setModel(rolesComboBoxModel);
        parametros = new HashMap();
        rolesTs = (ArrayList<RolesT>) DesktopApp.getApplication().getRolesT(parametros);
        rolesFunciones = new ArrayList<RolesT>();
        if (getDto() == null) {
            for (RolesT rol : rolesTs) {
                if (rol.getIdRolPadre() == null) {
                    rolesFunciones.add(rol);
                }
            }
        } else {
            for (RolesT rol : rolesTs) {
                if (rol.getIdRolPadre().equals(getDto())) {
                    rolesFunciones.add(rol);
                }
            }
        }
        Collections.sort(rolesFunciones);

        DefaultListModel rolesListModel = new DefaultListModel();
        for (RolesT rol : rolesFunciones) {
            rolesListModel.addElement(rol);
        }
        lstRoles.setModel(rolesListModel);

        cboRoles.addItemListener(itemListener);

        lstRoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void cambiarRolT() {
        cboRoles.removeItemListener(itemListener);

        rolesFunciones = new ArrayList<RolesT>();
        if (getDto() == null) {
            for (RolesT rol : rolesTs) {
                if (rol.getIdRolPadre() == null) {
                    rolesFunciones.add(rol);
                }
            }
        } else {
            for (RolesT rol : rolesTs) {
                if (rol.getIdRolPadre() != null) {
                    if (rol.getIdRolPadre().equals(getDto())) {
                        rolesFunciones.add(rol);
                    }
                }
            }
        }
        Collections.sort(rolesFunciones);

        DefaultListModel rolesListModel = new DefaultListModel();
        for (RolesT rol : rolesFunciones) {
            rolesListModel.addElement(rol);
        }
        lstRoles.setModel(rolesListModel);
        cboRoles.addItemListener(itemListener);
    }

    @Action
    public void subir() {
        if (lstRoles.isSelectionEmpty()) {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un rol previamente");
        } else {
            int index = lstRoles.getSelectedIndex();
            if (index != 0) {

                rolOrigen = (RolesT) lstRoles.getModel().getElementAt(index);
                index--;
                rolDestino = (RolesT) lstRoles.getModel().getElementAt(index);
                
                int ordenHermanoOrigen = rolOrigen.getOrdenHermano();
                int ordenHermanoDestino = rolDestino.getOrdenHermano();

                rolOrigen.setOrdenHermano(ordenHermanoDestino);
                rolDestino.setOrdenHermano(ordenHermanoOrigen);
                DesktopApp.getApplication().updateRolesT(rolOrigen);
                DesktopApp.getApplication().updateRolesT(rolDestino);
            }
        }
    }

    @Action
    public void bajar() {
        if (lstRoles.isSelectionEmpty()) {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un rol previamente");
        } else {
        }
    }

    @Action
    public void cerrar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(OrganizarMenu.class.getName()).log(Level.SEVERE, null, ex);
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
        cboRoles = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstRoles = new javax.swing.JList();
        btnBajar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setName("Form"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(OrganizarMenu.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        cboRoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboRoles.setName("cboRoles"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        lstRoles.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstRoles.setName("lstRoles"); // NOI18N
        jScrollPane1.setViewportView(lstRoles);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(OrganizarMenu.class, this);
        btnBajar.setAction(actionMap.get("bajar")); // NOI18N
        btnBajar.setName("btnBajar"); // NOI18N

        btnCerrar.setAction(actionMap.get("cerrar")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N

        btnSubir.setAction(actionMap.get("subir")); // NOI18N
        btnSubir.setName("btnSubir"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboRoles, 0, 344, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(btnSubir, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBajar, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnBajar)
                    .addComponent(btnSubir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

    dispose();

}//GEN-LAST:event_formInternalFrameClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBajar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnSubir;
    private javax.swing.JComboBox cboRoles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstRoles;
    // End of variables declaration//GEN-END:variables
    private List<RolesT> rolesMenues;
    private List<RolesT> rolesFunciones;
    private List<RolesT> rolesTs;
    private RolesT rolOrigen;
    private RolesT rolDestino;
    private ItemListener itemListener;
    private HashMap parametros;
}
