package view;

import auth.Credentials;
import common.Constants;
import common.Utils;
import comp.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class EntryDialog implements Constants {

    public static Book getBookInfo(Component parent) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JTextField firstNameField = new JTextField(12);
        JTextField lastNameField = new JTextField(12);
        JTextField titleField = new JTextField(12);
        JTextField isbnField = new JTextField(12);

        firstNameField.setBorder(new BevelBorder(BevelBorder.RAISED));
        lastNameField.setBorder(new BevelBorder(BevelBorder.RAISED));
        titleField.setBorder(new BevelBorder(BevelBorder.RAISED));
        isbnField.setBorder(new BevelBorder(BevelBorder.RAISED));


        JCheckBox availibilityBox = new JCheckBox();
        availibilityBox.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Create a Combo box for role
        JComboBox<Genre> roleComboBox = new JComboBox<>(Genre.values());
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
        panel.add(new JLabel("Title:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Isbn:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Availability:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Genre:"), constraints);

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
        panel.add(titleField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(isbnField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(availibilityBox, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.weightx = 1.0;
        panel.add(roleComboBox, constraints);
        panel.requestFocus();
        int okCxl = ViewUtils.showDialog(parent, panel, "Book Information");

        // 3. Process the result when the user clicks OK
        if (okCxl == JOptionPane.OK_OPTION) {
            // Get the login and password as a character array
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String title = titleField.getText().trim();
            String isbn = isbnField.getText().trim();

            Boolean available = availibilityBox.isSelected();


            // Get back the stored password
            Book book =  new Book(new Author(firstName, lastName), title, available, (Genre)roleComboBox.getSelectedItem(),isbn );

            return book;
        } else {
            logger.info("Dialog cancelled or closed.");
            return null;
        }
    }

    public static Book getBookInfo(Component parent, Book book) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JTextField firstNameField = new JTextField(12);
        firstNameField.setEnabled(false);
        firstNameField.setText(book.getAuthor().name());
        JTextField lastNameField = new JTextField(12);
        lastNameField.setText(book.getAuthor().surname());
        lastNameField.setEnabled(false);
        JTextField titleField = new JTextField(12);
        titleField.setEnabled(false);
        titleField.setText(book.getTitle());
        JTextField isbnField = new JTextField(12);
        isbnField.setEnabled(false);
        isbnField.setText(book.getIsbn());


        firstNameField.setBorder(new BevelBorder(BevelBorder.RAISED));
        lastNameField.setBorder(new BevelBorder(BevelBorder.RAISED));
        titleField.setBorder(new BevelBorder(BevelBorder.RAISED));
        isbnField.setBorder(new BevelBorder(BevelBorder.RAISED));

        JCheckBox availibilityBox = new JCheckBox();
        availibilityBox.setEnabled(false);
        availibilityBox.isSelected();
        availibilityBox.setBorder(new BevelBorder(BevelBorder.RAISED));

        // Create a Combo box for role
        JComboBox<Genre> roleComboBox = new JComboBox<>(Genre.values());
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.4;
        constraints.insets = new Insets(0, 5, 10, 10);
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Author:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Title:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Isbn:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Availability:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(firstNameField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(lastNameField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(availibilityBox, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.weightx = 1.0;
        panel.add(roleComboBox, constraints);
        panel.requestFocus();
        int okCxl = ViewUtils.showDialog(parent, panel, "User Role");

        // 3. Process the result when the user clicks OK
        if (okCxl == JOptionPane.OK_OPTION) {
            // Get back the new role
            Genre genre = (Genre) roleComboBox.getSelectedItem();
            book.getGenre();
            return book;
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
