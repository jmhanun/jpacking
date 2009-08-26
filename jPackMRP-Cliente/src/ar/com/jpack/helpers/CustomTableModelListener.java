/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers;

import ar.com.jpack.desktop.ventas.RegistrarRemito;
import ar.com.jpack.helpers.tablemodels.DetalleRemitosTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author jmhanun
 */
public class CustomTableModelListener implements TableModelListener {

    CustomInternalFrame iFrame;

    public CustomTableModelListener() {
        super();
    }

    public CustomTableModelListener(CustomInternalFrame iFrame) {
        super();
        this.iFrame = iFrame;
    }

    public void tableChanged(TableModelEvent evt) {
//        System.err.println("Tipo evento: " + evt.getType());
        if (evt.getType() == TableModelEvent.INSERT) {
//            System.out.println("Paso por aqui...");
//            int column = evt.getColumn();
//            int row = evt.getFirstRow();
//            System.out.println("row: " + row + " column: " + column);
        }
        if (evt.getType() == TableModelEvent.UPDATE) {
            int column = evt.getColumn();
//            int row = evt.getFirstRow();
            if (iFrame != null) {
                //Pone el importe total en el txtTotal del InternalFrameRegistrarRemitos
                if (iFrame.getClass().getCanonicalName().equals("ar.com.jpack.desktop.ventas.RegistrarRemito")) {
                    RegistrarRemito iFrameCast = (RegistrarRemito) iFrame;
                    Double total = new Double(0.0);
                    DetalleRemitosTableModel tableModel = (DetalleRemitosTableModel) evt.getSource();
                    int rows = tableModel.getRowCount();
                    for (int i = 0; i < rows; i++) {
                        total += (Double) tableModel.getValueAt(i, column);
                    }
                    iFrameCast.setTotal(total);
                }
            }
        }
    }
}
