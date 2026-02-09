package view;

import javax.swing.*;
import java.awt.*;

public class LendingView {
    private JPanel lendingPanel;

    public LendingView(Component parent) {
        lendingPanel.setPreferredSize(parent.getPreferredSize());
    }

    public JPanel getLendingPanel() {
        return lendingPanel;
    }
}
