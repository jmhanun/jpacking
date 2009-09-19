/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.DomiciliosT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class DomiciliosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int CALLE_INDEX = 1;
    public static final int NUMERO_INDEX = 2;
    public static final int PISO_INDEX = 3;
    public static final int DEPARTAMENTO_INDEX = 4;
    public static final int TORRE_INDEX = 5;
    public static final int BARRIO_INDEX = 6;
    public static final int LOCALIDAD_INDEX = 7;
    public static final int PROVINCIA_INDEX = 8;

    public DomiciliosTableModel(String[] columnNames, List datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        DomiciliosT record = (DomiciliosT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case ID_INDEX:
                return record.getIdDomicilio();
            case CALLE_INDEX:
                return record.getCalle();
            case NUMERO_INDEX:
                return record.getNumero();
            case PISO_INDEX:
                return record.getPiso();
            case DEPARTAMENTO_INDEX:
                return record.getDepartamento();
            case TORRE_INDEX:
                return record.getTorre();
            case BARRIO_INDEX:
                return record.getBarrio();
            case LOCALIDAD_INDEX:
                if (record.getIdLocalidad() == null) {
                    return "";
                } else {
                    return record.getIdLocalidad().getLocalidad();
                }
            case PROVINCIA_INDEX:
                if (record.getIdLocalidad() == null) {
                    return "";
                } else {
                    if (record.getIdLocalidad().getIdProvincia() == null) {
                        return "";
                    } else {
                        return record.getIdLocalidad().getIdProvincia().getProvincia();
                    }
                }
            default:
                return new Object();
        }
    }
}


