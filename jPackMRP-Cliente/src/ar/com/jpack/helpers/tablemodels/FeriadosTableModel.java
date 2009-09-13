/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.FeriadosT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class FeriadosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int FECHA_INDEX = 1;
    public static final int MOTIVO_INDEX = 2;

    public FeriadosTableModel(String[] columnNames, List<FeriadosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        FeriadosT record = (FeriadosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdFeriado();
            case FECHA_INDEX:
                return record.getFecha();
            case MOTIVO_INDEX:
                return record.getMotivo();
            default:
                return new Object();
        }
    }
}
