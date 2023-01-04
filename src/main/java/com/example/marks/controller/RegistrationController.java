package com.example.marks.controller;

import com.example.marks.model.Role;
import com.example.marks.model.User;
import com.example.marks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user, Model model){
        User res = userRepository.findByUsername(user.getUsername());
        if(res != null){
            model.addAttribute("result", "User exists!");
            return "registration";
        }

        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";

    }
}
