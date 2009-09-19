/*
 * ABMClientes.java
 *
 * Created on 28 de junio de 2009, 19:14
 */
package ar.com.jpack.desktop.ventas;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.helpers.CustomTableModelListener;
import ar.com.jpack.helpers.tablemodels.ClientesTableModel;
import ar.com.jpack.transferencia.ClientesT;
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
 * @author  jmhanun
 */
public class ABMClientes extends CustomInternalFrame<ClientesT> {

    /** Creates new form ABMClientes */
    public ABMClientes() {
        super(new ClientesT());
        initComponents();
        HashMap parametros = new HashMap();
        setListDto((ArrayList<ClientesT>) DesktopApp.getApplication().getClientesT(parametros));

        tableModel = new ClientesTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());
        tblClientes.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblClientes.setRowSorter(sorter);

        setModificado(false);
        setNuevo(false);

        if (getPadre() == null) {
            btnSeleccionar.setEnabled(false);
        }
        tblClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


    }

    @Action
    public void agregar() {
        if (!isNuevo()) {
            if (isModificado()) {
                if (JOptionPane.showInternalConfirmDialog(this, "Algunos datos han sido modificados.\n¿Desea conservar esos cambios?", "Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    aplicar();
                } else {
                    setDto(getOldDto());
                    txtCuitEdicion.setEnabled(false);
                    txtRazonSocial.setEnabled(false);
                    txtLimiteCredito.setEnabled(false);
                    txtMails.setEnabled(false);
                    txtTelefonos.setEnabled(false);
                }
            }
            setDto(new ClientesT());
            cambiarClienteT();
            txtCuitEdicion.setEnabled(true);
            txtRazonSocial.setEnabled(true);
            txtLimiteCredito.setEnabled(true);
            txtMails.setEnabled(true);
            txtTelefonos.setEnabled(true);
            jTabbedPane1.setSelectedIndex(1);
            setNuevo(true);
            setModificado(true);
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
            txtRazonSocial.requestFocus();
        }
    }

    @Action
    public void buscar() {
        HashMap parametros = new HashMap();
        if (!txtCuit.getText().isEmpty()) {
            parametros.put("pCuit", txtCuit.getText());
        }
        if (!txtNombre.getText().isEmpty()) {
            parametros.put("pNombres", txtNombre.getText());
        }
        if (!txtIdCliente.getText().isEmpty()) {
            try {
                parametros.put("pIdCliente", Integer.parseInt(txtIdCliente.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showInternalMessageDialog(this, "El valor de id del cliente debe ser numerico");
                return;
            }
        }
        setListDto((ArrayList<ClientesT>) DesktopApp.getApplication().getClientesT(parametros));

        tableModel = new ClientesTableModel(columnNames, this.getListDto());
        tableModel.addTableModelListener(new CustomTableModelListener());

        tblClientes.setModel(tableModel);

        sorter = new TableRowSorter<TableModel>(tableModel) {

            @Override
            public void toggleSortOrder(int column) {
                RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                setRowFilter(null);
                super.toggleSortOrder(column);
                setRowFilter(f);
            }
        };
        tblClientes.setRowSorter(sorter);

        if ((getPadre() != null) && (getListDto().size() == 1)) {
            tblClientes.setRowSelectionInterval(0, 0);
            setDto((ClientesT) tableModel.getRow(sorter.convertRowIndexToModel(tblClientes.getSelectedRow())));
            cambiarClienteT();
            seleccionar();
        }
    }

    @Action
    public void borrar() {
        if (tblClientes.getSelectedRow() != - 1) {
            ClientesT x = (ClientesT) tableModel.getRow(sorter.convertRowIndexToModel(tblClientes.getSelectedRow()));
            Integer z = DesktopApp.getApplication().deleteClientesT(x.getIdCliente());
            //poner validacion correcta
            if (z == 0) {
                JOptionPane.showInternalMessageDialog(this, "No se puede eliminar el registro porque tiene datos asociados");
            }


            HashMap parametros = new HashMap();
            List<ClientesT> nuevo = DesktopApp.getApplication().getClientesT(parametros);
            setListDto((ArrayList<ClientesT>) nuevo);

            tableModel = new ClientesTableModel(columnNames, this.getListDto());
            tableModel.addTableModelListener(new CustomTableModelListener());
            tblClientes.setModel(tableModel);

            sorter = new TableRowSorter<TableModel>(tableModel) {

                @Override
                public void toggleSortOrder(int column) {
                    RowFilter<? super TableModel, ? super Integer> f = getRowFilter();
                    setRowFilter(null);
                    super.toggleSortOrder(column);
                    setRowFilter(f);
                }
            };
            tblClientes.setRowSorter(sorter);
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un cliente");
        }
    }

    @Action
    public void seleccionar() {
        if (getDto() != null) {
            if (getDto().getIdCliente() != null) {
                ClientesT cli = getDto();
                if (tblClientes.getSelectedRow() != - 1) {
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.ventas.RegistrarRemito")) {

                        ((RegistrarRemito) getPadre()).agregarCliente(cli);
                        cancelar();
                    }
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.ventas.ABMDomicilios")) {

                        ((ABMDomicilios) getPadre()).agregarPersona(cli);
                        cancelar();
                    }
                    if (getPadre().getClass().getCanonicalName().equals("ar.com.jpack.desktop.ventas.Facturador")) {

                        ((Facturador) getPadre()).agregarCliente(cli);
                        cancelar();
                    }
                } else {
                    JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un cliente");
                }
            } else {
                JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un cliente");
            }
        } else {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un cliente");
        }
    }

    @Action
    public void cancelar() {
        try {
            setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ABMClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Action
    public void modificar() {
        txtCuitEdicion.setEnabled(true);
        txtRazonSocial.setEnabled(true);
        txtLimiteCredito.setEnabled(true);
        txtMails.setEnabled(true);
        txtTelefonos.setEnabled(true);
        jTabbedPane1.setSelectedIndex(1);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        txtRazonSocial.requestFocus();
    }

    @Action(enabledProperty = "modificado")
    public void aplicar() {
        try {
            if (isNuevo() || isModificado()) {

                setDto(DesktopApp.getApplication().updateClientesT(getDto()));
                if (isNuevo()) {
                    tableModel.addRow(getDto());
                }
                setDto(new ClientesT());
                cambiarClienteT();

                setModificado(false);
                setNuevo(false);
                txtCuitEdicion.setEnabled(false);
                txtRazonSocial.setEnabled(false);
                txtLimiteCredito.setEnabled(false);
                txtMails.setEnabled(false);
                txtTelefonos.setEnabled(false);
                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(true);
                jTabbedPane1.setSelectedIndex(0);
                tblClientes.clearSelection();
            }
        } catch (javax.ejb.EJBException ex) {
            JOptionPane.showInternalMessageDialog(this, "No es posible agregar el nuevo registro.\nVerifique que los datos sean los correctos");
        }

    }

    void habilitarBtnSeleccionar(boolean valor) {

        btnSeleccionar.setEnabled(valor);
        btnAgregar.setEnabled(!valor);
        btnBorrar.setEnabled(!valor);
        btnModificar.setEnabled(!valor);
    }

    private void cambiarClienteT() {
        txtCuitEdicion.setText(getDto().getCuit());
        txtRazonSocial.setText(getDto().getNombres());
        txtLimiteCredito.setText(String.valueOf(getDto().getLimiteCredito()));
        txtMails.setText(getDto().getMails());
        txtTelefonos.setText(getDto().getTelefonos());

        txtCuitEdicion.setEnabled(false);
        txtRazonSocial.setEnabled(false);
        txtLimiteCredito.setEnabled(false);
        txtMails.setEnabled(false);
        txtTelefonos.setEnabled(false);
    }

    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
        txtCuit.setText(cuitCliente);
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        txtNombre.setText(nombreCliente);
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
        txtIdCliente.setText(numeroCliente);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCuit = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtCuitEdicion = new javax.swing.JTextField();
        txtTelefonos = new javax.swing.JTextField();
        txtMails = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtLimiteCredito = new javax.swing.JTextField();
        btnAplicar = new javax.swing.JButton();

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

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ABMClientes.class, this);
        btnCancelar.setAction(actionMap.get("cancelar")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N

        btnBorrar.setAction(actionMap.get("borrar")); // NOI18N
        btnBorrar.setName("btnBorrar"); // NOI18N

        btnModificar.setAction(actionMap.get("modificar")); // NOI18N
        btnModificar.setName("btnModificar"); // NOI18N

        btnSeleccionar.setAction(actionMap.get("seleccionar")); // NOI18N
        btnSeleccionar.setName("btnSeleccionar"); // NOI18N

        btnAgregar.setAction(actionMap.get("agregar")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.setName("tblClientes"); // NOI18N
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        tblClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ABMClientes.class);
        tblClientes.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblClientes.columnModel.title0")); // NOI18N
        tblClientes.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblClientes.columnModel.title1")); // NOI18N
        tblClientes.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblClientes.columnModel.title2")); // NOI18N
        tblClientes.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblClientes.columnModel.title3")); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtIdCliente.setText(resourceMap.getString("txtIdCliente.text")); // NOI18N
        txtIdCliente.setName("txtIdCliente"); // NOI18N

        txtNombre.setText(resourceMap.getString("txtNombre.text")); // NOI18N
        txtNombre.setName("txtNombre"); // NOI18N

        txtCuit.setText(resourceMap.getString("txtCuit.text")); // NOI18N
        txtCuit.setName("txtCuit"); // NOI18N

        btnBuscar.setAction(actionMap.get("buscar")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                                    .addComponent(txtCuit, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtRazonSocial.setText(resourceMap.getString("txtRazonSocial.text")); // NOI18N
        txtRazonSocial.setName("txtRazonSocial"); // NOI18N
        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyReleased(evt);
            }
        });

        txtCuitEdicion.setText(resourceMap.getString("txtCuitEdicion.text")); // NOI18N
        txtCuitEdicion.setName("txtCuitEdicion"); // NOI18N
        txtCuitEdicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuitEdicionKeyReleased(evt);
            }
        });

        txtTelefonos.setText(resourceMap.getString("txtTelefonos.text")); // NOI18N
        txtTelefonos.setName("txtTelefonos"); // NOI18N
        txtTelefonos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonosKeyReleased(evt);
            }
        });

        txtMails.setText(resourceMap.getString("txtMails.text")); // NOI18N
        txtMails.setName("txtMails"); // NOI18N
        txtMails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMailsKeyReleased(evt);
            }
        });

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtLimiteCredito.setText(resourceMap.getString("txtLimiteCredito.text")); // NOI18N
        txtLimiteCredito.setName("txtLimiteCredito"); // NOI18N
        txtLimiteCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLimiteCreditoKeyReleased(evt);
            }
        });

        btnAplicar.setAction(actionMap.get("aplicar")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCuitEdicion, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefonos, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                            .addComponent(txtMails, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                            .addComponent(txtLimiteCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)))
                    .addComponent(btnAplicar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCuitEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAplicar)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnBorrar)
                    .addComponent(btnCancelar)
                    .addComponent(btnModificar))
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

private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked

    //para el caso en que se navegue la tabla con el mouse
    setDto((ClientesT) tableModel.getRow(sorter.convertRowIndexToModel(tblClientes.getSelectedRow())));
    cambiarClienteT();
    if (evt.getClickCount() == 2) {
        this.jTabbedPane1.setSelectedIndex(1);
    }

}//GEN-LAST:event_tblClientesMouseClicked

private void tblClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClientesKeyReleased

    //para el caso en que se navegue la tabla con las flechas
    setDto((ClientesT) tableModel.getRow(sorter.convertRowIndexToModel(tblClientes.getSelectedRow())));
    cambiarClienteT();


}//GEN-LAST:event_tblClientesKeyReleased

private void txtRazonSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyReleased

    getDto().setNombres(String.valueOf(txtRazonSocial.getText()));
    setModificado(true);

}//GEN-LAST:event_txtRazonSocialKeyReleased

private void txtCuitEdicionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitEdicionKeyReleased

    getDto().setCuit(String.valueOf(txtCuitEdicion.getText()));
    setModificado(true);


}//GEN-LAST:event_txtCuitEdicionKeyReleased

private void txtTelefonosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonosKeyReleased

    getDto().setTelefonos(String.valueOf(txtTelefonos.getText()));
    setModificado(true);

}//GEN-LAST:event_txtTelefonosKeyReleased

private void txtMailsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMailsKeyReleased

    getDto().setMails(String.valueOf(txtMails.getText()));
    setModificado(true);


}//GEN-LAST:event_txtMailsKeyReleased

private void txtLimiteCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLimiteCreditoKeyReleased

    getDto().setLimiteCredito(Integer.valueOf(txtLimiteCredito.getText()));
    setModificado(true);


}//GEN-LAST:event_txtLimiteCreditoKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtCuitEdicion;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtLimiteCredito;
    private javax.swing.JTextField txtMails;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefonos;
    // End of variables declaration//GEN-END:variables
    public static final String[] columnNames = {
        "Id", "Nombre", "CUIT"
    };
    protected ClientesTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
    private String numeroCliente;
    private String cuitCliente;
    private String nombreCliente;
}
