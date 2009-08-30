/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.MantenimientoT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class MantenimientoTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int MAQUINA_INDEX = 1;
    public static final int TIPOSERVICIO_INDEX = 2;
    public static final int DESCRIPCION_INDEX = 3;
    public static final int FECHAINICIO_INDEX = 4;

    public MantenimientoTableModel(String[] columnNames, List<MantenimientoT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        MantenimientoT record = (MantenimientoT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdMantenimiento();
            case MAQUINA_INDEX:
                if (record.getIdMaquina() != null) {
                    return record.getIdMaquina().getDescripcion();
                } else {
                    return "";
                }
            case TIPOSERVICIO_INDEX:
                if (record.getIdTipoServicio() != null) {
                    return record.getIdTipoServicio().getDescripcion();
                } else {
                    return "";
                }
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            case FECHAINICIO_INDEX:
                return record.getFechaInicio();
            default:
                return new Object();
        }
    }
}
