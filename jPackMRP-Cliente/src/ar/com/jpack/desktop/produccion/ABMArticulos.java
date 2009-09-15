/*
 * ABMArticulos.java
 *
 * Created on 14 de junio de 2009, 18:41
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.compras.RegistrarCompra;
import ar.com.jpack.desktop.compras.RegistrarOrdenCompra;
import ar.com.jpack.desktop.ventas.RegistrarRemito;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.ActividadesArticulosTableModel;
import ar.com.jpack.helpers.tablemodels.ArticulosTableModel;
import ar.com.jpack.helpers.tablemodels.ComponentesArticulosTableModel;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.ComponentesT;
import ar.com.jpack.transferencia.DetRtosIngresoT;
import ar.com.jpack.transferencia.DetalleOrdenesComprasT;
import ar.com.jpack.transferencia.DetalleRemitosT;
import ar.com.jpack.transferencia.UnidadesMedidaT;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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

    private ItemListener itemMedidaListener;
    private ArrayList<UnidadesMedidaT> medidasTs;
    private ArrayList<ComponentesT> componentesTs;
    private ItemListener itemFinalListener;
    private ItemListener itemImprimibleListener;
    private ArrayList<ActividadesArticulosT> actividadesTs;

    /** Creates new form ABMArticulos */
    public ABMArticulos() {
        super(new ArticulosT());
        initComponents();

        itemFinalListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getDto().setArticuloFinal("S");
                } else {
                    getDto().setArticuloFinal("N");
                }
                setModificado(true);
            }
        };

        itemImprimibleListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getDto().setImprimible("S");
                } else {
                    getDto().setImprimible("N");
                }
                setModificado(true);
            }
        };

        itemMedidaListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("<Ninguno>")) {
                    getDto().setIdUnidMedida(null);
                } else {
                    getDto().setIdUnidMedida((UnidadesMedidaT) e.getItem());
                }
                setModificado(true);
            }
        };

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

        componentesTs = new ArrayList<ComponentesT>();

        tableModelComponentes = new ComponentesArticulosTableModel(columnNamesComponentes, componentesTs);
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

        tableModelActividades = new ActividadesArticulosTableModel(columnNamesActividades, actividadesTs);
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

        setModificado(false);
        setNuevo(false);
        txtDescripcion.setEnabled(false);
        txtCodigo.setEnabled(false);
        txtStockMinimo.setEnabled(false);
        cboMedida.setEnabled(false);

        parametros = new HashMap();
        medidasTs = (ArrayList<UnidadesMedidaT>) DesktopApp.getApplication().getUnidadesMedidaT(parametros);

        DefaultComboBoxModel medidasComboBoxModel = new DefaultComboBoxModel();
        int index = 0;
        int it = 0;
        medidasComboBoxModel.addElement("<Ninguno>");
        for (UnidadesMedidaT medida : medidasTs) {
            medidasComboBoxModel.addElement(medida);
            if (getDto().getIdUnidMedida() != null) {
                if (medida.getIdUnidMedida().equals(getDto().getIdUnidMedida().getIdUnidMedida())) {
                    index = it;
                }
            }
            it++;
        }
        cboMedida.setModel(medidasComboBoxModel);
        cboMedida.setSelectedIndex(index);


        if (getPadre() == null) {
            btnSeleccionar.setEnabled(false);
        } else {
            btnSeleccionar.setEnabled(true);
        }


        cboMedida.addItemListener(itemMedidaListener);
        chkFinalEdicion.addItemListener(itemFinalListener);
        chkImprimibleEdicion.addItemListener(itemImprimibleListener);

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
        if (!isNuevo()) {
            if (isModificado()) {
                if (JOptionPane.showInternalConfirmDialog(this, "Algunos datos han sido modificados.\n¿Desea conservar esos cambios?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    aplicar();
                } else {
                    setDto(getOldDto());
                    txtDescripcion.setEnabled(false);
                    txtCodigo.setEnabled(false);
                    txtStockMinimo.setEnabled(false);
                    cboMedida.setEnabled(false);
                }
            }
            setDto(new ArticulosT());
            cambiarArticuloT();
            txtDescripcion.setEnabled(true);
            txtCodigo.setEnabled(true);
            txtStockMinimo.setEnabled(true);
            cboMedida.setEnabled(true);
            jTabbedPane1.setSelectedIndex(1);
            setNuevo(true);
            setModificado(true);
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
        }
    }

    @Action
    public void seleccionar() {
        if (getDto() != null) {
            if (getDto().getIdArticulo() != null) {
                ArticulosT art = getDto();
                if (tblArticulos.getSelectedRow() != - 1) {
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.ventas.RegistrarRemito")) {
                        ((DetalleRemitosT) getPadre().getDto()).setIdArticulo(art);
                        ((DetalleRemitosT) getPadre().getDto()).setIdUnidMedida(art.getIdUnidMedida());
                        ((DetalleRemitosT) getPadre().getDto()).setPrecioUnitario(DesktopApp.getApplication().getPrecioArticuloVigente(art));

                        ((RegistrarRemito) getPadre()).agregarDetalle(((DetalleRemitosT) getPadre().getDto()));

                        cancelar();
                    }
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.produccion.ABMComponentes")) {
                        boolean encabezado = ((ABMComponentes) getPadre()).isEncabezado();
                        if (encabezado) {
                            ((ABMComponentes) getPadre()).agregarArticulo(art);
                            cancelar();
                        } else {
                            ((ComponentesT) getPadre().getDto()).setComponentes(art);

                            ((ABMComponentes) getPadre()).agregarDetalle((ComponentesT) getPadre().getDto());

                            cancelar();
                        }
                    }
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.produccion.ABMActividadesPorArticulo")) {
                        ((ABMActividadesPorArticulo) getPadre()).agregarArticulo(art);
                        cancelar();
                    }
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.compras.RegistrarCompra")) {

                        ((DetRtosIngresoT) getPadre().getDto()).setIdArticulo(art);

                        ((DetRtosIngresoT) getPadre().getDto()).setIdUnidMedida(art.getIdUnidMedida());

                        ((RegistrarCompra) getPadre()).agregarDetalle(((DetRtosIngresoT) getPadre().getDto()));

                        cancelar();
                    }
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.compras.RegistrarOrdenCompra")) {

                        ((DetalleOrdenesComprasT) getPadre().getDto()).setIdArticulo(art);

                        ((DetalleOrdenesComprasT) getPadre().getDto()).setIdUnidMedida(art.getIdUnidMedida());

                        ((RegistrarOrdenCompra) getPadre()).agregarDetalle(((DetalleOrdenesComprasT) getPadre().getDto()));

                        cancelar();
                    }
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.produccion.MaestroArticulos")) {

                        ((MaestroArticulos) getPadre()).agregarArticulo(art);

                        cancelar();
                    }

                } else {
                    JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un articulo");
                }
            } else {
                JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un articulo");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un articulo");
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

    @Action(enabledProperty = "modificado")
    public void aplicar() {
        try {
            if (isNuevo() || isModificado()) {

                if (getDto().getArticuloFinal() == null) {
                    getDto().setArticuloFinal("N");
                }
                if (getDto().getImprimible() == null) {
                    getDto().setImprimible("N");
                }
                getDto().setIdUsuario(DesktopApp.getApplication().getUsuarioLogueado());
                setDto(DesktopApp.getApplication().updateArticulosT(getDto()));
                if (isNuevo()) {
//                    getListDto().add(getDto());
                    tableModel.addRow(getDto());
                }
                setDto(new ArticulosT());
                cambiarArticuloT();

                setModificado(false);
                setNuevo(false);
                txtDescripcion.setEnabled(false);
                txtCodigo.setEnabled(false);
                txtStockMinimo.setEnabled(false);
                cboMedida.setEnabled(false);
                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(true);
                jTabbedPane1.setSelectedIndex(0);
                tblArticulos.clearSelection();
            }
        } catch (javax.ejb.EJBException ex) {
            JOptionPane.showInternalMessageDialog(this, "No es posible agregar el nuevo articulo.\nVerifique que los datos sean los correctos");
        }
    }

    public void cambiarArticuloT() {
        cboMedida.removeItemListener(itemMedidaListener);
        chkFinalEdicion.removeItemListener(itemFinalListener);
        chkImprimibleEdicion.removeItemListener(itemImprimibleListener);

        txtDescripcion.setText(getDto().getDescripcion());
        txtCodigo.setText(getDto().getCodigo());
        txtStockMinimo.setText(String.valueOf(getDto().getStockMinimo()));

        HashMap parametros = new HashMap();
        medidasTs = (ArrayList<UnidadesMedidaT>) DesktopApp.getApplication().getUnidadesMedidaT(parametros);
        int index = 0;
        int iteration = 1;
        for (UnidadesMedidaT medida : medidasTs) {
            if (getDto().getIdUnidMedida() != null) {
                if (medida.getIdUnidMedida().equals(getDto().getIdUnidMedida().getIdUnidMedida())) {
                    index = iteration;
                }
            }
            iteration++;
        }

        cboMedida.setSelectedIndex(index);

        parametros = new HashMap();
        parametros.put("pIdArticulo", getDto().getIdArticulo());
        componentesTs = (ArrayList<ComponentesT>) DesktopApp.getApplication().getComponentesT(parametros);

        tableModelComponentes = new ComponentesArticulosTableModel(columnNamesComponentes, componentesTs);
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

        actividadesTs = (ArrayList<ActividadesArticulosT>) DesktopApp.getApplication().getActividadesArticulosT(parametros);

        tableModelActividades = new ActividadesArticulosTableModel(columnNamesActividades, actividadesTs);
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

        txtDescripcion.setEnabled(false);
        txtCodigo.setEnabled(false);
        txtStockMinimo.setEnabled(false);
        cboMedida.setEnabled(false);

        cboMedida.addItemListener(itemMedidaListener);
        chkFinalEdicion.addItemListener(itemFinalListener);
        chkImprimibleEdicion.addItemListener(itemImprimibleListener);
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

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
        txtCodigoBusqueda.setText(codigoArticulo);
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
        chkImprimible = new javax.swing.JCheckBox();
        chkFinal = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStockMinimo = new javax.swing.JTextField();
        cboMedida = new javax.swing.JComboBox();
        chkImprimibleEdicion = new javax.swing.JCheckBox();
        chkFinalEdicion = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblComponentes = new javax.swing.JTable();
        btnAplicar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblActividades = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
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

        chkImprimible.setText(resourceMap.getString("chkImprimible.text")); // NOI18N
        chkImprimible.setName("chkImprimible"); // NOI18N

        chkFinal.setText(resourceMap.getString("chkFinal.text")); // NOI18N
        chkFinal.setName("chkFinal"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(chkImprimible)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkFinal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                                .addComponent(btnBuscar))
                            .addComponent(txtCodigoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtCodigo.setText(resourceMap.getString("txtCodigo.text")); // NOI18N
        txtCodigo.setName("txtCodigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtDescripcion.setText(resourceMap.getString("txtDescripcion.text")); // NOI18N
        txtDescripcion.setName("txtDescripcion"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtStockMinimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtStockMinimo.setText(resourceMap.getString("txtStockMinimo.text")); // NOI18N
        txtStockMinimo.setName("txtStockMinimo"); // NOI18N
        txtStockMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockMinimoKeyReleased(evt);
            }
        });

        cboMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMedida.setName("cboMedida"); // NOI18N

        chkImprimibleEdicion.setText(resourceMap.getString("chkImprimibleEdicion.text")); // NOI18N
        chkImprimibleEdicion.setName("chkImprimibleEdicion"); // NOI18N

        chkFinalEdicion.setText(resourceMap.getString("chkFinalEdicion.text")); // NOI18N
        chkFinalEdicion.setName("chkFinalEdicion"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

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
        jScrollPane2.setViewportView(tblComponentes);

        btnAplicar.setAction(actionMap.get("aplicar")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N

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

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(chkImprimibleEdicion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkFinalEdicion))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtStockMinimo, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(btnAplicar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkImprimibleEdicion)
                    .addComponent(chkFinalEdicion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(btnAplicar)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        btnAgregar.setAction(actionMap.get("agregar")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N

        btnSeleccionar.setAction(actionMap.get("seleccionar")); // NOI18N
        btnSeleccionar.setName("btnSeleccionar"); // NOI18N

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
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap())
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

private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

    if (isModificado() || isNuevo()) {
        if (JOptionPane.showInternalConfirmDialog(this, "Hay informacion que no han sido guardada\n¿Desea cerrar de todos modos?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    } else {
        dispose();
    }

}//GEN-LAST:event_formInternalFrameClosing

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased

    getDto().setCodigo(txtCodigo.getText());
    setModificado(true);

}//GEN-LAST:event_txtCodigoKeyReleased

private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased

    getDto().setDescripcion(txtDescripcion.getText());
    setModificado(true);

}//GEN-LAST:event_txtDescripcionKeyReleased

private void txtStockMinimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinimoKeyReleased

    getDto().setStockMinimo(Float.valueOf(txtStockMinimo.getText()));
    setModificado(true);

}//GEN-LAST:event_txtStockMinimoKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox cboMedida;
    private javax.swing.JCheckBox chkFinal;
    private javax.swing.JCheckBox chkFinalEdicion;
    private javax.swing.JCheckBox chkImprimible;
    private javax.swing.JCheckBox chkImprimibleEdicion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblActividades;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JTable tblComponentes;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoBusqueda;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtStockMinimo;
    // End of variables declaration//GEN-END:variables
    private ArticulosTableModel tableModel;
    private ComponentesArticulosTableModel tableModelComponentes;
    private ActividadesArticulosTableModel tableModelActividades;
    public static final String[] columnNames = {
        "Id", "Codigo", "Descripcion", "Stock Minimo",
        "Unidad de medida", "Estado", "Imprimible", "Final"
    };
    public static final String[] columnNamesComponentes = {
        "Componente", "Orden", "Cantidad"
    };
    public static final String[] columnNamesActividades = {
        "Actividad", "Orden", "Tiempo"
    };
    private TableRowSorter sorter;
    private TableRowSorter sorterComponentes;
    private TableRowSorter sorterActividad;
    private String codigoArticulo;
}