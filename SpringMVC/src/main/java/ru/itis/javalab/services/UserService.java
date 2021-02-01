package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers(Long userId);
    List<UserDto> getAllUsers();
    List<UserDto> findByAge();
    Optional<User> findUserByEmail(String email);
    Optional<UserDto> findUserDtoById(String id);
    List<UserDto> getAllUsers(int page, int size);
    void addUser(UserDto userDto);
    void save(User user);
    List<UserDto> getUsersByShablon(String shablon,Long id);
    void deleteUser(Long userId);
}
