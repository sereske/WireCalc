package ru.sereske.gui;

import ru.sereske.Control;
import ru.sereske.Montazh;

import javax.swing.*;

/**
 * Created by stepanovsg on 16.05.2017.
 */
public class NewGui extends JFrame {

    public NewGui() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        Control control = new Control();
        InputPanel inputPanel = new InputPanel();
        inputPanel.setControl(control);
        OutClimaPanel outClimaPanel = new OutClimaPanel();
        OutYPanel outYPanel = new OutYPanel();
        MontazhPanel montazhPanel = new MontazhPanel();
        GraphPanel graphPanel = new GraphPanel();
        control.setInputPanel(inputPanel);
        control.setOutYPanel(outYPanel);
        control.setOutClimaPanel(outClimaPanel);
        control.setMontazhPanel(montazhPanel);
        control.setGraphPanel(graphPanel);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(inputPanel)

                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(outYPanel)
                                .addComponent(outClimaPanel)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(montazhPanel)
                                .addComponent(graphPanel)
                        )
                )

        );

        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(inputPanel)

                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(outYPanel)
                                        .addComponent(outClimaPanel))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(montazhPanel)
                                        .addComponent(graphPanel))
                        )
                )
        );

        pack();
    }

    private static void createAndShowGui() {
        JFrame frame = new NewGui();
        frame.setTitle("Расчет провода");
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
