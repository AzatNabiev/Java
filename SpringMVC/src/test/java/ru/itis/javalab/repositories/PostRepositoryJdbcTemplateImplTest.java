package ru.itis.javalab.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.configuration.TestApplicationConfig;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryJdbcTemplateImplTest {

    PostRepositoryJdbcTemplateImpl postRepository;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        postRepository = new PostRepositoryJdbcTemplateImpl(context.getBean("postDataSource", DataSource.class));
    }

    @Test
    void findPostByUserId() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void getAll() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testFindAll() {
    }
}