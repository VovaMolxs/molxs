package com.chat.molxs.controller;

import com.chat.molxs.entity.Message;
import com.chat.molxs.entity.User;
import com.chat.molxs.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

   @GetMapping("/index")
    public String index(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String add(
            @AuthenticationPrincipal User user,
            String text,
            Map<String, Object> model
                    )

    {

        Message message = new Message(text, user);
        messageRepository.save(message);

        return "index";
    }

}
