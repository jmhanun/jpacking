/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.ProvinciasT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class ProvinciasTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int PROVINCIA_INDEX = 1;
    public static final int LETRA_INDEX = 2;

    public ProvinciasTableModel(String[] columnNames, List datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.isEmpty()) {
            return new Object();
        }

        ProvinciasT record = (ProvinciasT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case ID_INDEX:
                return record.getIdProvincia();
            case PROVINCIA_INDEX :
                return record.getProvincia();
            case LETRA_INDEX :
                return record.getLetra();
            default:
                return new Object();
        }
    }
}
