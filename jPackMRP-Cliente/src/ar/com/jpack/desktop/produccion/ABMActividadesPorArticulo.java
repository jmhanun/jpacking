/*
 * ABMActividadesPorArticulo.java
 *
 * Created on 5 de septiembre de 2009, 16:47
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.helpers.tablemodels.ActividadesArticulosTableModel;
import ar.com.jpack.transferencia.ActividadesArticulosPKT;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.transferencia.ArticulosT;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ABMActividadesPorArticulo extends CustomInternalFrame<ActividadesArticulosT> {

    /** Creates new form ABMActividadesPorArticulo */
    public ABMActividadesPorArticulo() {
        super(new ActividadesArticulosT());
        initComponents();
        txtDescripcion.setEnabled(false);

        setListDto(new ArrayList<ActividadesArticulosT>());

        tableModel = new ActividadesArticulosTableModel(columnNames, this.getListDto()) {

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                ActividadesArticulosT record = (ActividadesArticulosT) dataVector.get(rowIndex);
                switch (columnIndex) {
                    case 1:
                        record.setOrden(((Integer) aValue).intValue());
                        break;
                    case 2:
                        record.setTiempo(((Float) aValue).floatValue());
                        break;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column) {
                    case 1:
                        return true;
                    case 2:
                        return true;
                    default:
                        return false;
                }
            }
        };
        tableModel.addTableModelListener(new CustomTableModelListener(this));
        tblActividades.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblActividades.setRowSorter(sorter);
        tblActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        articuloT = new ArticulosT();

    }

    @Action
    public void buscar() {

        DesktopApp.getApplication().getDesktopView().setPadre(this);

        DesktopApp.getApplication().getDesktopView().showArticulos().run();

        articulosOpenFrame = (ABMArticulos) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.produccion.ABMArticulos");

        articulosOpenFrame.setPadre(this);
        articulosOpenFrame.habilitarBtnSeleccionar(true);
        articulosOpenFrame.setCodigoArticulo(txtCodigo.getText());
        articulosOpenFrame.buscar();

    }

    @Action
    public void agregar() {

        setDto(new ActividadesArticulosT());

        DesktopApp.getApplication().getDesktopView().setPadre(this);
        DesktopApp.getApplication().getDesktopView().showActividades().run();

        actividadesOpenFrame = (ABMActividades) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.produccion.ABMActividades");

        actividadesOpenFrame.setPadre(this);
        actividadesOpenFrame.habilitarBtnSeleccionar(true);

    }

    @Action
    public void modificar() {
        JOptionPane.showInternalMessageDialog(this, "modificar");
    }

    @Action
    public void eliminar() {
        JOptionPane.showInternalMessageDialog(this, "eliminar");
    }

    @Action
    public void aplicar() {
        //Verificar que haya al menos un item
        if (tableModel.getRowCount() > 0) {

            //Verificar que haya articulo padre seleccionado
            if (articuloT.getIdArticulo() != null) {
                //Verificar que haya cantidades y orden en los items
                boolean cantidadOk = true;
                ArrayList<ActividadesArticulosT> items = getListDto();
                for (int i = 0; i < items.size(); i++) {
                    ActividadesArticulosT actividadesArticulosT = items.get(i);
                    if (actividadesArticulosT.getTiempo() <= 0) {
                        cantidadOk = false;
                    }
                    if (actividadesArticulosT.getOrden() <= 0) {
                        cantidadOk = false;
                    }
                    if (cantidadOk) {
                        getListDto().get(i).setArticulos(articuloT);
                        getListDto().get(i).setActividadesxarticulosPK(new ActividadesArticulosPKT(actividadesArticulosT.getActividades().getIdActividad(), actividadesArticulosT.getArticulos().getIdArticulo()));
                    }

                }
                if (cantidadOk) {
                    DesktopApp.getApplication().updateActividadesArticulosT(getListDto());
                    JOptionPane.showInternalMessageDialog(this, "Se ha generado los actividades exitosamente");
                    cancelar();
                } else {
                    JOptionPane.showInternalMessageDialog(this, "Hay orden o tiempos igual o menor a cero!");
                }
            } else {
                JOptionPane.showInternalMessageDialog(this, "No hay un articulo seleccionado");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "No hay ninguna actividad para el articulo seleccionado!");
        }
    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ABMActividadesPorArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarDetalle(ActividadesArticulosT actArt) {
        tableModel.addRow(actArt);
    }

    public void agregarArticulo(ArticulosT art) {
        txtCodigo.setText(art.getCodigo());
        txtDescripcion.setText(art.getDescripcion());
        articuloT = art;

        
        HashMap parametros = new HashMap();
        
        parametros.put("pIdArticulo", art.getIdArticulo());
        
        setListDto((ArrayList<ActividadesArticulosT>) DesktopApp.getApplication().getActividadesArticulosT(parametros));

        tableModel = new ActividadesArticulosTableModel(columnNames, this.getListDto()) {

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                ActividadesArticulosT record = (ActividadesArticulosT) dataVector.get(rowIndex);
                switch (columnIndex) {
                    case 1:
                        record.setOrden(((Integer) aValue).intValue());
                        break;
                    case 2:
                        record.setTiempo(((Float) aValue).floatValue());
                        break;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column) {
                    case 1:
                        return true;
                    case 2:
                        return true;
                    default:
                        return false;
                }
            }
        };
        tableModel.addTableModelListener(new CustomTableModelListener(this));
        tblActividades.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblActividades.setRowSorter(sorter);
        tblActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
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
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActividades = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAplicar = new javax.swing.JButton();
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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMActividadesPorArticulo.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtCodigo.setText(resourceMap.getString("txtCodigo.text")); // NOI18N
        txtCodigo.setName("txtCodigo"); // NOI18N

        txtDescripcion.setText(resourceMap.getString("txtDescripcion.text")); // NOI18N
        txtDescripcion.setName("txtDescripcion"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ABMActividadesPorArticulo.class, this);
        btnBuscar.setAction(actionMap.get("buscar")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblActividades.setModel(new javax.swing.table.DefaultTableModel(
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
        tblActividades.setName("tblActividades"); // NOI18N
        jScrollPane1.setViewportView(tblActividades);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblActividades;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Actividad", "Orden", "Tiempo"
    };
    private ActividadesArticulosTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
    private ArticulosT articuloT;
    private ABMArticulos articulosOpenFrame;
    private ABMActividades actividadesOpenFrame;
    private boolean encabezado;
}
