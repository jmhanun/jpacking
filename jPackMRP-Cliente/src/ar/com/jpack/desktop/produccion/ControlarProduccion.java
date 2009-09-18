/*
 * ControlarProduccion.java
 *
 * Created on 7 de junio de 2009, 16:50
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablecellrenderer.DetalleProduccionTableCellRenderer;
import ar.com.jpack.helpers.tablemodels.DetalleProduccionTableModel;
import ar.com.jpack.transferencia.DetalleProduccionT;
import ar.com.jpack.transferencia.TiposDesviosT;
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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.application.Action;

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
        timer.stop();
        if (detalleProduccionTable.getSelectedRow() != -1) {

            DetalleProduccionT detalleSeleccionado = (DetalleProduccionT) tableModel.getRow(sorter.convertRowIndexToModel(detalleProduccionTable.getSelectedRow()));
            if ((detalleSeleccionado.getIdEstado().getIdEstado() == 22) || (detalleSeleccionado.getIdEstado().getIdEstado() == 21)) {
                Date ahora = new Date();

                DesktopApp.getApplication().setEstadoProduccion(detalleSeleccionado.getIdDetalleProduccion(), 14, detalleSeleccionado.getIdEstado().getIdEstado(), ahora);

                Long tolerancia = Long.parseLong(DesktopApp.getApplication().getValorSetup(18));
                if (DesktopApp.getApplication().getDiferenciaSegundos(detalleSeleccionado.getFechaInicioEstimada(), ahora) > tolerancia) {
                    HashMap parametros = new HashMap();
                    ArrayList<TiposDesviosT> tiposDesvioTs = (ArrayList<TiposDesviosT>) DesktopApp.getApplication().getTiposDesviosT(parametros);
                    TiposDesviosT tipo = (TiposDesviosT) JOptionPane.showInternalInputDialog(this,
                            "Desvio por desfasaje en inicio proceso - 1° Paso(Seleccione el tipo de desvio)" +
                            "\n\nTipo desvio",
                            "Desvio por desfasaje en inicio proceso",
                            JOptionPane.PLAIN_MESSAGE, null, tiposDesvioTs.toArray(), tiposDesvioTs.get(0));
                    String comentario = (String) JOptionPane.showInternalInputDialog(this,
                            "Desvio por desfasaje en inicio proceso - 2° Paso(Escriba un comentario)" +
                            "\n\nComentario",
                            "Desvio por desfasaje en inicio proceso",
                            JOptionPane.PLAIN_MESSAGE);
                    if (tipo == null) {
                        tipo = new TiposDesviosT();
                        tipo.setIdTipoDesvio(1);
                    }
                    if (comentario == null) {
                        comentario = "";
                    }
                    DesktopApp.getApplication().insertDesvioT(detalleSeleccionado.getIdDetalleProduccion(), tipo.getIdTipoDesvio(), comentario);
                }

                refresh();


                timer.setRepeats(true);
                timer.start();
            } else {
                JOptionPane.showInternalMessageDialog(this, "La actividad debe estar \"EN ESPERA\" o \"SUSPENDIDA\" para poder iniciarse");
                timer.setRepeats(true);
                timer.start();
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
            timer.setRepeats(true);
            timer.start();
        }
    }

    @Action
    public void finalizar() {
        timer.stop();
        if (detalleProduccionTable.getSelectedRow() != -1) {
            DetalleProduccionT detalleSeleccionado = (DetalleProduccionT) tableModel.getRow(sorter.convertRowIndexToModel(detalleProduccionTable.getSelectedRow()));
            if (detalleSeleccionado.getIdEstado().getIdEstado() == 14) {
                Date ahora = new Date();
                DesktopApp.getApplication().setEstadoProduccion(detalleSeleccionado.getIdDetalleProduccion(), 15, detalleSeleccionado.getIdEstado().getIdEstado(), ahora);

                Integer tiempoReal = Math.abs(DesktopApp.getApplication().getTiempoRealProduccion(detalleSeleccionado.getIdDetalleProduccion()));
                Integer tiempoEstimado = Math.abs(DesktopApp.getApplication().getTiempoEstimadoProduccion(detalleSeleccionado.getIdDetalleProduccion()));

                Long tolerancia = Long.parseLong(DesktopApp.getApplication().getValorSetup(18));
                HashMap parametros = new HashMap();
                ArrayList<TiposDesviosT> tiposDesvioTs = (ArrayList<TiposDesviosT>) DesktopApp.getApplication().getTiposDesviosT(parametros);
                if (DesktopApp.getApplication().getDiferenciaSegundos(detalleSeleccionado.getFechaFinEstimada(), ahora) > tolerancia) {
                    TiposDesviosT tipo = (TiposDesviosT) JOptionPane.showInternalInputDialog(this,
                            "Desvio por desfasaje en fin proceso - 1° Paso(Seleccione el tipo de desvio)" +
                            "\n\nTipo desvio",
                            "Desvio por desfasaje en fin proceso",
                            JOptionPane.PLAIN_MESSAGE, null, tiposDesvioTs.toArray(), tiposDesvioTs.get(0));
                    String comentario = (String) JOptionPane.showInternalInputDialog(this,
                            "Desvio por desfasaje en fin proceso - 2° Paso(Escriba un comentario)" +
                            "\n\nComentario",
                            "Desvio por desfasaje en fin proceso",
                            JOptionPane.PLAIN_MESSAGE);
                    if (tipo == null) {
                        tipo = new TiposDesviosT();
                        tipo.setIdTipoDesvio(1);
                    }
                    if (comentario == null) {
                        comentario = "";
                    }
                    DesktopApp.getApplication().insertDesvioT(detalleSeleccionado.getIdDetalleProduccion(), tipo.getIdTipoDesvio(), comentario);
                }

                if (Math.abs(tiempoEstimado - tiempoReal) > tolerancia) {
                    TiposDesviosT tipo = (TiposDesviosT) JOptionPane.showInternalInputDialog(this,
                            "Desvio en duracion proceso - 1° Paso(Seleccione el tipo de desvio)" +
                            "\n\nTipo desvio",
                            "Desvio en duracion proceso",
                            JOptionPane.PLAIN_MESSAGE, null, tiposDesvioTs.toArray(), tiposDesvioTs.get(0));
                    String comentario = (String) JOptionPane.showInternalInputDialog(this,
                            "Desvio en duracion proceso - 2° Paso(Escriba un comentario)" +
                            "\n\nComentario",
                            "Desvio en duracion proceso",
                            JOptionPane.PLAIN_MESSAGE);
                    if (tipo == null) {
                        tipo = new TiposDesviosT();
                        tipo.setIdTipoDesvio(1);
                    }
                    if (comentario == null) {
                        comentario = "";
                    }
                    DesktopApp.getApplication().insertDesvioT(detalleSeleccionado.getIdDetalleProduccion(), tipo.getIdTipoDesvio(), comentario);
                }

                parametros = new HashMap();
                parametros.put("pJoinMaquinas", true);
                parametros.put("pJoinArticulos", true);
                parametros.put("pJoinUnidadesMedidas", true);
                parametros.put("pJoinEstados", true);
                parametros.put("pJoinOrdenes", true);
                parametros.put("pIdEstadoOrden", 4);

                ArrayList<DetalleProduccionT> nuevaLista = (ArrayList<DetalleProduccionT>) DesktopApp.getApplication().getDetalleProduccionT(parametros);

                boolean actualizaOP = true;
                for (DetalleProduccionT detalle : nuevaLista) {
                    if (actualizaOP) {
                        if (detalle.getDetordenesproduccion().getOrdenesproduccion().getIdOrdenProduccion().equals(detalleSeleccionado.getDetordenesproduccion().getOrdenesproduccion().getIdOrdenProduccion())) {
                            if (detalle.getFechaFinProceso() == null) {
                                actualizaOP = false;
                            }
                        }
                    }
                }
                if (actualizaOP) {
                    DesktopApp.getApplication().setEstadoOP(detalleSeleccionado.getDetordenesproduccion().getOrdenesproduccion().getIdOrdenProduccion(), 5);
                    refresh();
                    timer.setRepeats(true);
                    timer.start();
                } else {
                    refresh();
                    timer.setRepeats(true);
                    timer.start();
                }
            } else {
                JOptionPane.showInternalMessageDialog(this, "La actividad debe estar \"INICIADA\"");
                timer.setRepeats(true);
                timer.start();
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
            timer.setRepeats(true);
            timer.start();
        }
    }

    @Action
    public void suspender() {
        timer.stop();
        if (detalleProduccionTable.getSelectedRow() != -1) {
            DetalleProduccionT detalleSeleccionado = (DetalleProduccionT) tableModel.getRow(sorter.convertRowIndexToModel(detalleProduccionTable.getSelectedRow()));
            if (detalleSeleccionado.getIdEstado().getIdEstado() == 14) {
                DesktopApp.getApplication().setEstadoProduccion(detalleSeleccionado.getIdDetalleProduccion(), 21, detalleSeleccionado.getIdEstado().getIdEstado(), new Date());
                refresh();
                timer.setRepeats(true);
                timer.start();
            } else {
                JOptionPane.showInternalMessageDialog(this, "La actividad debe estar \"INICIADO\" para poder iniciarse");
                timer.setRepeats(true);
                timer.start();
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
            timer.setRepeats(true);
            timer.start();
        }
    }

    @Action
    public void deshabilitar() {
        timer.stop();
        if (detalleProduccionTable.getSelectedRow() != -1) {
            DetalleProduccionT detalleSeleccionado = (DetalleProduccionT) tableModel.getRow(sorter.convertRowIndexToModel(detalleProduccionTable.getSelectedRow()));
            if ((detalleSeleccionado.getIdEstado().getIdEstado() == 22) || (detalleSeleccionado.getIdEstado().getIdEstado() == 21) || (detalleSeleccionado.getIdEstado().getIdEstado() == 14)) {
                DesktopApp.getApplication().setEstadoProduccion(detalleSeleccionado.getIdDetalleProduccion(), 17, detalleSeleccionado.getIdEstado().getIdEstado(), new Date());
                refresh();
                timer.setRepeats(true);
                timer.start();
            } else {
                JOptionPane.showInternalMessageDialog(this, "La actividad debe estar \"EN ESPERA\" o \"INICIADA\" o \"SUSPENDIDA\" para poder iniciarse");
                timer.setRepeats(true);
                timer.start();
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
            timer.setRepeats(true);
            timer.start();
        }
    }

    @Action
    public void modificar() {
        timer.stop();
        if (detalleProduccionTable.getSelectedRow() != -1) {
            JOptionPane.showInternalMessageDialog(this, "Se modifica la fila seleccinada");
            timer.setRepeats(true);
            timer.start();
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila");
            timer.setRepeats(true);
            timer.start();
        }
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
        parametros.put("pJoinArticulos", true);
        parametros.put("pJoinUnidadesMedidas", true);
        parametros.put("pJoinEstados", true);
        parametros.put("pJoinOrdenes", true);
        parametros.put("pIdEstadoOrden", 4);
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

        detalleProduccionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Para poner formato en la tabla...
        DetalleProduccionTableCellRenderer tableCellRenderer = new DetalleProduccionTableCellRenderer();
        detalleProduccionTable.setDefaultRenderer(Double.class, tableCellRenderer);

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
        btnCancelar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

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

        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        btnActualizar.setAction(actionMap.get("refresh")); // NOI18N
        btnActualizar.setName("btnActualizar"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuspender, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeshabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar)
                    .addComponent(btnFinalizar)
                    .addComponent(btnSuspender)
                    .addComponent(btnDeshabilitar)
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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeshabilitar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnSuspender;
    private javax.swing.JTable detalleProduccionTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Orden", "Maquina", "Prioridad", "Estado",
        "Cantidad", "Articulo",
        "Fecha Inicio Estimada", "Fecha Fin Estimada",
        "Fecha Inicio Real", "Fecha Fin Real",
        "Progreso"
    };
    protected DetalleProduccionTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
    private Timer timer;
}
