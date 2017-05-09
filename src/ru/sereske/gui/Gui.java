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
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Map;

/**
 * Created by stepanovsg on 14.04.2017.
 */
public class Gui extends JFrame /*JPanel*/ implements PropertyChangeListener {

    private double diffY = 105.0;
    private double diffX = 1700.0;
    private double density = 2.5;
    private double length = diffX * 1.02;

    private double x0;
    private double y0;
    private double xCenterMass;
    private double yCenterMass;

    private Map<Double, Double> dots;

    private JLabel lblLength;
    private JLabel lblDiffY;
    private JLabel lblDiffX;
    private JLabel lblDensity;

    private static String lblLengthString = "Длина провода, м";
    private static String lblDiffYString = "Разность высот опор, м";

    private static String lblX0String = "Низшая точка провисания - ось X: ";
    private static String lblY0String = "Низшая точка провисания - ось Y: ";
    private static String lblXCenterMassString = "Координата центра масс - ось X: ";
    private static String lblYCenterMassString = "Координата центра масс - ось Y: ";

    private JFormattedTextField tfLength;
    private JFormattedTextField tfDiffY;
    private JFormattedTextField tfDiffX;
    private JFormattedTextField tfDensity;

    private JTextField tfX0;
    private JTextField tfY0;
    private JTextField tfXCenterMass;
    private JTextField tfYCenterMass;

    private JLabel lblX0;
    private JLabel lblY0;
    private JLabel lblXCenterMass;
    private JLabel lblYCenterMass;

    private JPanel graphPanel;

    private NumberFormat fmtLength;
    private NumberFormat fmtDiffY;
    private NumberFormat fmtDiffX;
    private NumberFormat fmtDensity;

    public Gui() {
        //super(new BorderLayout());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        setUpFormats();

        compute();

        graphPanel = new JPanel(new GridLayout(0, 1));

        lblX0 = new JLabel(lblX0String);
        lblY0 = new JLabel(lblY0String);
        lblXCenterMass = new JLabel(lblXCenterMassString);
        lblYCenterMass = new JLabel(lblYCenterMassString);

        tfX0 = new JTextField();
        tfX0.setEditable(false);
        tfX0.setForeground(Color.BLUE);

        tfY0 = new JTextField();
        tfY0.setEditable(false);
        tfY0.setForeground(Color.BLUE);

        tfXCenterMass = new JTextField();
        tfXCenterMass.setEditable(false);
        tfXCenterMass.setForeground(Color.BLUE);

        tfYCenterMass = new JTextField();
        tfYCenterMass.setEditable(false);
        tfYCenterMass.setForeground(Color.BLUE);

        lblLength = new JLabel(lblLengthString);
        String lblDiffXString = "Расстояние между опорами, м";
        lblDiffX = new JLabel(lblDiffXString);
        lblDiffY = new JLabel(lblDiffYString);
        String lblDensityString = "Линейная плотность провода, кг/м";
        lblDensity = new JLabel(lblDensityString);

        tfLength = new JFormattedTextField(fmtLength);
        tfLength.setEditable(false);
        tfLength.setValue(new Double(length));
        tfLength.setColumns(10);
        tfLength.addPropertyChangeListener(this);

        tfDiffX = new JFormattedTextField(fmtDiffX);
        tfDiffX.setValue(new Double(diffX));
        tfDiffX.setColumns(10);
        tfDiffX.addPropertyChangeListener(this);

        tfDiffY = new JFormattedTextField(fmtDiffY);
        tfDiffY.setValue(new Double(diffY));
        tfDiffY.setColumns(10);
        tfDiffY.addPropertyChangeListener(this);

        tfDensity = new JFormattedTextField(fmtDensity);
        tfDensity.setValue(new Double(density));
        tfDensity.setColumns(10);
        tfDensity.addPropertyChangeListener(this);

        lblLength.setLabelFor(tfLength);
        lblDiffX.setLabelFor(tfDiffX);
        lblDiffY.setLabelFor(tfDiffY);
        lblDensity.setLabelFor(tfDensity);

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        //labelPane.add(lblLength);
        labelPane.add(lblDiffX);
        labelPane.add(lblDiffY);
        labelPane.add(lblDensity);
        labelPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        //fieldPane.add(tfLength);
        fieldPane.add(tfDiffX);
        fieldPane.add(tfDiffY);
        fieldPane.add(tfDensity);
        fieldPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel resultLabelPane = new JPanel(new GridLayout(0, 1));
        resultLabelPane.add(lblX0);
        resultLabelPane.add(lblY0);
        resultLabelPane.add(lblXCenterMass);
        resultLabelPane.add(lblYCenterMass);

        JPanel resultTextFieldPane = new JPanel(new GridLayout(0, 1));
        resultTextFieldPane.add(tfX0);
        resultTextFieldPane.add(tfY0);
        resultTextFieldPane.add(tfXCenterMass);
        resultTextFieldPane.add(tfYCenterMass);

        //graphPanel = new JPanel(new GridLayout(0, 1));

        //setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //add(labelPane, BorderLayout.CENTER);
        //add(fieldPane, BorderLayout.LINE_END);
        //add(resultLabelPane, BorderLayout.SOUTH);
        //add(resultTextFieldPane, BorderLayout.AFTER_LINE_ENDS);

        /*
        XYSeries series = new XYSeries("asd");
        for (Map.Entry<Double, Double> dot : dots.entrySet()) {
            series.add(dot.getKey(), dot.getValue());
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Провод", "x", "y", xyDataset, PlotOrientation.VERTICAL,
                true, true, true);
        graphPanel.add(new ChartPanel(chart));
        */

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelPane)
                        .addComponent(resultLabelPane)
                ).addGroup(layout.createParallelGroup()
                        .addComponent(fieldPane)
                        .addComponent(resultTextFieldPane)
                ).addComponent(graphPanel)
        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPane)
                        .addComponent(resultLabelPane)
                ).addGroup(layout.createSequentialGroup()
                        .addComponent(fieldPane)
                        .addComponent(resultTextFieldPane)
                ).addComponent(graphPanel)
        );

        pack();
    }

    private void setUpFormats() {
        fmtLength = NumberFormat.getNumberInstance();
        fmtDiffY = NumberFormat.getNumberInstance();
        fmtDiffX = NumberFormat.getNumberInstance();
        fmtDensity = NumberFormat.getNumberInstance();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        if (source == tfLength) {
            length = ((Number) tfLength.getValue()).doubleValue();
        } else if (source == tfDiffX) {
            diffX = ((Number) tfDiffX.getValue()).doubleValue();
            length = diffX * 1.02;
        } else if (source == tfDiffY) {
            diffY = ((Number) tfDiffY.getValue()).doubleValue();
        } else if (source == tfDensity) {
            density = ((Number) tfDensity.getValue()).doubleValue();
        }

        compute();

        updateUi();
    }

    private void compute() {
        Computations computations = new Computations(length, diffY, diffX, density);
        x0 = computations.computeX0();
        y0 = computations.computeY0();
        xCenterMass = computations.computeXCenterMass();
        yCenterMass = computations.computeYCenterMass();
        dots = computations.getDots();
    }

    private void updateUi() {
        //lblX0.setText(lblX0String + x0);
        //lblY0.setText(lblY0String + y0);
        //lblXCenterMass.setText(lblXCenterMassString + xCenterMass);
        //lblYCenterMass.setText(lblYCenterMassString + yCenterMass);

        tfX0.setText(String.valueOf(x0));
        tfY0.setText(String.valueOf(y0));
        tfXCenterMass.setText(String.valueOf(xCenterMass));
        tfYCenterMass.setText(String.valueOf(yCenterMass));

        refreshChart();
    }

    private void refreshChart() {
        graphPanel.removeAll();
        graphPanel.revalidate();
        XYSeries series = new XYSeries("Провод");
        for (Map.Entry<Double, Double> dot : dots.entrySet()) {
            series.add(dot.getKey(), dot.getValue());
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Провод", "x, м", "y, м", xyDataset, PlotOrientation.VERTICAL,
                true, true, true);
        graphPanel.add(new ChartPanel(chart));
    }

    private static void createAndShowGui() {
        JFrame frame = new Gui();
        frame.setTitle("Расчет координат центра масс провода");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }
}
