/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.DetalleProduccionT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class DetalleProduccionTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int NUMEROORDEN_INDEX = 1;
    public static final int MAQUINA_INDEX = 2;
    public static final int ACTIVIDAD_INDEX = 3;
    public static final int PRIORIDAD_INDEX = 4;
    public static final int ESTADO_INDEX = 5;
    public static final int FECHAINICIOEST_INDEX = 6;
    public static final int FECHAFINEST_INDEX = 7;
    public static final int FECHAINICIO_INDEX = 8;
    public static final int FECHAFIN_INDEX = 9;

    public DetalleProduccionTableModel(String[] columnNames, List<DetalleProduccionT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        DetalleProduccionT record = (DetalleProduccionT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdDetalleProduccion();
            case NUMEROORDEN_INDEX:
                if (record.getDetordenesproduccion() == null) {
                    return "";
                } else {
                    if (record.getDetordenesproduccion().getOrdenesproduccion() == null) {
                        return "";
                    } else {
                        return record.getDetordenesproduccion().getOrdenesproduccion().getNroOrdenProduccion();
                    }
                }
            case MAQUINA_INDEX:
                if (record.getIdMaquina() == null) {
                    return "";
                } else {
                    return record.getIdMaquina().getDescripcion();
                }
            case ACTIVIDAD_INDEX:
                if (record.getIdMaquina() == null) {
                    return "";
                } else {
                    if (record.getIdMaquina().getIdActividad() == null) {
                        return "";
                    } else {
                         return record.getIdMaquina().getIdActividad().getDescripcion();
                    }
                }
            case PRIORIDAD_INDEX:
                return record.getPrioridad();
            case ESTADO_INDEX:
                if (record.getIdEstado() == null) {
                    return "";
                } else {
                    return record.getIdEstado().getDescripcion();
                }
            case FECHAINICIOEST_INDEX:
                return record.getFechaInicioEstimada();
            case FECHAFINEST_INDEX:
                return record.getFechaFinEstimada();
            case FECHAINICIO_INDEX:
                return record.getFechaInicioProceso();
            case FECHAFIN_INDEX:
                return record.getFechaFinProceso();
            default:
                return new Object();
        }
    }
}
