package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame{
    private static final String TAG1 = "administrator";
    private JButton adminCreateButton;
    private JPanel adminPanel;
    private JTextField adminUser;
    private JPasswordField adminPassword;
    private Controller controller;

    public JPasswordField getPasswordField1() {
        return adminPassword;
    }

    public JButton getAdminCreateButton() {

        return adminCreateButton;
    }

    public JPanel getAdminPanel() {
        return adminPanel;
    }

    public JTextField getAdminUser() {

        return adminUser;
    }

}
