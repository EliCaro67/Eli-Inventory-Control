package main;

import auth.AccountsManager;
import common.Constants;
import common.Utils;
import comp.AccountsTableModel;
import comp.Controller;
import comp.User;
import dbase.DAO;
import dbase.PersonnelDAO;
import view.AccountsView;
import view.MainScreen;

import javax.swing.*;
import java.awt.*;

public class Main implements Controller, Constants {
    private final DAO< User> dao;
    private AccountsTableModel tableModel;

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
        MainScreen mainScreen = new MainScreen(frame, this);
        AccountsManager accManager = new AccountsManager(dao, mainScreen);
        AccountsView acctView = mainScreen.getAccountsView();
        acctView.getAddUserButton().addActionListener(accManager);
        acctView.getUpdateUserButton().addActionListener(accManager);
        acctView.getDeleteUserButton().addActionListener(accManager);
        frame.setContentPane(mainScreen);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    @Override
    public AccountsTableModel getTableModel() {
        if (tableModel == null)
            tableModel = new AccountsTableModel(dao);
        return tableModel;
    }
}
