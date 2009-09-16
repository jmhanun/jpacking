/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.ApplicationFrameJM;
import ar.com.jpack.transferencia.DetalleProduccionT;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

/**
 *
 * @author jmhanun
 */
public class GanttReal extends ApplicationFrameJM {

    ArrayList<DetalleProduccionT> listaDetalleProduccion;

    public GanttReal(String title, List<DetalleProduccionT> listaProduccion) {
        super(title);
        listaDetalleProduccion = (ArrayList<DetalleProduccionT>) listaProduccion;
        DateFormat fechaFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        final IntervalCategoryDataset dataset = createSampleDataset(listaProduccion);

        // create the chart...
        final JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt de la orden de produccion #" + listaProduccion.get(0).getDetordenesproduccion().getOrdenesproduccion().getNroOrdenProduccion(), // chart title
                "Maquinas", // domain axis label
                "Fecha", // range axis label
                dataset, // data
                false, // include legend
                false, // tooltips
                false // urls
                );
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        //      plot.getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.getHSBColor(0.125f, 1.0f, 0.8f));

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private IntervalCategoryDataset createSampleDataset(List<DetalleProduccionT> listaProduccion) {

        final TaskSeries s1 = new TaskSeries("Estimado");
        final TaskSeries s2 = new TaskSeries("Real");

        ArrayList<Modulo> listaProcesos = new ArrayList<Modulo>();

        GregorianCalendar fechaInicio = new GregorianCalendar();
        GregorianCalendar fechaFin = new GregorianCalendar();
        GregorianCalendar fechaTokenInicio = new GregorianCalendar();
        GregorianCalendar fechaTokenFin = new GregorianCalendar();
        Modulo modulo;
        Long segundosAlFinTurno;
        Long duracion;
//Averigua cual es la fecha maxima del gantt para que no aparezcan tareas cortadas.
        for (DetalleProduccionT detalleProduccionT : listaProduccion) {
            modulo = new Modulo();
            modulo.setIdDetalleProduccion(detalleProduccionT.getIdDetalleProduccion());
            modulo.setDescripcion(detalleProduccionT.getIdMaquina().getDescripcion());
            fechaInicio.setTime(detalleProduccionT.getFechaInicioEstimada());
            modulo.setInicio(fechaInicio.getTime());

            duracion = (DesktopApp.getApplication().getTiempoEstimadoProduccion(modulo.getIdDetalleProduccion())).longValue();

            fechaTokenInicio.setTime(modulo.getInicio());
            fechaTokenFin.setTime(modulo.getInicio());
            fechaTokenFin.set(GregorianCalendar.HOUR_OF_DAY, 18);
            fechaTokenFin.set(GregorianCalendar.MINUTE, 0);
            fechaTokenFin.set(GregorianCalendar.SECOND, 0);
            segundosAlFinTurno = (fechaTokenFin.getTimeInMillis() - fechaTokenInicio.getTimeInMillis()) / 1000;

            //Comienza y Termina la tarea en el mismo turno
            if (segundosAlFinTurno >= duracion) {
                fechaTokenInicio.add(GregorianCalendar.SECOND, duracion.intValue());
                modulo.setFin(fechaTokenInicio.getTime());
                listaProcesos.add(modulo);
            } else {
                do {
                    modulo.setFin(fechaTokenFin.getTime());
                    listaProcesos.add(modulo);
                    duracion -= segundosAlFinTurno;
                    segundosAlFinTurno = 28800L;
                    modulo = new Modulo();
                    modulo.setIdDetalleProduccion(detalleProduccionT.getIdDetalleProduccion());
                    modulo.setDescripcion(detalleProduccionT.getIdMaquina().getDescripcion());
                    fechaTokenInicio = getSiguienteDiaHabil(fechaTokenInicio);
                    fechaTokenFin.setTime(fechaTokenInicio.getTime());
                    fechaTokenInicio.set(GregorianCalendar.HOUR_OF_DAY, 10);
                    fechaTokenInicio.set(GregorianCalendar.MINUTE, 0);
                    fechaTokenInicio.set(GregorianCalendar.SECOND, 0);
                    fechaTokenFin.set(GregorianCalendar.HOUR_OF_DAY, 18);
                    fechaTokenFin.set(GregorianCalendar.MINUTE, 0);
                    fechaTokenFin.set(GregorianCalendar.SECOND, 0);

                    modulo.setInicio(fechaTokenInicio.getTime());

                } while (segundosAlFinTurno <= duracion);
                if (duracion > 0) {
                    fechaTokenInicio.add(GregorianCalendar.SECOND, duracion.intValue());
                    modulo.setFin(fechaTokenInicio.getTime());
                    listaProcesos.add(modulo);
                }
            }
        }
        Integer ultimoID = 0;
        Task tarea = null;
        Task subTarea = null;
        for (Modulo mod : listaProcesos) {
            if (ultimoID != mod.getIdDetalleProduccion()) {
                if(tarea!=null){
                    s1.add(tarea);
                }
                DetalleProduccionT detalle = getDetalle(mod.getIdDetalleProduccion());
                tarea = new Task(mod.getDescripcion(), detalle.getFechaInicioEstimada(), detalle.getFechaFinEstimada());
                subTarea = new Task(mod.getDescripcion(), mod.getInicio(), mod.getFin());
                tarea.addSubtask(subTarea);
            } else {
                subTarea = new Task(mod.getDescripcion(), mod.getInicio(), mod.getFin());
                tarea.addSubtask(subTarea);
            }
            ultimoID = mod.getIdDetalleProduccion();
        }


        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
//        collection.add(s2);
        return collection;
    }

    private DetalleProduccionT getDetalle(Integer idDetalleProduccion) {
        DetalleProduccionT detalle = null;
        for (DetalleProduccionT detalleProduccionT : listaDetalleProduccion) {
            if (detalleProduccionT.getIdDetalleProduccion().equals(idDetalleProduccion)) {
                detalle = detalleProduccionT;
            }
        }
        return detalle;
    }

    private GregorianCalendar getSiguienteDiaHabil(GregorianCalendar fecha) {
        do {
            fecha.add(GregorianCalendar.DAY_OF_MONTH, 1);
            //es sabado? suma 2
            if (fecha.get(GregorianCalendar.DAY_OF_WEEK) == 7) {
                fecha.add(GregorianCalendar.DAY_OF_MONTH, 2);
            }
            //es domingo? suma 1
            if (fecha.get(GregorianCalendar.DAY_OF_WEEK) == 1) {
                fecha.add(GregorianCalendar.DAY_OF_MONTH, 1);
            }
        } while (DesktopApp.getApplication().getFeriado(fecha.getTime()));
        return fecha;
    }

    class Modulo {

        Integer idDetalleProduccion;
        String descripcion;
        Date inicio;
        Date fin;

        public Modulo() {
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Date getFin() {
            return fin;
        }

        public void setFin(Date fin) {
            this.fin = fin;
        }

        public Integer getIdDetalleProduccion() {
            return idDetalleProduccion;
        }

        public void setIdDetalleProduccion(Integer idDetalleProduccion) {
            this.idDetalleProduccion = idDetalleProduccion;
        }

        public Date getInicio() {
            return inicio;
        }

        public void setInicio(Date inicio) {
            this.inicio = inicio;
        }
    }
}