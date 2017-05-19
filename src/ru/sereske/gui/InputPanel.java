package ru.sereske.gui;

import ru.sereske.Computations;
import ru.sereske.Control;
import ru.sereske.Wire;
import ru.sereske.enumeration.Area;
import ru.sereske.enumeration.Ice;
import ru.sereske.enumeration.Wind;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

/**
 * Created by stepanovsg on 16.05.2017.
 */
public class InputPanel extends JPanel implements PropertyChangeListener {

    private Control control;

    private double diffY = 104.0;
    private double diffX = 1700.0;
    private double length = diffX * 1.02;
    private double heightOpora = 140.0;

    private int voltage = 110;
    private int chainNumber = 2;

    private Wire wire = Wire.get("300/204");
    private Area area = Area.B;
    private Wind wind = Wind.Two;
    private Ice ice = Ice.One;

    private int maxTemp = 35;
    private int avgTemp = -5;
    private int minTemp = -55;
    private int iceTemp = -10;

    //private JLabel lblLength;
    private JLabel lblDiffX;
    private JLabel lblDiffY;
    private JLabel lblHeightOpora;
    private JLabel lblVoltage;
    private JLabel lblChainNumber;
    private JLabel lblWire;
    private JLabel lblArea;
    private JLabel lblWind;
    private JLabel lblIce;
    private JLabel lblMaxTemp;
    private JLabel lblAvgTemp;
    private JLabel lblMinTemp;
    private JLabel lblIceTemp;

    //private JFormattedTextField tfLength;
    private JFormattedTextField tfDiffX;
    private JFormattedTextField tfDiffY;
    private JFormattedTextField tfHeightOpora;
    private JFormattedTextField tfVoltage;
    private JFormattedTextField tfChainNumber;
    private JComboBox<Wire> cmbWire;
    private JComboBox<Area> cmbArea;
    private JComboBox<Wind> cmbWind;
    private JComboBox<Ice> cmbIce;
    private JFormattedTextField tfMaxTemp;
    private JFormattedTextField tfAvgTemp;
    private JFormattedTextField tfMinTemp;
    private JFormattedTextField tfIceTemp;

    private JButton btnCompute;

    private NumberFormat fmtLength;
    private NumberFormat fmtDiffY;
    private NumberFormat fmtDiffX;
    private NumberFormat fmtHeightOpora;
    private NumberFormat fmtVoltage;
    private NumberFormat fmtChainNumber;
    private NumberFormat fmtMaxTemp;
    private NumberFormat fmtAvgTemp;
    private NumberFormat fmtMinTemp;
    private NumberFormat fmtIceTemp;

    public InputPanel() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String title = "Входные данные";
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);

        setupGuiElements(layout);

        setUpFormats();
    }

    private void setupGuiElements(GroupLayout layout) {
        /*
        lblLength = new JLabel("Длина провода, м");
        tfLength = new JFormattedTextField(fmtLength);
        tfLength.setColumns(10);
        tfLength.setValue(length);
        */

        lblDiffX = new JLabel("Расстояние между опорами, м");
        tfDiffX = new JFormattedTextField(fmtDiffX);
        tfDiffX.setColumns(10);
        tfDiffX.setValue(diffX);
        tfDiffX.addPropertyChangeListener(this);
        lblDiffX.setLabelFor(tfDiffX);

        lblDiffY = new JLabel("Разность высот опор, м");
        tfDiffY = new JFormattedTextField(fmtDiffY);
        tfDiffY.setColumns(10);
        tfDiffY.setValue(diffY);
        tfDiffY.addPropertyChangeListener(this);
        lblDiffY.setLabelFor(tfDiffY);

        lblHeightOpora = new JLabel("Высота низкой опоры, м");
        tfHeightOpora = new JFormattedTextField(fmtHeightOpora);
        tfHeightOpora.setColumns(10);
        tfHeightOpora.setValue(heightOpora);
        tfHeightOpora.addPropertyChangeListener(this);
        lblHeightOpora.setLabelFor(tfHeightOpora);

        lblVoltage = new JLabel("Номинальное напряжение, В");
        tfVoltage = new JFormattedTextField(fmtVoltage);
        tfVoltage.setColumns(10);
        tfVoltage.setValue(voltage);
        tfVoltage.addPropertyChangeListener(this);
        lblVoltage.setLabelFor(tfVoltage);

        lblChainNumber = new JLabel("Число цепей");
        tfChainNumber = new JFormattedTextField(fmtChainNumber);
        tfChainNumber.setColumns(10);
        tfChainNumber.setValue(chainNumber);
        tfChainNumber.addPropertyChangeListener(this);
        lblChainNumber.setLabelFor(tfChainNumber);

        lblWire = new JLabel("Марка провода");
        Wire[] cmbWires = new Wire[Wire.getAll().size()];
        for (int i = 0; i < Wire.getAll().size(); i++) {
            cmbWires[i] = Wire.getAll().get(i);
        }
        cmbWire = new JComboBox<Wire>(cmbWires);
        cmbWire.addActionListener((event)->{
            JComboBox<Wire> combo = (JComboBox<Wire>) event.getSource();
            Wire selectedWire = (Wire) combo.getSelectedItem();
            this.wire = selectedWire;
        });
        cmbWire.setSelectedItem(wire);

        lblArea = new JLabel("Тип местности");
        cmbArea = new JComboBox<Area>(Area.values());
        cmbArea.addActionListener((event)->{
            JComboBox<Area> combo = (JComboBox<Area>) event.getSource();
            Area selectedArea = (Area) combo.getSelectedItem();
            this.area = selectedArea;
        });
        cmbArea.setSelectedItem(area);

        lblWind = new JLabel("Район по ветру");
        cmbWind = new JComboBox<Wind>(Wind.values());
        cmbWind.addActionListener((event)->{
            JComboBox<Wind> combo = (JComboBox<Wind>) event.getSource();
            Wind selectedWind = (Wind) combo.getSelectedItem();
            this.wind = selectedWind;
        });
        cmbWind.setSelectedItem(wind);

        lblIce = new JLabel("Район по гололеду");
        cmbIce = new JComboBox<Ice>(Ice.values());
        cmbIce.addActionListener((event)->{
            JComboBox<Ice> combo = (JComboBox<Ice>) event.getSource();
            Ice selectedIce = (Ice) combo.getSelectedItem();
            this.ice = selectedIce;
        });
        cmbIce.setSelectedItem(ice);

        lblMaxTemp = new JLabel("Высшая температура");
        tfMaxTemp = new JFormattedTextField(fmtMaxTemp);
        tfMaxTemp.setColumns(10);
        tfMaxTemp.setValue(maxTemp);
        tfMaxTemp.addPropertyChangeListener(this);
        lblMaxTemp.setLabelFor(tfMaxTemp);

        lblAvgTemp = new JLabel("Среднегодовая температура");
        tfAvgTemp = new JFormattedTextField(fmtAvgTemp);
        tfAvgTemp.setColumns(10);
        tfAvgTemp.setValue(avgTemp);
        tfAvgTemp.addPropertyChangeListener(this);
        lblAvgTemp.setLabelFor(tfAvgTemp);

        lblMinTemp = new JLabel("Минимальная температура");
        tfMinTemp = new JFormattedTextField(fmtMinTemp);
        tfMinTemp.setColumns(10);
        tfMinTemp.setValue(minTemp);
        tfMinTemp.addPropertyChangeListener(this);
        lblMinTemp.setLabelFor(tfMinTemp);

        lblIceTemp = new JLabel("Температура гололедообразования");
        tfIceTemp = new JFormattedTextField(fmtIceTemp);
        tfIceTemp.setColumns(10);
        tfIceTemp.setValue(iceTemp);
        tfIceTemp.addPropertyChangeListener(this);
        lblIce.setLabelFor(tfIceTemp);

        btnCompute = new JButton("Рассчитать");
        btnCompute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Computations computations = new Computations(diffY, diffX, heightOpora,
                        wire, wind, area, ice,
                        minTemp, avgTemp, iceTemp, maxTemp, voltage);
                control.setComputations(computations);
                control.update();
            }
        });

        layout.setHorizontalGroup(layout.createParallelGroup()
                /*.addGroup(layout.createSequentialGroup()
                        .addComponent(lblLength)
                        .addComponent(tfLength))*/
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDiffX)
                        .addComponent(tfDiffX))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDiffY)
                        .addComponent(tfDiffY))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHeightOpora)
                        .addComponent(tfHeightOpora))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblVoltage)
                        .addComponent(tfVoltage))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblChainNumber)
                        .addComponent(tfChainNumber))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblWire)
                        .addComponent(cmbWire))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblArea)
                        .addComponent(cmbArea))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblWind)
                        .addComponent(cmbWind))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIce)
                        .addComponent(cmbIce))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMaxTemp)
                        .addComponent(tfMaxTemp))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAvgTemp)
                        .addComponent(tfAvgTemp))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMinTemp)
                        .addComponent(tfMinTemp))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIceTemp)
                        .addComponent(tfIceTemp))
                .addComponent(btnCompute)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                /*.addGroup(layout.createParallelGroup()
                        .addComponent(lblLength)
                        .addComponent(tfLength))*/
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblDiffX)
                        .addComponent(tfDiffX))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblDiffY)
                        .addComponent(tfDiffY))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblHeightOpora)
                        .addComponent(tfHeightOpora))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblVoltage)
                        .addComponent(tfVoltage))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblChainNumber)
                        .addComponent(tfChainNumber))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblWire)
                        .addComponent(cmbWire))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblArea)
                        .addComponent(cmbArea))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblWind)
                        .addComponent(cmbWind))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblIce)
                        .addComponent(cmbIce))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblMaxTemp)
                        .addComponent(tfMaxTemp))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblAvgTemp)
                        .addComponent(tfAvgTemp))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblMinTemp)
                        .addComponent(tfMinTemp))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblIceTemp)
                        .addComponent(tfIceTemp))
                .addComponent(btnCompute)
        );
    }

    private void setUpFormats() {
        fmtLength = NumberFormat.getNumberInstance();
        fmtDiffY = NumberFormat.getNumberInstance();
        fmtDiffX = NumberFormat.getNumberInstance();
        fmtHeightOpora = NumberFormat.getNumberInstance();
        fmtVoltage = NumberFormat.getNumberInstance();
        fmtChainNumber = NumberFormat.getNumberInstance();
        fmtMaxTemp = NumberFormat.getNumberInstance();
        fmtAvgTemp = NumberFormat.getNumberInstance();
        fmtMinTemp = NumberFormat.getNumberInstance();
        fmtIceTemp = NumberFormat.getNumberInstance();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        /*if (source == tfLength) {
            length = ((Number) tfLength.getValue()).doubleValue();
        } else*/
        if (source == tfDiffX) {
            diffX = ((Number) tfDiffX.getValue()).doubleValue();
            length = diffX * 1.02;
        } else if (source == tfDiffY) {
            diffY = ((Number) tfDiffY.getValue()).doubleValue();
        } else if (source == tfHeightOpora) {
            heightOpora = ((Number) tfHeightOpora.getValue()).doubleValue();
        } else if (source == tfVoltage) {
            voltage = ((Number) tfVoltage.getValue()).intValue();
        } else if (source == tfChainNumber) {
            chainNumber = ((Number) tfChainNumber.getValue()).intValue();
        } else if (source == tfMaxTemp) {
            maxTemp = ((Number) tfMaxTemp.getValue()).intValue();
        } else if (source == tfAvgTemp) {
            avgTemp = ((Number) tfAvgTemp.getValue()).intValue();
        } else if (source == tfMinTemp) {
            minTemp = ((Number) tfMinTemp.getValue()).intValue();
        } else if (source == tfIceTemp) {
            iceTemp = ((Number) tfIceTemp.getValue()).intValue();
        }

        if (tfDiffX != null && tfDiffY != null && tfHeightOpora != null &&
                tfVoltage != null && tfChainNumber != null &&
                tfMaxTemp != null && tfMinTemp != null && tfAvgTemp != null && tfIceTemp != null) {
            update();
        }
    }

    private void update() {
        //tfLength.setText(String.valueOf(length));
        tfDiffX.setText(String.valueOf(diffX));
        tfDiffY.setText(String.valueOf(diffY));
        tfHeightOpora.setText(String.valueOf(heightOpora));
        tfVoltage.setText(String.valueOf(voltage));
        tfChainNumber.setText(String.valueOf(chainNumber));
        tfMaxTemp.setText(String.valueOf(maxTemp));
        tfAvgTemp.setText(String.valueOf(avgTemp));
        tfMinTemp.setText(String.valueOf(minTemp));
        tfIceTemp.setText(String.valueOf(iceTemp));
    }

    public void setControl(Control control) {
        this.control = control;
    }
}