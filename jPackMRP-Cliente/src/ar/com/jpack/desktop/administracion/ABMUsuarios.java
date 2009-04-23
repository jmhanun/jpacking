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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

/**
 *
 * @author  jmhanun
 */
public class ABMUsuarios extends CustomInternalFrame<UsuariosT> {

    /** Creates new form ABMUsuarios */
    public ABMUsuarios() {
        super(DesktopApp.getApplication().getUsuarioLogueado());
        initComponents();
        itemListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                getDto().setIdEstado((EstadosT) e.getItem());
                setModificado(true);
            }
        };

        treeSelectionListener = new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                int indexArbol = -1;
                int iteration = 0;
                DefaultMutableTreeNode padre = new DefaultMutableTreeNode(getResourceMap().getString("usuarios"));
                JInternalFrame temp = (JInternalFrame) ((Component) e.getSource()).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
                if (isModificado()) {
                    if (JOptionPane.showInternalConfirmDialog(temp, "Algunos datos han sido modificados.\n¿Desea conservar esos cambios?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        grabar();
                    } else {// el usuario fue modificado y no quiere grabar los cambios
                        if (isNuevo()) {
                            getListDto().remove(getDto());
                            for (UsuariosT usuario : getListDto()) {
                                if (getDto() != null) {
                                    if (usuario.getIdUsuario() == null) {
                                        indexArbol = iteration + 1;
                                    }
                                }
                                DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(usuario);
                                padre.add(hijo);
                                iteration++;
                            }
                        }
                        setDto(getOldDto());
                        txtUsuario.setEnabled(false);
                        setModificado(false);
                        setNuevo(false);
                        btnNuevo.setEnabled(true);
                        if (isNuevo()) {
                            jTree.setModel(new DefaultTreeModel(padre));
                            jTree.setSelectionRow(indexArbol);
                        }
                    }
                }
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
                if ((node != null) && (node.getUserObject() instanceof UsuariosT)) {
                    setDto((UsuariosT) node.getUserObject());
                } else {
                    jTree.setSelectionRow(1);
                }
                cambiarUsuarioT();
            }
        };

        setModificado(false);
        setNuevo(false);
        txtUsuario.setEnabled(false);
        iniciar();
    }

    public void iniciar() {
        setResourceMap(DesktopApp.getApplication().getContext().getResourceMap(ABMUsuarios.class));
        HashMap parametrosUsuarios = new HashMap();
        parametrosUsuarios.put("pJoinEstados", true);
        parametrosUsuarios.put("pJoinRoles", true);
        this.setListDto((ArrayList<UsuariosT>) DesktopApp.getApplication().getUsuariosT(parametrosUsuarios));
        rolesTs = (ArrayList<RolesT>) DesktopApp.getApplication().getRolesT(new HashMap());
        HashMap parametrosEstados = new HashMap();
        parametrosEstados.put("pJoinDominios", true);
        parametrosEstados.put("pDescripcionDominio", "USUARIOS");
        estadosTs = (ArrayList<EstadosT>) DesktopApp.getApplication().getEstadosT(parametrosEstados);
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode(getResourceMap().getString("usuarios"));
        int indexArbol = -1;
        int iteration = 0;
        for (UsuariosT usu : getListDto()) {
            //Setea la variable index para que se seleccione en el arbol el usuario logueado
            if (getDto() != null) {
                if (getDto().getIdUsuario().equals(usu.getIdUsuario())) {
                    indexArbol = iteration + 1;
                }
            }
            DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(usu);
            padre.add(hijo);
            iteration++;
        }
        DefaultComboBoxModel estadosComboBoxModel = new DefaultComboBoxModel();
        int indexEstado = -1;
        iteration = 0;
        for (EstadosT est : estadosTs) {
            estadosComboBoxModel.addElement(est);
            if (est.getIdEstado().equals(getDto().getIdEstado().getIdEstado())) {
                indexEstado = iteration;
            }
            iteration++;
        }
        cboEstados.setModel(estadosComboBoxModel);
        cboEstados.setSelectedIndex(indexEstado);
        DefaultListModel disponiblesListModel = new DefaultListModel();
        for (RolesT rol : rolesTs) {
            if (rol.getFuncion() != null) {
                disponiblesListModel.addElement(rol);
            }
        }
        lstRoles.setModel(disponiblesListModel);
        jTree.addTreeSelectionListener(treeSelectionListener);
        jTree.setModel(new DefaultTreeModel(padre));
        jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree.setSelectionRow(indexArbol);
    }

    private void cambiarUsuarioT() {
        cboEstados.setEnabled(!DesktopApp.getApplication().getUsuarioLogueado().getIdUsuario().equals(getDto().getIdUsuario()));
        cboEstados.removeItemListener(itemListener);
        DefaultListModel asignadosListModel = new DefaultListModel();
        for (RolesT rol : getDto().getIdRolCollection()) {
            if (rol.getFuncion() != null) {
                asignadosListModel.addElement(rol);
            }
        }
        lstRolesAsignados.setModel(asignadosListModel);
        int index = -1;
        int iteration = 0;
        for (EstadosT est : estadosTs) {
            if (est.getIdEstado().equals(getDto().getIdEstado().getIdEstado())) {
                index = iteration;
            }
            iteration++;
        }
        txtUsuario.setText(getDto().getUsuario());
        txtNombres.setText(getDto().getNombres());
        txtEmail.setText(getDto().getMails());
        txtApellidos.setText(getDto().getApellidos());
        cboEstados.setSelectedIndex(index);
        cboEstados.addItemListener(itemListener);
    }

    @Action
    public void nuevo() {
        if (isModificado()) {
            if (JOptionPane.showInternalConfirmDialog(this, "Algunos datos han sido modificados.\n¿Desea conservar esos cambios?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                grabar();
            } else {
                setDto(getOldDto());
                txtUsuario.setEnabled(false);
                setModificado(false);
                setNuevo(false);
                btnNuevo.setEnabled(true);
            }
        }
        UsuariosT nuevoUsuario = new UsuariosT();
        HashMap parametrosEstados = new HashMap();
        parametrosEstados.put("pIdEstado", 1);//HARDCODEADO
        nuevoUsuario.setIdEstado(((ArrayList<EstadosT>) DesktopApp.getApplication().getEstadosT(parametrosEstados)).get(0));
        nuevoUsuario.setIdRolCollection(new ArrayList<RolesT>());
        nuevoUsuario.setContrasena("");
        nuevoUsuario.setUltimoAcceso(new Date());
        txtUsuario.setEnabled(true);
        setDto(nuevoUsuario);
        getListDto().add(getDto());
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode(getResourceMap().getString("usuarios"));
        int indexArbol = -1;
        int iteration = 0;
        for (UsuariosT usuario : getListDto()) {
            if (getDto() != null) {
                if (usuario.getIdUsuario() == null) {
                    indexArbol = iteration + 1;
                }
            }
            DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(usuario);
            padre.add(hijo);
            iteration++;
        }
        cambiarUsuarioT();
        jTree.setModel(new DefaultTreeModel(padre));
        jTree.setSelectionRow(indexArbol);
        setNuevo(true);
        setModificado(true);
        btnNuevo.setEnabled(false);
    }

    @Action
    public void agregarRol() {
        JOptionPane.showInternalMessageDialog(this, "agregarRol");
    }

    @Action
    public void quitarRol() {
        JOptionPane.showInternalMessageDialog(this, "quitarRol");
    }

    @Action(enabledProperty = "modificado")
    public void grabar() {
        if (isNuevo()) {
            DesktopApp.getApplication().actualizarUsuariosT(getDto(), true);
        } else {
            DesktopApp.getApplication().actualizarUsuariosT(getDto(), false);
        }
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode(getResourceMap().getString("usuarios"));
        int indexArbol = -1;
        int iteration = 0;
        for (UsuariosT usuario : getListDto()) {
            if (getDto() != null) {
                if (usuario.getIdUsuario() == null) {
                    indexArbol = iteration + 1;
                }
            }
            DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(usuario);
            padre.add(hijo);
            iteration++;
        }
        setModificado(false);
        setNuevo(false);
        jTree.setModel(new DefaultTreeModel(padre));
        jTree.setSelectionRow(indexArbol);
        txtUsuario.setEnabled(false);
        btnNuevo.setEnabled(true);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        cboEstados = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstRoles = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstRolesAsignados = new javax.swing.JList();
        btnAgregarRol = new javax.swing.JButton();
        btnQuitarRol = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMUsuarios.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(411, 331));
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N

        tabPanel.setName("tabPanel"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

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

        cboEstados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboEstados.setName("cboEstados"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboEstados, 0, 169, Short.MAX_VALUE))
                    .addComponent(btnGrabar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(cboEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGrabar)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarRol)
                    .addComponent(btnQuitarRol))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        tabPanel.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        btnNuevo.setAction(actionMap.get("nuevo")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo)
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
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
    getDto().setNombres(((txtNombres.getText() + String.valueOf(evt.getKeyChar())).trim()).toUpperCase());
    setModificado(true);
}//GEN-LAST:event_txtNombresKeyTyped

private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
    getDto().setApellidos(((txtApellidos.getText() + String.valueOf(evt.getKeyChar())).trim()).toUpperCase());
    setModificado(true);
}//GEN-LAST:event_txtApellidosKeyTyped

private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
    getDto().setMails((txtEmail.getText() + String.valueOf(evt.getKeyChar())).trim());
    setModificado(true);
}//GEN-LAST:event_txtEmailKeyTyped

private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
    getDto().setUsuario(((txtUsuario.getText() + String.valueOf(evt.getKeyChar())).trim()).toUpperCase());
    setModificado(true);
}//GEN-LAST:event_txtUsuarioKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarRol;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitarRol;
    private javax.swing.JComboBox cboEstados;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
    private ArrayList<RolesT> rolesTs;
    private ArrayList<EstadosT> estadosTs;
    private ItemListener itemListener;
    private TreeSelectionListener treeSelectionListener;
}