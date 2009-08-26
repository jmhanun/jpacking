/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.TiposDocumentoT;
import java.util.List;

/**
 *
 * @author jmhanun
 */
public class TiposDocumentoTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;
    public static final int ABREVIATURA_INDEX = 2;

    public TiposDocumentoTableModel(String[] columnNames, List<TiposDocumentoT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        TiposDocumentoT record = (TiposDocumentoT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdTipoDocumento();
            case ABREVIATURA_INDEX:
                return record.getAbreviatura();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            default:
                return new Object();
        }
    }
}
