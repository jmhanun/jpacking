/*
 * DesktopTestApp.java
 */
package ar.com.jpack.desktop;

import ar.com.jpack.negocio.UsuariosFacadeRemote;
import ar.com.jpack.transferencia.UsuariosT;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class DesktopApp extends SingleFrameApplication {

    private static InitialContext contexto;
    private static UsuariosFacadeRemote usuariosFacade;
    private JDialog loginBox;
    private UsuariosT usuarioLogueado;
    private DesktopView desktopView;

    public void setDesktopView(DesktopView desktopView) {
        this.desktopView = desktopView;
    }

    public DesktopView getDesktopView() {
        return desktopView;
    }

    /**
     * 
     * @return contexto - InitialContext de la aplicación
     */
    public static InitialContext getContexto() {
        return contexto;
    }

    /**
     * 
     * @param contexto
     */
    public static void setContexto(InitialContext contexto) {
        DesktopApp.contexto = contexto;
    }

    /**
     * 
     * @return
     */
    public UsuariosT getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * 
     * @param usuarioLogueado
     */
    public void setUsuarioLogueado(UsuariosT usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        setDesktopView(new DesktopView(this));
        show(getDesktopView());
    }

    @Override
    protected void ready() {
        JFrame mainFrame = getApplication().getMainFrame();
        loginBox = new DesktopLoginBox(mainFrame);
        loginBox.setLocationRelativeTo(mainFrame);
        getApplication().show(loginBox);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DesktopTestApp
     */
    public static DesktopApp getApplication() {
        return Application.getInstance(DesktopApp.class);
    }

    /**
     * Main method launching the application.
     * Además instancia la variable DesktopApp.contexto
     * que da 'visibilidad' a los objetos EJB
     * @param args 
     */
    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("jndi.properties"));
            setContexto(new InitialContext(props));
        } catch (NamingException ex) {
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(DesktopApp.class, args);
    }

    /**
     * 
     * @param usuariosT - Usuario que habrá que validar si existe en la BD
     * @return - true si el usuarioT existe en la BD
     * - false si el usuarioT no existe en la BD
     */
    public Boolean isUsuario(UsuariosT usuariosT) {
        try {
            usuariosFacade = (UsuariosFacadeRemote) DesktopApp.getContexto().lookup("ar.com.jpack.negocio.UsuariosFacadeRemote");
            setUsuarioLogueado(usuariosFacade.validarUsuario(usuariosT));
            if (getUsuarioLogueado().getIdUsuario() != null) {
                return true;
            } else {
                return false;
            }
        } catch (NamingException ex) {
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
