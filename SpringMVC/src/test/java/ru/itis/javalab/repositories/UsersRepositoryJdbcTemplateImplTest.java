package ru.itis.javalab.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.configuration.TestApplicationConfig;
import ru.itis.javalab.models.Role;
import ru.itis.javalab.models.User;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsersRepositoryJdbcTemplateImplTest {

    private static final int USER_COUNT=3;
    private static final int USER_COUNT_25=1;

    private static final User USER= User.builder()
            .id(3L)
            .firstName("asasas")
            .lastName("asasasa")
            .age(25L)
            .email("test4@gmail.com")
            .password("$2a$10$vh4yWullot2rxztSQYK/TOn7Oq85U4T8Q7iWsP3m7pw73GTWZ03U2")
            .role(Role.builder().build())
            .build();

    UsersRepositoryJdbcTemplateImpl usersRepository;


    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        usersRepository=new UsersRepositoryJdbcTemplateImpl(context.getBean(DataSource.class));
    }

    @Test
    void findAll() {
        List<User> users=usersRepository.findAll();
        assertEquals(USER_COUNT,users.size());
    }

    @Test
    void findAllByAge() {
        List<User> users=usersRepository.findAllByAge(25);
        assertEquals(USER_COUNT_25,users.size());
    }

    @Test
    void findUserById() {
        Optional<User> user = usersRepository.findUserById(3);
        user.ifPresent(val->assertEquals(USER,val));
    }

    @Test
    void findFirstByEmailAndPassword() {
        Optional<User> user=usersRepository.findFirstByEmailAndPassword("test4@gmail.com","$2a$10$vh4yWullot2rxztSQYK/TOn7Oq85U4T8Q7iWsP3m7pw73GTWZ03U2");
        user.ifPresent(val-> assertEquals(USER,val));
    }

    @Test
    void getAllUsersByShablon() {
        List<User> users=usersRepository.getAllUsersByShablon("te",3L);
        assertEquals(1,users.size());
    }

    @Test
    void findUserByEmail() {
        Optional<User> user = usersRepository.findUserByEmail("test4@gmail.com");
        user.ifPresent(val-> assertEquals(USER,val));
    }

    @Test
    void update() {
        User userForUpdate = usersRepository.findUserById(3).get();
        userForUpdate.setEmail("TEST_EMAIL");
        usersRepository.update(userForUpdate);
        User userAfterUpdate=usersRepository.findUserById(3).get();
        assertEquals("TEST_EMAIL", userAfterUpdate.getEmail());
    }

    @Test
    void deleteByObject() {
        usersRepository.delete(USER);
        Optional<User> user = usersRepository.findUserById(3);
        assertFalse(user.isPresent());
    }

    @Test
    void deleteUserById() {
        usersRepository.deleteUser(USER.getId());
        Optional<User> user= usersRepository.findUserById(3);
        assertFalse(user.isPresent());
    }

    @Test
    void save() {
        User user=User.builder()
                .firstName("TEST")
                .lastName("TEST")
                .password("TEST")
                .build();
        usersRepository.save(user);
        assertEquals(4,user.getId());

    }
}