package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MainScreen extends JTabbedPane {

    private AccountsView accountsView;
    private EntryView entryView;
    private DeletionView deletionView;
    private ListingView listingView;
    private LendingView lendingView;
    private RepoView repoView;
    private final Controller controller;

    public MainScreen(Component parent, Controller controller) {
        this.controller = controller;
        setPreferredSize(parent.getPreferredSize());
        setBorder(new BevelBorder(BevelBorder.RAISED));
        addComponents();
    }

    private void addComponents() {
        // Assign them to instance variables
        accountsView = new AccountsView(this,
                controller.getTableModel());
        entryView = new EntryView(this);
        deletionView = new DeletionView(this);
        listingView = new ListingView(this);
        lendingView = new LendingView(this);
        repoView = new RepoView(this);

        add(accountsView.getAccountsPanel());
        add(entryView.getEntryPanel());
        add(deletionView.getDeletionPanel());
        add(listingView.getListingPanel());
        add(lendingView.getLendingPanel());
        add(repoView.getRepoPanel());
    }

    public AccountsView getAccountsView() {
        return accountsView;
    }

    public DeletionView getDeletionView() {
        return deletionView;
    }

    public EntryView getEntryView() {
        return entryView;
    }

    public LendingView getLendingView() {
        return lendingView;
    }

    public ListingView getListingView() {
        return listingView;
    }

    public RepoView getRepoView() {
        return repoView;
    }
}
