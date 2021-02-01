package ru.itis.javalab.repositories;


import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    Optional<User> findUserById(Integer id);
    Optional<User> findFirstByEmailAndPassword(String email, String pass);
    Optional<User> findUserByEmail(String email);
    List<User> getAllUsersByShablon(String shablon,Long id);
    List<User> findAll(Long userId);
    void deleteUser(Long userId);
}

