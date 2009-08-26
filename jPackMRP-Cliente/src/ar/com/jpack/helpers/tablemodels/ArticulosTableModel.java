/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.ArticulosT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class ArticulosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int CODIGO_INDEX = 1;
    public static final int DESCRIPCION_INDEX = 2;
    public static final int STOCKMINIMO_INDEX = 3;
    public static final int UNIDADMEDIDA_INDEX = 4;
    public static final int ESTADO_INDEX = 5;
    public static final int IMPRIMIBLE_INDEX = 6;
    public static final int ARTICULOFINAL_INDEX = 7;

    public ArticulosTableModel(String[] columnNames, List<ArticulosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        ArticulosT record = (ArticulosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdArticulo();
            case CODIGO_INDEX:
                return record.getCodigo();
            case DESCRIPCION_INDEX:
                return record.getCodigo();
            case STOCKMINIMO_INDEX:
                return String.valueOf(record.getStockMinimo());
            case UNIDADMEDIDA_INDEX:
                if (record.getIdUnidMedida() != null) {
                    if (record.getIdUnidMedida().getAbreviatura() != null) {
                        return record.getIdUnidMedida().getAbreviatura();
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            case ESTADO_INDEX:
                if (record.getIdEstado() != null) {
                    if (record.getIdEstado().getDescripcion() != null) {
                        return record.getIdEstado().getDescripcion();
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            case IMPRIMIBLE_INDEX:
                return record.getImprimible();
            case ARTICULOFINAL_INDEX:
                return record.getArticuloFinal();
            default:
                return new Object();
        }
    }
}
