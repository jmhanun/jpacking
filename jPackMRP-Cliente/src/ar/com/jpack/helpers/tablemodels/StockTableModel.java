/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.StockT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class StockTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int ARTICULO_INDEX = 1;
    public static final int CANTIDAD_INDEX = 2;
    public static final int USUARIO_INDEX = 3;
    public static final int FECHAMOD_INDEX = 4;


    public StockTableModel(String[] columnNames, List<StockT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        StockT record = (StockT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdStock();
            case ARTICULO_INDEX:
                if (record.getIdArticulo() == null) {
                    return "";
                } else {
                    return record.getIdArticulo().getCodigo();
                }
            case CANTIDAD_INDEX:
                return record.getCantidad();
            case USUARIO_INDEX:
                if (record.getIdUsuario() == null) {
                    return "";
                } else {
                    return record.getIdUsuario().getUsuario();
                }
            case FECHAMOD_INDEX:
                return record.getFechaUltMod();
            default:
                return new Object();
        }
    }
}
