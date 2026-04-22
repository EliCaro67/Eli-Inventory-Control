package controller;

import auth.AccountsManager;
import auth.Decryptor;
import auth.Encryptor;
import auth.SecretKeyGenerator;
import common.Constants;
import common.Utils;
import comp.AccountsTableModel;
import comp.Administrator;
import comp.User;
import dbase.DAO;
import dbase.PersonnelDAO;
import view.AccountsDialog;
import view.AccountsView;
import view.AdminView;
import view.MainScreen;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.*;
import javax.swing.text.html.parser.TagElement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author Elisha Carothers
 * This is the entry point to the code.
 */
public class Main implements Controller, Constants {
    private final DAO<User> dao;
    private AccountsTableModel tableModel;
    private AccountsManager accManager;
    private MainScreen mainScreen;
    private Administrator admin;
    private byte[] password;
    private SecretKey secretKey;
    private String name;
    private AdminView adminView = new AdminView();

    private static final String TAG1 = "administrator";


    public Main() {
        dao = new PersonnelDAO();
        createAndShowGUI();
    }


    private void createAndShowGUI() {

        JFrame frame = new JFrame();
        frame.setTitle("Inventory Control");
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        addComponents(frame);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();


        //Administrator Setup
        if (isInitialSetup()) {
            JFrame frameAdmin = new JFrame();
            JPanel contentPanel = new JPanel();
            CardLayout layout = new CardLayout();
            contentPanel.setLayout(layout);
            AdminView adminView = new AdminView();
            JPanel adminContent = adminView.getAdminPanel();
            contentPanel.add(adminContent, TAG1);
            frameAdmin.setContentPane(contentPanel);
            frameAdmin.setDefaultCloseOperation(frameAdmin.EXIT_ON_CLOSE);
            frameAdmin.setLocationRelativeTo(null);
            frameAdmin.pack();
            frameAdmin.setVisible(true);

            JPasswordField adminPassword = adminView.getPasswordField1();
            JTextField adminusername = adminView.getAdminUser();


            JButton adminSave = adminView.getAdminCreateButton();
            adminSave.addActionListener(actionEvent -> {
                password = adminPassword.getText().getBytes();
                System.out.println(password);
                name = adminusername.getText();
                System.out.println(name);

                try {
                    secretKey = new SecretKeyGenerator(AccountsManager.algorithmInUse).getSecretKey();

                } catch (NoSuchAlgorithmException e) {
                    logger.info(e.getMessage());
                    throw new RuntimeException(e.getMessage());
                }

                try {
                    password = new Encryptor(password, AccountsManager.algorithmInUse, secretKey).getCipherText();
                } catch (BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                         IOException e) {
                    throw new RuntimeException(e);
                }

                admin = new Administrator(name, password, secretKey);
                Utils.commitToFile(admin, Constants.ADMIN_DB);
                System.out.println("Created Admin entry...");
                System.out.println("Admin Name:" + admin.getUserName());
                System.out.println("Admin Password:" + Arrays.toString(admin.getPassword()));
                frameAdmin.dispose();
                frame.setVisible(true);
            });
        }

        if (!isInitialSetup()) {
            frame.setVisible(true);
        }
    }


    private void addComponents(JFrame frame) {
        mainScreen = new MainScreen(frame, this);
        accManager = new AccountsManager();
        AccountsView acctView = mainScreen.getAccountsView();
        acctView.getAddUserButton().addActionListener(this);
        acctView.getUpdateUserButton().addActionListener(this);
        acctView.getDeleteUserButton().addActionListener(this);
        frame.setContentPane(mainScreen);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Changes Table contents
        String actionCommand = e.getActionCommand();
        JButton button = (JButton) e.getSource();
        User user;
        AccountsView accountsView = mainScreen.getAccountsView();
        JTable accountsTable = accountsView.getAccountsTable();
        AccountsTableModel tableModel = (AccountsTableModel) accountsTable.getModel();
        List<User> users;
        int row;
        switch (actionCommand) {
            case "add_user":
                user = AccountsDialog.getUserRole(accountsView.getAccountsPanel());
                if (user != null) {
                    user.setPassword(accManager.encryptPassword(user.getPassword()));
                    dao.save(user);
                }
                break;
            case "update_role":
                row = accountsTable.getSelectedRow();
                users = dao.getAll();
                if (users == null || row < 0)
                    break;
                user = users.get(row);
                User updatedUser = AccountsDialog
                        .getUserRole(button.getParent(), user);
                if (updatedUser == null)
                    break;
                if (!updatedUser.equals(user)) {
                    updatedUser.setPassword(accManager.encryptPassword(updatedUser.getPassword()));
                    if (dao.get(user.getLogin()).isPresent()) {
                        dao.delete(user);
                        dao.save(updatedUser);
                    }
                }
                break;
            case "delete_user":
                row = accountsTable.getSelectedRow();
                if (row < 0)
                    break;
                users = dao.getAll();
                user = users.get(row);
                dao.delete(user);
                break;
            default:
        }
        // Set up JTable on AccountView
        tableModel.fireTableDataChanged();
        for (User u : dao.getAll()) {
            System.out.println(u);
        }
        System.out.println("---");
    }

    public boolean isInitialSetup() {
        //TODO

        try {
            admin = Utils.loadFromFile(Constants.ADMIN_DB);
        } catch (NullPointerException | IOException e) {
            logger.info(e.getMessage());
            return true;
        }
        return false;
    }

    public String getAdministratorName() {
        if (admin == null)
            return null;
        else
            return admin.getUserName();
    }

    public byte[] getAdministratorPassword() {
        if (admin == null)
            return null;
        else
            return admin.getPassword();
    }

    @Override
    public AccountsTableModel getTableModel() {
        if (tableModel == null)
            tableModel = new AccountsTableModel(dao);
        return tableModel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

}
