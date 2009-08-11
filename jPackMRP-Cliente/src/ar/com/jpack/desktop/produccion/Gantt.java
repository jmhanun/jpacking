/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.desktop.produccion;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author jmhanun
 */
public class Gantt extends ApplicationFrame {

    public Gantt(final String title) {
        super(title);

        final IntervalCategoryDataset dataset = createSampleDataset();

        // create the chart...
        final JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt Chart Demo", // chart title
                "Task", // domain axis label
                "Date", // range axis label
                dataset, // data
                true, // include legend
                true, // tooltips
                true // urls
                );
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        //      plot.getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);




    }

    private IntervalCategoryDataset createSampleDataset() {

        final TaskSeries s1 = new TaskSeries("Scheduled");

        final Task t1 = new Task(
                "Write Proposal", date(1, Calendar.APRIL, 2001), date(5, Calendar.APRIL, 2001));
        t1.setPercentComplete(1.00);
        s1.add(t1);

        final Task t2 = new Task(
                "Obtain Approval", date(9, Calendar.APRIL, 2001), date(9, Calendar.APRIL, 2001));
        t2.setPercentComplete(1.00);
        s1.add(t2);

        // here is a task split into two subtasks...
        final Task t3 = new Task(
                "Requirements Analysis",
                date(10, Calendar.APRIL, 2001), date(5, Calendar.MAY, 2001));
        final Task st31 = new Task(
                "Requirements 1",
                date(10, Calendar.APRIL, 2001), date(25, Calendar.APRIL, 2001));
        st31.setPercentComplete(1.0);
        final Task st32 = new Task(
                "Requirements 2",
                date(1, Calendar.MAY, 2001), date(5, Calendar.MAY, 2001));
        st32.setPercentComplete(1.0);
        t3.addSubtask(st31);
        t3.addSubtask(st32);
        s1.add(t3);

        // and another...
        final Task t4 = new Task(
                "Design Phase",
                date(6, Calendar.MAY, 2001), date(10, Calendar.MAY, 2001));
        final Task st41 = new Task(
                "Design 1",
                date(6, Calendar.MAY, 2001), date(10, Calendar.MAY, 2001));
        st41.setPercentComplete(1.5);
        final Task st42 = new Task(
                "Design 2",
                date(15, Calendar.MAY, 2001), date(20, Calendar.MAY, 2001));
        st42.setPercentComplete(1.0);
        final Task st43 = new Task(
                "Design 3",
                date(23, Calendar.MAY, 2001), date(30, Calendar.MAY, 2001));
        st43.setPercentComplete(0.50);
        t4.addSubtask(st41);
        t4.addSubtask(st42);
        t4.addSubtask(st43);
        s1.add(t4);

        final Task t5 = new Task(
                "Design Signoff", date(2, Calendar.JUNE, 2001), date(2, Calendar.JUNE, 2001));
        s1.add(t5);

        final Task t6 = new Task(
                "Alpha Implementation", date(3, Calendar.JUNE, 2001), date(31, Calendar.JULY, 2001));
        t6.setPercentComplete(0.60);

        s1.add(t6);

        final Task t7 = new Task(
                "Design Review", date(1, Calendar.AUGUST, 2001), date(8, Calendar.AUGUST, 2001));
        t7.setPercentComplete(0.0);
        s1.add(t7);

        final Task t8 = new Task(
                "Revised Design Signoff",
                date(10, Calendar.AUGUST, 2001), date(10, Calendar.AUGUST, 2001));
        t8.setPercentComplete(0.0);
        s1.add(t8);

        final Task t9 = new Task(
                "Beta Implementation",
                date(12, Calendar.AUGUST, 2001), date(12, Calendar.SEPTEMBER, 2001));
        t9.setPercentComplete(0.0);
        s1.add(t9);

        final Task t10 = new Task(
                "Testing", date(13, Calendar.SEPTEMBER, 2001), date(31, Calendar.OCTOBER, 2001));
        t10.setPercentComplete(0.0);
        s1.add(t10);

        final Task t11 = new Task(
                "Final Implementation",
                date(1, Calendar.NOVEMBER, 2001), date(15, Calendar.NOVEMBER, 2001));
        t11.setPercentComplete(0.0);
        s1.add(t11);

        final Task t12 = new Task(
                "Signoff", date(28, Calendar.NOVEMBER, 2001), date(30, Calendar.NOVEMBER, 2001));
        t12.setPercentComplete(0.0);
        s1.add(t12);

        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);

        return collection;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }
}
