/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.ActividadesArticulosT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class ActividadesArticulosMaestroTableModel extends CustomTableModel {

    public static final int ACTIVIDAD_INDEX = 0;
    public static final int ORDEN_INDEX = 1;
    public static final int TIEMPOUNITARIO_INDEX = 2;
    public static final int TIEMPOTOTAL_INDEX = 3;
    public Integer cantidad;

    public ActividadesArticulosMaestroTableModel(String[] columnNames, List datos, Integer cantidad) {
        super(columnNames, datos);
        this.cantidad = cantidad;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        ActividadesArticulosT record = (ActividadesArticulosT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case ACTIVIDAD_INDEX:
                return record.getActividades().getDescripcion();
            case ORDEN_INDEX:
                return record.getOrden();
            case TIEMPOUNITARIO_INDEX:
                return record.getTiempo();
            case TIEMPOTOTAL_INDEX:
                return record.getTiempo() * cantidad;
            default:
                return new Object();
        }
    }
}
