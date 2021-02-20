package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.util.EmailUtil;
import ru.itis.javalab.util.MailsGenerator;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier(value = "userRepository")
    UsersRepository usersRepository;

    @Autowired
    @Qualifier(value = "bcrypt")
    PasswordEncoder passwordEncoder;

    @Autowired
    MailsGenerator mailsGenerator;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public List<UserDto> getUsersByShablon(String shablon,Long id) {
        return UserDto.from(usersRepository.getAllUsersByShablon(shablon,id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public List<UserDto> getAllUsers(Long userId) {
        return UserDto.from(usersRepository.findAll(userId));
    }

    @Override
    public List<UserDto> findByAge() {
        return null;
    }

    @Override
    public Optional<UserDto> findUserDtoById(String id) {
        Optional<User> user=usersRepository.findUserById(Integer.parseInt(id));
        return  Optional.ofNullable(UserDto.from(user.get()));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email);
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        return null;
    }

    @Override
    public void addUser(UserDto userDto) {

    }

    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteUser(userId);
    }

    @Override
    public void save(User user) {
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        //usersRepository.save(user);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl,user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(),"Registration", from, confirmMail);
    }
}
