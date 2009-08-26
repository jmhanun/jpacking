/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.TiposIvaT;
import java.util.List;

public class TiposIvaTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int ABREVIATURA_INDEX = 1;
    public static final int DESCRIPCION_INDEX = 2;

    public TiposIvaTableModel(String[] columnNames, List<TiposIvaT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        TiposIvaT record = (TiposIvaT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdTipoIVA();
            case ABREVIATURA_INDEX:
                return record.getAbreviatura();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            default:
                return new Object();
        }
    }
}
