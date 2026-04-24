package dbase;

import common.Constants;
import common.Utils;
import comp.Book;
import comp.User;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static common.Constants.INVENTORY_DB;

public class InventoryDAO implements DAO<Book> {

    private HashMap<String/* login */, Book> bookDB;
    public InventoryDAO() {
        loadOrInitializeMap();
    }

    private void loadOrInitializeMap() {
        try {
            bookDB = Utils.loadFromFile(Constants.INVENTORY_DB);
        } catch (IOException e) {
            // EOF seen - overwrite contents.
            bookDB = new HashMap<>();
            Utils.commitToFile(bookDB, INVENTORY_DB);
        }
    }

    @Override
    public void save(Book t) {
        //TODO
         bookDB.put(t.getIsbn(), t);
         Utils.commitToFile(bookDB, INVENTORY_DB);
    }

    @Override
    public Optional<Book> get(String identifier) {
        //TODO
        Book book = bookDB.get(identifier);
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> getAll() {
        //TODO
        List<Book> bookList = new ArrayList<>();
        for (String key : bookDB.keySet()) {
            bookList.add(bookDB.get(key));
        }
        return bookList;
    }

    @Override
    public void update(Book t) {
        //TODO
        bookDB.put(t.getIsbn(),t);
        Utils.commitToFile(bookDB, INVENTORY_DB);
    }

    @Override
    public void delete(Book t) {
        //TODO
        bookDB.remove(t.getIsbn());
        Utils.commitToFile(bookDB, INVENTORY_DB);
    }
}
