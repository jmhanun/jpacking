/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.MaquinasT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class MaquinasTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;
    public static final int ESTADO_INDEX = 2;
    public static final int ACTIVIDAD_INDEX = 3;
    public static final int HORASMANTENIMIENTO_INDEX = 4;
    public static final int HORASUSO_INDEX = 5;


    public MaquinasTableModel(String[] columnNames, List<MaquinasT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        MaquinasT record = (MaquinasT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdMaquina();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            case ESTADO_INDEX:
                if (record.getIdEstado() == null) {
                    return "";
                } else {
                    return record.getIdEstado().getDescripcion();
                }
            case ACTIVIDAD_INDEX:
                if (record.getIdActividad() == null) {
                    return "";
                } else {
                    return record.getIdActividad().getDescripcion();
                }
            case HORASMANTENIMIENTO_INDEX:
                return record.getHorasMantenimiento();
            case HORASUSO_INDEX:
                return record.getHorasUso();
            default:
                return new Object();
        }
    }
}
