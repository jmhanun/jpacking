/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.MailsT;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class MailsTableModel extends CustomTableModel {

    public static final int ID_INDEX = 0;
    public static final int GRUPO_INDEX = 1;
    public static final int USUARIO_INDEX = 2;

    public MailsTableModel(String[] columnNames, List<MailsT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        if (dataVector.isEmpty()) {
            return new Object();
        }
        MailsT record = (MailsT) dataVector.get(row);
        switch (column) {
            case ID_INDEX:
                return record.getIdMail();
            case GRUPO_INDEX:
                if (record.getIdGrupoMail() == null) {
                    return "";
                } else {
                    return record.getIdGrupoMail().getGrupoMail();
                }
            case USUARIO_INDEX:
                if (record.getIdUsuario() == null) {
                    return "";
                } else {
                    return record.getIdUsuario().getUsuario();
                }
            default:
                return new Object();
        }
    }
}
