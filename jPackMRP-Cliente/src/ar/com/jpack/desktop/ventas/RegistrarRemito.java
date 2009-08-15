/*
 * RegistrarRemito.java
 *
 * Created on 7 de junio de 2009, 16:45
 */
package ar.com.jpack.desktop.ventas;

import ar.com.jpack.desktop.DesktopApp;
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
import org.jdesktop.application.Action;

/**
 *
 * @author  jmhanun
 */
public class RegistrarRemito extends CustomInternalFrame<DetalleRemitosT> {

    /** Creates new form RegistrarRemito */
    public RegistrarRemito() {
        super(new DetalleRemitosT());
        initComponents();
        setListDto(new ArrayList<DetalleRemitosT>());

        tableModel = new DetalleRemitosTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
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
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un articulo");
        }

    }

    @Action
    public void eliminar() {
        if (tblDetalleRemito.getSelectedRow() != - 1) {
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un articulo");
        }
    }

    @Action
    public void buscarCliente() {



        DesktopApp.getApplication().getDesktopView().setPadre(this);

        DesktopApp.getApplication().getDesktopView().showClientes().run();

        clientesOpenFrame = (ABMClientes) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.ventas.ABMClientes");

        clientesOpenFrame.setPadre(this);
        clientesOpenFrame.habilitarBtnSeleccionar(true);

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


    }

    public void agregarDetalle(DetalleRemitosT detalle) {
        tableModel.addRow(detalle);
    }

    public void agregarCliente(ClientesT cliente) {
        txtCliente.setText(cliente.getNombres());
        remito.setIdCliente(cliente);
    }

    @Action
    public void aplicar() {
        //Verificar que haya al menos un item
        cuerpoMail = new StringBuffer();
        cuerpoMail2 = new StringBuffer();
        importeTotal = 0.0;
        if (tableModel.getRowCount() > 0) {

            //Verificar que haya cliente seleccionado
            if (!txtCliente.getText().equals("")) {
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
                        DesktopApp.getApplication().updateRemitosT(remito, getListDto());
                        JOptionPane.showInternalMessageDialog(this, "Se ha generado el remito exitosamente");
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
                            opT.getIdEstado().setIdEstado(1);
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
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistrarRemito.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleRemito = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnAplicar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        dchFecha = new com.toedter.calendar.JDateChooser();
        dchAcordada = new com.toedter.calendar.JDateChooser();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();

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
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtNumero.setText(resourceMap.getString("txtNumero.text")); // NOI18N
        txtNumero.setName("txtNumero"); // NOI18N

        txtCliente.setText(resourceMap.getString("txtCliente.text")); // NOI18N
        txtCliente.setName("txtCliente"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtImporte.setText(resourceMap.getString("txtImporte.text")); // NOI18N
        txtImporte.setName("txtImporte"); // NOI18N

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

        dchFecha.setName("dchFecha"); // NOI18N

        dchAcordada.setName("dchAcordada"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnEliminar.setAction(actionMap.get("eliminar")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N

        btnBuscarCliente.setAction(actionMap.get("buscarCliente")); // NOI18N
        btnBuscarCliente.setName("btnBuscarCliente"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtImporte, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(dchFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(dchAcordada, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnBuscarCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dchAcordada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnAplicar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dchAcordada, dchFecha, txtCliente, txtImporte, txtNumero});

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
    private com.toedter.calendar.JDateChooser dchAcordada;
    private com.toedter.calendar.JDateChooser dchFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDetalleRemito;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNumero;
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
}