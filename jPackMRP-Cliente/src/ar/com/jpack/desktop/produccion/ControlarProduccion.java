/*
 * ControlarProduccion.java
 *
 * Created on 7 de junio de 2009, 16:50
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.DetalleProduccionTableModel;
import ar.com.jpack.transferencia.DetalleProduccionT;
import ar.com.jpack.transferencia.EstadosT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.application.Action;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author  jmhanun
 */
public class ControlarProduccion extends CustomInternalFrame<DetalleProduccionT> {

    /** Creates new form ControlarProduccion */
    public ControlarProduccion() {
        super(new DetalleProduccionT());
        initComponents();
        refresh();

        setModificado(false);
        setNuevo(false);


        timer = new Timer(60000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                detalleProduccionTable.clearSelection();
                refresh();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    @Action
    public void iniciar() {
        if (detalleProduccionTable.getSelectedRow() != -1) {
            DetalleProduccionT detalleSeleccionado = (DetalleProduccionT) tableModel.getRow(detalleProduccionTable.getSelectedRow());
            if ((detalleSeleccionado.getIdEstado().getIdEstado() == 22) || (detalleSeleccionado.getIdEstado().getIdEstado() == 21)) {
                EstadosT estadoIniciado = new EstadosT();
                estadoIniciado.setIdEstado(14);
                detalleSeleccionado.setIdEstado(estadoIniciado);
                detalleSeleccionado.setFechaInicioProceso(new Date());
                DesktopApp.getApplication().updateDetalleProduccion(detalleSeleccionado);
                refresh();
            } else {
                JOptionPane.showInternalMessageDialog(this, "La actividad debe estar \"EN ESPERA\" o \"SUSPENDIDA\" para poder iniciarse");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
        }
    }

    @Action
    public void finalizar() {
        if (detalleProduccionTable.getSelectedRow() != -1) {
            DetalleProduccionT detalleSeleccionado = (DetalleProduccionT) tableModel.getRow(detalleProduccionTable.getSelectedRow());
            if (detalleSeleccionado.getIdEstado().getIdEstado() == 14) {
                EstadosT estadoIniciado = new EstadosT();
                estadoIniciado.setIdEstado(15);
                detalleSeleccionado.setIdEstado(estadoIniciado);
                detalleSeleccionado.setFechaInicioProceso(new Date());
                DesktopApp.getApplication().updateDetalleProduccion(detalleSeleccionado);
                refresh();
            } else {
                JOptionPane.showInternalMessageDialog(this, "La actividad debe estar \"INICIADA\"");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
        }
    }

    @Action
    public void suspender() {
        if (detalleProduccionTable.getSelectedRow() != -1) {
            DetalleProduccionT detalleSeleccionado = (DetalleProduccionT) tableModel.getRow(detalleProduccionTable.getSelectedRow());
            if (detalleSeleccionado.getIdEstado().getIdEstado() == 14) {
                EstadosT estadoIniciado = new EstadosT();
                estadoIniciado.setIdEstado(21);
                detalleSeleccionado.setIdEstado(estadoIniciado);
                DesktopApp.getApplication().updateDetalleProduccion(detalleSeleccionado);
                refresh();
            } else {
                JOptionPane.showInternalMessageDialog(this, "La actividad debe estar \"INICIADO\" para poder iniciarse");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
        }
    }

    @Action
    public void deshabilitar() {
        if (detalleProduccionTable.getSelectedRow() != -1) {
            DetalleProduccionT detalleSeleccionado = (DetalleProduccionT) tableModel.getRow(detalleProduccionTable.getSelectedRow());
            if ((detalleSeleccionado.getIdEstado().getIdEstado() == 22) || (detalleSeleccionado.getIdEstado().getIdEstado() == 21) || (detalleSeleccionado.getIdEstado().getIdEstado() == 14)) {
                EstadosT estadoIniciado = new EstadosT();
                estadoIniciado.setIdEstado(17);
                detalleSeleccionado.setIdEstado(estadoIniciado);
                DesktopApp.getApplication().updateDetalleProduccion(detalleSeleccionado);
                refresh();
            } else {
                JOptionPane.showInternalMessageDialog(this, "La actividad debe estar \"EN ESPERA\" o \"INICIADA\" o \"SUSPENDIDA\" para poder iniciarse");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
        }
    }

    @Action
    public void modificar() {
        if (detalleProduccionTable.getSelectedRow() != -1) {
            JOptionPane.showInternalMessageDialog(this, "Se modifica la fila seleccinada");
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
        }

//        final Gantt demo = new Gantt("Gantt Chart Demo desde jPack");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);

    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ControlarProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Action
    public void refresh() {
        HashMap parametros = new HashMap();
        parametros.put("pJoinMaquinas", true);
        parametros.put("pJoinEstados", true);
        parametros.put("pJoinOrdenes", true);
        setListDto((ArrayList<DetalleProduccionT>) DesktopApp.getApplication().getDetalleProduccionT(parametros));

        tableModel = new DetalleProduccionTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        detalleProduccionTable.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(
                tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        detalleProduccionTable.setRowSorter(sorter);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        detalleProduccionTable = new javax.swing.JTable();
        btnDeshabilitar = new javax.swing.JButton();
        btnSuspender = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

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

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        detalleProduccionTable.setModel(new javax.swing.table.DefaultTableModel(
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
        detalleProduccionTable.setName("detalleProduccionTable"); // NOI18N
        jScrollPane1.setViewportView(detalleProduccionTable);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ControlarProduccion.class, this);
        btnDeshabilitar.setAction(actionMap.get("deshabilitar")); // NOI18N
        btnDeshabilitar.setName("btnDeshabilitar"); // NOI18N

        btnSuspender.setAction(actionMap.get("suspender")); // NOI18N
        btnSuspender.setName("btnSuspender"); // NOI18N

        btnFinalizar.setAction(actionMap.get("finalizar")); // NOI18N
        btnFinalizar.setName("btnFinalizar"); // NOI18N

        btnIniciar.setAction(actionMap.get("iniciar")); // NOI18N
        btnIniciar.setName("btnIniciar"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        jButton1.setAction(actionMap.get("refresh")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuspender, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeshabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar)
                    .addComponent(btnFinalizar)
                    .addComponent(btnSuspender)
                    .addComponent(btnDeshabilitar)
                    .addComponent(btnModificar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

    if (isModificado() || isNuevo()) {
        if (JOptionPane.showInternalConfirmDialog(this, "Hay informacion que no han sido guardada\n¿Desea cerrar de todos modos?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            timer.stop();
            dispose();
        }
    } else {
        timer.stop();
        dispose();
    }

}//GEN-LAST:event_formInternalFrameClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeshabilitar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSuspender;
    private javax.swing.JTable detalleProduccionTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Orden", "Maquina", "Prioridad", "Estado",
        "Fecha Inicio Estimada", "Fecha Fin Estimada",
        "Fecha Inicio Real", "Fecha Fin Real",
        "Progreso"
    };
    protected DetalleProduccionTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
    private Timer timer;
}
