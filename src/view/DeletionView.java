package view;

import javax.swing.*;
import java.awt.*;

public class DeletionView {

    private JPanel deletionPanel;

    public DeletionView(Component parent) {
        deletionPanel.setPreferredSize(parent.getPreferredSize());
    }

    public JPanel getDeletionPanel() {
        return deletionPanel;
    }

}
