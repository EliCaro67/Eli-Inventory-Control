package comp;

import auth.AccountsManager;

import java.awt.event.ActionListener;

public interface Controller extends ActionListener {
    AccountsTableModel getTableModel();
}
