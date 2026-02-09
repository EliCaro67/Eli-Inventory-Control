package view;

import javax.swing.*;
import java.awt.*;

public class EntryView  extends View {
    private JPanel entryPanel;

    public EntryView(Component parent) {
        entryPanel.setPreferredSize(parent.getPreferredSize());
    }

    public JPanel getEntryPanel() {
        return entryPanel;
    }
}
