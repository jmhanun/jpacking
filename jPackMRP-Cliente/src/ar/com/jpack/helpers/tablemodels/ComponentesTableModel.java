/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.ComponentesT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class ComponentesTableModel extends CustomTableModel {

    public static final int COMPONENTE_INDEX = 0;
    public static final int ORDEN_INDEX = 1;
    public static final int CANTIDAD_INDEX = 2;

    public ComponentesTableModel(String[] columnNames, List datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        ComponentesT record = (ComponentesT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case COMPONENTE_INDEX:
                return record.getComponentes().getCodigo();
            case ORDEN_INDEX:
                return record.getOrden();
            case CANTIDAD_INDEX:
                return record.getCantidad();
            default:
                return new Object();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ComponentesT record = (ComponentesT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case 1:
                record.setOrden(((Integer) aValue).intValue());
                break;
            case 2:
                record.setCantidad(((Float) aValue).floatValue());
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
}
