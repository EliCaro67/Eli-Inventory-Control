package view;

import comp.AccountsTableModel;

import javax.swing.*;
import java.awt.*;

public class AccountsView extends View{
    private JPanel accountsPanel;
    private JTable accountsTable;
    private JButton addUserButton;
    private JButton updateUserButton;
    private JPanel accountsContent;
    private JButton deleteUserButton;
    private final AccountsTableModel model;

    public AccountsView(Component parent, AccountsTableModel model) {
        accountsPanel.setPreferredSize(parent.getPreferredSize());
        this.model = model;
        setUpTable();
    }

    private void setUpTable() {
        accountsTable.setFillsViewportHeight(true);
        accountsTable.setModel(model);
    }

    public JPanel getAccountsPanel() {
        return accountsPanel;
    }

    public JTable getAccountsTable() {
        return accountsTable;
    }

    public JButton getAddUserButton() {
        return addUserButton;
    }

    public JButton getUpdateUserButton() {
        return updateUserButton;
    }

    public JButton getDeleteUserButton() {
        return deleteUserButton;
    }

    public JPanel getAccountsContent() {
        return accountsContent;
    }

    public AccountsTableModel getTableModel() {
        return model;
    }
}
