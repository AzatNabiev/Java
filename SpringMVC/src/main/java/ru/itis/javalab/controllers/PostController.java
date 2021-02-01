package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.PostDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.PostService;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/sendNewPost")
    public String sendNewPost( PostDto postDto,
                                HttpSession httpSession){
        Long userId=((User)httpSession.getAttribute("user")).getId();
        postDto.setUserId(userId);
        postService.save(postDto);
        return "redirect:/userPage";
    }

    @PostMapping("/deletePost/{post-id}")
    public String deletePost(@PathVariable("post-id") Long postId){
        postService.delete(postId);
        return "redirect:/userPage";
    }
}
