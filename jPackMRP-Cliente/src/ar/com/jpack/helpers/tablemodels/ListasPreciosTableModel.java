/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.ListasPreciosT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class ListasPreciosTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int FECHADESDE_INDEX = 1;
    public static final int FECHAHASTA_INDEX = 2;
    public static final int ESTADO_INDEX = 3;
    public static final int USUARIO_INDEX = 4;
    public static final int FECHAMODIF_INDEX = 5;


    public ListasPreciosTableModel(String[] columnNames, List<ListasPreciosT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        ListasPreciosT record = (ListasPreciosT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdLista();
            case FECHADESDE_INDEX:
                return record.getFechaDesde();
            case FECHAHASTA_INDEX:
                if (record.getFechaHasta()== null) {
                    return "";
                } else {
                    return record.getFechaHasta();
                }
            case ESTADO_INDEX:
                if (record.getIdEstado() == null) {
                    return "";
                } else {
                    return record.getIdEstado().getDescripcion();
                }
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
