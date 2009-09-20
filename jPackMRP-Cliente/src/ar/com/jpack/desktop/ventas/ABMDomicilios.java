/*
 * ABMDomicilios.java
 *
 * Created on 18 de septiembre de 2009, 18:52
 */
package ar.com.jpack.desktop.ventas;

import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.DomiciliosTableModel;
import ar.com.jpack.transferencia.DomiciliosT;
import java.awt.event.ItemEvent;
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
import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.compras.ABMProveedores;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.transferencia.LocalidadesT;
import ar.com.jpack.transferencia.ProveedoresT;
import ar.com.jpack.transferencia.ProvinciasT;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

/**
 *
 * @author  jmhanun
 */
public class ABMDomicilios extends CustomInternalFrame<DomiciliosT> {

    private ABMClientes clientesOpenFrame;
    private ABMProveedores proveedoresOpenFrame;
    private ItemListener itemLocalidadesListener;
    private ItemListener itemProvinciasListener;
    private ArrayList<LocalidadesT> localidadesTs;
    private ArrayList<ProvinciasT> provinciasTs;
    private DefaultComboBoxModel provinciasComboBoxModel;

    /** Creates new form ABMDomicilios */
    public ABMDomicilios() {
        super(new DomiciliosT());
        initComponents();

        itemProvinciasListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("<Ninguno>")) {
                    cboLocalidades.setEnabled(false);
                    cargaLocalidades(0);
                } else {
                    cboLocalidades.setEnabled(true);
                    cargaLocalidades(((ProvinciasT) e.getItem()).getIdProvincia());
                }
                cboLocalidades.setSelectedIndex(0);
                setModificado(true);
            }
        };

        itemLocalidadesListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("<Ninguno>")) {
                    getDto().setIdLocalidad(null);
                } else {
                    getDto().setIdLocalidad((LocalidadesT) e.getItem());
                    getDto().getIdLocalidad().setIdProvincia((ProvinciasT) cboProvincias.getSelectedItem());
                }
                setModificado(true);
            }
        };

        cboLocalidades.setEnabled(false);

        HashMap parametros = new HashMap();
        provinciasTs = (ArrayList<ProvinciasT>) DesktopApp.getApplication().getProvinciasT(parametros);

        provinciasComboBoxModel = new DefaultComboBoxModel();
        int index = 0;
        int iteration = 0;
        provinciasComboBoxModel.addElement("<Ninguno>");
        for (ProvinciasT provincia : provinciasTs) {
            provinciasComboBoxModel.addElement(provincia);
            if (getDto().getIdLocalidad() != null) {
                if (getDto().getIdLocalidad().getIdProvincia() != null) {
                    if (provincia.getIdProvincia().equals(getDto().getIdLocalidad().getIdProvincia().getIdProvincia())) {
                        index = iteration;
                    }
                }
            }
            iteration++;
        }
        cboProvincias.setModel(provinciasComboBoxModel);
        cboProvincias.setSelectedIndex(index);

        cargaLocalidades(0);

        DefaultComboBoxModel personasComboBoxModel = new DefaultComboBoxModel();
        personasComboBoxModel.addElement("Cliente");
        personasComboBoxModel.addElement("Proveedor");
        cboTipoPersona.setModel(personasComboBoxModel);
        cboTipoPersona.setSelectedIndex(0);

        txtPersonaDetalle.setEnabled(false);
        txtPersonaLista.setEnabled(false);

        setListDto(new ArrayList<DomiciliosT>());

        tableModel = new DomiciliosTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblDomicilios.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblDomicilios.setRowSorter(sorter);

        setModificado(false);
        setNuevo(false);

        if (getPadre() == null) {
            btnSeleccionar.setEnabled(false);
        }
        tblDomicilios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        btnEliminar.setEnabled(false);
    }

    @Action
    public void buscar() {
        if (cboTipoPersona.getSelectedItem().equals("Cliente")) {
            DesktopApp.getApplication().getDesktopView().setPadre(this);

            DesktopApp.getApplication().getDesktopView().showClientes().run();

            clientesOpenFrame = (ABMClientes) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.ventas.ABMClientes");

            clientesOpenFrame.setPadre(this);
            clientesOpenFrame.habilitarBtnSeleccionar(true);
            clientesOpenFrame.setNumeroCliente(txtNumeroPersona.getText());
            clientesOpenFrame.setCuitCliente(txtCuitPersona.getText());
            clientesOpenFrame.setNombreCliente(txtNombrePersona.getText());
            clientesOpenFrame.buscar();
        } else {
            DesktopApp.getApplication().getDesktopView().setPadre(this);

            DesktopApp.getApplication().getDesktopView().showProveedores().run();

            proveedoresOpenFrame = (ABMProveedores) DesktopApp.getApplication().getDesktopView().getInternalFrame("ar.com.jpack.desktop.compras.ABMProveedores");

            proveedoresOpenFrame.setPadre(this);
            proveedoresOpenFrame.habilitarBtnSeleccionar(true);
            proveedoresOpenFrame.setNumeroProveedor(txtNumeroPersona.getText());
            proveedoresOpenFrame.setCuitProveedor(txtCuitPersona.getText());
            proveedoresOpenFrame.setNombreProveedor(txtNombrePersona.getText());
            proveedoresOpenFrame.buscar();
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
                    txtBarrio.setEnabled(false);
                    txtCalle.setEnabled(false);
                    txtDepto.setEnabled(false);
                    txtNumero.setEnabled(false);
                    txtPiso.setEnabled(false);
                    txtTorre.setEnabled(false);
                    cboLocalidades.setEnabled(false);
                    cboProvincias.setEnabled(false);

                }
            }
            setDto(new DomiciliosT());
            getDto().setIdCliente(cliente);
            getDto().setIdProveedor(proveedor);

            cambiarDomicilioT();
            txtBarrio.setEnabled(true);
            txtCalle.setEnabled(true);
            txtDepto.setEnabled(true);
            txtNumero.setEnabled(true);
            txtPiso.setEnabled(true);
            txtTorre.setEnabled(true);
//            cboLocalidades.setEnabled(true);
            cboProvincias.setEnabled(true);
            jTabbedPane1.setSelectedIndex(1);
            setNuevo(true);
            setModificado(true);
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
            txtCalle.requestFocus();
        }
    }

    @Action
    public void seleccionar() {
    }

    @Action
    public void modificar() {
        txtBarrio.setEnabled(true);
        txtCalle.setEnabled(true);
        txtDepto.setEnabled(true);
        txtNumero.setEnabled(true);
        txtPiso.setEnabled(true);
        txtTorre.setEnabled(true);
//      cboLocalidades.setEnabled(true);
        cboProvincias.setEnabled(true);

        jTabbedPane1.setSelectedIndex(1);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        txtCalle.requestFocus();

    }

    @Action
    public void eliminar() {
    }

    @Action
    public void cancelar() {
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ABMDomicilios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Action(enabledProperty = "modificado")
    public void aplicar() {
        try {
            if (isNuevo() || isModificado()) {

                setDto(DesktopApp.getApplication().updateDomicilioT(getDto()));
                if (isNuevo()) {
                    tableModel.addRow(getDto());
                }
                setDto(new DomiciliosT());
                cambiarDomicilioT();

                setModificado(false);
                setNuevo(false);
                txtBarrio.setEnabled(false);
                txtCalle.setEnabled(false);
                txtDepto.setEnabled(false);
                txtNumero.setEnabled(false);
                txtPiso.setEnabled(false);
                txtTorre.setEnabled(false);
                cboLocalidades.setEnabled(false);
                cboProvincias.setEnabled(false);

                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(true);
                jTabbedPane1.setSelectedIndex(0);
                tblDomicilios.clearSelection();
            }
        } catch (javax.ejb.EJBException ex) {
            JOptionPane.showInternalMessageDialog(this, "No es posible agregar el nuevo registro.\nVerifique que los datos sean los correctos");
        }
    }

    public void agregarPersona(Object persona) {
        HashMap parametros = new HashMap();
        String datos;
        if (persona instanceof ClientesT) {
            cliente = (ClientesT) persona;
            proveedor = null;
            datos = "Cliente: " + cliente.getNombres();
            parametros.put("pIdCliente", cliente.getIdCliente());

        } else {
            proveedor = (ProveedoresT) persona;
            cliente = null;
            datos = "Proveedor: " + proveedor.getNombres();
            parametros.put("pIdProveedor", proveedor.getIdProveedor());
        }
        getDto().setIdCliente(cliente);
        getDto().setIdProveedor(proveedor);

        txtPersonaDetalle.setText(datos);
        txtPersonaLista.setText(datos);

        setListDto((ArrayList<DomiciliosT>) DesktopApp.getApplication().getDomiciliosT(parametros));

        tableModel = new DomiciliosTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblDomicilios.setModel(tableModel);

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
        tblDomicilios.setRowSorter(sorter);

    }

    private void cambiarDomicilioT() {
        cboProvincias.removeItemListener(itemProvinciasListener);
        cboLocalidades.removeItemListener(itemLocalidadesListener);
        txtBarrio.setText(getDto().getBarrio());
        txtCalle.setText(getDto().getCalle());
        txtDepto.setText(getDto().getDepartamento());
        txtNumero.setText(getDto().getNumero());
        txtPiso.setText(getDto().getPiso());
        txtTorre.setText(getDto().getTorre());

        HashMap parametros = new HashMap();
        provinciasTs = (ArrayList<ProvinciasT>) DesktopApp.getApplication().getProvinciasT(parametros);
        int index = 0;
        int iteration = 1;
        for (ProvinciasT provincia : provinciasTs) {
            if (getDto().getIdLocalidad() != null) {
                if (getDto().getIdLocalidad().getIdProvincia() != null) {
                    if (provincia.getIdProvincia().equals(getDto().getIdLocalidad().getIdProvincia().getIdProvincia())) {
                        index = iteration;
                    }
                }
            }
            iteration++;
        }
        cboProvincias.setSelectedIndex(index);


        int idProvincia;
        if (getDto().getIdLocalidad() != null) {
            if (getDto().getIdLocalidad().getIdProvincia() != null) {
                idProvincia = getDto().getIdLocalidad().getIdProvincia().getIdProvincia();
            } else {
                idProvincia = 0;
            }
        } else {
            idProvincia = 0;
        }
        cargaLocalidades(idProvincia);

        index = 0;
        iteration = 1;
        for (LocalidadesT localidad : localidadesTs) {
            if (getDto().getIdLocalidad() != null) {
                if (localidad.getIdLocalidad().equals(getDto().getIdLocalidad().getIdLocalidad())) {
                    index = iteration;
                }
            }
            iteration++;
        }

        cboLocalidades.setSelectedIndex(index);

        txtBarrio.setEnabled(false);
        txtCalle.setEnabled(false);
        txtDepto.setEnabled(false);
        txtNumero.setEnabled(false);
        txtPiso.setEnabled(false);
        txtTorre.setEnabled(false);
        cboProvincias.setEnabled(false);
        cboProvincias.addItemListener(itemProvinciasListener);
        cboLocalidades.setEnabled(false);
        cboLocalidades.addItemListener(itemLocalidadesListener);

    }

    private void cargaLocalidades(Integer idProvincia) {


        HashMap parametros = new HashMap();
        parametros.put("pIdProvincia", idProvincia);
        localidadesTs = (ArrayList<LocalidadesT>) DesktopApp.getApplication().getLocalidadesT(parametros);

        DefaultComboBoxModel localidadesComboBoxModel = new DefaultComboBoxModel();
        localidadesComboBoxModel.addElement("<Ninguno>");
        for (LocalidadesT localidad : localidadesTs) {
            localidadesComboBoxModel.addElement(localidad);
        }
        cboLocalidades.setModel(localidadesComboBoxModel);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumeroPersona = new javax.swing.JTextField();
        txtNombrePersona = new javax.swing.JTextField();
        txtCuitPersona = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtPersonaLista = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDomicilios = new javax.swing.JTable();
        cboTipoPersona = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtPersonaDetalle = new javax.swing.JTextField();
        txtCalle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPiso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDepto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTorre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtBarrio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboProvincias = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cboLocalidades = new javax.swing.JComboBox();
        btnAplicar = new javax.swing.JButton();
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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMDomicilios.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtNumeroPersona.setText(resourceMap.getString("txtNumeroPersona.text")); // NOI18N
        txtNumeroPersona.setName("txtNumeroPersona"); // NOI18N

        txtNombrePersona.setText(resourceMap.getString("txtNombrePersona.text")); // NOI18N
        txtNombrePersona.setName("txtNombrePersona"); // NOI18N

        txtCuitPersona.setText(resourceMap.getString("txtCuitPersona.text")); // NOI18N
        txtCuitPersona.setName("txtCuitPersona"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ABMDomicilios.class, this);
        btnBuscar.setAction(actionMap.get("buscar")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        txtPersonaLista.setText(resourceMap.getString("txtPersonaLista.text")); // NOI18N
        txtPersonaLista.setName("txtPersonaLista"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDomicilios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDomicilios.setName("tblDomicilios"); // NOI18N
        tblDomicilios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDomiciliosMouseClicked(evt);
            }
        });
        tblDomicilios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDomiciliosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDomicilios);

        cboTipoPersona.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTipoPersona.setName("cboTipoPersona"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cboTipoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                                .addComponent(btnBuscar)
                                .addGap(10, 10, 10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroPersona, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                                    .addComponent(txtCuitPersona, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                                    .addComponent(txtNombrePersona, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPersonaLista, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumeroPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombrePersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCuitPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(cboTipoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPersonaLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtPersonaDetalle.setText(resourceMap.getString("txtPersonaDetalle.text")); // NOI18N
        txtPersonaDetalle.setName("txtPersonaDetalle"); // NOI18N

        txtCalle.setText(resourceMap.getString("txtCalle.text")); // NOI18N
        txtCalle.setName("txtCalle"); // NOI18N
        txtCalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCalleKeyReleased(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtNumero.setText(resourceMap.getString("txtNumero.text")); // NOI18N
        txtNumero.setName("txtNumero"); // NOI18N
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtPiso.setText(resourceMap.getString("txtPiso.text")); // NOI18N
        txtPiso.setName("txtPiso"); // NOI18N
        txtPiso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPisoKeyReleased(evt);
            }
        });

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtDepto.setText(resourceMap.getString("txtDepto.text")); // NOI18N
        txtDepto.setName("txtDepto"); // NOI18N
        txtDepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeptoKeyReleased(evt);
            }
        });

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtTorre.setText(resourceMap.getString("txtTorre.text")); // NOI18N
        txtTorre.setName("txtTorre"); // NOI18N
        txtTorre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTorreKeyReleased(evt);
            }
        });

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtBarrio.setText(resourceMap.getString("txtBarrio.text")); // NOI18N
        txtBarrio.setName("txtBarrio"); // NOI18N
        txtBarrio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarrioKeyReleased(evt);
            }
        });

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        cboProvincias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProvincias.setName("cboProvincias"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        cboLocalidades.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocalidades.setName("cboLocalidades"); // NOI18N

        btnAplicar.setAction(actionMap.get("aplicar")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCalle, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPiso, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDepto, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTorre, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                    .addComponent(txtBarrio, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addComponent(cboLocalidades, 0, 406, Short.MAX_VALUE)
                    .addComponent(cboProvincias, 0, 406, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtPersonaDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(399, Short.MAX_VALUE)
                .addComponent(btnAplicar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPersonaDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtDepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTorre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cboLocalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAplicar)
                .addContainerGap(142, Short.MAX_VALUE))
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
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

private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

    if (isModificado() || isNuevo()) {
        if (JOptionPane.showInternalConfirmDialog(this, "Hay informacion que no han sido guardada\n¿Desea cerrar de todos modos?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    } else {
        dispose();
    }


}//GEN-LAST:event_formInternalFrameClosing

private void tblDomiciliosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDomiciliosKeyReleased

    //para el caso en que se navegue la tabla con las flechas
    setDto((DomiciliosT) tableModel.getRow(sorter.convertRowIndexToModel(tblDomicilios.getSelectedRow())));
    cambiarDomicilioT();


}//GEN-LAST:event_tblDomiciliosKeyReleased

private void tblDomiciliosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDomiciliosMouseClicked

    //para el caso en que se navegue la tabla con el mouse
    setDto((DomiciliosT) tableModel.getRow(sorter.convertRowIndexToModel(tblDomicilios.getSelectedRow())));
    cambiarDomicilioT();
    if (evt.getClickCount() == 2) {
        this.jTabbedPane1.setSelectedIndex(1);
    }


}//GEN-LAST:event_tblDomiciliosMouseClicked

private void txtCalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCalleKeyReleased

    getDto().setCalle(String.valueOf(txtCalle.getText()));
    setModificado(true);

}//GEN-LAST:event_txtCalleKeyReleased

private void txtBarrioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarrioKeyReleased

    getDto().setBarrio(String.valueOf(txtBarrio.getText()));
    setModificado(true);

}//GEN-LAST:event_txtBarrioKeyReleased

private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased

    getDto().setNumero(String.valueOf(txtNumero.getText()));
    setModificado(true);

}//GEN-LAST:event_txtNumeroKeyReleased

private void txtPisoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPisoKeyReleased

    getDto().setPiso(String.valueOf(txtPiso.getText()));
    setModificado(true);

}//GEN-LAST:event_txtPisoKeyReleased

private void txtDeptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeptoKeyReleased

    getDto().setDepartamento(String.valueOf(txtDepto.getText()));
    setModificado(true);

}//GEN-LAST:event_txtDeptoKeyReleased

private void txtTorreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTorreKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtTorreKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox cboLocalidades;
    private javax.swing.JComboBox cboProvincias;
    private javax.swing.JComboBox cboTipoPersona;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDomicilios;
    private javax.swing.JTextField txtBarrio;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCuitPersona;
    private javax.swing.JTextField txtDepto;
    private javax.swing.JTextField txtNombrePersona;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNumeroPersona;
    private javax.swing.JTextField txtPersonaDetalle;
    private javax.swing.JTextField txtPersonaLista;
    private javax.swing.JTextField txtPiso;
    private javax.swing.JTextField txtTorre;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Calle", "Numero", "Piso", "Depto", "Torre",
        "Barrio", "Localidad", "Provincia"
    };
    protected DomiciliosTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
    private ClientesT cliente;
    private ProveedoresT proveedor;
}