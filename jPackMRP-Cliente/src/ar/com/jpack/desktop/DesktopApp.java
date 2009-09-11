/*
 * DesktopApp.java
 */
package ar.com.jpack.desktop;

import ar.com.jpack.negocio.ArticulosFacadeRemote;
import ar.com.jpack.negocio.ClientesFacadeRemote;
import ar.com.jpack.negocio.DetalleproduccionFacadeRemote;
import ar.com.jpack.negocio.EstadosFacadeRemote;
import ar.com.jpack.negocio.RemitosFacadeRemote;
import ar.com.jpack.negocio.TiposComprobantesFacadeRemote;
import ar.com.jpack.negocio.ReportesFacadeRemote;
import ar.com.jpack.negocio.RolesFacadeRemote;
import ar.com.jpack.negocio.SetupFacadeRemote;
import ar.com.jpack.negocio.TiposDocumentoFacadeRemote;
import ar.com.jpack.negocio.TiposIvaFacadeRemote;
import ar.com.jpack.negocio.UnidadesmedidaFacadeRemote;
import ar.com.jpack.negocio.UsuariosFacadeRemote;
import ar.com.jpack.negocio.ActividadesFacadeRemote;
import ar.com.jpack.negocio.FeriadosFacadeRemote;
import ar.com.jpack.negocio.GruposmailsFacadeRemote;
import ar.com.jpack.negocio.ListaspreciosFacadeRemote;
import ar.com.jpack.negocio.MailsFacadeRemote;
import ar.com.jpack.negocio.MantenimientoFacadeRemote;
import ar.com.jpack.negocio.MaquinasFacadeRemote;
import ar.com.jpack.negocio.OrdenesproduccionFacadeRemote;
import ar.com.jpack.negocio.PreciosFacadeRemote;
import ar.com.jpack.negocio.ProveedoresFacadeRemote;
import ar.com.jpack.negocio.SellosFacadeRemote;
import ar.com.jpack.negocio.StockFacadeRemote;
import ar.com.jpack.negocio.TiposdesviosFacadeRemote;
import ar.com.jpack.negocio.TiposserviciosFacadeRemote;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import ar.com.jpack.transferencia.ActividadesT;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.transferencia.ComponentesT;
import ar.com.jpack.transferencia.DetOrdenesProduccionT;
import ar.com.jpack.transferencia.DetRtosIngresoT;
import ar.com.jpack.transferencia.DetalleProduccionT;
import ar.com.jpack.transferencia.DetalleRemitosT;
import ar.com.jpack.transferencia.DetalleRemitosTempT;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.FeriadosT;
import ar.com.jpack.transferencia.GruposMailsT;
import ar.com.jpack.transferencia.ListasPreciosT;
import ar.com.jpack.transferencia.MailsT;
import ar.com.jpack.transferencia.MantenimientoT;
import ar.com.jpack.transferencia.MaquinasT;
import ar.com.jpack.transferencia.OrdenesProduccionT;
import ar.com.jpack.transferencia.PreciosT;
import ar.com.jpack.transferencia.ProveedoresT;
import ar.com.jpack.transferencia.RemitosIngresoT;
import ar.com.jpack.transferencia.RemitosT;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.SellosT;
import ar.com.jpack.transferencia.SetupT;
import ar.com.jpack.transferencia.StockT;
import ar.com.jpack.transferencia.TiposComprobantesT;
import ar.com.jpack.transferencia.TiposDesviosT;
import ar.com.jpack.transferencia.TiposDocumentoT;
import ar.com.jpack.transferencia.TiposIvaT;
import ar.com.jpack.transferencia.TiposServiciosT;
import ar.com.jpack.transferencia.UnidadesMedidaT;
import ar.com.jpack.transferencia.UsuariosT;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;

/**
 * The main class of the application.
 * Clase principal de la aplicacion. Contiene el metodo main que da inicio a la aplicacion.
 * @author  jmhanun
 */
public class DesktopApp extends SingleFrameApplication {

    private static InitialContext contexto;
    private UsuariosFacadeRemote usuariosFacade;
    private RolesFacadeRemote rolesFacade;
    private ReportesFacadeRemote reportesFacade;
    private ClientesFacadeRemote clientesFacade;
    private MailsFacadeRemote mailsFacade;
    private ArticulosFacadeRemote articulosFacade;
    private UnidadesmedidaFacadeRemote unidadesMedidaFacade;
    private ActividadesFacadeRemote actividadesFacade;
    private TiposIvaFacadeRemote tiposIvaFacade;
    private MaquinasFacadeRemote maquinasFacade;
    private StockFacadeRemote stockFacade;
    private TiposDocumentoFacadeRemote tiposDocumentoFacade;
    private DetalleproduccionFacadeRemote detalleProduccionFacade;
    private TiposComprobantesFacadeRemote tiposComprobantesFacade;
    private RemitosFacadeRemote remitosFacade;
    private OrdenesproduccionFacadeRemote ordenesproduccionFacade;
    private EstadosFacadeRemote estadosFacade;
    private JDialog loginBox;
    private UsuariosT usuarioLogueado;
    private DesktopView desktopView;
    private SetupFacadeRemote setupFacade;

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
    private TiposdesviosFacadeRemote tiposDesviosFacade;
    private FeriadosFacadeRemote feriadosFacade;
    private MantenimientoFacadeRemote mantenimientoFacade;
    private TiposserviciosFacadeRemote tiposServiciosFacade;
    private PreciosFacadeRemote preciosFacade;
    private ListaspreciosFacadeRemote listaspreciosFacade;
    private GruposmailsFacadeRemote gruposmailsFacade;
    private SellosFacadeRemote sellosFacade;
    private ProveedoresFacadeRemote proveedoresFacade;

    public void sendSSLMessage(ArrayList<String> recipients, String subject,
            String message) {

        HashMap parametros = new HashMap();
        parametros.put("pDescripcion", "MAIL.SMTP.HOST");
        String smtpHostName = getSetupT(parametros).get(0).getValor();

        parametros = new HashMap();
        parametros.put("pDescripcion", "MAIL.SMTP.PORT");
        String smtpPort = getSetupT(parametros).get(0).getValor();

        parametros = new HashMap();
        parametros.put("pDescripcion", "MAIL.SMTP.SOCKETFACTORY.CLASS");
        String sslFactory = getSetupT(parametros).get(0).getValor();

        parametros = new HashMap();
        parametros.put("pDescripcion", "MAIL.SMTP.USUARIO");
        String usuario = getSetupT(parametros).get(0).getValor();

        parametros = new HashMap();
        parametros.put("pDescripcion", "MAIL.SMTP.PASSWORD");
        String pass = getSetupT(parametros).get(0).getValor();

        final Properties props = new Properties();
        props.put("mail.smtp.host", smtpHostName);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.socketFactory.port", smtpPort);
        props.put("mail.smtp.socketFactory.class", sslFactory);

        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.socketFactory.fallback", "false");

        props.put("usuario", usuario);
        props.put("pass", pass);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(props.getProperty("usuario"), props.getProperty("pass"));
                    }
                });

        session.setDebug(false);
        try {
            Message msg = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress(usuario);
            msg.setFrom(addressFrom);

            InternetAddress[] addressTo = new InternetAddress[recipients.size()];
            for (int i = 0; i < recipients.size(); i++) {
                addressTo[i] = new InternetAddress(recipients.get(i));
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);

// Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(message, "text/plain");
            Transport.send(msg);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "Apartentemente no tiene conexion a Internet. No es posible enviar el mail. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long getDiferenciaSegundos(Date inicial, Date ahora) {
        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();

// Set the date for both of the calendar instance
        cal1.setTime(inicial);
        cal2.setTime(ahora);

// Calculate difference in milliseconds
        Long diff = Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis());
// Calculate difference in seconds
        return (diff / 1000);
    }

    public String getFechaLiteral(Date fechaAcordada) {

        StringBuffer fechaLiteral = new StringBuffer();

        DateFormat fechaFormatter = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat fechaLargaFormatter = new SimpleDateFormat("'el ' EEEE dd ' de ' MMMM");
        DateFormat horaFormatter = new SimpleDateFormat("H");

        GregorianCalendar hoy = new GregorianCalendar();

        if (fechaFormatter.format(hoy.getTime()).equals(fechaFormatter.format(fechaAcordada))) {
            fechaLiteral.append("Hoy");
        } else {
            hoy.add(GregorianCalendar.DAY_OF_MONTH, 1);
            if (fechaFormatter.format(hoy.getTime()).equals(fechaFormatter.format(fechaAcordada))) {
                fechaLiteral.append("Mañana");
            } else {
                fechaLiteral.append(fechaLargaFormatter.format(fechaAcordada));
            }
        }

        int hora = Integer.parseInt(horaFormatter.format(fechaAcordada));
        switch (hora) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                fechaLiteral.append(" a la mañana");
                break;
            case 11:
            case 12:
            case 13:
            case 14:
                fechaLiteral.append(" al mediodia");
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                fechaLiteral.append(" a la tarde");
                break;
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                fechaLiteral.append(" a la noche");
                break;
        }
        return fechaLiteral.toString();
    }

    /**
     * Almacena en contexto inicial con la base de datos
     * @param contexto - InitialContext definido en jndi.properties
     */
    public void setContexto(InitialContext contexto) {
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
        System.setProperty("user.timezone", "America/Argentina/Cordoba");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        DesktopApp myApp = new DesktopApp();
        myApp.levantar(args);
    }

    private void levantar(String[] args) {
        try {
            Properties props = new Properties();

            InputStream io = this.getClass().getResourceAsStream("/ar/com/jpack/desktop/resources/jndi.properties");

            props.load(io);

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
            if (usuariosT.getUsuario() != null) {
                if (usuariosT.getContrasena() == null) {
                    usuariosT.setContrasena("");
                }
                try {
                    usuariosFacade = (UsuariosFacadeRemote) lookUp("ar.com.jpack.negocio.UsuariosFacadeRemote");
                    //valida los datos en la base y los asigna a usuarioLogueado
                    setUsuarioLogueado(usuariosFacade.validateUsuarioT(usuariosT));
                    if (getUsuarioLogueado().getIdUsuario() != null) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (NamingException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
                    Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
// Si da error de privilegios probar este codigo.......
//                } catch (Exception ex){
//                    JOptionPane.showMessageDialog(null, "Los privilegios no han sido asignados. Consulte al administrador.");
//                    Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
//                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Deprecated
    public List<UnidadesMedidaT> getUnidadesMedida() {
        try {
            unidadesMedidaFacade = (UnidadesmedidaFacadeRemote) lookUp("ar.com.jpack.negocio.UnidadesmedidaFacadeRemote");
            List<UnidadesMedidaT> unidadesMedida = unidadesMedidaFacade.findAllUnidadesMedidaT();
            if (unidadesMedida == null) {
                unidadesMedida = Collections.emptyList();
            }
            return unidadesMedida;
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Deprecated
    public void addTipoIva(TiposIvaT nuevoIva) {
        try {
            tiposIvaFacade = (TiposIvaFacadeRemote) lookUp("ar.com.jpack.negocio.TiposIvaFacadeRemote");
            tiposIvaFacade.addTipoIva(nuevoIva);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Double getAvanceProduccion(DetalleProduccionT detalleProduccionT) {
        try {
            detalleProduccionFacade = (DetalleproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.DetalleproduccionFacadeRemote");
            return detalleProduccionFacade.getAvanceProduccion(detalleProduccionT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        }
    }

    public Boolean getFeriado(Date fecha) {
        try {
            detalleProduccionFacade = (DetalleproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.DetalleproduccionFacadeRemote");
            return detalleProduccionFacade.getFeriado(fecha);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<RemitosT> getRemitosT(HashMap parametros) {
        try {
            remitosFacade = (RemitosFacadeRemote) lookUp("ar.com.jpack.negocio.RemitosFacadeRemote");
            return remitosFacade.getRemitosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene el siguiente numero de remito
     * @return devuelve el siguiente numero de remito como int
     */
    public int getNextRemito() {
        try {
            remitosFacade = (RemitosFacadeRemote) lookUp("ar.com.jpack.negocio.RemitosFacadeRemote");
            return remitosFacade.getNextRemito();
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * Obtiene el siguiente numero de instancia del detalleRemtioTemp
     * @return devuelve el siguiente numero de instancia del detalleRemtioTemp como int
     */
    public int getNextInstancia() {
        try {
            remitosFacade = (RemitosFacadeRemote) lookUp("ar.com.jpack.negocio.RemitosFacadeRemote");
            return remitosFacade.getNextInstancia();
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * Obtiene el stock de un Articulo
     * @param ArticuloT del que se desea conocer el stock
     * @return devuelve la cantidad de stock como double
     */
    public double getStockArticulo(ArticulosT articulosT) {
        try {
            articulosFacade = (ArticulosFacadeRemote) lookUp("ar.com.jpack.negocio.ArticulosFacadeRemote");
            return articulosFacade.getStockArticulo(articulosT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        }
    }

    /**
     * Obtiene el precio vigente de un Articulo
     * @param ArticuloT del que se desea conocer el precio
     * @return devuelve el precio como double
     */
    public double getPrecioArticuloVigente(ArticulosT articulosT) {
        try {
            articulosFacade = (ArticulosFacadeRemote) lookUp("ar.com.jpack.negocio.ArticulosFacadeRemote");
            return articulosFacade.getPrecioArticuloVigente(articulosT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        }
    }

    public List<ActividadesT> getActividadesT(HashMap parametros) {
        try {
            actividadesFacade = (ActividadesFacadeRemote) lookUp("ar.com.jpack.negocio.ActividadesFacadeRemote");
            return actividadesFacade.getActividadesT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ActividadesArticulosT> getActividadesArticulosT(HashMap parametros) {
        try {
            actividadesFacade = (ActividadesFacadeRemote) lookUp("ar.com.jpack.negocio.ActividadesFacadeRemote");
            return actividadesFacade.getActividadesArticulosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Mails filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdUsuario</b>  filtra por 'eq' idUsuario (Integer) <br>
     * <b>pIdGrupoMail</b>    filtra por 'eq' idGrupoMail (Integer) <br>
     * <b>pGrupoMail</b> filtra por 'like AnyWhere' grupoMail (String) <br>
     * <b>pJoinGruposMails</b>  obliga a Joinear con GruposMails<br>
     * <b>pJoinUsuarios</b>  obliga a Joinear con Usuarios<br>
     * @return devuelve la lista de los Mails que cumplan con el filtro
     */
    public List<MailsT> getMailsT(HashMap parametros) {
        try {
            mailsFacade = (MailsFacadeRemote) lookUp("ar.com.jpack.negocio.MailsFacadeRemote");
            return mailsFacade.getMailsT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<TiposDesviosT> getTiposDesviosT(HashMap parametros) {
        try {
            tiposDesviosFacade = (TiposdesviosFacadeRemote) lookUp("ar.com.jpack.negocio.TiposdesviosFacadeRemote");
            return tiposDesviosFacade.getTiposDesviosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void insertDesvioT(Integer idDetalleProduccion, Integer idTipoDesvio, String comentario) {
        try {
            tiposDesviosFacade = (TiposdesviosFacadeRemote) lookUp("ar.com.jpack.negocio.TiposdesviosFacadeRemote");
            tiposDesviosFacade.insertDesvioT(idDetalleProduccion, idTipoDesvio, comentario);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene la lista de Usuarios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdUsuario</b>  filtra por 'eq' idUsuario (Integer) <br>
     * <b>pUsuario</b>    filtra por 'like AnyWhere' usuario (String) <br>
     * <b>pContrasena</b> filtra por 'like AnyWhere' contrasena (String) <br>
     * <b>pNombres</b>    filtra por 'like AnyWhere' nombres (String) <br>
     * <b>pApellidos</b>  filtra por 'like AnyWhere' apellidos (String) <br>
     * <b>pMails</b>      filtra por 'like AnyWhere' mails (String) <br>
     * <b>pTelefonos</b>  filtra por 'like AnyWhere' telefonos (String) <br>
     * <b>pJoinEstado</b> obliga a Joinear con Estados <br>
     * <b>pIdEstado</b>   filtra por 'eq' idEstado (Integer) (debe haber sido joineado con Estado) <br>
     * <b>pJoinRoles</b>  obliga a Joinear con Roles <br>
     * @return devuelve la lista de los Usuarios que cumplan con el filtro
     */
    public List<UsuariosT> getUsuariosT(HashMap parametros) {
        try {
            usuariosFacade = (UsuariosFacadeRemote) lookUp("ar.com.jpack.negocio.UsuariosFacadeRemote");
            return usuariosFacade.getUsuariosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Maquinas filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMaquina</b>  filtra por 'eq' idMaquina (Integer) <br>
     * <b>pMantenimiento</b>  filtra si horasUso >= horasMantenimiento<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pJoinActividades</b>  obliga a Joinear con Actividades<br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * @return devuelve la lista de los Maquinas que cumplan con el filtro
     */
    public List<MaquinasT> getMaquinasT(HashMap parametros) {
        try {
            maquinasFacade = (MaquinasFacadeRemote) lookUp("ar.com.jpack.negocio.MaquinasFacadeRemote");
            return maquinasFacade.getMaquinasT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<PreciosT> getPreciosT(HashMap parametros) {
        try {
            preciosFacade = (PreciosFacadeRemote) lookUp("ar.com.jpack.negocio.PreciosFacadeRemote");
            return preciosFacade.getPreciosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<GruposMailsT> getGruposMailsT(HashMap parametros) {
        try {
            gruposmailsFacade = (GruposmailsFacadeRemote) lookUp("ar.com.jpack.negocio.GruposmailsFacadeRemote");
            return gruposmailsFacade.getGruposMailsT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ListasPreciosT> getListasPreciosT(HashMap parametros) {
        try {
            listaspreciosFacade = (ListaspreciosFacadeRemote) lookUp("ar.com.jpack.negocio.ListaspreciosFacadeRemote");
            return listaspreciosFacade.getListasPreciosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Tipos de comprobantes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTipoComprobante</b>  filtra por 'eq' idTipoComprobante (Integer) <br>
     * @return devuelve la lista de los Tipos de comprabantes que cumplan con el filtro
     */
    public List<TiposComprobantesT> getTiposComprobantesT(HashMap parametros) {
        try {
            tiposComprobantesFacade = (TiposComprobantesFacadeRemote) lookUp("ar.com.jpack.negocio.TiposComprobantesFacadeRemote");
            return tiposComprobantesFacade.getTiposComprobantesT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Stock filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdStock</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pJoinArticulos</b>  obliga a Joinear con Articulos<br>
     * @return devuelve la lista de los Mails que cumplan con el filtro
     */
    List<StockT> getStockT(HashMap parametros) {
        try {
            stockFacade = (StockFacadeRemote) lookUp("ar.com.jpack.negocio.StockFacadeRemote");
            return stockFacade.getStockT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Articulos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdArticulos</b>   filtra por 'eq' idArticulo (Integer) <br>
     * <b>pCodigo</b>        filtra por 'like AnyWhere' codigo (String) <br>
     * <b>pDescripcion</b>   filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pFinal</b>   filtra por 'eq' articuloFinal (String) <br>
     * <b>pImprimible</b>   filtra por 'eq' imprimible (String) <br>
     * @return devuelve la lista de los Articulos que cumplan con el filtro <br>
     */
    public List<ArticulosT> getArticulosT(HashMap parametros) {
        try {
            articulosFacade = (ArticulosFacadeRemote) lookUp("ar.com.jpack.negocio.ArticulosFacadeRemote");
            return articulosFacade.getArticulosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<SellosT> getSellosT(HashMap parametros) {
        try {
            sellosFacade = (SellosFacadeRemote) lookUp("ar.com.jpack.negocio.SellosFacadeRemote");
            return sellosFacade.getSellosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Mantenimientos filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdMantenimiento</b>  filtra por 'eq' idMantenimiento (Integer) <br>
     * <b>pFechaFinNull</b>  filtra por 'isNull' fechaFin (Integer) <br>
     * <b>pJoinMaquinas</b>  obliga a Joinear con Maquinas<br>
     * <b>pJoinTiposServicios</b>  obliga a Joinear con TiposServicios<br>
     * @return devuelve la lista de los Mantenimientos que cumplan con el filtro
     */
    public List<MantenimientoT> getMantenimientoT(HashMap parametros) {
        try {
            mantenimientoFacade = (MantenimientoFacadeRemote) lookUp("ar.com.jpack.negocio.MantenimientoFacadeRemote");
            return mantenimientoFacade.getMantenimientoT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Estados filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdEstados</b>           filtra por 'eq' idUsuario (Integer) <br>
     * <b>pDescripcionEstado</b>   filtra por 'like AnyWhere' descripcion del Estado (String) <br>
     * <b>pNotas</b>               filtra por 'like AnyWhere' notas (String) <br>
     * <b>pJoinDominios</b>        obliga a Joinear con Dominios <br>
     * <b>pIdDominio</b>           filtra por 'eq' idDominio (Integer) <br>
     * <b>pDescripcionDominio</b>  filtra por 'like AnyWhere' descripcion del Dominio (String) <br>
     * @return devuelve la lista de los Estados que cumplan con el filtro <br>
     */
    public List<EstadosT> getEstadosT(HashMap parametros) {
        try {
            estadosFacade = (EstadosFacadeRemote) lookUp("ar.com.jpack.negocio.EstadosFacadeRemote");
            return estadosFacade.getEstadosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de TiposServicios filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdTiposServicios</b>  filtra por 'eq' idTiposServicios(Integer) <br>
     * @return devuelve la lista de los TiposServicios que cumplan con el filtro
     */
    public List<TiposServiciosT> getTiposServiciosT(HashMap parametros) {
        try {
            tiposServiciosFacade = (TiposserviciosFacadeRemote) lookUp("ar.com.jpack.negocio.TiposserviciosFacadeRemote");
            return tiposServiciosFacade.getTiposServiciosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Roles filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdRol</b>        filtra por 'eq' idRol (Integer) <br>
     * <b>pRol</b>          filtra por 'like AnyWhere' rol (String) <br>
     * <b>pDescripcion</b>  filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pComponente</b>   filtra por 'like AnyWhere' componente (String) <br>
     * <b>pFuncion</b>      filtra por 'like AnyWhere' funcion (String) <br>
     * <b>pOrden</b>        filtra por 'eq' orden (int) <br>
     * <b>pOrdenHermano</b> filtra por 'eq' ordenHermano (int) <br>
     * <b>pJoinUsuarios</b> obliga a Joinear con Usuarios <br>
     * <b>pIdUsuario</b>    filtra por 'eq' idUsuario (Integer) (debe haber sido joineado con Estado) <br>
     * @return devuelve la lista de los Roles que cumplan con el filtro
     */
    public List<RolesT> getRolesT(HashMap parametros) {
        try {
            rolesFacade = (RolesFacadeRemote) lookUp("ar.com.jpack.negocio.RolesFacadeRemote");
            return rolesFacade.getRolesT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Clientes filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdCliente</b>    filtra por 'eq' idCliente (Integer) <br>
     * <b>pNombres</b>      filtra por 'like AnyWhere' nombres (String) <br>
     * <b>pCuit</b>         filtra por 'like AnyWhere' cuit (String) <br>
     * @return devuelve la lista de los Clientes que cumplan con el filtro
     */
    public List<ClientesT> getClientesT(HashMap parametros) {
        try {
            clientesFacade = (ClientesFacadeRemote) lookUp("ar.com.jpack.negocio.ClientesFacadeRemote");
            return clientesFacade.getClientesT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ProveedoresT> getProveedoresT(HashMap parametros) {
        try {
            proveedoresFacade = (ProveedoresFacadeRemote) lookUp("ar.com.jpack.negocio.ProveedoresFacadeRemote");
            return proveedoresFacade.getProveedoresT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<RolesT> getMenuesT(boolean isMenu) {
        try {
            rolesFacade = (RolesFacadeRemote) lookUp("ar.com.jpack.negocio.RolesFacadeRemote");
            return rolesFacade.getMenuesT(isMenu);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ComponentesT> getComponentesT(HashMap parametros) {
        try {
            articulosFacade = (ArticulosFacadeRemote) lookUp("ar.com.jpack.negocio.ArticulosFacadeRemote");
            return articulosFacade.getComponentesT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<UnidadesMedidaT> getUnidadesMedidaT(HashMap parametros) {
        try {
            unidadesMedidaFacade = (UnidadesmedidaFacadeRemote) lookUp("ar.com.jpack.negocio.UnidadesmedidaFacadeRemote");
            return unidadesMedidaFacade.getUnidadesMedidaT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<TiposIvaT> getTiposIvaT(HashMap parametros) {
        try {
            tiposIvaFacade = (TiposIvaFacadeRemote) lookUp("ar.com.jpack.negocio.TiposIvaFacadeRemote");
            return tiposIvaFacade.getTiposIvaT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<TiposDocumentoT> getTiposDocumentoT(HashMap parametros) {
        try {
            tiposDocumentoFacade = (TiposDocumentoFacadeRemote) lookUp("ar.com.jpack.negocio.TiposDocumentoFacadeRemote");
            return tiposDocumentoFacade.getTiposDocumentoT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<OrdenesProduccionT> getOrdenesProduccionT(HashMap parametros) {
        try {
            ordenesproduccionFacade = (OrdenesproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.OrdenesproduccionFacadeRemote");
            return ordenesproduccionFacade.getOrdenesProduccionT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de DetalleProduccion filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdDetalleProduccion</b>  filtra por 'eq' idStock (Integer) <br>
     * <b>pFechaInicioEstimadaLT</b>  filtra por 'lt' fechaInicioEstimada (Date) <br>
     * <b>pFechaFinEstimadaGT</b>  filtra por 'gt' pFechaFinEstimada (Date) <br>
     * <b>pFechaInicioEstimada</b>  filtra por 'between' fechaInicioEstimada (Date) <br>
     * <b>pFechaDesdeEstimada</b> ; <b>pFechaHastaEstimada</b> <br>
     * <b>pIdEstado</b>  filtra por 'eq' idEstado (Integer) <br>
     * <b>pJoinMaquinas</b> obliga a Joinear con Maquinas<br>
     * <b>pJoinEstados</b>  obliga a Joinear con Estados<br>
     * <b>pJoinOrdenes</b>  obliga a Joinear con Detalle de Ordenes de Produccion<br>
     * @return devuelve la lista de los DetalleProduccion que cumplan con el filtro
     */
    public List<DetalleProduccionT> getDetalleProduccionT(HashMap parametros) {
        try {
            detalleProduccionFacade = (DetalleproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.DetalleproduccionFacadeRemote");
            return detalleProduccionFacade.getDetalleProduccionT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Actualiza o crea un usuarioT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     *
     * @param usuariosT contiene los datos del usuario a actualizar
     * @param contrasenia si es true la contraseña ha sido modificada
     * @return devuelve el usuarioT actualizado
     */
    public UsuariosT updateUsuariosT(UsuariosT usuariosT, boolean contrasenia) throws javax.ejb.EJBException {
        try {
            usuariosFacade = (UsuariosFacadeRemote) lookUp("ar.com.jpack.negocio.UsuariosFacadeRemote");
            return usuariosFacade.updateUsuariosT(usuariosT, contrasenia);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void updateComponentesT(ArrayList<ComponentesT> listDto) {
        try {
            articulosFacade = (ArticulosFacadeRemote) lookUp("ar.com.jpack.negocio.ArticulosFacadeRemote");
            articulosFacade.updateComponentesT(listDto);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateActividadesArticulosT(ArrayList<ActividadesArticulosT> listDto) {
        try {
            actividadesFacade = (ActividadesFacadeRemote) lookUp("ar.com.jpack.negocio.ActividadesFacadeRemote");
            actividadesFacade.updateActividadesArticulosT(listDto);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArticulosT updateArticulosT(ArticulosT dto) {
        try {
            articulosFacade = (ArticulosFacadeRemote) lookUp("ar.com.jpack.negocio.ArticulosFacadeRemote");
            return articulosFacade.updateArticulosT(dto);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public MantenimientoT updateMantenimientoT(MantenimientoT dto) {
        try {
            mantenimientoFacade = (MantenimientoFacadeRemote) lookUp("ar.com.jpack.negocio.MantenimientoFacadeRemote");
            return mantenimientoFacade.updateMantenimientoT(dto);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Actualiza o crea un rolT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     *
     * @param rolesT contiene los datos del rol a actualizar
     * @return devuelve el rolT actualizado
     */
    public RolesT updateRolesT(RolesT rolesT) {
        try {
            rolesFacade = (RolesFacadeRemote) lookUp("ar.com.jpack.negocio.RolesFacadeRemote");
            return rolesFacade.updateRolesT(rolesT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Actualiza o crea un remitoT recibido por parametro
     * Si existe, se actualiza. Si no existe, se crea.
     *
     * @param remitosT contiene los datos del remito a actualizar
     * @return devuelve el remitoT actualizado
     */
    public RemitosT updateRemitosT(RemitosT remitosT, List<DetalleRemitosT> detallesRemitosT) {
        try {
            remitosFacade = (RemitosFacadeRemote) lookUp("ar.com.jpack.negocio.RemitosFacadeRemote");
            return remitosFacade.updateRemitosT(remitosT, detallesRemitosT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public RemitosIngresoT updateRemitosIngresosT(RemitosIngresoT remito, ArrayList<DetRtosIngresoT> listDto) {
        try {
            remitosFacade = (RemitosFacadeRemote) lookUp("ar.com.jpack.negocio.RemitosFacadeRemote");
            return remitosFacade.updateRemitosIngresosT(remito, listDto);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setEstadoOP(Integer idOP, Integer newEstado) {
        try {
            ordenesproduccionFacade = (OrdenesproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.OrdenesproduccionFacadeRemote");
            ordenesproduccionFacade.setEstadoOP(idOP, newEstado);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setEstadoProduccion(Integer idDetalleProduccion, Integer idEstado, Integer idEstadoAnterior, Date fecha) {
        try {
            detalleProduccionFacade = (DetalleproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.DetalleproduccionFacadeRemote");
            detalleProduccionFacade.setEstadoProduccion(idDetalleProduccion, idEstado, idEstadoAnterior, fecha);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Integer getTiempoEstimadoProduccion(Integer idDetalleProduccion){
        try {
            detalleProduccionFacade = (DetalleproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.DetalleproduccionFacadeRemote");
            return detalleProduccionFacade.getTiempoEstimadoProduccion(idDetalleProduccion);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Integer getTiempoRealProduccion(Integer idDetalleProduccion) {
        try {
            detalleProduccionFacade = (DetalleproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.DetalleproduccionFacadeRemote");
            return detalleProduccionFacade.getTiempoRealProduccion(idDetalleProduccion);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void updateDetalleProduccion(DetalleProduccionT detalleProduccionT) {
        try {
            detalleProduccionFacade = (DetalleproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.DetalleproduccionFacadeRemote");
            detalleProduccionFacade.updateDetalleProduccion(detalleProduccionT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OrdenesProduccionT updateOrdenesProduccionT(OrdenesProduccionT opT, ArrayList<DetOrdenesProduccionT> listaDetalleOPT) {
        try {
            ordenesproduccionFacade = (OrdenesproduccionFacadeRemote) lookUp("ar.com.jpack.negocio.OrdenesproduccionFacadeRemote");
            return ordenesproduccionFacade.updateOrdenesProduccionT(opT, listaDetalleOPT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Date updateRemitosTempT(List<DetalleRemitosTempT> detalleRemitosTempT) {
        try {
            remitosFacade = (RemitosFacadeRemote) lookUp("ar.com.jpack.negocio.RemitosFacadeRemote");
            return remitosFacade.updateRemitosTempT(detalleRemitosTempT);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Integer deleteFeriadoT(Integer idFeriado) {
        try {
            feriadosFacade = (FeriadosFacadeRemote) lookUp("ar.com.jpack.negocio.FeriadosFacadeRemote");
            return feriadosFacade.deleteFeriadoT(idFeriado);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Integer deleteClientesT(Integer idCliente) {
        try {
            clientesFacade = (ClientesFacadeRemote) lookUp("ar.com.jpack.negocio.ClientesFacadeRemote");
            return clientesFacade.deleteClienteT(idCliente);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Integer deleteGrupoMailT(Integer idGrupoMail) {
        try {
            gruposmailsFacade = (GruposmailsFacadeRemote) lookUp("ar.com.jpack.negocio.GruposmailsFacadeRemote");
            return gruposmailsFacade.deleteGruposMailsT(idGrupoMail);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<FeriadosT> getFeriadosT(HashMap parametros) {
        try {
            feriadosFacade = (FeriadosFacadeRemote) lookUp("ar.com.jpack.negocio.FeriadosFacadeRemote");
            return feriadosFacade.getFeriadosT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de Setup filtrados por el Hasmap
     * @param parametros <br>
     * Lista de parametros: <br>
     * <b>pIdSetup</b>           filtra por 'eq' idRol (Integer) <br>
     * <b>pDescripcion</b>       filtra por 'like AnyWhere' descripcion (String) <br>
     * <b>pValor</b>             filtra por 'like AnyWhere' valor (String) <br>
     * <b>pFechaModificacion</b> filtra por 'eq' fechaModificacion (Date) <br>
     * <b>pJoinUsuarios</b>      obliga a Joinear con Usuarios <br>
     * <b>pIdUsuario</b>         filtra por 'eq' idUsuario (Integer) (debe haber sido joineado con Estado) <br>
     * @return devuelve la lista de los Setup que cumplan con el filtro
     */
    public List<SetupT> getSetupT(HashMap parametros) {
        try {
            setupFacade = (SetupFacadeRemote) lookUp("ar.com.jpack.negocio.SetupFacadeRemote");
            return setupFacade.getSetupT(parametros);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getValorSetup(Integer idSetup) {
        try {
            setupFacade = (SetupFacadeRemote) lookUp("ar.com.jpack.negocio.SetupFacadeRemote");
            return setupFacade.getValorSetup(idSetup);
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un NamingException. Consulte al administrador.");
            Logger.getLogger(DesktopApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Devuelve el reporte solicitado
     * @param nombreReporte String con el nombre del reporte (sin extension)
     * @param parametro HashMap con los parametros. Si no tiene parametros = null
     * @return
     */
    public JasperPrint getReporte(String nombreReporte, HashMap parametro) {
        try {
            JasperPrint retorno = null;
            reportesFacade = (ReportesFacadeRemote) lookUp("ar.com.jpack.negocio.ReportesFacadeRemote");
            retorno = reportesFacade.getReporte(nombreReporte, parametro);
            return retorno;
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
        setUsuarioLogueado(new UsuariosT());
        loginBox = new DesktopLoginBox(mainFrame);
        loginBox.setLocationRelativeTo(mainFrame);
        getApplication().show(loginBox);
    }

    private synchronized Object lookUp(String canonicalName) throws NamingException {
        return DesktopApp.getContexto().lookup(canonicalName);
    }
}