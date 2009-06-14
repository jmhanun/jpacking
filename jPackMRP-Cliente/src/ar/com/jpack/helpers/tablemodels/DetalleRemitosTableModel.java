/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.DetalleRemitosT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class DetalleRemitosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int CODIGO_INDEX = 1;
    public static final int ARTICULO_INDEX = 2;
    public static final int MEDIDA_INDEX = 3;
    public static final int PRECIO_INDEX = 4;
    public static final int IMPORTE_INDEX = 5;

    public DetalleRemitosTableModel(String[] columnNames, List<DetalleRemitosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        DetalleRemitosT record = (DetalleRemitosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                if (record.getDetalleremitosPK() != null) {
                    return String.valueOf(record.getDetalleremitosPK().getIdDetalleRemito());
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
            case PRECIO_INDEX:
                return String.valueOf(record.getPrecioUnitario());
            case IMPORTE_INDEX:
                return String.valueOf(record.getImporte());
            default:
                return new Object();
        }
    }
}
