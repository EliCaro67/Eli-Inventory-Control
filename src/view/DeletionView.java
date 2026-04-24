package view;

import comp.DeleteTableModel;

import javax.swing.*;
import java.awt.*;

public class DeletionView {

    private JPanel deletionPanel;
    private JPanel deletionContent;
    private JTable deletionTable;
    private JButton deleteButton;
    private final DeleteTableModel model;

    public DeletionView(Component parent, DeleteTableModel model) {
        this.model = model;
        deletionPanel.setPreferredSize(parent.getPreferredSize());
        setUpTable();

    }
    private void setUpTable() {
        deletionTable.setFillsViewportHeight(true);
        deletionTable.setModel(model);
    }

    public JPanel getDeletionPanel() {
        return deletionPanel;
    }

    public JPanel getDeletionContent() {
        return deletionContent;
    }

    public JTable getDeletionTable() {
        return deletionTable;
    }

    public JButton getDelete() {
        return deleteButton;
    }

    public DeleteTableModel getModel() {
        return model;
    }
}
