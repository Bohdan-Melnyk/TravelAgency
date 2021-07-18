package com.softserve.travelagency.controller;


import com.softserve.travelagency.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String allUsers(Model model){
        model.addAttribute("myusers", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{userId}")
    public String userInfo(@PathVariable("userId") Long id, Model model){
        model.addAttribute("user", userService.readById(id));
        return "user-info";
    }
}
