/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers.tablemodels;

import ar.com.jpack.helpers.CustomTableModel;
import ar.com.jpack.transferencia.TiposIvaT;
import ar.com.jpack.transferencia.listas.TiposIvaListaT;
import java.util.List;

public class TiposIvaTableModel extends CustomTableModel {

    public static final int ABREVIATURA_INDEX = 0;
    public static final int DESCRIPCION_INDEX = 1;
    public static final int ESTADO_INDEX = 2;

    public TiposIvaTableModel(String[] columnNames, List<TiposIvaListaT> datos) {
        super(columnNames, datos);
    }

    public Object getValueAt(int row, int column) {
        TiposIvaListaT record = (TiposIvaListaT) dataVector.get(row);
        switch (column) {
            case ABREVIATURA_INDEX:
                return record.getAbreviatura();
            case DESCRIPCION_INDEX:
                return record.getDescripcion();
            case ESTADO_INDEX:
                return record.getEstado();
            default:
                return new Object();
        }
    }
    
    public void addRow(TiposIvaT nuevoRegistro){
        TiposIvaListaT nuevaFila = convertirALista(nuevoRegistro);
        super.addRow(nuevaFila);
    }

    private TiposIvaListaT convertirALista(TiposIvaT nuevoRegistro) {
        TiposIvaListaT nuevaFila = new TiposIvaListaT();
        nuevaFila.setAbreviatura(nuevoRegistro.getAbreviatura());
        nuevaFila.setDescripcion(nuevoRegistro.getDescripcion());
        nuevaFila.setEstado(nuevoRegistro.getIdEstado().getDescripcion());
        nuevaFila.setIdTipoIVA(nuevoRegistro.getIdTipoIVA());
        return nuevaFila;
    }
}
