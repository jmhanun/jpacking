/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class ActividadesArticulosTableModel extends CustomTableModel{
    public static final int ACTIVIDAD_INDEX = 0;
    public static final int ORDEN_INDEX = 1;
    public static final int TIEMPO_INDEX = 2;

    public ActividadesArticulosTableModel(String[] columnNames, List datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
       ActividadesArticulosT record = (ActividadesArticulosT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case ACTIVIDAD_INDEX:
                return record.getActividades().getDescripcion();
            case ORDEN_INDEX:
                return record.getOrden();
            case TIEMPO_INDEX:
                return record.getTiempo();
            default:
                return new Object();
        }
    }
}
