/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablecellrenderer;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jmhanun
 */
public class DetalleProduccionTableCellRenderer implements TableCellRenderer {

    public static final int ID_INDEX = 0;
    public static final int NUMEROORDEN_INDEX = 1;
    public static final int MAQUINA_INDEX = 2;
    public static final int ESTADO_INDEX = 3;
    public static final int CANTIDAD_INDEX = 4;
    public static final int ARTICULO_INDEX = 5;
    public static final int FECHAINICIOEST_INDEX = 6;
    public static final int FECHAFINEST_INDEX = 7;
    public static final int FECHAINICIO_INDEX = 8;
    public static final int FECHAFIN_INDEX = 9;
    public static final int PROGRESO_INDEX = 10;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JProgressBar b = new JProgressBar(0, 100);
        b.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(JLabel.RIGHT);

        if (isSelected) {
            lbl.setOpaque(true);
        } else {
            lbl.setOpaque(false);
        }

        if (value instanceof String) {
            lbl.setHorizontalAlignment(JLabel.LEFT);
        }
        switch (column) {
            case ID_INDEX:
            case NUMEROORDEN_INDEX:
            case MAQUINA_INDEX:
            case ESTADO_INDEX:
            case CANTIDAD_INDEX:
            case ARTICULO_INDEX:
            case FECHAINICIOEST_INDEX:
            case FECHAFINEST_INDEX:
            case FECHAINICIO_INDEX:
            case FECHAFIN_INDEX:
                lbl.setText(value.toString());
                lbl.setToolTipText(value.toString());
                return lbl;
            case PROGRESO_INDEX:
                Double d;
                Integer i;
                if (value != null) {
                    d = (Double) value;
                    i = 0;
                    if (d < 0) {
                        i = 0;
                    } else if (d > 100) {
                        i = 100;
                    } else {
                        i = d.intValue();
                    }
                } else {
                    i = 0;
                }
                b.setValue(i);
                b.setToolTipText(i.toString() + " %");
                return b;
            default:
                return null;
        }
    }
}