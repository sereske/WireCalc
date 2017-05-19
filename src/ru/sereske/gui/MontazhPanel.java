package ru.sereske.gui;

import ru.sereske.Computations;
import ru.sereske.Montazh;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created by Sergey on 17.05.2017.
 */
public class MontazhPanel extends JPanel {

    private Computations computations;

    private JTable montazhTable;

    public MontazhPanel() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String title = "Монтажная таблица";
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);

        String[] colNames = {"Температура", "Fa", "Fb"};
        String[][] data = {};
        montazhTable = new JTable(new DefaultTableModel(data, colNames));
        JScrollPane scrollPane = new JScrollPane(montazhTable);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPane))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(scrollPane))
        );
    }

    public void setComputations(Computations computations) {
        this.computations = computations;
    }

    public void update() {
        String[] colNames = {"Температура", "Fa", "Fb"};
        List<Montazh> montazhs = computations.getMontazh();
        DefaultTableModel model = (DefaultTableModel) montazhTable.getModel();
        model.setRowCount(0);
        for (int j = 0; j < montazhs.size(); j++) {
            model.insertRow(j, (Object[]) null);
            Montazh m  = montazhs.get(j);
            montazhTable.setValueAt(String.valueOf(m.getTemp()), j, 0);
            montazhTable.setValueAt(String.valueOf(m.getFa()), j, 1);
            montazhTable.setValueAt(String.valueOf(m.getFb()), j, 2);
        }
        model.fireTableDataChanged();
    }

    private class MontazhTableModel extends AbstractTableModel {

        private final Object[][] rowData;
        private final Object[] columnNames;

        private MontazhTableModel(Object[][] rowData, Object[] columnNames) {
            this.rowData = rowData;
            this.columnNames = columnNames;
        }

        @Override
        public void fireTableDataChanged() {
            super.fireTableDataChanged();
        }

        public String getColumnName(int column) {
            this.fireTableDataChanged();
            return columnNames[column].toString();
        }

        public int getRowCount() {
            return rowData.length;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int row, int col) { return rowData[row][col]; }

        public boolean isCellEditable(int row, int column) { return true; }

        public void setValueAt(Object value, int row, int col) {
            rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
}
