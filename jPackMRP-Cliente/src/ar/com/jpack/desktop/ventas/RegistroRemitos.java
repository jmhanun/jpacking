/*
 * RegistroRemitos.java
 *
 * Created on 3 de julio de 2008, 15:42
 */
package ar.com.jpack.desktop.ventas;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.desktop.DesktopView;
import ar.com.jpack.transferencia.ClientesT;
import ar.com.jpack.util.AuxiliarDetalleRemitosT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;

/**
 *
 * @author  jmhanun
 */
public class RegistroRemitos extends javax.swing.JInternalFrame {

    private Binding findBinding(BindingGroup bindingGroup, Object source, Object target) {
        for (Iterator<Binding> i = bindingGroup.getBindings().iterator(); i.hasNext();) {
            Binding b = i.next();
            boolean found =
                    (source == null || b.getSourceObject() == source) &&
                    (target == null || b.getTargetObject() == target);
            if (found) {
                return b;
            }

        }
        return null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        arrayList1 = new java.util.ArrayList<ar.com.jpack.util.AuxiliarDetalleRemitosT>();
        remitoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        idClienteTextField = new javax.swing.JTextField();
        cuitTextField = new javax.swing.JTextField();
        nombresTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        situacionIvaTextField = new javax.swing.JTextField();
        articulosPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        articulosTable = new javax.swing.JTable();
        borrarButton = new javax.swing.JButton();
        agregarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(RegistroRemitos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        remitoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("remitoPanel.border.title"))); // NOI18N
        remitoPanel.setName("remitoPanel"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        idClienteTextField.setEditable(false);
        idClienteTextField.setText(resourceMap.getString("idClienteTextField.text")); // NOI18N
        idClienteTextField.setName("idClienteTextField"); // NOI18N

        cuitTextField.setEditable(false);
        cuitTextField.setText(resourceMap.getString("cuitTextField.text")); // NOI18N
        cuitTextField.setName("cuitTextField"); // NOI18N

        nombresTextField.setEditable(false);
        nombresTextField.setText(resourceMap.getString("nombresTextField.text")); // NOI18N
        nombresTextField.setName("nombresTextField"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(RegistroRemitos.class, this);
        jButton4.setAction(actionMap.get("showLOVClientes")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        jFormattedTextField1.setEditable(false);
        jFormattedTextField1.setText(resourceMap.getString("jFormattedTextField1.text")); // NOI18N
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N

        situacionIvaTextField.setEditable(false);
        situacionIvaTextField.setText(resourceMap.getString("situacionIvaTextField.text")); // NOI18N
        situacionIvaTextField.setName("situacionIvaTextField"); // NOI18N

        javax.swing.GroupLayout remitoPanelLayout = new javax.swing.GroupLayout(remitoPanel);
        remitoPanel.setLayout(remitoPanelLayout);
        remitoPanelLayout.setHorizontalGroup(
            remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remitoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(remitoPanelLayout.createSequentialGroup()
                        .addGroup(remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(remitoPanelLayout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(9, 9, 9))
                            .addGroup(remitoPanelLayout.createSequentialGroup()
                                .addComponent(cuitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(12, 12, 12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(situacionIvaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(remitoPanelLayout.createSequentialGroup()
                        .addComponent(idClienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombresTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );

        remitoPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cuitTextField, jFormattedTextField1, jTextField1, situacionIvaTextField});

        remitoPanelLayout.setVerticalGroup(
            remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remitoPanelLayout.createSequentialGroup()
                .addGroup(remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idClienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(nombresTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(remitoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cuitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(situacionIvaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        articulosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("articulosPanel.border.title"))); // NOI18N
        articulosPanel.setName("articulosPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        articulosTable.setName("articulosTable"); // NOI18N

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, arrayList1, articulosTable, "jTable");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idArticulo}"));
        columnBinding.setColumnName("Id Articulo");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cantidad}"));
        columnBinding.setColumnName("Cantidad");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idUnidadMedida}"));
        columnBinding.setColumnName("Id Unidad Medida");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${precioUnitario}"));
        columnBinding.setColumnName("Precio Unitario");
        columnBinding.setColumnClass(Double.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importe}"));
        columnBinding.setColumnName("Importe");
        columnBinding.setColumnClass(Double.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(articulosTable);

        borrarButton.setAction(actionMap.get("borrarItem")); // NOI18N
        borrarButton.setEnabled(false);
        borrarButton.setName("borrarButton"); // NOI18N

        agregarButton.setAction(actionMap.get("agregarItem")); // NOI18N
        agregarButton.setName("agregarButton"); // NOI18N

        javax.swing.GroupLayout articulosPanelLayout = new javax.swing.GroupLayout(articulosPanel);
        articulosPanel.setLayout(articulosPanelLayout);
        articulosPanelLayout.setHorizontalGroup(
            articulosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulosPanelLayout.createSequentialGroup()
                .addContainerGap(332, Short.MAX_VALUE)
                .addComponent(agregarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrarButton)
                .addContainerGap())
            .addGroup(articulosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        articulosPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {agregarButton, borrarButton});

        articulosPanelLayout.setVerticalGroup(
            articulosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(articulosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrarButton)
                    .addComponent(agregarButton))
                .addContainerGap())
        );

        cancelarButton.setAction(actionMap.get("cancelar")); // NOI18N
        cancelarButton.setName("cancelarButton"); // NOI18N

        jButton2.setAction(actionMap.get("grabarRemito")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(remitoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(articulosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(340, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelarButton)
                .addGap(6, 6, 6))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelarButton, jButton2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(remitoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(articulosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(jButton2))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarButton;
    private java.util.ArrayList<ar.com.jpack.util.AuxiliarDetalleRemitosT> arrayList1;
    private javax.swing.JPanel articulosPanel;
    private javax.swing.JTable articulosTable;
    private javax.swing.JButton borrarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField cuitTextField;
    private javax.swing.JTextField idClienteTextField;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nombresTextField;
    private javax.swing.JPanel remitoPanel;
    private javax.swing.JTextField situacionIvaTextField;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    private static RegistroRemitos registroRemitos = new RegistroRemitos();
    private LOVClientes lOVClientes = null;
    private ClientesT clienteT;

    /** Creates new form RegistroRemitos */
    public RegistroRemitos() {
        initComponents();
    }

    public static RegistroRemitos getRegistroRemitos() {
        return registroRemitos;

    }

    public ClientesT getClienteT() {
        return clienteT;
    }

    public void setClienteT(ClientesT clienteT) {
        this.clienteT = clienteT;
    }

    @Action
    public void showLOVClientes() {
        if (lOVClientes == null) {
            JFrame mainFrame = DesktopApp.getApplication().getMainFrame();
            lOVClientes = new LOVClientes(mainFrame, true);
            lOVClientes.setLocationRelativeTo(mainFrame);
        } else {
            lOVClientes.limpiar();
        }
        DesktopApp.getApplication().show(lOVClientes);
        setClienteT(lOVClientes.getClienteTSeleccionado());
        if (clienteT != null) {
            idClienteTextField.setText(clienteT.getIdCliente().toString());
            nombresTextField.setText(clienteT.getNombres());
            cuitTextField.setText(clienteT.getCuit());
            situacionIvaTextField.setText(clienteT.getSituacionIva());
        } else {
            limpiar();
        }
    }

    @Action
    public void cancelar() {
        limpiar();
        this.dispose();
    }

    @Action
    public Task grabarRemito() {
        return new GrabarRemitoTask(DesktopApp.getApplication(), "Grabando remito...");
    }

    private boolean isDetalleCompleto() {
        boolean detalleCompleto = true;
        Iterator<AuxiliarDetalleRemitosT> it = arrayList1.iterator();
        while (it.hasNext() && detalleCompleto) {
            AuxiliarDetalleRemitosT auxiliarDetalleRemitosT = it.next();
            detalleCompleto = auxiliarDetalleRemitosT.isCompleto();
        }
        return detalleCompleto;
    }

    private void limpiar() {
        setClienteT(null);
        idClienteTextField.setText("");
        nombresTextField.setText("");
        cuitTextField.setText("");
        situacionIvaTextField.setText("");
        if (!arrayList1.isEmpty()) {
            Binding b = findBinding(bindingGroup, arrayList1, articulosTable);
            b.unbind();
            arrayList1.clear();
            b.bind();
            articulosTable.repaint();
        }
    }

    private class GrabarRemitoTask extends org.jdesktop.application.Task<String, Void> {

        String mensaje;
        DesktopView view;

        GrabarRemitoTask(DesktopApp app, String mensaje) {
            super(app);
            this.mensaje = mensaje;
            this.view = app.getDesktopView();
        }

        @Override
        protected String doInBackground() {
            view.setStatusMessage(mensaje);
            JOptionPane.showMessageDialog(null, "aqui se graba");
            cancelar();
            return "Remito grabado";
        }

        @Override
        protected void succeeded(String result) {
            super.succeeded(result);
            view.setStatusMessage(result);
        }
    }

    @Action
    public void borrarItem() {
        int n = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los registros seleccionados?", "Aviso",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
        if (n == JOptionPane.YES_OPTION) {
            int[] selected = articulosTable.getSelectedRows();
            List<AuxiliarDetalleRemitosT> toRemove = new ArrayList<AuxiliarDetalleRemitosT>(selected.length);
            for (int idx = 0; idx < selected.length; idx++) {
                AuxiliarDetalleRemitosT aux = arrayList1.get(articulosTable.convertRowIndexToModel(selected[idx]));
                toRemove.add(aux);
            }
            arrayList1.removeAll(toRemove);
        }
    }

    @Action
    public void agregarItem() {
        if (clienteT != null && isDetalleCompleto()) {
            if (arrayList1 == null) {
                arrayList1 = new ArrayList<AuxiliarDetalleRemitosT>();
            }
            AuxiliarDetalleRemitosT aux = new AuxiliarDetalleRemitosT();
            Binding b = findBinding(bindingGroup, arrayList1, articulosTable);
            b.unbind();
            arrayList1.add(aux);
            b.bind();
            articulosTable.repaint();
            int row = articulosTable.getRowCount() - 1;
            articulosTable.setRowSelectionInterval(row, row);
            articulosTable.scrollRectToVisible(articulosTable.getCellRect(row, 0, true));
        } else {
            JOptionPane.showMessageDialog(null, "epa! complete antes de continuar");
        }
    }
}
