package view;

import common.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SplashPanel extends JPanel {
    private BufferedImage image;

    public SplashPanel(Component parent, String resource) {
        setPreferredSize(parent.getPreferredSize());
        this.image = Utils.getImageFromSource(resource);
        setName("About");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,
                0, 0,
                getWidth(), getHeight(),
                0, 0,
                image.getWidth(null),
                image.getHeight(null),
                null);
    }
}
