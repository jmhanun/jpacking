/*
 * DesktopTestApp.java
 */
package ar.com.jpack.desktop;

import ar.com.jpack.negocio.RolesFacadeRemote;
import ar.com.jpack.negocio.UsuariosFacadeRemote;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.UsuariosT;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 * Clase principal de la aplicacion. Contiene el metodo main que da inicio a la aplicacion.
 * @author  jmhanun
 */
public class DesktopApp extends SingleFrameApplication {

    private static InitialContext contexto;
    private static UsuariosFacadeRemote usuariosFacade;
    private static RolesFacadeRemote rolesFacade;
    private JDialog loginBox;
    private UsuariosT usuarioLogueado;
    private DesktopView desktopView;

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DesktopTestApp
     */
    public static DesktopApp getApplication() {
        return Application.getInstance(DesktopApp.class);
    }

    /**
     * Obtiene el contexto inicial (InitialContext) con la base de datos definido en jndi.properties
     * @return contexto - InitialContext de la aplicación
     */
    public static InitialContext getContexto() {
        return contexto;
    }

    /**
     * Almacena en contexto inicial con la base de datos
     * @param contexto - InitialContext definido en jndi.properties
     */
    public static void setContexto(InitialContext contexto) {
        DesktopApp.contexto = contexto;
    }

    /**
     * Obtiene el usuario logueado en la aplicacion
     * @return Devuelve el usuario logueado en la aplicacion
     */
    public UsuariosT getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * Almacena el usuario logueado en el sistema
     * @param usuarioLogueado
     */
    public void setUsuarioLogueado(UsuariosT usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * Obtiene el DesktopView definido en el DesktopApp.
     * Se prefiere usar este metodo al DesktopApp.getApplication().getMainView() 
     * para poder tener acceso a los metodos propios del DesktopView como por
     * ejemplo: cargaInicial() Metodo usado para la carga de la barra del menu
     * dependiendo de los roles asignados al usuario logueado.
     * @return - Devuelve el MainFrame del DesktopApp
     * 
     */
    public DesktopView getDesktopView() {
        return desktopView;
    }

    /**
     * Asigna el MainView del DesktopApp.
     * @param desktopView - MainView del DesktopApp
     */
    public void setDesktopView(DesktopView desktopView) {
        this.desktopView = desktopView;
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
            launch(DesktopApp.class, args);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un IOException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        setDesktopView(new DesktopView(this));
        show(getDesktopView());
    }

    /**
     * Una vez terminado el startup de la aplicacion se ejecuta el ready
     * Muestra la ventana de login.
     */
    @Override
    protected void ready() {
        showLoginBox();
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
     * Metodo que sirve para identificar si un usuario ingresado como parametro
     * forma parte de la base de datos de usuario.
     * El parametro enviado es un objeto UsuariosT que solo tiene asignados los
     * valores usuario y contrasena, valores estos que son usados para hacer la
     * busqueda en la base de datos.
     * De encontrar un registro en la base de datos con los valores ingresado,
     * el atributo usuarioLogueado de DesktopApp es seteado con todos los datos
     * del usuario, asi como tambien los roles activos.
     * 
     * @param usuariosT - datos de usuariosT que habrá que validar si existe en la BD
     * @return - true si el usuariosT existe en la BD
     * - false si el usuarioT no existe en la BD
     */
    public Boolean isUsuario(UsuariosT usuariosT) {
        if (usuariosT != null) {
            if (usuariosT.getUsuario() != null && usuariosT.getContrasena() != null) {
                try {
                    usuariosFacade = (UsuariosFacadeRemote) lookUp("ar.com.jpack.negocio.UsuariosFacadeRemote");
                    //valida los datos en la base y los asigna a usuarioLogueado
                    setUsuarioLogueado(usuariosFacade.validarUsuario(usuariosT));
                    if (getUsuarioLogueado().getIdUsuario() != null) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (NamingException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
                    Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public List<UsuariosT> getAllUsuarios() {
        try {
            usuariosFacade = (UsuariosFacadeRemote) lookUp("ar.com.jpack.negocio.UsuariosFacadeRemote");
            return usuariosFacade.findAllUsuariosT();
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<RolesT> getAllRoles() {
        try {
            rolesFacade = (RolesFacadeRemote) lookUp("ar.com.jpack.negocio.RolesFacadeRemote");
            return rolesFacade.findAllUsuariosT();
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public UsuariosT grabarUsuarioT(UsuariosT usuariosT) {
        try {
            usuariosFacade = (UsuariosFacadeRemote) lookUp("ar.com.jpack.negocio.UsuariosFacadeRemote");
            return usuariosFacade.editUsuariosT(usuariosT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Muestra el JDialog de Login
     */
    @Action
    public void showLoginBox() {
        getDesktopView().closeAllFrames();
        JFrame mainFrame = getApplication().getMainFrame();
        loginBox = new DesktopLoginBox(mainFrame);
        loginBox.setLocationRelativeTo(mainFrame);
        getApplication().show(loginBox);
    }

    private synchronized Object lookUp(String canonicalName) throws NamingException {
        return DesktopApp.getContexto().lookup(canonicalName);
    }
}