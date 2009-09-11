/*
 * TipificarDesvio.java
 *
 * Created on 9 de septiembre de 2009, 22:07
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomInternalFrame;
import ar.com.jpack.transferencia.DesviosT;
import ar.com.jpack.transferencia.DetalleProduccionT;
import ar.com.jpack.transferencia.TiposDesviosT;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author  jmhanun
 */
public class TipificarDesvio extends CustomInternalFrame<DesviosT> {

    private ItemListener itemListener;
    private ArrayList<TiposDesviosT> tiposDesvioTs;

    /** Creates new form TipificarDesvio */
    public TipificarDesvio() {
        super(new DesviosT());
        initComponents();


        itemListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                getDto().setIdtipodesvio((TiposDesviosT) e.getItem());
            }
        };

        setModificado(false);
        setNuevo(false);

        HashMap parametros = new HashMap();
        tiposDesvioTs = (ArrayList<TiposDesviosT>) DesktopApp.getApplication().getTiposDesviosT(parametros);

        DefaultComboBoxModel tiposDesviosComboBoxModel = new DefaultComboBoxModel();
        for (TiposDesviosT tiposDesviosT : tiposDesvioTs) {
            tiposDesviosComboBoxModel.addElement(tiposDesviosT);
        }
        cboTipoDesvio.setModel(tiposDesviosComboBoxModel);
        cboTipoDesvio.addItemListener(itemListener);
        cboTipoDesvio.setSelectedIndex(0);

    }

    @Action
    public void aceptar() {
        DesktopApp.getApplication().insertDesvioT(detalleProduccionT.getIdDetalleProduccion(), getDto().getIdtipodesvio().getIdTipoDesvio(), txtObservacion.getText());
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TipificarDesvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DetalleProduccionT getDetalleProduccionT() {
        return detalleProduccionT;
    }

    public void setDetalleProduccionT(DetalleProduccionT detalleProduccionT) {
        this.detalleProduccionT = detalleProduccionT;
    }

    public Integer getTipoDesvio() {
        return tipoDesvio;
    }

    public void setTipoDesvio(Integer tipoDesvio) {
        this.tipoDesvio = tipoDesvio;
        String titulo = null;
        switch (tipoDesvio) {
            case 1:
                titulo = "Desvio por desfasaje en inicio";
                break;
            case 2:
                titulo = "Desvio por desfasaje en inicio";
                break;
            case 3:
                titulo = "Desvio por desfasaje en inicio";
                break;
            default:
                titulo = "--";
                break;
        }
        lblTitulo.setText(titulo);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboTipoDesvio = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();

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

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(TipificarDesvio.class);
        lblTitulo.setText(resourceMap.getString("lblTitulo.text")); // NOI18N
        lblTitulo.setName("lblTitulo"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        cboTipoDesvio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTipoDesvio.setName("cboTipoDesvio"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        txtObservacion.setName("txtObservacion"); // NOI18N
        jScrollPane1.setViewportView(txtObservacion);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(TipificarDesvio.class, this);
        btnAceptar.setAction(actionMap.get("aceptar")); // NOI18N
        btnAceptar.setName("btnAceptar"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoDesvio, 0, 338, Short.MAX_VALUE))
                    .addComponent(jLabel3)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTipoDesvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JComboBox cboTipoDesvio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtObservacion;
    // End of variables declaration//GEN-END:variables
    private Integer tipoDesvio;
    private DetalleProduccionT detalleProduccionT;
}