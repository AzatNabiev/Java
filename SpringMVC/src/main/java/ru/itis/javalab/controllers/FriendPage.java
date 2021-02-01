package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.FileService;
import ru.itis.javalab.services.PostService;
import ru.itis.javalab.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@Controller
public class FriendPage {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    @GetMapping("/showFriendAvatar")
    public void showFriendPage(HttpServletRequest req, HttpServletResponse resp, HttpSession httpSession) throws IOException {
        HttpSession session=req.getSession(false);
        UserDto user=(UserDto)session.getAttribute("friend");
        System.out.println(user.toString());
        File files= new File("C://files//"+user.getId().toString()+"//main.jpg");
        resp.setContentType("image/jpg");
        resp.setContentLength((int) files.length());
        resp.setHeader("Content-Disposition","inline; filename=\"" + files.getName() + "\"");
        OutputStream out=resp.getOutputStream();
        Files.copy(files.toPath(), out);
        out.close();
    }
}
