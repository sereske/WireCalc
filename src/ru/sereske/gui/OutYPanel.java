package ru.sereske.gui;

import ru.sereske.Computations;

import javax.swing.*;
import javax.swing.border.Border;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by stepanovsg on 17.05.2017.
 */
public class OutYPanel extends JPanel {

    private Computations computations;

    private double y1;
    private double y2;
    private double y3;
    private double y4;
    private double y5;
    private double y6;
    private double y7;

    private JLabel lblY1;
    private JLabel lblY2;
    private JLabel lblY3;
    private JLabel lblY4;
    private JLabel lblY5;
    private JLabel lblY6;
    private JLabel lblY7;

    private JFormattedTextField tfY1;
    private JFormattedTextField tfY2;
    private JFormattedTextField tfY3;
    private JFormattedTextField tfY4;
    private JFormattedTextField tfY5;
    private JFormattedTextField tfY6;
    private JFormattedTextField tfY7;

    private NumberFormat format;

    public OutYPanel() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String title = "Выходные данные - Нагрузки";
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);

        setupGuiElements(layout);

        setUpFormats();
    }

    private void setupGuiElements(GroupLayout layout) {
        lblY1 = new JLabel("Y1");
        tfY1 = new JFormattedTextField(format);
        tfY1.setColumns(10);
        tfY1.setValue(y1);
        tfY1.setEditable(false);

        lblY2 = new JLabel("Y2");
        tfY2 = new JFormattedTextField(format);
        tfY2.setColumns(10);
        tfY2.setValue(y2);
        tfY2.setEditable(false);

        lblY3 = new JLabel("Y3");
        tfY3 = new JFormattedTextField(format);
        tfY3.setColumns(10);
        tfY3.setValue(y3);
        tfY3.setEditable(false);

        lblY4 = new JLabel("Y4");
        tfY4 = new JFormattedTextField(format);
        tfY4.setColumns(10);
        tfY4.setValue(y4);
        tfY4.setEditable(false);

        lblY5 = new JLabel("Y5");
        tfY5 = new JFormattedTextField(format);
        tfY5.setColumns(10);
        tfY5.setValue(y5);
        tfY5.setEditable(false);

        lblY6 = new JLabel("Y6");
        tfY6 = new JFormattedTextField(format);
        tfY6.setColumns(10);
        tfY6.setValue(y6);
        tfY6.setEditable(false);

        lblY7 = new JLabel("Y7");
        tfY7 = new JFormattedTextField(format);
        tfY7.setColumns(10);
        tfY7.setValue(y7);
        tfY7.setEditable(false);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblY1)
                        .addComponent(tfY1))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblY2)
                        .addComponent(tfY2))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblY3)
                        .addComponent(tfY3))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblY4)
                        .addComponent(tfY4))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblY5)
                        .addComponent(tfY5))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblY6)
                        .addComponent(tfY6))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblY7)
                        .addComponent(tfY7))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY1)
                        .addComponent(tfY1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY2)
                        .addComponent(tfY2))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY3)
                        .addComponent(tfY3))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY4)
                        .addComponent(tfY4))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY5)
                        .addComponent(tfY5))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY6)
                        .addComponent(tfY6))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY7)
                        .addComponent(tfY7))
        );
    }

    private void setUpFormats() {
        format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
    }


    public void setComputations(Computations computations) {
        this.computations = computations;
    }

    public void update() {
        tfY1.setValue(computations.getY1());
        tfY2.setValue(computations.getY2());
        tfY3.setValue(computations.getY3());
        tfY4.setValue(computations.getY4());
        tfY5.setValue(computations.getY5());
        tfY6.setValue(computations.getY6());
        tfY7.setValue(computations.getY7());
    }
}

