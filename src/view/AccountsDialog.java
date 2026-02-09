package view;

import auth.Credentials;
import comp.Role;
import comp.User;
import common.Constants;
import common.Utils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Arrays;

public class AccountsDialog implements Constants {


    public static User getUserRole(Component parent) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JTextField firstNameField = new JTextField(12);
        JTextField lastNameField = new JTextField(12);
        JTextField loginField = new JTextField(12);

        firstNameField.setBorder(new BevelBorder(BevelBorder.RAISED));
        lastNameField.setBorder(new BevelBorder(BevelBorder.RAISED));
        loginField.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Create a JPasswordField instance
        JPasswordField passwordField = new JPasswordField(12);
        passwordField.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Create a Combo box for role
        JComboBox<Role> roleComboBox = new JComboBox<>(Role.values());
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.4;
        constraints.insets = new Insets(0, 5, 10, 10);
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("First Name:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Last Name:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Login:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Role:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(firstNameField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(lastNameField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(loginField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(passwordField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.weightx = 1.0;
        panel.add(roleComboBox, constraints);
        panel.requestFocus();
        int okCxl = ViewUtils.showDialog(parent, panel, "User Information");

        // 3. Process the result when the user clicks OK
        if (okCxl == JOptionPane.OK_OPTION) {
            // Get the login and password as a character array
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String login = loginField.getText().trim().toLowerCase();

            char[] passwordChars = passwordField.getPassword();
            byte[] passwordBytes = Utils.charToBytes(passwordChars);
            // Get back the stored password
            User user =  new User(firstName, lastName, login, passwordBytes);
            user.setPrimaryRole((Role) roleComboBox.getSelectedItem());
            return user;
        } else {
            logger.info("Dialog cancelled or closed.");
            return null;
        }
    }

    public static User getUserRole(Component parent, User user) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JTextField firstNameField = new JTextField(12);
        firstNameField.setEnabled(false);
        firstNameField.setText(user.getFirstName());
        JTextField lastNameField = new JTextField(12);
        lastNameField.setText(user.getLastName());
        lastNameField.setEnabled(false);

        firstNameField.setBorder(new BevelBorder(BevelBorder.RAISED));
        lastNameField.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Create a Combo box for role
        JComboBox<Role> roleComboBox = new JComboBox<>(Role.values());
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.4;
        constraints.insets = new Insets(0, 5, 10, 10);
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("First Name:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Last Name:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Role:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(firstNameField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(lastNameField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.weightx = 1.0;
        panel.add(roleComboBox, constraints);
        panel.requestFocus();
        int okCxl = ViewUtils.showDialog(parent, panel, "User Information");

        // 3. Process the result when the user clicks OK
        if (okCxl == JOptionPane.OK_OPTION) {
            // Get back the new role
            Role role = (Role) roleComboBox.getSelectedItem();
            user.setPrimaryRole(role);
            return user;
        } else {
            logger.info("Dialog cancelled or closed.");
            return null;
        }
    }

        /**
         * Return a credential object (login, password) or null
         * @return credentials
         * @see User
         */
    public static Credentials getCredentials(Component parent) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JTextField loginField = new JTextField(12);
        loginField.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Create a JPasswordField instance
        JPasswordField passwordField = new JPasswordField(12);
        passwordField.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Create a panel to hold the fields and any additional message
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.4;
        constraints.insets = new Insets(0, 0, 5, 0);
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Login:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loginField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        int okCxl = ViewUtils.showDialog(parent, panel, "Authentication Required");

        // 3. Process the result when the user clicks OK
        if (okCxl == JOptionPane.OK_OPTION) {
            // Get the login and password as a character array
            String login = loginField.getText().trim().toLowerCase();

            char[] passwordChars = passwordField.getPassword();
            byte[] passwordBytes = Utils.charToBytes(passwordChars);
            // Get back the stored password
            return new Credentials(login, passwordBytes);
        } else {
            logger.info("Dialog cancelled or closed.");
            return null;
        }
    }

}
