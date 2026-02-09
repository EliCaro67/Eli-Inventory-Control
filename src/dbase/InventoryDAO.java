package dbase;

import comp.Book;

import java.util.List;
import java.util.Optional;

public class InventoryDAO implements DAO<Book> {

    @Override
    public void save(Book t) {
        //TODO
    }

    @Override
    public Optional<Book> get(String identifier) {
        //TODO
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        //TODO
        return List.of();
    }

    @Override
    public void update(Book t) {
        //TODO
    }

    @Override
    public void delete(Book t) {
        //TODO
    }
}
