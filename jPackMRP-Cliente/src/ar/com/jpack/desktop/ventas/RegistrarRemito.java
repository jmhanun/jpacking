/*
 * RegistrarRemito.java
 *
 * Created on 7 de junio de 2009, 16:45
 */
package ar.com.jpack.desktop.ventas;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.DesktopView;
import ar.com.jpack.desktop.produccion.ABMArticulos;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.DetalleRemitosTableModel;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import ar.com.jpack.transferencia.ActividadesT;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.transferencia.DetOrdenesProduccionPKT;
import ar.com.jpack.transferencia.DetOrdenesProduccionT;
import ar.com.jpack.transferencia.DetalleRemitosPKT;
import ar.com.jpack.transferencia.DetalleRemitosT;
import ar.com.jpack.transferencia.DetalleRemitosTempPKT;
import ar.com.jpack.transferencia.DetalleRemitosTempT;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.OrdenesProduccionT;
import ar.com.jpack.transferencia.PrioridadesT;
import ar.com.jpack.transferencia.RemitosT;
import ar.com.jpack.transferencia.RolesT;
import ar.com.jpack.transferencia.TiposComprobantesT;
import ar.com.jpack.transferencia.UsuariosT;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author  jmhanun
 */
public class RegistrarRemito extends CustomInternalFrame<DetalleRemitosT> {

    /** Creates new form RegistrarRemito */
    public RegistrarRemito() {
        super(new DetalleRemitosT());
        initComponents();
        setTotal(0.0);
        DateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date hoy = new Date();
        txtFecha.setText(fechaFormat.format(hoy));
        btnModificar.setEnabled(false);

        setListDto(new ArrayList<DetalleRemitosT>());

        tableModel = new DetalleRemitosTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener(this));
        tblDetalleRemito.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblDetalleRemito.setRowSorter(sorter);


        tblDetalleRemito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        contadorDetalle = 0;

        remito = new RemitosT();

    }

    @Action
    public void modificar() {
        if (tblDetalleRemito.getSelectedRow() != - 1) {
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un articulo");
        }

    }

    @Action
    public void eliminar() {
        if (tblDetalleRemito.getSelectedRow() != - 1) {
            DetalleRemitosT x = (DetalleRemitosT) tableModel.removeRow(sorter.convertRowIndexToModel(tblDetalleRemito.getSelectedRow()));
            setTotal(getTotal() - x.getImporte());
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un articulo");
        }
    }

    @Action
    public void buscarCliente() {



        DesktopApp.getApplication().getDesktopView().setPadre(this);

        DesktopApp.getApplication().getDesktopView().showClientes().run();

        clientesOpenFrame = (ABMClientes) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.ventas.ABMClientes");

        clientesOpenFrame.setPadre(this);
        clientesOpenFrame.habilitarBtnSeleccionar(true);
        clientesOpenFrame.setNumeroCliente(txtNumeroCliente.getText());
        clientesOpenFrame.setCuitCliente(txtCuit.getText());
        clientesOpenFrame.setNombreCliente(txtCliente.getText());
        clientesOpenFrame.buscar();

    }

    @Action
    public void agregar() {
        contadorDetalle++;
        DetalleRemitosPKT id = new DetalleRemitosPKT(contadorDetalle, 0);

        setDto(new DetalleRemitosT());
        getDto().setDetalleremitosPK(id);


        DesktopApp.getApplication().getDesktopView().setPadre(this);
        DesktopApp.getApplication().getDesktopView().showArticulos().run();

        articulosOpenFrame = (ABMArticulos) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.produccion.ABMArticulos");

        articulosOpenFrame.setPadre(this);
        articulosOpenFrame.habilitarBtnSeleccionar(true);
        articulosOpenFrame.habilitarChkFinal(false, true);
        articulosOpenFrame.buscar();
    }

    @Action
    public void aplicar() {
        //Verificar que haya al menos un item
        cuerpoMail = new StringBuffer();
        cuerpoMail2 = new StringBuffer();
        importeTotal = 0.0;
        if (tableModel.getRowCount() > 0) {

            //Verificar que haya cliente seleccionado
            if (remito.getIdCliente() != null) {
                //Verificar que haya cantidades y precios en los items
                //Verificar que haya stock
                boolean cantidadOk = true;
                double stock = 0.0;
                ArrayList<DetalleRemitosT> items = getListDto();
                for (DetalleRemitosT detalleRemitoSeleccionado : items) {
                    importeTotal += detalleRemitoSeleccionado.getImporte();
                    if (detalleRemitoSeleccionado.getCantidad() <= 0) {
                        cantidadOk = false;
                    }
                    if (detalleRemitoSeleccionado.getPrecioUnitario() <= 0) {
                        cantidadOk = false;
                    }

                    if (cantidadOk) {
                        stock = DesktopApp.getApplication().getStockArticulo(detalleRemitoSeleccionado.getIdArticulo());
                        if (stock < 0) {
                            stock = 0;
                        }
                        int nuevaCantidad = (int) (detalleRemitoSeleccionado.getCantidad() - stock);
                        if (nuevaCantidad < 0) {
                            nuevaCantidad = 0;
                        }
                        detalleRemitoSeleccionado.setSaldoOP(nuevaCantidad);

                        // Si no hay suficiente stock crear un DetalleTemporal por la diferencia
                        if (nuevaCantidad > 0) {

                            int instancia = DesktopApp.getApplication().getNextInstancia();
                            int idArticulo = detalleRemitoSeleccionado.getIdArticulo().getIdArticulo();

                            parametros = new HashMap();
                            parametros.put("pIdArticulo", idArticulo);
                            parametros.put("pJoinActividadesxArticulos", true);

                            //obtiene lista de actividades joineada con actividadesxarticulos
                            //donde el idArticulo = articulo faltante en stock
                            ArrayList<ActividadesT> actividadesTList = (ArrayList<ActividadesT>) DesktopApp.getApplication().getActividadesT(parametros);

                            //recorro la lista de actividades joineada con actividadesxarticulos
                            //donde el idArticulo = articulo faltante en stock
                            for (ActividadesT actividadesT : actividadesTList) {
                                int idActividad = actividadesT.getIdActividad();
                                DetalleRemitosTempPKT nuevoDetallePK = new DetalleRemitosTempPKT(instancia, idArticulo, idActividad);

                                parametros = new HashMap();
                                parametros.put("pIdArticulo", idArticulo);
                                parametros.put("pIdActividad", idActividad);

                                ArrayList<ActividadesArticulosT> actividadesArticulosT = (ArrayList<ActividadesArticulosT>) DesktopApp.getApplication().getActividadesArticulosT(parametros);

                                int orden = actividadesArticulosT.get(0).getOrden();
                                DetalleRemitosTempT nuevoDetalle = new DetalleRemitosTempT(nuevoDetallePK, nuevaCantidad, null, orden);
                                detalleTemporal.add(nuevoDetalle);
                            }
                        }
                    }
                }
                if (cantidadOk) {
                    remito.setImporte(importeTotal);
                    remito.setFecha(new Date());
                    remito.setIdEstado(new EstadosT());
                    remito.getIdEstado().setIdEstado(5);
                    remito.setIdTipoComprobante(new TiposComprobantesT());
                    remito.getIdTipoComprobante().setIdTipoComprobante(4);
                    remito.setIdUsuario(DesktopApp.getApplication().getUsuarioLogueado());
                    remito.setFechaModificacion(remito.getFecha());
                    remito.setIdRemito(null);
                    //Hay stock de todos los articulos solicitados.
                    if (detalleTemporal.isEmpty()) {
                        remito.setFechaAcordada(remito.getFecha());
                        remito.setFechaEntrega(remito.getFecha());
                        remito = DesktopApp.getApplication().updateRemitosT(remito, getListDto());
                        JOptionPane.showInternalMessageDialog(this, "Se ha generado el remito exitosamente");
                        imprimir().run();

                        cancelar();
                    } else {
                        //insertar detalleremitostemp
                        //llamar funcion calculo de fecha estimada
                        Date fechaAcordada = DesktopApp.getApplication().updateRemitosTempT(detalleTemporal);
                        StringBuffer mensaje = new StringBuffer("Para completar el remito se necesita:\n\n");
                        for (DetalleRemitosT item : getListDto()) {
                            if (item.getSaldoOP() > 0) {
                                mensaje.append(item.getSaldoOP() + " de " + item.getIdArticulo().getDescripcion() + "\n");
                            }
                        }
                        String fechaAcordadaLiteral = DesktopApp.getApplication().getFechaLiteral(fechaAcordada);
                        mensaje.append("\nEl pedido estara completo aproximadamente " + fechaAcordadaLiteral + ".\n\n");
                        mensaje.append("¿Desea crear una orden de producción?");

                        if (JOptionPane.showInternalConfirmDialog(this, mensaje,
                                "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            //si esta de acuerdo con la fecha crear remito y crear orden de produccion por lo que falta
                            opT = new OrdenesProduccionT();
                            opT.setFecha(remito.getFecha());
                            opT.setIdEstado(new EstadosT());
                            opT.getIdEstado().setIdEstado(4);
                            opT.setIdTipoComprobante(new TiposComprobantesT());
                            opT.getIdTipoComprobante().setIdTipoComprobante(6);
                            opT.setIdUsuario(DesktopApp.getApplication().getUsuarioLogueado());
                            opT.setFechaModificacion(remito.getFecha());
                            opT.setFechaInicioEstimada(null);
                            opT.setIdPrioridad(new PrioridadesT());

                            //Cambiar prioridad??????????????????????????????'
                            opT.getIdPrioridad().setIdPrioridad(3);

                            ArrayList<DetOrdenesProduccionT> listaDetalleOPT = new ArrayList<DetOrdenesProduccionT>();
                            int idDetalleOP = 1;
                            for (DetalleRemitosT detalleRemitosT : getListDto()) {
                                if (detalleRemitosT.getSaldoOP() > 0) {
                                    DetOrdenesProduccionPKT idDetalleOPPKT = new DetOrdenesProduccionPKT(idDetalleOP, 0);
                                    DetOrdenesProduccionT detalleOP = new DetOrdenesProduccionT();
                                    detalleOP.setDetordenesproduccionPK(idDetalleOPPKT);
                                    detalleOP.setIdArticulo(detalleRemitosT.getIdArticulo());
                                    detalleOP.setIdUnidMedida(detalleRemitosT.getIdUnidMedida());
                                    detalleOP.setCantidad(detalleRemitosT.getSaldoOP());

                                    listaDetalleOPT.add(detalleOP);
                                    cuerpoMail2.append(detalleRemitosT.getSaldoOP() + " " + detalleRemitosT.getIdUnidMedida().getAbreviatura() + " de " + detalleRemitosT.getIdArticulo().getCodigo() + "(" + detalleRemitosT.getIdArticulo().getDescripcion() + ")\n");

                                    idDetalleOP++;
                                }
                            }

                            remito.setFechaAcordada(fechaAcordada);
                            remito.setFechaEntrega(null);

                            remito = DesktopApp.getApplication().updateRemitosT(remito, getListDto());
                            opT.setIdRemito(remito);
                            opT = DesktopApp.getApplication().updateOrdenesProduccionT(opT, listaDetalleOPT);

                            if (JOptionPane.showInternalConfirmDialog(this, "¿Desea enviar mail de confirmacion?",
                                    "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                parametros = new HashMap();
                                parametros.put("pRol", "PRODUCCION");
                                parametros.put("pJoinUsuarios", true);
                                ArrayList<RolesT> listaRoles = (ArrayList<RolesT>) DesktopApp.getApplication().getRolesT(parametros);
                                RolesT rolProduccion = listaRoles.get(0);
                                ArrayList<String> destinatarios = new ArrayList<String>();
                                for (UsuariosT usuariosT : rolProduccion.getIdUsuarioCollection()) {
                                    destinatarios.add(usuariosT.getMails());
                                }
                                tituloMail = "Nueva Orden de produccion #" + opT.getNroOrdenProduccion();
                                DateFormat fechaFormatter = new SimpleDateFormat("dd/MM/yyyy H:mm");

                                cuerpoMail.append(fechaFormatter.format(opT.getFecha()) + "\n");
                                cuerpoMail.append("Se ha generado exitosamente la orden de produccion #" + opT.getNroOrdenProduccion() + "\n");
                                cuerpoMail.append("Esta relacionada con el remito #" + opT.getIdRemito().getNroRemito() + "\n");
                                cuerpoMail.append("Fue generada por " + opT.getIdUsuario().getUsuario() + "\n");
                                cuerpoMail.append("Con el siguiente detalle:\n\n");
                                cuerpoMail.append(cuerpoMail2);
                                cuerpoMail.append("\n\nPor favor, no responda este mail.");
                                DesktopApp.getApplication().sendSSLMessage(destinatarios, tituloMail, cuerpoMail.toString());
                            }
                            JOptionPane.showInternalMessageDialog(this, "Se ha generado el remito y la orden de produccion exitosamente");

//                            Reporte remito va aqui
                            imprimir().run();

                            cancelar();
                        }
                    }
                } else {
                    JOptionPane.showInternalMessageDialog(this, "Hay cantidades o precios igual o menor a cero!");
                }
            } else {
                JOptionPane.showInternalMessageDialog(this, "No hay un cliente seleccionado");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "No hay ningun item en el remito!");
        }
    }

    @Action
    public Task imprimir() {
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

            parametro.put("premito", remito.getIdRemito());

            System.out.println(parametro);

            parametro.put("pduke", "C:\\Logos\\Duke.gif");
            parametro.put("pimagen", "C:\\Logos\\logoreporte.jpg");
            JasperPrint jp = DesktopApp.getApplication().getReporte("remito", parametro);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Reporte de Remito");
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

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistrarRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
        this.total = total;
        this.txtImporte.setText(df.format(total));
    }

    public void agregarDetalle(DetalleRemitosT detalle) {
        tableModel.addRow(detalle);
    }

    public void agregarCliente(ClientesT cliente) {
        txtCliente.setText(cliente.getNombres());
        txtCuit.setText(cliente.getCuit());
        txtNumeroCliente.setText(cliente.getIdCliente().toString());
        remito.setIdCliente(cliente);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleRemito = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnAplicar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        txtNumeroCliente = new javax.swing.JTextField();
        txtCuit = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();

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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(RegistrarRemito.class);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtFecha.setEditable(false);
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtFecha.setText(resourceMap.getString("txtFecha.text")); // NOI18N
        txtFecha.setName("txtFecha"); // NOI18N

        txtCliente.setText(resourceMap.getString("txtCliente.text")); // NOI18N
        txtCliente.setName("txtCliente"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDetalleRemito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDetalleRemito.setName("tblDetalleRemito"); // NOI18N
        jScrollPane1.setViewportView(tblDetalleRemito);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(RegistrarRemito.class, this);
        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        btnAplicar.setAction(actionMap.get("aplicar")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N

        btnAgregar.setAction(actionMap.get("agregar")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnEliminar.setAction(actionMap.get("eliminar")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N

        btnBuscarCliente.setAction(actionMap.get("buscarCliente")); // NOI18N
        btnBuscarCliente.setName("btnBuscarCliente"); // NOI18N

        txtNumeroCliente.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumeroCliente.setText(resourceMap.getString("txtNumeroCliente.text")); // NOI18N
        txtNumeroCliente.setName("txtNumeroCliente"); // NOI18N

        txtCuit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCuit.setText(resourceMap.getString("txtCuit.text")); // NOI18N
        txtCuit.setName("txtCuit"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtImporte.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtImporte.setText(resourceMap.getString("txtImporte.text")); // NOI18N
        txtImporte.setEnabled(false);
        txtImporte.setName("txtImporte"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNumeroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCuit, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                            .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNumeroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnAplicar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCliente, txtFecha});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

    if (isModificado() || isNuevo()) {
        if (JOptionPane.showInternalConfirmDialog(this, "Hay informacion que no han sido guardada\n¿Desea cerrar de todos modos?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    } else {
        dispose();
    }


}//GEN-LAST:event_formInternalFrameClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDetalleRemito;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNumeroCliente;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Codigo", "Articulo", "Cantidad", "Medida", "Precio", "Importe"
    };
    private DetalleRemitosTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
    private RemitosT remito;
    private int contadorDetalle;
    private ABMArticulos articulosOpenFrame;
    private ABMClientes clientesOpenFrame;
    private ArrayList<DetalleRemitosTempT> detalleTemporal = new ArrayList<DetalleRemitosTempT>();
    private double importeTotal;
    private HashMap parametros;
    private OrdenesProduccionT opT;
    private String tituloMail;
    private StringBuffer cuerpoMail;
    private StringBuffer cuerpoMail2;
    private Double total;
}