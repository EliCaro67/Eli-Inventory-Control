package controller;

import comp.AccountsTableModel;

import java.awt.event.ActionListener;

public interface Controller extends ActionListener {
    AccountsTableModel getTableModel();
}
