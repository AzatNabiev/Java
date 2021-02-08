package ru.itis.javalab.repositories;

import ru.itis.javalab.dto.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<PostDto> {
    Optional<PostDto> findFirstPostByUserId(Long id);
    List<PostDto> getAllById(Long useId);
    void delete(Long postId);
    Optional<PostDto> findPostByPostId(Long id);
}
