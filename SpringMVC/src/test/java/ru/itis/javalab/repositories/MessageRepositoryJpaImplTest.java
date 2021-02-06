package ru.itis.javalab.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.configuration.TestApplicationConfig;
import ru.itis.javalab.models.Message;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryJpaImplTest {

    private MessageRepository messageRepository;

    private static final int MESSAGE_COUNT=3;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        messageRepository = context.getBean("messageRepositoryJpaImpl", MessageRepository.class);
    }

    @Test
    void save() {
        messageRepository.save(Message.builder().text("TEST").build());
        System.out.println(messageRepository.findAll());

    }
    @Test
    void findById() {
        Message message=Message.builder().text("TEST").build();
        messageRepository.save(message);
        Optional<Message> messageAfterSaving=messageRepository.findById(1L);
        messageAfterSaving.ifPresent(val-> assertEquals(message,val));
    }

    @Test
    void update() {
        Message message=Message.builder().text("TEST").build();
        messageRepository.save(message);
        message.setText("TEST2");
        System.out.println(message.toString());
        messageRepository.update(message);
        Optional<Message> messageAfterUpdating=messageRepository.findById(1L);
        messageAfterUpdating.ifPresent(val -> assertEquals(message,val));
    }

    @Test
    void delete() {
        Message message=Message.builder().text("TEST").build();
        messageRepository.save(message);
        messageRepository.delete(message);
        Optional<Message> messageAfterDeleting=messageRepository.findById(1L);
        assertFalse(messageAfterDeleting.isPresent());
    }

    @Test
    void findAll() {
        Message message=Message.builder().text("TEST").build();
        Message message1=Message.builder().text("TEST").build();
        Message message2=Message.builder().text("TEST3").build();
        messageRepository.save(message);
        messageRepository.save(message1);
        messageRepository.save(message2);
        List<Message> messages = messageRepository.findAll();
        assertEquals(MESSAGE_COUNT,messages.size());
    }
}