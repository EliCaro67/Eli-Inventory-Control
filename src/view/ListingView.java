package view;

import javax.swing.*;
import java.awt.*;

public class ListingView  extends View {
    private JPanel listingPanel;

    public ListingView(Component parent) {
        listingPanel.setPreferredSize(parent.getPreferredSize());
    }

    public JPanel getListingPanel() {
        return listingPanel;
    }
}
