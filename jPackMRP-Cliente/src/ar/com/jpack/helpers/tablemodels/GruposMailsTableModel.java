/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.GruposMailsT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class GruposMailsTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int GRUPO_INDEX = 1;

    public GruposMailsTableModel(String[] columnNames, List<GruposMailsT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        GruposMailsT record = (GruposMailsT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdGrupoMail();
            case GRUPO_INDEX:
                return record.getGrupoMail();
            default:
                return new Object();
        }
    }
}
