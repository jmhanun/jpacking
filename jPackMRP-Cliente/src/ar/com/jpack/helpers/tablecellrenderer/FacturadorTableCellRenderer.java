/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablecellrenderer;

import java.awt.Component;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jmhanun
 */
public class FacturadorTableCellRenderer implements TableCellRenderer {

    public static final int ID_INDEX = 0;
    public static final int NUMERO_INDEX = 1;
    public static final int FECHA_INDEX = 2;
    public static final int IMPORTE_INDEX = 3;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(JTextField.RIGHT);

        NumberFormat nroFactura = new DecimalFormat("00000000");
        NumberFormat importe = new DecimalFormat("#,##0.00");
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");


        if (isSelected) {
            lbl.setOpaque(true);
        } else {
            lbl.setOpaque(false);
        }
        switch (column) {
            case ID_INDEX:
                lbl.setText(value.toString());
                break;
            case NUMERO_INDEX:
                lbl.setText(nroFactura.format(value));
                break;
            case FECHA_INDEX:
                lbl.setText(fecha.format(value));
                break;
            case IMPORTE_INDEX:
                lbl.setText(importe.format(value));
                break;
            default:
                lbl.setText(value.toString());
                break;
        }
        return lbl;
    }
}


