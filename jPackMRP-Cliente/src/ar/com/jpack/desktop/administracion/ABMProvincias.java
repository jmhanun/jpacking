/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMProvincias.java
 *
 * Created on 19-sep-2009, 10:18:10
 */
package ar.com.jpack.desktop.administracion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.ProvinciasTableModel;
import ar.com.jpack.transferencia.ProvinciasT;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
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
public class ABMProvincias extends CustomInternalFrame<ProvinciasT> {

    /** Creates new form ABMProvincias */
    public ABMProvincias() {
        super(new ProvinciasT());
        initComponents();
        btnBorrar.setEnabled(false);

        btnSeleccionar.setEnabled(false);
        HashMap parametros = new HashMap();
        setListDto((ArrayList<ProvinciasT>) DesktopApp.getApplication().getProvinciasT(parametros));

        tableModel = new ProvinciasTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblProvincias.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblProvincias.setRowSorter(sorter);

        txtLetra.setEnabled(false);
        txtProvincia.setEnabled(false);

        tblProvincias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    @Action
    public void agregar() {
        if (!isNuevo()) {
            if (isModificado()) {
                if (JOptionPane.showInternalConfirmDialog(this, "Algunos datos han sido modificados.\n¿Desea conservar esos cambios?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    aplicar();
                } else {
                    setDto(getOldDto());
                    txtLetra.setEnabled(false);
                    txtProvincia.setEnabled(false);
                }
            }
            setDto(new ProvinciasT());
            cambiarProvinciasT();
            txtLetra.setEnabled(true);
            txtProvincia.setEnabled(true);
            jTabbedPane1.setSelectedIndex(1);
            setNuevo(true);
            setModificado(true);
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
            txtProvincia.requestFocus();
        }
    }

    @Action
    public void seleccionar() {
    }

    @Action
    public void modificar() {
        txtLetra.setEnabled(true);
        txtProvincia.setEnabled(true);
        jTabbedPane1.setSelectedIndex(1);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        txtProvincia.requestFocus();
    }

    @Action
    public void borrar() {
    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ABMProvincias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Action(enabledProperty = "modificado")
    public void aplicar() {
        try {
            if (isNuevo() || isModificado()) {

                setDto(DesktopApp.getApplication().updateProvinciasT(getDto()));
                if (isNuevo()) {
                    tableModel.addRow(getDto());
                }
                setDto(new ProvinciasT());
                cambiarProvinciasT();

                setModificado(false);
                setNuevo(false);
                txtLetra.setEnabled(false);
                txtProvincia.setEnabled(false);
                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(true);
                jTabbedPane1.setSelectedIndex(0);
                tblProvincias.clearSelection();
            }
        } catch (javax.ejb.EJBException ex) {
            JOptionPane.showInternalMessageDialog(this, "No es posible agregar el nuevo registro.\nVerifique que los datos sean los correctos");
        }
    }

    private void cambiarProvinciasT() {
        txtLetra.setText(getDto().getLetra());
        txtProvincia.setText(getDto().getProvincia());
        txtLetra.setEnabled(false);
        txtProvincia.setEnabled(false);
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
        tblProvincias = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLetra = new javax.swing.JTextField();
        btnAplicar = new javax.swing.JButton();
        txtProvincia = new javax.swing.JTextField();
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

        tblProvincias.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProvincias.setName("tblProvincias"); // NOI18N
        tblProvincias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProvinciasMouseClicked(evt);
            }
        });
        tblProvincias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProvinciasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblProvincias);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMProvincias.class);
        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtLetra.setText(resourceMap.getString("txtLetra.text")); // NOI18N
        txtLetra.setName("txtLetra"); // NOI18N
        txtLetra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLetraKeyReleased(evt);
            }
        });

        btnAplicar.setText(resourceMap.getString("btnAplicar.text")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N

        txtProvincia.setText(resourceMap.getString("txtProvincia.text")); // NOI18N
        txtProvincia.setName("txtProvincia"); // NOI18N
        txtProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProvincia, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                            .addComponent(txtLetra, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)))
                    .addComponent(btnAplicar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAplicar)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        btnBorrar.setText(resourceMap.getString("btnBorrar.text")); // NOI18N
        btnBorrar.setName("btnBorrar"); // NOI18N

        btnModificar.setText(resourceMap.getString("btnModificar.text")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnSeleccionar.setText(resourceMap.getString("btnSeleccionar.text")); // NOI18N
        btnSeleccionar.setName("btnSeleccionar"); // NOI18N

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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
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
            if (JOptionPane.showInternalConfirmDialog(this, "Hay informacion que no ha sido guardada\n¿Desea cerrar de todos modos?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dispose();
            }
        } else {
            dispose();
        }

    }//GEN-LAST:event_formInternalFrameClosing

private void tblProvinciasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProvinciasKeyReleased

    setDto((ProvinciasT) tableModel.getRow(sorter.convertRowIndexToModel(tblProvincias.getSelectedRow())));
    cambiarProvinciasT();


}//GEN-LAST:event_tblProvinciasKeyReleased

private void tblProvinciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProvinciasMouseClicked

    setDto((ProvinciasT) tableModel.getRow(sorter.convertRowIndexToModel(tblProvincias.getSelectedRow())));
    cambiarProvinciasT();
    if (evt.getClickCount() == 2) {
        this.jTabbedPane1.setSelectedIndex(1);
    }


}//GEN-LAST:event_tblProvinciasMouseClicked

private void txtProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyReleased

    getDto().setProvincia(String.valueOf(txtProvincia.getText()));
    setModificado(true);

}//GEN-LAST:event_txtProvinciaKeyReleased

private void txtLetraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLetraKeyReleased

    getDto().setLetra(String.valueOf(txtLetra.getText()));
    setModificado(true);

}//GEN-LAST:event_txtLetraKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblProvincias;
    private javax.swing.JTextField txtLetra;
    private javax.swing.JTextField txtProvincia;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Provincia", "Letras"
    };
    protected ProvinciasTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
}