/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.RemitosT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class FacturadorTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int NUMERO_INDEX = 1;
    public static final int FECHA_INDEX = 2;
    public static final int CLIENTE_INDEX = 3;
    public static final int IMPORTE_INDEX = 4;

    public FacturadorTableModel(String[] columnNames, List<RemitosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        RemitosT record = (RemitosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdRemito();
            case NUMERO_INDEX:
                return record.getNroRemito();
            case FECHA_INDEX:
                return record.getFecha();
            case CLIENTE_INDEX:
                if (record.getIdCliente() != null) {
                    if (record.getIdCliente().getNombres() != null) {
                        return record.getIdCliente().getNombres();
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
//            case UNIDADMEDIDA_INDEX:
//                if (record.getIdUnidMedida() != null) {
//                    if (record.getIdUnidMedida().getAbreviatura() != null) {
//                        return record.getIdUnidMedida().getAbreviatura();
//                    } else {
//                        return "";
//                    }
//                } else {
//                    return "";
//                }
            case IMPORTE_INDEX:
                return record.getImporte();
            default:
                return new Object();
        }
    }
}
