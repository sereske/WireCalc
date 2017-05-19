package ru.sereske.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.sereske.Computations;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.Map;

/**
 * Created by stepanovsg on 18.05.2017.
 */
public class GraphPanel extends JPanel {

    private Computations computations;
    private Map<Double, Double> dots;

    public GraphPanel() {
        String title = "График";
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);
    }

    private void refreshChart() {
        removeAll();
        revalidate();
        XYSeries series = new XYSeries("Провод");
        dots = computations.getDots();
        for (Map.Entry<Double, Double> dot : dots.entrySet()) {
            series.add(dot.getKey(), dot.getValue());
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Провод", "x, м", "y, м", xyDataset, PlotOrientation.VERTICAL,
                true, true, true);
        add(new ChartPanel(chart));
    }

    public void setComputations(Computations computations) {
        this.computations = computations;
    }

    public void update() {
        refreshChart();
    }
}
