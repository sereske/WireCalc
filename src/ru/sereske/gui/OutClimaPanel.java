package ru.sereske.gui;

import ru.sereske.Computations;

import javax.swing.*;
import javax.swing.border.Border;
import java.text.NumberFormat;

/**
 * Created by stepanovsg on 17.05.2017.
 */
public class OutClimaPanel extends JPanel {

    private Computations computations;

    private double sigmaOpr;
    private double yOpr;
    private double tempOpr;
    private double sigmaMaxProvis;
    private double yMaxProvis;
    private double tempMaxProvis;
    private double fAMaxProvis;
    private double fBMaxProvis;
    private double lMaxProvis;

    private JLabel lblSigmaOpr;
    private JLabel lblYOpr;
    private JLabel lblTempOpr;
    private JLabel lblSigmaMaxProvis;
    private JLabel lblYMaxProvis;
    private JLabel lblTempMaxProvis;
    private JLabel lblFAMaxProvis;
    private JLabel lblFBMaxProvis;
    private JLabel lblLMaxProvis;

    private JFormattedTextField tfSigmaOpr;
    private JFormattedTextField tfYOpr;
    private JFormattedTextField tfTempOpr;
    private JFormattedTextField tfSigmaMaxProvis;
    private JFormattedTextField tfYMaxProvis;
    private JFormattedTextField tfTempMaxProvis;
    private JFormattedTextField tfFAMaxProvis;
    private JFormattedTextField tfFBMaxProvis;
    private JFormattedTextField tfLMaxProvis;


    private NumberFormat format;

    public OutClimaPanel() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String title = "Выходные данные - Определяющие климатические условия";
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);

        setupGuiElements(layout);

        setUpFormats();
    }

    private void setupGuiElements(GroupLayout layout) {
        lblSigmaOpr = new JLabel("Sigma опр");
        tfSigmaOpr = new JFormattedTextField(format);
        tfSigmaOpr.setColumns(10);
        tfSigmaOpr.setValue(sigmaOpr);
        tfSigmaOpr.setEditable(false);

        lblYOpr = new JLabel("Y опр");
        tfYOpr = new JFormattedTextField(format);
        tfYOpr.setColumns(10);
        tfYOpr.setValue(yOpr);
        tfYOpr.setEditable(false);

        lblTempOpr = new JLabel("Темп-ра опр");
        tfTempOpr = new JFormattedTextField(format);
        tfTempOpr.setColumns(10);
        tfTempOpr.setValue(tempOpr);
        tfTempOpr.setEditable(false);

        lblSigmaMaxProvis = new JLabel("Sigma наиб. провисания");
        tfSigmaMaxProvis = new JFormattedTextField(format);
        tfSigmaMaxProvis.setColumns(10);
        tfSigmaMaxProvis.setValue(sigmaMaxProvis);
        tfSigmaMaxProvis.setEditable(false);

        lblYMaxProvis = new JLabel("Y наиб. провисания");
        tfYMaxProvis = new JFormattedTextField(format);
        tfYMaxProvis.setColumns(10);
        tfYMaxProvis.setValue(yMaxProvis);
        tfYMaxProvis.setEditable(false);

        lblTempMaxProvis = new JLabel("Темп-ра наиб. провисания");
        tfTempMaxProvis = new JFormattedTextField(format);
        tfTempMaxProvis.setColumns(10);
        tfTempMaxProvis.setValue(tempMaxProvis);
        tfTempMaxProvis.setEditable(false);

        lblFAMaxProvis = new JLabel("Стрела провиса отн-но т. А");
        tfFAMaxProvis = new JFormattedTextField(format);
        tfFAMaxProvis.setColumns(10);
        tfFAMaxProvis.setValue(fAMaxProvis);
        tfFAMaxProvis.setEditable(false);

        lblFBMaxProvis = new JLabel("Стрела провиса отн-но т. B");
        tfFBMaxProvis = new JFormattedTextField(format);
        tfFBMaxProvis.setColumns(10);
        tfFBMaxProvis.setValue(fBMaxProvis);
        tfFBMaxProvis.setEditable(false);

        lblLMaxProvis = new JLabel("Максимальная длина провода");
        tfLMaxProvis = new JFormattedTextField(format);
        tfLMaxProvis.setColumns(10);
        tfLMaxProvis.setValue(lMaxProvis);
        tfLMaxProvis.setEditable(false);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSigmaOpr)
                        .addComponent(tfSigmaOpr)
                        .addComponent(lblYOpr)
                        .addComponent(tfYOpr)
                        .addComponent(lblTempOpr)
                        .addComponent(tfTempOpr)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSigmaMaxProvis)
                        .addComponent(tfSigmaMaxProvis)
                        .addComponent(lblYMaxProvis)
                        .addComponent(tfYMaxProvis)
                        .addComponent(lblTempMaxProvis)
                        .addComponent(tfTempMaxProvis)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFAMaxProvis)
                        .addComponent(tfFAMaxProvis)
                        .addComponent(lblFBMaxProvis)
                        .addComponent(tfFBMaxProvis)
                        .addComponent(lblLMaxProvis)
                        .addComponent(tfLMaxProvis)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblSigmaOpr)
                        .addComponent(tfSigmaOpr)
                        .addComponent(lblYOpr)
                        .addComponent(tfYOpr)
                        .addComponent(lblTempOpr)
                        .addComponent(tfTempOpr)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblSigmaMaxProvis)
                        .addComponent(tfSigmaMaxProvis)
                        .addComponent(lblYMaxProvis)
                        .addComponent(tfYMaxProvis)
                        .addComponent(lblTempMaxProvis)
                        .addComponent(tfTempMaxProvis)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblFAMaxProvis)
                        .addComponent(tfFAMaxProvis)
                        .addComponent(lblFBMaxProvis)
                        .addComponent(tfFBMaxProvis)
                        .addComponent(lblLMaxProvis)
                        .addComponent(tfLMaxProvis)
                )
        );
    }

    private void setUpFormats() {
        format = NumberFormat.getNumberInstance();
    }

    public void setComputations(Computations computations) {
        this.computations = computations;
    }

    public void update() {
        tfSigmaOpr.setValue(computations.getSigmaOpr());
        tfYOpr.setValue(computations.getyOpr());
        tfTempOpr.setValue(computations.getTempOpr());
        tfSigmaMaxProvis.setValue(computations.getSigmaMaxProvis());
        tfYMaxProvis.setValue(computations.getYMaxProvis());
        tfTempMaxProvis.setValue(computations.getTempMaxProvis());
        tfFAMaxProvis.setValue(computations.getFaMaxProvis());
        tfFBMaxProvis.setValue(computations.getFbMaxProvis());
        tfLMaxProvis.setValue(computations.getLMaxProvis());
    }
}
