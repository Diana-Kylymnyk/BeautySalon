package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class RegistrationController {

    @Autowired
    private MessageSource messageSource;

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model, Locale locale) {
        if (userService.loadUserByUsername(user.getUsername()) != null) {
            model.addAttribute("emailError", messageSource.getMessage("not.unique.email", null, locale));
            return "registration";
        }
        userService.saveNewUser(user);
        return "redirect:/login";
    }
}
