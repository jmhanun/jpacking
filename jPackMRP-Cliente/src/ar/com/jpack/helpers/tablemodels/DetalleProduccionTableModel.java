/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.DetalleProduccionT;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class DetalleProduccionTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int NUMEROORDEN_INDEX = 1;
    public static final int MAQUINA_INDEX = 2;
    public static final int ESTADO_INDEX = 3;
    public static final int CANTIDAD_INDEX = 4;
    public static final int ARTICULO_INDEX = 5;
    public static final int FECHAINICIOEST_INDEX = 6;
    public static final int FECHAFINEST_INDEX = 7;
    public static final int FECHAINICIO_INDEX = 8;
    public static final int FECHAFIN_INDEX = 9;
    public static final int PROGRESO_INDEX = 10;

    public DetalleProduccionTableModel(String[] columnNames, List<DetalleProduccionT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        DateFormat fechaFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

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
            case ESTADO_INDEX:
                if (record.getIdEstado() == null) {
                    return "";
                } else {
                    return record.getIdEstado().getDescripcion();
                }
            case ARTICULO_INDEX:
                if (record.getDetordenesproduccion() == null) {
                    return "";
                } else {
                    if (record.getDetordenesproduccion().getIdArticulo() == null) {
                        return "";
                    } else {
                        return record.getDetordenesproduccion().getIdArticulo().getDescripcion();
                    }
                }
            case CANTIDAD_INDEX:
                if (record.getDetordenesproduccion() == null) {
                    return 0;
                } else {
                    return record.getDetordenesproduccion().getCantidad();
                }
            case FECHAINICIOEST_INDEX:
                if (record.getFechaInicioEstimada() == null) {
                    return "";
                } else {
                    return fechaFormatter.format(record.getFechaInicioEstimada());
                }
            case FECHAFINEST_INDEX:
                if (record.getFechaFinEstimada() == null) {
                    return "";
                } else {
                    return fechaFormatter.format(record.getFechaFinEstimada());
                }
            case FECHAINICIO_INDEX:
                if (record.getFechaInicioProceso() == null) {
                    return "";
                } else {
                    return fechaFormatter.format(record.getFechaInicioProceso());
                }

            case FECHAFIN_INDEX:
                if (record.getFechaFinProceso() == null) {
                    return "";
                } else {
                    return fechaFormatter.format(record.getFechaFinProceso());
                }
            case PROGRESO_INDEX:
//                return "NO";
                return DesktopApp.getApplication().getAvanceProduccion(record);
            default:
                return new Object();
        }
    }
}
