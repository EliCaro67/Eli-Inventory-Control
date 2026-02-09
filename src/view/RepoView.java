package view;

import javax.swing.*;
import java.awt.*;

public class RepoView {
    private JPanel repoPanel;

    public RepoView(Component parent) {
        repoPanel.setPreferredSize(parent.getPreferredSize());
    }

    public JPanel getRepoPanel() {
        return repoPanel;
    }
}
