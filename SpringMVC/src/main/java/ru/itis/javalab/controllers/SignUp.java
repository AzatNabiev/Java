package ru.itis.javalab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.AuthDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.BCrypterService;
import ru.itis.javalab.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class SignUp {
    @Autowired
    UserService userService;

    @Autowired
    BCrypterService bCrypterService;

    @GetMapping(value = "/signUp")
    public String showSignUpPage(HttpServletRequest req, HttpServletResponse resp, Model model) {
        if (req.getSession(false) == null) {
            model.addAttribute("error", "");
            return "signUp";
        } else {
            return "signUp";
        }
    }

    @PostMapping(value = "/signUpp")
    public String getUser(HttpServletRequest req, HttpServletResponse resp, AuthDto authDto, Model model) {
        String email = authDto.getEmail();
        String password = authDto.getPassword();
        HttpSession httpSession = req.getSession(false);

        Optional<User> user = userService.findUserByEmail(email);
        if (user.isPresent() && bCrypterService.checkPass(password, user.get().getPassword().trim())) {
            HttpSession httpsession = req.getSession(true);
            httpsession.setAttribute("user", user.get());
            if (user.get().getRole()==2) {
                return "redirect:/userPage";
            }else{
                return "redirect:/adminPage";
            }
        } else {
            model.addAttribute("error", "incorrect log/pas");
            return "/signUp";
        }
    }
}


