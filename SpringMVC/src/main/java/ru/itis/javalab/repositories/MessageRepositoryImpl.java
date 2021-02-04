package ru.itis.javalab.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
//TODO:реализовать repo
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(MessageRepository entity) {

    }

    @Override
    public void update(MessageRepository entity) {

    }

    @Override
    public void delete(MessageRepository entity) {

    }

    @Override
    public List<MessageRepository> findAll() {
        return null;
    }

    @Override
    public List<MessageRepository> findAll(int page, int size) {
        return null;
    }
}
