/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.DetMovimientosStockT;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class DetalleMovimientoStockTableModel extends CustomTableModel {

    public static final int FECHA_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;
    public static final int CANTIDAD_INDEX = 2;

    public DetalleMovimientoStockTableModel(String[] columnNames, List datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        DateFormat fechaFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        DetMovimientosStockT record = (DetMovimientosStockT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case FECHA_INDEX:
                if (record.getFechaMovimiento() == null) {
                    return "";
                } else {
                    return fechaFormatter.format(record.getFechaMovimiento());
                }
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            case CANTIDAD_INDEX:
                return record.getCantidad();
            default:
                return new Object();
        }
    }
}
