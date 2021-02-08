package ru.itis.javalab.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.configuration.TestApplicationConfig;
import ru.itis.javalab.dto.PostDto;

import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryJdbcTemplateImplTest {

    PostRepositoryJdbcTemplateImpl postRepository;

    private static final int POST_COUNT=4;
    private static final int USER_POST_COUNT=2;

    private static final PostDto postDto=PostDto.builder()
            .text("TEST_TEXT5")
            .userId(2L)
            .build();
    private static final PostDto CopyOfPostDtoFromDb=PostDto.builder()
            .postId(1L)
            .text("TEST_TEXT")
            .userId(1L)
            .build();

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        postRepository = new PostRepositoryJdbcTemplateImpl(context.getBean("postDataSource", DataSource.class));
    }


    @Test
    void update() {
        PostDto postForUpdate=postRepository.findPostByPostId(1L).get();
        postForUpdate.setText("TEST_TEXT_CHANGED");
        postRepository.update(postForUpdate);
        PostDto postAfterUpdate=postRepository.findPostByPostId(1L).get();
        assertEquals("TEST_TEXT_CHANGED",postAfterUpdate.getText());
    }

    @Test
    void findPostByPostId(){
        Optional<PostDto> postDto=postRepository.findPostByPostId(1L);
        postDto.ifPresent(val->assertEquals(CopyOfPostDtoFromDb,postDto.get()));
    }

    @Test
    void findFirstPostByUserId() {
    }

    @Test
    void findAll() {
        List<PostDto> postDtos = postRepository.findAll();
        assertEquals(POST_COUNT,postDtos.size());

    }

    @Test
    void getAllById() {
        List<PostDto> postDtos=postRepository.getAllById(1L);
        assertEquals(USER_POST_COUNT,postDtos.size());
    }

    @Test
    void delete() {
        List<PostDto> postsBeforeDeleting=postRepository.findAll();
        postRepository.delete(2L);
        List<PostDto>postsAfterDeleting=postRepository.findAll();
        assertEquals(postsBeforeDeleting.size()-1,postsAfterDeleting.size());
    }

    @Test
    void save() {
        List<PostDto> postsBeforeSaving=postRepository.findAll();
        System.out.println(postsBeforeSaving.size());
        postRepository.save(postDto);
        List<PostDto> postsAfterSaving=postRepository.findAll();
        System.out.println();
        assertEquals(postsBeforeSaving.size()+1,postsAfterSaving.size());
    }
}
