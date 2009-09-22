/*
 * MaestroArticulos.java
 *
 * Created on 13 de septiembre de 2009, 17:34
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.ActividadesArticulosMaestroTableModel;
import ar.com.jpack.helpers.tablemodels.ComponentesMaestroTableModel;
import ar.com.jpack.helpers.tablemodels.DetalleMovimientoStockTableModel;
import ar.com.jpack.helpers.tablemodels.DetalleProduccionResumenTableModel;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.ComponentesT;
import ar.com.jpack.transferencia.DetMovimientosStockT;
import ar.com.jpack.transferencia.DetalleProduccionT;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JSpinner;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.application.Action;

/**
 *
 * @author  jmhanun
 */
public class MaestroArticulos extends CustomInternalFrame<ArticulosT> {

    private ArrayList<ComponentesT> componentesTs;
    private ComponentesMaestroTableModel tableModelComponentes;
    private TableRowSorter<TableModel> sorterComponentes;
    private ArrayList<ActividadesArticulosT> actividadesTs;
    private ActividadesArticulosMaestroTableModel tableModelActividades;
    private TableRowSorter<TableModel> sorterActividad;
    private ABMArticulos articulosOpenFrame;
    private ArrayList<DetalleProduccionT> detalleProduccionTs;
    private DetalleProduccionResumenTableModel tableModelDetalleProduccion;
    private TableRowSorter<TableModel> sorterDetalleProduccion;
    private ArrayList<DetMovimientosStockT> detalleMovimientoTs;
    private DetalleMovimientoStockTableModel tableModelDetalleMovimiento;
    private TableRowSorter<TableModel> sorterDetalleMovimiento;

    /** Creates new form MaestroArticulos */
    public MaestroArticulos() {
        super(new ArticulosT());
        initComponents();

        cantidad = 1;
        // Add the listener
        spnCantidad.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) e.getSource();
//                System.out.println("cambio valor: " + spinner.getValue());
                // Get the new value
                cantidad = (Integer) spinner.getValue();
                Float tiempo = new Float(0);
                if (getDto().getIdArticulo() != null) {
                    for (ActividadesArticulosT act : actividadesTs) {
                        tiempo += act.getTiempo() * cantidad;
                    }
                    tableModelActividades = new ActividadesArticulosMaestroTableModel(columnNamesActividades, actividadesTs, cantidad);
                    tableModelActividades.addTableModelListener(new CustomTableModelListener());
                    tblActividades.setModel(tableModelActividades);

                    sorterActividad = new TableRowSorter<TableModel>(tableModelActividades) {

                        @Override
                        public void toggleSortOrder(int column) {
                            RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                            setRowFilter(null);
                            super.toggleSortOrder(column);
                            setRowFilter(f);
                        }
                    };
                    tblActividades.setRowSorter(sorterActividad);

                    tableModelComponentes = new ComponentesMaestroTableModel(columnNamesComponentes, componentesTs, cantidad);
                    tableModelComponentes.addTableModelListener(new CustomTableModelListener());
                    tblComponentes.setModel(tableModelComponentes);

                    sorterComponentes = new TableRowSorter<TableModel>(tableModelComponentes) {

                        @Override
                        public void toggleSortOrder(int column) {
                            RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                            setRowFilter(null);
                            super.toggleSortOrder(column);
                            setRowFilter(f);
                        }
                    };
                    tblComponentes.setRowSorter(sorterComponentes);

                }
                txtTiempo.setText(tiempo.toString() + " seg");

            }
        });
        componentesTs = new ArrayList<ComponentesT>();

        tableModelComponentes = new ComponentesMaestroTableModel(columnNamesComponentes, componentesTs, cantidad);
        tableModelComponentes.addTableModelListener(new CustomTableModelListener());
        tblComponentes.setModel(tableModelComponentes);

        sorterComponentes = new TableRowSorter<TableModel>(tableModelComponentes) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblComponentes.setRowSorter(sorterComponentes);

        actividadesTs = new ArrayList<ActividadesArticulosT>();

        tableModelActividades = new ActividadesArticulosMaestroTableModel(columnNamesActividades, actividadesTs, cantidad);
        tableModelActividades.addTableModelListener(new CustomTableModelListener());
        tblActividades.setModel(tableModelActividades);

        sorterActividad = new TableRowSorter<TableModel>(tableModelActividades) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblActividades.setRowSorter(sorterActividad);



        detalleProduccionTs = new ArrayList<DetalleProduccionT>();

        tableModelDetalleProduccion = new DetalleProduccionResumenTableModel(columnNamesDetalleProduccion, detalleProduccionTs);
        tableModelDetalleProduccion.addTableModelListener(new CustomTableModelListener());
        tblProduccion.setModel(tableModelDetalleProduccion);

        sorterDetalleProduccion = new TableRowSorter<TableModel>(tableModelDetalleProduccion) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblProduccion.setRowSorter(sorterDetalleProduccion);


        detalleMovimientoTs = new ArrayList<DetMovimientosStockT>();

        tableModelDetalleMovimiento = new DetalleMovimientoStockTableModel(columnNamesDetalleMovimiento, detalleMovimientoTs);
        tableModelDetalleMovimiento.addTableModelListener(new CustomTableModelListener());
        tblMovimientosStock.setModel(tableModelDetalleMovimiento);

        sorterDetalleMovimiento = new TableRowSorter<TableModel>(tableModelDetalleMovimiento) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblMovimientosStock.setRowSorter(sorterDetalleMovimiento);
        Float tiempo = new Float(0);
        for (ActividadesArticulosT act : actividadesTs) {
            tiempo += act.getTiempo() * cantidad;
        }
        txtTiempo.setText(tiempo.toString() + " seg");
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

    public void agregarArticulo(ArticulosT articulo) {
        setDto(articulo);

        chkFinal.setSelected(stringToBoolean(articulo.getArticuloFinal()));
        chkImprimible.setSelected(stringToBoolean(articulo.getImprimible()));

        txtDescripcion.setText(getDto().getDescripcion());
        txtCodigo.setText(getDto().getCodigo());
        txtStockMinimo.setText(String.valueOf(getDto().getStockMinimo()) + " " + getDto().getIdUnidMedida().getAbreviatura());

        HashMap parametros = new HashMap();

        parametros = new HashMap();
        parametros.put("pIdArticulo", getDto().getIdArticulo());

        double stockActual = DesktopApp.getApplication().getStockArticulo(articulo);
        txtStockActual.setText(stockActual + " " + getDto().getIdUnidMedida().getAbreviatura());

        Integer stockProduccion = DesktopApp.getApplication().getStockOrdenesProduccion(getDto().getIdArticulo());
        txtStockEnProduccion.setText(stockProduccion + ".0 " + getDto().getIdUnidMedida().getAbreviatura());

        double precio = DesktopApp.getApplication().getPrecioArticuloVigente(articulo);
        if (precio > 0) {
            txtPrecio.setText("$ " + precio);
        } else {
            txtPrecio.setText("--");
        }

        componentesTs = (ArrayList<ComponentesT>) DesktopApp.getApplication().getComponentesT(parametros);

        tableModelComponentes = new ComponentesMaestroTableModel(columnNamesComponentes, componentesTs, cantidad);
        tableModelComponentes.addTableModelListener(new CustomTableModelListener());
        tblComponentes.setModel(tableModelComponentes);

        sorterComponentes = new TableRowSorter<TableModel>(
                tableModelComponentes) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblComponentes.setRowSorter(sorterComponentes);

        actividadesTs = (ArrayList<ActividadesArticulosT>) DesktopApp.getApplication().getActividadesArticulosT(parametros);

        tableModelActividades = new ActividadesArticulosMaestroTableModel(columnNamesActividades, actividadesTs, cantidad);
        tableModelActividades.addTableModelListener(new CustomTableModelListener());
        tblActividades.setModel(tableModelActividades);

        sorterActividad = new TableRowSorter<TableModel>(
                tableModelActividades) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblActividades.setRowSorter(sorterActividad);

        parametros = new HashMap();
        parametros.put("pJoinMaquinas", true);
        parametros.put("pJoinArticulos", true);
        parametros.put("pJoinUnidadesMedidas", true);
        parametros.put("pJoinEstados", true);
        parametros.put("pJoinOrdenes", true);
        parametros.put("pIdEstadoOrden", 4);
        parametros.put("pIdArticulo", getDto().getIdArticulo());

        detalleProduccionTs = (ArrayList<DetalleProduccionT>) DesktopApp.getApplication().getDetalleProduccionT(parametros);

        tableModelDetalleProduccion = new DetalleProduccionResumenTableModel(columnNamesDetalleProduccion, detalleProduccionTs);
        tableModelDetalleProduccion.addTableModelListener(new CustomTableModelListener());
        tblProduccion.setModel(tableModelDetalleProduccion);

        sorterDetalleProduccion = new TableRowSorter<TableModel>(
                tableModelDetalleProduccion) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblProduccion.setRowSorter(sorterDetalleProduccion);

        parametros = new HashMap();
        parametros.put("pJoinArticulos", true);
        parametros.put("pIdArticulo", getDto().getIdArticulo());
        parametros.put("pResumen", true);

        detalleMovimientoTs = (ArrayList<DetMovimientosStockT>) DesktopApp.getApplication().getDetMovimientosStockT(parametros);

        tableModelDetalleMovimiento = new DetalleMovimientoStockTableModel(columnNamesDetalleMovimiento, detalleMovimientoTs);
        tableModelDetalleMovimiento.addTableModelListener(new CustomTableModelListener());
        tblMovimientosStock.setModel(tableModelDetalleMovimiento);

        sorterDetalleMovimiento = new TableRowSorter<TableModel>(
                tableModelDetalleMovimiento) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblMovimientosStock.setRowSorter(sorterDetalleMovimiento);

        Float tiempo = new Float(0);
        for (ActividadesArticulosT act : actividadesTs) {
            tiempo += act.getTiempo() * cantidad;
        }
        txtTiempo.setText(tiempo.toString() + " seg");
    }

    public String booleanToString(Boolean valor) {
        if (valor) {
            return "S";
        } else {
            return "N";
        }
    }

    public Boolean stringToBoolean(String valor) {
        if (valor.equals("S")) {
            return true;
        } else {
            return false;
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        chkImprimible = new javax.swing.JCheckBox();
        chkFinal = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblComponentes = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblActividades = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtStockMinimo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStockActual = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtStockEnProduccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProduccion = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblMovimientosStock = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JTextField();

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.setName("jTable2"); // NOI18N
        jScrollPane2.setViewportView(jTable2);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(MaestroArticulos.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtCodigo.setText(resourceMap.getString("txtCodigo.text")); // NOI18N
        txtCodigo.setName("txtCodigo"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(MaestroArticulos.class, this);
        btnBuscar.setAction(actionMap.get("buscar")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtDescripcion.setText(resourceMap.getString("txtDescripcion.text")); // NOI18N
        txtDescripcion.setEnabled(false);
        txtDescripcion.setName("txtDescripcion"); // NOI18N

        chkImprimible.setText(resourceMap.getString("chkImprimible.text")); // NOI18N
        chkImprimible.setEnabled(false);
        chkImprimible.setName("chkImprimible"); // NOI18N

        chkFinal.setText(resourceMap.getString("chkFinal.text")); // NOI18N
        chkFinal.setEnabled(false);
        chkFinal.setName("chkFinal"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblComponentes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblComponentes.setName("tblComponentes"); // NOI18N
        jScrollPane1.setViewportView(tblComponentes);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

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
        jScrollPane3.setViewportView(tblActividades);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtStockMinimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtStockMinimo.setText(resourceMap.getString("txtStockMinimo.text")); // NOI18N
        txtStockMinimo.setEnabled(false);
        txtStockMinimo.setName("txtStockMinimo"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtStockActual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtStockActual.setText(resourceMap.getString("txtStockActual.text")); // NOI18N
        txtStockActual.setEnabled(false);
        txtStockActual.setName("txtStockActual"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtStockEnProduccion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtStockEnProduccion.setText(resourceMap.getString("txtStockEnProduccion.text")); // NOI18N
        txtStockEnProduccion.setEnabled(false);
        txtStockEnProduccion.setName("txtStockEnProduccion"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        tblProduccion.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProduccion.setName("tblProduccion"); // NOI18N
        jScrollPane4.setViewportView(tblProduccion);

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        tblMovimientosStock.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMovimientosStock.setName("tblMovimientosStock"); // NOI18N
        jScrollPane5.setViewportView(tblMovimientosStock);

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecio.setText(resourceMap.getString("txtPrecio.text")); // NOI18N
        txtPrecio.setEnabled(false);
        txtPrecio.setName("txtPrecio"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spnCantidad.setName("spnCantidad"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        txtTiempo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTiempo.setText(resourceMap.getString("txtTiempo.text")); // NOI18N
        txtTiempo.setEnabled(false);
        txtTiempo.setName("txtTiempo"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(456, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(470, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(261, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStockMinimo, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStockActual, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStockEnProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(chkImprimible)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkImprimible)
                    .addComponent(chkFinal)
                    .addComponent(jLabel11)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12)
                    .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStockEnProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JCheckBox chkFinal;
    private javax.swing.JCheckBox chkImprimible;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable2;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblActividades;
    private javax.swing.JTable tblComponentes;
    private javax.swing.JTable tblMovimientosStock;
    private javax.swing.JTable tblProduccion;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStockActual;
    private javax.swing.JTextField txtStockEnProduccion;
    private javax.swing.JTextField txtStockMinimo;
    private javax.swing.JTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNamesComponentes = {
        "Componente", "Orden", "Cantidad unitario", "Cantidad total"
    };
    public static final String[] columnNamesActividades = {
        "Actividad", "Orden", "Tiempo unitario", "Tiempo total"
    };
    public static final String[] columnNamesDetalleMovimiento = {
        "Fecha", "Descripcion", "Cantidad"
    };
    public static final String[] columnNamesDetalleProduccion = {
        "Id", "NroOrden", "Maquina", "Estado", "Cantidad", "Inicio Estimado", "Fin Estimado"
    };
    private Integer cantidad;
}
