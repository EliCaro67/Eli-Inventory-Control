package view;

import javax.swing.*;
import java.awt.*;

public class LendingView  extends View {
    private JPanel lendingPanel;

    public LendingView(Component parent) {
        lendingPanel.setPreferredSize(parent.getPreferredSize());
    }

    public JPanel getLendingPanel() {
        return lendingPanel;
    }
}
