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
    public static final int PRIORIDAD_INDEX = 3;
    public static final int ESTADO_INDEX = 4;
    public static final int CANTIDAD_INDEX = 5;
    public static final int ARTICULO_INDEX = 6;
    public static final int FECHAINICIOEST_INDEX = 7;
    public static final int FECHAFINEST_INDEX = 8;
    public static final int FECHAINICIO_INDEX = 9;
    public static final int FECHAFIN_INDEX = 10;
    public static final int PROGRESO_INDEX = 11;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JProgressBar b = new JProgressBar(0, 100);
        b.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));


        switch (column) {
            case PROGRESO_INDEX:
                Double d = (Double) value;
                Integer i = 0;
                if (d < 0) {
                    i = 0;
                } else if (d > 100) {
                    i = 100;
                } else {
                    i = d.intValue();
                }
                b.setValue(i);
                break;
        }
        return b;
    }
}

