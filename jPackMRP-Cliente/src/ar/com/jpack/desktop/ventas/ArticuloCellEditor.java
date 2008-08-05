/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.desktop.ventas;

import ar.com.jpack.desktop.DesktopApp;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author jmhanun
 */
public class ArticuloCellEditor extends AbstractCellEditor implements TableCellEditor {

    JComponent idArticulo = new JTextField();

    public Object getCellEditorValue() {
        return ((JTextField) idArticulo).getText();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (DesktopApp.getApplication().isArticulo((Integer) value)) {
            ((JTextField) idArticulo).setText((String) value);

        } else {

            ((JTextField) idArticulo).setText("-1");

        }
        return idArticulo;
    }
    /*
     *         // This is the component that will handle the editing of the cell value
    JComponent component = new JTextField();
    
    // This method is called when a cell value is edited by the user.
    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int rowIndex, int vColIndex) {
    // 'value' is value contained in the cell located at (rowIndex, vColIndex)
    
    if (isSelected) {
    // cell (and perhaps other cells) are selected
    }
    
    // Configure the component with the specified value
    ((JTextField)component).setText((String)value);
    
    // Return the configured component
    return component;
    }
    
    // This method is called when editing is completed.
    // It must return the new value to be stored in the cell.
    public Object getCellEditorValue() {
    return ((JTextField)component).getText();
    }
    
     */
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        if (DesktopApp.getApplication().isArticulo((Integer) value)) {
//            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        } else {
//            return super.getTableCellRendererComponent(table, 0, isSelected, hasFocus, row, column);
//        }
}