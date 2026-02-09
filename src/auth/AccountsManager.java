/**
 *
 */
package auth;

import common.Constants;
import common.Utils;
import comp.AccountsTableModel;
import comp.Role;
import comp.User;
import dbase.DAO;
import view.AccountsDialog;
import view.AccountsView;
import view.MainScreen;
import view.ViewUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

/**
 * @author pillai
 *
 */
public final class AccountsManager implements Constants, ActionListener {

    /**
     * Authenticator
     */
    private SecretKey key;
    private final String algorithms = "DES/ECB/PKCS5Padding";
    private final String algorithmInUse = "DES";
    private final DAO<User> dao;
    private MainScreen mainScreen;

    public AccountsManager(DAO<User> dao, MainScreen mainScreen) {
        this.dao = dao;
        this.mainScreen = mainScreen;
        try {
            key = Utils.loadFromFile(KEY_DB);
            logger.info("KEY loaded!");
        } catch (IOException e1) {
            try {
                key = new SecretKeyGenerator(algorithmInUse).getSecretKey();
                Utils.commitToFile(key, KEY_DB);
                logger.info("New KEY generated!");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Adds login and password to the map.
     * @param password
     * @return
     */
    public byte[] encryptPassword(byte[] password) {
        if (password == null) {
            logger.log(Level.SEVERE, "No password supplied!");
            throw new IllegalArgumentException("No password supplied!");
        }

        try {
            Encryptor encrypt = new Encryptor(password, algorithms, key);
            password = encrypt.getCipherText();
        } catch (InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException
                 | IOException e) {
            logger.log(Level.SEVERE, "Error encrypting password!");
            throw new RuntimeException(e.getMessage());
        }
       return password;
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

        switch (actionCommand) {
            case "add_user":
                user = AccountsDialog.getUserInfo(null);
                if (user != null){
                    user.setPassword(encryptPassword(user.getPassword()));
                    dao.save(user);
                }
                break;
            case "update_user":
                Credentials credentials = AccountsDialog.getCredentials(button.getParent());
                if (credentials != null){
                    String login = credentials.login();
                    if(dao.get(login).isPresent()) {
                        user = dao.get(login).get();
                        user.setPassword(encryptPassword(user.getPassword()));
                        dao.save(user);
                    }
                }
                break;
            case "delete_user":
                int row = accountsTable.getSelectedRow();
                if (row < 0)
                    break;
                List<User> users = dao.getAll();
                user = users.get(row);
                dao.delete(user);
                break;
            default:
        }
        // Set up JTable on AccountView
        tableModel.fireTableDataChanged();
        for (User u : dao.getAll()){
            System.out.println(u);
        }
        System.out.println("---");
    }

    public byte[] decryptPassword(byte[] password) {
        try {
            Decryptor decrypt = new Decryptor(password, algorithms, key);
            return decrypt.getClearText();
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
