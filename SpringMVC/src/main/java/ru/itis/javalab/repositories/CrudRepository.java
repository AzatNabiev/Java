package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Message;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);

    List<T> findAll();
    List<T> findAll(int page, int size);

}
