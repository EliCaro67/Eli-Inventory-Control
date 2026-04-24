package view;

import comp.EntryTableModel;

import javax.swing.*;
import java.awt.*;

public class EntryView {
    private JPanel entryPanel;
    private JPanel entryContent;
    private JTable entryTable;
    private JButton entryButton;
    private JButton updateButton;
    private final EntryTableModel model;

    public EntryView(Component parent, EntryTableModel model){
        this.model = model;
        entryPanel.setPreferredSize(parent.getPreferredSize());
        setUpTable();
    }

    private void setUpTable() {
        entryTable.setFillsViewportHeight(true);
        entryTable.setModel(model);
    }

    public JPanel getEntryPanel() {
        return entryPanel;
    }

    public JPanel getEntryContent() {
        return entryContent;
    }

    public JTable getEntryTable() {
        return entryTable;
    }

    public JButton getSaveButton() {
        return entryButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public EntryTableModel getModel() {
        return model;
    }
}
