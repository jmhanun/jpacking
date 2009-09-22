/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.LocalidadesT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class LocalidadesTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int LOCALIDAD_INDEX = 1;
    public static final int CODIGO_INDEX = 2;
    public static final int PROVINCIA_INDEX = 3;

    public LocalidadesTableModel(String[] columnNames, List datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.isEmpty()) {
            return new Object();
        }

        LocalidadesT record = (LocalidadesT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case ID_INDEX:
                return record.getIdLocalidad();
            case LOCALIDAD_INDEX:
                return record.getLocalidad();
            case CODIGO_INDEX:
                return record.getCodigoPostal();
            case PROVINCIA_INDEX:
                if (record.getIdProvincia() != null) {
                    return record.getIdProvincia().getProvincia();
                } else {
                    return "";
                }
            default:
                return new Object();
        }   
    }
}
