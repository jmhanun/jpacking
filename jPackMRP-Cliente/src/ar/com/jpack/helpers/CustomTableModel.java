/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public abstract class CustomTableModel<E> extends AbstractTableModel {

    protected String[] columnNames;
    protected List<E> dataVector;

    public CustomTableModel(String[] columnNames, List<E> datos) {
        this.columnNames = columnNames;
        dataVector = datos;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
//
//    @Override
//    public Class getColumnClass(int column) {
//        return String.class;
//    }

    @Override
    public Class<?> getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public E getRow(int row) {
        return dataVector.get(row);
    }

    public void addRow(E newRow) {
        dataVector.add(newRow);
        fireTableRowsInserted(
                dataVector.size() - 1,
                dataVector.size() - 1);
    }

    public void deleteRow(int deleteRow) {
        dataVector.remove(deleteRow);
        fireTableRowsDeleted(
                dataVector.size() - 1,
                dataVector.size() - 1);
    }

    public E removeRow(int deleteRow) {
        E removido = dataVector.remove(deleteRow);
        fireTableRowsDeleted(
                dataVector.size() - 1,
                dataVector.size() - 1);
        return removido;
    }
}
