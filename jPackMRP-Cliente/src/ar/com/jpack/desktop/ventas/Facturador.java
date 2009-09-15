/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Facturador.java
 *
 * Created on 08-sep-2009, 19:57:07
 */
package ar.com.jpack.desktop.ventas;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablecellrenderer.FacturadorTableCellRenderer;
import ar.com.jpack.helpers.tablemodels.FacturadorTableModel;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.transferencia.RemitosT;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
 * @author Pablo
 */
public class Facturador extends CustomInternalFrame<RemitosT> {

    private ABMClientes clientesOpenFrame;

    /** Creates new form Facturador */
    public Facturador() {
        super(new RemitosT());
        initComponents();
        DateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date hoy = new Date();
        txtFecha.setEnabled(false);
        txtFecha.setText(fechaFormat.format(hoy));
        setListDto(new ArrayList<RemitosT>());

        tableModel = new FacturadorTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblRemitos.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblRemitos.setRowSorter(sorter);

        setModificado(false);
        setNuevo(false);

        tblRemitos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Para poner formato en la tabla...
        FacturadorTableCellRenderer tableCellRenderer = new FacturadorTableCellRenderer();
        tblRemitos.setDefaultRenderer(Integer.class, tableCellRenderer);
        tblRemitos.setDefaultRenderer(Date.class, tableCellRenderer);
        tblRemitos.setDefaultRenderer(Double.class, tableCellRenderer);
    }

    @Action
    public void buscar() {
        DesktopApp.getApplication().getDesktopView().setPadre(this);

        DesktopApp.getApplication().getDesktopView().showClientes().run();

        clientesOpenFrame = (ABMClientes) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.ventas.ABMClientes");

        clientesOpenFrame.setPadre(this);
        clientesOpenFrame.habilitarBtnSeleccionar(true);
        clientesOpenFrame.setNumeroCliente(txtNumero.getText());
        clientesOpenFrame.setCuitCliente(txtCuit.getText());
        clientesOpenFrame.setNombreCliente(txtNombre.getText());
        clientesOpenFrame.buscar();
    }

    @Action
    public void facturar() {
        if (tblRemitos.getSelectedRow() != - 1) {
            RemitosT remitoSeleccionado = (RemitosT) tableModel.getRow(sorter.convertRowIndexToModel(tblRemitos.getSelectedRow()));
            Integer nroFactura = DesktopApp.getApplication().insertFacturaT(remitoSeleccionado.getIdRemito());
            JOptionPane.showInternalMessageDialog(this, "Se ha generado exitosamente la factura #" + nroFactura);
            agregarCliente(remitoSeleccionado.getIdCliente());
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un remito para ser facturado");
        }
    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Facturador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void agregarCliente(ClientesT cliente) {
        txtNombre.setText(cliente.getNombres());
        txtCuit.setText(cliente.getCuit());
        txtNumero.setText(cliente.getIdCliente().toString());
        HashMap parametros = new HashMap();
        parametros.put("pJoinClientes", true);
        parametros.put("pJoinEstados", true);
        parametros.put("pIdCliente", cliente.getIdCliente());
        //trae los estados cerrados unicamente, osea.. los que pueden ser facturados.
        parametros.put("pIdEstado", 5);

        List<RemitosT> nuevo = DesktopApp.getApplication().getRemitosT(parametros);

        setListDto((ArrayList<RemitosT>) nuevo);

        tableModel = new FacturadorTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblRemitos.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblRemitos.setRowSorter(sorter);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRemitos = new javax.swing.JTable();
        txtFecha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCuit = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

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

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(Facturador.class, this);
        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(Facturador.class);
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        btnFacturar.setAction(actionMap.get("facturar")); // NOI18N
        btnFacturar.setText(resourceMap.getString("btnFacturar.text")); // NOI18N
        btnFacturar.setName("btnFacturar"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblRemitos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRemitos.setName("tblRemitos"); // NOI18N
        jScrollPane1.setViewportView(tblRemitos);

        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setText(resourceMap.getString("txtFecha.text")); // NOI18N
        txtFecha.setName("txtFecha"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtNumero.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumero.setText(resourceMap.getString("txtNumero.text")); // NOI18N
        txtNumero.setName("txtNumero"); // NOI18N

        txtNombre.setText(resourceMap.getString("txtNombre.text")); // NOI18N
        txtNombre.setName("txtNombre"); // NOI18N

        btnBuscar.setAction(actionMap.get("buscar")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtCuit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCuit.setText(resourceMap.getString("txtCuit.text")); // NOI18N
        txtCuit.setName("txtCuit"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(btnFacturar, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCuit, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGap(10, 10, 10)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(299, Short.MAX_VALUE)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnFacturar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        if (isModificado() || isNuevo()) {
            if (JOptionPane.showInternalConfirmDialog(this, "Hay informacion que no han sido guardada\n¿Desea cerrar de todos modos?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dispose();
            }
        } else {
            dispose();
        }
    }//GEN-LAST:event_formInternalFrameClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRemitos;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Numero", "Fecha", "Importe"
    };
    protected FacturadorTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
}
