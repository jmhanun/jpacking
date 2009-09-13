/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.DetalleOrdenesComprasT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class DetalleOrdenCompraTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int CODIGO_INDEX = 1;
    public static final int ARTICULO_INDEX = 2;
    public static final int CANTIDAD_INDEX = 3;
    public static final int MEDIDA_INDEX = 4;

    public DetalleOrdenCompraTableModel(String[] columnNames, List datos) {
        super(columnNames, datos);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        DetalleOrdenesComprasT record = (DetalleOrdenesComprasT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case 3:
                record.setCantidad(((Number) aValue).intValue());
                break;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        switch (column) {
            case 3:
                return true;
            default:
                return false;
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        DetalleOrdenesComprasT record = (DetalleOrdenesComprasT) dataVector.get(rowIndex);
        switch (columnIndex) {
            case ID_INDEX:
                if (record.getDetalleordenescomprasPK() != null) {
                    return String.valueOf(record.getDetalleordenescomprasPK().getIdDetOrdenCompra());
                } else {
                    return "";
                }
            case CODIGO_INDEX:
                if (record.getIdArticulo() != null) {
                    if (record.getIdArticulo().getCodigo() != null) {
                        return record.getIdArticulo().getCodigo();
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            case ARTICULO_INDEX:
                if (record.getIdArticulo() != null) {
                    if (record.getIdArticulo().getDescripcion() != null) {
                        return record.getIdArticulo().getDescripcion();
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            case CANTIDAD_INDEX:
                return record.getCantidad();
            case MEDIDA_INDEX:
                if (record.getIdUnidMedida() != null) {
                    if (record.getIdUnidMedida().getAbreviatura() != null) {
                        return record.getIdUnidMedida().getAbreviatura();
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            default:
                return new Object();
        }
    }
}
