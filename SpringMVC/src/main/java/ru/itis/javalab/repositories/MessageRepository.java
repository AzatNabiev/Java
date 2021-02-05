package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Message;

import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message> {
    Optional<Message> findById(Long id);
}
