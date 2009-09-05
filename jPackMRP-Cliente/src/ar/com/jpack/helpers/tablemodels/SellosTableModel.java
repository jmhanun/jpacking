/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.SellosT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class SellosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;
    public static final int TAMANO_INDEX = 2;
    public static final int ARTICULO_INDEX = 3;
    public static final int ESTADO_INDEX = 4;

    public SellosTableModel(String[] columnNames, List<SellosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        SellosT record = (SellosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdSello();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            case TAMANO_INDEX:
                return record.getTamano();
            case ARTICULO_INDEX:
                if (record.getIdArticulo() == null) {
                    return "";
                } else {
                    return record.getIdArticulo().getCodigo();
                }
            case ESTADO_INDEX:
                if (record.getIdEstado() == null) {
                    return "";
                } else {
                    return record.getIdEstado().getDescripcion();
                }

            default:
                return new Object();
        }
    }
}
