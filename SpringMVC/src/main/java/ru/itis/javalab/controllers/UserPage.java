package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.itis.javalab.dto.PostDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.FileService;
import ru.itis.javalab.services.PostService;
import ru.itis.javalab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Controller
public class UserPage {

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @GetMapping("/showUserAvatar")
    public void showUserPage(HttpServletRequest req, HttpServletResponse resp, HttpSession httpSession) throws IOException {
        HttpSession session=req.getSession(false);
        User user=(User)session.getAttribute("user");
        File file= new File("C://files//"+user.getId().toString()+"//main.jpg");
        resp.setContentType("image/jpg");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition","inline; filename=\"" + file.getName() + "\"");
        OutputStream out=resp.getOutputStream();
        Files.copy(file.toPath(), out);
        file=null;
        out.close();
    }

    @GetMapping("/userPage")
    public String showPage(HttpServletRequest req, HttpServletResponse resp,Model model){
        HttpSession session=req.getSession(false);
        User user=(User)session.getAttribute("user");
        System.out.println(user.toString());
        List<PostDto> post=postService.getAll(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("posts", post);
        return "userPage";
    }

    @PostMapping("/saveFile")
    public String upload(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam CommonsMultipartFile file, HttpSession session) throws IOException {
        Long id =((User) session.getAttribute("user")).getId();
        fileService.saveFileToStorage(file.getInputStream(),file.getName(),file.getContentType(),file.getSize(),id);
        return "redirect:/userPage";
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDto> showPage(@RequestParam("email") String emailShablon,HttpSession httpSession){
        Long userId=((User)httpSession.getAttribute("user")).getId();
        List<UserDto> users=userService.getUsersByShablon(emailShablon, userId);
        System.out.println(users);
        return users;
    }

    @GetMapping("/showFriend/{user-id}")
    public String showFriendPage(HttpServletResponse resp, @PathVariable("user-id") String userId
            , Model model, HttpSession httpSession) throws IOException {
        Optional<UserDto> user=userService.findUserDtoById(userId);
        List<PostDto> postDto=postService.getAll(Long.parseLong(userId));
        if (user.isPresent()){
            httpSession.setAttribute("friend",user.get());
            model.addAttribute("posts", postDto);
            model.addAttribute("friend",user.get());
            return "friendPage";
        }else {
            return "error";
        }
    }
}
