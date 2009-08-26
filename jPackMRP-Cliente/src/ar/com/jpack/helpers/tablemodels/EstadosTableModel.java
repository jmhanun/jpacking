/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.EstadosT;
import java.util.List;

public class EstadosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int ABREVIATURA_INDEX = 1;
    public static final int DESCRIPCION_INDEX = 2;

    public EstadosTableModel(String[] columnNames, List<EstadosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        EstadosT record = (EstadosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdEstado();
            case ABREVIATURA_INDEX:
                return record.getDescripcion();
            case DESCRIPCION_INDEX:
                return record.getIdDominio().getDescripcion();
            default:
                return new Object();
        }
    }
}

