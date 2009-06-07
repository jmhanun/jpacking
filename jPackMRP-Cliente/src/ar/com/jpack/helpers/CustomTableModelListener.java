/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author jmhanun
 */
public class CustomTableModelListener implements TableModelListener {

    public void tableChanged(TableModelEvent evt) {
        System.err.println("Tipo evento: " + evt.getType());
        if (evt.getType() == TableModelEvent.INSERT) {
            System.out.println("Paso por aqui...");
            int column = evt.getColumn();
            int row = evt.getFirstRow();
            System.out.println("row: " + row + " column: " + column);
//            table.setColumnSelectionInterval(column + 1, column + 1);
//            table.setRowSelectionInterval(row, row);
        }
    }
}
