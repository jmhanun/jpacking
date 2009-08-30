/*
 * ABMArticulos.java
 *
 * Created on 14 de junio de 2009, 18:41
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.ventas.RegistrarRemito;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.ArticulosTableModel;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.DetalleRemitosT;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.application.Action;

/**
 *
 * @author  jmhanun
 */
public class ABMArticulos extends CustomInternalFrame<ArticulosT> {

    /** Creates new form ABMArticulos */
    public ABMArticulos() {
        super(new ArticulosT());
        initComponents();

        HashMap parametros = new HashMap();
        setListDto((ArrayList<ArticulosT>) DesktopApp.getApplication().getArticulosT(parametros));

        tableModel = new ArticulosTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblArticulos.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblArticulos.setRowSorter(sorter);

        if (getPadre() == null) {
            btnSeleccionar.setEnabled(false);
        } else {
            btnSeleccionar.setEnabled(true);
        }
    }

    @Action
    public void eliminar() {
        JOptionPane.showInternalMessageDialog(this, "eliminar");
    }

    @Action
    public void modificar() {
        JOptionPane.showInternalMessageDialog(this, "modificar");
    }

    @Action
    public void agregar() {
        JOptionPane.showInternalMessageDialog(this, "agregar");
    }

    @Action
    public void seleccionar() {
        if (getDto() != null) {
            if (getDto().getIdArticulo() != null) {
                if (tblArticulos.getSelectedRow() != - 1) {

                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.ventas.RegistrarRemito")) {
                        ArticulosT art = getDto();
                        ((DetalleRemitosT) getPadre().getDto()).setIdArticulo(art);

                        ((DetalleRemitosT) getPadre().getDto()).setIdUnidMedida(art.getIdUnidMedida());
                        ((DetalleRemitosT) getPadre().getDto()).setPrecioUnitario(DesktopApp.getApplication().getPrecioArticuloVigente(art));

                        ((RegistrarRemito) getPadre()).agregarDetalle(((DetalleRemitosT) getPadre().getDto()));

                        cancelar();
                    }
                } else {
                    JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un articulo");
                }
            } else {
                JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un articulo");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un articulo");
        }
    }

    @Action
    public void cancelar() {
        try {
            setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ABMArticulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Action
    public void buscar() {
        HashMap parametros = new HashMap();
        if (!txtCodigoBusqueda.getText().isEmpty()) {
            parametros.put("pCodigo", txtCodigoBusqueda.getText());
        }
        if (chkFinal.isSelected()) {
            parametros.put("pFinal", booleanToString(chkFinal.isSelected()));
        }
        if (chkImprimible.isSelected()) {
            parametros.put("pImprimible", booleanToString(chkImprimible.isSelected()));
        }
        setListDto((ArrayList<ArticulosT>) DesktopApp.getApplication().getArticulosT(parametros));
        tableModel = new ArticulosTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblArticulos.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblArticulos.setRowSorter(sorter);

        if ((btnSeleccionar.isEnabled()) && (getListDto().size() == 1)) {
            tblArticulos.setRowSelectionInterval(0, 0);
            setDto((ArticulosT) tableModel.getRow(sorter.convertRowIndexToModel(tblArticulos.getSelectedRow())));
            cambiarArticuloT();
            seleccionar();
        }
    }

    public void cambiarArticuloT() {
        //Cambia los datos de los txts
    }

    public void habilitarBtnSeleccionar(boolean valor) {
        btnSeleccionar.setEnabled(valor);
        btnAgregar.setEnabled(!valor);
        btnEliminar.setEnabled(!valor);
        btnModificar.setEnabled(!valor);
    }

    public void habilitarChkFinal(boolean habilitado, boolean valor) {
        chkFinal.setSelected(valor);
        chkFinal.setEnabled(habilitado);

    }

    public String booleanToString(Boolean valor) {
        if (valor) {
            return "S";
        } else {
            return "N";
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        chkImprimible = new javax.swing.JCheckBox();
        chkFinal = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setName("Form"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMArticulos.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtCodigoBusqueda.setText(resourceMap.getString("txtCodigoBusqueda.text")); // NOI18N
        txtCodigoBusqueda.setName("txtCodigoBusqueda"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ABMArticulos.class, this);
        btnBuscar.setAction(actionMap.get("buscar")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblArticulos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblArticulos.setName("tblArticulos"); // NOI18N
        tblArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArticulosMouseClicked(evt);
            }
        });
        tblArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblArticulosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblArticulos);

        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        btnEliminar.setAction(actionMap.get("eliminar")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnSeleccionar.setAction(actionMap.get("seleccionar")); // NOI18N
        btnSeleccionar.setName("btnSeleccionar"); // NOI18N

        btnAgregar.setAction(actionMap.get("agregar")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N

        chkImprimible.setText(resourceMap.getString("chkImprimible.text")); // NOI18N
        chkImprimible.setName("chkImprimible"); // NOI18N

        chkFinal.setText(resourceMap.getString("chkFinal.text")); // NOI18N
        chkFinal.setName("chkFinal"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(chkImprimible)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkFinal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                                .addComponent(btnBuscar))
                            .addComponent(txtCodigoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chkFinal, chkImprimible});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkImprimible)
                        .addComponent(chkFinal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tblArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArticulosMouseClicked

    //para el caso en que se navegue la tabla con el mouse
    setDto((ArticulosT) tableModel.getRow(sorter.convertRowIndexToModel(tblArticulos.getSelectedRow())));
    cambiarArticuloT();
    if (evt.getClickCount() == 2) {
        this.jTabbedPane1.setSelectedIndex(1);
    }


}//GEN-LAST:event_tblArticulosMouseClicked

private void tblArticulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblArticulosKeyReleased

    //para el caso en que se navegue la tabla con las flechas
    setDto((ArticulosT) tableModel.getRow(sorter.convertRowIndexToModel(tblArticulos.getSelectedRow())));
    cambiarArticuloT();


}//GEN-LAST:event_tblArticulosKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JCheckBox chkFinal;
    private javax.swing.JCheckBox chkImprimible;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JTextField txtCodigoBusqueda;
    // End of variables declaration//GEN-END:variables
    private ArticulosTableModel tableModel;
    public static final String[] columnNames = {
        "Id", "Codigo", "Descripcion", "Stock Minimo",
        "Unidad de medida", "Estado", "Imprimible", "Final"
    };
    private TableRowSorter sorter;
}