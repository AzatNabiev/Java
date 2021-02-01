package ru.itis.javalab.services;

import ru.itis.javalab.dto.PostDto;

import java.util.List;

public interface PostService {
    void save(PostDto postDto);
    void delete(PostDto postDto);
    List<PostDto> getAll();
    List<PostDto> getAll(Long useId);
    void delete(Long postId);
}
