package com.chat.molxs.controller;

import com.chat.molxs.entity.User;
import com.chat.molxs.repository.Role;
import com.chat.molxs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        } user.setActivity(true);
            user.setRoles(Collections.singleton(Role.ROLE));
            userRepository.save(user);

        return "redirect:/login";
    }
}
