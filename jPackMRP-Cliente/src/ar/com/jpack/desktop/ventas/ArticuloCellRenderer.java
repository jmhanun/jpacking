package ar.com.jpack.desktop.ventas;

import ar.com.jpack.transferencia.ArticulosT;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Table cell renderer for currency.
 *
 */
public class ArticuloCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       //ArticulosT articulosT = (ArticulosT) value;
        value = ((ArticulosT) value).getIdArticulo();
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
