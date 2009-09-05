/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.ActividadesT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class ActividadesTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;

    public ActividadesTableModel(String[] columnNames, List<ActividadesT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        ActividadesT record = (ActividadesT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdActividad();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            default:
                return new Object();
        }
    }
}
