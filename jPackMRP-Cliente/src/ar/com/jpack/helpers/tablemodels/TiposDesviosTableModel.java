/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.TiposDesviosT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class TiposDesviosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int MOTIVO_INDEX = 1;

    public TiposDesviosTableModel(String[] columnNames, List<TiposDesviosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        TiposDesviosT record = (TiposDesviosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdTipoDesvio();
            case MOTIVO_INDEX:
                return record.getMotivo();
            default:
                return new Object();
        }
    }
}
