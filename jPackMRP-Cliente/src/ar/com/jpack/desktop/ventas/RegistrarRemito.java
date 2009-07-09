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
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.transferencia.DetalleRemitosPKT;
import ar.com.jpack.transferencia.DetalleRemitosT;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.RemitosT;
import ar.com.jpack.transferencia.TiposComprobantesT;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Date;
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
                        // Si no hay suficiente stock crear un DetalleTemporal por la diferencia
                        if (stock < detalleRemitoSeleccionado.getCantidad()) {
                            int nuevaCantidad;
                            nuevaCantidad = (int) (detalleRemitoSeleccionado.getCantidad() - stock);
                            //TODO - Debo cambiar a DetalleRemitoTemporal.. cuando este listo                        
                            DetalleRemitosT nuevoDetalle = new DetalleRemitosT(detalleRemitoSeleccionado.getDetalleremitosPK(),
                                    nuevaCantidad,
                                    detalleRemitoSeleccionado.getPrecioUnitario(),
                                    detalleRemitoSeleccionado.getImporte(),
                                    detalleRemitoSeleccionado.getIdArticulo(),
                                    detalleRemitoSeleccionado.getRemitos(),
                                    detalleRemitoSeleccionado.getIdUnidMedida());
                            detalleTemporal.add(nuevoDetalle);
                        }
                    }
                }
                if (cantidadOk) {
                    //Hay stock de todos los articulos solicitados.
                    if (detalleTemporal.isEmpty()) {
                        remito.setImporte(importeTotal);
                        remito.setFecha(new Date());
                        remito.setFechaAcordada(remito.getFecha());
                        remito.setIdEstado(new EstadosT());
                        remito.getIdEstado().setIdEstado(5);
                        remito.setIdTipoComprobante(new TiposComprobantesT());
                        remito.getIdTipoComprobante().setIdTipoComprobante(4);
                        remito.setIdUsuario(DesktopApp.getApplication().getUsuarioLogueado());
                        remito.setFechaModificacion(remito.getFecha());
//                        remito.setFechaEntrega(null);
                        remito.setFechaEntrega(remito.getFecha());
                        remito.setIdRemito(null);
                        DesktopApp.getApplication().updateRemitosT(remito, getListDto());
                        
                        cancelar();
                    } else {
                        JOptionPane.showInternalMessageDialog(this, "Falta stock de algun articulo");
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
    private ArrayList<DetalleRemitosT> detalleTemporal = new ArrayList<DetalleRemitosT>(); //Cuando este en la bbdd detalleRemitoTemporal.. cambiar tipo de dato
    private double importeTotal;
}