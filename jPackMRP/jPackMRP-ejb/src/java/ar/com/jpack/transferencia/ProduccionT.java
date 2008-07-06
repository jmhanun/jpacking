/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.transferencia;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jmhanun
 */
public class ProduccionT implements Serializable {

    protected ProduccionPKT produccionPK;
    private int unidades;
    private ArticulosT idArticulo;
    private EmpleadosT empleados;
    private MaquinasT idMaquina;

    public ProduccionT() {
    }

    public ProduccionT(ProduccionPKT produccionPK, int unidades, ArticulosT idArticulo, EmpleadosT empleados, MaquinasT idMaquina) {
        this.produccionPK = produccionPK;
        this.unidades = unidades;
        this.idArticulo = idArticulo;
        this.empleados = empleados;
        this.idMaquina = idMaquina;
    }

    public ProduccionT(int idEmpleado, Date fecha, int unidades, ArticulosT idArticulo, EmpleadosT empleados, MaquinasT idMaquina) {
        this.produccionPK = new ProduccionPKT(idEmpleado, fecha);
        this.unidades = unidades;
        this.idArticulo = idArticulo;
        this.empleados = empleados;
        this.idMaquina = idMaquina;
    }

    public EmpleadosT getEmpleados() {
        return empleados;
    }

    public void setEmpleados(EmpleadosT empleados) {
        this.empleados = empleados;
    }

    public ArticulosT getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(ArticulosT idArticulo) {
        this.idArticulo = idArticulo;
    }

    public MaquinasT getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(MaquinasT idMaquina) {
        this.idMaquina = idMaquina;
    }

    public ProduccionPKT getProduccionPK() {
        return produccionPK;
    }

    public void setProduccionPK(ProduccionPKT produccionPK) {
        this.produccionPK = produccionPK;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
}
