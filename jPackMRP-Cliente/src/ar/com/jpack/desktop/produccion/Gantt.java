/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.desktop.produccion;

import ar.com.jpack.desktop.DesktopApp;
import ar.com.jpack.helpers.ApplicationFrameJM;
import ar.com.jpack.transferencia.DetalleProduccionT;
import ar.com.jpack.transferencia.MaquinasT;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
public class Gantt extends ApplicationFrameJM {

    public Gantt(String title, List<DetalleProduccionT> listaProduccion, Date d, Date h) {
        super(title);
        DateFormat fechaFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        final IntervalCategoryDataset dataset = createSampleDataset(listaProduccion, d, h);

        // create the chart...
        final JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt desde " + fechaFormatter.format(d) + " hasta " + fechaFormatter.format(h), // chart title
                "Maquinas", // domain axis label
                "Fecha", // range axis label
                dataset, // data
                false, // include legend
                false, // tooltips
                false // urls
                );
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.getHSBColor(0.125f, 1.0f, 0.8f));

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private IntervalCategoryDataset createSampleDataset(List<DetalleProduccionT> listaProduccion, Date d, Date h) {

        final TaskSeries s1 = new TaskSeries("Scheduled");

        List<MaquinasT> listaMaquinas = DesktopApp.getApplication().getMaquinasT(new HashMap());

        Date maxDate = d;
//Averigua cual es la fecha maxima del gantt para que no aparezcan tareas cortadas.
        for (DetalleProduccionT detalleProduccionT : listaProduccion) {
            if (maxDate.before(detalleProduccionT.getFechaFinEstimada())) {
                maxDate = detalleProduccionT.getFechaFinEstimada();
            }
        }

        boolean tieneTarea = false;
//Recorre todas las maquinas y verifica si hay tareas asignadas en 
//esas maquinas dentro del periodo consultado
        for (MaquinasT maquinasT : listaMaquinas) {
            Task tarea = new Task(maquinasT.getDescripcion(), d, maxDate);
            for (DetalleProduccionT detalleProduccionT : listaProduccion) {
                if (detalleProduccionT.getIdMaquina().getIdMaquina().equals(maquinasT.getIdMaquina())) {
                    tieneTarea = true;
                    Task subTarea = null;
                    GregorianCalendar fechaInicio = new GregorianCalendar();
                    GregorianCalendar fechaFin = new GregorianCalendar();
                    GregorianCalendar fechaTokenInicio = new GregorianCalendar();
                    GregorianCalendar fechaTokenFin = new GregorianCalendar();

                    fechaInicio.setTime(detalleProduccionT.getFechaInicioEstimada());
                    fechaFin.setTime(detalleProduccionT.getFechaFinEstimada());
                    //La tarea comienza y termina el mismo dia?
                    if (fechaInicio.get(GregorianCalendar.DATE) == fechaFin.get(GregorianCalendar.DATE)) {
                        subTarea = new Task(detalleProduccionT.getIdMaquina().getDescripcion(), fechaInicio.getTime(), fechaFin.getTime());
//                        subTarea.setPercentComplete(DesktopApp.getApplication().getAvanceProduccion(detalleProduccionT) / 100);
                        tarea.addSubtask(subTarea);
                    } else {
                        Boolean completo = false;

                        fechaTokenInicio.setTime(fechaInicio.getTime());
                        fechaTokenFin.setTime(fechaInicio.getTime());
                        fechaTokenFin.set(GregorianCalendar.HOUR_OF_DAY, 18);
                        fechaTokenFin.set(GregorianCalendar.MINUTE, 0);
                        fechaTokenFin.set(GregorianCalendar.SECOND, 0);
                        //Crea la tarea en el dia del comienzo hasta que termina el turno
                        subTarea = new Task(detalleProduccionT.getIdMaquina().getDescripcion(), fechaInicio.getTime(), fechaTokenFin.getTime());
//                        subTarea.setPercentComplete(getAvanceParcial(detalleProduccionT, fechaInicio, fechaTokenFin));
                        tarea.addSubtask(subTarea);

                        do {
                            //busca el siguiente dia habil
                            do {
                                fechaTokenInicio.add(GregorianCalendar.DAY_OF_MONTH, 1);
                                fechaTokenFin.add(GregorianCalendar.DAY_OF_MONTH, 1);
                                //es sabado? suma 2
                                if (fechaTokenInicio.get(GregorianCalendar.DAY_OF_WEEK) == 7) {
                                    fechaTokenInicio.add(GregorianCalendar.DAY_OF_MONTH, 2);
                                    fechaTokenFin.add(GregorianCalendar.DAY_OF_MONTH, 2);
                                }
                                //es domingo? suma 1
                                if (fechaTokenInicio.get(GregorianCalendar.DAY_OF_WEEK) == 1) {
                                    fechaTokenInicio.add(GregorianCalendar.DAY_OF_MONTH, 1);
                                    fechaTokenFin.add(GregorianCalendar.DAY_OF_MONTH, 1);
                                }
                            } while (DesktopApp.getApplication().getFeriado(fechaTokenInicio.getTime()));

                            //Crea la tarea en el siguiente dia habil
                            fechaTokenInicio.set(GregorianCalendar.HOUR_OF_DAY, 10);
                            fechaTokenInicio.set(GregorianCalendar.MINUTE, 0);
                            fechaTokenInicio.set(GregorianCalendar.SECOND, 0);
                            if (fechaTokenInicio.get(GregorianCalendar.DATE) == fechaFin.get(GregorianCalendar.DATE)) {
                                subTarea = new Task(detalleProduccionT.getIdMaquina().getDescripcion(), fechaTokenInicio.getTime(), fechaFin.getTime());
//                                subTarea.setPercentComplete(getAvanceParcial(detalleProduccionT, fechaTokenInicio, fechaFin));
                                tarea.addSubtask(subTarea);
                                completo = true;
                            } else {
                                subTarea = new Task(detalleProduccionT.getIdMaquina().getDescripcion(), fechaTokenInicio.getTime(), fechaTokenFin.getTime());
//                                subTarea.setPercentComplete(getAvanceParcial(detalleProduccionT, fechaTokenInicio, fechaTokenFin));
                                tarea.addSubtask(subTarea);
                            }
                        } while (!completo);
                    }
                }
                if (tieneTarea) {
                    tieneTarea = false;
                    s1.add(tarea);
                }
            }
        }
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }
}