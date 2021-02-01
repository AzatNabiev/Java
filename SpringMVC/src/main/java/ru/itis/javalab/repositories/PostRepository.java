package ru.itis.javalab.repositories;

import ru.itis.javalab.dto.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<PostDto> {
    Optional<PostDto> findPostByUserId(Long id);
    List<PostDto> getAll(Long useId);
    void delete(Long postId);
}
