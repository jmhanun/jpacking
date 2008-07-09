/*
 * DesktopTestView.java
 */
package ar.com.jpack.desktop;

import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.UsuariosT;
import ar.com.jpack.util.StringHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.ActionMap;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

/**
 * The application's main frame.
 * @author jmhanun
 */
public class DesktopView extends FrameView {

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        desktopPanel = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.BorderLayout());

        desktopPanel.setName("desktopPanel"); // NOI18N
        mainPanel.add(desktopPanel, java.awt.BorderLayout.CENTER);

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(DesktopView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(DesktopView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents
//<editor-fold defaultstate="coolapsed" desc="Declaracion de variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    ArrayList<RolesT> rolesTs;
//</editor-fold>
    /**
     * Instancia el DesktopView indicandole el DesktopApp de quien depende.
     * @param app - DesktopApp del cual depende el DesktopView
     */
    public DesktopView(SingleFrameApplication app) {
        super(app);
        initComponents();
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);
        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    public void setStatusMessage(String result) {
        statusMessageLabel.setText(result);
        messageTimer.restart();
    }

    /**
     * Funcion que se ejecuta automaticamente al finalizar el login exitoso.
     * Es llamada desde DesktopLoginBox si el login fue exito.
     * Primero carga el JMenu Archivo comun a todos los usuarios.
     * Despues carga los JMenu de acuerdo a los roles asignados al usuario logueado.
     * Finalmente carga los JMenu Ventanas y Ayuda comunes a todos los usuarios.
     */
    public void cargaInicial() {

        JMenu fileMenu = new JMenu();
        JMenuItem loginMenuItem = new JMenuItem();
        JMenuItem exitMenuItem = new JMenuItem();
        JMenu ventanaMenu = new JMenu();
        JMenuItem lookMenuItem = new JMenuItem();
        JMenuItem closeAllMenuItem = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem aboutMenuItem = new JMenuItem();
        UsuariosT usuariosT = DesktopApp.getApplication().getUsuarioLogueado();
        ResourceMap resourceMap = Application.getInstance(DesktopApp.class).getContext().getResourceMap(DesktopView.class);
        ActionMap actionMap = Application.getInstance(DesktopApp.class).getContext().getActionMap(DesktopView.class, this);

        menuBar.removeAll();

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        loginMenuItem.setAction(actionMap.get("showLoginBox")); // NOI18N
        loginMenuItem.setName("loginMenuItem"); // NOI18N
        fileMenu.add(loginMenuItem);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        if (usuariosT != null) {
            rolesTs = (ArrayList<RolesT>) usuariosT.getIdRolCollection();
            if (rolesTs != null) {
                //ordena la lista rolesTs
                Collections.sort(rolesTs);
                for (Iterator<RolesT> it = rolesTs.iterator(); it.hasNext();) {
                    RolesT rolesT = it.next();
                    if (rolesT.getIdRolPadre() == null) {
                        JMenu m = new JMenu();
                        m.setText(resourceMap.getString(rolesT.getComponente() + ".text")); // NOI18N
                        m.setName(rolesT.getComponente()); // NOI18N
                        m = cargarMenu(m, rolesT, resourceMap, actionMap);
                        menuBar.add(m);
                    }
                }

            }
        }

        ventanaMenu.setText(resourceMap.getString("ventanaMenu.text")); // NOI18N
        ventanaMenu.setName("ventanaMenu"); // NOI18N

        lookMenuItem.setAction(actionMap.get("changeLookFeel")); // NOI18N
        lookMenuItem.setName("lookMenuItem"); // NOI18N
        ventanaMenu.add(lookMenuItem);

        ventanaMenu.add(new JSeparator());

        closeAllMenuItem.setAction(actionMap.get("closeAllFrames")); // NOI18N
        closeAllMenuItem.setName("closeAllMenuItem"); // NOI18N
        ventanaMenu.add(closeAllMenuItem);

        menuBar.add(ventanaMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setMenuBar(menuBar);
    }

    /**
     * Metodo que recorre todas las ventanas agregadas al desktopPane
     * y las va cerrando
     */
    @Action
    public void closeAllFrames() {
        JInternalFrame[] internalFrames = desktopPanel.getAllFrames();
        int i = internalFrames.length;
        while (0 < i) {
            i--;
            internalFrames[i].dispose();
        }
    }

    /**
     * Cambia el Look&Feel.
     * Rota entre el lookAndFeel NimROD y el lookAndFeel por defecto del sistema.
     */
    @Action
    public void changeLookFeel() {
        try {
            if (UIManager.getLookAndFeel().getName().equals("NimROD")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
            }
            SwingUtilities.updateComponentTreeUI(this.getFrame());
            this.getFrame().invalidate();
            this.getFrame().validate();
            this.getFrame().repaint();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DesktopView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DesktopView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DesktopView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DesktopView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muestra el frame de Acerca de...
     */
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DesktopApp.getApplication().getMainFrame();
            aboutBox = new DesktopAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DesktopApp.getApplication().show(aboutBox);
    }

    /**
     * Muestra el InternalFrame GestionUsuarios
     * @return Devuelve la tarea de mostrar el InternalFrame GestionUsuarios.
     * Corre en un hilo independiente.
     */
    @Action
    public Task showGestionUsuariosFrame() {
        return new ShowFrame(getApplication(), "ar.com.jpack.desktop.administracion.GestionUsuarios", "Gestion de usuarios");
    }

    @Action
    public Task showRegistroRemitosFrame() {
        return new ShowFrame(getApplication(), "ar.com.jpack.desktop.ventas.RegistroRemitos", "Registro de Remitos");
    }

    /**
     * InnerClass para mostrar los InternalFrame
     * @author jmhanun
     */
    class ShowFrame extends Task<String, Void> {

        String jInternalFrame;
        String mensaje;

        /**
         * 
         * @param application DesktopApp.getApplication()
         * @param jInternalFrame String del internalFrame al que deseo invocar (canonicalname de la clase)
         * @param mensaje String del mensaje que deseo aparezca en el status bar una vez finalizada la tarea
         */
        public ShowFrame(Application application, String jInternalFrame, String mensaje) {
            super(application);
            this.jInternalFrame = jInternalFrame;
            this.mensaje = mensaje;
        }

        @Override
        protected String doInBackground() throws Exception {
            statusMessageLabel.setText("Abriendo " + mensaje + "...");
            String[] partes = StringHelper.split(jInternalFrame, ".");
            int indice = partes.length - 1;
            Class c = Class.forName(jInternalFrame);
            Method m = c.getMethod("get" + partes[indice]);
            JInternalFrame f = (JInternalFrame) m.invoke(null);
            if (!isOpen(f)) {
                desktopPanel.add(f);
            }
            f.setVisible(true);
            desktopPanel.getDesktopManager().activateFrame(f);
            return mensaje;
        }

        @Override
        protected void succeeded(String result) {
            super.succeeded(result);
            setStatusMessage(result);
        }

        @Override
        protected void failed(Throwable cause) {
            super.failed(cause);
            JOptionPane.showMessageDialog(desktopPanel, "Parece que no es posible ejecutar la tarea. Consulte con el administrador.");
        }
    }

    /**
     * Funcion recursiva para poblar el JMenu con los JMenu y los JMenuItems que dependen de el.
     * @param m JMenu que aun posee JMenu y/o JMenuItems que dependen de el.
     * @param rolPadreT Rol que define los datos del JMenu.
     * - para el caso de un JMenu define el nombre del componente.
     * - para el caso de un JMenuItem define la funcion que ejecutara.
     * @param resourceMap
     * @param actionMap
     * @return Devuelve el JMenu con todos los JMenu y JMenuItems que dependen de el.
     */
    private JMenu cargarMenu(JMenu m, RolesT rolPadreT, ResourceMap resourceMap, ActionMap actionMap) {
        for (Iterator<RolesT> it = rolesTs.iterator(); it.hasNext();) {
            RolesT rolesT = it.next();
            if (rolesT.getIdRolPadre() != null) {
                if (rolesT.getIdRolPadre().getIdRol() == rolPadreT.getIdRol()) {
                    if (rolesT.getFuncion() == null) {
                        JMenu menuHijo = new JMenu();
                        menuHijo.setText(resourceMap.getString(rolesT.getComponente() + ".text")); // NOI18N
                        menuHijo.setName(rolesT.getComponente()); // NOI18N
                        menuHijo = cargarMenu(menuHijo, rolesT, resourceMap, actionMap);
                        m.add(menuHijo);
                    } else {

                        JMenuItem item = new JMenuItem();
                        item.setAction(actionMap.get(rolesT.getFuncion())); // NOI18N
                        item.setName(rolesT.getComponente()); // NOI18N
                        m.add(item);
                    }
                }
            }
        }
        return m;
    }

    /**
     * Metodo para identificar si un JInternalFrame ya ha sido abierto en el desktopPanel.
     * @param f - InternalFrame que se desea abrir.
     * @return si el InternalFrame que se desea abrir ya se encuentra abierto.
     */
    private boolean isOpen(JInternalFrame f) {
        JInternalFrame[] list = desktopPanel.getAllFrames();
        int i = 0;
        boolean open = false;
        while ((i < list.length) && (!open)) {
            if (f.getClass().getCanonicalName().equals(list[i].getClass().getCanonicalName())) {
                open = true;
            }
            i++;
        }
        return open;
    }
}

