/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.PreciosT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class PreciosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int LISTA_INDEX = 1;
    public static final int ARTICULO_INDEX = 2;
    public static final int PRECIO_INDEX = 3;
    public static final int USUARIO_INDEX = 4;
    public static final int FECHAMODIF_INDEX = 5;


    public PreciosTableModel(String[] columnNames, List<PreciosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        PreciosT record = (PreciosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdPrecio();
            case LISTA_INDEX:
                if (record.getIdArticulo() == null) {
                    return "";
                } else {
                    return record.getIdLista().getIdLista();
                }
            case ARTICULO_INDEX:
                if (record.getIdArticulo() == null) {
                    return "";
                } else {
                    return record.getIdArticulo().getCodigo();
                }
            case PRECIO_INDEX:
                return record.getPrecio();
            case USUARIO_INDEX:
                if (record.getIdUsuario() == null) {
                    return "";
                } else {
                    return record.getIdUsuario().getUsuario();
                }
            case FECHAMODIF_INDEX:
                return record.getFechaModificacion();
            default:
                return new Object();
        }
    }
}
