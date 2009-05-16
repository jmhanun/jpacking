/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.SetupT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class SetupTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;
    public static final int VALOR_INDEX = 2;

    public SetupTableModel(String[] columnNames, List<SetupT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        SetupT record = (SetupT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdSetup();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            case VALOR_INDEX:
                return record.getValor();
            default:
                return new Object();
        }
    }
}
