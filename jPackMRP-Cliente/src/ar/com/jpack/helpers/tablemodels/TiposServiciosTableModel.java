/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.TiposServiciosT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class TiposServiciosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;

    public TiposServiciosTableModel(String[] columnNames, List<TiposServiciosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        TiposServiciosT record = (TiposServiciosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdTipoServicio();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            default:
                return new Object();
        }
    }
}
