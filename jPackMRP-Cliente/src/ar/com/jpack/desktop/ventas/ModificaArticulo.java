/*
 * Articulos.java
 *
 * Created on 4 de agosto de 2008, 15:41
 */
package ar.com.jpack.desktop.ventas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.jdesktop.application.Action;

/**
 *
 * @author  jmhanun
 */
public class ModificaArticulo extends javax.swing.JDialog {

    /** Creates new form Articulos */
    public ModificaArticulo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setDefaultButton(aceptarButton);
    }
    protected int cantidad = 0;
    protected double precioUnitario = 0.0;
    public static final String PROP_PRECIOUNITARIO = "precioUnitario";
    public static final String PROP_CANTIDAD = "cantidad";

    /**
     * Get the value of precioUnitario
     *
     * @return the value of precioUnitario
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Set the value of precioUnitario
     *
     * @param precioUnitario new value of precioUnitario
     */
    public void setPrecioUnitario(double precioUnitario) {
        double oldPrecioUnitario = this.precioUnitario;
        this.precioUnitario = precioUnitario;
        propertyChangeSupport.firePropertyChange(PROP_PRECIOUNITARIO, oldPrecioUnitario, precioUnitario);
    }

    /**
     * Get the value of cantidad
     *
     * @return the value of cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Set the value of cantidad
     *
     * @param cantidad new value of cantidad
     */
    public void setCantidad(int cantidad) {
        int oldCantidad = this.cantidad;
        this.cantidad = cantidad;
        propertyChangeSupport.firePropertyChange(PROP_CANTIDAD, oldCantidad, cantidad);
    }
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void limpiar() {
        cantidadTextField.setText("0");
        precioUnitarioTextField.setText("0.0");
        aceptarButton.setEnabled(false);
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
        cantidadTextField = new javax.swing.JTextField();
        precioUnitarioTextField = new javax.swing.JTextField();
        cancelarButton = new javax.swing.JButton();
        aceptarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Articulos"); // NOI18N
        setResizable(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getResourceMap(ModificaArticulo.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        cantidadTextField.setName("cantidadTextField"); // NOI18N

        precioUnitarioTextField.setName("precioUnitarioTextField"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ar.com.jpack.desktop.DesktopApp.class).getContext().getActionMap(ModificaArticulo.class, this);
        cancelarButton.setAction(actionMap.get("cancelar")); // NOI18N
        cancelarButton.setName("cancelarButton"); // NOI18N

        aceptarButton.setAction(actionMap.get("seleccionarArticulo")); // NOI18N
        aceptarButton.setName("aceptarButton"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidadTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(precioUnitarioTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(aceptarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {aceptarButton, cancelarButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cantidadTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(precioUnitarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(aceptarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void seleccionarArticulo() {
        int cant = 0;
        double precio=0.0;
        try{
            cant = Integer.parseInt(cantidadTextField.getText());
            precio = Double.parseDouble(precioUnitarioTextField.getText());
            setCantidad(cant);
            setPrecioUnitario(precio);
            setVisible(false);
        }catch(NumberFormatException ex){
            setCantidad(cant);
            setPrecioUnitario(precio);
        }
    }

    @Action
    public void cancelar() {
        setCantidad(0);
        setPrecioUnitario(0.0);
        setVisible(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField cantidadTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField precioUnitarioTextField;
    // End of variables declaration//GEN-END:variables

}