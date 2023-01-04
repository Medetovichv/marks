package com.example.marks.controller;

import com.example.marks.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{user}")
    public String userDetails(
            @PathVariable(name = "user") User user,
            Model model
    ){
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user-details";
    }

}
