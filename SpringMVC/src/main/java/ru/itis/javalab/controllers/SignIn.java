package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@Controller
public class SignIn {
    @Autowired
    UserService userService;

    @Autowired
    Validator validator;

    @GetMapping("/signIn")
    public String showSignInPage(Model model) {
        model.addAttribute("errors", new ArrayList<>());
        return "signIn";
    }

    @PostMapping("/signInn")
    public String signIn(User user, Model model) {
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        if (!constraintViolations.isEmpty()) {
            model.addAttribute("errors", constraintViolations);
            return "/signIn";
        } else {
            user.setConfirmCode(UUID.randomUUID().toString());
            userService.save(user);
            return "redirect:/signUp";
        }
    }
}


