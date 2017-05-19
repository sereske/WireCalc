package ru.sereske;

import ru.sereske.gui.*;

import javax.swing.*;

/**
 * Created by Sergey on 17.05.2017.
 */
public class Control {

    private InputPanel inputPanel;
    private OutYPanel outYPanel;
    private OutClimaPanel outClimaPanel;
    private MontazhPanel montazhPanel;
    private GraphPanel graphPanel;

    public void setComputations(Computations computations) {
        outYPanel.setComputations(computations);
        outClimaPanel.setComputations(computations);
        montazhPanel.setComputations(computations);
        graphPanel.setComputations(computations);
    }

    public void update() {
        outYPanel.update();
        outClimaPanel.update();
        montazhPanel.update();
        graphPanel.update();
    }

    public void setInputPanel(InputPanel inputPanel) {
        this.inputPanel = inputPanel;
    }

    public void setOutYPanel(OutYPanel outYPanel) {
        this.outYPanel = outYPanel;
    }

    public void setOutClimaPanel(OutClimaPanel outClimaPanel) {
        this.outClimaPanel = outClimaPanel;
    }

    public void setMontazhPanel(MontazhPanel montazhPanel) {
        this.montazhPanel = montazhPanel;
    }

    public void setGraphPanel(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }
}
