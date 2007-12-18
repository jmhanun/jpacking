/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.app.modelos;

import ar.com.jpack.negocio.ArticulosSessionRemote;
import ar.com.jpack.transferencia.ArticulosT;
import ar.com.jpack.transferencia.ComponentesT;
import java.util.List;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Pablo
 */
public class ArticulosModelo {

    private TableModel articulosTableModel;
    private ArticulosSessionRemote articulosSession;
    private List<ArticulosT> articulos;
    private ArticulosT articulo;
    private TableModel componentesTableModel;
    private List<ComponentesT> componentes;
    private ComboBoxModel componentesComboBoxModel;

    public ArticulosModelo() {
        articulosSession = this.lookupArticulosSessionBean();
        this.actualizarArticulos();

        articulosTableModel = new AbstractTableModel() {

            @Override
            public String getColumnName(int col) {
                if (col == 0) {
                    return "Id";
                } else if (col == 1) {
                    return "Codigo";
                } else if (col == 2) {
                    return "Descripcion";
                } else if (col == 3) {
                    return "Estado";
                } else if (col == 4) {
                    return "Stock";
                } else if (col == 5) {
                    return "Stock minimo";
                } else if (col == 6) {
                    return "Leadtime";
                } else {
                    return null;
                }
            //return columnNames[col].toString();
            }

            public int getRowCount() {
                return articulos.size();
            //return rowData.length;
            }

            public int getColumnCount() {
                return 7;
            //return columnNames.length;
            }

            public Object getValueAt(int row, int col) {
                if (col == 0) {
                    return articulos.get(row).getIdArticulo();
                } else if (col == 1) {
                    return articulos.get(row).getCodigo();
                } else if (col == 2) {
                    return articulos.get(row).getDescripcion();
                } else if (col == 3) {
                    return articulos.get(row).getEstado();
                } else if (col == 4) {
                    return articulos.get(row).getStock();
                } else if (col == 5) {
                    return articulos.get(row).getStockMinimo();
                } else if (col == 6) {
                    return articulos.get(row).getLeadTime();
                } else {
                    return null;
                }
            //return rowData[row][col];
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }

            @Override
            public void setValueAt(Object value, int row, int col) {
            //rowData[row][col] = value;
            //fireTableCellUpdated(row, col);
            }
        };
    }

    public void actualizarArticulo() {
        if (articulo.getIdArticulo() == null) {
            articulosSession.agregarArticulo(articulo);
        } else {
            articulosSession.editarArticulo(articulo);
        }
        actualizarArticulos();
    }

    private void actualizarArticulos() {
        articulos = articulosSession.obtenerArticulos();
    }

    private ArticulosSessionRemote lookupArticulosSessionBean() {
        try {
            Context c = new InitialContext();
            return (ArticulosSessionRemote) c.lookup("java:comp/env/ArticulosSessionBean");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public TableModel getArticulosTableModel() {
        return articulosTableModel;
    }

    public TableModel getComponentesTableModel() {
        return componentesTableModel;
    }

    public ArticulosT getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticulosT articulo) {
        this.articulo = articulo;
    }

    public void nuevoArticulo() {
        this.setArticulo(new ArticulosT());
        this.setComponentes((List<ComponentesT>) this.getArticulo().getColeccionComponentesT());
        this.setComponentesTableModel();
        this.setComponentesComboBoxModel();
    }

    public void editarArticulo(int fila) {
        this.setArticulo(articulos.get(fila));
        this.setComponentes((List<ComponentesT>) this.getArticulo().getColeccionComponentesT());
        this.setComponentesTableModel();
        this.setComponentesComboBoxModel();
    }

    public void setComponentes(List<ComponentesT> componentes) {
        this.componentes = componentes;
    }

    public void setComponentesTableModel() {
        componentesTableModel = new AbstractTableModel() {

            @Override
            public String getColumnName(int col) {
                if (col == 0) {
                    return "Orden";
                } else if (col == 1) {
                    return "Codigo";
                } else if (col == 2) {
                    return "Descripcion";
                } else if (col == 3) {
                    return "Cantidad";
                } else {
                    return null;
                }
            //return columnNames[col].toString();
            }

            public int getRowCount() {
                return componentes.size();
            //return rowData.length;
            }

            public int getColumnCount() {
                return 4;
            //return columnNames.length;
            }

            public Object getValueAt(int row, int col) {
                if (col == 0) {
                    return componentes.get(row).getOrden();
                } else if (col == 1) {
                    return componentes.get(row).getComponentes().getCodigo();
                } else if (col == 2) {
                    return componentes.get(row).getComponentes().getDescripcion();
                } else if (col == 3) {
                    return componentes.get(row).getCantidad();
                } else {
                    return null;
                }
            //return rowData[row][col];
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }

            @Override
            public void setValueAt(Object value, int row, int col) {
            //rowData[row][col] = value;
            //fireTableCellUpdated(row, col);
            }
            };
    }

    public ComboBoxModel getComponentesComboBoxModel() {
        return componentesComboBoxModel;
    }

    public void setComponentesComboBoxModel() {
        this.componentesComboBoxModel = new DefaultComboBoxModel(new Vector<ArticulosT>(articulos));

    }
}
