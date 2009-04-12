/*
 * GestionUsuarios.java
 *
 * Created on 25 de junio de 2008, 19:58
 */
package ar.com.jpack.desktop.administracion;
// <editor-fold defaultstate="collapsed" desc="Imports">
import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.UsuariosT;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
// </editor-fold>
/**
 * InternalFrame para la gestion de los usuarios.
 * @author  jmhanun
 */
public class GestionUsuarios extends JInternalFrame {

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuariosT = new ar.com.jpack.transferencia.UsuariosT();
        jSplitPane = new javax.swing.JSplitPane();
        izqPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
        derPanel = new javax.swing.JPanel();
        nuevoButton = new javax.swing.JButton();
        tabPanel = new javax.swing.JTabbedPane();
        datosPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        usuarioTextField = new javax.swing.JTextField();
        nombresTextField = new javax.swing.JTextField();
        apellidosTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        estadoTextField = new javax.swing.JTextField();
        contrasenaPasswordField = new javax.swing.JPasswordField();
        grabarButton = new javax.swing.JButton();
        rolesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        disponiblesList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        asignadosList = new javax.swing.JList();
        agregarRolButton = new javax.swing.JButton();
        quitarRolButton = new javax.swing.JButton();
        deshabilitarToggleButton = new javax.swing.JToggleButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(GestionUsuarios.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jSplitPane.setDividerLocation(150);
        jSplitPane.setName("jSplitPane"); // NOI18N

        izqPanel.setName("izqPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTree.setName("jTree"); // NOI18N
        jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree);

        javax.swing.GroupLayout izqPanelLayout = new javax.swing.GroupLayout(izqPanel);
        izqPanel.setLayout(izqPanelLayout);
        izqPanelLayout.setHorizontalGroup(
            izqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );
        izqPanelLayout.setVerticalGroup(
            izqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );

        jSplitPane.setLeftComponent(izqPanel);

        derPanel.setName("derPanel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(GestionUsuarios.class, this);
        nuevoButton.setAction(actionMap.get("nuevoUsuario")); // NOI18N
        nuevoButton.setText(resourceMap.getString("nuevoButton.text")); // NOI18N
        nuevoButton.setName("nuevoButton"); // NOI18N

        tabPanel.setName("tabPanel"); // NOI18N

        datosPanel.setName("datosPanel"); // NOI18N

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

        usuarioTextField.setEnabled(false);
        usuarioTextField.setName("usuarioTextField"); // NOI18N
        usuarioTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuarioTextFieldKeyTyped(evt);
            }
        });

        nombresTextField.setName("nombresTextField"); // NOI18N
        nombresTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombresTextFieldKeyTyped(evt);
            }
        });

        apellidosTextField.setName("apellidosTextField"); // NOI18N
        apellidosTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidosTextFieldKeyTyped(evt);
            }
        });

        emailTextField.setName("emailTextField"); // NOI18N
        emailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyTyped(evt);
            }
        });

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        estadoTextField.setEnabled(false);
        estadoTextField.setName("estadoTextField"); // NOI18N

        contrasenaPasswordField.setEnabled(false);
        contrasenaPasswordField.setName("contrasenaPasswordField"); // NOI18N
        contrasenaPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contrasenaPasswordFieldKeyTyped(evt);
            }
        });

        grabarButton.setAction(actionMap.get("grabarUsuario")); // NOI18N
        grabarButton.setName("grabarButton"); // NOI18N

        javax.swing.GroupLayout datosPanelLayout = new javax.swing.GroupLayout(datosPanel);
        datosPanel.setLayout(datosPanelLayout);
        datosPanelLayout.setHorizontalGroup(
            datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contrasenaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombresTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTextField)
                    .addComponent(apellidosTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(estadoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosPanelLayout.createSequentialGroup()
                .addContainerGap(238, Short.MAX_VALUE)
                .addComponent(grabarButton)
                .addContainerGap())
        );

        datosPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {apellidosTextField, contrasenaPasswordField, emailTextField, estadoTextField, nombresTextField, usuarioTextField});

        datosPanelLayout.setVerticalGroup(
            datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contrasenaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombresTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estadoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grabarButton)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        tabPanel.addTab(resourceMap.getString("datosPanel.TabConstraints.tabTitle"), datosPanel); // NOI18N

        rolesPanel.setName("rolesPanel"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        disponiblesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        disponiblesList.setName("disponiblesList"); // NOI18N
        jScrollPane2.setViewportView(disponiblesList);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        asignadosList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        asignadosList.setName("asignadosList"); // NOI18N
        jScrollPane3.setViewportView(asignadosList);

        agregarRolButton.setAction(actionMap.get("agregarRoles")); // NOI18N
        agregarRolButton.setText(resourceMap.getString("agregarRolButton.text")); // NOI18N
        agregarRolButton.setName("agregarRolButton"); // NOI18N

        quitarRolButton.setAction(actionMap.get("quitarRoles")); // NOI18N
        quitarRolButton.setText(resourceMap.getString("quitarRolButton.text")); // NOI18N
        quitarRolButton.setName("quitarRolButton"); // NOI18N

        javax.swing.GroupLayout rolesPanelLayout = new javax.swing.GroupLayout(rolesPanel);
        rolesPanel.setLayout(rolesPanelLayout);
        rolesPanelLayout.setHorizontalGroup(
            rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rolesPanelLayout.createSequentialGroup()
                .addGroup(rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rolesPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rolesPanelLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(agregarRolButton)
                        .addGap(18, 18, 18)
                        .addComponent(quitarRolButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rolesPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {agregarRolButton, quitarRolButton});

        rolesPanelLayout.setVerticalGroup(
            rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rolesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarRolButton)
                    .addComponent(quitarRolButton))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabPanel.addTab(resourceMap.getString("rolesPanel.TabConstraints.tabTitle"), rolesPanel); // NOI18N

        deshabilitarToggleButton.setAction(actionMap.get("deshabilitarUsuario")); // NOI18N
        deshabilitarToggleButton.setText(resourceMap.getString("deshabilitarToggleButton.text")); // NOI18N
        deshabilitarToggleButton.setName("deshabilitarToggleButton"); // NOI18N

        javax.swing.GroupLayout derPanelLayout = new javax.swing.GroupLayout(derPanel);
        derPanel.setLayout(derPanelLayout);
        derPanelLayout.setHorizontalGroup(
            derPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, derPanelLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(nuevoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deshabilitarToggleButton)
                .addContainerGap())
        );

        derPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deshabilitarToggleButton, nuevoButton});

        derPanelLayout.setVerticalGroup(
            derPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, derPanelLayout.createSequentialGroup()
                .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(derPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deshabilitarToggleButton)
                    .addComponent(nuevoButton))
                .addContainerGap())
        );

        jSplitPane.setRightComponent(derPanel);

        getContentPane().add(jSplitPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTreeValueChanged
    //Returns the last path element of the selection.
    //This method is useful only when the selection model allows a single selection.
    //Cada vez que haya un cambio en el valor seleccionado del arbol,
    //la variable usuariosT se actualiza.
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
    if (node == null) {
        usuariosT = new UsuariosT();
        tabPanel.setEnabledAt(1, false);
    } else {
        if (node.getUserObject() instanceof UsuariosT) {
            usuariosT = (UsuariosT) node.getUserObject();
            tabPanel.setEnabledAt(1, true);
        } else {
            usuariosT = new UsuariosT();
            tabPanel.setEnabledAt(1, false);
        }
    }
    tabPanel.setSelectedIndex(0);
    grabarButton.setEnabled(false);
    usuarioTextField.setEnabled(false);
    contrasenaPasswordField.setEnabled(false);
    disponiblesList.clearSelection();
    cargar();
}//GEN-LAST:event_jTreeValueChanged

private void usuarioTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioTextFieldKeyTyped
    usuariosT.setUsuario(usuarioTextField.getText() + String.valueOf(evt.getKeyChar()));
    grabarButton.setEnabled(true);
}//GEN-LAST:event_usuarioTextFieldKeyTyped

private void contrasenaPasswordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contrasenaPasswordFieldKeyTyped
    usuariosT.setContrasena(String.copyValueOf(contrasenaPasswordField.getPassword()) + String.valueOf(evt.getKeyChar()));
    grabarButton.setEnabled(true);
}//GEN-LAST:event_contrasenaPasswordFieldKeyTyped

private void nombresTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombresTextFieldKeyTyped
    usuariosT.setNombres(nombresTextField.getText() + String.valueOf(evt.getKeyChar()));
    grabarButton.setEnabled(true);
}//GEN-LAST:event_nombresTextFieldKeyTyped

private void apellidosTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidosTextFieldKeyTyped
    usuariosT.setApellidos(apellidosTextField.getText() + String.valueOf(evt.getKeyChar()));
    grabarButton.setEnabled(true);
}//GEN-LAST:event_apellidosTextFieldKeyTyped

private void emailTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailTextFieldKeyTyped
    usuariosT.setMails(emailTextField.getText() + String.valueOf(evt.getKeyChar()));
    grabarButton.setEnabled(true);
}//GEN-LAST:event_emailTextFieldKeyTyped
    //<editor-fold defaultstate="collapsed" desc="Declaracion de variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarRolButton;
    private javax.swing.JTextField apellidosTextField;
    private javax.swing.JList asignadosList;
    private javax.swing.JPasswordField contrasenaPasswordField;
    private javax.swing.JPanel datosPanel;
    private javax.swing.JPanel derPanel;
    private javax.swing.JToggleButton deshabilitarToggleButton;
    private javax.swing.JList disponiblesList;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField estadoTextField;
    private javax.swing.JButton grabarButton;
    private javax.swing.JPanel izqPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JTree jTree;
    private javax.swing.JTextField nombresTextField;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JButton quitarRolButton;
    private javax.swing.JPanel rolesPanel;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTextField usuarioTextField;
    private ar.com.jpack.transferencia.UsuariosT usuariosT;
    // End of variables declaration//GEN-END:variables
    private static GestionUsuarios gestionUsuarios = new GestionUsuarios();
    private ArrayList<RolesT> rolesTs;
//</editor-fold>
    /** Creates new form GestionUsuarios */
    private GestionUsuarios() {
        initComponents();
        iniciar(DesktopApp.getApplication().getUsuarioLogueado());

    }

    public static GestionUsuarios getGestionUsuarios() {
        return gestionUsuarios;

    }

    private void iniciar(UsuariosT usu) {
        ResourceMap resourceMap = DesktopApp.getApplication().getContext().getResourceMap(GestionUsuarios.class);
        ArrayList<UsuariosT> usuariosTs = (ArrayList<UsuariosT>) DesktopApp.getApplication().getAllUsuarios();
        rolesTs = (ArrayList<RolesT>) DesktopApp.getApplication().getAllRoles();
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode(resourceMap.getString("usuarios"));
        int index = -1;
        int iteration = 0;
        for (Iterator<UsuariosT> it = usuariosTs.iterator(); it.hasNext();) {
            UsuariosT usuario = it.next();
            if (usu != null) {
                if (usu.toString().equals(usuario.toString())) {
                    index = iteration + 1;
                }
            }
            DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(usuario);
            padre.add(hijo);
            iteration++;
        }
        DefaultListModel disponiblesListModel = new DefaultListModel();
        for (Iterator<RolesT> it = rolesTs.iterator(); it.hasNext();) {
            RolesT rolesT = it.next();
            if (rolesT.getFuncion() != null) {
                disponiblesListModel.addElement(rolesT);
            }
        }
        disponiblesList.setModel(disponiblesListModel);
        jTree.setModel(new DefaultTreeModel(padre));
        jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree.setSelectionRow(index);
    }

    private void cargar() {
        this.apellidosTextField.setText(usuariosT.getApellidos());
        this.contrasenaPasswordField.setText(usuariosT.getContrasena());
        this.emailTextField.setText(usuariosT.getMails());
        this.estadoTextField.setText(usuariosT.getIdEstado() != null ? usuariosT.getIdEstado().getDescripcion() : "");
        this.nombresTextField.setText(usuariosT.getNombres());
        this.usuarioTextField.setText(usuariosT.getUsuario());
        if (usuariosT.getIdEstado() != null) {
            this.deshabilitarToggleButton.setSelected(usuariosT.getIdEstado().getIdEstado() == 1 ? false : true);
        } else {
            this.deshabilitarToggleButton.setSelected(false);
        }
        DefaultListModel asignadosListModel = new DefaultListModel();
        if (usuariosT.getIdRolCollection() != null) {
            for (Iterator<RolesT> it = usuariosT.getIdRolCollection().iterator(); it.hasNext();) {
                RolesT rolesT = it.next();
                if (rolesT.getFuncion() != null) {
                    asignadosListModel.addElement(rolesT);
                }
            }
        }
        asignadosList.setModel(asignadosListModel);
    }

    @Action
    public void grabarUsuario() {
        usuariosT = DesktopApp.getApplication().grabarUsuarioT(usuariosT);
        cargar();
        iniciar(usuariosT);
        DesktopApp.getApplication().getDesktopView().setStatusMessage("Cambios del usuario aplicados");
    }

    @Action
    public void nuevoUsuario() {
        jTree.setSelectionRow(-1);
        grabarButton.setEnabled(true);
        usuarioTextField.setEnabled(true);
        contrasenaPasswordField.setEnabled(true);
        DesktopApp.getApplication().getDesktopView().setStatusMessage("Ingrese los datos del nuevo usuario");
    }

    @Action
    public void deshabilitarUsuario() {
        if (usuariosT.getIdUsuario() != null) {
            if (!usuariosT.getIdUsuario().equals(DesktopApp.getApplication().getUsuarioLogueado().getIdUsuario())) {
                if (usuariosT.getIdEstado().getIdEstado() == 1) {
                    usuariosT.getIdEstado().setIdEstado(3);
                } else {
                    usuariosT.getIdEstado().setIdEstado(1);
                }
                grabarUsuario();
            } else {
                JOptionPane.showMessageDialog(this, "No es posible deshabilitar el usuario, ya que es SU usuario");
            }
        }
    }

    @Action
    public void agregarRoles() {
        //Cargo la lista de roles para agregar
        //Son los roles seleccionados en el JList disponiblesList
        List<RolesT> asignarRolesFromJList = cargarListaRoles(disponiblesList.getSelectedValues());

        //Elimino de la lista anterior los roles seleccionados 
        //que ya esten asignado al usuario.       
        asignarRolesFromJList.removeAll(usuariosT.getIdRolCollection());

        //Luego valido que haya quedado algo seleccionado.
        if (asignarRolesFromJList.size() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un rol no asignado");
        } else {
            //De los roles que si se van a agregar, agregar los padres.
            //Creo el Set de roles a asignar con los roles seleccionados
            //ademas de los padres de esos roles
            HashSet<RolesT> nuevosRoles = new HashSet<RolesT>();
            for (Iterator<RolesT> it = asignarRolesFromJList.iterator(); it.hasNext();) {
                RolesT rolesT = it.next();
                nuevosRoles.add(rolesT);
                nuevosRoles = getPadres(nuevosRoles, rolesT);
            }
            //Agrego a los nuevos roles los que ya estaban asignados al usuario
            for (Iterator<RolesT> it = usuariosT.getIdRolCollection().iterator(); it.hasNext();) {
                RolesT rolesT = it.next();
                nuevosRoles.add(rolesT);
            }


//            usuariosT.setIdRolCollection(nuevosRoles);
            asignarRolesFromJList = new ArrayList<RolesT>();
            for (Iterator<RolesT> it = nuevosRoles.iterator(); it.hasNext();) {
                RolesT rolesT = it.next();
                asignarRolesFromJList.add(rolesT);
            }
            usuariosT.setIdRolCollection(asignarRolesFromJList);
            grabarUsuario();
        }
        disponiblesList.clearSelection();
        asignadosList.clearSelection();
    }

    @Action
    public void quitarRoles() {
        //Cargo la lista de roles para quitar
        //Son los roles seleccionados en el JList asignadosList
        List<RolesT> quitarRolesFromJList = cargarListaRoles(asignadosList.getSelectedValues());

        //Luego valido que haya algo seleccionado.
        if (quitarRolesFromJList.size() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un rol");
        } else {
            //Elimino de la lista de roles asignados al usuario
            //los roles seleccionados en la lista anterior
            usuariosT.getIdRolCollection().removeAll(quitarRolesFromJList);
            //Hago una lista con los los roles que me quedaron que sean funcion
            List<RolesT> nuevos = new ArrayList<RolesT>();
            for (Iterator<RolesT> it = usuariosT.getIdRolCollection().iterator(); it.hasNext();) {
                RolesT rolesT = it.next();
                if (rolesT.getFuncion() != null) {
                    nuevos.add(rolesT);
                }
            }
            //Agrego los padres que hagan falta
            //En el hashset me van a quedar todos los roles que quedaron
            //con los padres que sean necesarios para verlos
            HashSet<RolesT> nuevosRoles = new HashSet<RolesT>();
            for (Iterator<RolesT> it = nuevos.iterator(); it.hasNext();) {
                RolesT rolesT = it.next();
                nuevosRoles.add(rolesT);
                nuevosRoles = getPadres(nuevosRoles, rolesT);
            }
            
            nuevos = new ArrayList<RolesT>();
            for (Iterator<RolesT> it = nuevosRoles.iterator(); it.hasNext();) {
                RolesT rolesT = it.next();
                nuevos.add(rolesT);
            }
            usuariosT.setIdRolCollection(nuevos);
            grabarUsuario();
        }
        disponiblesList.clearSelection();
        asignadosList.clearSelection();
    }

    private List<RolesT> cargarListaRoles(Object[] o) {
        List<RolesT> roles = new ArrayList<RolesT>();
        for (int i = 0; i < o.length; i++) {
            RolesT rol = (RolesT) o[i];
            roles.add(rol);
        }
        return roles;
    }

    private HashSet<RolesT> getPadres(HashSet<RolesT> nuevosRoles, RolesT rolHijo) {
        if (rolHijo.getIdRolPadre() != null) {
            for (int i = 0; i < rolesTs.size(); i++) {
                RolesT rolPadre = rolesTs.get(i);
                if (rolHijo.getIdRolPadre().getIdRol() == rolPadre.getIdRol()) {
                    nuevosRoles.add(rolPadre);
                    nuevosRoles = getPadres(nuevosRoles, rolPadre);
                }
            }
        }
        return nuevosRoles;
    }
}