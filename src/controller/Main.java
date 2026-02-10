package controller;

import auth.AccountsManager;
import common.Constants;
import comp.AccountsTableModel;
import comp.User;
import dbase.DAO;
import dbase.PersonnelDAO;
import view.AccountsDialog;
import view.AccountsView;
import view.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Main implements Controller, Constants {
    private final DAO< User> dao;
    private AccountsTableModel tableModel;
    private AccountsManager accManager;
    private MainScreen mainScreen;

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
        frame.setVisible(true);
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
                if (user != null){
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
                    if(dao.get(user.getLogin()).isPresent()) {
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
        for (User u : dao.getAll()){
            System.out.println(u);
        }
        System.out.println("---");
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
