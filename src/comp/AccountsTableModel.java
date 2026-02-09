package comp;

import dbase.DAO;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Optional;

public class AccountsTableModel extends AbstractTableModel {

    private final DAO<User> dao;

    private String[] columnNames = {"First Name", "Last Name", "Login", "Role"};
    private final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, Role.class
    };

    public AccountsTableModel(DAO<User> dao) {
        this.dao = dao;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
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
        return columnNames.length;
    }

//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        List<User> userList = null;
//
//        if (Optional.ofNullable(dao).isPresent()) {
//            userList = dao.getAll();
//
//            User user = userList.get(rowIndex);
//            if (0 == columnIndex) {
//                user.setFirstName((String) aValue);
//            } else if (1 == columnIndex) {
//                user.setLastName((String) aValue);
//            } else if (2 == columnIndex) {
//                user.setLogin((String) aValue);
//            } else if (3 == columnIndex) {
//                user.setPrimaryRole((Role) aValue);
//            }
//        }
//    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<User> userList = null;

        if (Optional.ofNullable(dao).isPresent()) {
            userList = dao.getAll();
            User user = userList.get(rowIndex);

            if (0 == columnIndex) {
                return user.getFirstName();
            } else if (1 == columnIndex) {
                return user.getLastName();
            } else if (2 == columnIndex) {
                return user.getLogin();
            } else if (3 == columnIndex) {
                return user.getPrimaryRole();
            }
        }
        return null;
    }
}
