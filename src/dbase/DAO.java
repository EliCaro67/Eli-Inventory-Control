package dbase;

import java.util.List;
import java.util.Optional;

public interface DAO<Item> {

        // Create
        void save(Item t);

        // Retrieve one
        Optional<Item> get(String identifier);

        // Retrieve all
        List<Item> getAll();

        // Update
        void update(Item t);

        // Destroy
        void delete(Item t);
}
