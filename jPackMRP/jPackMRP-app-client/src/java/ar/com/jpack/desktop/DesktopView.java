/*
 * DesktopTestView.java
 */
package ar.com.jpack.desktop;

import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.UsuariosT;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ActionMap;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.jdesktop.application.Application;

/**
 * The application's main frame.
 */
public class DesktopView extends FrameView {

    /**
     * 
     * @param app
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

    /**
     * 
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

//    @Action
//    public void showRolesFrame() {
//        JInternalFrame x = verificarInternalFrame("ar.com.jpack.app.gui.RolesFrame");
//        if (x != null) {
//            desktopPanel.getDesktopManager().activateFrame(x);
//        } else {
//            RolesFrame rolesFrame = new RolesFrame();
//            rolesFrame.setVisible(true);
//            desktopPanel.add(rolesFrame);
//            desktopPanel.getDesktopManager().activateFrame(rolesFrame);
//        }
//        this.statusMessageLabel.setText("Roles por accion");
//    }

//    @Action
//    public void showArticulosFrame() {
//        JInternalFrame x = verificarInternalFrame("ar.com.jpack.app.gui.ArticulosFrame");
//        if (x != null) {
//            desktopPanel.getDesktopManager().activateFrame(x);
//        } else {
//            ArticulosFrame articulosFrame = new ArticulosFrame();
//            articulosFrame.setVisible(true);
//            desktopPanel.add(articulosFrame);
//            desktopPanel.getDesktopManager().activateFrame(articulosFrame);
//        }
//        this.statusMessageLabel.setText("Articulos por accion");
//    }
    /**
     * 
     */
    @Action
    public void closeAllFrames() {
        JInternalFrame[] internalFrames = desktopPanel.getAllFrames();
        int i = 0;
        while (i < internalFrames.length) {
            internalFrames[i].dispose();
            i++;
        }
    }

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
        mainPanel.add(desktopPanel, java.awt.BorderLayout.PAGE_START);

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

    private JInternalFrame verificarInternalFrame(String clase) {
        JInternalFrame[] internalFrames = desktopPanel.getAllFrames();
        JInternalFrame x = null;
        int i = 0;
        while ((i < internalFrames.length) && (x == null)) {
            JInternalFrame jInternalFrame = internalFrames[i];
            if (jInternalFrame.getClass().getCanonicalName().equals(clase)) {
                x = jInternalFrame;
            }
            i++;
        }

        return x;
    }

    /**
     * 
     */
    public void cargaInicial() {

        JMenu fileMenu = new JMenu();
        JMenuItem loginMenuItem = new JMenuItem();
        JMenuItem exitMenuItem = new JMenuItem();
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
            ArrayList<RolesT> rolesTs = (ArrayList<RolesT>) usuariosT.getIdRolCollection();
            if (rolesTs != null) {
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println(rolesTs.size());
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                //TODO ordenar rolesTs
                for (Iterator<RolesT> it = rolesTs.iterator(); it.hasNext();) {
                    RolesT rolesT = it.next();
                    if (rolesT.getIdRolPadre() == null) {
                        JMenu r = new JMenu();
                        r.setText(resourceMap.getString(rolesT.getComponente() + ".text")); // NOI18N
                        r.setName(rolesT.getComponente()); // NOI18N
                        menuBar.add(r);
                    }
                }

            }
        }

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setMenuBar(menuBar);
    }
}
