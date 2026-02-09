package dbase;

import common.Constants;
import common.Utils;
import comp.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PersonnelDAO implements DAO<User>, Constants {

    // Hash map is keyed by unique login
    private HashMap<String/* login */, User> userDB;

    public PersonnelDAO() {
        loadOrInitializeMap();
    }

    private void loadOrInitializeMap() {
        try {
            userDB = Utils.loadFromFile(Constants.PERSONNEL_DB);
        } catch (IOException e) {
            // EOF seen - overwrite contents.
            userDB = new HashMap<>();
            Utils.commitToFile(userDB, PERSONNEL_DB);
        }
    }

    @Override
    public void save(User t) {
        userDB.put(t.getLogin(), t);
        Utils.<HashMap<String, User>>commitToFile(userDB, PERSONNEL_DB);
    }

    @Override
    public Optional<User> get(String login) {
        User user = userDB.get(login);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        for (String key : userDB.keySet()) {
            list.add(userDB.get(key));
        }
        return list;
    }

    @Override
    public void update(User t) {
        userDB.put(t.getLogin(), t);
        Utils.commitToFile(userDB, PERSONNEL_DB);
    }

    @Override
    public void delete(User t) {
        userDB.remove(t.getLogin());
        Utils.commitToFile(userDB, PERSONNEL_DB);
    }
}
