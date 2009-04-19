/*
 * ABMUsuarios.java
 *
 * Created on 10 de abril de 2009, 09:52
 */
package ar.com.jpack.desktop.administracion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.UsuariosT;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author  jmhanun
 */
public class ABMUsuarios extends CustomInternalFrame<UsuariosT> {

    /** Creates new form ABMUsuarios */
    public ABMUsuarios() {
        super(DesktopApp.getApplication().getUsuarioLogueado());
        initComponents();
        cambio = false;
        txtUsuario.setEnabled(false);
        iniciar();
    }

    public void iniciar() {
        ResourceMap resourceMap = DesktopApp.getApplication().getContext().getResourceMap(ABMUsuarios.class);
        HashMap parametrosUsuarios = new HashMap();
        parametrosUsuarios.put("pJoinEstados", true);
        parametrosUsuarios.put("pJoinRoles", true);
        this.setListDto((ArrayList<UsuariosT>) DesktopApp.getApplication().getUsuariosT(parametrosUsuarios));
        rolesTs = (ArrayList<RolesT>) DesktopApp.getApplication().getRolesT(new HashMap());
        HashMap parametrosEstados = new HashMap();
        parametrosEstados.put("pJoinDominios", true);
        parametrosEstados.put("pDescripcionDominio", "USUARIOS");
        estadosTs = (ArrayList<EstadosT>) DesktopApp.getApplication().getEstadosT(parametrosEstados);
        jTree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                JInternalFrame temp = (JInternalFrame) ((Component) e.getSource()).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();

                if (cambio) {
                    if (JOptionPane.showInternalConfirmDialog(temp, "Algunos datos han sido modificados.\n¿Desea conservar esos cambios?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        JOptionPane.showInternalMessageDialog(temp, "Graba!!");
                    } else {
                        JOptionPane.showInternalMessageDialog(temp, "NO Graba!!");
                    }
                }
                cambio = false;
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
                if ((node != null) && (node.getUserObject() instanceof UsuariosT)) {
                    setDto((UsuariosT) node.getUserObject());
                } else {
                    jTree.setSelectionRow(1);
                }
                cambiarUsuarioT();
            }
        });
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode(resourceMap.getString("usuarios"));
        int indexArbol = -1;
        int iteration = 0;
        for (Iterator<UsuariosT> it = this.getListDto().iterator(); it.hasNext();) {
            UsuariosT usuario = it.next();
            //Setea la variable index para que se seleccione en el arbol el usuario logueado
            if (getDto() != null) {
                if (getDto().getIdUsuario().equals(usuario.getIdUsuario())) {
                    indexArbol = iteration + 1;
                }
            }
            DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(usuario);
            padre.add(hijo);
            iteration++;
        }
        DefaultComboBoxModel estadosComboBoxModel = new DefaultComboBoxModel();
        int indexEstado = -1;
        iteration = 0;
        for (Iterator<EstadosT> it = estadosTs.iterator(); it.hasNext();) {
            EstadosT estadosT = it.next();
            estadosComboBoxModel.addElement(estadosT);
            if (estadosT.getIdEstado().equals(getDto().getIdEstado().getIdEstado())) {
                indexEstado = iteration;
            }
            iteration++;
        }
        cboEstados.setModel(estadosComboBoxModel);
        cboEstados.setSelectedIndex(indexEstado);
        DefaultListModel disponiblesListModel = new DefaultListModel();
        for (Iterator<RolesT> it = rolesTs.iterator(); it.hasNext();) {
            RolesT rolesT = it.next();
            if (rolesT.getFuncion() != null) {
                disponiblesListModel.addElement(rolesT);
            }
        }
        lstRoles.setModel(disponiblesListModel);
        jTree.setModel(new DefaultTreeModel(padre));
        jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree.setSelectionRow(indexArbol);
    }

    private void cambiarUsuarioT() {
        DefaultListModel asignadosListModel = new DefaultListModel();
        for (Iterator<RolesT> it = getDto().getIdRolCollection().iterator(); it.hasNext();) {
            RolesT rolesT = it.next();
            if (rolesT.getFuncion() != null) {
                asignadosListModel.addElement(rolesT);
            }
        }
        lstRolesAsignados.setModel(asignadosListModel);

        int index = -1;
        int iteration = 0;
        for (Iterator<EstadosT> it = estadosTs.iterator(); it.hasNext();) {
            EstadosT estadosT = it.next();
            if (estadosT.getIdEstado().equals(getDto().getIdEstado().getIdEstado())) {
                index = iteration;
            }
            iteration++;
        }
        cboEstados.setSelectedIndex(index);

        txtUsuario.setText(getDto().getUsuario());
        txtNombres.setText(getDto().getNombres());
        txtEmail.setText(getDto().getMails());
        txtContrasenia.setText(getDto().getContrasena());
        txtApellidos.setText(getDto().getApellidos());
    }

    private void cargarUsuariosT() {
        txtApellidos.setText(getDto().getApellidos());
        txtContrasenia.setText(getDto().getContrasena());
        txtEmail.setText(getDto().getMails());
//        this.estadoTextField.setText(usuariosT.getIdEstado() != null ? usuariosT.getIdEstado().getDescripcion() : "");
//        this.nombresTextField.setText(usuariosT.getNombres());
//        this.usuarioTextField.setText(usuariosT.getUsuario());
//        if (usuariosT.getIdEstado() != null) {
//            this.deshabilitarToggleButton.setSelected(usuariosT.getIdEstado().getIdEstado() == 1 ? false : true);
//        } else {
//            this.deshabilitarToggleButton.setSelected(false);
//        }
//        DefaultListModel asignadosListModel = new DefaultListModel();
//        if (usuariosT.getIdRolCollection() != null) {
//            for (Iterator<RolesT> it = usuariosT.getIdRolCollection().iterator(); it.hasNext();) {
//                RolesT rolesT = it.next();
//                if (rolesT.getFuncion() != null) {
//                    asignadosListModel.addElement(rolesT);
//                }
//            }
//        }
//        asignadosList.setModel(asignadosListModel);
    }

    @Action
    public void nuevo() {
        JOptionPane.showInternalMessageDialog(this, "nuevo");
    }

    @Action
    public void deshabilitar() {
        JOptionPane.showInternalMessageDialog(this, "deshabilitar");
    }

    @Action
    public void agregarRol() {
        JOptionPane.showInternalMessageDialog(this, "agregarRol");
    }

    @Action
    public void quitarRol() {
        JOptionPane.showInternalMessageDialog(this, "quitarRol");
    }

    @Action
    public void grabar() {
        JOptionPane.showInternalMessageDialog(this, "grabar");
    }

    @Action
    public void modificar() {
        JOptionPane.showInternalMessageDialog(this, "modificar");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        tabPanel = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContrasenia = new javax.swing.JPasswordField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        txtModificar = new javax.swing.JButton();
        cboEstados = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstRoles = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstRolesAsignados = new javax.swing.JList();
        btnAgregarRol = new javax.swing.JButton();
        btnQuitarRol = new javax.swing.JButton();
        btnDeshabilitar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMUsuarios.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(411, 370));
        setName("Form"); // NOI18N

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTree.setName("jTree"); // NOI18N
        jScrollPane1.setViewportView(jTree);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N

        tabPanel.setName("tabPanel"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtUsuario.setText(resourceMap.getString("txtUsuario.text")); // NOI18N
        txtUsuario.setName("txtUsuario"); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        txtContrasenia.setText(resourceMap.getString("txtContrasenia.text")); // NOI18N
        txtContrasenia.setName("txtContrasenia"); // NOI18N
        txtContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseniaKeyTyped(evt);
            }
        });

        txtNombres.setText(resourceMap.getString("txtNombres.text")); // NOI18N
        txtNombres.setName("txtNombres"); // NOI18N
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        txtApellidos.setText(resourceMap.getString("txtApellidos.text")); // NOI18N
        txtApellidos.setName("txtApellidos"); // NOI18N
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });

        txtEmail.setText(resourceMap.getString("txtEmail.text")); // NOI18N
        txtEmail.setName("txtEmail"); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ABMUsuarios.class, this);
        btnGrabar.setAction(actionMap.get("grabar")); // NOI18N
        btnGrabar.setName("btnGrabar"); // NOI18N

        txtModificar.setAction(actionMap.get("modificar")); // NOI18N
        txtModificar.setName("txtModificar"); // NOI18N

        cboEstados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboEstados.setName("cboEstados"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel1))
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(txtContrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(cboEstados, 0, 159, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGrabar)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnGrabar, txtModificar});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContrasenia)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrabar)
                    .addComponent(txtModificar))
                .addContainerGap())
        );

        tabPanel.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        lstRoles.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstRoles.setName("lstRoles"); // NOI18N
        jScrollPane2.setViewportView(lstRoles);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        lstRolesAsignados.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstRolesAsignados.setName("lstRolesAsignados"); // NOI18N
        jScrollPane3.setViewportView(lstRolesAsignados);

        btnAgregarRol.setAction(actionMap.get("agregarRol")); // NOI18N
        btnAgregarRol.setName("btnAgregarRol"); // NOI18N

        btnQuitarRol.setAction(actionMap.get("quitarRol")); // NOI18N
        btnQuitarRol.setName("btnQuitarRol"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAgregarRol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(btnQuitarRol)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAgregarRol, btnQuitarRol});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarRol)
                    .addComponent(btnQuitarRol))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        tabPanel.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        btnDeshabilitar.setAction(actionMap.get("deshabilitar")); // NOI18N
        btnDeshabilitar.setName("btnDeshabilitar"); // NOI18N

        btnNuevo.setAction(actionMap.get("nuevo")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeshabilitar)
                .addContainerGap())
            .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDeshabilitar, btnNuevo});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeshabilitar)
                    .addComponent(btnNuevo))
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_txtUsuarioKeyTyped

private void txtContraseniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseniaKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_txtContraseniaKeyTyped

private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
// TODO add your handling code here:
    cambio = true;
}//GEN-LAST:event_txtNombresKeyTyped

private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_txtApellidosKeyTyped

private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_txtEmailKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarRol;
    private javax.swing.JButton btnDeshabilitar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitarRol;
    private javax.swing.JComboBox cboEstados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTree jTree;
    private javax.swing.JList lstRoles;
    private javax.swing.JList lstRolesAsignados;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JButton txtModificar;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
    private ArrayList<RolesT> rolesTs;
    private ArrayList<EstadosT> estadosTs;
    private boolean cambio;
}