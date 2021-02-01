package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.DataService;
import ru.itis.javalab.services.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    DataService dataService;

    @GetMapping("/adminPage")
    public String showAdminPage(Model model, HttpSession httpSession){
        Long userId=((User)httpSession.getAttribute("user")).getId();
        List<UserDto> users=userService.getAllUsers(userId);
        model.addAttribute("users",users);
        return "adminPage";
    }

    @PostMapping("/deleteUser/{user-id}")
    public String deleteUser(@PathVariable(value = "user-id")Long userId) throws IOException {
        dataService.deleteAllDataOfUser(userId);
        userService.deleteUser(userId);
        return "redirect:/adminPage";
    }
}
