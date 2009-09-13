/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.RemitosT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class FacturadorTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int NUMERO_INDEX = 1;
    public static final int FECHA_INDEX = 2;
    public static final int IMPORTE_INDEX = 3;

    public FacturadorTableModel(String[] columnNames, List<RemitosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        RemitosT record = (RemitosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdRemito();
            case NUMERO_INDEX:
                return record.getNroRemito();
            case FECHA_INDEX:
                return record.getFecha();
            case IMPORTE_INDEX:
                return new Double(record.getImporte());
            default:
                return new Object();
        }
    }
}
