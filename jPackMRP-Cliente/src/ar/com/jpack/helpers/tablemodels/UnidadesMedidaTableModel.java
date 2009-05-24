/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.UnidadesMedidaT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class UnidadesMedidaTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;
    public static final int ABREVIATURA_INDEX = 2;

    public UnidadesMedidaTableModel(String[] columnNames, List<UnidadesMedidaT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        UnidadesMedidaT record = (UnidadesMedidaT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdUnidMedida();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            case ABREVIATURA_INDEX:
                return record.getAbreviatura();
            default:
                return new Object();
        }
    }
}
