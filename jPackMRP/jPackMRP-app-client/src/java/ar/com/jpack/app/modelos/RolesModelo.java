/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.app.modelos;

//import ar.com.jpack.negocio.RolesSessionRemote;
import ar.com.jpack.transferencia.RolesT;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Pablo
 */
public class RolesModelo {

    private TableModel rolesTableModel; //Modelo de datos de la tabla de roles
//    private RolesSessionRemote rolesSession; //SessionBean de roles
    private List<RolesT> roles; //Lista de roles activos
    private RolesT rol; //Rol seleccionado

    /** Constructor de RolesModelo */
    public RolesModelo() {
//        rolesSession = this.lookupRolesSessionBean();
        this.actualizarRoles();

        rolesTableModel = new AbstractTableModel() {

            @Override
            public String getColumnName(int col) {
                if (col == 0) {
                    return "Id";
                } else if (col == 1) {
                    return "Nombre";
                } else if (col == 2) {
                    return "Descripcion";
                } else {
                    return null;
                }
            //return columnNames[col].toString();
            }

            public int getRowCount() {
                return roles.size();
            //return rowData.length;
            }

            public int getColumnCount() {
                return 3;
            //return columnNames.length;
            }

            public Object getValueAt(int row, int col) {
                if (col == 0) {
                    return roles.get(row).getIdRol();
                } else if (col == 1) {
                    return roles.get(row).getRol();
                } else if (col == 2) {
                    return roles.get(row).getDescripcion();
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

    /** Agrega o edita el rol seleccionado en la base de datos */
    public void actualizarRol() {
        if (rol.getIdRol() == null) {
//            rolesSession.agregarRol(rol); //Nuevo rol
//        } else {
//            rolesSession.editarRol(rol); //Editar rol
        }
        actualizarRoles();
    }

    /** Refresca la lista de roles activos contra la bbdd */
    private void actualizarRoles() {
//        roles = rolesSession.obtenerRoles();
    }

    /** Obtiene el EJB */
//    private RolesSessionRemote lookupRolesSessionBean() {
//        try {
//            Context c = new InitialContext();
//            return (RolesSessionRemote) c.lookup("java:comp/env/RolesSessionBean");
//        } catch (NamingException ne) {
//            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
//            throw new RuntimeException(ne);
//        }
//    }

    /** Devuelve el modelo de datos de la tabla de roles */
    public TableModel getRolesTableModel() {
        return rolesTableModel;
    }

    public RolesT getRol() {
        return rol;
    }

    public void setRol(RolesT rol) {
        this.rol = rol;
    }

    /** Crea un rol en blanco y lo deja como seleccionado para un nuevo rol */
    public void nuevoRol() {
        this.setRol(new RolesT());
    }

    /** Crea un rol a partir de la seleccion de una fila del modelo de datos de la tabla de roles */
    public void editarRol(int fila) {
        this.setRol(roles.get(fila));
    }
}
