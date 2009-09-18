/*
 * RegistrarAjustesStock.java
 *
 * Created on 16 de septiembre de 2009, 16:02
 */
package ar.com.jpack.desktop.depositos;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.produccion.ABMArticulos;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.DetAjustesStockTableModel;
import ar.com.jpack.transferencia.AjustesStockT;
import ar.com.jpack.transferencia.DetAjustesStockPKT;
import ar.com.jpack.transferencia.DetAjustesStockT;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.TiposComprobantesT;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
public class RegistrarAjustesStock extends CustomInternalFrame<DetAjustesStockT> {

    private int contadorDetalle;
    private AjustesStockT ajuste;
    private ABMArticulos articulosOpenFrame;

    /** Creates new form RegistrarAjustesStock */
    public RegistrarAjustesStock() {
        super(new DetAjustesStockT());
        initComponents();
        DateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date hoy = new Date();
        txtFecha.setText(fechaFormat.format(hoy));

        setListDto(new ArrayList<DetAjustesStockT>());

        tableModel = new DetAjustesStockTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener(this));
        tblDetAjustesStock.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblDetAjustesStock.setRowSorter(sorter);


        tblDetAjustesStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        contadorDetalle = 0;

        DefaultComboBoxModel ajusteComboBoxModel = new DefaultComboBoxModel();
        ajusteComboBoxModel.addElement("POSITIVO");
        ajusteComboBoxModel.addElement("NEGATIVO");
        cboSigno.setModel(ajusteComboBoxModel);
        cboSigno.setSelectedIndex(0);

        ajuste = new AjustesStockT();

        btnModificar.setEnabled(false);
    }

    @Action
    public void aplicar() {
        //Verificar que haya al menos un item
        if (tableModel.getRowCount() > 0) {

            //Verificar que haya cantidades
            boolean cantidadOk = true;
            ArrayList<DetAjustesStockT> items = getListDto();
            for (DetAjustesStockT detalleAjusteSeleccionado : items) {
                if (detalleAjusteSeleccionado.getCantidad() <= 0) {
                    cantidadOk = false;
                }
            }
            if (cantidadOk) {

                ajuste = new AjustesStockT();
                Date ahora = new Date();
                ajuste.setFecha(ahora);
                ajuste.setIdEstado(new EstadosT());
                ajuste.getIdEstado().setIdEstado(1);
                ajuste.setIdTipoComprobante(new TiposComprobantesT());
                ajuste.getIdTipoComprobante().setIdTipoComprobante(8);
                ajuste.setIdUsuario(DesktopApp.getApplication().getUsuarioLogueado());
                ajuste.setFechaModificacion(ahora);
                ajuste.setSigno(getSigno(cboSigno.getSelectedItem().toString()));


                ajuste = DesktopApp.getApplication().updateAjustesStockT(ajuste, getListDto());
                JOptionPane.showInternalMessageDialog(this, "Se ha generado el ajuste exitosamente");


                cancelar();
            } else {
                JOptionPane.showInternalMessageDialog(this, "Hay cantidades igual o menor a cero!");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "No hay ningun item en el ajuste!");
        }
    }

    @Action
    public void modificar() {
        if (tblDetAjustesStock.getSelectedRow() != - 1) {
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un articulo");
        }
    }

    @Action
    public void eliminar() {
        if (tblDetAjustesStock.getSelectedRow() != - 1) {
            tableModel.removeRow(sorter.convertRowIndexToModel(tblDetAjustesStock.getSelectedRow()));
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un articulo");
        }
    }

    @Action
    public void agregar() {
        contadorDetalle++;
        DetAjustesStockPKT id = new DetAjustesStockPKT(contadorDetalle, 0);

        setDto(new DetAjustesStockT());
        getDto().setDetajustesstockPK(id);


        DesktopApp.getApplication().getDesktopView().setPadre(this);
        DesktopApp.getApplication().getDesktopView().showArticulos().run();

        articulosOpenFrame = (ABMArticulos) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.produccion.ABMArticulos");

        articulosOpenFrame.setPadre(this);
        articulosOpenFrame.habilitarBtnSeleccionar(true);
        articulosOpenFrame.buscar();
    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistrarAjustesStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarDetalle(DetAjustesStockT detalle) {
        tableModel.addRow(detalle);
    }

    private String getSigno(String string) {
        if (string.equals("POSITIVO")) {
            return "+";
        } else {
            return "-";
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

        txtFecha = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetAjustesStock = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAplicar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cboSigno = new javax.swing.JComboBox();

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

        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(RegistrarAjustesStock.class);
        txtFecha.setText(resourceMap.getString("txtFecha.text")); // NOI18N
        txtFecha.setEnabled(false);
        txtFecha.setName("txtFecha"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDetAjustesStock.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetAjustesStock.setName("tblDetAjustesStock"); // NOI18N
        jScrollPane1.setViewportView(tblDetAjustesStock);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(RegistrarAjustesStock.class, this);
        btnAgregar.setAction(actionMap.get("agregar")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnEliminar.setAction(actionMap.get("eliminar")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N

        btnAplicar.setAction(actionMap.get("aplicar")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N

        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        cboSigno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSigno.setName("cboSigno"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSigno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboSigno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnAplicar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox cboSigno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDetAjustesStock;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Codigo", "Articulo", "Cantidad", "Medida"
    };
    private DetAjustesStockTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
}