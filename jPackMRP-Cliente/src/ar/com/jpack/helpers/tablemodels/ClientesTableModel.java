/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.listas.ClientesListaT;
import java.util.List;

public class ClientesTableModel extends CustomTableModel {

    public static final int ABREVIATURA_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;
    public static final int ESTADO_INDEX = 2;

    public ClientesTableModel(String[] columnNames, List<ClientesListaT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        ClientesListaT record = (ClientesListaT) dataVector.get(row);
        switch (column) {
            case ABREVIATURA_INDEX:
                return record.getNombres();
            case DESCRIPCION_INDEX:
                return record.getCuit();
            case ESTADO_INDEX:
                return record.getIdCliente().toString();
            default:
                return new Object();
        }
    }
}
