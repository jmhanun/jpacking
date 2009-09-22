/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMMaquinas.java
 *
 * Created on 25-ago-2009, 20:09:26
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.MaquinasTableModel;
import ar.com.jpack.transferencia.ActividadesT;
import ar.com.jpack.transferencia.MaquinasT;

import ar.com.jpack.transferencia.RolesT;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * @author Pablo
 */
public class ABMMaquinas extends CustomInternalFrame<MaquinasT> {

    private ItemListener itemListener;
    private ArrayList<ActividadesT> actividadesTs;

    /** Creates new form ABMMaquinas */
    public ABMMaquinas() {
        super(new MaquinasT());
        initComponents();
        btnBorrar.setEnabled(false);
        itemListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("<Ninguno>")) {
                    getDto().setIdActividad(null);
                } else {
                    getDto().setIdActividad((ActividadesT) e.getItem());
                }
                setModificado(true);
            }
        };

        HashMap parametros = new HashMap();
        parametros.put("pJoinEstados", true);
        parametros.put("pJoinActividades", true);
        List<MaquinasT> nuevo = DesktopApp.getApplication().getMaquinasT(parametros);
        setListDto((ArrayList<MaquinasT>) nuevo);

        tableModel = new MaquinasTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblMaquinas.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblMaquinas.setRowSorter(sorter);

        setModificado(false);
        setNuevo(false);
        txtDescripcion.setEnabled(false);
        cmbActividad.setEnabled(false);
        txtHorasMantenimiento.setEnabled(false);
        txtHorasUso.setEnabled(false);

        if (getPadre() == null) {
            btnSeleccionar.setEnabled(false);
        }

        parametros = new HashMap();
        actividadesTs = (ArrayList<ActividadesT>) DesktopApp.getApplication().getActividadesT(parametros);

        DefaultComboBoxModel actividadesComboBoxModel = new DefaultComboBoxModel();
        int index = 0;
        int iteration = 0;
        actividadesComboBoxModel.addElement("<Ninguno>");
        for (ActividadesT actividad : actividadesTs) {
            actividadesComboBoxModel.addElement(actividad);
            if (getDto().getIdActividad() != null) {
                if (actividad.getIdActividad().equals(getDto().getIdActividad().getIdActividad())) {
                    index = iteration;
                }
            }
            iteration++;
        }
        cmbActividad.setModel(actividadesComboBoxModel);
        cmbActividad.setSelectedIndex(index);
        cmbActividad.addItemListener(itemListener);

        tblMaquinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Action(enabledProperty = "modificado")
    public void aplicar() {
        try {
            if (isNuevo() || isModificado()) {
                setDto(DesktopApp.getApplication().updateMaquinasT(getDto()));
                if (isNuevo()) {
//                    getListDto().add(getDto());
                    tableModel.addRow(getDto());
                }
                setDto(new MaquinasT());
                cambiarMaquinasT();

                setModificado(false);
                setNuevo(false);
                txtDescripcion.setEnabled(false);
                txtHorasMantenimiento.setEnabled(false);
                txtHorasUso.setEnabled(false);
                cmbActividad.setEnabled(false);
                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(true);
                jTabbedPane1.setSelectedIndex(0);
                tblMaquinas.clearSelection();
            }
        } catch (javax.ejb.EJBException ex) {
            JOptionPane.showInternalMessageDialog(this, "No es posible agregar el nuevo registro.\nVerifique que los datos sean los correctos");
        }
    }

    @Action
    public void agregar() {
        if (!isNuevo()) {
            if (isModificado()) {
                if (JOptionPane.showInternalConfirmDialog(this, "Algunos datos han sido modificados.\n¿Desea conservar esos cambios?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    aplicar();
                } else {
                    setDto(getOldDto());
                    txtDescripcion.setEnabled(false);
                    txtHorasMantenimiento.setEnabled(false);
                    txtHorasUso.setEnabled(false);
                    cmbActividad.setEnabled(false);
                }
            }
            setDto(new MaquinasT());
            cambiarMaquinasT();
            txtDescripcion.setEnabled(true);
            txtHorasMantenimiento.setEnabled(true);
            txtHorasUso.setEnabled(true);
            cmbActividad.setEnabled(true);
            jTabbedPane1.setSelectedIndex(1);
            setNuevo(true);
            setModificado(true);
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
            txtDescripcion.requestFocus();
        }
    }

    @Action
    public void seleccionar() {
    }

    @Action
    public void modificar() {
        txtDescripcion.setEnabled(true);
        cmbActividad.setEnabled(true);
        txtHorasMantenimiento.setEnabled(true);
        txtHorasUso.setEnabled(true);
        jTabbedPane1.setSelectedIndex(1);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        txtDescripcion.requestFocus();
    }

    @Action
    public void borrar() {
    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ABMMaquinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cambiarMaquinasT() {
        cmbActividad.removeItemListener(itemListener);
        txtDescripcion.setText(getDto().getDescripcion());
        txtHorasMantenimiento.setText(String.valueOf(getDto().getHorasMantenimiento()));
        txtHorasUso.setText(String.valueOf(getDto().getHorasUso()));

        int index = 0;
        int iteration = 1;
        for (ActividadesT actividadesT : actividadesTs) {
            if (getDto().getIdActividad() != null) {
                if (actividadesT.getIdActividad().equals(getDto().getIdActividad().getIdActividad())) {
                    index = iteration;
                }
            }
            iteration++;
        }

        cmbActividad.setSelectedIndex(index);

        txtDescripcion.setEnabled(false);
        cmbActividad.setEnabled(false);
        txtHorasMantenimiento.setEnabled(false);
        txtHorasUso.setEnabled(false);

        cmbActividad.addItemListener(itemListener);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaquinas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        cmbActividad = new javax.swing.JComboBox();
        txtHorasMantenimiento = new javax.swing.JTextField();
        txtHorasUso = new javax.swing.JTextField();
        btnAplicar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();

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

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblMaquinas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMaquinas.setName("tblMaquinas"); // NOI18N
        tblMaquinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMaquinasMouseClicked(evt);
            }
        });
        tblMaquinas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblMaquinasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblMaquinas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMMaquinas.class);
        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtDescripcion.setText(resourceMap.getString("txtDescripcion.text")); // NOI18N
        txtDescripcion.setName("txtDescripcion"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        cmbActividad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbActividad.setName("cmbActividad"); // NOI18N

        txtHorasMantenimiento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtHorasMantenimiento.setText(resourceMap.getString("txtHorasMantenimiento.text")); // NOI18N
        txtHorasMantenimiento.setName("txtHorasMantenimiento"); // NOI18N
        txtHorasMantenimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHorasMantenimientoKeyReleased(evt);
            }
        });

        txtHorasUso.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtHorasUso.setText(resourceMap.getString("txtHorasUso.text")); // NOI18N
        txtHorasUso.setName("txtHorasUso"); // NOI18N
        txtHorasUso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHorasUsoKeyReleased(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ABMMaquinas.class, this);
        btnAplicar.setAction(actionMap.get("aplicar")); // NOI18N
        btnAplicar.setText(resourceMap.getString("btnAplicar.text")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbActividad, 0, 349, Short.MAX_VALUE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHorasUso, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                            .addComponent(txtHorasMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)))
                    .addComponent(btnAplicar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHorasMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtHorasUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAplicar)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        btnBorrar.setAction(actionMap.get("borrar")); // NOI18N
        btnBorrar.setText(resourceMap.getString("btnBorrar.text")); // NOI18N
        btnBorrar.setName("btnBorrar"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setText(resourceMap.getString("btnModificar.text")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnSeleccionar.setAction(actionMap.get("seleccionar")); // NOI18N
        btnSeleccionar.setText(resourceMap.getString("btnSeleccionar.text")); // NOI18N
        btnSeleccionar.setName("btnSeleccionar"); // NOI18N

        btnAgregar.setAction(actionMap.get("agregar")); // NOI18N
        btnAgregar.setText(resourceMap.getString("btnAgregar.text")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnBorrar)
                    .addComponent(btnModificar)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnAgregar))
                .addGap(15, 15, 15))
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

private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased

    getDto().setDescripcion(String.valueOf(txtDescripcion.getText()));
    setModificado(true);


}//GEN-LAST:event_txtDescripcionKeyReleased

private void txtHorasMantenimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasMantenimientoKeyReleased

    getDto().setHorasMantenimiento(Float.valueOf(txtHorasMantenimiento.getText()));
    setModificado(true);


}//GEN-LAST:event_txtHorasMantenimientoKeyReleased

private void txtHorasUsoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasUsoKeyReleased

    getDto().setHorasUso(Float.valueOf(txtHorasUso.getText()));
    setModificado(true);


}//GEN-LAST:event_txtHorasUsoKeyReleased

private void tblMaquinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMaquinasMouseClicked

    //para el caso en que se navegue la tabla con el mouse
    setDto((MaquinasT) tableModel.getRow(sorter.convertRowIndexToModel(tblMaquinas.getSelectedRow())));
    cambiarMaquinasT();
    if (evt.getClickCount() == 2) {
        this.jTabbedPane1.setSelectedIndex(1);
    }


}//GEN-LAST:event_tblMaquinasMouseClicked

private void tblMaquinasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblMaquinasKeyReleased

    //para el caso en que se navegue la tabla con las flechas
    setDto((MaquinasT) tableModel.getRow(sorter.convertRowIndexToModel(tblMaquinas.getSelectedRow())));
    cambiarMaquinasT();


}//GEN-LAST:event_tblMaquinasKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox cmbActividad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblMaquinas;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtHorasMantenimiento;
    private javax.swing.JTextField txtHorasUso;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Descripcion", "Estado", "Actividad", "Horas Mantenimiento", "Horas Uso"
    };
    protected MaquinasTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
}
