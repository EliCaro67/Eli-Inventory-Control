package controller;

import comp.AccountsTableModel;
import comp.DeleteTableModel;
import comp.EntryTableModel;
import view.EntryView;

import java.awt.event.ActionListener;

public interface Controller extends ActionListener {
    AccountsTableModel getTableModel();
    DeleteTableModel getDeleteTableModel();
    EntryTableModel getEntryTableModel();
}
