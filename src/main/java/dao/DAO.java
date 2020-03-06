package dao;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(int id);

    Collection<T> getAll();

    void add(T newItem);

    void remove(int id);
}
