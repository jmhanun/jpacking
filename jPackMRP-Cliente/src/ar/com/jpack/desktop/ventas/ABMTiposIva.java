/*
 * ABMTiposIva.java
 *
 * Created on 21 de enero de 2009, 19:40
 */
package ar.com.jpack.desktop.ventas;

import ar.com.jpack.helpers.tablemodels.TiposIvaTableModel;
import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.transferencia.EstadosT;
import ar.com.jpack.transferencia.TiposIvaT;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.application.Action;

/**
 *
 * @author  jmhanun
 */
public class ABMTiposIva extends CustomInternalFrame<TiposIvaT> {

    /** Creates new form ABMTiposIva */
    public ABMTiposIva() {
        super(new TiposIvaT());
        initComponents();
        HashMap parametros = new HashMap();
        //Obliga a que joinee con Estado.
        parametros.put("pJoinEstados", true);
        //IdEstado == 1 es para traer los tipos de iva habilitados
        parametros.put("pIdEstado", 1);
        this.setListDto((ArrayList<TiposIvaT>) DesktopApp.getApplication().getTiposIvaT(parametros));

        tableModel = new TiposIvaTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tiposIvaTable.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tiposIvaTable.setRowSorter(sorter);
    }

    @Action
    public void agregar() {
        // <editor-fold defaultstate="collapsed" desc="Hard Code - Nuevo TipoIVA (nuevoIva)">
        //Inicio de carga hard-code
        HashMap parametros = new HashMap();
        parametros.put("pIdEstados", new Integer(1));
        EstadosT estado = (EstadosT) DesktopApp.getApplication().getEstadosT(parametros);
        TiposIvaT nuevoIva = new TiposIvaT(99, "Nuevo", "N", estado);
        //Fin de carga hard-code
        // </editor-fold>

        DesktopApp.getApplication().addTipoIva(nuevoIva);

        tableModel.addRow(nuevoIva);
        JOptionPane.showInternalMessageDialog(this, "agregar");
    }

    @Action
    public void borrar() {
        int indiceAEditar = tiposIvaTable.getSelectedRow();
        TiposIvaT editado = (TiposIvaT) tableModel.getRow(indiceAEditar);
        HashMap parametros = new HashMap();
        parametros.put("pIdTipoIva", editado.getIdTipoIVA());

        ArrayList<TiposIvaT> lista = (ArrayList<TiposIvaT>) DesktopApp.getApplication().getTiposIvaT(parametros);

//        DesktopApp.getApplication().removeTipoIva(tipoIvaT);
        tableModel.deleteRow(indiceAEditar);
        JOptionPane.showInternalMessageDialog(this, "borrar");
    }

    @Action
    public void seleccionar() {
        JOptionPane.showInternalMessageDialog(this, "seleccionar");
    }

    @Action
    public void cancelar() {

        JOptionPane.showInternalMessageDialog(this, "cancelar");
    }

    @Action
    public void modificar() {
        int indiceAEditar = tiposIvaTable.getSelectedRow();
        TiposIvaT editado = (TiposIvaT) tableModel.getRow(indiceAEditar);



        HashMap parametros = new HashMap();
        parametros.put("pIdTipoIva", editado.getIdTipoIVA());

        ArrayList<TiposIvaT> lista = (ArrayList<TiposIvaT>) DesktopApp.getApplication().getTiposIvaT(parametros);

        JOptionPane.showInternalMessageDialog(this, "editar: " + lista.get(0).getDescripcion());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tiposIvaTable = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMTiposIva.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ABMTiposIva.class, this);
        btnAgregar.setAction(actionMap.get("agregar")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnBorrar.setAction(actionMap.get("borrar")); // NOI18N
        btnBorrar.setName("btnBorrar"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tiposIvaTable.setModel(new javax.swing.table.DefaultTableModel(
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
        tiposIvaTable.setName("tiposIvaTable"); // NOI18N
        tiposIvaTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tiposIvaTable);
        tiposIvaTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tiposIvaTable.columnModel.title0")); // NOI18N
        tiposIvaTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tiposIvaTable.columnModel.title1")); // NOI18N
        tiposIvaTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tiposIvaTable.columnModel.title2")); // NOI18N
        tiposIvaTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tiposIvaTable.columnModel.title3")); // NOI18N

        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        btnSeleccionar.setAction(actionMap.get("seleccionar")); // NOI18N
        btnSeleccionar.setName("btnSeleccionar"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnModificar)
                    .addComponent(btnBorrar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tiposIvaTable;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Abreviatura", "Descripcion"
    };
    protected TiposIvaTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
}
