package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.PostDto;
import ru.itis.javalab.repositories.PostRepository;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public void save(PostDto postDto) {
        postRepository.save(postDto);
    }

    @Override
    public void delete(PostDto postDto) {
        postRepository.delete(postDto);
    }

    @Override
    public List<PostDto> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<PostDto> getAll(Long useId) {
        return postRepository.getAll(useId);
    }

    @Override
    public void delete(Long postId) {
        postRepository.delete(postId);
    }
}
