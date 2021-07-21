package com.softserve.travelagency.controller;

import com.softserve.travelagency.model.Status;
import com.softserve.travelagency.model.User;
import com.softserve.travelagency.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        user.setStatus(Status.ACTIVE);
        userService.create(user);
        return "login";
    }

}
