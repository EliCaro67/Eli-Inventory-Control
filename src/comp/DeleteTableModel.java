package comp;

import dbase.DAO;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Optional;

public class DeleteTableModel extends AbstractTableModel {
    private final DAO<Book> dao;

    private String[] deleteColumnNames = {"Author", "Title", "Genre", "Availability", "Isbn"};
    private final Class[] columnClass = new Class[]{
            String.class, String.class, Role.class, boolean.class, String.class
    };

    public DeleteTableModel(DAO<Book> dao) {
        this.dao = dao;
    }

    @Override
    public String getColumnName(int column) {
        return deleteColumnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getRowCount() {
        if (Optional.ofNullable(dao).isPresent()) {
            return dao.getAll().size();
        }
        return -1;
    }

    @Override
    public int getColumnCount() {
        return deleteColumnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        List<Book> bookList = null;

        if (Optional.ofNullable(dao).isPresent()) {
            bookList = dao.getAll();
            Book book = bookList.get(i);

            if (0 == i1) {
                return book.getAuthor();
            } else if (1 == i1) {
                return book.getTitle();
            } else if (2 == i1) {
                return book.getGenre();
            } else if (3 == i1) {
                return book.isAvailable();
            }else if (4 == i1) {
                return book.getIsbn();
            }
        }

        return null;
    }
}
