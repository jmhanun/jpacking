/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.RolesT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class RolesTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int ROL_INDEX = 1;
    public static final int DESCRIPCION_INDEX = 2;
    public static final int ROLPADRE_INDEX = 3;
    public static final int COMPONENTE_INDEX = 4;
    public static final int FUNCION_INDEX = 5;
    public static final int ORDEN_INDEX = 6;
    public static final int ORDENHERMANO_INDEX = 7;

    public RolesTableModel(String[] columnNames, List<RolesT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        RolesT record = (RolesT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdRol();
            case ROL_INDEX:
                return record.getRol();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            case ROLPADRE_INDEX:
                if (record.getIdRolPadre() == null) {
                    return "";
                } else {
                    return record.getIdRolPadre().getRol();
                }
            case COMPONENTE_INDEX:
                return record.getComponente();
            case FUNCION_INDEX:
                if (record.getFuncion() == null) {
                    return "";
                } else {
                    return record.getFuncion();
                }
            case ORDEN_INDEX:
                return Integer.valueOf(record.getOrden());
            case ORDENHERMANO_INDEX:
                return Integer.valueOf(record.getOrdenHermano());
            default:
                return new Object();
        }
    }
}