/*
 * ABMMantenimientos.java
 *
 * Created on 28 de agosto de 2009, 20:26
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.MantenimientoTableModel;
import ar.com.jpack.transferencia.MantenimientoT;
import ar.com.jpack.transferencia.MaquinasT;
import ar.com.jpack.transferencia.TiposServiciosT;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ABMMantenimientos extends CustomInternalFrame<MantenimientoT> {

    private ItemListener itemMaquinaListener;
    private ItemListener itemTipoServicioListener;
    private ArrayList<TiposServiciosT> tiposServiciosTs;

    /** Creates new form ABMMantenimientos */
    public ABMMantenimientos() {
        super(new MantenimientoT());
        initComponents();
        itemMaquinaListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("<Ninguno>")) {
                    getDto().setIdMaquina(null);
                } else {
                    getDto().setIdMaquina((MaquinasT) e.getItem());
                }
                setModificado(true);
            }
        };
        itemTipoServicioListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("<Ninguno>")) {
                    getDto().setIdTipoServicio(null);
                } else {
                    getDto().setIdTipoServicio((TiposServiciosT) e.getItem());
                }
                setModificado(true);
            }
        };

        HashMap parametros = new HashMap();
        setListDto((ArrayList<MantenimientoT>) DesktopApp.getApplication().getMantenimientoT(parametros));

        tableModel = new MantenimientoTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblMantenimiento.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblMantenimiento.setRowSorter(sorter);

        setModificado(false);
        setNuevo(false);
        txtDescripcion.setEnabled(false);
        cboMaquina.setEnabled(false);
        cboTipoServicio.setEnabled(false);

        parametros = new HashMap();
        parametros.put("pJoinEstados", true);
        parametros.put("pIdEstado", 22);
        maquinasTs = (ArrayList<MaquinasT>) DesktopApp.getApplication().getMaquinasT(parametros);


        DefaultComboBoxModel maquinasComboBoxModel = new DefaultComboBoxModel();
        int index = 0;
        int it = 0;
        maquinasComboBoxModel.addElement("<Ninguno>");
        for (MaquinasT maquina : maquinasTs) {
            maquinasComboBoxModel.addElement(maquina);
            if (getDto().getIdMaquina() != null) {
                if (maquina.getIdMaquina().equals(getDto().getIdMaquina().getIdMaquina())) {
                    index = it;
                }
            }
            it++;
        }
        cboMaquina.setModel(maquinasComboBoxModel);
        cboMaquina.setSelectedIndex(index);

        parametros = new HashMap();
        tiposServiciosTs = (ArrayList<TiposServiciosT>) DesktopApp.getApplication().getTiposServiciosT(parametros);

        DefaultComboBoxModel tiposServiciosComboBoxModel = new DefaultComboBoxModel();
        index = 0;
        it = 0;
        tiposServiciosComboBoxModel.addElement("<Ninguno>");
        for (TiposServiciosT tiposServiciosT : tiposServiciosTs) {
            tiposServiciosComboBoxModel.addElement(tiposServiciosT);
            if (getDto().getIdTipoServicio() != null) {
                if (tiposServiciosT.getIdTipoServicio().equals(getDto().getIdTipoServicio().getIdTipoServicio())) {
                    index = it;
                }
            }
            it++;
        }

        cboTipoServicio.setModel(tiposServiciosComboBoxModel);
        cboTipoServicio.setSelectedIndex(index);

        tblMantenimiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cboMaquina.addItemListener(itemMaquinaListener);
        cboTipoServicio.addItemListener(itemTipoServicioListener);

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
                    cboMaquina.setEnabled(false);
                    cboTipoServicio.setEnabled(false);
                }
            }
            setDto(new MantenimientoT());
            cambiarMantenimientoT();
            txtDescripcion.setEnabled(true);

            cboMaquina.removeItemListener(itemMaquinaListener);
            cboTipoServicio.removeItemListener(itemTipoServicioListener);


            HashMap parametros = new HashMap();
            parametros.put("pJoinEstados", true);
            parametros.put("pIdEstado", 22);
            maquinasTs = (ArrayList<MaquinasT>) DesktopApp.getApplication().getMaquinasT(parametros);


            DefaultComboBoxModel maquinasComboBoxModel = new DefaultComboBoxModel();
            int index = 0;
            int it = 0;
            maquinasComboBoxModel.addElement("<Ninguno>");
            for (MaquinasT maquina : maquinasTs) {
                maquinasComboBoxModel.addElement(maquina);
                if (getDto().getIdMaquina() != null) {
                    if (maquina.getIdMaquina().equals(getDto().getIdMaquina().getIdMaquina())) {
                        index = it;
                    }
                }
                it++;
            }
            cboMaquina.setModel(maquinasComboBoxModel);
            cboMaquina.setSelectedIndex(index);

            parametros = new HashMap();
            tiposServiciosTs = (ArrayList<TiposServiciosT>) DesktopApp.getApplication().getTiposServiciosT(parametros);

            DefaultComboBoxModel tiposServiciosComboBoxModel = new DefaultComboBoxModel();
            index = 0;
            it = 0;
            tiposServiciosComboBoxModel.addElement("<Ninguno>");
            for (TiposServiciosT tiposServiciosT : tiposServiciosTs) {
                tiposServiciosComboBoxModel.addElement(tiposServiciosT);
                if (getDto().getIdTipoServicio() != null) {
                    if (tiposServiciosT.getIdTipoServicio().equals(getDto().getIdTipoServicio().getIdTipoServicio())) {
                        index = it;
                    }
                }
                it++;
            }

            cboTipoServicio.setModel(tiposServiciosComboBoxModel);
            cboTipoServicio.setSelectedIndex(index);

            cboMaquina.setEnabled(true);
            cboTipoServicio.setEnabled(true);
            jTabbedPane1.setSelectedIndex(1);
            cboMaquina.addItemListener(itemMaquinaListener);
            cboTipoServicio.addItemListener(itemTipoServicioListener);
            setNuevo(true);
            setModificado(true);
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
        }
    }

    @Action
    public void finalizar() {
        JOptionPane.showInternalMessageDialog(this, "finalizar");
    }

    @Action
    public void modificar() {

        DefaultComboBoxModel maquinasComboBoxModel = new DefaultComboBoxModel();
        if (getDto().getIdMaquina() == null) {
            maquinasComboBoxModel.addElement("<Ninguno>");
        } else {
            maquinasComboBoxModel.addElement(getDto().getIdMaquina());
        }
        cboMaquina.setModel(maquinasComboBoxModel);

        DefaultComboBoxModel tiposServiciosComboBoxModel = new DefaultComboBoxModel();
        if (getDto().getIdTipoServicio() == null) {
            tiposServiciosComboBoxModel.addElement("<Ninguno>");
        } else {
            tiposServiciosComboBoxModel.addElement(getDto().getIdTipoServicio());
        }
        cboTipoServicio.setModel(tiposServiciosComboBoxModel);

        txtDescripcion.setEnabled(true);
        cboMaquina.setEnabled(false);
        cboTipoServicio.setEnabled(false);
        jTabbedPane1.setSelectedIndex(1);
    }

    @Action
    public void eliminar() {
        JOptionPane.showInternalMessageDialog(this, "eliminar");
    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ABMMantenimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Action(enabledProperty = "modificado")
    public void aplicar() {
        try {
            if (isNuevo() || isModificado()) {
                setDto(DesktopApp.getApplication().updateMantenimientoT(getDto()));
                if (isNuevo()) {
//                    getListDto().add(getDto());
                    tableModel.addRow(getDto());
                }
                setDto(new MantenimientoT());
                cambiarMantenimientoT();

                setModificado(false);
                setNuevo(false);
                txtDescripcion.setEnabled(false);
                cboMaquina.setEnabled(false);
                cboTipoServicio.setEnabled(false);
                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(true);
                jTabbedPane1.setSelectedIndex(0);
                tblMantenimiento.clearSelection();
            }
        } catch (javax.ejb.EJBException ex) {
            JOptionPane.showInternalMessageDialog(this, "No es posible agregar el nuevo mantenimiento.\nVerifique que los datos sean los correctos");
        }
    }

    private void cambiarMantenimientoT() {
        cboMaquina.removeItemListener(itemMaquinaListener);
        cboTipoServicio.removeItemListener(itemTipoServicioListener);
        txtDescripcion.setText(getDto().getDescripcion());
        DefaultComboBoxModel maquinasComboBoxModel = new DefaultComboBoxModel();
        if (getDto().getIdMaquina() == null) {
            maquinasComboBoxModel.addElement("<Ninguno>");
        } else {
            maquinasComboBoxModel.addElement(getDto().getIdMaquina());
        }
        cboMaquina.setModel(maquinasComboBoxModel);

        DefaultComboBoxModel tiposServiciosComboBoxModel = new DefaultComboBoxModel();
        if (getDto().getIdTipoServicio() == null) {
            tiposServiciosComboBoxModel.addElement("<Ninguno>");
        } else {
            tiposServiciosComboBoxModel.addElement(getDto().getIdTipoServicio());
        }
        cboTipoServicio.setModel(tiposServiciosComboBoxModel);

        txtDescripcion.setEnabled(false);
        cboMaquina.setEnabled(false);
        cboTipoServicio.setEnabled(false);

        cboMaquina.addItemListener(itemMaquinaListener);
        cboTipoServicio.addItemListener(itemTipoServicioListener);
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
        tblMantenimiento = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboMaquina = new javax.swing.JComboBox();
        cboTipoServicio = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnAplicar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

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

        tblMantenimiento.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMantenimiento.setName("tblMantenimiento"); // NOI18N
        tblMantenimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMantenimientoMouseClicked(evt);
            }
        });
        tblMantenimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblMantenimientoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblMantenimiento);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMMantenimientos.class);
        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        cboMaquina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMaquina.setName("cboMaquina"); // NOI18N

        cboTipoServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTipoServicio.setName("cboTipoServicio"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.setName("txtDescripcion"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtDescripcion);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ABMMantenimientos.class, this);
        btnAplicar.setAction(actionMap.get("aplicar")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboMaquina, 0, 350, Short.MAX_VALUE)
                            .addComponent(cboTipoServicio, 0, 350, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(339, Short.MAX_VALUE)
                        .addComponent(btnAplicar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTipoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAplicar)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        btnAgregar.setAction(actionMap.get("agregar")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N

        btnFinalizar.setAction(actionMap.get("finalizar")); // NOI18N
        btnFinalizar.setName("btnFinalizar"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnEliminar.setAction(actionMap.get("eliminar")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N

        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnFinalizar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
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

private void tblMantenimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMantenimientoMouseClicked

    //para el caso en que se navegue la tabla con el mouse
    setDto((MantenimientoT) tableModel.getRow(sorter.convertRowIndexToModel(tblMantenimiento.getSelectedRow())));
    cambiarMantenimientoT();
    if (evt.getClickCount() == 2) {
        this.jTabbedPane1.setSelectedIndex(1);
    }

}//GEN-LAST:event_tblMantenimientoMouseClicked

private void tblMantenimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblMantenimientoKeyReleased

    //para el caso en que se navegue la tabla con las flechas
    setDto((MantenimientoT) tableModel.getRow(sorter.convertRowIndexToModel(tblMantenimiento.getSelectedRow())));
    cambiarMantenimientoT();


}//GEN-LAST:event_tblMantenimientoKeyReleased

private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased

    getDto().setDescripcion(String.valueOf(txtDescripcion.getText()));
    setModificado(true);

}//GEN-LAST:event_txtDescripcionKeyReleased
    private boolean modificado = false;
    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean b) {
        boolean old = isModificado();
        this.modificado = b;
        firePropertyChange("modificado", old, isModificado());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox cboMaquina;
    private javax.swing.JComboBox cboTipoServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblMantenimiento;
    private javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables
    private MantenimientoTableModel tableModel;
    public static final String[] columnNames = {
        "Id", "Maquina", "Tipo de Servicio", "Descripcion", "Fecha Inicio"
    };
    private TableRowSorter sorter;
    private ArrayList<MaquinasT> maquinasTs;
}
