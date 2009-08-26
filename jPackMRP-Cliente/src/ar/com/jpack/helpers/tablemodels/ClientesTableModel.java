/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.ClientesT;
import java.util.List;

public class ClientesTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int NOMBRES_INDEX = 1;
    public static final int CUIT_INDEX = 2;

    public ClientesTableModel(String[] columnNames, List<ClientesT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        ClientesT record = (ClientesT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdCliente().toString();
            case NOMBRES_INDEX:
                return record.getNombres();
            case CUIT_INDEX:
                return record.getCuit();
            default:
                return new Object();
        }
    }
}
