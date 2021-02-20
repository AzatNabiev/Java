package ru.itis.javalab.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

@Profile("dev")
@Repository
@Qualifier(value = "userRepository")
public class UsersRepositoryFakeImpl implements UsersRepository {
    @Override
    public List<User> findAllByAge(Integer age) {
        return null;
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return Optional.of(User.builder()
        .firstName("TEST")
        .lastName("TEST_2")
        .build());
    }

    @Override
    public Optional<User> findFirstByEmailAndPassword(String email, String pass) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsersByShablon(String shablon, Long id) {
        return null;
    }

    @Override
    public List<User> findAll(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(int page, int size) {
        return null;
    }
}
